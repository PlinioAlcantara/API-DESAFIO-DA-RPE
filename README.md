# Backend - Desafio Técnico - Evoluir by RPE

## Visão Geral

Este repositório contém a implementação do backend para o sistema de gerenciamento de clientes e faturas, desenvolvido utilizando **Java 21**, **Spring Boot**, **Spring Data** e **MySQL**. A aplicação expõe uma API RESTful para gerenciar clientes, faturas e pagamentos. A arquitetura do sistema foi construída para lidar com as principais regras de negócio do desafio técnico.

## Funcionalidades

- **Gerenciamento de Clientes**: Criação, atualização, consulta e bloqueio de clientes.
- **Gerenciamento de Faturas**: Exibição das faturas de um cliente, registro de pagamentos e visualização de faturas em atraso.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Swagger** para documentação da API
- **JUnit** para testes unitários
- **Docker** para conteinerização

## Endpoints Implementados

1. **Clientes**
    - `GET /clientes`: Lista todos os clientes.
    - `POST /clientes`: Cadastra um novo cliente.
    - `GET /clientes/{id}`: Consulta um cliente pelo ID.
    - `PUT /clientes/{id}`: Atualiza ou bloqueia um cliente.
    - `GET /clientes/bloqueados`: Lista clientes bloqueados.

2. **Faturas**
    - `GET /faturas/{id}`: Lista todas as faturas de um cliente.
    - `PUT /faturas/{id}/pagamento`: Registra o pagamento de uma fatura.
    - `GET /faturas/atrasadas`: Lista faturas em atraso.

## Arquitetura

- **Spring Security**: Configurado para permitir acesso sem autenticação.
- **Swagger**: Implementado para documentar a API de forma interativa, permitindo que os desenvolvedores visualizem os endpoints e testem as requisições diretamente pela interface.
- **MySQL**: Utilizado para persistência de dados, com configurações no arquivo `application.properties`.
- **Docker**: A aplicação está configurada para rodar dentro de containers Docker, incluindo o banco de dados MySQL.

## Como Executar

### Com Docker

Este projeto pode ser facilmente executado utilizando Docker. Siga as etapas abaixo para executar a aplicação:

1. Clone este repositório.
2. Navegue até o diretório onde o arquivo `docker-compose.yml` está localizado.
3. Execute o comando:
   ```bash
   
   docker-compose up --build
   ````
### Sem Docker

1. Clone este repositório.

2. Navegue até o diretório do backend.

3. Compile o projeto utilizando Maven ou Gradle.

4. Execute a aplicação com o comando:

```bash

mvn spring-boot:run

ou

./gradlew bootRun

```
## Configuração do Banco de Dados

A aplicação utiliza o MySQL. As configurações do banco de dados estão no arquivo application.properties:

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/gerenciador_de_clientes?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
````

## Dockerfile
O Dockerfile está configurado para rodar a aplicação Spring Boot dentro de um container. O comando para construir a imagem é:

```bash

docker build -t gerenciador-de-clientes-backend .

```
## Testes
Testes unitários estão implementados utilizando JUnit. Para rodar os testes, execute o comando:

```bash

mvn test
````
## OBSERVAÇÕES

As imagens do docker foram geradas, na minha máquina, mas na hora de fazer a implementação dentro do container, a conexão entre o back e front acabam falhando, e não consegui resolver esse erro antes do horário da entrega. 






