#!/bin/bash
#
# Starts the development environment for the Finances project.
#
# This script performs the following steps:
#   1. Checks if Docker Desktop is running. If not, shows a clear error and exits.
#   2. Builds the project using Maven.
#   3. Removes any existing 'finances-mysql' container to avoid conflicts.
#   4. Builds and starts the Docker Compose services.
#   5. Shows the logs for the Spring Boot application container.
#
# Usage: ./start-dev.sh [profile]
# Default profile is 'local'.

PROFILE="${1:-local}"

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
  echo "ERROR: Docker Desktop is not running. Please start Docker Desktop and try again." >&2
  exit 1
fi

# Build the project with Maven and check coverage
mvn clean verify
if [ $? -ne 0 ]; then
  echo "Maven build (with coverage) failed. Exiting." >&2
  exit 1
fi

# Remove existing finances-mysql container if it exists
CONTAINER_ID=$(docker ps -a --filter "name=finances-mysql" --format "{{.ID}}")
if [ -n "$CONTAINER_ID" ]; then
  echo "Removing existing finances-mysql container..."
  docker rm -f finances-mysql
fi

# Set the profile for docker-compose
export SPRING_PROFILES_ACTIVE="$PROFILE"

# Build and start Docker Compose services
docker-compose build --no-cache
docker-compose up -d

# Show logs for the Spring Boot app container
docker-compose logs -f app
