<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<fmt:bundle basename="message">
    <head>
        <title><fmt:message key="pagina.empresa" /></title>
        <link rel="icon" type="image/png" href="https://cdn.iconscout.com/icon/free/png-256/dashboard-1739866-1481441.png" />
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <% String contextPath = request.getContextPath().replace("/", ""); %>
        <div class="right">
            <p style="margin-right: 20px;">
                <fmt:message key="mensagem.olamensagem">
                    <fmt:param value="${sessionScope.usuarioLogado.nome}" />
                </fmt:message>
            </p>
            <a href="${pageContext.request.contextPath}/logout.jsp" class="sair">
                <fmt:message key="botao.logout" />
            </a>
        </div>
        
        <div align="center">
            <c:choose>
                <c:when test="${empresa != null}">
                    <form action="atualizacao" method="post">
                        <%@ include file="campos.jsp" %>
                    </form>
                    <a href="/<%= contextPath %>/empresas/remocao?id=${empresa.id}"
                       onclick="return confirm('<fmt:message key="acao.confirmar" />')">
                        <fmt:message key="acao.remover" />
                    </a>
                </c:when>
                <c:otherwise>
                    <form action="insercao" method="post">
                        <%@ include file="campos.jsp" %>
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
