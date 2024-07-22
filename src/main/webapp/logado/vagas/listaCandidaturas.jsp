<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="candidatura.titulo" /></title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="table" style="margin-top: 2rem;">
            <h2><fmt:message key="candidatura.lista" /></h2>
            <table border="1">
                <tr>
                    <th><fmt:message key="candidatura.nomeProfissional" /></th>
                    <th><fmt:message key="candidatura.arquivoCurriculo" /></th>
                    <th><fmt:message key="candidatura.dataCandidatura" /></th>
                    <th><fmt:message key="candidatura.status" /></th>
                    <th><fmt:message key="acao" /></th>
                </tr>
                <c:forEach var="candidatura" items="${requestScope.candidaturas}">
                    <tr>
                        <td>${candidatura.profissional.usuario.nome}</td>
                        <td>${candidatura.arquivoCurriculo}</td>
                        <td><fmt:formatDate value="${candidatura.dataCandidatura}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td>${candidatura.status}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/vagas/editarCandidatura?id=${candidatura.id}">
                                <fmt:message key="acao.editar" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</fmt:bundle>
</html>
