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
<title>监利县广播电视台</title>
<meta name="keywords"
	content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">

<!-- 点播详情-->
<div  style="margin-top: 10px;margin-bottom:10px;" >
	<!-- <h5 class="line">
		<span>点播详情</span>
		<div class="navbar">
			<a id="next2" class="next" href="#"><span></span></a> <a id="prev2"
				class="prev" href="#"><span></span></a>
		</div>
	</h5> -->
	<div style="border-bottom: 1px solid #f38399;padding-bottom: 20px">
		<h5>${video.title }</h5>
		<span style="float: right" >发表时间:${video.releaseTimeStr }</span>

	</div>
	<p><div style="text-align: center;" >
	<a href="<%=path%>/video/${video.id }?targetView=video/broadcast_flv"><img src="${video.titlePic }" title="点击开始播放"
																			   style="max-width: 100%;"></a></div>
	${video.content }</p>
</div>
<script type="text/javascript"
		src="<%=path%>/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/tv.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
<!-- /点播详情 -->
<%--<h5 class="line">
	<span>评论</span>
	<div class="navbar" >
     <a style="float:right" class="aHref"  onclick="news.addNewsComment(${news.id });" >我要评论</a>
     </div>
</h5>--%>
<!-- 评论 -->
<%--<div id="news_reply_list">
	<jsp:include page="./reply.jsp" />
</div>--%>
<!-- /评论 -->