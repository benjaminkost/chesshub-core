FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/core-1.0.0.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]