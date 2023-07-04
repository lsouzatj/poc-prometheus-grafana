FROM openjdk:19-jdk
WORKDIR /app
COPY ./target/monitoring-observability-0.0.1-SNAPSHOT.jar app.jar
COPY . .
EXPOSE 8080
ENTRYPOINT java -jar app.jar