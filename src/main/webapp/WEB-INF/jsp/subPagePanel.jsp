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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<meta name="keywords"
      content="transition, off-canvas, navigation, effect, 3d, css3, smooth"/>
<link rel="shortcut icon" mce_href="favicon.ico" href="/static/img/sms-4.ico">

<!-- 遮罩层 -->
<div id="loadPanel" style="background-attachment: fixed;background-repeat: no-repeat;background-position: center;position: absolute;z-index: 99999;width: 100%;"></div>
<!-- / 遮罩层 -->

<!-- 弹出窗口层 -->
<div id="subPagePanel" style="display:none;" class="subPagePanel shadow">
    <h2 style="color: #fff;font-weight: bold;" class="ui-icon-close"><label>发帖</label>
        <a title="关闭" onclick="closeSubPagePanel();" style="margin-top: 4px;margin-right: 4px;  " class="close"></a>
    </h2>

    <div class="subContent">
        <!-- <img style="margin:500px;width:50px" src="<%=path%>/static/images/loading/progress.gif"> -->


    </div>

</div>
<!-- / 弹出窗口层 -->