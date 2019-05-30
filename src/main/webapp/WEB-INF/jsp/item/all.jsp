<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<html:form modelAttribute="all" action="${basePath}/all/jieshou" method="post">
    <html:input path="allName"/>
    <br>
    <form:checkbox path="box"  value="java"/>java
    <form:checkbox path="box"  value="c"/>c
    <br>
    <input type="submit" value="subumit">
</html:form>

</body>
</html>
