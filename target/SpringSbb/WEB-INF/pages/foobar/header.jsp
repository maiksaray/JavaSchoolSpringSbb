<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div id="logodiv" style="position: absolute; left:0">--%>
    <%--<img src="${pageContext.request.contextPath}/images/sbb_200(2).png" height="66" width="118">--%>
<%--</div>--%>
<div id="header" style="text-align: center; top:0">Welcome to our service</div>
<div class="navigation-bar" style="position: absolute; top:70px" >
    <a href="${pageContext.request.contextPath}/">Index</a>
    <br>
    <a href="${pageContext.request.contextPath}/stations">stations</a>
    <br>
    <a href="${pageContext.request.contextPath}/trains">trains</a>
</div>
<div id="loginLink" style="position: absolute; right: 0; top:0">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <a href="${pageContext.request.contextPath}/logout">Log Out</a>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_ADMIN">
        <a href="${pageContext.request.contextPath}/login">Log In</a>
    </sec:authorize>
</div>
