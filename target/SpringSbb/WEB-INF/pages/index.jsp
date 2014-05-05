<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SBB index</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center-content.css">

</head>
<body>
<%@include file="foobar/header.jsp" %>
<div id="content">
    <br>

    <div class="navigation">
        <a href="${pageContext.request.contextPath}/stations">see list of stations</a>
        <a style="margin-left: 50px" href="${pageContext.request.contextPath}/trains">find yor trains</a>
    </div>
    <br>
    <img class="shadow" width="800px" height="392px"
         src="${pageContext.request.contextPath}/images/stadler-rail-sbb-fernverkehr-doppelstock-designbook-1-03.jpg">
    <br>

    <div id="welcomeText" style="width: 700px; margin-left:50px; margin-right: 50px">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis doloremque eveniet maxime molestiae porro
        quaerat quod sint voluptatum? Alias amet hic id inventore nam praesentium quae quis, quo veritatis voluptatum?
    </div>
</div>
<%@include file="foobar/footer.jsp" %>
</body>
</html>
