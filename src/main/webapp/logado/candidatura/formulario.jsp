<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="candidatura.cadastro" /></title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <% String contextPath = request.getContextPath().replace("/", ""); %>

        <a href="${pageContext.request.contextPath}/logout.jsp" class="sair">
            <fmt:message key="navbar.sair" />
        </a>

        <div align="center">
            <c:choose>
                <c:when test="${candidatura != null}">
                    <form action="atualizar" method="post">
                        <%@include file="campos.jsp" %>
                    </form>
                    <a href="/<%= contextPath %>/vagas/remover?id=${candidatura.id}"
                        onclick="return confirm('<fmt:message key="link.confirmar" />')">
                        <fmt:message key="candidatura.remover" />
                    </a>
                </c:when>
                <c:otherwise>
                    <form action="inserir" method="post" enctype="multipart/form-data">
                        <%@include file="campos.jsp" %>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                </c:forEach>
            </ul>
        </c:if>
    </body>
</fmt:bundle>
</html>
