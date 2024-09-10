# Sistema para oferta de vagas de estágios/empregos • ![Development](https://img.shields.io/badge/Ativo-blue)

## Tecnologias  
<div style="display: flex; justify-content: space-between;">
    <div style="flex: 1;">
        <h5>Lado Servidor</h5>
        <div style="display: flex; flex-wrap: wrap; gap: 10px;">
            <img src="https://img.shields.io/badge/Servlet-CB3837.svg?style=for-the-badge" alt="Servlet" />
            <img src="https://img.shields.io/badge/JSP-61DAFB.svg?style=for-the-badge" alt="JSP" />
            <img src="https://img.shields.io/badge/JSTL-092E20.svg?style=for-the-badge" alt="JSTL" />
            <img src="https://img.shields.io/badge/JDBC-38B2AC.svg?style=for-the-badge" alt="JDBC" />
        </div>
    </div>
    <div style="flex: 1;">
        <h4>Lado Cliente</h4>
        <div style="display: flex; flex-wrap: wrap; gap: 10px;">
            <img src="https://shields.io/badge/JavaScript-F7DF1E?logo=JavaScript&logoColor=000&style=flat-square" alt="Javascript" />
            <img src="https://shields.io/badge/CSS-0000FF?logo=CSS3&logoColor=000&style=flat-square" alt="CSS" />
        </div>
    </div>
</div>


## Sobre o Projeto

Projeto desenvolvido para a matéria de Desenvolvimento de Software para Web 1 da Universidade Federal de São Carlos (UFSCar), ministrada pelo docente Prof. Delano M. Beder (UFSCar). O sistema se baseia em uma plataforma para oferta de vagas de emprego/estágio. É possível se cadastrar como profissional, capaz de se candidatar em uma vaga, ou empresa, capaz de registrar uma nova vaga para candidatura, além de existirem usuários admnistradores, capazes de gerenciar os usuários. 

## Recursos Principais

- **Cadastro de vagas pela empresa** 
- **Candidatura em uma vaga pelos profissionais** 
- **Envio de emails para candidatos a uma vaga**
- **Download de currículo para uma vaga**
- **Disponibilidade do sistema em Inglês e Português-br**
- **API REST para requisições**

## Dependências
Requisitos para rodar o sistema
1. [JDK](https://openjdk.java.net/)
2. [Maven](https://maven.apache.org/)
3. [MySQL](https://www.mysql.com/)
5. [Spring-Boot](https://spring.io/projects/spring-boot)

## Como Rodar
Para iniciar o projeto, siga as instruções abaixo:

1. Abra um terminal no diretório raiz e execute o seguinte comando.
   ```bash
   mvn spring-boot:run
   ```

O frontend agora estará acessível em [http://localhost:8080/](http://localhost:8080/).

## Endpoints da API

### REST API -- CRUD de profissionais  
Cria um novo profissional [Create - CRUD]  
POST http://localhost:8080/api/profissionais  
Body: raw/JSON (application/json)  

Retorna a lista de profissionais [Read - CRUD]  
GET http://localhost:8080/api/profissionais  

Retorna o profissional de id = {id} [Read - CRUD]  
GET http://localhost:8080/api/profissionais/{id}  

Atualiza o profissional de id = {id} [Update - CRUD]  
PUT http://localhost:8080/api/profissionais/{id}  
Body: raw/JSON (application/json)  

Remove o profissional de id = {id} [Delete - CRUD]  
DELETE http://localhost:8080/api/profissionais/{id}  

### REST API -- CRUD de empresas  
Cria uma nova empresa [Create - CRUD]  
POST http://localhost:8080/api/empresas  
Body: raw/JSON (application/json)  

Retorna a lista de empresas [Read - CRUD]  
GET http://localhost:8080/api/empresas  

Retorna a empresa de id = {id} [Read - CRUD]  
GET http://localhost:8080/api/empresas/{id}  

Retorna a lista de todas as empresas da cidade de nome = {nome}  
GET http://localhost:8080/api/empresas/cidades/{nome}  

Atualiza a empresa de id = {id} [Update - CRUD]  
PUT http://localhost:8080/api/empresas/{id}  
Body: raw/JSON (application/json)  
 
Remove a empresa de id = {id} [Delete - CRUD]  
DELETE http://localhost:8080/api/empresas/{id}  

### REST API -- Endpoints de Vagas  
Retorna a lista de vagas [Read - CRUD]  
GET http://localhost:8080/api/vagas  

Retorna a vaga de id = {id} [Read - CRUD]  
GET http://localhost:8080/api/vagas/{id}  

Retorna a lista de vagas (em aberto) da empresa de id = {id} [Read - CRUD]  
GET http://localhost:8080/api/vagas/empresas/{id}  

## Requisitos 

Os requisitos para o sistema podem ser verificiados em [Requisitos-A2](Requisitos-A2.pdf) e [Requisitos-A3](Requisitos-A3.pdf)

CRUD para T3: 

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R1:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (100%)

R2:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (100%)

R3:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R4:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado   
Divisão na implementação da funcionalidade: JoaoOLM (100%)

R5:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R6:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R7:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R8:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JoaoOLM (100%)

R9:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

R10:

(X) Implementado ( ) Parcialmente implementado ( ) Não implementado  
Divisão na implementação da funcionalidade: JoaoOLM (100%)

## Contato

Se você tiver alguma dúvida, sugestão ou precisar de suporte, por favor, sinta-se à vontade para entrar em contato conosco:

[<img src="https://github.com/JoaoOLM.png" width="60px;" style="border-radius:50%"/>](https://github.com/JoaoOLM/)
[<img src="https://github.com/JaksonHZ.png" width="60px;" style="border-radius:50%"/>](https://github.com/JaksonHZ/)
