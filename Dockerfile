# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar chatbot.jar

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "chatbot.jar"]
