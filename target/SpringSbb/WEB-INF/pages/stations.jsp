<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Stations</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table-style.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <h1>Stations</h1>

    <div class="datagrid">
        <table>
            <thead>
            <tr>
                <th>Station name</th>
                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                    <th>remove</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${stations}" var="station">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/stations/schedule/${station}">${station}</a>
                    </td>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <td>
                            <a href="${pageContext.request.contextPath}/stations/remove/${station}">remove this
                                station</a>
                        </td>
                    </sec:authorize>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <form action="${pageContext.request.contextPath}/stations/create">
            <label for="stationName">Add Station</label><input id="stationName" name="stationName" type="text">
            <input type="submit">
        </form>
    </sec:authorize>
</div>

<%@include file="foobar/footer.jsp" %>
</body>
</html>
