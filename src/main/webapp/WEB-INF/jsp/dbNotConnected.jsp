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
	<title>正在处理中...</title>
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<meta HTTP-EQUIV="REFRESH" content="0; url=<%=path%>/">
	<link rel="shortcut icon" href="<%=path%>/static/img/sms-4.ico">

</head>
<body>

<div class="errorPage" style="display:none;" >
	<img style="max-width: 60px" src="<%=path%>/static/img/error.gif">
连接数据库失败!<br>
</div>

</body>
</html>