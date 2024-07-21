<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="card">
    <div class="tableCadastrar">
        <table>

            <caption class="title">
                <c:choose>
                    <c:when test="${vaga != null}">
                        <fmt:message key="vaga.atualizar" />
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="vaga.criar" />
                    </c:otherwise>
                </c:choose>
            </caption>

            <c:if test="${vaga != null}">
                <input type="hidden" name="id" value="${vaga.id}" />
            </c:if>
            <tr>
                <td>
                    <label for="cnpj"> <fmt:message key="empresa.cnpj" /> </label>
                </td>
                <td>
                    <input disabled type="text" id="cnpj" name="cnpj" value="${empresa.CNPJ}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="descricao"> <fmt:message key="vaga.descricao" /> </label>
                </td>
                <td>
                    <input type="text" id="descricao" name="descricao" value="${vaga.descricao}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="remuneracao"> <fmt:message key="vaga.remuneracao" /> </label>
                </td>
                <td>
                    <input type="text" id="remuneracao" name="remuneracao" value="${vaga.remuneracao}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="dataLimite"> <fmt:message key="vaga.dataLimite" /> </label>
                </td>
                <td>
                    <input type="date" id="dataLimite" name="dataLimite" value="${vaga.dataLimiteInscricao}" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="<fmt:message key= "acao.salvar"/>" class="button" />
                </td>
            </tr>
        </table>
    </div>
</div>
