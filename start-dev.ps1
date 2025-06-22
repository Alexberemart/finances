<#
.SYNOPSIS
    Starts the development environment for the Finances project.

.DESCRIPTION
    This script performs the following steps:
    1. Checks if Docker Desktop is running. If not, shows a clear error and exits.
    2. Builds the project using Maven.
    3. Removes any existing 'finances-mysql' container to avoid conflicts.
    4. Builds and starts the Docker Compose services.
    5. Shows the logs for the Spring Boot application container.

.NOTES
    Make sure Docker Desktop is running before executing this script.
    Run this script from the project root directory.
#>

# Check if Docker is running
$dockerRunning = docker info 2>$null

if (-not $dockerRunning) {
    Write-Host "ERROR: Docker Desktop is not running. Please start Docker Desktop and try again." -ForegroundColor Red
    exit 1
}

# Build the project with Maven
$mvn = Start-Process mvn -ArgumentList 'clean', 'package' -NoNewWindow -Wait -PassThru
if ($mvn.ExitCode -ne 0) {
    Write-Host "Maven build failed. Exiting."
    exit $mvn.ExitCode
}

# Remove existing finances-mysql container if it exists
$container = docker ps -a --filter "name=finances-mysql" --format "{{.ID}}"
if ($container) {
    Write-Host "Removing existing finances-mysql container..."
    docker rm -f finances-mysql
}

# Build and start Docker Compose services
docker-compose build --no-cache
docker-compose up -d

# Show logs for the Spring Boot app container
docker-compose logs -f app