<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h1>Add User</h1>
<form action="${pageContext.request.contextPath}/user?action=add" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="surname">Surname:</label>
    <input type="text" id="surname" name="surname" required><br><br>
    <label for="dateOfBirth">Date of Birth:</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" required><br><br>
    <input type="submit" value="Add User">
</form>
</body>
</html>
