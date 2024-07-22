<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">
    <body>
        <div class="card">
            <h4>
                <a href="${pageContext.request.contextPath}/profissionais" class="sair">
                    <fmt:message key="botao.voltar" />
                </a>
            </h4>
            <div class="tableCadastrar">
                <table>
                    <caption class="title">
                        <c:choose>
                            <c:when test="${profissional != null}">
                                <fmt:message key="profissional.atualizar" />
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="profissional.criar" />
                            </c:otherwise>
                        </c:choose>
                    </caption>

                    <c:if test="${profissional != null}">
                        <input type="hidden" name="id" value="${profissional.id}" />
                    </c:if>
                    <tr>
                        <td>
                            <label for="nome"> <fmt:message key="campo.nome" /> </label>
                        </td>
                        <td>
                            <input type="text" id="nome" name="nome" required value="${profissional.usuario.nome}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cpf"> <fmt:message key="campo.cpf" /> </label>
                        </td>
                        <td>
                            <input type="text" id="cpf" name="cpf" required value="${profissional.cpf}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="telefone"> <fmt:message key="campo.telefone" /> </label>
                        </td>
                        <td>
                            <input type="text" id="telefone" name="telefone" required value="${profissional.telefone}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="sexo"> <fmt:message key="campo.sexo" /> </label>
                        </td>
                        <td>
                           <select id="sexo" name="sexo" required>
                                <option value="0" ${profissional.sexo == 'MASCULINO' ? 'selected' : ''}><fmt:message key="opcao.sexo.masculino" /></option>
                                <option value="1" ${profissional.sexo == 'FEMININO' ? 'selected' : ''}><fmt:message key="opcao.sexo.feminino" /></option>
                                <option value="2" ${profissional.sexo == 'OUTRO' ? 'selected' : ''}><fmt:message key="opcao.sexo.outro" /></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="dataNascimento"> <fmt:message key="campo.dataNascimento" /> </label>
                        </td>
                        <td>
                            <input type="date" id="dataNascimento" name="dataNascimento" required value="${profissional.dataNascimento}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email"> <fmt:message key="campo.email" /> </label>
                        </td>
                        <td>
                            <input type="email" id="email" name="email" required value="${profissional.usuario.email}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="senha"> <fmt:message key="campo.senha" /> </label>
                        </td>
                        <td>
                            <input type="password" id="senha" name="senha" required value="${profissional.usuario.senha}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="admin"> <fmt:message key="campo.admin" /> </label>
                        </td>
                        <td>
                            <input type="checkbox" id="admin" name="admin" value="${profissional.usuario.admin}" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="<fmt:message key="botao.salvar" />" class="button" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</fmt:bundle>
</html>
