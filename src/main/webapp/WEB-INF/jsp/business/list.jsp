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
<meta name="keywords"
	content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" mce_href="favicon.ico" href="/static/img/sms-4.ico">

<%-- <script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script> --%>

<div class="column">
	<ul class="block2" >
		<c:forEach varStatus="status" items="${view.recordList }"
			var="business">
			<%-- <li style="margin-right: 10px"><!--<a href="#"><img src="${paperNews.pic }"
					alt="${paperNews.title }" class="alignleft" /></a>
				<p>
					<span>发表日期${paperNews.releaseTimeStr }</span> <a href="#">${paperNews.title }</a>
				</p>  <span class="rating"><span style="width: 80%;"></span></span> -->
				<img src="${business.pic }" alt="${business.title }" class="alignleft" />
                                    <h6 class="regular"><a   onclick="tabSelect('<%=path%>/news/${paperNews.id }?1=1',this,false)"  >${paperNews.title }</a></h6>
                                    <span class="meta">${business.releaseTimeStr }   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                    <p>${business.content }...</p>
				</li> --%>
				
				<li class="" style="margin-right: 10px">
                                    <div style="padding-bottom: 20px;" >
                                        <a href="${business.link }" target="_blank" ><h4>${business.title }</h4> </a>
                                        <div style="float:left;color:#c5c5c5;"  >${business.releaseTimeStr }</div>
                                    </div>
                                    <span style="color:#000;"  >${business.fromWebsit }</span>
                                    <!--<span class="rating"><span style="width:80%;"></span></span>-->
                                </li>
		</c:forEach>
	</ul>
</div>
<jsp:include page="../pageBottom.jsp">
	<jsp:param name="action" value="news.query" />
	<jsp:param name="numPerPage" value="5" />
</jsp:include>
<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
