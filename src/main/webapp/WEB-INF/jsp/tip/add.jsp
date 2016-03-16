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
<script type="text/javascript" src="<%=path%>/static/js/ajaxfileupload.js"></script>
<form class="bbs-send-form form"
      onsubmit="return tip.add_tip(this);"><!-- tip.add_tip(this) -->

    <fieldset class="inputs">
        <label for="tip_title">标题</label> <input type="text" id="tip_title" name="title" placeholder="请输入标题"
                                                 required>
    </fieldset>
    <fieldset class="inputs">
        <label for="tip_content">具体内容</label>
        <script charset="utf-8" src="<%=path%>/static/js/kindeditor/kindeditor-min.js"></script>
        <script>
            var editor;
            $(function () {
                var subPagePanel_width = $('#subPagePanel').width();
                editor = KindEditor.create('textarea[name="content"]', {
                    uploadJson: '<%=path%>/upload/uploadKEditor',
                    fileManagerJson: '<%=path%>/upload/fileManager',
                    items: [
                        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
                        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                        'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                        'anchor', 'link', 'unlink', '|', 'about'
                    ],
                    allowFileManager: true, width: (subPagePanel_width - 10) + 'px'
                });
            });
        </script>
        <textarea rows="10" id="tip_content" name="content" placeholder="请输入具体内容" required></textarea>
    </fieldset>
    <fieldset class="inputs">
        <label for="tip_contacts">联系人</label> <input type="text" id="tip_contacts" name="contacts" placeholder="联系人"
                                                     required>
    </fieldset>
    <fieldset class="inputs">
        <label for="tip_contactWay">联系方式</label> <input type="text" id="tip_contactWay" name="contactWay"
                                                        placeholder="联系方式"
                                                        required>
    </fieldset>
    <fieldset class="inputs">
        <label for="fileToUpload">图片</label> <input id="fileToUpload" type="file" accept="image/*" name="image223"
                                                    placeholder="图片"
            >
    </fieldset>
    <fieldset class="submit2 inputs">
        <input type="button" class="ui-button" id="uploadFileBtn" onclick="ajaxUploadFile(this)" value="上传附件">
    </fieldset>
    <fieldset>
        <input type="hidden" name="pic">
        <img id="previewImage" style="max-width: 800px;" alt="暂无图片显示">
    </fieldset>
    <fieldset class="submit2 inputs">
        <input type="button" onclick="tip.add_tip(this);" class="ui-button" style="margin-top: 5px;"
               value="提交">
    </fieldset>
    <script type="text/javascript">
        com.whuang.hsj.previewLocalDiskImage($('#fileToUpload'), $("#previewImage"));
    </script>
    <%-- <input type="hidden" name="targetId" value="${targetId }"> --%>
</form>

