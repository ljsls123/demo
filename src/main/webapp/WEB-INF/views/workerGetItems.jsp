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
        <a href="${pageContext.request.contextPath}/worker/main"><span>hello, ${sessionScope.user.nickname}</span></a>
    </div>

    <div class="header-menu">
        <span style="font-size:19px">?????????????????????</span>
    </div>
</div>

<div id="menu" class="main-sidebar"></div>

<div class="main">
    <div id="mainframe" style="width:100%;height:100%;text-align: center">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>??????</th>
                <th>??????</th>
                <th>??????</th>
                <th>??????</th>
                <th>????????????</th>
                <th>??????</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${getItemVO.list}" var="getItemVO">
                <tr>
                    <td align="center">${getItemVO.title}</td>
                    <td align="center">${getItemVO.type}</td>
                    <td align="center">${getItemVO.description}</td>
                    <td align="center">${getItemVO.price}</td>
                    <c:if test="${getItemVO.online == '1'}">
                        <td align="center">?????????</td>
                        <td align="center">
                            <button onclick="offline(${getItemVO.id})">??????</button>
                            <button><a href="/item/workerDetail?id=${getItemVO.id}"/> ????????????</button>
                        </td>
                    </c:if>
                    <c:if test="${getItemVO.online != '1'}">
                        <td align="center">?????????</td>
                        <td align="center">
                            <button onclick="online(${getItemVO.id})">??????</button>
                            <button><a href="/item/workerDetail?id=${getItemVO.id}"/> ????????????</button>
                        </td>
                    </c:if>
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
                        <a href="/worker/getItems?page=${page-1}}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${getItemVO.totalPage != page}">
                    <li>
                        <a href="/worker/getItems?page=${page+1}}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${getItemVO.totalPage == page}">
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
        data: eval('${sessionScope.menuList}'),
    });
</script>
<script type="application/javascript">
    function online(id) {
        $.ajax({
            url: "/worker/item/online",
            type: "post",
            datatype: "json",
            data: {
                id: id
            },
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

    function offline(id) {
        $.ajax({
            url: "/worker/item/offline",
            type: "post",
            datatype: "json",
            data: {
                id: id
            },
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
</script>
</body>
</html>