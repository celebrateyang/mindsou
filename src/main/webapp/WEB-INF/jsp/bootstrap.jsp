<%--
  Created by IntelliJ IDEA.
  User: 竹庆
  Date: 2016/9/20
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.11.2.min.js"></script>
    <link href="${basePath}/resources/css/bootstrap-combined.min.css" rel="stylesheet">
    <link href="${basePath}/resources/css/layoutit.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span8">
            <form>
                <fieldset>
                    <label>你此时的想法是：</label>
                    <textarea class="form-control" rows="3"></textarea>
                    <button type="submit" class="btn">提交</button>
                </fieldset>
            </form>
            <div class="accordion" id="accordion-915054">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-915054" href="#accordion-element-727249">打开看一下</a>
                    </div>
                    <div id="accordion-element-727249" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            功能块...
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-915054" href="#accordion-element-477321">打开看一下</a>
                    </div>
                    <div id="accordion-element-477321" class="accordion-body collapse">
                        <div class="accordion-inner">
                            功能块...
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="span4">
            <div class="alert">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>
                    哇塞!
                </h4> 这么奇葩的想法竟然也有人和你想得一样。.
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
