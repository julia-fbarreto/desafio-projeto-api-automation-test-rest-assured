# desafio-DIO-projeto-api-automation-test-rest-assured
Código e documentos do desafio de projeto final do bootcamp DIO GFT Quality Assurance Para Mulheres.

Mentora: Carolina Louzada

#### Objetivo do projeto
Neste projeto foi explorado o framework RestAssured + JUnit e a geração de reports com o Allure Framework. Além disso, foi utilizado o JsonServer como forma de simular uma API de forma a incrementar a suíte de testes.

#### API Base
[Restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-DeleteBooking)

#### Frameworks/tools
- RestAssured
- Json Server
- Intellij
- Allure Framework

Biblioteca/Documentação/Referências
<br>https://www.npmjs.com/package/json-server
<br>https://github.com/rest-assured/rest-assured/wiki/Usage
<br>https://www.npmjs.com/package/json-server-auth?activeTab=readme
<br>https://github.com/allure-framework
<br>https://github.com/rest-assured/rest-assured 

## Desafio obrigatório
1. <b>Conhecendo a API</b>
<br>A base para qualquer teste automatizado é o teste manual, portanto foi explorado a API primeiramente no [Postman](https://www.postman.com/).
  - Fazer a collection usando a API Restful-booker
  - Exportar a collection <i>(foi exportado como .json e também evidenciado as suites em pdf)</i>
  - Subir a collection no [repositório](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-api-manual-postman)


2. <b>Automatizando com Rest Assured</b>
- Fazer uma suíte de testes cobrindo todos os endpoints da documentação
- Gerar o report usando o Allure Framework <i>(foi gerado um report em [Allure-results](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-api-automation-test-rest-assured/allure-results), exportado como suites.csv e também evidenciado as suites em pdf em [Allure-evidences](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-api-automation-test-rest-assured/allure-evidences))</i>
- Subir o projeto para o [repositório](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-api-automation-test-rest-assured)

## Desafio opcional
Criar seu próprio server que servirá de base para criação antecipada dos testes de API com a ferramenta [JSON SERVER](https://www.npmjs.com/package/json-server-auth?activeTab=readme).
- Incrementar a API com outros endpoints
- Adicionar o JSON SERVER no [repositório](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-opcional-json-server/rest-assured-json-server) do projeto de automação
- Subir o JSON Server no [repositório](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/tree/master/desafio-opcional-json-server/rest-assured-json-server)
- Criar um [READ ME](https://github.com/julia-fbarreto/desafio-projeto-api-automation-test-rest-assured/blob/master/desafio-opcional-json-server/rest-assured-json-server/readme.md) para que as pessoas saibam como usar os endpoints criados e entender o projeto

<br/>Obrigada por ter lido até aqui, espero que goste! 💜
