<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/16
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <!-- STYLES -->
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/superfish.css" media="screen"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/fontello/fontello.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/flexslider.css" media="screen"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/ui.css"/>

    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/960.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/1000.css"
          media="only screen and (min-width: 768px) and (max-width: 1000px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/767.css"
          media="only screen and (min-width: 480px) and (max-width: 767px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/479.css"
          media="only screen and (min-width: 200px) and (max-width: 479px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/dropdown.css"/>
    <style>
        input[type=submit] {
            padding: 10px 30px;
            border: 0 none;
            background-color: #05a1e7;
            font-size: 30px;
            color: #ffffff;
        }
    </style>
</head>
<body>
<!-- 点播列表 -->
<div class="container" style="margin-left: 0;margin-right: 0" id="broadcast_list">

    <div>
        <form action="./add" method="post" onsubmit="return checkForm2()">
            <textarea name="info" id="info" cols="40" rows="5"></textarea>

            <div style="margin-top: 10px">
                <input type="submit" value="提交"/>
            </div>

        </form>
    </div>
    <div id="broadcast_list_right">
        <jsp:include page="./list_common.jsp"/>
    </div>

</div>
<!-- /点播列表 -->
</div>
<script type="text/javascript"
        src="<%=path%>/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
<script>
    var checkForm2 = function () {
        var $info = $('textarea[name=info]');
        var val = $info.val();
        if (val) {
            return true;
        } else {
            alert('请输入内容');
            $info.get(0).focus();
            return false;
        }
    }
    $(function () {
        $('textarea[name=info]').get(0).focus();
        ;
    })
</script>
</body>
</html>
