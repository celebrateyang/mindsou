
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${allErrors!=null}">
    <c:forEach items="${allErrors}" var="error">
        ${error.defaultMessage}
    </c:forEach>
</c:if>
<form action="${basePath}/item/goitems" method="post" >
    <input type="text" name="name" value="${items.name}"/>
    <input type="text" name="price"/>
    <input type="text" name="pic"/>
    <input type="text" name="createtime"/>
<%--<input type="text" name="createtime" value="<fmt:formatDate value="${items.createtime}" var="createtime" pattern="yyyy-MM-dd"/>"/>--%>
    <select name="haha" multiple="multiple">
        <option value="1" selected="selected">1</option>
        <option value="2" selected="selected">2</option>
        <option value="3">3</option>
        <option value="4" selected="selected">4</option>
    </select>
    <input type="submit" value="submit">
</form>

</body>
</html>
