<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <main>
        <h1>Add User</h1>
        <form action="${pageContext.request.contextPath}/">
            <p><button>Go back to main page...</button></p>
        </form>

        <div class="border">
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

            <span class="message">
                <c:if test="${not empty errorMessage}">
                    <p style="color:red;">${errorMessage}</p>
                </c:if>
            </span>

        </div>
    </main>
</body>
</html>
