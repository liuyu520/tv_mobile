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
<c:forEach   items="${view.recordList }" var="newsComment"  varStatus="status" >
<div class="bbs-reply">
	<div
            style="padding-bottom: 15px; margin-bottom: 5px; padding-top: 5px;">
        <span class="little_gray" style="float: left;">${newsComment.fromUsername }<a class="aHref"
                                                                          onclick="news.addNewsComment(${newsComment.targetId },'${newsComment.fromUsername }');"
                                                                          style="margin-left: 10px;">回复</a></span>
		<span style="float: right;" class="little_gray">${newsComment.releaseTimeStr }</span>

	</div>
    <div style="color: #000;">${newsComment.comments }</div>
</div>
</c:forEach>
							<jsp:include page="../pageBottom_subDialog.jsp">
	<jsp:param name="action" value="news.queryReply" />
	<jsp:param name="numPerPage" value="10" />
</jsp:include>
</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;color: #999;" >
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
