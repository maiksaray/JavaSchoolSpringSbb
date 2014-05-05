<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maiks_000
  Date: 5/2/14
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>${stationName}</h1>

    <div class="datagrid">
        <table>
            <thead>
            <tr>
                <th>Train</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${Schedules}" var="scheduleItem">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/trains/schedule/${scheduleItem.key}">${scheduleItem.key}</a>
                    </td>
                    <td>${scheduleItem.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="foobar/footer.jsp" %>
</body>

</html>
