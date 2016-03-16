<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/16
  Time: 22:25
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
    <script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
    <!-- STYLES -->
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/superfish.css" media="screen"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/fontello/fontello.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/flexslider.css" media="screen"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/ui.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/base.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/style.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/960.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/1000.css"
          media="only screen and (min-width: 768px) and (max-width: 1000px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/767.css"
          media="only screen and (min-width: 480px) and (max-width: 767px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/devices/479.css"
          media="only screen and (min-width: 200px) and (max-width: 479px)"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/dropdown.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/register.css"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=path%>/static/js/customM.js"></script> <![endif]-->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=path%>/static/js/html5.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/msgbox.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/static/css/grumble.css"/>
    <meta name="keywords"
          content="transition, off-canvas, navigation, effect, 3d, css3, smooth"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/wowSlider.css" media="screen"/>
</head>
<body>
<jsp:include page="../qinli/top_common.jsp">
    <jsp:param name="showLogo" value="false"/>
</jsp:include>
<section id="content">
    <div class="container">
        <jsp:include page="./list.jsp"/>
    </div>
</section>

<!-- SCRIPTS -->
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
<script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.grumble.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/qr_code_js.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/Chart.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/qinli/index.js"></script>
</body>
</html>
