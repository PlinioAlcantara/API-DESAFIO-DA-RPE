FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gerenciador-de-clientes-0.0.1-SNAPSHOT.jar app.jar

LABEL authors="Plinio Alcântara" description="Backend API para o sistema de gerenciamento de clientes"

# Definir a variável de ambiente JAVA_OPTS para configurações do Spring Boot
ENV JAVA_OPTS="-Dspring.datasource.url=${SPRING_DATASOURCE_URL} -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} -Dspring.application.name=gerenciador-de-clientes"

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
