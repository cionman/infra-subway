## Dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV spring.profiles.active prod
ENTRYPOINT ["java","-jar","/app.jar"]