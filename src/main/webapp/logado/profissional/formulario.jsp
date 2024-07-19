<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
  <fmt:bundle basename="message">
    <head>
      <title>Profissional</title>
    </head>

    <body>
      <% String contextPath = request.getContextPath().replace("/", ""); %>
      <div align="center">
        <c:choose>
          <c:when test="${profissional != null}">
            <form action="atualizacao" method="post">
              <%@include file="campos.jsp"%>
            </form>
            <a href="/<%= contextPath%>/profissionais/remocao?id=${profissional.id}"
              onclick="return confirm('<fmt:message key="link.confirmar" />')">
              Remover profissional
            </a>
          </c:when>
          <c:otherwise>
            <form action="insercao" method="post">
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
