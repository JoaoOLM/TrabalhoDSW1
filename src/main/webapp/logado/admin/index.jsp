<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:setBundle basename="messages" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="menu.titulo" /></title>
    </head>
    <body>
        <h1><fmt:message key="page.administrator" /></h1>
        <p><fmt:message key="admin.companies" /></p>
        <a href="${pageContext.request.contextPath}/empresas" class="link">
            <fmt:message key="link.companies" />
        </a>
        <br/>
        <p><fmt:message key="admin.professional" /></p>
        <a href="${pageContext.request.contextPath}/profissionais" class="link">
            <fmt:message key="link.professionals" />
        </a>
    </body>
</html>
