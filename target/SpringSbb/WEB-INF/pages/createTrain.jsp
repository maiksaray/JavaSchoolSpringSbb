<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create new train</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id="content">
    <div id="errorDiv" hidden="hidden"></div>
    <form action="${pageContext.request.contextPath}/trains/create/submit">
        <div id="stations">
            <div class="stationinput">
                <label for="stationfirst">first station</label>
                <select id="stationfirst" name="stationfirst" class="stationSelect">
                    <c:forEach items="${stations}" var="station">
                        <option>${station}</option>
                    </c:forEach>
                </select>
                <label for="stationfirsttime">time:</label>
                <input id="stationfirsttime" name="station1time" class="stationTime" type="time">
            </div>
            <div class="stationinput">
                <label for="stationlast">last station</label>
                <select id="stationlast" name="stationlast" class="stationSelect">
                    <c:forEach items="${stations}" var="station">
                        <option>${station}</option>
                    </c:forEach>
                </select>
                <label for="stationlasttime">time:</label>
                <input id="stationlasttime" name="station1time" class="stationTime" type="time">
            </div>
        </div>
        <label for="numberOfSeats">Number of seats</label>
        <input id="numberOfSeats" name="numberOfSeats" type="number">
        <input type="submit" hidden="hidden">
        <input onclick="utils.collectInputData()" type="button" value="Add Train">
    </form>
    <button onclick="utils.appendNewStationInput()">Add another station</button>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>