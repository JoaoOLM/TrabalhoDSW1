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
        <a href="${pageContext.request.contextPath}/logout.jsp" class="sair">
            <fmt:message key="navbar.sair" />
        </a>
        <br/>
        <a href="${pageContext.request.contextPath}/candidaturas/lista" class="candidaturas">
            <fmt:message key="candidaturas.listar" />
            </a>
        </div>
    <% String contextPath=request.getContextPath().replace("/", "" ); %>
        <div class="table">
            <div style="margin-top: 2rem;">
                <div class="title">
                    <fmt:message key="vagasAbertasLista" />
                </div>
                <!-- Formulário para filtrar por cidade -->
                <form action="${pageContext.request.contextPath}/candidaturas" method="get">
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
                        <th><fmt:message key="acao" /></th>
                    </tr>
                    <c:forEach var="vaga" items="${requestScope.listaVagasAbertas}">
                        <tr>
                            <td>${vaga.empresa.usuario.nome}</td>
                            <td>${vaga.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vaga.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vaga.dataLimiteInscricao}</td>
                        <td>
                            <a href="/<%= contextPath%>/candidaturas/inscrever?id=${vaga.id}">
                                <fmt:message key="listavaga.inscrever" />
                            </a>
                        </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</fmt:bundle>
</html>
