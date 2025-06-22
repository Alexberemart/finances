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

docker-compose build --no-cache
docker-compose up -d

# Show logs for the Spring Boot app container
docker-compose logs -f app