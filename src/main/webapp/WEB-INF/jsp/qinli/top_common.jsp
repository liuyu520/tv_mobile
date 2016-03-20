<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/16
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!-- header -->
<div class="header_wrap">
    <div class="header">

        <div class="header_r">
            <c:choose>
                <c:when test="${sessionScope.user==null || sessionScope.user.username==null}">
                    <div>
                        <a href="javascript:void(0)" class="login_btn">登录</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0);" class="reg_btn">注册</a>

                        <form class="log_f" onsubmit="return user.login_submit(this);">
                            <dl>
                                <label for="username_login">用户名：</label>
                                <input type="text" placeholder="用户名或手机号" id="username_login" name="username">
                            </dl>
                            <dl>
                                <label for="psw">密码：</label>
                                <input type="password" id="password" name="password">
                            </dl>
                            <dl class="sub_dl">
                                <input type="button" class="login_sub" value="登录" onclick="user.login_submit(this)">
                            </dl>
                        </form>
                        <form action="" class="reg_f">
                            <dl>
                                <label for="username">用户名：</label>
                                <input type="text" placeholder="手机号" name="username" id="username">
                            </dl>
                            <dl>
                                <label for="psw">密码：</label>
                                <input type="password" name="password" id="psw">
                            </dl>
                            <dl>
                                <label for="psw">确认密码：</label>
                                <input type="password" id="repassword">
                            </dl>
                            <dl>
                                <label for="psw">邮箱：</label>
                                <input type="text" placeholder="邮箱" name="email" id="email">
                            </dl>
                            <dl class="sub_dl">
                                <input type="button" class="login_sub" onclick="user.register(this);" value="注册">
                            </dl>
                        </form>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="welcome" style="cursor: pointer">欢迎您,<span class="head-name" >${sessionScope.user.username}</span>
                        <ul style="display: none;z-index: 9999; background: #FFFFFF;position: absolute;width: 90px;border-radius: 3px;">
                            <li onclick="user.logout()" class="user_profile" >
                                <i class="iconExit"></i><span>退出</span>
                            </li>
                        </ul>
                    </div>

                </c:otherwise></c:choose>


        </div>
    </div>
</div>
<c:if test="${param.showLogo!=false}">
<!-- jl img -->
<div class="top_wrap">
    <div class="logo"><a href="http://hbjltv.com/"><img style="width: 110px;height: 100px"
                                                        src="<%=path%>/static/img/g.png"
                                               alt="监利县广播电视台" title="监利县广播电视台"></a></div>

</div>
</c:if>
<!-- nav -->
<div class="nav_wrap">
    <ul class="nav">
        <li><a href="http://hbjltv.com">首页</a></li>
        <li><a href="<%=path%>/news/list/2">新闻</a></li>
        <li><a href="<%=path%>/pic_news/list2?type=2">图闻</a></li>
        <li><a href="<%=path%>/video/list/1">点播</a></li>
        <li><a href="<%=path%>/video/list/2?status=1&targetView=lives/index">直播</a></li>
        <li><a href="<%=path%>/news/list/5">政务</a></li>
        <li><a href="<%=path%>/news/list/6">民生</a></li>
        <li><a href="<%=path%>/news/list/3">监利</a></li>
        <li><a href="<%=path%>/news/list/4">商讯</a></li>
        <li><a href="<%=path%>/news/list/1">报料</a></li>
        <li><a href="<%=path%>/bbs/list">论坛</a></li>
        <li><a href="<%=path%>/html/more.html" target="_blank">爱监利</a></li>
    </ul>
</div>

<!-- bbs详情弹出窗口层 -->
<div id="subPageBBS" style="display: none" class="subPagePanel shadow">
    <h2 style="color: #fff;font-weight: bold;" class="ui-icon-close"><label>帖子详情</label>
        <a title="关闭" onclick="closeSubPageBBS();" style="margin-top: 4px;margin-right: 4px;  " class="close"></a>
    </h2>

    <div class="subContent">

    </div>

</div>
<!-- / bbs详情弹出窗口层 -->
<jsp:include page="../subPagePanel.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.grumble.js"></script>