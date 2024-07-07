<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fight club</title>
</head>
<body>

<h1>Fight!</h1>

<table>
    <thead>
    <tr>
        <th>Orange Team Member</th>
        <th>Pink Team Member</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${opponents}" var="opponent">
        <tr>
            <td><c:out value="${opponent.orange.name} ${opponent.orange.surname}" /></td>
            <td><c:out value="${opponent.pink.name} ${opponent.pink.surname}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
