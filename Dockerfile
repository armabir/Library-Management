# Use Java 17 (matches Spring Boot 3+)
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (to leverage Docker cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable (important!)
RUN chmod +x mvnw

# Download dependencies (offline)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Build the Spring Boot application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Render maps this automatically)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/*.jar"]
