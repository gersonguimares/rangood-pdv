# API Rangood PDV


[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://img.shields.io/badge/build-passing-brightgreen)

Uma API Rest que fornece serviços para cadastro e gestão de produtos e pedidos de uma lanchonete.

## Características

- Desenvolvida sob arquitetura de microsserviços
- Implementada sob o ecossistem Spring


## Arquitetura

![](https://github.com/gersonguimares/rangood-pdv/raw/master/diagrama_arquitetura.png)

## Ecossistema

Foram utilizadas as seguintes ferramentas do ecossistema Spring Boot e Spring Cloud

- [Spring Cloud Server Config] - Servidor para configuração centralizada
- [Eureka Service Discorevy] - Servidor para descoberta de serviços.
- [Netflix Zull Proxy] - API Gateway para roteamento e autorização de recursos.
- [Spring Cloud OpenFeign] - Provedor de comunicação entre-serviços.
- [spring Cloud actuator] - Monitoramento e sincronização

