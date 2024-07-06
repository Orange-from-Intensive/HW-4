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
        width: 80vw;
        border-style: solid;
        border-radius: 10px;
    }

    body>div>div>table {
        margin: 0 auto;
        width: 80vw;
    }

    body>div>table>tbody>tr>td {
        padding: 10px;
    }

    body>div>div>table>tbody>tr>td:nth-child(6) {
        width: 15%;
    }

    body>div>div>table>tbody>tr>td:nth-child(6)>a>button {
        margin: 0 auto;
        text-align: center;
        width: auto;
    }
</style>

<body>
    <h1>Users List</h1>
    <p><a href="${pageContext.request.contextPath}/">go back to main page...</a></p>

    
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
                            <td>
                                <c:out value="${user.id}" /> 
                            </td>
                            <td>
                                <c:out value="${user.name}" />
                            </td>
                            <td>
                                <c:out value="${user.surname}" />
                            </td>
                            <td>
                                <c:out value="${user.birthDate}" />
                            </td>
                            <td>
                                <c:out value="${user.team}" />
                            </td>
                            <td>
                               
                                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                    <button>Edit</button>
                                </a>
                                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                    method="post" style="display:inline;">
                                    <button>Delete</button>
                                </form>

                            </td>
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
                            <td>
                                <c:out value="${user.id}" />
                            </td>
                            <td>
                                <c:out value="${user.name}" />
                            </td>
                            <td>
                                <c:out value="${user.surname}" />
                            </td>
                            <td>
                                <c:out value="${user.birthDate}" />
                            </td>
                            <td>
                                <c:out value="${user.team}" />
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                    <button>Edit</button>
                                </a>
                                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                    method="post" style="display:inline;">
                                    <button>Delete</button>
                                </form>
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
                            <td>
                                <c:out value="${user.id}" />
                            </td>
                            <td>
                                <c:out value="${user.name}" />
                            </td>
                            <td>
                                <c:out value="${user.surname}" />
                            </td>
                            <td>
                                <c:out value="${user.birthDate}" />
                            </td>
                            <td>
                                <c:out value="${user.team}" />
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}">
                                    <button>Edit</button>
                                </a>
                                <form action="${pageContext.request.contextPath}/user?action=remove&id=${user.id}"
                                    method="post" style="display:inline;">
                                    <button>Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>