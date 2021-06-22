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



## Execução

*STANDALONE MODE* (Somente para homologação/dev. Para produção, utilize o modo de instalação Docker)

Este projeto é modular, portanto você deve executar cada módulo separadamente. Cada módulo instanciará um microsserviço em modo standalone.

0. Para modo de execução standalone será necessário as seguintes dependências

- [OpenJDK 11]
- [PostgreSQL v. 12]


1. Após instalar as depedências, faça o clone do projeto.

2. Altere a configuração da sua instancia local PostgreSQL nas propriedades de cada projeto no arquivo application.properties '<diretorio-módulo>/src/main/resources'

2. Para executar cada serviço, navegue via terminal até o respectivo diretório, faça o build com o mavem.

> Note: Em ambientes Windows, utilize o PowerShell com privilégios de administrador


```sh
cd <diretorio-modulo>
mvnw clean package
```

3. Em seguida, execute o aplicativo

> Para estabilização mais rápida no servidor servicediscorevy, execute os módulos preferenciamente nesta ordem: shareconfig >> servicediscovery >> apigateway >> <demais módulos>

```sh
java -jar ./target/rangood-pdv-<nome-servico>-0.0.1-SNAPSHOT.jar
```


## License

MIT


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
