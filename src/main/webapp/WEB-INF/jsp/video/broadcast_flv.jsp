<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<script type="text/javascript"
		src="<%=path%>/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/player/sewise.player.min.js"></script>
	<script type="text/javascript" >
		$(function(){
			var intervalHook=null;
			var $container_video=$('#container_video');
			function listenLoadOver(){
				//$container_video_div=$('')
				if($( "#container_video:has(object)" ).length!=0){
					$container_video.find("h4").hide();
					clearInterval(intervalHook);
				}
			}
			intervalHook=setInterval(listenLoadOver, 500);
		});
	</script>
	<!-- 播放点播的视频(视频格式:flv) -->
<div id="container_video" style="width: 100%; height: auto; ">
<h4>正在加载...</h4>
		<script type="text/javascript">
			SewisePlayer.setup({
				server: "vod",
				type: "flv",
				videourl: "${video.path}",
				poster: "http://jackzhang1204.github.io/materials/poster.png",
		        skin: "vodWhite",
		        title: "${video.title}",
				lang: 'zh_CN',
				claritybutton: 'enable'
			}, "container_video");
		</script>
	</div>

