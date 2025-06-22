# Use a Java runtime as base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the jar
COPY target/finances-0.0.1-SNAPSHOT.war app.war

# Expose port (change if your app uses a different port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.war"]