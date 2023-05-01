# Get Base-Image
FROM tomcat:8.5.87

# Set the working directory to the Tomcat webapps directory
WORKDIR /usr/local/tomcat/webapps

# Copy the .war file to the webapps directory
COPY ./target/ChessGameManagement-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose the default Timcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]