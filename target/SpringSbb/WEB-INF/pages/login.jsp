<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content>
    <c:if test="${!empty error}">
        there was an error
    </c:if>
    <form action="<c:url value="/j_spring_security_check" />" method="post">
        <label for="username">Username</label>
        <input id="username" type="text" name="j_username">
        <br>
        <label for="password">Password</label>
        <input id="password" type="password" name="j_password">
        <br>
        <label for="rememberme">Remember Me</label>
        <input id="rememberme" type="checkbox" name="_spring_security_remember_me">
        <input type="submit">
    </form>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
