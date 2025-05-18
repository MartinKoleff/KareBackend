#FROM jelastic/maven:3.9.1-openjdk-20.0.1 AS build
#COPY . .
#RUN mvn install -DskipTests
#RUN mvn clean package -DskipTests
#
#EXPOSE 8080

FROM openjdk:17-jdk-alpine
COPY . .
RUN mvn clean install

COPY target/kare-0.0.1-SNAPSHOT.jar  kare-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/kare-0.0.1-SNAPSHOT.jar"]