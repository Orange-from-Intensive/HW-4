<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<h1>Edit User</h1>
<c:if test="${user != null}">
    <form action="${pageContext.request.contextPath}/user?action=edit" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" required><br><br>
        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" value="${user.surname}" required><br><br>
        <label for="birthDate">Date of Birth:</label>
        <input type="date" id="birthDate" name="birthDate" value="${user.birthDate}" required><br><br>
        <input type="submit" value="Update User">
    </form>
</c:if>
<c:if test="${user == null}">
    <p>Error: User not found!</p>
</c:if>
</body>
</html>
