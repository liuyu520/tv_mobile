<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>商品详情</title>
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" media="all" href="<%=path%>/static/css/global.css"></link>

	<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
</head>
<body>
<div>
<input type="button" value="返回" onclick="closeDetailPanel();" >
<table class="detail"  >
<tr><td class="theader"  style="min-width: 110px;" >ID</td><td style="word-break:break-all;" > ${dictionary.id } </td></tr>

<tr><td class="theader" >groupid</td><td> ${dictionary.groupId } </td></tr>
<tr><td class="theader" >key</td><td>${dictionary.key2 } </td></tr>
<tr><td class="theader" >value</td><td style="word-break:break-all;" > ${dictionary.value } </td></tr>
<tr><td class="theader" >description</td><td> ${dictionary.description } </td></tr>


</table>
</div>
</body>
</html>