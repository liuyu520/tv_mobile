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
                    <div class="sidebar">
                        <h5 class="line"><span>视频直播</span></h5>
                        <!-- <iframe src="http://player.vimeo.com/video/65110834?title=0&amp;byline=0&amp;portrait=0&amp;color=ffffff" width="300px" height="170px" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe> -->
                        <c:forEach varStatus="status" items="${tVVideosLive }" var="live">
                        	<a class="aHref" onclick="video.ajaxTVLive('<%=path%>/video/${live.id }?1=1')" ><h2>${live.title }</h2></a>
                        </c:forEach>
                        <a class="aHref"
                           onclick="tabSelect('<%=path%>/video/list?type=2&status=1&targetView=lives/list',this,true)"
                           style="float: right;  margin-top: 10px;">查看更多...</a>
                    </div>
<div class="sidebar">
    <h5 class="line"><span>友情链接</span></h5>
    <ul class="social">
        <li>
            <a href="#" class="facebook"><i class="icon-facebook"></i></a>
            <span>6,800 <br/> <i>fans</i></span>
        </li>
        <li>
            <a href="#" class="twitter"><i class="icon-twitter"></i></a>
            <span>12,475 <br/> <i>followers</i></span>
        </li>
        <li>
            <a href="#" class="rss"><i class="icon-rss"></i></a>
            <span><i>Subscribe via rss</i></span>
        </li>
    </ul>
</div>
                      <!-- <div class="sidebar">
                    	<div id="tabs">
                            <ul>
                                <li><a href="#tabs1">Recent.</a></li>
                                <li><a href="#tabs2">Popular.</a></li>
                                <li><a href="#tabs3">Comments.</a></li>
                            </ul>
                            <div id="tabs1">
                                <ul>
                                	<li>
                                    	<a href="#" class="title">Blandit Rutrum, Erat et Sagittis Adipcising Elit.</a>
                                        <span class="meta">26 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Blandit Rutrum, Erat et Sagittis Adipcising Elit.</a>
                                        <span class="meta">26 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Blandit Rutrum, Erat et Sagittis Adipcising Elit.</a>
                                        <span class="meta">26 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Blandit Rutrum, Erat et Sagittis Adipcising Elit.</a>
                                        <span class="meta">26 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                </ul>
                            </div>
                            <div id="tabs2">
                                <ul>
                                	<li>
                                    	<a href="#" class="title">Mauris eleifend est et turpis. Duis id erat.</a>
                                        <span class="meta">27 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Mauris eleifend est et turpis. Duis id erat.</a>
                                        <span class="meta">27 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Mauris eleifend est et turpis. Duis id erat.</a>
                                        <span class="meta">27 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                    <li>
                                    	<a href="#" class="title">Mauris eleifend est et turpis. Duis id erat.</a>
                                        <span class="meta">27 May, 2013.   \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a></span>
                                        <span class="rating"><span style="width:70%;"></span></span>
                                    </li>
                                </ul>
                            </div>
                            <div id="tabs3">
                                <ul>
                                	<li>
                                    	<a href="#" class="title"><strong>Someone:</strong> eleifend est et turpis. Duis id erat.Mauris eleifend est et turpis. Duis id erat.</a>
                                    </li>
                                    <li>
                                    	<a href="#" class="title"><strong>Someone:</strong> eleifend est et turpis. Duis id erat.Mauris eleifend est et turpis. Duis id erat.</a>
                                    </li>
                                    <li>
                                    	<a href="#" class="title"><strong>Someone:</strong> eleifend est et turpis. Duis id erat.Mauris eleifend est et turpis. Duis id erat.</a>
                                    </li>
                                    <li>
                                    	<a href="#" class="title"><strong>Someone:</strong> eleifend est et turpis. Duis id erat.Mauris eleifend est et turpis. Duis id erat.</a>
                                    </li>
                                    <li>
                                    	<a href="#" class="title"><strong>Someone:</strong> eleifend est et turpis. Duis id erat.Mauris eleifend est et turpis. Duis id erat.</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div> -->
                    
                   <!--  <div class="sidebar"> 
                    	<h5 class="line"><span>Accordion.</span></h5>
                        <div id="accordion">
                        
                            <h3>Poserue Clubre.</h3>
                            <div>
                                <p>Vestibulum tempor feugiat est in posuere. Sed auctor libero augue, a faucibus turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices. posuere.</p>
                            </div>
                            
                            <h3>Lubelia Mest.</h3>
                            <div>
                                <p>Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.</p>
                            </div>
                        
                            <h3>Tincidunt Massa.</h3>
                            <div>
                                <p>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac liberoac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.</p>
                            </div>
                            
                            <h3>Quisque lobortis.</h3>
                            <div>
                                <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netuset malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est.</p>
                            </div>
                            
                        </div>
                    </div>-->
                    