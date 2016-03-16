<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/16
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!-- footer -->
<div id="footer" class="clearfix">
    <div class="container clearfix">
        <div class="column">
            <h5 class="line">
                <span>技术支持</span>
            </h5>

            <p>由北京北极环影科技有限公司提供技术支持</p>
        </div>
        <p class="copyright">Copyright 2015. All Rights Reserved</p>
    </div>
</div>