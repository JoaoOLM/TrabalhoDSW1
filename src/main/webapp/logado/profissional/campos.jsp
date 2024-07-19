<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="message">
    <body>
        <div class="card">
            <h4>
                <a href="${pageContext.request.contextPath}/profissionais" class="sair">
                   Voltar
                </a>
            </h4>
            <div class="tableCadastrar">
                <table>

                    <caption class="title">
                        <c:choose>
                            <c:when test="${profissional != null}">
                                Atualizar Profissional
                            </c:when>
                            <c:otherwise>
                                Criar Profissional
                            </c:otherwise>
                        </c:choose>
                    </caption>

                    <c:if test="${profissional != null}">
                        <input type="hidden" name="id" value="${profissional.id}" />
                    </c:if>
                    <tr>
                        <td>
                            <label for="nome"> Nome </label>
                        </td>
                        <td>
                            <input type="text" id="nome" name="nome" required value="${profissional.usuario.nome}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cpf"> CPF </label>
                        </td>
                        <td>
                            <input type="text" id="cpf" name="cpf" required value="${profissional.cpf}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="telefone"> Telefone </label>
                        </td>
                        <td>
                            <input type="text" id="telefone" name="telefone" required value="${profissional.telefone}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="sexo"> Sexo </label>
                        </td>
                        <td>
                           <select id="sexo" name="sexo" required>
                                <option value="" >Selecione o sexo</option>
                                <option value="0" >Masculino</option>
                                <option value="1" >Feminino</option>
                                <option value="2" >>Outros</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="dataNascimento"> Data de Nascimento </label>
                        </td>
                        <td>
                            <input type="date" id="dataNascimento" name="dataNascimento" required value="${profissional.dataNascimento}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email"> E-mail </label>
                        </td>
                        <td>
                            <input type="email" id="email" name="email" required value="${profissional.usuario.email}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="senha"> Senha </label>
                        </td>
                        <td>
                            <input type="password" id="senha" name="senha" required value="${profissional.usuario.senha}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="admin"> Admin </label>
                        </td>
                        <td>
                            <input type="checkbox" id="admin" name="admin" value="${profissional.usuario.admin}" />
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
    </body>
</fmt:bundle>

</html>