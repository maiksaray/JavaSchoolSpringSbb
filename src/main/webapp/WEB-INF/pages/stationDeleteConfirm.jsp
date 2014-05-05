<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Station deleted</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content style="color: red">
   Can't delete, there are trains
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
