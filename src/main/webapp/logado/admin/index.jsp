<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="menu.titulo" /></title>
    </head>
    <body>
        <h1><fmt:message key="pagina.administrador" /></h1>
        <p>Olá</p>
        <a href="${pageContext.request.contextPath}/empresas" class="link">
            <fmt:message key="link.empresas" />
        </a>
        <a href="${pageContext.request.contextPath}/profissionais" class="link">
            <fmt:message key="link.profissionais" />
        </a>
    </body>
</html>
