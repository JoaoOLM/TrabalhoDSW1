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
    <h1><fmt:message key="page.label" /></h1>   
    <c:if test="${mensagens.existeErros}">
        <div id="erro">
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
                <th>Login: </th>
                <td><input type="text" name="login" value="${param.login}"/></td>
            </tr>
            <tr>
                <th>Senha: </th>
                <td><input type="password" name="senha" /></td>
            </tr>
            <tr>
                <td colspan="2"> 
                    <input type="submit" name="bOK" value="Entrar"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
