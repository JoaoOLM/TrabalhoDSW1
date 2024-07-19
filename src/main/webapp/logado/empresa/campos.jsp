<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="card">
    <h4>
        <a href="${pageContext.request.contextPath}/empresas" class="sair">
           Voltar
        </a>
    </h4>
    <div class="tableCadastrar">
        <table>

            <caption class="title">
                <c:choose>
                    <c:when test="${empresa != null}">
                        Atualizar Empresa
                    </c:when>
                    <c:otherwise>
                        Criar Empresa
                    </c:otherwise>
                </c:choose>
            </caption>
            <c:if test="${empresa != null}">
                <input type="hidden" name="id" value="${empresa.id}" />
            </c:if>
            <tr>
                <td>
                    <label for="nome">Nome da Empresa</label>
                </td>
                <td>
                    <input type="text" id="nome" name="nome" required value="${empresa.usuario.nome}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="cnpj"> CNPJ </label>
                </td>
                <td>
                    <input type="text" id="cnpj" name="cnpj" required value="${empresa.CNPJ}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="descricao">Descricao</label>
                </td>
                <td>
                    <input type="text" id="descricao" name="descricao" required value="${empresa.descricao}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="cidade"> Cidade </label>
                </td>
                <td>
                    <input type="text" id="cidade" name="cidade" required value="${empresa.cidade}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email"> E-mail </label>
                </td>
                <td>
                    <input type="email" id="email" name="email" required value="${empresa.usuario.email}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="senha"> Senha </label>
                </td>
                <td>
                    <input type="password" id="senha" name="senha" required value="${empresa.usuario.senha}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="admin"> Admin </label>
                </td>
                <td>
                    <input type="checkbox" id="admin" name="admin" value="${empresa.usuario.admin}" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Salvar" class="button" />
                </td>
            </tr>
        </table>
    </div>
</div>