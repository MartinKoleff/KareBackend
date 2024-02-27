FROM maven:3.8.4-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

# Set up environment variables
ENV PORT=8080
ENV HOST=0.0.0.0
# Expose the application's port
EXPOSE 8080

RUN #chmod +x /cloud_sql_proxy

ENTRYPOINT ["java", "-jar", "/kare-0.0.1-SNAPSHOT.jar"]