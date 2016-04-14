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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>字典列表</title>
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" media="all" href="<%=path%>/static/css/dictionary.css">
	<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
	<script type="text/javascript" >
	var ajaxDetail=function(id_product,edit){
		 	// $("#subPagePanel").css("background-image", "url(\"<%=path%>/static/images/loading/progress.gif\")");
		 	showLoadPanel("<%=path%>/static/images/loading/progress.gif");
		 	$('#subPagePanel').css("top", com.whuang.hsj.getScroll().top+"px");//弹出panel兼容滚动条
		 	$('#subPagePanel').show('normal');
		 	var targetView;
		 	if(arguments.length==1)
		 	{
		 		var edit="";
		 		targetView="detail_ajax";
		 	}else{
		 		targetView="add";
		 	}
		 	ajaxHtml("<%=path%>/${label }/"+id_product+"/"+edit+"?fsdf=${currentTime}&targetView=${label }/"+targetView,$('#dialogContent'));
		 	
		 }
		 var closeDetailPanel=function()
		 {
		 	$('#subPagePanel').hide('normal');
		 	$('#dialogContent').empty();
		 	
		 }
	</script>
</head>
<body>
<div>
    <c:choose>
    <c:when test="${sessionScope.logined==null ||sessionScope.logined==''}">
    </c:when>
        <c:otherwise>
	<a  href="add" >增加字典</a>&nbsp;
<c:choose>
	<c:when test="${view.recordList!=null && fn:length(view.recordList)!=0 }">
	<table  class="dictionaryList" border="1" >
		<tr> <th>ID</th><th>grouid</th><th>key</th><th>value</th><th style="width:200px" >description</th> <th>操作</th></tr>
	<c:forEach var="dictionary" items="${view.recordList}" varStatus="status">

	<tr><td>${dictionary.id}</td> <td>${dictionary.groupId}</td><td>${dictionary.key2}</td><td>${dictionary.value}</td><td title="${dictionary.description}" >${dictionary.description}</td>
<td> <a class="hrefClass" 
						onclick="ajaxDetail(${dictionary.id });" title="查看详细信息"  >详情</a>|
<a class="hrefClass" 
						onclick="ajaxDetail(${dictionary.id },'edit');" title="查看详细信息"  >编辑</a>
						|<a class="hrefClass" 
						onclick="return com.whuang.hsj.confirmDelete('确定要删除吗?') " href="${dictionary.id }/delete" >删除</a>
					</td>
	</tr>
	
	 </c:forEach>
	 </table>
	 </c:when>
          <c:otherwise>
            <div style="text-align:center;padding-top:20px;" >
              没有查询到符合条件的商品.
              <br>
              <br>
            </div>
          </c:otherwise>
        </c:choose>

    </c:otherwise>
    </c:choose>
</div>
<div id="subPagePanel">
<h2>数据字典<img onclick="closeDetailPanel();" class="" style="margin-top: 1px;float:right;width:50px;" title="关闭" src="<%=path%>/static/img/new/close.png"></h2>
<div id="dialogContent" ><!-- <img style="margin:500px;width:50px" src="<%=path%>/static/images/loading/progress.gif"> --></div>

</div>
</body>
</html>