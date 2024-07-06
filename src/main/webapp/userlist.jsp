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

<h2>Orange Team</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthdate</th>
        <th>Team</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orangeTeam}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
            <td><c:out value="${user.birthDate}" /></td>
            <td><c:out value="${user.team}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                    <button>Edit</button>
                </a>
                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}" method="post" style="display:inline;">
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Pink Team</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthdate</th>
        <th>Team</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pinkTeam}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
            <td><c:out value="${user.birthDate}" /></td>
            <td><c:out value="${user.team}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                    <button>Edit</button>
                </a>
                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}" method="post" style="display:inline;">
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Out of any Team</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthdate</th>
        <th>Team</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${noTeam}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
            <td><c:out value="${user.birthDate}" /></td>
            <td><c:out value="${user.team}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                    <button>Edit</button>
                </a>
                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}" method="post" style="display:inline;">
                    <button>Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
