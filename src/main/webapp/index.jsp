<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    
<p><a href="${pageContext.request.contextPath}/user?action=list">List users</a></p>
<p><a href="${pageContext.request.contextPath}/user?action=addget">Add user</a></p>
<p><a href="${pageContext.request.contextPath}/user?action=journal">Journal</a></p>




</body>
</html>