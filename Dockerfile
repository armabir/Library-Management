# Use Java 21 (matches your Spring Boot project)
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (to leverage Docker cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable (important for Linux/Docker)
RUN chmod +x mvnw

# Download dependencies (offline)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Build the Spring Boot application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Render maps this automatically)
EXPOSE 8080

# Find the Spring Boot fat jar dynamically
CMD java -jar target/*-SNAPSHOT.jar
