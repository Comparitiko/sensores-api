# Builder stage of the spring boot app
FROM openjdk:23-jdk-slim AS builder

WORKDIR /app

COPY . .

# Create the jar file
RUN ./gradlew build

# Final stage of the spring boot app
FROM openjdk:23-jdk-slim AS production

ARG APP_PORT=8080

WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/build/libs/api-sensores-0.0.1-SNAPSHOT.jar ./api-sensores.jar

# Copy the entrypoint script and give it execution rights
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE ${APP_PORT}

# Check if the environment variables are set
ENTRYPOINT ["/entrypoint.sh"]

# Run the application
CMD ["java", "-jar", "/app/api-sensores.jar"]