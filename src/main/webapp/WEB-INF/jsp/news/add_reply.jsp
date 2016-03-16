<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<form id="formNews_reply" class="bbs-send-form form" onsubmit="return news.add_newsReply(this);">


	<fieldset class="inputs">
        <label for="comments">评论</label>
		<textarea rows="10" name="comments" placeholder="您的评论"  onkeydown="com.whuang.hsj.ctrlEnter2Submit(event,this);"
				  required></textarea>
	</fieldset>
	<fieldset class="submit2 inputs">
		<input type="button" onclick="news.add_newsReply(this);" class="ui-button" style="margin-top: 5px;"
			value="发送">
	</fieldset>
	<input type="hidden" name="targetId" value="${targetId }" >
	<script type="text/javascript">
		$(function () {
			$("#formNews_reply textarea[name=comments]").get(0).focus();
		});
	</script>
</form>

