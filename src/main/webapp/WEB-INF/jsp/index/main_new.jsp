<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/14
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>

<div id="main22">
    <div class="column-one-half">
        <div class="flexslider">
            <ul class="slides">
                <c:forEach var="carouselDiagram" items="${carouselDiagrams}"
                           varStatus="status">
                    <li><img src="${carouselDiagram.picPath}"
                             alt="${carouselDiagram.desc}"/>
                        <section
                                style="bottom:1px;width: inherit; position: absolute;background-color: rgba(255,255,255,0.7)">
                            <h5 style="color: blue; font-weight: bold;font-size: 20px;">${carouselDiagram.desc}</h5>
                        </section>
                    </li>
                </c:forEach>


            </ul>
        </div>
        </div>
    <div class="column-one-half one_module" style="height: 302px;">
        <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_492 }" var="paperNews">
            <li>
                <h6 class="regular"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                        >${paperNews.title }</a>
                </h6>
            </li>
        </c:forEach>
    </div>
    <div class="column-one-half one_module ">
        <h2>本地新闻</h2>
        <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_491 }" var="paperNews">
            <li>
                <c:if test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
                    <a href="<%=path%>/news/${paperNews.id }" target="_blank">
                        <img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft"
                             style="max-width:300px;max-height:162px;"/>
                    </a>
                </c:if>
                <h6 class="regular"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                        >${paperNews.title }</a>
                </h6>
                <span class="meta">${paperNews.releaseTimeStr }   <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                <p>${paperNews.content }...</p>
            </li>
        </c:forEach>
    </div>
    <div class="column-one-half one_module">
        <h2>真相调查</h2>
        <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_494 }" var="paperNews">
            <li style="height: 195px;">
                <c:if test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
                    <img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft"
                         style="max-width:300px;max-height:162px;"/>
                </c:if>
                <h6 class="regular"><a href="<%=path%>/news/${paperNews.id }" target="_blank">${paperNews.title }</a>
                </h6>
                <span class="meta">${paperNews.releaseTimeStr }   <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                <p>${paperNews.content }...</p>
            </li>
        </c:forEach>
    </div>
    <div class="container one_module" style="padding-bottom: 145px;">
        <h2>专题报道</h2>
        <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_492 }" var="paperNews">
            <a href="<%=path%>/news/${paperNews.id }" target="_blank">
                <img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft"
                     style="max-width:210px;max-height:162px;"
                        />
            </a>
        </c:forEach>
    </div>
    <div class="container one_module" style="margin-bottom: 30px;">
        <h2>图闻</h2>

        <div id="wowslider-container">
            <div class="ws_images">
                <ul>
                    <c:forEach varStatus="status" items="${picNewsOnes }" var="picNewsOne">
                    <li><a><img src="${picNewsOne[0] }"
                                alt="${picNewsOne[1] }"/></a>${picNewsOne[1] }</li>
                    </c:forEach>
            </div>
            <div class="ws_bullets">
                <div>
                    <c:forEach varStatus="status" items="${picNewsOnes }" var="picNewsOne">
                        <a href="#"></a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="column-two-third one_module">
        <h2>点播</h2>

        <div style="margin-left: 0;height:240px">
            <h5 class="line"><span>点播栏目</span></h5>
            <c:forEach items="${commonDictionaries_broadcast }" var="commonDictionary" varStatus="status">
                <section style="width: 50%;float: left">
                    <a href="#"
                       onclick="tabSelect('<%=path%>/video/list?type=1&status=1&columnType=${commonDictionary.id }',this,true)">
                        <h3>${commonDictionary.value } <span style="display: none">></span></h3></a>
                </section>
            </c:forEach>


        </div>
    </div>
    <div>
        <h2>直播</h2>

    </div>
    <div class="column one_module" style="padding-top: 10px;">
        <!-- Nav --><h2>政务</h2>
        <nav id="newsSortNav">
            <ul class="sf-menu" style="background:inherit;">
                <c:forEach varStatus="status" items="${commonDictionaries_gov }" var="commonDictionary">
                <li><a class="aHref"
                       onclick="tabSelect('<%=path%>/news/list?type=5&status=1&sort=${commonDictionary.id }',this,true)"
                       style="font-size:20px;text-transform:auto;">${commonDictionary.value }</a>
                    </c:forEach>

            </ul>

        </nav>
        <c:if test="${paperNewsList_gov!=null && fn:length(paperNewsList_gov)!=0 }">
            <input type="hidden" value="${paperNewsList_gov[0].type }" name="news_type">
        </c:if>
        <!-- /Nav -->
        <div id="news_list_placeholder">
            <c:choose>
                <c:when test="${paperNewsList_gov!=null && fn:length(paperNewsList_gov)!=0 }">
                    <ul class="block2">
                        <c:forEach varStatus="status" items="${paperNewsList_gov }"
                                   var="paperNews">
                            <li style="margin-right: 10px;">
                                <!--<a href="#"><img src="${paperNews.pic }"
					alt="${paperNews.title }" class="alignleft" /></a>
				<p>
					<span>发表日期${paperNews.releaseTimeStr }</span> <a href="#">${paperNews.title }</a>
				</p>  <span class="rating"><span style="width: 80%;"></span></span> -->
                                <c:if test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
                                    <img src="${paperNews.pic }" alt="${paperNews.title }"
                                         style="height: 200px;width: 100%;"
                                         class="alignleft aHref"
                                         onclick="news.ajaxNewsDetailOnDialog('<%=path%>/news/${paperNews.id }?status=1',this,${paperNews.id })"/>
                                </c:if>
                                <h6 class="regular">
                                    <a class="aHref" href="<%=path%>/news/${paperNews.id }" target="_blank"
                                            >${paperNews.title }</a>
                                </h6> <span style="color:#c5c5c5">${paperNews.releaseTimeStr } <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                                <p>${fn:substring(paperNews.content,0,160)}...<a class="aHref"
                                                                                 onclick="news.ajaxNewsDetailOnDialog('<%=path%>/news/${paperNews.id }?status=1',this,${paperNews.id })">查看详情</a>
                                </p>
                            </li>


                        </c:forEach>
                    </ul>

                </c:when>
                <c:otherwise>
                    <div style="text-align:center;padding-top:20px;">
                        没有查询到符合条件的项.
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <!--  政务-->
    <!-- 民生 -->
    <div class="column one_module" style="padding-top: 10px;">
        <h2>民生</h2>
        <nav id="newsSortNav">
            <ul class="sf-menu" style="background:inherit;">
                <c:forEach varStatus="status" items="${commonDictionaries_people }" var="commonDictionary">
                    <li><a class="aHref"
                           onclick="tabSelect('<%=path%>/news/list?type=6&status=1&sort=${commonDictionary.id }',this,true)"
                           target="_blank"
                       style="font-size:20px;text-transform:auto;">${commonDictionary.value }</a>
                    </c:forEach>

            </ul>

        </nav>
        <c:if test="${paperNewsList_people!=null && fn:length(paperNewsList_gov)!=0 }">
            <input type="hidden" value="${paperNewsList_gov[0].type }" name="news_type">
        </c:if>
        <!-- /Nav -->
        <div id="news_list_placeholder">
            <c:choose>
                <c:when test="${paperNewsList_gov!=null && fn:length(paperNewsList_gov)!=0 }">
                    <ul class="block2">
                        <c:forEach varStatus="status" items="${paperNewsList_gov }"
                                   var="paperNews">
                            <li style="margin-right: 10px;">
                                <!--<a href="#"><img src="${paperNews.pic }"
					alt="${paperNews.title }" class="alignleft" /></a>
				<p>
					<span>发表日期${paperNews.releaseTimeStr }</span> <a href="#">${paperNews.title }</a>
				</p>  <span class="rating"><span style="width: 80%;"></span></span> -->
                                <c:if test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
                                    <img src="${paperNews.pic }" alt="${paperNews.title }"
                                         style="height: 200px;width: 100%;"
                                         class="alignleft aHref"
                                         onclick="news.ajaxNewsDetailOnDialog('<%=path%>/news/${paperNews.id }?status=1',this,${paperNews.id })"/>
                                </c:if>
                                <h6 class="regular">
                                    <a class="aHref" href="<%=path%>/news/${paperNews.id }" target="_blank"
                                            >${paperNews.title }</a>
                                </h6> <span style="color:#c5c5c5">${paperNews.releaseTimeStr } <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                                <p>${fn:substring(paperNews.content,0,160)}...<a class="aHref"
                                                                                 onclick="news.ajaxNewsDetailOnDialog('<%=path%>/news/${paperNews.id }?status=1',this,${paperNews.id })">查看详情</a>
                                </p>
                            </li>


                        </c:forEach>
                    </ul>

                </c:when>
                <c:otherwise>
                    <div style="text-align:center;padding-top:20px;">
                        没有查询到符合条件的项.
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <!-- 民生 -->

    <div class="column-one-half one_module">
        <h2>商讯</h2>

        <div>
            <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_business }" var="paperNews">
                <li style="height: 195px;">
                    <img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft"
                         style="max-width:300px;max-height:162px;"/>
                    <h6 class="regular"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                            >${paperNews.title }</a>
                    </h6>
                    <span class="meta">${paperNews.releaseTimeStr }   <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                    <p>${paperNews.content }...</p>
                </li>
            </c:forEach>
        </div>
    </div>

    <div class="column-one-half one_module">
        <h2>报料台</h2>
        <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_tip }" var="paperNews">
            <li style="height: 195px;">
                <img src="${paperNews.pic }" alt="${paperNews.title }" class="alignleft"
                     style="max-width:300px;max-height:162px;"/>
                <h6 class="regular"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                        >${paperNews.title }</a>
                </h6>
                <span class="meta">${paperNews.releaseTimeStr }   <!-- \\   <a href="#">World News.</a>   \\   <a href="#">No Coments.</a> --></span>

                <p>${paperNews.content }...</p>
            </li>
        </c:forEach>
    </div>
</div>
</html>
