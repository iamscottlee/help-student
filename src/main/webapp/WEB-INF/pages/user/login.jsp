<%--
  Created by IntelliJ IDEA.
  User: Scott Lee
  Date: 2018/5/14
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link href="/js/bootstrap.min.js" rel="stylesheet">--%>
    <%--<script src="webjars/jquery/3.1.1/jquery.min.js"></script>--%>
    <%--<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
    <%--<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css" />--%>

    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <%--<style type="text/css">--%>

    <%--body {background-color: #EAFFF0}--%>
    <%--/*#C1FFC1  #CAE1FF*/--%>
    <%--/*h2 {background-color: #5cb85c}*/--%>

    <%--</style>--%>

    <title>登录</title>
</head>
<body>
<div class="jumbotron ">
    <div class="container" align="center">
        <h2 class="text-info" style="font-weight:bold;font-size:49px;">贫困生认定系统</h2>
    </div>
</div>
<div class="container">
    <h1>用户登录</h1>
    <hr/>
    <form:form action="/user/loginValidate" method="post" role="form">
        <%--commandName="user"--%>
        <div class="col-xs-4">
            <div class="form-group">
                <label for="username">username:</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名："/>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码："/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success" title="登录">登录</button>
                <button href="register.jsp" class="btn btn-sm btn-primary">注册</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
