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
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>买家姓名</th>
                <th>买家地址</th>
                <th>买家电话</th>
                <th>状态</th>
                <th>项目名</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${getOrdersVO.list}" var="getOrdersVO">
                <tr>
                    <td align="center">${getOrdersVO.user.userName}</td>
                    <td align="center">${getOrdersVO.user.address}</td>
                    <td align="center">${getOrdersVO.user.telephone}</td>
                    <c:if test="${getOrdersVO.ordered.orderStatus == '0'}">
                        <td align="center">未接单</td>
                    </c:if>
                    <c:if test="${getOrdersVO.ordered.orderStatus == '1'}">
                        <td align="center">已接单</td>
                    </c:if>
                    <c:if test="${getOrdersVO.ordered.orderStatus == '2'}">
                        <td align="center">施工中</td>
                    </c:if>
                    <c:if test="${getOrdersVO.ordered.orderStatus == '3'}">
                        <td align="center">完成</td>
                    </c:if>
                    <c:if test="${getOrdersVO.ordered.orderStatus == '4'}">
                        <td align="center">商家拒绝</td>
                    </c:if>
                    <td align="center">${getOrdersVO.item.title}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: eval('${sessionScope.menuList}'),
    });
</script>
</body>
</html>