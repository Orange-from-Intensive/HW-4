<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    body>div {
        margin: 0 auto;
        align-content: center;
        width: auto;
    }
    .form {
        margin: 0 auto;
        text-align: center;
    }
</style>
<body>
<h1>Add User</h1>
<div>

<form class="form" action="${pageContext.request.contextPath}/user?action=add" method="post">
    <label for="name">Name:</label>
    <input type="text" name="name" required><br><br>
    <label for="surname">Surname:</label>
    <input type="text" name="surname" required><br><br>
    <label for="dateOfBirth">Date of Birth:</label>
    <input type="date" name="dateOfBirth" required><br><br>
    <label for="team">Team:</label>
    <input type="text" name="team" required><br><br>
    <input type="submit" value="Add User">
</form>
</div>
</body>
</html>
