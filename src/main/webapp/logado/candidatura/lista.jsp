<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            Candidatura
        </title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <% String contextPath = request.getContextPath().replace("/", ""); %>
            <div class="table">
                <div style="margin-top: 2rem;">
                    <h2>
                        <fmt:message key="candidatura.lista" />
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
                                <fmt:message key="vaga.dataLimite" /> 
                            </th>
                            <th> Status </th>
                        </tr>
                        <c:forEach var="candidatura" items="${requestScope.listaCandidaturas}">
                            <tr>
                                <td>${candidatura.vaga.descricao}</td>
                                <td>
                                    <fmt:formatNumber value="${candidatura.vaga.remuneracao}" type="currency" currencySymbol="R$" />
                                </td>
                                <td>${candidatura.vaga.dataLimite}</td>
                                <td>${candidatura.status}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

    </body>

</fmt:bundle>

</html>