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
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">

                   <c:choose>
          				<c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
                                <ul >
                                <c:forEach      items="${view.recordList }" var="bbs"  varStatus="status" >
                                <li >
                                    <a class="aHref" href="<%=path%>/bbs/${bbs.id }" target="_blank"
                                            >
                                        <h4 style="margin-bottom: 0;">${bbs.title }</h4></a>
                                        <div style=" margin-bottom: 20px;padding-top: 5px;" >
                                            <div class="" style="float: left">${bbs.releaseName }</div>
                                            <span  style="float: right" >${bbs.releaseTimeStr }</span>

                                        </div>
                                    <div>
                                            ${fn:substring(bbs.cardcontent,0,120) }&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                    class="aHref" href="<%=path%>/bbs/${bbs.id }" target="_blank"
                                                    >查看详情</a>
                                        &nbsp;&nbsp; <a class="aHref" onclick="bbs.addBBSComment(${bbs.id });">我要评论</a>
                                    </div>
                                        <!--<span class="rating"><span style="width:80%;"></span></span>-->
                                    </li>
                                </c:forEach>

                                </ul>
<jsp:include page="../pageBottom.jsp">
	<jsp:param name="action" value="bbs.query" />
	<jsp:param name="numPerPage" value="10" />
</jsp:include>
 	</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
             	 没有查询到符合条件的帖子.
            </div>
          </c:otherwise>
        </c:choose>

