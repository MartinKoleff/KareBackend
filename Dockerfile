FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app
RUN mvn clean package
RUN mvn install -DskipTests

FROM openjdk:17
WORKDIR /app
COPY target/kare-0.0.1-SNAPSHOT.jar  kare-0.0.1-SNAPSHOT.jar

EXPOSE 8080
CMD ["java", "-jar", "kare-0.0.1-SNAPSHOT.jar"]