# Builder stage of the spring boot app
FROM openjdk:23-jdk-slim AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build

# Final stage of the spring boot app
FROM openjdk:23-jdk-slim AS production

WORKDIR /app

COPY --from=builder /app/build/libs/api-sensores-0.0.1-SNAPSHOT.jar ./api-sensores.jar
COPY ./.env ./.env

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/api-sensores.jar"]