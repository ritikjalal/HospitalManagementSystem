FROM openjdk:17-jdk-slim

LABEL maintainer="hospital-webhook-team"

# Install necessary packages
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Create app directory
WORKDIR /app

# Copy maven wrapper and pom.xml first for better caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create logs directory
RUN mkdir -p /app/logs

# Expose port
EXPOSE 8094

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8094/api/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "target/HospitalwebHook-0.0.1-SNAPSHOT.jar"]
