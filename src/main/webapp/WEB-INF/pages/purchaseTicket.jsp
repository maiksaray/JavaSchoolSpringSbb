<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Your ticket</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>Get your ticket here</h1>

    <%@include file="trainScheduleInternal.jsp" %>

    <form action="${pageContext.request.contextPath}/tickets/purchaseTicket">
        <input type="text" name="trainId" id="trainId" hidden="hidden" value="${trainId}">
        <label for="startStation">From:</label>
        <select id="startStation" name="startStation">
            <c:forEach items="${stations}" var="station">
                <option>${station}</option>
            </c:forEach>
        </select>
        <label for="endStation">To:</label>
        <select id="endStation" name="endStation">
            <c:forEach items="${stations}" var="station">
                <option>${station}</option>
            </c:forEach>
        </select>
        <br>
        <label for="firstName">first name</label>
        <input id="firstName" name="firstName" type="text">
        <label for="lastName">last name</label>
        <input id="lastName" name="lastName" type="text">
        <br>
        <label for="birthDate">birth date</label>
        <input id="birthDate" name="birthDate" type="date">
        <input type="submit" name="submit">
    </form>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
