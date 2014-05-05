<%--
  Created by IntelliJ IDEA.
  User: maiks_000
  Date: 5/2/14
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>Your Ticket</h1>

    <div class="datagrid">
        <table>
            <thead>
            <tr>
                <th>train</th>
                <th>from</th>
                <th>to</th>
                <th>first Name</th>
                <th>last Name</th>
                <th>birth date</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${ticket.train}</td>
                <td>${ticket.startStation}</td>
                <td>${ticket.endStation}</td>
                <td>${ticket.passenger.firstName}</td>
                <td>${ticket.passenger.lastName}</td>
                <td>${ticket.passenger.birthDate}</td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
