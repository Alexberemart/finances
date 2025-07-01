#!/bin/bash
#
# Starts the development environment for the Finances project.
#
# This script performs the following steps:
#   1. Loads environment variables from .env.
#   2. Checks if Docker Desktop is running.
#   3. Builds the project with Maven and runs tests.
#   4. Starts a temporary MariaDB test container (using docker-compose.migration.yml).
#   5. Waits for the test MariaDB to be healthy.
#   6. Runs Flyway migrations against the test database to validate all migrations.
#   7. Stops and removes the temporary MariaDB test container.
#   8. If migrations succeed, builds and starts the main Docker Compose services.
#   9. Shows the logs for the Spring Boot application container.
#
# Usage: ./start-dev.sh [profile]
# Default profile is 'local'.

PROFILE="${1:-local}"

# 1. Load environment variables from .env file
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

# 2. Check if Docker is running
if ! docker info > /dev/null 2>&1; then
  echo "ERROR: Docker Desktop is not running. Please start Docker Desktop and try again." >&2
  exit 1
fi

# 3. Build the project with Maven and run tests
mvn clean verify
if [ $? -ne 0 ]; then
  echo "Maven build (with coverage) failed. Exiting." >&2
  exit 1
fi

echo "Testing Flyway migrations on a fresh, isolated MariaDB test container..."

# 4. Start test MariaDB container using a separate docker-compose file
docker-compose -f docker-compose.migration.yml up -d mariadb-test

# 5. Wait for healthcheck on the test MariaDB container
echo "Waiting for test MariaDB to be healthy..."
until [ "$(docker inspect --format='{{.State.Health.Status}}' finances-mariadb-test)" == "healthy" ]; do
  sleep 2
done

# 6. Run Flyway migrations against the test database
./mvnw flyway:migrate \
  -Dflyway.url=jdbc:mariadb://localhost:3307/$MARIADB_TEST_DATABASE \
  -Dflyway.user=root \
  -Dflyway.password="$MARIADB_TEST_PASSWORD"
MIGRATION_RESULT=$?

# 7. Stop and remove the test MariaDB container
docker-compose -f docker-compose.migration.yml rm -sf mariadb-test

# 8. If migrations failed, exit; otherwise, continue to start main environment
if [ $MIGRATION_RESULT -ne 0 ]; then
  echo "Flyway migration failed on fresh test database. Exiting." >&2
  exit 1
fi

echo "Flyway migrations succeeded on test database. Starting full dev environment..."

export SPRING_PROFILES_ACTIVE="$PROFILE"
docker-compose build --no-cache
docker-compose up -d

# 9. Show logs for the Spring Boot app container
docker-compose logs -f app