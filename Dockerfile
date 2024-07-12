# Start with a base image that has Java 17 installed
FROM openjdk:17
# Copy the compiled application JAR file from the builder stage
COPY ./target/*.jar rest-api-image.jar
# Expose the port that the application uses (if necessary)
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "rest-api-image.jar"]
