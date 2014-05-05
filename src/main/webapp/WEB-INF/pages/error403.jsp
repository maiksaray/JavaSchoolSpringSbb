<html>
<head>
    <title>error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id=content style="color: red">
    We don't know how you got here, but maybe, you want to <a href="${pageContext.request.contextPath}/login">login</a>
    <br>
    Or go to <a href="${pageContext.request.contextPath}/index">main page</a>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>