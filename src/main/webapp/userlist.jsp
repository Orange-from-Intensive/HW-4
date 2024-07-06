<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users List</title>
</head>
<body>
<h1>Users List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthdate</th>
        <th>Team</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
            <td><c:out value="${user.birthDate}" /></td>
            <td><c:out value="${user.team}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}"><button>Edit</button></a>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}" method="post">
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
