<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Journal</title>
</head>
<body>
<h1>Journal</h1>
<form action="${pageContext.request.contextPath}/"><p><button>go back to main page...</button></p></form>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>User1</th>
                <th>mark1</th>
                <th>user2</th>
                <th>mark2</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${journalView}" var="pairs">
                <tr>
                    <td><c:out value="${journalView.lessonDate}"/></td>
                    <td><c:out value="${journalView.user1.name} ${journalView.user1.surname}"/></td>
                    <td><c:out value="${journalView.mark1}"/></td>
                    <td><c:out value="${journalView.user2.name} ${journalView.user2.surname}"/></td>
                    <td><c:out value="${journalView.mark2}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>