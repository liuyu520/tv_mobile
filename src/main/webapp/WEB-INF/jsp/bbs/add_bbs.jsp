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

<form class="bbs-send-form form" id="formAddBBS" onsubmit="return bbs.add_bbs(this);">
        	<fieldset class="inputs" >
                <label for="bbs_type">论坛栏目</label>
                <select name="type" id="bbs_type" required>
                    <option value="0">---请选择栏目---</option>
					<c:forEach      items="${commonDictionaries }" var="commonDictionary"  varStatus="status" >
                 		<option value="${commonDictionary.id }">${commonDictionary.value } </option>
                 	</c:forEach>
                </select>
                
            </fieldset>
            <fieldset class="inputs" >
                <label for="bbs_title">标题</label>
                <input type="text" id="bbs_title" name="title" placeholder="帖子标题" required>
            </fieldset>
            <fieldset  class="inputs" >
                <label for="cardcontent">主体内容</label>
                <script charset="utf-8" src="<%=path%>/static/js/kindeditor/kindeditor-min.js"></script>
                <script>
                    var editor;
                    $(function() {
                        var subPagePanel_width=$('#subPagePanel').width();
                        editor = KindEditor.create('textarea[name="cardcontent"]', {
                            uploadJson : '<%=path%>/upload/uploadKEditor',
                            fileManagerJson : '<%=path%>/upload/fileManager',
                            items : [
                                'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template',  'cut', 'copy', 'paste',
                                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                                'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                                 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                                'anchor', 'link', 'unlink', '|', 'about'
                            ],
                            allowFileManager : true,width : (subPagePanel_width-10)+'px'});
                    });
                </script>
                <textarea id="cardcontent" rows="40" name="cardcontent" placeholder="帖子主体内容" required></textarea>
            </fieldset>
            <fieldset class="submit2 inputs" >
                <input type="button" onclick="bbs.add_bbs(this);" class="ui-button" style="margin-top: 5px;" value="发送">
            </fieldset>
    <script type="text/javascript">
        $(function () {
            $(".bbs-send-form input[name=title]").get(0).focus();
        });
    </script>
        </form>
