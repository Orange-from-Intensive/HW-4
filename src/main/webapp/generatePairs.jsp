<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fight club</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <main>
        <h1>Fight!</h1>
        <form action="${pageContext.request.contextPath}/">
            <p><button>Go back to main page...</button></p>
        </form>

        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th class="orange">Orange Team Member</th>
                        <th class="pink">Pink Team Member</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${opponents}" var="opponent">
                        <tr>
                            <td class="names"><c:out value="${opponent.orange.name} ${opponent.orange.surname}" /></td>
                            <td class="names"><c:out value="${opponent.pink.name} ${opponent.pink.surname}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <form action="${pageContext.request.contextPath}/user">
            <input type="hidden" name="action" value="generatePairs"/>
            <p><button class="pairs">New pairs</button></p>
        </form>
    </main>
</body>
</html>
