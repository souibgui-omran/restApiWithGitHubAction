# Start with a base image that has Java 17 installed
FROM openjdk:17-jdk-slim as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN ./mvnw clean package -DskipTests

# Create a new image with only the JRE and the built application
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled application JAR file from the builder stage
COPY --from=builder /app/target/*.jar ./app.jar

# Expose the port that the application uses (if necessary)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
