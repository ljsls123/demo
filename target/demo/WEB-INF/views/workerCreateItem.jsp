<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
    <title></title>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../js/messages_zh.js"></script>
    <script type="text/javascript" src="../../js/md5.min.js"></script>
    <script type="text/javascript" src="../../js/additional-methods.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <script type="text/javascript" src="../../js/menudata.js"></script>
    <script type="text/javascript" src="../../js/sidemenu.js"></script>
    <link href="../../css/font-awesome.css" rel="stylesheet"/>
    <link href="../../css/skin.css" rel="stylesheet"/>
    <link href="../../css/sidemenu.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <style>
        .main {
            background: #fff;
            position: absolute;
            float: left;
            right: 0;
            top: 100px;
            bottom: 0;
            width: 85%;
            transition-duration: 0.3s;
            transition-timing-function: ease;
            -webkit-transition-duration: 0.3s;
            -webkit-transition-timing-function: ease;
        }

        .collapse .main {
            left: 50px;
            transition-duration: 0.3s;
            transition-timing-function: ease;
            -webkit-transition-duration: 0.3s;
            -webkit-transition-timing-function: ease;
        }

        .mymenu .mini-menu-float {
            display: inline-block;
            float: right;
        }
    </style>
</head>
<body>

<div class="header">

    <div class="logo">
        <a href="${pageContext.request.contextPath}/worker/main"><span>hello, ${sessionScope.user.nickname}</span></a>
    </div>

    <div class="header-menu">
        <span style="font-size:19px">XX装修中介</span>
    </div>
</div>

<div id="menu" class="main-sidebar"></div>

<div class="main">
    <div id="mainframe" style="width:100%;height:100%;text-align: center">
        <div class="col-md-3 col-md-offset-4"
             style="padding-top: 0.9375rem;position: relative;top: 50%;transform: translateY(-50%);">
            <div class="col-md-offset-1">
                <h1 style="padding-left: 8px;">新建项目</h1>
            </div>
            <form class="form-horizontal" style="padding: 1.875rem;" name="register" id="register"
                  enctype="multipart/form-data" action="${pageContext.request.contextPath}/worker/createItem"
                  method="post">
                <div class="form-group">
                    <label for="title" class="col-md-4 control-label">标题</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="title" placeholder="标题" name="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="type" class="col-md-4 control-label">类型</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="type" placeholder="类型"
                               name="type">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-md-4 control-label">介绍</label>
                    <div class="col-md-8">
                        <textarea type="password" class="form-control" id="description" placeholder="介绍"
                                  name="description"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-md-4 control-label">价格</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="price" placeholder="价格"
                               name="price">
                    </div>
                </div>
                <div class="form-group">
                    <label for="img" class="col-md-4 control-label">图片</label>
                    <div class="col-md-8">
                        <input type="file" class="form-control" id="img" placeholder="图片"
                               name="img">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button type="submit" class="btn btn-default" id="button">新建</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: workerMenuData,
    });

    // $(function () {
    //     $("#button").click(function () {
    //         if ($("#register").valid()) {
    //             var formData = new FormData($("#register")[0]);
    //             $.ajax({
    //                 url: "/worker/createItem",
    //                 type: "post",
    //                 datatype: "json",
    //                 contentType: false,
    //                 processData: false,
    //                 async: false,
    //                 data: formData,
    //                 success: function (data) {
    //                     if (data.code === 0) {
    //                         window.location.href = '/worker/main'
    //                         alert("新建成功")
    //                     } else {
    //                         alert(data.msg)
    //                     }
    //                 },
    //                 error: function (jqXHR, textStatus, errorThrown) {
    //                     console.log(jqXHR.responseText);
    //                     console.log(textStatus);
    //                     console.log(errorThrown);
    //                 },
    //             })
    //         }
    //     })
    // })
</script>
</body>
</html>