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

<link rel="stylesheet" href="<%=path%>/static/css/base4.min.css">

<div class="column" style="margin-top: 20px;">


    <!-- 图文列表 -->
    <div id="photo_grid" class="flex_grid">
        <c:forEach varStatus="status" items="${view.recordList }" var="picNewsOne">
            <div class="item" style="width: 225px; height: 160px; display: block;"><%--<a
                    href="javascript:void(0);">--%>
                <img
                        src="${picNewsOne[1] }" class="preview_img"
                        data-url="${picNewsOne[1] }" data-width="${picNewsOne[5] }"
                        data-height="${picNewsOne[6] }" alt="${picNewsOne[2] }"
                        title="${picNewsOne[2] }"><em>${picNewsOne[2] }</em><%--</a>--%>
            </div>
        </c:forEach>
        <%--<div class="open_preview_img" style="z-index: 100009; display: block; top: 391px; left: 5px;">
            <div style="position:relative;overflow:hidden;width:552px;height:369px"><img
                    style="width:550px;height:367px"
                    src="http://123.57.250.51/static/img//uploadimgs/1437467516.jpg"><img style="width:550px"
                                                                                          src="http://123.57.250.51/static/img//uploadimgs/1437467516.jpg">
            </div>
            <em>参观中，县博物馆邀请到玉沙小学五年级的小讲解员谭舒龙为同学们细致讲解了馆藏文物，详细介绍了湘鄂西特委的历史文化和革命先辈们的光辉事迹。</em></div>--%>
        <%--
                <div class="item" style="width: 240px; height: 160px; display: block;"><a
                        href="http://pixabay.com/zh/%E8%B6%B3%E7%90%83-%E5%9B%A2%E9%98%9F-%E4%BA%BA-%E6%B8%B8%E6%88%8F-%E4%BA%BA%E7%BE%A4-%E8%BF%90%E5%8A%A8-%E7%BE%8E%E5%9B%BD-%E7%8C%AA%E7%9A%AE-%E7%BE%8E%E5%BC%8F%E8%B6%B3%E7%90%83-461340/"><img
                        src="http://pixabay.com/static/uploads/photo/2014/10/06/14/50/boy-476340__180.jpg" class="preview_img"
                        data-url="http://pixabay.com/static/uploads/photo/2014/10/06/14/50/boy-476340__180.jpg"
                        data-width="533.333333333"
                        data-height="355.0" alt="足球, 团队, 人, 游戏, 人群, 运动, 美国, 猪皮, 美式足球, 事件" title="足球, 团队, 人, 游戏, 人群"><em>足球, 团队, 人, 游戏, 人群,
                    运动, 美国, 猪皮, 美式足球, 事件</em></a></div>

                <div class="item" style="width: 240px; height: 160px; display: block;"><a
                        href="http://pixabay.com/zh/%E8%B6%B3%E7%90%83-%E5%9B%A2%E9%98%9F-%E4%BA%BA-%E6%B8%B8%E6%88%8F-%E4%BA%BA%E7%BE%A4-%E8%BF%90%E5%8A%A8-%E7%BE%8E%E5%9B%BD-%E7%8C%AA%E7%9A%AE-%E7%BE%8E%E5%BC%8F%E8%B6%B3%E7%90%83-461340/"><img
                        src="http://pixabay.com/static/uploads/photo/2014/10/06/14/50/boy-476340__180.jpg" class="preview_img"
                        data-url="/static/uploads/photo/2014/09/26/01/51/football-461340_640.jpg" data-width="533.333333333"
                        data-height="355.0" alt="足球, 团队, 人, 游戏, 人群, 运动, 美国, 猪皮, 美式足球, 事件" title=""><em>足球, 团队, 人, 游戏, 人群,
                    运动, 美国, 猪皮, 美式足球, 事件</em></a></div>--%>
    </div>
    <script src="<%=path%>/static/js/pic_base3.js"></script>
    <!-- /图文列表 -->
    <jsp:include page="../pageBottom.jsp">
        <jsp:param name="action" value="pic.query"/>
        <jsp:param name="numPerPage" value="10"/>
    </jsp:include>
</div>


