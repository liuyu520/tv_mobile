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
 <c:choose>
          				<c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
<ul class="block2">
	<c:forEach varStatus="status" items="${view.recordList }"
		var="paperNews">
		<li style="margin-right: 10px;	min-height: 447px;">
			<!--<a href="#"><img src="${paperNews.pic }"
					alt="${paperNews.title }" class="alignleft" /></a>
				<p>
					<span>发表日期${paperNews.releaseTimeStr }</span> <a href="#">${paperNews.title }</a>
				</p>  <span class="rating"><span style="width: 80%;"></span></span> -->
			<c:if test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
				<a href="<%=path%>/news/${paperNews.id }" target="_blank">
					<img src="${paperNews.pic }" alt="${paperNews.title }" style="height: 200px;width: 100%;"
						 class="alignleft aHref"
							/>
				</a>
			</c:if>
			<h6 class="regular">
				<a class="aHref" href="<%=path%>/news/${paperNews.id }?status=1" target="_blank"
						>${paperNews.title }</a>
            </h6> <span style="color:#c5c5c5">${paperNews.releaseTimeStr } <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

			<p>${fn:substring(paperNews.content,0,160)}...<a class="aHref"
															 href="<%=path%>/news/${paperNews.id }?status=1"
															 target="_blank"
					>查看详情</a>
			</p>
		</li>


	</c:forEach>
</ul>
<jsp:include page="../pageBottom.jsp">
	<jsp:param name="action" value="news.query" />
	<jsp:param name="numPerPage" value="9" />
</jsp:include>
</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
				没有查询到符合条件的项.
            </div>
          </c:otherwise>
        </c:choose>
