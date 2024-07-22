<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <fmt:message key="profissionais.titulo" />
        </title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="right">
            <p style="margin-right: 20px;">
                <fmt:message key="usuario.olamensagem" /> ${sessionScope.usuarioLogado.nome}!
            </p>
            <a href="${pageContext.request.contextPath}/logout.jsp" class="sair">
                <fmt:message key="usuario.logout" />
            </a>
        </div>

        <% String contextPath = request.getContextPath().replace("/", ""); %>
        <div class="table">
            <div style="margin-top: 2rem;">
                <div class="title">
                    <fmt:message key="profissionais.lista" />
                </div>
                <table border="1">
                    <tr>
                        <th>
                            <fmt:message key="profissionais.nome" />
                        </th>
                        <th>
                            <fmt:message key="profissionais.email" />
                        </th>
                        <th>
                            <fmt:message key="profissionais.cpf" />
                        </th>
                        <th>
                            <fmt:message key="profissionais.sexo" />
                        </th>
                        <th>
                            <fmt:message key="profissionais.dataNascimento" />
                        </th>
                        <th>
                            <fmt:message key="profissionais.acoes" />
                        </th>
                    </tr>

                    <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                        <tr>
                            <td>${profissional.usuario.nome}</td>
                            <td>${profissional.usuario.email}</td>
                            <td>${profissional.cpf}</td>
                            <td>${profissional.sexo}</td>
                            <td>${profissional.dataNascimento}</td>
                            <td>
                                <a href="/<%= contextPath%>/profissionais/edicao?id=${profissional.id}">
                                    <fmt:message key="profissionais.editar" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/profissionais/cadastro" class="button">
            <fmt:message key="profissionais.criar" />
        </a>
    </body>
</fmt:bundle>

</html>
