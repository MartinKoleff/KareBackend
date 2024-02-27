#FROM maven:3.8.4-openjdk-17 as build
#COPY . .
##Clean and install jar
#RUN mvn clean package
#
## Copy your built application JAR
#COPY target/kare-0.0.1-SNAPSHOT.jar kare-0.0.1-SNAPSHOT.jar
#
##Download the Cloud SQL Proxy
#ADD https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 /cloud_sql_proxy
#RUN chmod +x /cloud_sql_proxy
#
##Start the Cloud SQL Proxy and then your application
#CMD ./cloud_sql_proxy -instances=your-instance-connection-name=tcp:3306 & java -jar kare-0.0.1-SNAPSHOT.jar

FROM maven:3.8.4-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

EXPOSE 8080
COPY target/kare-0.0.1-SNAPSHOT.jar  kare-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/kare-0.0.1-SNAPSHOT.jar"]