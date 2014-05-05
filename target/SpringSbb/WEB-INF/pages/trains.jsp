<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Trains</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/enableAutoComplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>Find your train here</h1>

    <form method="post" action="${pageContext.request.contextPath}/trains/filtered">


        <label for="fromStationInput">from:</label>
        <input type="text" name="fromStation" id="fromStationInput" value="${from}"/>


        <label for="toStationInput">to:</label>
        <input type="text" name="toStation" id="toStationInput" value="${to}"/>
        <br>
        <label for="fromTime">from time</label>
        <input id="fromTime" name="fromTime" type="time" value="${start}">

        <label for="toTime">to time</label>
        <input id="toTime" name="toTime" type="time" value="${end}">

        <input type="submit" value="find"/>
    </form>
    <c:if test="${!empty trains}">
        <label for="trainsTable">Your Trains</label>

        <div class="datagrid">
            <table id="trainsTable">
                <thead>
                <tr>
                    <th>Train</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Tickets</th>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <th>remove</th>
                        <th>view Tickets</th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${trains}" var="train">
                    <tr class="alt">
                        <td>
                            <a href="${pageContext.request.contextPath}/trains/schedule/${train.trainId}">${train.trainId}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/stations/schedule/${train.startStation}">${train.startStation}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/stations/schedule/${train.endStation}">${train.endStation}</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/tickets/purchase/${train.trainId}">get
                                ticket</a>
                        </td>
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                            <td>
                                <a href="${pageContext.request.contextPath}/trains/remove/${train.trainId}">Remove this
                                    train</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/tickets/showfortrain/${train.trainId}">list
                                    tickets</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty trains}">
        <label>There are no trains for you</label>
    </c:if>
    <sec:authorize ifAllGranted="ROLE_ADMIN">
        <a href="${pageContext.request.contextPath}/trains/create">go to train creation</a>
    </sec:authorize>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
