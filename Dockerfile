# Use Maven with OpenJDK for the build stage
FROM maven:3.8.3-openjdk-17 AS build

LABEL authors="krittamettanboontor"

# Set working directory inside the container
WORKDIR /app

# Clone the Git repository into the container
RUN git clone https://github.com/Deathrow002/bbl_backend_crud.git .

# Run Maven to download dependencies and build the application
RUN mvn clean install -DskipTests

# Check if the JAR file is generated correctly
RUN ls -l /app/target/

# Build the application and package it
RUN mvn package -DskipTests

# Use OpenJDK 17 slim for running the application (runtime stage)
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]