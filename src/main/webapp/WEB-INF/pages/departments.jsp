<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<html>
<head>
    <style>
        <%@include file="../../resources/css/style.css" %>
    </style>
    <title>Departments</title>
</head>
</br>

<a href="/">Back to main menu</a>

<h1>Departments</h1>

<c:if test="${!empty listDepartments}">
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Name</th>
    </tr>
    <c:forEach items="${listDepartments}" var="department">
        <tr>
            <td>${department.id}</td>
            <td><a href="/departments/data/${department.id}" target="_blank">${department.name}</a></td>
            <td width="60"><a href="<c:url value='/departments/edit/${department.id}'/>">Edit</a></td>
            <td width="60"><a href="<c:url value='/departments/remove/${department.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>

</br>

<h1>Add New Department</h1>

<c:url var="addAction" value="/departments/add"/>

<form:form action="${addAction}" commandName="department">
<table>
    <c:if test="${!empty department.name}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8" disabled="true"/>
            <form:hidden path="id"/>
        </td>
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty department.name}">
            <input type="submit" class="button"
                   value="<spring:message text="Edit"/>"/>
            </c:if>
            <c:if test="${empty department.name}">
            <input type="submit" class="button"
                   value="<spring:message text="Add"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>