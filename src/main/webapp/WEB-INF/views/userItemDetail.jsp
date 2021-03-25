<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <div style="margin-top: 30px;margin-bottom: 30px"><span>标题:</span>
            <div style="display: inline">${item.title}</div>
        </div>
        <div style="margin-top: 30px;margin-bottom: 30px"><span>类型:</span>
            <div style="display: inline">${item.type}</div>
        </div>
        <div style="margin-top: 30px;margin-bottom: 30px"><span>介绍:</span>
            <div style="display: inline">${item.description}</div>
        </div>
        <div style="margin-top: 30px;margin-bottom: 30px"><span>价格:</span>
            <div style="display: inline">${item.price}</div>
        </div>
        <div style="margin-top: 30px;margin-bottom: 30px"><span>示例图:</span><img src="${item.img}" alt=""/>
        </div>
        <div style="margin-top: 30px;margin-bottom: 30px">
            <div style="display: inline"><span>用户评论：</span></div>
            <c:if test="${list.size() != 0}">
                <c:forEach items="${list}" var="comment">
                    <div style="display: inline"><span>${comment.nickName}</span><span>${comment.detail}</span></div>
                </c:forEach>
            </c:if>
            <c:if test="${list.size() == 0}">
                <div style="display: inline"><span>无</span></div>
            </c:if>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}/user/comment" method="get">
                <span>评论：   </span>
                <textarea id="detail" name="detail"></textarea>
                <input type="hidden" id="itemId" name="itemId" value="${item.id}"/>
                <button type="submit">提交</button>
            </form>
        </div>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: userMenuData,
    });
</script>
</body>
</html>