<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tickets for train</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>tickets</h1>
    <c:if test="${!empty tickets}">
    <div class="datagrid">
        <table>
            <thead>
            <tr>
                <th>from</th>
                <th>to</th>
                <th>first name</th>
                <th>last name</th>
                <th>birth date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tickets}" var="ticket">
                <tr>
                    <td>${ticket.startStation.name}</td>
                    <td>${ticket.endStation.name}</td>
                    <td>${ticket.passenger.firstName}</td>
                    <td>${ticket.passenger.lastName}</td>
                    <td>${ticket.passenger.birthDate}</td>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <td>
                            <a href="${pageContext.request.contextPath}/tickets/remove/${ticket.ticketId}">remove</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
    </div>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>