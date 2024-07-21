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
Para iniciar o projeto, siga as instruções abaixo:

1. Abra um terminal no diretório raiz e entre no mysql.
   ```bash
   mysql -uroot -p
   ```
2. Após inserir sua senha gere o banco de dados.
   ```bash
   source db/MySQL/create.db
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


## Contato

Se você tiver alguma dúvida, sugestão ou precisar de suporte, por favor, sinta-se à vontade para entrar em contato conosco:

[<img src="https://github.com/JoaoOLM.png" width="60px;" style="border-radius:50%"/>](https://github.com/JoaoOLM/)
[<img src="https://github.com/JaksonHZ.png" width="60px;" style="border-radius:50%"/>](https://github.com/JaksonHZ/)
