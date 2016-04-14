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
<title>增加字典</title>
<meta name="keywords" content="transition, off-canvas, navigation, effect, 3d, css3, smooth" />
<link rel="shortcut icon" href="../favicon.ico">

	<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
	<script type="text/javascript">
	 function checkForm(){
         var groupid=com.whuang.hsj.$$one("groupId");
         if(!com.whuang.hsj.checkNullValue(groupid,'productTitle2Span','请填写groupId.')){
           return false;
         }

         var key22=com.whuang.hsj.$$one("key2");
         if(!com.whuang.hsj.checkNullValue(key22,'imageUrlSpan','请填写key22.')){
           return false;
         }


         var value22=com.whuang.hsj.$$one("value");
         if(!com.whuang.hsj.checkNullValue(value22,'productUrlSpan','请填写value.')){
           return false;
         }
         return true;
     }
      $(function() {
        var isEdit = "${isEdit }";
        if (isEdit != null && isEdit == "yes") {
            //alert("edit");
            var inputform = com.whuang.hsj.$$one("inputform");
            //modify the target action fo form
            inputform.action = "${dictionary.id}/update";
            var titleSpan = document.getElementById("titleSpan");
            //alert(titleSpan);
            if(titleSpan){
                titleSpan.innerHTML = "编辑商品";
            }
            // $('input.cancel').bind('click',function(){alert(closeDetailPanel); closeDetailPanel();});
            $('input.cancel').get(0).onclick=closeDetailPanel;
            var hidden_div = document.getElementById("hidden_div");
            if(hidden_div){
                hidden_div.innerHTML = '<input type="text" name="id" readOnly="readonly" value="${dictionary.id }" ></input>';//id of goods for update 
            }
            var groupId33=com.whuang.hsj.$$one("groupId");
            groupId33.value=String("${dictionary.groupId}");
            
            var key22=com.whuang.hsj.$$one("key2");
            key22.value=String("${dictionary.key2}");

            var value222=com.whuang.hsj.$$one("value");
            value222.value=String("${dictionary.value}");

            var description22=com.whuang.hsj.$$one("description");
            description22.value=String("${dictionary.description}");

        }
    });
	</script>
</head>
<body>
    <a href="list" >字典列表</a>&nbsp;
    <c:if test="${isEdit!=null &&  fn:length(isEdit)!=0}" >
      <a href="add" >增加字典</a>
  </c:if>
<form action="add" name="inputform" method="post" onsubmit="return checkForm();" >
     <div id="hidden_div" ></div>
        <table class="add">
            <tr>
                <td class="header" >groupId</td>
                <td colspan="2" ><input type="text"  name="groupId"  placeholder="请填写groupId"  > </td>
            </tr>
            <tr>
                <td class="header" >key</td>
                <td colspan="2" ><input type="text"  name="key2"  placeholder="请填写key"  > </td>
            </tr>
             <tr>
                <td class="header" >value</td>
                <td colspan="2" ><input type="text"  name="value"  placeholder="请填写value"  > </td>
            </tr>
             <tr>
                <td class="header" >description</td>
                <td colspan="2" ><input type="text"  name="description"  placeholder="请填写description"  > </td>
            </tr>
              <tr>
                <td colspan="2" style="text-align: center" ><input class="submit" type="submit" value="提交" > 
            <input type="button" value="取消" class="cancel" onclick="history.go(-1)" >
                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>