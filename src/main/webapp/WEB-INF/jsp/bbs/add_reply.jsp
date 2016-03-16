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

<form id="formBBS_reply" class="bbs-send-form form" onsubmit="return bbs.add_bbsReply(this);">


	<fieldset class="inputs">
		<label>评论</label>
		<textarea rows="10" name="followcardcontent" onkeydown="com.whuang.hsj.ctrlEnter2Submit(event,this);"
				  placeholder="您的评论"
			required></textarea>
	</fieldset>
	<fieldset class="submit2 inputs">
		<input type="button" ONCLICK="bbs.add_bbsReply(this);" class="ui-button" style="margin-top: 5px;"
			value="发送">
	</fieldset>
	<input type="hidden" name="cardid" value="${cardid }" >
    <script type="text/javascript">
        $(function () {
            $(".bbs-send-form textarea[name=followcardcontent]").get(0).focus();
        });
    </script>
</form>

