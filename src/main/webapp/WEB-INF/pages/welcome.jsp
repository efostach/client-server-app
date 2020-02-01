<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../resources/css/style.css" rel="stylesheet">
    <title>Welcome page</title>
</head>
<body>
</br>
<h1>Menu</h1>
</br>
<a href="<c:url value="/employees"/>" target="_blank">Employees Information</a></br>
<a href="<c:url value="/departments"/>" target="_blank">Departments Information</a>
</body>
</html>