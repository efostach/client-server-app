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
    <title>Employees</title>
</head>
</br>

<a href="/">Back to main menu</a>

<h1>Employees</h1>

<c:if test="${!empty listEmployees}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="200">First Name</th>
            <th width="200">Last Name</th>
            <th width="250">Date of Birth</th>
            <th width="120">Salary</th>
            <th width="250">Start Date</th>
            <th width="120">Department</th>
        </tr>
        <c:forEach items="${listEmployees}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td><a href="/employees/data/${employee.id}" target="_blank">${employee.firstName}</a></td>
                <td>${employee.lastName}</td>
                <td>${employee.dateOfBirth}</td>
                <td>${employee.salary}</td>
                <td>${employee.startDate}</td>
                <td><a href="/departments/data/${employee.department}" target="_blank">${employee.department}</a></td>
                <td width="60"><a href="<c:url value='/employees/edit/${employee.id}'/>">Edit</a></td>
                <td width="60"><a href="<c:url value='/employees/remove/${employee.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</br>

<h1>Add New Employee</h1>

<c:url var="addAction" value="/employees/add"/>

<form:form action="${addAction}" commandName="employee">
    <table>
        <c:if test="${!empty employee.firstName}">
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
                <form:label path="firstName">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="Last Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateOfBirth">
                    <spring:message text="Date Of Birth"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="dateOfBirth"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="salary">
                    <spring:message text="Salary"/>
                </form:label>
            </td>
            <td>
                <form:input path="salary"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="startDate">
                    <spring:message text="Start Date"/>
                </form:label>
            </td>
            <td>
                <form:input type="date" path="startDate"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="department">
                    <spring:message text="Department"/>
                </form:label>
            </td>
            <td>
                <form:input path="department"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty employee.firstName}">
                    <input type="submit" class="button"
                           value="<spring:message text="Edit"/>"/>
                </c:if>
                <c:if test="${empty employee.firstName}">
                    <input type="submit" class="button"
                           value="<spring:message text="Add"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>