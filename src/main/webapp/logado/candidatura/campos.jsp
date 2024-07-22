<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="card">
    <div class="tableCadastrar">
        <table>
            <caption class="title">
                <fmt:message key="candidatura.criar" />
            </caption>

            <input type="hidden" name="idVaga" id="idVaga" value="${vaga.id}" />

            <tr>
                <td>
                    <label for="empresa"> <fmt:message key="vaga.empresa" /> </label>
                </td>
                <td>
                    <input disabled type="text" id="empresa" name="empresa" value="${vaga.empresa.usuario.nome}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="descricao"> <fmt:message key="vaga.descricao" /> </label>
                </td>
                <td>
                    <input disabled type="text" id="descricao" name="descricao" value="${vaga.descricao}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="remuneracao"> <fmt:message key="vaga.remuneracao" /> </label>
                </td>
                <td>
                    <input disabled type="text" id="remuneracao" name="remuneracao" value="${vaga.remuneracao}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="curriculo"> <fmt:message key="candidatura.arquivoCurriculo" /> </label>
                </td>
                <td>
                    <input type="file" id="curriculo" name="curriculo" required />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="<fmt:message key='acao.salvar'/>" class="button" />
                </td>
            </tr>
        </table>
    </div>
</div>
