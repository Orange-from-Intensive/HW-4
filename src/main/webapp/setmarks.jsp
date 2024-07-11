<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Set Marks</title>
</head>
<body>
<h1>Set Marks</h1>
<form action="${pageContext.request.contextPath}/"><p><button>go back to main page...</button></p></form>
<div>
    <form action="${pageContext.request.contextPath}/user?action=updatemarks" method="post">
        <input type="hidden" name="journal"></input>
        
        <input type="text" name="mark1" value="<c:out value="${journal.mark1}" />"></input>
        <input type="text" name="mark2" value="<c:out value="${journal.mark2}" />"></input>
        
        <input type="submit" value="set marks">
    </form>
</div>
</body>
</html>