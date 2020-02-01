<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../../resources/css/style.css" %>
    </style>
    <title>Department Information</title>
</head>
<body>
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
        </tr>
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
        </tr>
    </table>
</body>
</html>