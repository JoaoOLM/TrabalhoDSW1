<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<fmt:setBundle basename="messages" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="page.title" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="login-container">
        <h1><fmt:message key="page.label" /></h1>
        <c:if test="${mensagens.existeErros}">
            <div class="error-container">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li>${erro}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th><fmt:message key="login.label" />: </th>
                    <td><input type="text" name="login" value="${param.login}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="senha.label" />: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="<fmt:message key='entrar.label'/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
