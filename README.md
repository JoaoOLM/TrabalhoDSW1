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

## Dependências
Requisitos para rodar o sistema
1. [JDK](https://openjdk.java.net/)
2. [Maven](https://maven.apache.org/)
3. [MySQL](https://www.mysql.com/)
4. [Tomcat](https://tomcat.apache.org/)

## Como Rodar
Para iniciar o projeto será necessário alterar o arquivo GenericDAO.java para alterar suas credencias do banco de dados, além de incluir um arquivo config.properties dentro de src\main\resources\, o arquivo deve seguir o seguinte modelo:

    mail.smtp.host = smtp.gmail.com  
    mail.smtp.port = 465  
    mail.smtp.ssl.enable = true  
    mail.smtp.auth = true  
    username = [username]@gmail.com  
    name = <emailName>
    password = <password>  

Mais informações em: [https://support.google.com/accounts/answer/185833?hl=pt-BR](https://support.google.com/accounts/answer/185833?hl=pt-BR)

Para iniciar o projeto, siga as instruções abaixo:

1. Abra um terminal no diretório raiz e entre no mysql.
   ```bash
   mysql -uroot -p
   ```
2. Após inserir sua senha gere o banco de dados.
   ```bash
   source db/MySQL/create.sql
   ```
4. Inicie o tomcat.
    - Windows
   ```bash
   cd SUA_INSTALAÇÂO_TOMCAT/bin
   ./startup.bat
   ```
   -Linux/Mac
    ```bash
   cd SUA_INSTALAÇÂO_TOMCAT/bin
   export JAVA_HOME="/<caminho_para_java>/jreXXX"
   ./startup.sh
   ```
5. Inicie o servidor.
   ```bash
   mvn tomcat7:redeploy
   ```

O frontend agora estará acessível em [http://localhost:8080/Estagio/index.jsp](http://localhost:8080/Estagio/index.jsp).

Para possibilitar o teste das funcionalidades foram adicionados alguns usuários de teste:  
    admin@gmail.com : Usuário Admin  
    user1@gmail.com e user2@gmail.com : Usuários Profissional  
    user3@gmail.com e user4@gmail.com : Usuários Empresa  
A senha para todos os usuários é: teste

Também foram criadas 3 vagas, duas do user3 e uma do user4.

## Requisitos 

Os requisitos para o sistema podem ser verificiados em [Requisitos-A1](Requisitos-A1.pdf)


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
Divisão na implementação da funcionalidade: JaksonHZ (50%), JoaoOLM (50%)

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

## Contato

Se você tiver alguma dúvida, sugestão ou precisar de suporte, por favor, sinta-se à vontade para entrar em contato conosco:

[<img src="https://github.com/JoaoOLM.png" width="60px;" style="border-radius:50%"/>](https://github.com/JoaoOLM/)
[<img src="https://github.com/JaksonHZ.png" width="60px;" style="border-radius:50%"/>](https://github.com/JaksonHZ/)
