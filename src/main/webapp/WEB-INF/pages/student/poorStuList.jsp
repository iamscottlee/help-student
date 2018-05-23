<%--
  Created by IntelliJ IDEA.
  User: Scott Lee
  Date: 2018/5/20
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>贫困生认定系统</title>
    <script src="/js/jquery-1.12.3.min.js"></script>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/jquery.bootgrid.min.css">
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="/js/jquery.bootgrid.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-datetimepicker.min.css" type="text/css">

</head>
<body>
<%--1--%>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar">**</span>
                        </button>
                        <a class="navbar-brand" href="/stu/student/stuList">贫困生认定系统</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="/user/logout">logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>贫困生筛选结果</h2>
            <a class="btn btn-primary" href="/data/exportPoorStu">导出数据为excel</a>
            <a class="btn btn-success" href="/stu/student/stuList">返回学生信息表</a>
            <table id="grid-data" class="table table-condensed table-hover table-striped">
                <thead>
                <tr>
                    <th data-column-id="stuNum">学号</th>
                    <th data-column-id="stuName">姓名</th>
                    <th data-column-id="stuMajor">专业</th>
                    <th data-column-id="finalScore" data-order="desc">最后得分</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<%--2--%>
<script>
    $(document).ready(function () {
        var grid = $("#grid-data").bootgrid({
            ajax: true,
            post: function () {
                return {
                    id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
                };
            },
            url: "/data/poorstu",
            formatters: {
                "commands": function (column, row) {
                }
            }
        });
    });


</script>


</body>
</html>
