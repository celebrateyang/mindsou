<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.11.2.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/resources/css/layoutit.css">
    <link href="${basePath}/resources/css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/minda.js?r=${mathRandom}"></script>
    <script type="text/javascript" src="${basePath}/resources/js/jquery.form-3.09.js"></script>
    <script type="text/javascript">
        var fullPath = '${fullPath}';
        function findTheOne(){
            if($("textarea").val()==""){
                alert("一点想法也没有？");
                return;
            }
            $("#message").html("");
            $("#formMind").ajaxForm();
            $("form").submit();
        }

    </script>
</head>
<body>
<%--<div align="center">
    <form id="formMind" action="${basePath}/mindPost.do" method="post" >
        <input type="hidden" name="uid" value="${uid}"/>
        <textarea name="mindContent" placeholder="写下你那牛逼的想法吧！"></textarea>
        <br>

        <a href="javascript:void(0);" onclick="findTheOne()">寻找和我一样牛逼的人</a>
        <br>
        我的uid是：${uid}
    </form>
</div>


<div id="message"></div>--%>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span8">
            <form id="formMind" action="${basePath}/mindPost.do" method="post">
                <input type="hidden" name="uid" value="${uid}"/>
                <div align="center" class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">你此时的想法是：</span>
                      <textarea class="form-control" rows="3" name="mindContent" placeholder="写下你那牛逼的想法吧！"></textarea>
                    </div>
                </div>
                    <div align="center" class="form-group">
                    <input type="button" class="btn btn-success active btn-large" onclick="findTheOne()" value="提交" />
                    </div>

            </form>
            <div class="accordion" id="message">

            </div>
        </div>
        <div class="span4">
            <div class="alert">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    哇塞!
                </h4> <p class="bg-danger">这么奇葩的想法竟然也有人和你想得一样。</p>.
            </div>
            <div class="alert">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    建议!
                </h4> 不如开个临时聊天室一探究竟。
            </div>
        </div>
    </div>
</div>
</body>
</html>