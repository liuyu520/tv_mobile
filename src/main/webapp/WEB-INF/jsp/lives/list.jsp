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
<link rel="shortcut icon" href="../favicon.ico">

<div class="column" style="margin-top: 20px;background-color: #fff">

                       
                        <!-- 直播列表 -->
                        <div   id="lives_list" >
                            <h5 id="lives_right_title" class="line"><span>直播列表</span>
                            <div class="navbar" >
                                <%--<a style="float:right" class="aHref"
                                   onclick="tabSelect('<%=path%>/?targetView=index/main_content',this,true,true)">返回</a>--%>
                            </div>
                            </h5>
                            <div class="" id="tip_list_right" >
                               <jsp:include page="./list_common.jsp" />
                            </div>

                        </div>
                        <!-- /直播列表 -->
                    </div>


