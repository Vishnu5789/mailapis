# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /home/app/
COPY target/mailapis-0.0.1-SNAPSHOT.jar /home/app/

# Make the JAR file executable
RUN chmod +x /app/app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
