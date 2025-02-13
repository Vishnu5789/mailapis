# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Install Maven and build the application
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

# Use a smaller final image to run the app
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy only the built JAR file from the previous stage
COPY --from=build /app/target/mailapis-0.0.1-SNAPSHOT.jar /app/app.jar

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
