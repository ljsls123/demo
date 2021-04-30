<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
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
        <a href="/user/main"><span>hello, ${sessionScope.user.nickname}</span></a>
    </div>

    <div class="header-menu">
        <span style="font-size:19px">向月航装修中介</span>
    </div>
</div>

<div id="menu" class="main-sidebar"></div>

<div class="main">
    <div id="mainframe" style="width:100%;height:100%;text-align: center">
        <div class="col-md-3 col-md-offset-4"
             style="padding-top: 0.9375rem;position: relative;top: 50%;transform: translateY(-50%);">
            <div class="col-md-offset-1">
                <h1 style="padding-left: 8px;">修改信息</h1>
            </div>
            <form class="form-horizontal" style="padding: 1.875rem;" name="register" id="register">
                <div class="form-group">
                    <label for="telephone" class="col-md-4 control-label">电话</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="telephone" placeholder="电话" name="telephone">
                    </div>
                </div>
                <div class="form-group">
                    <label for="nickname" class="col-md-4 control-label">昵称</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="nickname" placeholder="昵称" name="nickname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-md-4 control-label">地址</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="address" placeholder="地址"
                               name="address">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button type="button" class="btn btn-default" id="button">修改</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: eval('${sessionScope.menuList}'),
    });

    $(function () {
        $("#button").click(function () {
            if ($("#register").valid()) {
                $.ajax({
                    url: "/user/updateUserInfo",
                    type: "post",
                    datatype: "json",
                    data: {
                        telephone: $("#telephone").val(),
                        address: $("#address").val(),
                        nickname: $("#nickname").val()
                    },
                    success: function (data) {
                        if (data.code === 0) {
                            window.location.href = "/user/main"
                            alert("修改成功")
                        } else {
                            alert(data.msg)
                        }
                    }
                    ,
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR.responseText);
                        console.log(textStatus);
                        console.log(errorThrown);
                    }
                    ,
                })
            }
        })
    })
</script>
<script type="text/javascript">
    $(function () {
        // 手机号校验
        jQuery.validator.addMethod("telephone", function (value, element) {
            var length = value.length;
            var mobile = /^[1]\d{2}\d{8}$/
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
        $("#register").validate({
            debug: true,
            focusCleanup: true,
            onkeyup: false,
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                telephone: {
                    telephone: true
                },
            },
            messages: {
                telephone: {
                    telephone: "格式错误"
                }
            }
        })
    })
</script>
</body>
</html>