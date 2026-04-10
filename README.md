# BioGreen API

API REST para gestão ambiental, desenvolvida com Java e Spring Boot

## Descrição

A BioGreen API é um software voltado a gestão ambiental, com objetivo de solucionar o problema de 
descarte inadequado de itens (móveis, eletrônicos e etc).

Nele é possível registrar solicitações de coletas de itens, registrar denúncias, e salvar locais de descartes.

## Tecnologias

* Java 11
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (JSON Web Token)
* Flyway (migrations)
* Lombok
* Bean Validation
* REST Template
* Maven
* PostgreSQL
* Postman (testar a API)

## Arquitetura

* **Controller:** responsável por expor os endpoints
* **Service:** regras de negócio
* **Repository:** acesso a dados
* **DTOs:** transporte de dados entre camadas 
* **Infra:** segurança, exceções e integrações externas
* **Client:** consumo de APIs externas (ViaCEP e Nominatim)

## Estrutura de Pastas

```
src/main/java/com.api.biogreen
├── controller
├── domain
│   ├── coleta
│   ├── denuncia
│   ├── local_descarte
│   ├── manual
│   ├── solicitacao
│   └── usuario
├── infra
│   ├── client
│   ├── config
│   ├── exception
│   ├── files
│   └── security
├── utils
└── BiogreenApplication
```

## Segurança e Autenticação

* Utilização de Spring Security
* Autenticação Stateless baseada em JWT
* Filtro de segurança (SecurityFilter)
* Controle de permissões e acesso de endpoints com roles

## Integrações Externas

* ViaCEP
  * Utilizado para obter endereço completo por CEP e nº
* Nominatim
  * Utilizado para obter coordenadas (latitude e longitude) por endereço completo

## Migrations e Versionamento do Banco

* Utilização do Flyway
* Scripts em **resourses/db/migration**
* Versionamento incremental

## Testes da API

* A API foi testada utilizando Postman
* Testes manuais dos endpoints
* Validação de autenticação, regras e respostas HTTP
* Veja aqui [JSON da Collection Postman](https://github.com/CaioSouza07/biogreen-api/blob/main/BioGreen%20API%20Collections.postman_collection.json)

## Como Executar o Projeto

1. Clonar o repositório
```
git clone https://github.com/CaioSouza07/biogreen-api.git
```
2. Configurar o **application.properties**
```properties
spring.application.name=biogreen

spring.datasource.url=[COLOQUE AQUI URL DO BANCO DE DADOS]
spring.datasource.username=[USUARIO DO BANCO]
spring.datasource.password=[SENHA DO BANCO]

# Configure abaixo as variáveis de ambiente ou deixe o default
api.security.token.secret=${JWT_SECRET:my-secret-key}
api.upload-dir.solicitacoes=${DIR_SOLICITACOES:uploads/solicitacoes}
api.upload-dir.manuais=${DIR_MANUAIS:uploads/manuais}


spring.servlet.multipart.max-file-size=10MB
```
3. Executar a aplicação

   1. Abra o projeto na sua IDE
   2. Localize a classe principal: ```BiogreenApplication.java```
   3. Execute o método **main** da classe

> **Dica:** Caso  for executar o projeto com Maven, execute com o seguinte código:
```mvn spring-boot:run```



























