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
* **Client:** integração com APIs externas

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



























