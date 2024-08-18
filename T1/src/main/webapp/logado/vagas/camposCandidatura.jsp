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

            <input type="hidden" name="id" value="${candidatura.id}" />

            <tr>
                <td>
                    <label for="profissional"> Nome do profissional </label>
                </td>
                <td>
                    <input type="text" id="profissional" name="profissional" value="${candidatura.profissional.usuario.nome}" disabled />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="telefone"> Telefone do profissional </label>
                </td>
                <td>
                    <input type="text" id="telefone" name="telefone" value="${candidatura.profissional.telefone}" disabled />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="data"> Data da Candidatura </label>
                </td>
                <td>
                    <input type="text" id="data" name="data" value="${candidatura.dataCandidatura}" disabled />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="data"> Status da Candidatura </label>
                </td>
                <td>
                    <select id="status" name="status" required>
                        <option value="0" ${candidatura.status == 'NAO_SELECIONADO' ? 'selected' : ''}>NAO SELECIONADO</option>
                        <option value="1" ${candidatura.status == 'ENTREVISTA' ? 'selected' : ''}>ENTREVISTA</option>
                        <option value="2" ${candidatura.status == 'ABERTO' ? 'selected' : ''}>ABERTO</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="linkEntrevista"> Link da Entrevista </label>
                </td>
                <td>
                    <input type="text" id="linkEntrevista" name="linkEntrevista" value="${linkEntrevista}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="horarioEntrevista"> Horário da Entrevista </label>
                </td>
                <td>
                    <input type="text" id="horarioEntrevista" name="horarioEntrevista" value="${horarioEntrevista}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label> Currículo </label>
                </td>
                <td colspan="2" align="center">
                    <a href="/<%=contextPath%>/upload/${candidatura.arquivoCurriculo}">${candidatura.arquivoCurriculo}</a>
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
