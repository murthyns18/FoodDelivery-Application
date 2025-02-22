# Use official Tomcat 10.1 image
FROM tomcat:10.1

# Copy the WAR file to Tomcat's webapps directory
COPY FoodApplication.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
