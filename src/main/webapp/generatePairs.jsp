<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fight club</title>
</head>
<style>
    
    h1 {
        margin: 0;
        text-align: center;
    }

    .table {
        margin: 0 auto;
        width: fit-content;
        text-align: center;
        border-style: solid;
        border-radius: 10px;
    }

    body>form>p{
        text-align:center;
    }
    .names{
        text-align: center;
    }

</style>

<body>

    <h1>Fight!</h1>
    <form action="${pageContext.request.contextPath}/"><p><button>go back to main page...</button></p></form>

    <div class="table">
        <table>
            <thead>
                <tr>
                    <th style="text-shadow: 0px 0px 15px orange;">Orange Team Member</th>
                    <th style="text-shadow: 0px 0px 30px pink;">Pink Team Member</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${opponents}" var="opponent">
                    <tr>
                        <td class="names"><c:out value="${opponent.orange.name} ${opponent.orange.surname}" />awd</td>
                        <td class="names"><c:out value="${opponent.pink.name} ${opponent.pink.surname}" />dwd</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</body>
</html>
