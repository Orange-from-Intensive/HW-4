<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>User manager</title>
</head>
<style>

    body{
        font-family: Arial, Helvetica, sans-serif;
    }
    
    h1 {
        text-align: center;
        margin: 0;
    }

    body>form>p {
        text-align: center;
    }
</style>

<body>
    <h1><%= "Choose your destiny...." %></h1>
    
    <form action="${pageContext.request.contextPath}/user?action=list"><p><button>List users</button></p></form>
    <form action="$user?action=add"><p><button>Add user</button></p></form>

</body>
</html>