<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth"/>
<link rel="shortcut icon" mce_href="favicon.ico" href="/static/img/sms-4.ico">

<c:choose>
    <c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
        <ul>
            <c:forEach items="${view.recordList }" var="tip" varStatus="status">
                <li style="float: left;width: 100%;margin-left: -30px;margin-bottom: 15px">

                    <div style=" margin-bottom: 2px;">
                            <span title="${tip.info }"  >${tip.info }</span>
                    </div>
                    <a href="./${tip.id }/delete">删除</a>
                </li>

            </c:forEach>

        </ul>
        <jsp:include page="../pageBottom.jsp">
            <jsp:param name="action" value="video.query"/>
            <jsp:param name="numPerPage" value="10"/>
        </jsp:include>
    </c:when>
    <c:otherwise>
        <div style="text-align:center;padding-top:20px;">
            没有查询到符合条件的消息.
        </div>
    </c:otherwise>
</c:choose>

