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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Insert title here</title>
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" mce_href="favicon.ico" href="/static/img/sms-4.ico">

                    <div class="column" style="margin-top: 20px;" >

                       
                        <!-- 爆料列表 -->
                        <div   id="bbs_list" >
                            <h5 id="bbs_right_title" class="line"><span>报料列表</span>
                            <div class="navbar" >
                            <a style="float:right" class="aHref"  onclick="tip.addTipDialog();" >我要报料</a>
                                <a style="margin-right: 5px;float:right" class="aHref"
                                   onclick="tabSelect('<%=path%>/?targetView=index/main_content',this,true,true)">返回</a>
                            </div>
                            </h5>
                            <div class="" id="tip_list_right" >
                               <jsp:include page="./list_common.jsp" />
                            </div>

                        </div>
                        <!-- /论坛列表 -->
                    </div>
<!--  弹出窗口层 -->
<%--<div id="subPagePanel">
<h2 style="color: #fff;font-weight: bold;" class="ui-icon-close" ><label>发帖</label>
    <a title="关闭" onclick="closeSubPagePanel();" style="margin-top: 4px;margin-right: 4px;  " class="close" ></a>
    </h2>
    <div id="subContent" ><!-- <img style="margin:500px;width:50px" src="<%=path%>/static/images/loading/progress.gif"> -->


</div>

</div>--%>
	<!-- / 弹出窗口层 -->
