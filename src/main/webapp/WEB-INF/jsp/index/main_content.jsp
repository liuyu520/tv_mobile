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

<!-- 视频点播 -->
<div class="column-two-third">
    <h5 class="line"><span>视频点播</span>

        <div class="navbar">
            <a id="next3" class="next" href="#"><span></span></a>
            <a id="prev3" class="prev" href="#"><span></span></a>
        </div>
    </h5>
    <div class="outerwide">
        <ul class="wnews" id="carousel3">
            <c:forEach varStatus="status" items="${tVVideos }" var="tVVideo">
                <li>
                        <%--<span class="rating"><span style="width:80%;"></span></span>--%>
                            <a onclick="video.ajaxBroadcast('<%=path%>/video/${tVVideo.id }?targetView=video/broadcast_flv',this,false)"><img
                            class="aHref alignleft" style="max-height: 217px" src="${tVVideo.titlePic }"
                            alt="${tVVideo.title }"
                            /></a>
                    <h6 class="regular"><a >${tVVideo.title }</a></h6>
                    <span class="meta">${tVVideo.releaseTimeStr }  <%--\\   <a href="#">World News.</a>  \\   <a href="#">No
                        Coments.</a>--%> </span>

                            <p>${tVVideo.content }</p>
                                </li>
            </c:forEach>
                            </ul>
        <a class="aHref" onclick="tabSelect('<%=path%>/video/list?type=1&status=1&targetView=video/list',this,true)"
           style="float: right;  margin-top: 10px;">查看更多...</a>
                        </div>
                        
                    </div>
                    <!-- /视频点播 -->
                    
                    <!-- 图文-->
<div class="column-two-third">
                    	<h5 class="line"><span>图文</span></h5>

    <div class=" m-r-no">
        <%--<ul class="block">
        <c:forEach varStatus="status"  items="${picNewsOnes }" var="picNewsOne" >
            <li>
                <a href="#"><img src="${picNewsOne[0] }" alt="${picNewsOne[1] }" class="alignleft" /></a>
                <p>
                    <span>2015.02.15</span>
                    <a href="#">${picNewsOne[1] }</a>
                </p>
                <span class="rating"><span style="width:80%;"></span></span>
            </li>
        </c:forEach>

        </ul>--%>

        <div id="wowslider-container">
            <div class="ws_images">
                <ul>
                    <c:forEach varStatus="status" items="${picNewsOnes }" var="picNewsOne">
                        <li><a><img src="${picNewsOne[0] }"
                                             alt="${picNewsOne[1] }"/></a>${picNewsOne[1] }</li>
                    </c:forEach>
                    <%-- <li><a href="#"><img src="images/data/images/slide2.png" alt="456" title="标题aaa" /></a>标题bbb</li>
                     <li><a href="#"><img src="images/data/images/slide3.png" alt="789" title="标题ccc" /></a>标题ddd</li>
                     <li><iframe width="100%" height="100%" src="https://www.youtube.com/embed/_GOt0sMIZVY?autoplay=0&rel=0&enablejsapi=1&playerapiid=ytplayer&wmode=transparent"frameborder="0" allowfullscreen></iframe><img src="images/data/images/slide5new.jpg" alt="012" title="" /></li>
                --%> </ul>
            </div>
            <div class="ws_bullets">
                <div>
<c:forEach varStatus="status" items="${picNewsOnes }" var="picNewsOne">
                    <a href="#"></a>
</c:forEach>
                </div>
            </div>
        </div>
            <a class="aHref" onclick="tabSelect('<%=path%>/pic_news/list2?type=2&status=1',this,true)"
               style="float: right;  margin-top: 10px;">查看更多...</a>
        <script type="text/javascript" src="<%=path%>/static/js/wowslider.js"></script>
        <script type="text/javascript" src="<%=path%>/static/js/wowslider_script.js"></script>
        <!-- 主体部分. -->

                        </div>
                        
                    </div>
                    <!-- /图文 -->
                    
                     <!-- 新闻 -->
                    <div class="column-two-third">
                    	<h5 class="line">
                        	<span>新闻</span>
                            <div class="navbar">
                                <a id="next2" class="next" href="#"><span></span></a>	
                                <a id="prev2" class="prev" href="#"><span></span></a>
                            </div>
                        </h5>
                        
                        <div class="outerwide" >
                        	<ul class="wnews" id="carousel2">
                        	<c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList }" var="paperNews" >
                                <li style="height: 195px;">
                                	<img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft" style="max-width:300px;max-height:162px;"  />
                                    <h6 class="regular"><a  onclick="tabSelect('<%=path%>/news/${paperNews.id }?1=1',this,false)"  >${paperNews.title }</a></h6>
                                    <span class="meta">${paperNews.releaseTimeStr }   <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>
                                    <p>${paperNews.content }...</p>
                                </li>
                        	</c:forEach>
                            	
                            </ul>
                        </div>
                        
                        <div class="outerwide">
                        	<ul class="block2">
                        	<c:forEach varStatus="status" begin="3" end="6" items="${paperNewsList }" var="paperNews" >
                        		<li <c:if test="${status.count%2==1}">class="m-r-no"</c:if>  >
                                    <a class="aHref"
                                       onclick="tabSelect('<%=path%>/news/${paperNews.id }?1=1',this,false)"><img
                                            src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft aHref"
                                            style="max-width:140px;max-height:86px;"/></a>
                                    <p>
                                        <span>${paperNews.releaseTimeStr }</span>
                                        <a class="aHref" onclick="tabSelect('<%=path%>/news/${paperNews.id }?1=1',this,false)" >${paperNews.title }</a>
                                    </p>
                                        <%-- <span class="rating"><span style="width:80%;"></span></span>--%>
                                </li>
                        	</c:forEach>
                                
                               <%--   <li class="m-r-no">
                                    <a href="#"><img src="<%=path%>/static/img/trash/18.png" alt="MyPassion" class="alignleft" /></a>
                                    <p>
                                        <span>26 May, 2013.</span>
                                        <a href="#">Blandit Rutrum, Erat et Sagittis.</a>
                                    </p>
                                    <span class="rating"><span style="width:100%;"></span></span>
                                </li>
                                <li>
                                    <a href="#"><img src="<%=path%>/static/img/trash/19.png" alt="MyPassion" class="alignleft" /></a>
                                    <p>
                                        <span>26 May, 2013.</span>
                                        <a href="#">Blandit Rutrum, Erat et Sagittis.</a>
                                    </p>
                                    <span class="rating"><span style="width:70%;"></span></span>
                                </li>
                                <li class="m-r-no">
                                    <a href="#"><img src="<%=path%>/static/img/trash/20.png" alt="MyPassion" class="alignleft" /></a>
                                    <p>
                                        <span>26 May, 2013.</span>
                                        <a href="#">Blandit Rutrum, Erat et Sagittis.</a>
                                    </p>
                                    <span class="rating"><span style="width:60%;"></span></span>
                                </li>--%>
                            </ul>
                        </div>
                        <a class="aHref" onclick="tabSelect('<%=path%>/news/list?type=2&status=1',this,true)"
                           style="float: right;  margin-top: 10px;">查看更多...</a>
                    </div>
                    <!-- /新闻 -->
                    	<script type="text/javascript"
		src="<%=path%>/static/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/easing.min.js"></script>
		<script type="text/javascript"
		src="<%=path%>/static/js/carouFredSel.js"></script>
                    <script type="text/javascript"
		src="<%=path%>/static/js/flexslider-min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/mypassion.js"></script>
                    