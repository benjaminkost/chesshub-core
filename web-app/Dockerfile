# Basisimage
FROM openjdk:17-jdk-slim

# Arbeitsverzeichnis im Container erstellen
WORKDIR /app

# Das JAR mit Abhängigkeiten ins Container-Verzeichnis kopieren
COPY target/web-app-0.0.1-SNAPSHOT.jar /app/app.jar

# Spring Boot-Anwendung starten
ENTRYPOINT ["java", "-jar", "/app/app.jar"]