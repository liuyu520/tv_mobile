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
                                <c:forEach   items="${view.recordList }" var="tip"  varStatus="status" >
                                    <li style="margin-bottom: 30px;float: left;width: 100%;">
                                        <a class="aHref"
                                           href="<%=path%>/video/${tip.id }?targetView=video/broadcast_flv"
                                           target="_blank"
                                           style="float: left;">
                                            <img title="点击开始播放" src="${tip.titlePic }" style="width: 70px;height: 60px;">
                                        </a>

                                        <div style="margin-left: 50px;" >
                                            <a class="aHref"
                                               href="<%=path%>/video/${tip.id }?status=1&targetView=video/detail"
                                               target="_blank">
                                                <h3 style="margin-bottom: -5px;">${tip.title }</h3></a>
                                                <c:if test="${tip.broadcastSize!=null && fn:length(tip.broadcastSize)!=0 }">
                                                <div>大小:${tip.broadcastSize }</div>
                                                </c:if>

                                            <div style=" margin-bottom: 20px;">
                                                <p  style="float: left">${tip.content }</p>
                                                <span  style="float: right" >${tip.releaseTimeStr }</span>

                                            </div>
                                        </div>
                                        </li>
                                <%-- <li >
                                        <a class="aHref" onclick="ajaxBBSDetail('<%=path%>/bbs/${bbs.id }?1=1',this,${bbs.id })" ><h2>${bbs.title }</h2></a>
                                        <div style=" margin-bottom: 20px;padding-top: 5px;" >
                                            <h4 class="" style="float: left" >${bbs.releaseName }</h4>
                                            <span  style="float: right" >${bbs.releaseTimeStr }</span>

                                        </div>
                                        <!--<span class="rating"><span style="width:80%;"></span></span>-->
                                    </li> --%>
                                </c:forEach>

                                </ul>
<jsp:include page="../pageBottom.jsp">
    <jsp:param name="action" value="video.query"/>
    <jsp:param name="numPerPage" value="10"/>
</jsp:include>
 	</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
             	 没有查询到符合条件的视频.
            </div>
          </c:otherwise>
        </c:choose>

