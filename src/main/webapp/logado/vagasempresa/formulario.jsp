<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
  <fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar</title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
        <title><fmt:message key="vaga.cadastro" /></title>
    </head>

    <body>
    <% String contextPath = request.getContextPath().replace("/", ""); %>
        <div align="center">
            <c:choose>
                <c:when test="${vaga != null}">
                    <form action="atualizar" method="post">
                        <%@include file="campos.jsp"%>
                    </form>
                    <a href="/<%= contextPath%>/vagasempresa/remover?id=${vaga.id}"
                        onclick="return confirm('<fmt:message key="link.confirmar" />')">
                        <fmt:message key="vaga.remover" />
                    </a>
                </c:when>
                <c:otherwise>
                    <form action="inserir" method="post">
                        <%@include file="campos.jsp"%>
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
