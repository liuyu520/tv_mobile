<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" >
	/* $(function(){
		ui_tab_wh( jQuery );
	}); */
</script>
<!-- Main Content -->
<div class="main-content">
	<jsp:include page="./main_new.jsp"/>
</div>
<!-- /Main Content -->

<!-- Left Sidebar -->
<%--<div class="column-one-third" id="leftSidebar" >
	<jsp:include page="./left_sidebar.jsp" />
</div>--%>
<!-- /Left Sidebar -->
