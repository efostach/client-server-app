<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../../resources/css/style.css" %>
    </style>
    <title>Employee Information</title>
</head>
<body>
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="200">First Name</th>
        <th width="200">Last Name</th>
        <th width="250">Date of Birth</th>
        <th width="120">Salary</th>
        <th width="250">Start Date</th>
        <th width="120">Department</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <tr>
        <td>${employee.id}</td>
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.dateOfBirth}</td>
        <td>${employee.salary}</td>
        <td>${employee.startDate}</td>
        <td>${employee.department}</td>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
</table>
</body>
</html>