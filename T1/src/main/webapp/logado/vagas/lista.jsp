<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">

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

                    <c:forEach var="vagaAberta" items="${requestScope.listaVagasAbertas}">
                        <tr>
                            <td>${vagaAberta.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vagaAberta.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vagaAberta.dataLimiteInscricao}</td>
                            <td>
                                <a href="/<%= contextPath %>/vagas/editar?id=${vagaAberta.id}">
                                    <fmt:message key="acao.editar" />
                                </a>
                            </td>
                            <td>
                                <a href="/<%= contextPath %>/vagas/candidaturas?id=${vagaAberta.id}">
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

                    <c:forEach var="vagaExpirada" items="${requestScope.listaVagasExpiradas}">
                        <tr>
                            <td>${vagaExpirada.descricao}</td>
                            <td>
                                <fmt:formatNumber value="${vagaExpirada.remuneracao}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>${vagaExpirada.dataLimiteInscricao}</td>
                            <td>
                                <a href="/<%= contextPath %>/vagas/editar?id=${vagaExpirada.id}">
                                    <fmt:message key="acao.editar" />
                                </a>
                            </td>
                            <td>
                                <a href="/<%= contextPath %>/vagas/candidaturas?id=${vagaExpirada.id}">
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
