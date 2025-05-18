# Base image with OpenJDK 17 and Gradle to build
FROM gradle:7.6.1-jdk17 AS builder

WORKDIR /app

# Copy the entire project to the container
COPY . .

# Build the project with Gradle (without a daemon)
RUN gradle build --no-daemon

# Lightweight image with only Java to run the .jar
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the .jar file from the builder stage
COPY --from=builder /app/build/libs/TodoTechProject-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Command to run the app
CMD ["java", "-jar", "TodoTechProject-0.0.1-SNAPSHOT.jar"]
