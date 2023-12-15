FROM maven:3-eclipse-temurin-21 AS builder

LABEL MAINTAINER="khairulimran6810@gmail.com"
LABEL APPLICATION="News Application"

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN mvn package -Dmaven.test.skip=true

# Publish stage
FROM openjdk:21-jdk

ARG APP_DIR2=/app 
WORKDIR ${APP_DIR2}

COPY --from=builder /app/target/eventmanagement-0.0.1-SNAPSHOT.jar eventapp.jar 

# Need these for the environment variables
ENV PORT=8080
#Copy these to your .txt file
ENV SPRING_REDIS_HOST=localhost 
ENV SPRING_REDIS_PORT=1234
ENV SPRING_REDIS_DATABASE=0
ENV SPRING_REDIS_USERNAME=default 
ENV SPRING_REDIS_PASSWORD=abc123

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar eventapp.jar