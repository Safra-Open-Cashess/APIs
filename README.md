![LOGO](https://github.com/Safra-Open-Cashless/App/blob/main/Assets/github-app.png?raw=true)

# Backend Safra Open Cashless
Arranjo de APIs/estruturas do backend do sistema Safra Open Cashless

## Motivação
Aplicações desenvolvidas para o **Hackathon 2022 do Banco Safra** (Time 3)

## Tecnologias

### [Spring Boot](https://spring.io/projects/spring-boot)
Framework Java open source que tem como objetivo facilitar o processo de criação de aplicações Java WEB.

### [Spring cloud gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html/)
Gateway contruido em cima do ecosistema spring, que pode ser facilmente integrado com outros sistemas de load balancer/circuibraker.

### [Spring Cloud Netflix Eureka](https://spring.io/projects/spring-cloud-netflix)
Service Registry e Load balancer feito pela netflix, neste projeto é utilizado sua versão modificada para spring.

### [PostgreSQL](https://www.postgresql.org/)
Banco de dados relacional de alta performace e escalabilidade.

### [PGAdmin](https://www.pgadmin.org/)
Ferramenta oficial para administração do banco de dados PostgreSQL

### [Docker](https://www.docker.com/)
Ferramenta de virtualização de containers e redes, fornece segurança e simplicidade de build/deploy.

### [RabbitMQ](https://www.docker.com/)
[Não implementado mas previsto no roadmap] Serviço de mensageria/filas que permite a escalabilidade de aplicações.

### [AWS EC2](https://www.docker.com/)
Instância Linux em núvem que permite uma rápida configuração de servidores.

## Ambiente

PGAdmin:
http://ec2-54-165-208-25.compute-1.amazonaws.com:16543/

Aplicação:
http://ec2-54-165-208-25.compute-1.amazonaws.com:8080/

Swagger:
A implementar

Gateway:
A implementar

## Arquitetura

Esquemático de arquitetura:

![DIAGRAM](https://github.com/Safra-Open-Cashless/APIs/blob/main/Assets/Arquitetura.png?raw=true)

## Configuração

1. Pré-requisitos

Para execução local do projeto é necessário possuir configurado versões atuais das seguinte tecnologias:
* Docker
* Docker-Compose
* Java 11
* Maven vinculado ao Java 11

2. Build Gateway, ServiceRegistry e OpenCashlessApi

```shell
cd Gateway
mvn clean package install
cd ..
cd ServiceRegistry
mvn clean package install
cd ..
cd OpenCashlessApi
mvn clean package install
cd ..
```

3. Subir o orquestramento de containers.

```shell
docker-compose up
```

4. Adicionar banco de dados 

A aplicação Open Cashless API não ira subit corretamente pois o banco de dados não está configurado, antes de parar a execução execute os passo abaixo.

Conectar ao PosgreSQL pelo PGAdmin em http://localhost:16543/ conforme a imagem.

![DIAGRAM](https://github.com/Safra-Open-Cashless/APIs/blob/main/Assets/server-register.png?raw=true)

Adicionar banco de dados safra-open-cashless.

5. Subir Subir novamente a aplicação.

No mesmo terminal em que o docker estiver rodando pare a aplicação com o CTRL + C e execute novamente o projeto.

```shell
docker-compose up
```

## Implementações Futuras (RoadMap)

1. Serviço de filas para transações (Evitando gargalos)

4. Configuração automatica do banco de dados

2. Servidor de autenticação/validação JWT pronto para uso, é necessário o consumo do mesmo pelo front.

5. Integração com as APIs da fase 2/3 do open banking.

3. CI/CD com proteção de dados sensiveis(ex: private key)