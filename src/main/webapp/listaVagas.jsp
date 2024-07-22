<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <fmt:message key="vaga.titulo" />
        </title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="right">
            <a href="${pageContext.request.contextPath}/login.jsp" class="sair">
                <fmt:message key="login" />
            </a>
        </div>
        <div class="table">
            <div class="title" style="margin-top: 20px;">
                <fmt:message key="facaLogin" />
            </div>
            <div style="margin-top: 2rem;">
                <div class="title">
                    <fmt:message key="vagasAbertasLista" />
                </div>
                <!-- Formulário para filtrar por cidade -->
                <form action="${pageContext.request.contextPath}/index.jsp" method="get">
                    <label for="cidade"><fmt:message key="filtrarPorCidade" /></label>
                    <select id="cidade" name="cidade">
                        <option value=""><fmt:message key="selecioneCidade" /></option>
                        <c:forEach var="cidade" items="${cidades}">
                            <option value="${cidade}" ${param.cidade == cidade ? 'selected' : ''}>${cidade}</option>
                        </c:forEach>
                    </select>
                    <button type="submit"><fmt:message key="filtrar" /></button>
                </form>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <th><fmt:message key="vaga.empresa" /></th>
                        <th><fmt:message key="vaga.descricao" /></th>
                        <th><fmt:message key="vaga.remuneracao" /></th>
                        <th><fmt:message key="vaga.dataLimiteInscricao" /></th>
                    </tr>
                    <c:forEach var="vaga" items="${requestScope.listaVagasAbertas}">
                        <tr>
                            <td>${vaga.empresa.usuario.nome}</td>
                            <td>${vaga.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vaga.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vaga.dataLimiteInscricao}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</fmt:bundle>
</html>
