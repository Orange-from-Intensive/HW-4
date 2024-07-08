<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }
    h1 {
        margin: 0;
        text-align: center;
    }
    body >form> p{
        text-align:center;
    }
    body>div {
        margin: 0 auto;
        align-content: center;
        width: 20vw;
        border-style: solid;
        border-radius: 5px;
    }

    .form {
        width: auto;
        margin: 10px;
        text-align: center;
    }
</style>
<body>
<h1>Add User</h1>
<form action="${pageContext.request.contextPath}/"><p><button>go back to main page...</button></p></form>

<div>
    <form class="form" action="${pageContext.request.contextPath}/user?action=add" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" required><br><br>
        <label for="surname">Surname:</label>
        <input type="text" name="surname" required><br><br>
        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" name="dateOfBirth" required><br><br>
        <label for="team">Team:</label>
        <select name="team" required><br><br>
            <option value="noteam">no team</option>
            <option value="orange">orange</option>
            <option value="pink">pink</option>
        </select>
        <input type="submit" value="Add User">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

</div>
</body>
</html>
