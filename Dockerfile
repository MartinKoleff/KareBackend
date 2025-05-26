#FROM jelastic/maven:3.9.1-openjdk-20.0.1 AS build
#COPY . .
#RUN mvn install -DskipTests
#RUN mvn clean package -DskipTests
#
#EXPOSE 8080

# Step 1: Build stage with Maven
FROM maven:3.9.1-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run stage with only the JAR
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/kare-0.0.1-SNAPSHOT.jar kare-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kare-0.0.1-SNAPSHOT.jar"]