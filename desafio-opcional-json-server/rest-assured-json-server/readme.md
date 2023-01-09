<h2>Mock de Api criado com json-server-auth 🖥️</h2><br>
versão utilizada: 2.1.0

#### Primeiros Passos:

- Instalar [NodeJs](https://nodejs.org/en/)

- Dentro do diretório do seu projeto, abrir o terminal para começar a criar o projeto digite:<br>
  `npm initi`
  
  <i>permite iniciar um pacote, criando o arquivo package.json</i>

- Em seguida instalar o json-server


#### Instalação Json Server:

No terminal digite: <br>
`npm install -D json-server json-server-auth`

<i>utilizando o json-server-auth pois será necessário realizar autenticação e fluxo de autenticação fake (que já é json-server + json-server-auth juntos)</i>


#### Criando o arquivo de banco de dados:<br>
Criar db.json como collection inicial (irá funcionar como um banco de dados)
```
{
    "users": [],
    "bookings": [
        {
          "bookingid": 1,
          "firstname": "Sally",
          "lastname": "Brown",
          "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
            "checkin": "2013-02-23",
            "chekcout": "2014-10-23"
          },
          "additionalneeds": "Breakfast"
        }
    ]
}
```

Utilizei como referência os endpoints do [restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-DeleteBooking)
<br>E como request o [Postman](https://www.postman.com/)
<br>Utilizei a IDE [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) 


#### Configurando o script do arquivo package.json:<br>
Na sua IDE de preferência e na pasta package.json digite no script
`json-server-auth db.json`
```
{
    "scripts": {
        "start-server": "json-server-auth db.json -r routes.json"
    }
}
```

#### Iniciando o servidor: <br>
Digite no terminal:<br> 
`npm run start-server`
<p>Por padrão a API vai funcionar no endereço: http://localhost:3000<br>


#### Rotas:<br>
Você define as rotas da API mas utilizei as rotas abaixo:

<table>
    <tr>
        <td><b>Request</b></td>
        <td><b>URL</b></td>
        <td><b>Detalhes</b></td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:3000/register</td>
        <td>Registro de um novo usuário</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>http://localhost:3000/login</td>
        <td>Login de um usuário existente</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:3000/bookings</td>
        <td>Restringir acesso</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>http://localhost:3000/users/2</td>
        <td>Procurar um usuário por ID</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>http://localhost:3000/users/1</td>
        <td>Atualizar dados de usuário</td>
    </tr>
    <tr>
        <td>PATCH</td>
        <td>http://localhost:3000/users/1</td>
        <td>Atualizar dados parciais de usuário</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>http://localhost:3000/users/3</td>
        <td>Deletar usuário</td>
    </tr>
</table>

#### Exemplos:

<b>Exemplo 1:</b> Registro de um novo usuário
<br>Dados de envio:
```
POST http://localhost:3000/register

{
    "email": "olivier@mail.com",
    "password": "bestPassw0rd"
} 
```

<br>Retorno
```
{
    "user": {
    "email": "olivier@mail.com",
    "id": 1
    }
}
```

<br><b>Exemplo 2:</b> Login de um usuário existente
<br>Dados de envio:
```
POST http://localhost:3000/login
{
    "email": "olivier@mail.com",
    "password": "bestPassw0rd"
}
```

<br>Retorno
```
{
    "user": {
    "email": "olivier@mail.com",
    "id": 1
    }
}
```

<br><b>Exemplo 3:</b> Restringir acesso
<br>Dados de envio:
```
GET http://localhost:3000/bookings
{
    "bookings": 600
}
```

<br>Retorno
```
401 Unauthorized
```

<br><b>Exemplo 4:</b> Procurar um usuário por ID
<br>Dados de envio:
```
GET http://localhost:3000/users/2
```

<br>Retorno
```
{
    "email": "testejulia1@mail.com",
    "password": "$2a$10$dqC0j9FmH5pqya7E1HbNjeIneNlrPDPiQxYhuJyTxu7rNF7owRFUm",
    "id": 2 
}
```

<br><b>Exemplo 5:</b> Atualizar dados de usuário
<br>Dados de envio:
```
PUT http://localhost:3000/users/1
{
    "email": "olivier@mail.com",
    "password": "bestPassw0rd",
    "firstname": "Olivier",
    "lastname": "Monge",
    "age": 32
}
```

<br>Retorno
```
{
    "email": "olivier@mail.com",
    "password": "$2a$10$iZM7npQxjGKjkxcTEuCA5./zPjJlpne23LMhYX88JQesXcZv1f3vK",
    "firstname": "Olivier",
    "lastname": "Monge",
    "age": 32,
    "id": 1
}
```

<br><b>Exemplo 6:</b> Atualizar dados parciais de usuário
<br>Dados de envio:
```
PATCH http://localhost:3000/users/1
{
    "email": "olivier@mail.com",
    "password": "bestPassw0rd",
    "firstname": "Olivier",
    "lastname": "Monge",
    "age": 42
}
```
<br>Retorno
```
{
"email": "olivier@mail.com",
"password": "$2a$10$iZM7npQxjGKjkxcTEuCA5./zPjJlpne23LMhYX88JQesXcZv1f3vK",
"firstname": "Olivier",
"lastname": "Monge",
"age": 32,
"id": 1
}
```

<br><b>Exemplo 7:</b> Deletar usuário
<br>Dados de envio:
```
DELETE http://localhost:3000/users/3
```

<br>Retorno
```
200 OK
```

#### Referências:
https://www.npmjs.com/package/json-server-auth?activeTab=readme
<br>https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-DeleteBooking


<br>Obrigada por ter lido até aqui, espero que goste! 💜


