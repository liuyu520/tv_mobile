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


<div class="column" style="margin-top: 20px;background-color: #fff">
                       <aside  class="column-one-fourth" style="margin-left: 0" >
                           <h5 class="line2"><span>点播栏目</span></h5>
                            <c:forEach      items="${commonDictionaries }" var="commonDictionary"  varStatus="status" >
                            <section  >
                                <a href="#" onclick="tabSelectBroadcastSort('<%=path%>/video/list?type=1&columnType=${commonDictionary.id }&status=1&targetView=video/list_common',this,${commonDictionary.id })" ><h3>${commonDictionary.value } <span style="display: none" >></span> </h3></a>
                            </section>
                            </c:forEach> 
                            
                            
                        </aside>
                        <!-- 点播列表 -->
                        <div   class="column-three-fourth"  style="float:right;margin-left: 0;margin-right: 0" id="broadcast_list" >
                            <h5 id="broadcast_right_title" class="line2"><span>点播列表</span>

                                <div class="navbar">
                                    <a style="float:right" class="aHref"
                                       onclick="tabSelect('<%=path%>/?targetView=index/main_content',this,true,true)">返回</a>
                                </div>
                            </h5>
                            <div  id="broadcast_list_right" >
                               <jsp:include page="./list_common.jsp" />
                            </div>

                        </div>
                        <!-- /点播列表 -->
                    </div>
<!--  弹出窗口层 -->
<%--<div id="subPagePanel"  >
<h2 style="color: #fff;font-weight: bold;" class="ui-icon-close" ><label>发帖</label>
    <a title="关闭" onclick="closeSubPagePanel();" style="margin-top: 4px;margin-right: 4px;  " class="close" ></a>
    </h2>
    <div id="subContent" ><!-- <img style="margin:500px;width:50px" src="<%=path%>/static/images/loading/progress.gif"> -->


</div>

</div>--%>
	<!-- / 弹出窗口层 -->
