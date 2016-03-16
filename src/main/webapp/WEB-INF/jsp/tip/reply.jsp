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


<%-- <script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
<script type="text/javascript"
	src="<%=path%>/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/page.js"></script> --%>
<c:choose>
          				<c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
<c:forEach   items="${view.recordList }" var="userSendcard"  varStatus="status" >
<div class="bbs-reply">
	<div
		style="padding-bottom: 20px; margin-bottom: 5px; padding-top: 5px;">
		<h4 class="" style="float: left">${userSendcard.from_username }</h4>
		<span style="float: right">${userSendcard.releaseTimeStr }</span>

	</div>
	${userSendcard.followcardcontent }
</div>
</c:forEach>
							<jsp:include page="../pageBottom_subDialog.jsp">
	<jsp:param name="action" value="bbs.queryReply" />
	<jsp:param name="numPerPage" value="5" />
</jsp:include>
</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
             	<!--  没有查询到符合条件的评论. -->抢个沙发吧 :)
            </div>
          </c:otherwise>
        </c:choose>
<!-- <div class="bbs-reply">
	<div
		style="padding-bottom: 20px; margin-bottom: 5px; padding-top: 5px;">
		<h4 class="" style="float: left">发布者</h4>
		<span style="float: right">2014.12.28</span>

	</div>
	这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容这是跟帖内容
</div> -->
