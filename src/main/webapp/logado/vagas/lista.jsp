<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <fmt:message key="vaga.titulopagina" />
        </title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <% String contextPath = request.getContextPath().replace("/", ""); %>
        <div class="table">
            <div style="margin-top: 2rem;">
                <h2>
                    <fmt:message key="vaga.listaAbertas" />
                </h2>
                <table border="1">
                    <tr>
                        <th>
                            <fmt:message key="vaga.descricao" /> 
                        </th>
                        <th>
                            <fmt:message key="vaga.remuneracao" /> 
                        </th>
                        <th>
                            <fmt:message key="vaga.dataLimiteInscricao" /> 
                        </th>
                        <th>
                            <fmt:message key="acao" />
                        </th>
                        <th>
                            <fmt:message key="vaga.candidaturas" />
                        </th>
                    </tr>

                    <c:forEach var="vaga" items="${requestScope.listaVagasAbertas}">
                        <tr>
                            <td>${vaga.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vaga.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vaga.dataLimiteInscricao}</td>
                            <td>
                                <a href="/<%= contextPath%>/vagas/editar?id=${vaga.id}">
                                    <fmt:message key="acao.editar" />
                                </a>
                            </td>
                            <td>
                                <a href="/<%= contextPath%>/vagas/candidaturas?id=${vaga.id}">
                                    <fmt:message key="acao.analizarCandidaturas" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div style="margin-top: 2rem;">
                <h2>
                    <fmt:message key="vaga.listaExpirada" />
                </h2>
                <table border="1">
                    <tr>
                        <th>
                            <fmt:message key="vaga.descricao" /> 
                        </th>
                        <th>
                            <fmt:message key="vaga.remuneracao" /> 
                        </th>
                        <th>
                            <fmt:message key="vaga.dataLimiteInscricao" /> 
                        </th>
                        <th>
                            <fmt:message key="acao" />
                        </th>
                        <th>
                            <fmt:message key="vaga.candidaturas" />
                        </th>
                    </tr>

                    <c:forEach var="vaga" items="${requestScope.listaVagasExpiradas}">
                        <tr>
                            <td>${vaga.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vaga.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vaga.dataLimiteInscricao}</td>
                            <td>
                                <a href="/<%= contextPath%>/vagas/editar?id=${vaga.id}">
                                    <fmt:message key="acao.editar" />
                                </a>
                            </td>
                            <td>
                                <a href="/<%= contextPath%>/vagas/candidaturas?id=${vaga.id}">
                                    <fmt:message key="acao.analizarCandidaturas" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <a href="${pageContext.request.contextPath}/vagas/cadastrar" class="button">
                <fmt:message key="vaga.criar" />
            </a>
        </div>
    </body>
</fmt:bundle>

</html>
