FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/franqui-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/wallet /app/src/main/resources/wallet

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod","--server.port=8082"]