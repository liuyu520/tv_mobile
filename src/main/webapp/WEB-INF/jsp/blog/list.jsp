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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客信息</title>
</head>
<body>
<div id="add_div">
    <form class="bbs-send-form form" id="formAddBBS" onsubmit="return bbs.add_bbs(this);">

        <fieldset class="inputs" >
            <label for="bbs_title">标题</label>
            <input type="text" id="bbs_title" name="title" placeholder="帖子标题" required>
        </fieldset>
        <fieldset  class="inputs" >
            <label for="info">主体内容</label>
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
            <textarea id="info" rows="40" name="info" placeholder="主体内容" required></textarea>
        </fieldset>
        <fieldset class="submit2 inputs" >
            <input type="button" onclick="add_blogInfo(this);" class="ui-button" style="margin-top: 5px;" value="发送">
        </fieldset>
        <script type="text/javascript">
            $(function () {
                $(".bbs-send-form input[name=title]").get(0).focus();
            });
        </script>
    </form>
</div>
<div>
    <div>
        <input type="button" value="添加" onclick="addAction();" />
    </div>
    <div class="" id="bbs_list_right" >
        <jsp:include page="./list_common.jsp" />
    </div>
</div>
<script type="text/javascript"
        src="<%=path%>/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/common_util.js"></script>

<script type="text/javascript" src="<%=path%>/static/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/page.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/msgbox.js"></script>
<script type="text/javascript">
    var addAction= function () {
        $('#add_div').show();
    };

    add_blogInfo = function (self) {
        editor.sync();
        var $thisForm = null;
        if (arguments.length > 0) {//说明传入了参数self
            $thisForm = getForm(self);
        }
        /*else {
         $thisForm = $("#formAddBBS");
         }*/
        if (!$.msgbox) {
            msgBox(jQuery);
        }
        if ($thisForm.find("select").get(0).selectedIndex == 0) {
            $.msgbox.show({
                message: "请先选择论坛栏目.",
                icon: 'warn',
                timeOut: tips_timeout/*,
                 beforeHide: function(){
                 }*/
            });
            return false;
        }
        showMask();
        if (!$.msgbox) {
            msgBox(jQuery);
        }
        var options = {
            url: "bs/json_add_bbs",
            type: "POST",
            dataType: 'json',
            success: function (json2) {
                if (json2.result == 1) {
                    //添加帖子之后刷新论坛列表(默认定位第一页)
                    var url = server_url + '/bbs/list?status=1';
                    if (bbs_type != null) {
                        url = url + "&type=" + bbs_type;
                    }
                    tabSelect(server_url + '/bbs/list?status=1', this, false);
                    $.msgbox.show({
                        message: "添加成功",
                        icon: 'ok',
                        timeOut: 2000/*,
                         beforeHide: function(){
                         }*/
                    });
                    $thisForm.get(0).reset();
                    closeSubPagePanel();
                } else {
                    //alert("请先登录");
                    pleaseLoginFirst();
                    hideLoadPanel();
                    $login_trigger2.trigger('click');
                    hideCboxOverlay();
                    //$("#login-content").css("z-index",(subPagePanel_z_index+1));

                }
            },
            error: function (er) {
                console.log(er);
                if(er.status==401){
                    pleaseLoginFirst();
                    hideLoadPanel();
                    $login_trigger2.trigger('click');
                    hideCboxOverlay();
                    return;
                }
                alert(er.responseText);
            }
        };
        //采用Ajax 提交表单,页面不会跳转

        if ($thisForm.length == 0) {
            alert("can not get form ,maybe not pass on form object.");
            return;
        }
        $thisForm.ajaxSubmit(options);
        return false;
    };

</script>
</body>
</html>