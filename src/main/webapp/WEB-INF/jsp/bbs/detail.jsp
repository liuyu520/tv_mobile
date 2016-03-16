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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>监利县广播电视台</title>
<meta name="keywords"
	content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
	<link rel="shortcut icon" href="../favicon.ico">
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
		  href="<%=path%>/static/css/base.css"/>
	<link rel="stylesheet" type="text/css"
		  href="<%=path%>/static/css/style.css"/>
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
	<link rel="stylesheet" href="<%=path%>/static/css/qinli/common.css">
	<script type="text/javascript"
			src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/tv.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
</head>
<body>
<jsp:include page="../qinli/top_common.jsp">
	<jsp:param name="showLogo" value="false"/>
</jsp:include>
<div class="part1" style="background-color: #fff;height: auto; padding: 5px;">
	<div class="" style="margin-bottom: 10px;">
		<h5>【${bbs.typeStr }】${bbs.title }<a title="刷新"
											 onclick="bbs.ajaxBBSDetailOnDialog('<%=path%>/bbs/${bbs.id }?1=1',this,${bbs.id });"
											 style="margin-top: 0;margin-right: 4px;  " class="refresh"></a></h5><a
			name="title_loc_bbs"></a>

		<div
				style="padding-bottom: 25px; margin-bottom: 5px; padding-top: 5px; border-bottom: 1px solid #dbdbdb;">
			<div class="" style="float: left;color: #000;">${bbs.releaseName }</div>
			<span style="float: right" class="little_gray">${bbs.releaseTimeStr }</span>

		</div>

		<article style="color: #252525">${bbs.cardcontent }</article>
	</div>
	<div>
		<div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone"
																						  data-cmd="qzone"></a><a
				href="#" class="bds_tsina" data-cmd="tsina"></a><a href="#" class="bds_tqq" data-cmd="tqq"></a><a
				href="#" class="bds_renren" data-cmd="renren"></a><a href="#" class="bds_weixin" data-cmd="weixin"></a>
		</div>
		<script>window._bd_share_config = {
			"common": {
				"bdSnsKey": {},
				"bdText": "${fn:substring(news.splitAndFilterString,0,100) }",
				"bdMini": "2",
				"bdPic": "${news.pic }",
				"bdStyle": "0",
				"bdSize": "16"
			},
			"share": {},
			"image": {"viewList": ["qzone", "tsina", "tqq", "renren", "weixin"], "viewText": "分享到：", "viewSize": "16"},
			"selectShare": {"bdContainerClass": null, "bdSelectMiniList": ["qzone", "tsina", "tqq", "renren", "weixin"]}
		};
		with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
	</div>
	<h5 class="line2">
		<span>评论</span>

		<div class="navbar">
			<a style="float:right" class="aHref" onclick="bbs.addBBSComment(${bbs.id });">我要评论</a>
		</div>
	</h5>
	<!-- 评论 -->
	<div id="bbs_reply_list">
		<jsp:include page="./reply.jsp"/>
	</div>
	<!-- /评论 -->


</div>
<jsp:include page="../qinli/footer.jsp"/>
<script type="text/javascript" src="<%=path%>/static/js/qinli/index.js"></script>
</body>