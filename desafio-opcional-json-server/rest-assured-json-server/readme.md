Mock de Api criado com json-server-auth<br>
versão utilizada: 2.1.0

<b>Primeiros Passos:</b>

- Instalar NodeJs (https://nodejs.org/en/)

- Dentro do diretório do seu projeto, abrir o terminal para começar a criar o projeto digite (permite iniciar um pacote, criando o arquivo package.json):<br>
    'npm initi'
- Em seguida instalar o json-server

<br>
1) Instalação Json Server:

No terminal digite: <br>
'npm install -D json-server json-server-auth'

utilizando o json-server-auth porque precisamos de autenticação e fluxo de autenticação fake e tem interdependência com json-server
json-server + json-server-auth juntos

<br>
2) Criando o arquivo de banco de dados<br>
Criar db.json como collection inicial (irá funcionar como um banco de dados)

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

<br>
3) Configurando o script do arquivo package.json<br>
Digite 'json-server-auth db.json'

{
    "scripts": {
        "start-server": "json-server-auth db.json -r routes.json"
    }
}

<br>
4) Iniciando o servidor <br>
Digite no terminal:<br> 'npm run start-server'.<p>
Por padrão a API vai funcionar no endereço: http://localhost:3000<br>


<br>
5) Rotas<br>
Você define as rotas da API mas iremos usar as rotas abaixo:

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

<br><b>Exemplos</b>

Exemplos 1
<br>Dados de envio:
<br>POST http://localhost:3000/register
<br>
{
"email": "olivier@mail.com",
"password": "bestPassw0rd"
} 

<br>Retorno<br>
{
    "user": {
    "email": "olivier@mail.com",
    "id": 1
    }
}

<br>Exemplo 2
<br>Dados de envio:
<br>POST http://localhost:3000/login
<br>{
"email": "olivier@mail.com",
"password": "bestPassw0rd"
}

Retorno
<br>{
"user": {
"email": "olivier@mail.com",
"id": 1
}
}

<br>Exemplo 3
<br>Dados de envio:
<br>GET http://localhost:3000/bookings
<br>{
"bookings": 600
}

Retorno
<br>401 Unauthorized

<br>Exemplo 4
<br>Dados de envio:
<br> GET http://localhost:3000/users/2

Retorno
<br>{
"email": "testejulia1@mail.com",
"password": "$2a$10$dqC0j9FmH5pqya7E1HbNjeIneNlrPDPiQxYhuJyTxu7rNF7owRFUm",
"id": 2
}

<br>Exemplo 5
<br>Dados de envio:
<br>PUT http://localhost:3000/users/1
<br>{
"email": "olivier@mail.com",
"password": "bestPassw0rd",
"firstname": "Olivier",
"lastname": "Monge",
"age": 32
}

Retorno
<br>{
"email": "olivier@mail.com",
"password": "$2a$10$iZM7npQxjGKjkxcTEuCA5./zPjJlpne23LMhYX88JQesXcZv1f3vK",
"firstname": "Olivier",
"lastname": "Monge",
"age": 32,
"id": 1
}

<br>Exemplo 6
<br>Dados de envio:
<br>PATCH http://localhost:3000/users/1
<br>{
"email": "olivier@mail.com",
"password": "bestPassw0rd",
"firstname": "Olivier",
"lastname": "Monge",
"age": 42
}

Retorno
<br>{
"email": "olivier@mail.com",
"password": "$2a$10$iZM7npQxjGKjkxcTEuCA5./zPjJlpne23LMhYX88JQesXcZv1f3vK",
"firstname": "Olivier",
"lastname": "Monge",
"age": 32,
"id": 1
}

<br>Exemplo 7
<br>Dados de envio:
<br>DELETE http://localhost:3000/users/3

Retorno
<br>200 OK

<b>Referências</b>
<br>https://www.npmjs.com/package/json-server-auth?activeTab=readme
<br>https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-DeleteBooking



