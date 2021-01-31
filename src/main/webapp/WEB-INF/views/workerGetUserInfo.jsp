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
        <a href="${pageContext.request.contextPath}/worker/main"><span>hello, ${sessionScope.user.nickname}</span></a>
    </div>

    <div class="header-menu">
        <span style="font-size:19px">XX装修中介</span>
    </div>
</div>

<div id="menu" class="main-sidebar"></div>

<div class="main">
    <div id="mainframe" style="width:100%;height:100%;text-align: center">
        <div class="col-md-5 col-md-offset-3" style="position: absolute;top: 50%;transform: translateY(-50%);">
            <div><span class="col-md-2">邮箱</span>
                <div style="display: inline">${userInfoVo.email}</div>
            </div>
            <div><span class="col-md-2">电话</span>
                <div style="display: inline">${userInfoVo.telephone}</div>
            </div>
            <div><span class="col-md-2">姓名</span>
                <div style="display: inline">${userInfoVo.userName}</div>
            </div>
            <div><span class="col-md-2">性别</span>
                <div style="display: inline">${userInfoVo.gender}</div>
            </div>
            <div><span class="col-md-2">昵称</span>
                <div style="display: inline">${userInfoVo.nickName}</div>
            </div>
            <div><span class="col-md-2">地址</span>
                <div style="display: inline">${userInfoVo.address}</div>
            </div>
            <div><span class="col-md-2">类型</span>
                <div style="display: inline">${userInfoVo.userType}</div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: workerMenuData,
    });
</script>
</body>
</html>