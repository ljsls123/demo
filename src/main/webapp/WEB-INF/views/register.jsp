<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>注册</title>
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
            <h1 style="padding-left: 8px;">注册</h1>
        </div>
        <form class="form-horizontal" style="padding: 1.875rem;" name="register" id="register">
            <div class="form-group">
                <label for="email" class="col-md-3 control-label">邮箱</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="email" placeholder="邮箱" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="password" placeholder="密码" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="password2" class="col-md-3 control-label">确认密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="password2" placeholder="确认密码" name="password2">
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">姓名</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="name" placeholder="姓名" name="name">
                </div>
            </div>
            <div class="form-group">
                <label for="gender" class="col-md-3 control-label">性别</label>
                <div class="col-md-8">
                    <select class="form-control" id="gender" name="gender">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="nickname" class="col-md-3 control-label">昵称</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="nickname" placeholder="昵称" name="nickname">
                </div>
            </div>
            <div class="form-group">
                <label for="telephone" class="col-md-3 control-label">电话</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="telephone" placeholder="电话" name="telephone">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-md-3 control-label">地址</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" id="address" placeholder="地址" name="address">
                </div>
            </div>
            <div class="form-group">
                <label for="type" class="col-md-3 control-label">类型</label>
                <div class="col-md-8">
                    <select class="form-control" id="type" name="type">
                        <option value="1">用户</option>
                        <option value="2">装修工</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-5 col-md-offset-5">
                    <button type="button" class="btn btn-default" id="button">注册</button>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 0px;">
                <div class="col-md-5 col-md-offset-7">
                    <label>已经有账号？
                        <a href="${pageContext.request.contextPath}/user/login">登录</a>
                    </label>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#button").click(function () {
            const password = c_password_md5($("#password").val());
            const type = $("#type").val();
            if ($("#register").valid()) {
                $.ajax({
                    url: "/user/register",
                    type: "post",
                    datatype: "json",
                    data: {
                        nickname: $("#nickname").val(),
                        address: $("#address").val(),
                        email: $("#email").val(),
                        name: $("#name").val(),
                        gender: $("#gender").val(),
                        telephone: $("#telephone").val(),
                        password: password,
                        type: type
                    },
                    success: function (data) {
                        if (data.code === 0) {
                            window.location.href = "/user/login"
                        } else {
                            alert(data.msg)
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR.responseText);
                        console.log(textStatus);
                        console.log(errorThrown);
                    },
                })
            }
        })
    })
</script>

<%--//jquery validate参数校验--%>
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
                    required: true,
                    telephone: true
                },
                email: {
                    required: true,
                },
                password: {
                    required: true,
                    rangelength: [8, 16]
                },
                password2: {
                    required: true,
                    equalTo: $("#password")
                },
                nickname: {
                    required: true
                },
                address: {
                    required: true
                },
                type: {
                    required: true
                }
            },
            messages: {
                telephone: {
                    required: "请输入账号",
                    telephone: "格式错误"
                },
                email: {
                    required: "请输入账号",
                },
                password: {
                    required: "请输入密码",
                    rangelength: "长度不正确"
                },
                password2: {
                    required: "请确认密码",
                    equalTo: "两次密码不一致"
                },
                nickname: {
                    required: "请输入姓名"
                },
                address: {
                    required: "请输入地址"
                }
            }
        })
    })
</script>
</body>
</html>
