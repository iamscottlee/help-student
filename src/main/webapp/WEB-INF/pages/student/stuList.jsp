<%--
  Created by IntelliJ IDEA.
  User: Scott Lee
  Date: 2018/5/15
  Time: 10:30
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
                        <button type="button" class="navbar-toggle collapsed"  data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar">**</span>
                        </button>
                        <a class="navbar-brand" href="/stu/stuList">贫困生认定系统</a>
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
            <h2>学生信息</h2>
            <a class="btn btn-primary" href="/stu/exportStu">导出数据为excel</a>
            <a class="btn btn-primary" href="#" id="add">添加学生</a>
            <a class="btn btn-success" href="/data/poorStuList">筛选贫困生</a>
            <%--<div class="btn-group">--%>
                <%--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                    <%--查看接口数据 <span class="caret"></span>--%>
                <%--</button>--%>
                <%--<ul class="dropdown-menu">--%>
                    <%--<li><a href="/stu/stulistxml?current=1&rowCount=10&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed" target="_blank">XML</a></li>--%>
                    <%--<li><a href="/stu/getStuInfo?stuId=1&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed" target="_blank">JSON</a></li>--%>
                <%--</ul>--%>
            <%--</div>--%>

            <table id="grid-data" class="table table-condensed table-hover table-striped">
                <thead>
                <tr>
                    <th data-column-id="stuId"  data-identifier="true" data-type="numeric">Id</th>
                    <th data-column-id="stuNum">学号</th>
                    <th data-column-id="stuName">姓名</th>
                    <th data-column-id="stuSex">性别</th>
                    <th data-column-id="stuAge">年龄</th>
                    <th data-column-id="stuMajor" data-order="desc">专业</th>
                    <th data-column-id="poorLevel">等级</th>
                    <th data-column-id="commands" data-formatter="commands" data-sortable="false">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<%--2--%>
<script>
    $(document).ready(function(){
        var grid = $("#grid-data").bootgrid({
            ajax:true,
            post: function ()
            {
                return {
                    id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
                };
            },
            url:"/stu/stuList",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-warning command-edit\"  data-row-id=\"" + row.stuId + "\">编辑<span class=\"fa fa-pencil\"></span></button> " +
                        "<button type=\"button\" class=\"btn btn-xs btn-danger command-delete\" data-row-id=\"" + row.stuId + "\">删除<span class=\"fa fa-trash-o\"></span></button>";
                }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
        {
            grid.find(".command-edit").on("click", function(e)
            {
                $(".stumodal").modal();
                $.post("/stu/getStuInfo",{stuId:$(this).data("row-id")},function(str){
                    $("#stuId2").val(str.stuId);
                    $("#stuNum2").val(str.stuNum);
                    $("#stuName2").val(str.stuName);
                    $("#stuSex2").val(str.stuSex);
                    $("#stuAge2").val(str.stuAge);
                    $("#stuMajor2").val(str.stuMajor);
                    $("#poorLevel2").val(str.poorLevel);
                });
            }).end().find(".command-delete").on("click", function(e)
            {
                alert("You pressed delete on row: " + $(this).data("row-id"));

                $.post("/stu/delStu",{stuId:$(this).data("row-id")},function(){
                    alert("删除成功");
                    $("#grid-data").bootgrid("reload");
                });
            });
        });
    });

    $(document).ready(function(){
        $("#add").click(function(){
            $(".addmodal").modal();
        });
    });

</script>

<%--3--%>
<%--编辑子窗口??--%>
<div class="modal fade stumodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑学生信息</h4>
            </div>
            <form action="/stu/updateStu" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="stuId2">stuId</label>
                        <input type="text" name="stuId" class="form-control" id="stuId2" readonly="true">
                    </div>
                    <div class="form-group">
                        <label for="stuNum2">stuNum</label>
                        <input type="text" name="stuNum" class="form-control" id="stuNum2">
                    </div>
                    <div class="form-group">
                        <label for="stuName2">stuName</label>
                        <input type="text" name="stuName" class="form-control" id="stuName2">
                    </div>
                    <div class="form-group">
                        <label for="stuSex2">stuSex</label>
                        <input type="text" name="stuSex" class="form-control" id="stuSex2">
                    </div>
                    <div class="form-group">
                        <label for="stuAge2">stuAge</label>
                        <input type="text" name="stuAge" class="form-control" id="stuAge2">
                    </div>
                    <div class="form-group">
                        <label for="stuMajor2">stuMajor</label>
                        <input type="text" name="stuMajor" class="form-control" id="stuMajor2">
                    </div>
                    <div class="form-group">
                        <label for="poorLevel2">poorLevel</label>
                        <input type="text" name="poorLevel" class="form-control" id="poorLevel2" readonly="true">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--4--%>
<%--增加子窗口--%>
<div class="modal fade addmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加学生</h4>
            </div>
            <form action="/stu/addStu" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="stuNum1">学号</label>
                        <input type="text" name="stuNum" class="form-control" id="stuNum1">
                    </div>
                    <div class="form-group">
                        <label for="stuName1">姓名</label>
                        <input type="text" name="stuName" class="form-control" id="stuName1">
                    </div>
                    <div class="form-group">
                        <label for="stuSex1">性别</label>
                        <input type="text" name="stuSex" class="form-control" id="stuSex1">
                    </div>
                    <div class="form-group">
                        <label for="stuAge1">年龄</label>
                        <input type="text" name="stuAge" class="form-control" id="stuAge1">
                    </div>
                    <div class="form-group">
                        <label for="stuMajor1">专业</label>
                        <input type="text" name="stuMajor" class="form-control" id="stuMajor1">
                    </div>
                    <div class="form-group">
                        <label for="poorLevel1">等级</label>
                        <input type="text" name="poorLevel" class="form-control" id="poorLevel1">
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="stuId" class="form-control" id="Id">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">增加</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
