<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<style>
    body{
        font-family: Arial, Helvetica, sans-serif;
    }

    h1{
        margin: 0;
    text-align: center;
    }
    body >form> p{
        text-align:center;
    }
    

    body >form > div{
        margin: 0 auto;
        align-content: center;
        width: 20vw;
        border-style: solid;
        border-radius: 5px;
    }

    .form{
        width: auto;
        margin: 10px;
        text-align: center;
    }
    .Error{
        text-align: center;
    }

</style>
<body>
<h1>Edit User</h1>
<form action="${pageContext.request.contextPath}/"><p><button>go back to main page...</button></p></form>
<div>
    <c:if test="${user != null}">
        <c:if test="${not empty errorMessage}">
            <p class="Error">${errorMessage}</p>
        </c:if>
        <form class="form" action="${pageContext.request.contextPath}/user?action=update" method="post">
            <input type="hidden" name="id" value="<c:out value="${user.id}" />">
            <label for="name">Name:</label>
            <input type="text" name="name" value="<c:out value="${user.name}" />" required><br><br>
            <label for="surname">Surname:</label>
            <input type="text" name="surname" value="<c:out value="${user.surname}" />" required><br><br>
            <label for="birthDate">Date of Birth:</label>
            <input type="date" name="birthDate" value="<c:out value="${user.birthDate}" />" required><br><br>
            <label for="team">Team:</label>
            <input type="text" name="team" value="<c:out value="${user.team}" />" required><br><br>
            <input type="submit" value="Update User">

        </form>
    </c:if>
    <c:if test="${not empty param.errorMessage}">
        <p style="color:red;" class="Error">${param.errorMessage}</p>
    </c:if>
</div>
</body>
</html>