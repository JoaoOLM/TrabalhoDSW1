<%@ page language="java" content="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="formulario.atualizar" /></title>
        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="card">
            <h4>
                <a href="${pageContext.request.contextPath}/empresas" class="sair">
                    <fmt:message key="pagina.voltar" />
                </a>
            </h4>
            <div class="tableCadastrar">
                <table>
                    <caption class="title">
                        <c:choose>
                            <c:when test="${empresa != null}">
                                <fmt:message key="formulario.atualizar" />
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="formulario.criar" />
                            </c:otherwise>
                        </c:choose>
                    </caption>

                    <c:if test="${empresa != null}">
                        <input type="hidden" name="id" value="${empresa.id}" />
                    </c:if>
                    <tr>
                        <td>
                            <label for="nome"><fmt:message key="campo.nome" /></label>
                        </td>
                        <td>
                            <input type="text" id="nome" name="nome" required value="${empresa.usuario.nome}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cnpj"><fmt:message key="campo.cnpj" /></label>
                        </td>
                        <td>
                            <input type="text" id="cnpj" name="cnpj" required value="${empresa.CNPJ}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="descricao"><fmt:message key="campo.descricao" /></label>
                        </td>
                        <td>
                            <input type="text" id="descricao" name="descricao" required value="${empresa.descricao}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cidade"><fmt:message key="campo.cidade" /></label>
                        </td>
                        <td>
                            <input type="text" id="cidade" name="cidade" required value="${empresa.cidade}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email"><fmt:message key="campo.email" /></label>
                        </td>
                        <td>
                            <input type="email" id="email" name="email" required value="${empresa.usuario.email}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="senha"><fmt:message key="campo.senha" /></label>
                        </td>
                        <td>
                            <input type="password" id="senha" name="senha" required value="${empresa.usuario.senha}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="admin"><fmt:message key="campo.admin" /></label>
                        </td>
                        <td>
                            <input type="checkbox" id="admin" name="admin" value="${empresa.usuario.admin}" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="<fmt:message key='botao.salvar' />" class="button" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</fmt:bundle>
</html>
