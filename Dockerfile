# Stage 1: Build stage with Maven and Alpine
FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn package

# Stage 2: Runtime stage with Alpine and OpenJDK
FROM openjdk:17-slim AS runtime

WORKDIR /app

# Copy the built artifacts from the previous stage
COPY --from=builder /app/target/Spring_Boot-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port(s) on which your application listens (replace 8080 with your actual port)
EXPOSE 8080

# Set the entrypoint command to run your application
CMD ["java", "-jar", "app.jar"]
