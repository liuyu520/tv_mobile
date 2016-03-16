<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/16
  Time: 20:09
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
<head>
    <title>监利县广播电视台</title>
    <link rel="stylesheet" href="<%=path%>/static/css/qinli/common.css">
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/superfish.css" media="screen" />
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/fontello/fontello.css" />
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/flexslider.css" media="screen" />
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/ui.css" />
    <!--[if lt IE 9]>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=path%>/static/js/html5.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/msgbox.css" />
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/grumble.css" />
    <meta name="keywords"
          content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/wowSlider.css" media="screen"/>

    <script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=path%>/static/js/qinli/jquery.SuperSlide.2.1.1.js"></script>
</head>
<body>
<jsp:include page="./top_common.jsp"/>

<!-- part1 -->
<div class="part1">
    <div class="part1_l">
        <div id="slideBox" class="slideBox">
            <div class="hd">
                <ul>
                    <c:forEach var="carouselDiagram" items="${carouselDiagrams}"
                               varStatus="status">
                        <li>${status.count}</li>
                    </c:forEach>

                </ul>
            </div>
            <div class="bd">
                <ul>

                    <c:forEach var="carouselDiagram" items="${carouselDiagrams}"
                               varStatus="status">
                        <li><a href="${carouselDiagram.desc}" target="_blank"><img src="${carouselDiagram.picPath}"
                                                                                    alt="${carouselDiagram.desc}"/></a>
                        </li>
                    </c:forEach>


                </ul>
            </div>
        </div>
        <script type="text/javascript">
            jQuery(".slideBox").slide({mainCell: ".bd ul", autoPlay: true});
        </script>
    </div>
    <div class="part1_r">
        <h3 class="part_t">头条关注<span class="under_line"></span></h3>
        <ul class="tt_list">

            <c:forEach varStatus="status" begin="0" end="4" items="${paperNewsList_Recommend }" var="paperNews">
                <li><a href="<%=path%>/news/${paperNews.id }" target="_blank" title="${paperNews.title }"
                       class="over_hidden anim">${paperNews.title }</a><span class="tt_date">
                        ${fn:substring(paperNews.releaseTimeStr,0,10) }
                </span></li>
            </c:forEach>

        </ul>
    </div>
</div>

<!-- part2 -->
<div class="part2">
    <div class="part2_l">
        <h3 class="part_t">本地新闻<span class="under_line"></span></h3>
        <ul class="p2_list">


            <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_491 }" var="paperNews">


                <li>
                    <c:choose>
                    <c:when test="${paperNews.pic!=null && fn:length(paperNews.pic)!=0 }">
                    <a href="<%=path%>/news/${paperNews.id }" target="_blank" class="p2_img"><img
                            src="${paperNews.pic }"
                            alt=""></a>

                    <div class="p2_l_r">
                        </c:when>
                        <c:otherwise>
                        <div class="p2_l_r" style="width:100%;">
                            </c:otherwise>
                            </c:choose>

                            <p class="p2_l_t over_hidden"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                                                             class="anim">${paperNews.title }</a></p>

                        <p class="p2_l_disc">${paperNews.content }...</p>
                    </div>
                </li>

            </c:forEach>


        </ul>
    </div>
    <div class="part2_r">
        <h3 class="part_t">真相调查<span class="under_line"></span></h3>
        <a href="<%=path%>/news/${paperNewsList_494[0].id }" target="_blank" class="p2_r_img"><img
                src="${paperNewsList_494[0].pic }" alt="${paperNewsList_494[0].title }"
                title="${paperNewsList_494[0].title }"></a>
        <ul class="p2_r_list">

            <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_494 }" var="paperNews">

                <li class="over_hidden"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                                           class="anim">${paperNews.title }</a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- part3 -->
<div class="part3">
    <h3 class="part_t">专题报道<span class="under_line"></span><a href="#" class="more">more</a></h3>

    <div class="picMarquee-left">
        <div class="bd">
            <ul class="picList">

                <c:forEach varStatus="status" items="${paperNewsList_492 }" var="paperNews">
                    <li>
                        <div class="pic"><a href="<%=path%>/news/${paperNews.id }" target="_blank"><img
                                src="${paperNews.pic }" title="${paperNews.title }" alt="${paperNews.title }"/></a>
                        </div>
                    </li>
                </c:forEach>


            </ul>
        </div>
    </div>

    <script type="text/javascript">
        jQuery(".picMarquee-left").slide({
            mainCell: ".bd ul",
            autoPlay: true,
            effect: "leftMarquee",
            vis: 5,
            interTime: 50
        });
    </script>

</div>

<!-- part4 -->
<div class="part4">
    <div id="slideBox1" class="slideBox1">
        <div class="bd">
            <ul>

                <c:forEach varStatus="status" items="${picNewsOnes }" var="picNewsOne">

                    <li><a href="javascript:void(0)"><img src="${picNewsOne[0] }" alt="${picNewsOne[1] }"/></a></li>
                </c:forEach>

            </ul>
        </div>
    </div>
    <script type="text/javascript">
        jQuery(".slideBox1").slide({mainCell: ".bd ul", autoPlay: true});
    </script>
</div>

<!-- part5 -->
<div class="part5">
    <div class="part5_l">
        <h3 class="part_t">点播<span class="under_line"></span></h3>
        <ul class="p5_l_list">
            <c:forEach items="${commonDictionaries_broadcast }" var="commonDictionary" varStatus="status">
                <li><a href="<%=path%>/video/list/1?status=1">${commonDictionary.value }</a></li>
            </c:forEach>

        </ul>
    </div>
    <div class="part5_r">
        <h3 class="part_t">直播<span class="under_line"></span></h3>
        <ul class="p5_r_list">
            <li><a href="<%=path%>/video/list/2?status=1">电视</a></li>
            <li><a href="#">广播</a></li>
        </ul>
    </div>
</div>

<!-- part6 -->
<div class="part6">
    <h3 class="part_t">政务<span class="under_line"></span></h3>
    <ul class="list">

        <c:forEach varStatus="status" items="${commonDictionaries_gov }" var="commonDictionary">
            <li><a href="<%=path%>/news/list/5?sort=${commonDictionary.id }&status=1"
                   target="_blank">${commonDictionary.value }</a></li>
        </c:forEach>
    </ul>
</div>

<!-- part7 -->
<div class="part6">
    <h3 class="part_t">民生<span class="under_line"></span></h3>
    <ul class="list">
        <c:forEach varStatus="status" items="${commonDictionaries_people }" var="commonDictionary">
            <li><a href="<%=path%>/news/list/6?sort=${commonDictionary.id }&status=1"
                   target="_blank">${commonDictionary.value }</a></li>
        </c:forEach>


    </ul>
</div>

<!-- part8 -->
<div class="part6">
    <h3 class="part_t">监利<span class="under_line"></span></h3>
    <ul class="list">
        <c:forEach varStatus="status" items="${commonDictionaries_jl }" var="commonDictionary">
            <li><a href="<%=path%>/news/list/3?sort=${commonDictionary.id }&status=1"
                   target="_blank">${commonDictionary.value }</a></li>
        </c:forEach>
    </ul>
</div>

<!-- part9 -->
<div class="part2">
    <div class="part2_l">
        <h3 class="part_t">商讯<span class="under_line"></span></h3>
        <c:choose>
            <c:when test="${paperNewsList_business!=null && fn:length(paperNewsList_business)!=0 }">
        <ul class="p2_list">
            <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_business }" var="paperNews">

                <li>
                    <a href="<%=path%>/news/${paperNews.id }" target="_blank" class="p2_img"><img
                            src="${paperNews.pic }" alt=""></a>

                    <div class="p2_l_r">
                        <p class="p2_l_t over_hidden"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                                                         class="anim">${paperNews.title }</a></p>

                        <p class="p2_l_disc">${paperNews.content }...</p>
                    </div>
                </li>
            </c:forEach>

        </ul>
            </c:when>
            <c:otherwise>
                <div style="text-align:center;padding-top:20px;">
                    没有查询到符合条件的商讯.
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="part2_r">
        <h3 class="part_t">报料台<span class="under_line"></span></h3>

        <div class="part9_t">
            <a href="<%=path%>/news/${paperNewsList_tip[0].id }" class="p9_img"><img src="${paperNewsList_tip[0].pic }"
                                                                                     title="${paperNewsList_tip[0].title }"
                                                                                     alt="${paperNewsList_tip[0].title }"></a>

            <p class="p9_disc">${paperNewsList_tip[0].content }...</p>
        </div>
        <ul class="p2_r_list">
            <c:forEach varStatus="status" begin="0" end="2" items="${paperNewsList_tip }" var="paperNews">
                <li class="over_hidden"><a href="<%=path%>/news/${paperNews.id }" target="_blank"
                                           class="anim">${paperNews.title }</a></li>
            </c:forEach>

        </ul>
    </div>
</div>

<!-- part10 -->
<div class="part10">
    <h3 class="part_t">友情链接<span class="under_line"></span></h3>
    <ul class="part10_list">
        <li class="over_hidden"><a href="#">招聘信息 </a></li>
        <li class="over_hidden"><a href="#">停水停电</a></li>
        <li class="over_hidden"><a href="#">道路维修</a></li>
        <li class="over_hidden"><a href="#">衣食住行 </a></li>
    </ul>
</div>

<jsp:include page="./footer.jsp"/>

<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/static/js/jquery.js"></script> --%>
<script type="text/javascript" src="<%=path%>/static/js/easing.min.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/static/js/1.8.2.min.js"></script> --%>
<script type="text/javascript" src="<%=path%>/static/js/ui.js"></script>
<script type="text/javascript"
        src="<%=path%>/static/js/carouFredSel.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/superfish.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/customM.js"></script>
<script type="text/javascript"
        src="<%=path%>/static/js/flexslider-min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/tweetable.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/timeago.js"></script>
<script type="text/javascript"
        src="<%=path%>/static/js/jflickrfeed.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/mobilemenu.js"></script>


<script type="text/javascript" src="<%=path%>/static/js/mypassion.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/tv.js"></script>

<script type="text/javascript" src="<%=path%>/static/js/qr_code_js.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/Chart.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/qinli/index.js"></script>
</body>
</html>
