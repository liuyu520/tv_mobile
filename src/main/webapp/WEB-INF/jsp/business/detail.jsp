<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
	content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">

<!-- 新闻详情-->
<div class="column">
	<h5 class="line">
		<span>新闻详情</span>
		<div class="navbar">
			<a id="next2" class="next" href="#"><span></span></a> <a id="prev2"
				class="prev" href="#"><span></span></a>
		</div>
	</h5>
	<div style="border-bottom: 1px solid #f38399;padding-bottom: 20px">
		<h5>${news.title }</h5>
		<span style="float: right" >发表时间:${news.releaseTimeStr }</span>

	</div>
	<p><img src="${news.pic }"  style="max-width: 100%;" ><br>
	${news.content }</p>
</div>

<!-- /新闻详情-->