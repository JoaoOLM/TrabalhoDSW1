<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

                <html>
                <fmt:bundle basename="message">

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <title>
                            Empresas
                        </title>
                        <link href="${pageContext.request.contextPath}/index.css" rel="stylesheet" type="text/css" />
                    </head>

                    <body>
                        <div class="right">
                            <p style="margin-right: 20px;">Olá, ${sessionScope.usuarioLogado.nome}! </p>
                            <a href="${pageContext.request.contextPath}/logout.jsp" class="sair">
                                LOGOUT
                            </a>
                        </div>

                        <% String contextPath=request.getContextPath().replace("/", "" ); %>
                            <div class="table">

                                <div style="margin-top: 2rem;">
                                    <div class="title">
                                       Lista de empresas
                                    </div>
                                    <table border="1">
                                        <tr>
                                            <th>
                                                Nome da empresa
                                            </th>
                                            <th>
                                                Email
                                            </th>
                                            <th>
                                                CNPJ
                                            </th>
                                            <th>
                                               Descricao
                                            </th>
                                            <th>
                                                Cidade
                                            </th>
                                            <th>
                                                Ações
                                            </th>
                                        </tr>

                                        <c:forEach var="empresa" items="${requestScope.listaEmpresas}">
                                            <tr>
                                                <td>${empresa.usuario.nome}</td>
                                                <td>${empresa.usuario.email}</td>
                                                <td>${empresa.CNPJ}</td>
                                                <td>${empresa.descricao}</td>
                                                <td>${empresa.cidade}</td>
                                                <td>
                                                    <a href="/<%= contextPath%>/empresas/edicao?id=${empresa.id}">
                                                        Editar
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/empresas/cadastro" class="button">
                                Criar
                            </a>
                    </body>
                </fmt:bundle>

                </html>