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
        <div><span>价格筛选   </span>
            <select id="select" onchange="window.location=this.value">
                <option>请选择</option>
                <option value="/user/searchItems/price?page=1&price=100">100以下</option>
                <option value="/user/searchItems/price?page=1&price=500">500以下</option>
                <option value="/user/searchItems/price?page=1&price=1000">1000以下</option>
            </select>
            <div>
                <form action="/user/searchItems/type" method="get">
                    <span>类型筛选   </span><input id="type" name="type"/>
                    <input type="hidden" id="page" name="page" value="1"/>
                    <button type="submit">筛选</button>
                </form>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>标题</th>
                <th>类型</th>
                <th>简介</th>
                <th>价格</th>
                <th>商家昵称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${searchItemVO.list}" var="searchItem">
                <tr>
                    <td align="center">${searchItem.item.title}</td>
                    <td align="center">${searchItem.item.type}</td>
                    <td align="center">${searchItem.item.description}</td>
                    <td align="center">${searchItem.item.price}</td>
                    <td align="center">${searchItem.nickName}</td>
                    <td align="center">
                        <button onclick="buy(${searchItem.item.id})">购买</button>
                        <button onclick="detail(${searchItem.item.id})">查看详情</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page == 1}">
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${page != 1}">
                    <li>
                        <a href="/user/searchItems/price?page=${page-1}&price=${price}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${searchItemVO.totalPage != page}">
                    <li>
                        <a href="/user/searchItems/price?page=${page+1}&price=${price}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${searchItemVO.totalPage == page}">
                    <li class="disabled">
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
<script>
    $("#menu").sidemenu({
        data: userMenuData,
    });
</script>
<script type="application/javascript">
    function buy(id) {
        $.ajax({
            url: "/user/buy?itemId=" + id,
            type: "get",
            datatype: "json",
            data: {},
            success: function (data) {
                if (data.code === 0) {
                    window.location.reload()
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

    function detail(id) {
        window.location.href = "/item/userDetail?id=" + id;
    }
</script>
</body>
</html>