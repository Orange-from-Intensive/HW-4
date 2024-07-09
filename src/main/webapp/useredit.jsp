<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <main>
        <h1>Edit User</h1>
        <form action="${pageContext.request.contextPath}/">
            <p><button>Go back to main page...</button></p>
        </form>
        <div class="border">
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
                    <select name="team" required><br><br>
                        <option value="noteam">no team</option>
                        <option value="orange">orange</option>
                        <option value="pink">pink</option>
                    </select>
                    <input type="submit" value="Update User">

                </form>
            </c:if>
            <span class="message">
                <c:if test="${not empty param.errorMessage}">
                    <p style="color:red;" class="Error">${param.errorMessage}</p>
                </c:if>
            </span>
        </div>
    </main>
</body>
</html>