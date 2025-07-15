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

## Scrpits para sinserção de clientes

```SQL

INSERT INTO cliente (nome, cpf, data_nascimento, status_bloqueio, limite_credito)
VALUES
   ('João Silva', '12345678901', '1985-02-15', 'A', 1500.00),  -- Cliente Ativo
   ('Maria Oliveira', '23456789012', '1990-07-20', 'A', 2000.00),  -- Cliente Ativo
   ('Pedro Santos', '34567890123', '1982-12-05', 'B', 0.00),  -- Cliente Bloqueado
   ('Ana Costa', '45678901234', '1995-03-12', 'A', 1200.00),  -- Cliente Ativo
   ('Carlos Pereira', '56789012345', '1980-09-30', 'B', 0.00);  -- Cliente Bloqueado
```

## Scripts para inserção de Faturas

```
-- Inserir Faturas
INSERT INTO fatura (cliente_id, data_vencimento, data_pagamento, valor, status)
VALUES
  -- Faturas do Cliente João Silva (ID 1)
  (1, '2023-05-15', NULL, 500.00, 'B'),  
  (1, '2023-06-15', '2023-06-10', 600.00, 'P'),  

  -- Faturas do Cliente Maria Oliveira (ID 2)
  (2, '2023-07-20', NULL, 700.00, 'A'),  
  (2, '2023-08-10', '2023-08-01', 800.00, 'P'),  

  -- Faturas do Cliente Pedro Santos (ID 3)
  (3, '2023-04-01', NULL, 400.00, 'A'),  
  (3, '2023-05-10', NULL, 300.00, 'B'),  

  -- Faturas do Cliente Ana Costa (ID 4)
  (4, '2023-09-15', NULL, 250.00, 'B'),  
  (4, '2023-10-05', '2023-10-02', 400.00, 'P'),  

  -- Faturas do Cliente Carlos Pereira (ID 5)
  (5, '2023-06-20', '2023-06-15', 350.00, 'P'),  
  (5, '2023-07-01', NULL, 450.00, 'A');  
```

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





