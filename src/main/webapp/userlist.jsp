<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.orange.hw4.util.UserUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Users List</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
    <main class="list">
        <h1>Users List</h1>

        <form action="${pageContext.request.contextPath}/user">
            <input type="hidden" name="action" value="generatePairs"/>
            <p><button class="pairs">Create pairs</button></p>
        </form>

        <div class="tables">
            <h2 class="orange">Orange Team</h2>
            <div class="table">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Age</th>
                            <th>Team</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orangeTeam}" var="user">
                            <tr>
                                <td class="td"><c:out value="${user.name}"/></td>
                                <td class="td"><c:out value="${user.surname}"/></td>
                                <td class="td"><c:out value="${UserUtils.getAge(user.birthDate)}"/></td>
                                <td class="td"><c:out value="${user.team}"/></td>
                                <td class="td">
                                    <c:if test="${not empty user}">
                                        <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                            <button class="action">Edit</button>
                                        </a>
                                        <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                            method="post" style="display:inline;">
                                            <button class="action">Delete</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <h2 class="pink">Pink Team</h2>

            <div class="table">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Age</th>
                            <th>Team</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pinkTeam}" var="user">
                            <tr>
                                <td class="td"><c:out value="${user.name}"/></td>
                                <td class="td"><c:out value="${user.surname}"/></td>
                                <td class="td"><c:out value="${UserUtils.getAge(user.birthDate)}"/></td>
                                <td class="td"><c:out value="${user.team}"/></td>
                                <td class="td">
                                    <c:if test="${not empty user}">
                                        <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                            <button>Edit</button>
                                        </a>
                                        <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                            method="post" style="display:inline;">
                                            <button>Delete</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <h2>Out of any Team</h2>

            <div class="table">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Age</th>
                            <th>Team</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${noTeam}" var="user">
                            <tr>
                                <td class="td"><c:out value="${user.name}"/></td>
                                <td class="td"><c:out value="${user.surname}"/></td>
                                <td class="td"><c:out value="${UserUtils.getAge(user.birthDate)}"/></td>
                                <td class="td"><c:out value="${user.team}"/></td>
                                <td class="td">
                                    <c:if test="${not empty user}">
                                        <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                            <button>Edit</button>
                                        </a>
                                    <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                        method="post" style="display:inline;"><button>Delete</button>
                                    </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>

</html>
