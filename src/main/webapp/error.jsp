<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Error</title>
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

<h1>Error</h1>

<p>You tried to enter incorrect data</p>

<form action="${pageContext.request.contextPath}/">
    <p><button>go back to main page...</button></p>
</form>

</body>
</html>
