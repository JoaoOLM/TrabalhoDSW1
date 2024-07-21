<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Administrador</h1>
        <p>Olá</p>
        <a href="${pageContext.request.contextPath}/usuarios" class="link">
            Usuarios
        </a>
        <a href="${pageContext.request.contextPath}/empresas" class="link">
            Empresas
        </a>
        <a href="${pageContext.request.contextPath}/profissionais" class="link">
            Profissionais
        </a>
    </body>
</html>