# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the entire project into the container
COPY . /app

# Install Maven (or Gradle)
RUN apt-get update && apt-get install -y maven

# Build the application (this will generate the target folder and .jar file)
RUN ./mvnw clean package

# Copy the generated JAR file into the container
#COPY target/mailapis-0.0.1-SNAPSHOT.jar /app/app.jar
# Make the JAR file executable
#RUN chmod +x /app/app.jar

# Run the application
CMD ["java", "-jar"]
