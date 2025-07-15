FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gerenciador-de-clientes-0.0.1-SNAPSHOT.jar app.jar

LABEL authors="Plinio Alcântara" description="Backend API para o sistema de gerenciamento de clientes"


ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080