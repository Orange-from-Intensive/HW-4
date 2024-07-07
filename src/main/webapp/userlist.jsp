<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Users List</title>
</head>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        margin: 0;
        text-align: center;
    }

    h2 {
        margin: 0;
        text-align: center;
    }
    p{
        text-align:center;
    }


    body>table>thead>tr {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
        text-align: center;
    }

    body>table>tbody>tr {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
        text-align: center;
    }

    body>div>div {
        margin: 0 auto;
        width: 50vw;
        border-style: solid ;
        border-radius: 6px;
    }

    body>div>div>table {
        margin: 0 auto;
        width: 50vw;
    }

    body>div>tr>table>tbody>tr>td {
        padding: 10px;
        text-align: center;
    }

    body>div>div>table>tbody>tr>td:nth-child(6) {
        width: 15%;
    }

    body>div>div>table>tbody>tr>td:nth-child(6)>a>button {
        margin: 0 auto;
        text-align: center;
        width: auto;
    }
    .td{
        text-align: center;
    }
</style>

<body>
    <h1>Users List</h1>
    <form action="${pageContext.request.contextPath}/">
        <p><button>go back to main page...</button></p>
    </form>
    
    <form action="${pageContext.request.contextPath}/user?actions=generatePairs">
        <p><button>create pairs</button></p>
    </form>
    
    <div class="tables">
        <h2 style="text-shadow: 0px 0px 15px orange;">Orange Team</h2>
        <div class="table">
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
                            <td class="td"><c:out value="${user.id}"/></td>
                            <td class="td"><c:out value="${user.name}"/></td>
                            <td class="td"><c:out value="${user.surname}"/></td>
                            <td class="td"><c:out value="${user.birthDate}"/></td>
                            <td class="td"><c:out value="${user.team}"/></td>
                            <td class="td"><c:if test="${not empty user}">
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

        <h2 style="text-shadow: 5px 5px 5px pink;">Pink Team</h2>

        <div class="table">
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
                            <td class="td"><c:out value="${user.id}"/></td>
                            <td class="td"><c:out value="${user.name}"/></td>
                            <td class="td"><c:out value="${user.surname}"/></td>
                            <td class="td"><c:out value="${user.birthDate}"/></td>
                            <td class="td"><c:out value="${user.team}"/></td>
                            <td class="td"><c:if test="${not empty user}">
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

        <div>
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
                            <td class="td"><c:out value="${user.id}"/></td>
                            <td class="td"><c:out value="${user.name}"/></td>
                            <td class="td"><c:out value="${user.surname}"/></td>
                            <td class="td"><c:out value="${user.birthDate}"/></td>
                            <td class="td"><c:out value="${user.team}"/></td>
                            <td class="td"><c:if test="${not empty user}">
                                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}"><button>Edit</button>
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
</body>

</html>
