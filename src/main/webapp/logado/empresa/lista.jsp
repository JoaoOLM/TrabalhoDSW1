<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="pagina.empresas" /></title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
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

        <% String contextPath = request.getContextPath().replace("/", ""); %>
        <div class="table">
            <div style="margin-top: 2rem;">
                <div class="title">
                    <fmt:message key="titulo.listaEmpresas" />
                </div>
                <table border="1">
                    <tr>
                        <th><fmt:message key="coluna.nomeEmpresa" /></th>
                        <th><fmt:message key="coluna.email" /></th>
                        <th><fmt:message key="coluna.cnpj" /></th>
                        <th><fmt:message key="coluna.descricao" /></th>
                        <th><fmt:message key="coluna.cidade" /></th>
                        <th><fmt:message key="coluna.acoes" /></th>
                    </tr>

                    <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                        <tr>
                            <td>${empresa.usuario.nome}</td>
                            <td>${empresa.usuario.email}</td>
                            <td>${empresa.CNPJ}</td>
                            <td>${empresa.descricao}</td>
                            <td>${empresa.cidade}</td>
                            <td>
                                <a href="/<%= contextPath %>/empresas/edicao?id=${empresa.id}">
                                    <fmt:message key="botao.editar" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/empresas/cadastro" class="button">
            <fmt:message key="botao.criar" />
        </a>
    </body>
</fmt:bundle>
</html>
