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
<script type="text/javascript" src="<%=path%>/static/js/player/sewise.player.min.js"></script>
<div id="player" style="width: 100%; height: auto;">
		<script type="text/javascript">
			SewisePlayer.setup({
				server: "vod",
				type: "m3u8",
				autostart: "true",
				poster: "http://jackzhang1204.github.io/materials/poster.png",
				videourl: "${video.url}",
		        skin: "vodWhite",
				title: "${video.title}",
		        claritybutton: "disable",
		        lang: "zh_CN"
			}, "player");
		</script>

	</div>


	


