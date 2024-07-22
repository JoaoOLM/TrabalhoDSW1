<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="messages">
<html>
    <head>
        <title><fmt:message key="errorTitle" /></title>
    </head>
    <body>
        <center>
            <h1><fmt:message key="errorTitle" /></h1>
            <h2><fmt:message key="errorMessage" /> <br/> ${pageContext.exception.message}</h2>
        </center>    
    </body>
</html>
