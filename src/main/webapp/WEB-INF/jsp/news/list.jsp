w<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="shortcut icon" href="../favicon.ico">

<%-- <script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script> --%>


<div class="column" style="padding-top: 10px;padding-left: 10px;background-color: #fff;">
<!-- Nav -->
							<nav id="newsSortNav">
								<ul class="sf-menu" style="background:inherit;">
								<c:forEach varStatus="status" items="${commonDictionaries }" var="commonDictionary">
									<li id="type_${commonDictionary.id }"><a class="aHref"
																			 href="<%=path%>/news/list/${news_type}?sort=${commonDictionary.id }&status=1"
								style="font-size:20px;text-transform:auto;" >${commonDictionary.value }</a>
								</c:forEach>
									
								</ul>

							</nav>
	<c:if test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
		<input type="hidden" value="${view.recordList[0].type }" name="news_type">
	</c:if>
							<!-- /Nav -->
	<div id="news_list_placeholder"  >
		<jsp:include page="./list_common.jsp" />
	</div>
</div>

<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
