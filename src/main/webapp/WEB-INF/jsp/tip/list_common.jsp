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



                   <c:choose>
          				<c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
                                <ul >
                                <c:forEach   items="${view.recordList }" var="tip"  varStatus="status" >
                                <li >
                                        <a style="float: left;">
                                            <img class="aHref"
                                                 onclick="tip.ajaxTipDetailOnDialog('<%=path%>/news/${tip.id }?1=1',this,${tip.id })"
                                                 src="${tip.pic }" style="width: 50px;max-height: 40px;">
                                        </a>
                                        <div  style="margin-left: 50px" >
                                            <a class="aHref"
                                               onclick="tip.ajaxTipDetailOnDialog('<%=path%>/news/${tip.id }?1=1',this,${tip.id })">
                                                <h2 style="margin-bottom: 5px;"  >${tip.title }</h2></a>
                                            <div style=" margin-bottom: 22px;" >
                                                <div class="" style="float: left">${tip.contacts }</div>
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
    <jsp:param name="action" value="tip.query"/>
    <jsp:param name="numPerPage" value="10"/>
</jsp:include>
 	</c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
             	 没有查询到符合条件的报料.
            </div>
          </c:otherwise>
        </c:choose>

