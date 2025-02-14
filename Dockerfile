# Builder stage of the spring boot app
FROM gradle:8.12-jdk23 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build

# Final stage of the spring boot app
FROM openjdk:23-jdk-slim AS production

WORKDIR /app

COPY ./build/libs/api-sensores-0.0.1-SNAPSHOT.jar /app/api-sensores.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/api-sensores.jar"]