FROM eclipse-temurin:17
WORKDIR /app
COPY target/transmit-between-ports-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-cp", "app.jar", "com.socketapp.Server"]