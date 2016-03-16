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

<div class="column" style="margin-top: 20px;padding-left: 10px;background-color: #fff;">

                        <aside  class="column-one-fourth" style="margin-left: 0;border-right: 1px solid #ea4748;" >
                            <h5 class="line2"><span>论坛栏目</span></h5>
                            <c:forEach      items="${commonDictionaries }" var="commonDictionary"  varStatus="status" >
                            <section  >
                                <a class="aHref"
                                   onclick="tabSelectBBSSort('<%=path%>/bbs/list?type=${commonDictionary.id }&status=1&targetView=bbs/list_common',this,${commonDictionary.id })">
                                    <h3>${commonDictionary.value } <span style="display: none">></span></h3></a>
                            </section>
                            </c:forEach> 
                            
                            
                        </aside>
                        <!-- 论坛列表 -->
                        <div class="column-three-fourth" style="float:right;margin-left: 0;margin-right: 0" id="bbs_list" >
                            <h5 id="bbs_right_title" class="line2"><span>论坛列表</span>
                            <div class="navbar" >
                                <a id="navHrefComment" class="aHref"
                                   style="float:right;margin-left: 5px;padding-left: 5px; border-left: 2px solid #ddd;display: none;">评论</a>
                                <a class="aHref" style="float:right" onclick="bbs.addBBS();">发帖</a>

                            </div>
                            </h5>
                            <div class="" id="bbs_list_right" >
                               <jsp:include page="./list_common.jsp" />
                            </div>

                        </div>
                        <!-- /论坛列表 -->
                    </div>


<%--
<script type="text/javascript" language="JavaScript">
    $(function () {

    });
</script>--%>
