<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../../js/messages_zh.js"></script>
    <script type="text/javascript" src="../../js/md5.min.js"></script>
    <script type="text/javascript" src="../../js/additional-methods.js"></script>
    <script type="text/javascript" src="../../js/common.js"></script>
    <style type="text/css">
        .login-style {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
        }

        .login-back {
            background-image: linear-gradient(to top, #d9afd9 0%, #97d9e1 100%);
            border-radius: 1.875rem;
        }

        body {
            background: url(../../img/110404-1521083044b19d.jpg) no-repeat center;
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background-size: cover;
            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-style col-md-3 col-md-offset-2 login-back" style="padding-top: 0.9375rem;">
        <div class="col-md-offset-5">
            <h1 style="padding-left: 8px;">登录</h1>
        </div>
        <form class="form-horizontal" style="padding: 1.875rem;" name="login" id="login">
            <div class="form-group" style="margin-bottom: 30px; margin-top: 10px;">
                <label for="email" class="col-md-3 control-label">邮箱</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="email" placeholder="邮箱" name="email">
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px;">
                <label for="password" class="col-md-3 control-label">密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="password" placeholder="密码" name="password">
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px;">
                <label for="type" class="col-md-3 control-label">账号类型</label>
                <div class="col-md-8">
                    <select class="form-control" id="type" name="type">
                        <option value="1">用户登录</option>
                        <option value="2">工人登录</option>
                        <option value="3">管理员登录</option>
                    </select>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 30px;">
                <div class="col-md-12 col-md-offset-1">
                    <div class="col-md-4 col-md-offset-3" style="padding-top: 7px;">
                        <label>
                            <a href="/user/register">忘记密码</a>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-10 col-md-offset-5">
                    <button type="button" class="btn btn-default" id="button">登录</button>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 0px;">
                <div class="col-md-5 col-md-offset-7">
                    <label>还没有账号？
                        <a href="/user/register">注册</a>
                    </label>
                </div>
            </div>
        </form>
    </div>
</div>
<%--//ajax提交--%>
<script type="application/javascript">
    $(function () {
        $("#button").click(
            function () {
                //判断身份
                var type = $("#type").val();
                var passwordstr = $("#password").val();
                var password = c_password_md5($("#password").val());
                // ajax提交
                if ($("#login").valid()) {
                    $.ajax({
                        url: "/user/login",
                        type: "post",
                        dataType: "json",
                        data: {
                            email: $("#email").val(),
                            password: password,
                            type: type
                        },
                        success: function (data) {
                            if (data.code === 0) {
                                alert("成功");
                            } else {
                                alert(data.msg);
                            }

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR.responseText);
                            console.log(textStatus);
                            console.log(errorThrown);
                        },
                    });
                }

            }
        );
    });
</script>
<%--//jquery validate参数校验--%>
<script type="text/javascript">
    $(function () {
        $("#login").validate({
            debug: true,
            focusCleanup: true,
            onkeyup: false,
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                email: {
                    required: true,
                },
                password: {
                    required: true,
                    rangelength: [8, 64]
                },
                type: {
                    required: true
                }
            },
            messages: {
                email: {
                    required: "请输入账号",
                },
                password: {
                    required: "请输入密码",
                    rangelength: "长度不正确"
                },
            }
        })
    })
</script>
</body>
</html>