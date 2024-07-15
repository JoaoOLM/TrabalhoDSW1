<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Livraria Virtual</title>
</head>
<body>
<div align="center">
    <h1>Gerenciamento de Smartphones</h1>
    <h2>
        <a href="/${requestScope.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/smartphones/cadastro">Adicione Novo Smartphone</a>
    </h2>
</div>

<div align="center">
    <table border="1">
        <caption>Lista de Smartphones</caption>
        <tr>
            <th>ID</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Sistema Operacional</th>
            <th>Memória RAM (GB)</th>
            <th>Armazenamento (GB)</th>
            <th>Tamanho da Tela (polegadas)</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="smartphone" items="${requestScope.listaSmartphones}">
            <tr>
                <td>${smartphone.id}</td>
                <td>${smartphone.marca}</td>
                <td>${smartphone.modelo}</td>
                <td>${smartphone.sistemaOperacional}</td>
                <td>${smartphone.memoriaRamGB}</td>
                <td>${smartphone.armazenamentoGB}</td>
                <td>${smartphone.tamanhoTela}</td>
                <td>${smartphone.preco}</td>
                <td>
                    <a href="/${requestScope.contextPath}/smartphones/edicao?id=${smartphone.id}">Edição</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/${requestScope.contextPath}/smartphones/remocao?id=${smartphone.id}"
                       onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                        Remoção
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
