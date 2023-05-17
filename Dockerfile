# Get Base-Image
FROM amd64/tomcat:8.5.87

# Set the working directory to the Tomcat webapps directory
WORKDIR /usr/local/tomcat/webapps

# Rename the .war file to ROOT.war
COPY ./target/ChessGameManagement-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
