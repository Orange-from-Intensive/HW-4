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
                    <td><c:out value="${pairs.lessonDate}"/></td>
                    <td><c:out value="${pairs.user1.name} ${pairs.user1.surname}"/></td>
                    <td><c:out value="${pairs.mark1}"/></td>
                    <td><c:out value="${pairs.user2.name} ${pairs.user2.surname}"/></td>
                    <td><c:out value="${pairs.mark2}"/></td>
                    <td><c:if test="${not empty pairs}">
                        <a href="${pageContext.request.contextPath}/user?action=setmarks?id=${pairs.id}">
                            <button>Edit</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>