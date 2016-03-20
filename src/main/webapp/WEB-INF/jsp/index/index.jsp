<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"   import="com.dict.Constant2" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1">
<title>监利县广播电视台</title>

<link rel="shortcut icon" href="<%=path%>/static/img/sms-4.ico" />

<!-- STYLES -->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/superfish.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/fontello/fontello.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/flexslider.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/960.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/devices/1000.css"
	media="only screen and (min-width: 768px) and (max-width: 1000px)" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/devices/767.css"
	media="only screen and (min-width: 480px) and (max-width: 767px)" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/devices/479.css"
	media="only screen and (min-width: 200px) and (max-width: 479px)" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/dropdown.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/register.css" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=path%>/static/js/customM.js"></script> <![endif]-->
	<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=path%>/static/js/html5.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/msgbox.css" />
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/grumble.css" />
<meta name="keywords"
	content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/wowSlider.css" media="screen"/>
<script type="text/javascript" >
		var LANG = 'zh', I18N_DELETE = '确认删除？';
           </script>
    <script type="text/javascript"
            src="<%=path%>/static/js/jquery-1.11.1.js"></script>

</head>
<body>

	<!-- Body Wrapper -->
	<div class="body-wrapper">
		<div class="controller">
			<div class="controller2">

				<!-- Header -->
				<header id="header">
					<div class="container">
						<div class="column">
							<%--<div class="logo" style="padding: 10px;background-color:#ea4748;margin-bottom:3px; " >
                                <a href="" style="color:#fff;font-weight:bold;font-size: 22px;">&lt;%&ndash; <img
									src="<%=path%>/static/img/logo.png" alt="监利县广播电视台" /> &ndash;%&gt;
									监利TV
									</a>
							</div>--%>
							<labe id="currentTime"></labe>
							<iframe name="sinaWeatherTool"
									src="http://weather.news.sina.com.cn/chajian/iframe/weatherStyle0.html?city=监利"
									width="200" height="20" marginwidth="0" marginheight="0" hspace="0" vspace="0"
									frameborder="0" scrolling="no"></iframe>
							<div class="login-register">
								<!-- <a href="#" class="login">登录</a><a href="#">注册</a> -->
								<!-- 登录和注册 -->

								 <nav id="loginNav" style="display: <c:choose>
                                 <c:when test="${sessionScope.user==null || sessionScope.user.username==null}">block</c:when>
                                 <c:otherwise>none</c:otherwise></c:choose>"  >
                            <ul>
                                <li id="login">
                                    <a class="aHref" id="login-trigger">
                                        登录 <span>▼</span>
                                    </a>
                                    <div id="login-content">
                                        <form  onsubmit="return user.login_submit(this);">
                                            <fieldset class="inputs">
												<input id="username" type="text" name="username" placeholder="用户名"
													   onkeypress="pressFocusNext(event,$('#login-content input[name=password]'))"
													   required autocomplete="off" >
												<input id="password" type="password" name="password" placeholder="密码"
													   onkeypress="pressEnterTo(event,user.login_submit,this)" required>
                                            </fieldset>
                                            <fieldset >
												<input type="button" id="submit" onclick="user.login_submit(this)"
                                                       value="登录">
                                                <!-- <label><input type="checkbox" value="1" name="issavePasswd" >记住密码</label> -->
                                            </fieldset>
                                        </form>
                                    </div>
                                </li>
                                <li id="signup">
									<a class="aHref" id="register-trigger">注册
                                        <span>▼</span></a>
                                    <div id="register_user">

                                        <h2  style="margin-right: -15px;margin-left: -15px" ><span class="fontawesome-user"></span>注册</h2>

                                        <form action="#" method="POST">

                                            <fieldset class="submit2">

                                                <p><label for="username">用户名</label></p>

												<p><input type="text" name="username" placeholder="用户名"
														  onkeypress="pressFocusNext(event,$('#register_user input[name=password]'))"
														  required></p>

                                                <p><label for="password">密码</label></p>

												<p><input type="password" name="password" placeholder="密码"
														  onkeypress="pressFocusNext(event,$('#repassword'))" required>
												</p>

                                                <p><label for="repassword">确认密码</label></p>

												<p><input type="password" id="repassword" placeholder="确认密码"
														  onkeypress="pressFocusNext(event,$('#register_user input[name=email]'))"
														  required></p>

                                                <p><label for="email">电子邮箱</label></p>

												<p><input type="text" name="email" placeholder="电子邮箱"
														  onkeypress="pressEnterTo(event,user.register,this)" required></p>

												<p><input type="button" id="registerBtn" onclick="user.register(this);"
                                                          value="注册"></p>

                                            </fieldset>

                                        </form>

                                    </div> <!-- end login -->
                                </li>
                            </ul>
                        </nav>
								<!-- /登录和注册 -->
    <nav id="logoutNav" style="display: <c:choose>
    <c:when test="${sessionScope.user==null || sessionScope.user.username==null}">none</c:when>
    <c:otherwise>block</c:otherwise></c:choose>" >
            <a class="aHref" onclick="user.logout()" >注销</a>
    </nav>
							</div>
							<!--
                    <div class="search">
                        <form action="" method="post">
                            <input type="text" value="Search." onblur="if(this.value=='') this.value='Search.';" onfocus="if(this.value=='Search.') this.value='';" class="ft"/>
                            <input type="submit" value="" class="fs">
                        </form>
                    </div>
                    -->
							<!-- Nav -->
							<nav id="nav">
								<ul class="sf-menu">
									<li class="current"><a class="aHref" onclick="tabSelect('<%=path%>?targetView=index/main_content',this,true,true)" >首页</a></li>
									<%--	<li><a  >视频</a>
                                            <ul style="border-left:1px solid #dbdbdb;border-right:1px solid #dbdbdb;border-bottom:1px solid #dbdbdb;" >
                                             --%>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=2&status=1',this,true)">新闻</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/pic_news/list2?type=2&status=1',this,true)">图闻</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/video/list?type=1&status=1&targetView=video/list',this,true)">点播</a>
                                            </li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/video/list?type=2&status=1&targetView=lives/list',this,true)">直播</a>
									</li>

									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=5&status=1',this,true)">政务</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=6&status=1',this,true)">民生</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=3&status=1',this,true)">监利</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=4&status=1',this,true)">商讯</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/news/list?type=1&status=1&targetView=tip/list',this,true)">报料</a>
									</li>
									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/bbs/list?status=1',this,true);bbs_type=null;">论坛</a>
									</li>


									<li><a class="aHref"
										   onclick="tabSelect('<%=path%>/html/more.html?1',this,true)">爱监利</a>
									</li>
								</ul>

							</nav>
							<!-- /Nav -->
						</div>
					</div>
				</header>
				<!-- /Header -->

				<!-- Slider -->
				<section id="slider">
					<div class="container">
						<div class="main-slider">
							<!--
                	<div class="badg">
                    	<p><a href="#">Popular.</a></p>
                    </div>
					-->

						</div>

						<!--
                <div class="slider2">
                	<div class="badg">
                    	<p><a href="#">Latest.</a></p>
                    </div>
                    <a href="#"><img src="img/trash/2.png" alt="MyPassion" /></a>
                    <p class="caption"><a href="#">We Are News.</a> Donec bibendum dolor at ante. Proin neque dui, pre tium quis fringilla ut,  sodales sed metus. </p>
                </div>
                
                <div class="slider3">
                	<a href="#"><img src="img/trash/3.png" alt="MyPassion" /></a>
                    <p class="caption"><a href="#">Happy Birthday, blue jeans! </a></p>
                </div>
                
                <div class="slider3">
                	<a href="#"><img src="img/trash/4.png" alt="MyPassion" /></a>
                    <p class="caption"><a href="#">Fantasy Family Photos </a></p>
                </div>
                -->
					</div>
				</section>
				<!-- / Slider -->
				<!-- Content -->
				<section id="content">
					<a name="index_top"></a>
					<div class="container">
						<jsp:include page="./main_new.jsp"/>
					</div>
				</section>
				<!-- / Content -->

				<!-- Footer -->
				<footer id="footer">
					<div class="container">
						<%--<div class="column-one-fourth">
							<h5 class="line">
								<span>关于我们</span>
							</h5>
							<div id="tweets"></div>
						</div>
						<div class="column-one-fourth">
							<h5 class="line">
								<span>服务条款</span>
							</h5>
							<!-- <ul class="footnav">
								<li><a href="#"><i class="icon-right-open"></i> World.</a></li>
								<li><a href="#"><i class="icon-right-open"></i>
										Business.</a></li>
								<li><a href="#"><i class="icon-right-open"></i>
										Politics.</a></li>
								<li><a href="#"><i class="icon-right-open"></i> Sports.</a></li>
								<li><a href="#"><i class="icon-right-open"></i> Health.</a></li>
								<li><a href="#"><i class="icon-right-open"></i>
										Sciences.</a></li>
								<li><a href="#"><i class="icon-right-open"></i>
										Spotlight.</a></li>
							</ul> -->
						</div>--%>
						<%--<div class="column-one-fourth">
							<a class="aHref" onclick="statisticsPage();">
								<h5 class="line">
									<span>统计</span>
								</h5>
							</a>
							<div class="flickrfeed">
								<ul id="basicuse" class="thumbs">
									<li class="hide"></li>
								</ul>
							</div>
						</div>--%>
						<div class="column">
							<h5 class="line">
								<span>技术支持</span>
							</h5>
							<p>由北京北极环影科技有限公司提供技术支持</p> 
						</div>
						<p class="copyright">Copyright 2016. All Rights Reserved</p>
					</div>
				</footer>
				<!-- / Footer -->

			</div>
		</div>
	</div>
	<!-- / Body Wrapper -->
	<jsp:include page="../subPagePanel.jsp"></jsp:include>

	<div id="cboxOverlay" style="opacity: 0.1;  visibility: visible;"></div>

	<!-- bbs详情弹出窗口层 -->
	<div id="subPageBBS" class="subPagePanel shadow">
		<h2 style="color: #fff;font-weight: bold;" class="ui-icon-close"><label>帖子详情</label>
			<a title="关闭" onclick="closeSubPageBBS();" style="margin-top: 4px;margin-right: 4px;  " class="close"></a>
		</h2>

		<div class="subContent">

		</div>

	</div>
	<!-- / bbs详情弹出窗口层 -->

    <!-- SCRIPTS -->
	<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>

	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
	<%-- <script type="text/javascript" src="<%=path%>/static/js/jquery.js"></script> --%>
	<script type="text/javascript" src="<%=path%>/static/js/easing.min.js"></script>
	<%-- <script type="text/javascript" src="<%=path%>/static/js/1.8.2.min.js"></script> --%>
	<script type="text/javascript" src="<%=path%>/static/js/ui.js"></script>
	<script type="text/javascript"
		src="<%=path%>/static/js/carouFredSel.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/superfish.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/customM.js"></script>
	<script type="text/javascript"
		src="<%=path%>/static/js/flexslider-min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/tweetable.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/timeago.js"></script>
	<script type="text/javascript"
		src="<%=path%>/static/js/jflickrfeed.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/mobilemenu.js"></script>


	<script type="text/javascript" src="<%=path%>/static/js/mypassion.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/tv.js"></script>
    <script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
    <script type="text/javascript" src="<%=path%>/static/js/jquery.grumble.js"></script>
    <script type="text/javascript" src="<%=path%>/static/js/qr_code_js.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/Chart.js"></script>
	<script type="text/javascript" >
    $login_trigger2=null;
    $register_trigger2=null;
    $(document).ready(function(){
        $login_trigger2= $('#login-trigger');
        $login_trigger2.click(function(){
            $(this).next('#login-content').slideToggle();
            $(this).toggleClass('active');

            if ($(this).hasClass('active')) {
                if ($register_trigger2.hasClass('active')){
                    $register_trigger2.trigger('click');
                }
                $(this).find('span').html('&#x25B2;');
				$("#login-content input[name=username]").get(0).focus();
            }
            else $(this).find('span').html('&#x25BC;')
        });
        $register_trigger2=$('#register-trigger');
        $register_trigger2.click(function(){
            $(this).next('#register_user').slideToggle();
            $(this).toggleClass('active');

            if ($(this).hasClass('active')){
                if ($login_trigger2.hasClass('active')){
                    $login_trigger2.trigger('click');
                }
				$(this).find('span').html('&#x25B2;');
				$("#register_user input[name=username]").get(0).focus();
            }
            else $(this).find('span').html('&#x25BC;')
        })

	});//ready
	var needAdminLogin = true;
	var statisticsPage = function () {
		if (needAdminLogin) {
			ajaxSubPanel(server_url + "/html/admin_login.html?1=1", function () {
				$('#admin_username').get(0).focus();
			});
			$subPagePanelTitle.html('管理员授权');
			return;
		}
		tabSelect('<%=path%>/news/list?targetView=statistics/list&type=1&status=1&sort=1', this, true);
	}
</script>

</body>
</html>