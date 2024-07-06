<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>User manager</title>
</head>
<style>
    body{
        font-family: Arial, Helvetica, sans-serif;
    }
    h1 {
        text-align: center;
        margin: 0;
    }
    body>p {
        text-align: center;
    }
</style>
<body>
<h1><%= "Choose your destiny...." %></h1>
<p>
<a href="${pageContext.request.contextPath}/user?action=list">List users</a>
<a href="user?action=add">Add user</a>
</p>
</body>
</html>