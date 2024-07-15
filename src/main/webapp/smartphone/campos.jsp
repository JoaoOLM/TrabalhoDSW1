<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${smartphone != null}">
                Edição
            </c:when>
            <c:otherwise>
                Cadastro
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${smartphone != null}">
        <input type="hidden" name="id" value="${smartphone.id}" />
    </c:if>
    <tr>
        <td><label for="marca">Marca</label></td>
        <td><input type="text" id="marca" name="marca" size="45"
                   required value="${smartphone.marca}" /></td>
    </tr>
    <tr>
        <td><label for="modelo">Modelo</label></td>
        <td><input type="text" id="modelo" name="modelo" size="45" required
                   value="${smartphone.modelo}" /></td>
    </tr>
    <tr>
        <td><label for="sistemaOperacional">Sistema Operacional</label></td>
        <td><input type="text" id="sistemaOperacional" name="sistemaOperacional" size="45" required
                   value="${smartphone.sistemaOperacional}" /></td>
    </tr>
    <tr>
        <td><label for="memoriaRamGB">Memória RAM (GB)</label></td>
        <td><input type="number" id="memoriaRamGB" name="memoriaRamGB" size="5" required
                   min="1" value="${smartphone.memoriaRamGB}" /></td>
    </tr>
    <tr>
        <td><label for="armazenamentoGB">Armazenamento (GB)</label></td>
        <td><input type="number" id="armazenamentoGB" name="armazenamentoGB" size="5" required
                   min="1" value="${smartphone.armazenamentoGB}" /></td>
    </tr>
    <tr>
        <td><label for="tamanhoTela">Tamanho da Tela (polegadas)</label></td>
        <td><input type="number" id="tamanhoTela" name="tamanhoTela" required
                   min="1" step="any" size="5" value="${smartphone.tamanhoTela}" /></td>
    </tr>
    <tr>
        <td><label for="preco">Preço</label></td>
        <td><input type="number" id="preco" name="preco" required
                   min="0.01" step="any" size="5" value="${smartphone.preco}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>
