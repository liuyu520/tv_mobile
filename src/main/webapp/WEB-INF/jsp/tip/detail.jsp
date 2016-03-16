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
<!-- <h5 class="line">
	<span>论坛详情</span>
</h5> -->
<div class="" style="margin-bottom: 10px;">
	<h4>${news.title }</h4>
	<div
		style="padding-bottom: 20px; margin-bottom: 5px; padding-top: 5px; border-bottom: 1px solid #dbdbdb;">
		<h4 class="" style="float: left">${news.contacts }</h4>
		<span style="float: right">${news.releaseTimeStr }</span>

	</div>

	<article style="color: #252525">
		<img src="${news.pic }" onclick="window.open(this.src,'target')" style="max-width: 100%;">
		<br>
		${news.content }</article>
</div>
<h5 class="line">
	<span>评论</span>
	<div class="navbar" >
     <a style="float:right" class="aHref"  onclick="tip.addTipComment(${news.id });" >我要评论</a>
     </div>
</h5>
<!-- 评论 -->
<div id="tip_reply_list">
	<jsp:include page="./reply.jsp" />
</div>
<!-- /评论 -->