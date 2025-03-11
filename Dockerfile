# Use Maven with OpenJDK for the build stage
FROM maven:3.8.3-openjdk-17 AS build

LABEL authors="krittamettanboontor"

# Set working directory inside the container
WORKDIR /app

# Copy the Maven pom.xml and download dependencies first to leverage Docker cache
COPY pom.xml ./
RUN mvn clean install -DskipTests

# Copy the rest of the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Use OpenJDK 17 slim for running the application (runtime stage)
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven from the build stage
COPY --from=build /app/target/bbl-crud-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
