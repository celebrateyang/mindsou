
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <input type="submit" value="submit">
</form>
</body>
</html>
