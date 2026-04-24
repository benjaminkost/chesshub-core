FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ARG PROJECT_VERSION

COPY target/core-$PROJECT_VERSION.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]