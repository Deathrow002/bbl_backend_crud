
FROM maven:3.8.3-openjdk-17 AS build

LABEL authors="krittamettanboontor"

WORKDIR /app

RUN git clone https://github.com/Deathrow002/bbl_backend_crud.git .

#COPY pom.xml .
#COPY src /app/src

RUN mvn clean install -DskipTests

RUN ls -l /app/target/

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]