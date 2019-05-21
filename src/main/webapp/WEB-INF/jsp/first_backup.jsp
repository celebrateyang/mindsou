<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.11.2.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/resources/css/layoutit.css">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/minda.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.form-3.09.js"></script>
    <script type="text/javascript">
        var fullPath = '${fullPath}';
        function findTheOne(){
            if($("textarea").val()==""){
                alert("一点想法也没有？");
                return;
            }
            $("#formMind").ajaxForm();
            $("form").submit();
        }

    </script>
</head>
<body>
<div align="center">
    <form id="formMind" action="${basePath}/mindPost.do" method="post" >
        <input type="hidden" name="uid" value="${uid}"/>
        <textarea name="mindContent" placeholder="写下你那牛逼的想法吧！"></textarea>
        <br>

        <a href="javascript:void(0);" onclick="findTheOne()">寻找和我一样牛逼的人</a>
        <br>
        我的uid是：${uid}
    </form>
</div>


<div id="message"></div>
</body>
</html>