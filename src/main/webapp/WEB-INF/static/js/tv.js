var $exchangeDiv;
var $slider_carousel;//轮播图,根据情况来隐藏或显示(目前只有首页显示)
var $left_sidebar;
var currentHost = window.location.host;//"localhost:8080" or "http://hbjltv.com/"
var server_url_prefix = null;//最后要有斜杠
var server_url = null;
if (com.whuang.hsj.contains(currentHost, 'hbjltv.com', '123.57.250.51')) {//TODO 这样判断还是不够好
    server_url_prefix = 'http://hbjltv.com/';//公网
    server_url = '';
} else {
    server_url_prefix = "/tv_mobile/";//本地内网
    server_url = "/tv_mobile";
}
//本地:"/tv_mobile/"
var $cboxOverlay = null;
var $loginNav = null;
var $logoutNav = null;
var login_div_z_index = 999;
var subPagePanel_z_index = 99996;
var tips_timeout = 2000;
var $subPagePanelTitle = null;
var subPagePanelTitle_addbbs = "发帖";
var subPagePanelTitle_addbbsReply = "跟帖";
var subPagePanelTitle_liveTV = "直播";
var subPagePanelTitle_broadcast = "点播";
var subPagePanelTitle_comment = "评论";
var subPagePanelTitle_newsDetail = "新闻详情";
var $subPageBBSTitle = null;
var Constant_str_reply_at = "@";
var Constant_str_login = "登录";
var Constant_str_register = "注册";
var Constant_str_submit = "提交";
/***
 * 报料
 * @type {string}
 */
var subPagePanelTitle_tip = "报料";
var $subContent = null;
var $subPageBBS_news = null;


//本地:"/tv_mobile"

$(function () {
    $exchangeDiv = $("#content div.container");//主窗口,通过ajax 替换具体内容
    $slider_carousel = $('#slider');//首页最上面的轮播图
    $left_sidebar = $("#leftSidebar");//首页右边的内容
    com.whuang.hsj.drag("subPagePanel", "h2");//增加拖动对话框的功能
    com.whuang.hsj.drag("subPageBBS", "h2");
    $cboxOverlay = $("#cboxOverlay");//遮罩层
    $loginNav = $("#loginNav");//登录导航
    $logoutNav = $("#logoutNav");//注销导航
    $subPagePanelTitle = $("#subPagePanel h2 label");//对话框的标题
    $subContent = $("#subPagePanel .subContent");//对话框的内容
    $subPageBBSTitle=$("#subPageBBS h2 label");
    var currentTimeStr = new Date().format('yyyy-MM-dd');
    $('#currentTime').text(currentTimeStr);
    /*$(window).bind('beforeunload', function () {
            return '提示信息';
        }
     );*/
    //getIPs(function(ip){console&&console.log(ip);});
});
/***
 * 显示等待遮罩层(转圈)
 */
var showMask= function () {
    if (window.showLoadPanel) {
        showLoadPanel(server_url_prefix + "static/img/loading/progress.gif");
    }
}

function tabSelect(url, aHref, isNav, showslider) {
    showMask();
    if (isNav) {
        $('#nav li').removeClass("current")/*.removeClass("focus ")*/;
    }
    /* if ($slider_carousel.length == 0) {
        $slider_carousel = $('#slider');//首页最上面的轮播图
    }
    if (arguments.length > 3 && showslider) {
        $slider_carousel.show();
        $left_sidebar.show();
    } else {
        $slider_carousel.hide();
        $left_sidebar.hide();
     }*/
    ajaxHtml(url + "&random22=" + Math.random(), $exchangeDiv);//page.js
    if (isNav) {
        $(aHref).parent().addClass("current");
    }
    ui_tab_wh(jQuery);
}

var news = {};
news.query = function () {
    //var options = ajaxOption("orders/viewAll");
    /*var form2=$('#form_page');
     form2.action="/";
     form2.submit();
     */
    showMask();
    formAjaxHtml(server_url + "/news/list?type=2&status=1&targetView=/news/list", $exchangeDiv, $('#form_page'));

};
var currentNewsSort = null;//当前新闻栏目
/**
 * 当前的新闻 数据库ID
 */
var currentNewsId = null;
function tabSelectNewsSort(url, aHref, newsSort) {
    showMask();
    var $news_list_placeholder = $("#news_list_placeholder");
    var $newsSortNav_li = $("#newsSortNav li");

    $newsSortNav_li.removeClass("current");


    ajaxHtml(url + "&random22=" + Math.random()/*+"&sort="+newsSort*/, $news_list_placeholder);//page.js
//	$(aHref).parent().removeClass("not_current");
    $(aHref).parent().addClass("current");
    $("#bbs_right_title span").html("论坛列表");
    if (newsSort != null&&newsSort!=undefined) {
        currentNewsSort = newsSort;
    }
}
//查询新闻的评论列表,用于分页(上一页,下一页)
news.queryReply = function () {
    if (currentNewsId == null) {
        alert('currentNewsId is null');
        return;
    }
    var $news_reply_list = $("#news_reply_list");
    showMask();
    formAjaxHtml(server_url + "/comment/list?targetId=" + currentNewsId + "&targetView=news/reply&target_type=1&status=1&random22=" + Math.random(), $news_reply_list, $('#form_subpage'));
};
//tabSelect('/tv_mobile/news/3?1=1',this,false)
news.ajaxNewsDetail = function (url, currentNewsId2) {
    showMask();
    var $news_list_placeholder = $("#news_list_placeholder");
    ajaxHtml(url + "&random22=" + Math.random(), $news_list_placeholder);//page.js
    if (arguments.length > 1) {
        currentNewsId = currentNewsId2;
    }
    //$("#bbs_right_title span").html("论坛详情");
    $subPagePanelTitle.html(subPagePanelTitle_newsDetail);
};
/***
 * 添加新闻的评论
 * @param newsId
 * @param userToComment
 */
news.addNewsComment = function (newsId, userToComment) {
    //showLoadPanel();
    //bbsId&&(bbs_selectedId=bbsId);
    currentNewsId = newsId;
    var finalUrl =server_url + "/comment/add_front?targetView=news/add_reply&targetId=" + newsId;
    if (arguments.length > 1 && com.whuang.hsj.isHasValue(userToComment)) {
        ajaxSubPanel(finalUrl, function () {
            $("#formNews_reply fieldset textarea[name=comments]").val(Constant_str_reply_at + userToComment + ":");
        });
    } else {
        ajaxSubPanel(finalUrl);
    }
    $subPagePanelTitle.html(subPagePanelTitle_comment);
};
/***
 * self 可能是form本身,也可能是form下面的表单控件(例如input输入框,button)
 * @param self
 * @returns {*}
 */
var getForm = function (self) {
    if (self.tagName.toLowerCase() === 'form') {
        return $(self);
    } else {
        return com.whuang.hsj.getForm(self);
    }
};
news.add_newsReply = function (self) {
    if (currentNewsId == null) {
        $.msgbox.show({
            message: "currentNewsId is null",
            icon: 'warn',
            timeOut: tips_timeout/*,
             beforeHide: function(){
             }*/
        });
        return;
    }
    var $thisForm = null;
    if (arguments.length > 0) {
        $thisForm = getForm(self);
    }
    /*else {
     $thisForm =getForm(self) //$("#formNews_reply");
     }*/
    var textarea_comment=$thisForm.find('textarea[name=comments]');
    var comment_val=textarea_comment.val();
    if(!comment_val){
        $.msgbox.show({
            message: "您还没有填写",
            icon: 'warn',
            timeOut: tips_timeout/*,
             beforeHide: function(){
             }*/
        });
        return;
    }
    showMask();
    var options = {
        url: server_url + "/comment/json_add",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            if (json2.result == 1) {
                //添加帖子之后刷新论坛列表(默认定位第一页)
                /*var url='/tv_mobile/comment/list?status=1';
                 if(bbs_type!=null){
                 url=url+"&type="+bbs_type;
                 }*/
                var $news_reply_list = $("#news_reply_list");
                hideLoadPanel();
                //tabSelect(server_url +'/bbs/list?status=1',this,true);
                ajaxHtml(server_url + "/comment/list?targetId=" + currentNewsId + "&targetView=news/reply&type=1&status=1&random22=" + Math.random(),
                    $news_reply_list);
                $thisForm.get(0).reset();
                closeSubPagePanel();
                $.msgbox.show({
                    message: "添加成功",
                    icon: 'ok',
                    timeOut: tips_timeout/*,e{
                     beforeHide: function(){alert("请先登录");
                     }*/
                });
            } else {
                pleaseLoginFirst();
                hideLoadPanel();
            }
        }
        ,
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };
    //采用Ajax 提交表单,页面不会跳转

    if ($thisForm.length == 0) {
        alert("can not get form ,maybe not pass on form object.");
        return;
    }
    $thisForm.ajaxSubmit(options);
    return false;//如果是true就是普通的表单提交(页面有跳转)
};
news.ajaxNewsDetailOnDialog = function (url,self,newsId) {
    ajaxDetailOnDialogCommon(url,self,newsId,subPagePanelTitle_newsDetail)
};
var user = {};
//会员登录
user.login_submit = function (self) {
    var $username = $('.log_f input[name=username]');
    var $password = $('.log_f input[name=password]');
    if (!$password.grumble) {//也可以是$repassword,只是为了检查是否需要重新执行GrumbleBubbleJquery
        GrumbleBubbleJquery(jQuery, GrumbleBubble);
    }
    if ($username.val() === '') {
        $username.grumble({
            text: '请输入用户名',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    if ($password.val() === '') {
        $password.grumble({
            text: '请输入密码',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var $thisForm = null;
    if (arguments.length > 0) {
        if(self==undefined){
            alert('self is undefined');
            return;
        }
        $thisForm = getForm(self);
    }
    if ($thisForm == null || $thisForm.length == 0) {
        $thisForm = $("#login-content form");
    }
    var $submitBtn = $thisForm.find(".login_sub");
    var options = {
        url: server_url + "/user/login",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            com.whuang.hsj.enAbled($submitBtn.get(0));
            $submitBtn.val(Constant_str_login);
            hideLoadPanel();
            if (json2.result == 1) {
                $.msgbox.show({
                    message: "登录成功",
                    icon: 'ok',
                    timeOut: 2000,
                     beforeHide: function(){
                         location.reload();//刷新页面
                     }
                });
                $thisForm.hide();
                location.reload();//刷新页面
                return;
                if ($('.log_f').css('display') != "none") {
                    $('.log_f').slideUp(300);
                }
                $thisForm.get(0).reset();
            } else {
                //alert("用户名或密码不对");
                $username.grumble({
                    text: '用户名或密码不对',
                    angle: 180,
                    distance: 0,
                    showAfter: 100,
//				type : 'alt-',
                    hideAfter: 2000
                });
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
            hideLoadPanel();
            $submitBtn.val(Constant_str_login);
            com.whuang.hsj.enAbled($submitBtn.get(0));
        }
    };
    //采用Ajax 提交表单,页面不会跳转


    if ($thisForm.length == 0) {
        alert("can not get form ,maybe not pass on form object.");
        return;
    }
    $submitBtn.val("正在登录...");
    com.whuang.hsj.disAbled($submitBtn.get(0));
    showMask();
    if (!$thisForm.ajaxSubmit) {
        formAjaxSubmit(jQuery);
    }
    $thisForm.ajaxSubmit(options);

    //return false;//如果是true就是普通的表单提交(页面有跳转)
};
/***
 * 用户免费注册
 * @param self
 */
user.register = function (self) {
    var $username = $('.reg_f input[name=username]');
    var $password = $('.reg_f input[name=password]');
    if (!$password.grumble) {//也可以是$repassword,只是为了检查是否需要重新执行GrumbleBubbleJquery
        GrumbleBubbleJquery(jQuery, GrumbleBubble);
    }
    var $email = $('.reg_f input[name=email]');
    var $repassword = $('#repassword');//确认密码
    if ($username.val() === '') {
        $username.grumble({
            text: '请输入用户名',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var passwordVal = $password.val();
    if (passwordVal === '') {
        $password.grumble({
            text: '请输入密码',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var repasswordVal = $repassword.val();
    if (repasswordVal === '') {
        $repassword.grumble({
            text: '请输入确认密码',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    if (passwordVal !== repasswordVal) {
        $repassword.grumble({
            text: '两次密码须一致',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    if ($email.val() === '') {
        $email.grumble({
            text: '请输入邮箱',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var $thisForm = null;
    if (arguments.length > 0) {
        $thisForm = getForm(self);
    }
    if ($thisForm == null || $thisForm.length == 0) {
        $thisForm = $("form.reg_f");
    }
    var $submitBtn = $thisForm.find(".login_sub");
    ;
    var options = {
        url: server_url + "/user/register",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            com.whuang.hsj.enAbled($submitBtn.get(0));
            $submitBtn.val(Constant_str_register);
            hideLoadPanel();
            if (json2.result == 1) {
                $.msgbox.show({
                    message: "注册成功",
                    icon: 'ok',
                    timeOut: 2000,
                     beforeHide: function(){
                         location.reload();
                     }
                });
                location.reload();
                //$loginNav.hide();
                //$logoutNav.show();
                $thisForm.get(0).reset();
                if ($login_trigger2.hasClass('active')) {
                    $login_trigger2.trigger('click');
                }
            } else {
                var $tipLocation = $username;
                var erroMessage = null
                if (json2.result == 3) {
                    erroMessage = "用户名不能为空";
                } else if (json2.result == 4) {
                    erroMessage = "密码不能为空";
                    $tipLocation = $password;
                } else if (json2.result == 10) {
                    erroMessage = "用户名已经存在";
                } else if (json2.result == 11) {
                    erroMessage = "邮箱不能为空";
                    $tipLocation = $email;
                }
                //alert("用户名或密码不对");
                $tipLocation.grumble({
                    text: erroMessage,
                    angle: 180,
                    distance: 0,
                    showAfter: 100,
//				type : 'alt-',
                    hideAfter: 2000
                });
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
            hideLoadPanel();
            $submitBtn.val(Constant_str_register);
            com.whuang.hsj.enAbled($submitBtn.get(0));
        }
    };
    //采用Ajax 提交表单,页面不会跳转


    if ($thisForm.length == 0) {
        alert("can not get form ,maybe not pass on form object.");
        return;
    }
    $submitBtn.val("正在注册...");
    com.whuang.hsj.disAbled($submitBtn.get(0));
    showMask();
    if (!$thisForm.ajaxSubmit) {
        formAjaxSubmit(jQuery);
    }
    $thisForm.ajaxSubmit(options);

    //return false;//如果是true就是普通的表单提交(页面有跳转)
};

user.logout = function (self) {
    var errorMessage = null;
    showMask();
    $.ajax({
        url: server_url + "/user/logoutPC",
        type: "POST",
        timeout: 18000,
        dataType: 'json',
        success: function (json2) {
            hideLoadPanel();
            if (json2.result == 1) {
                errorMessage = "注销成功";
                $.msgbox.show({
                    message: errorMessage,
                    icon: 'ok',
                    timeOut: 2000,
                    beforeHide: function () {
                        location.reload();
                    }
                });
                $loginNav.show();
                $('div.header_r').hide();
                $logoutNav.hide();
            } else {
                errorMessage = "注销失败";
                if (json2.result == 21) {
                    errorMessage = "您还没有登录.";
                }
                $.msgbox.show({
                    message: errorMessage,
                    icon: 'no',
                    timeOut: 2000
                });
            }

        },
        error: function (er) {
            hideLoadPanel();
            if (er.statusText == 'timeout') {
                errorMessage = "连接服务器超时！";
            } else {
                errorMessage = er.responseText;
            }
            $.msgbox.show({
                message: errorMessage,
                icon: 'no',
                timeOut: 2000
            });
        }
    });

};
//记住密码
$(function () {
    var $username = $("#username");
    var $password = $("#password");
    var $issavePasswd = $("input[name=issavePasswd][type=checkbox]");

    var usernameVal = null;
    var passwordVal = null;
    usernameVal = com.whuang.hsj.getCookie("username2");
    passwordVal = com.whuang.hsj.getCookie("password2");
    if (com.whuang.hsj.isHasValue(usernameVal)) {
        $username.val(usernameVal);
    }
    if (com.whuang.hsj.isHasValue(passwordVal)) {
        $password.val(passwordVal);
        com.whuang.hsj.setSelectedCheckbox($issavePasswd.get(0));
    }
    console.log(usernameVal);
    console.log(passwordVal);
});
/***
 * 选中论坛栏目时
 * @param url
 * @param aHref
 * @param bbsTypeId
 */
function tabSelectBBSSort(url, aHref, bbsTypeId) {
    showMask();
    var $bbs_list_right = $("#bbs_list_right");
    var $aside_h3 = $("aside a h3");

    $aside_h3.removeClass("current");


    ajaxHtml(url + "&random22=" + Math.random(), $bbs_list_right);//page.js
    $(aHref).find("h3").addClass("current");
    $("#bbs_right_title span").html("论坛列表");
    $("#navHrefComment").hide();
    if (bbsTypeId != null) {
        bbs_type = bbsTypeId;
    }
}
function tabSelectBroadcastSort(url, aHref, bbsTypeId) {
    showMask();
    var $bbs_list_right = $("#broadcast_list_right");
    var $aside_h3 = $("aside a h3");

    $aside_h3.removeClass("current");


    ajaxHtml(url + "&random22=" + Math.random(), $bbs_list_right);//page.js
    $(aHref).find("h3").addClass("current");
    $("#broadcast_right_title span").html("点播列表");
    $("#navHrefComment").hide();
    if (bbsTypeId != null) {
        bbs_type = bbsTypeId;
    }
}
var cardid = null;
var bbs_type = null;//论坛栏目
var bbs_selectedId = null;
function ajaxBBSDetail(url, aHref, cardid2) {
    showMask();
    var $bbs_list_right = $("#bbs_list_right");
    ajaxHtml(url + "&random22=" + Math.random(), $bbs_list_right);//page.js
    if (arguments.length > 2) {
        cardid = cardid2;
    }
    $("#bbs_right_title span").html("论坛详情");
    var $navHrefComment = $("#navHrefComment");
    $navHrefComment.show();
    //if(!$navHrefComment.get(0).onclick){
    $navHrefComment.get(0).onclick = function () {
        bbs.addBBSComment(cardid2);
    };
    //}
}
var bbs = {};
bbs.ajaxBBSDetailOnDialog = function (url,self,newsId) {
    cardid = newsId;
    ajaxDetailOnDialogCommon(url,self,newsId,"帖子详情")
};
ajaxDetailOnDialogCommon = function (url,self,newsId,title2) {
    ajaxSubPanelBBS(url + "&random22=" + Math.random(), function () {
        $("#subPageBBS .subContent").css("height", ($(window).height() - 90) + "px");
    });
    currentNewsId=newsId;
    title2&& $subPageBBSTitle.html(title2);

};
//查询论坛的评论列表
bbs.queryReply = function () {
    if (cardid == null) {
        alert('cardid is null');
        return;
    }
    var $bbs_reply_list = $("#bbs_reply_list");
    showMask();
    formAjaxHtml(server_url + "/reply/list?cardid=" + cardid + "&targetView=bbs/reply&type=1&status=1&random22=" + Math.random(), $bbs_reply_list, $('#form_subpage'));
};
var ajaxSubPanel = function (url, callback) {
    ajaxSubPanelCommon(url, '#subPagePanel', true, $subContent, callback);
};
var ajaxSubPanelBBS = function (url, callback) {
    if ($subPageBBS_news == null || $subPageBBS_news == undefined) {
        $subPageBBS_news = $('#subPageBBS')
    }
    ajaxSubPanelCommon(url, $subPageBBS_news, false, null, callback);
};
/***
 * 通用的弹出窗口显示渲染方法
 * @param url
 * @param subPageId : 包括#
 * @param $subContent2
 */
var ajaxSubPanelCommon = function (url, subPageId, showOverlay/*是否显示modal遮罩层*/, $subContent2, callback/*回调函数*/) {
    showMask();
    var $subPagePanel = null;
    if (typeof  subPageId === "string") {
        $subPagePanel = $(subPageId);
    } else {
        $subPagePanel = subPageId;
    }
    if (arguments.length < 4 || $subContent2 == null) {
        $subContent2 = $subPagePanel.find(".subContent");
    }
    var finalUrl = url + "&random22=" + Math.random();
    var param22 = {};
    param22.jqueryObj = $subContent2;
    param22.isCenter = true;
    param22.$dialogPanel = $subPagePanel;
    if (arguments.length > 4) {
        ajaxHtml(finalUrl, param22, null/*data */, callback);//page.js
    } else {
        ajaxHtml(finalUrl, param22);//page.js
    }

    //$subPagePanel.css("position", 'absolute');//保证下面的语句生效
    //$subPagePanel.css("top", (/*com.whuang.hsj.getScroll().top+*/10) + "px");//弹出panel兼容滚动条
    if (arguments.length > 2 && showOverlay) {
    $cboxOverlay.height($(document).height());
        $("div.controller2").addClass('ui-modal-mask-blur');
    $cboxOverlay.show();
    }
};

bbs.addBBS = function () {
    //showLoadPanel();
    ajaxSubPanel(server_url + "/bbs/add_front?targetView=bbs/add_bbs");
    $subPagePanelTitle.html(subPagePanelTitle_addbbs);

};
bbs.addBBSComment = function (bbsId, userToComment) {
    //showLoadPanel();
    if(bbsId){
    	bbs_selectedId = bbsId;
    	cardid = bbsId;
    }
    $subPagePanelTitle.html(subPagePanelTitle_addbbsReply);
    var finalUrl = server_url + "/reply/add_front?targetView=bbs/add_reply&cardid=" + bbsId;
    if (arguments.length > 1 && com.whuang.hsj.isHasValue(userToComment)) {
        ajaxSubPanel(finalUrl, function () {
            var $textarea_content = $("#formBBS_reply fieldset textarea[name=followcardcontent]");
            $textarea_content.val("@" + userToComment + ":");
            $textarea_content.get(0).focus();
        });
    } else {
        ajaxSubPanel(finalUrl);
    }

};
var closeSubPagePanel = function () {
    var $subPagePanel = $('#subPagePanel');
    //var $subContent = $subPagePanel.find(("#subContent"));
    closeSubPagePanelCommon($subPagePanel, $subContent);
};
var closeSubPageBBS = function () {
    var $subPagePanel = $('#subPageBBS');
    var $subContentBBS = $subPagePanel.find((".subContent"));
    closeSubPagePanelCommon($subPagePanel, $subContentBBS);
};
var closeSubPagePanelCommon = function ($subPagePanel2, $subContent2) {
    if (arguments.length == 1 || $subContent2 == null) {
        $subContent2 = $subPagePanel2.find(".subContent");
    }
    $subContent2.empty();
    $subPagePanel2.hide('normal');
    var form1 = $subContent2.find("form").get(0);
    form1 && form1.reset();//清空表单项
    hideCboxOverlay();
};
/**
 * 发帖
 * @param self
 * @returns {boolean}
 */
bbs.add_bbs = function (self) {
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
        url: server_url + "/bbs/json_add_bbs",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            if (json2.result == 1) {
                //添加帖子之后刷新论坛列表(默认定位第一页)
                var url = server_url + '/bbs/list?status=1';
                if (bbs_type != null) {
                    url = url + "&type=" + bbs_type;
                }
                //tabSelect(server_url + '/bbs/list?status=1', this, false);
                location.reload();
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
bbs.add_bbsReply = function (self) {
    if (cardid == null) {
        alert("cardid is null");
        return;
    }
    var $followcardcontent=$('textarea[name=followcardcontent]');
    if (!$.msgbox) {
        msgBox(jQuery);
    }
    if(!$followcardcontent.val()){
        $.msgbox.show({
            message: "请输入您的评论.",
            icon: 'warn',
            timeOut: 2000,
             beforeHide: function(){
                 //文本域聚焦
                 $followcardcontent.focus();
             }
        });
        return false;
    }
    var $thisForm = null;
    if (arguments.length > 0) {//说明传入了参数self
        $thisForm = getForm(self);
    }
    /*else {
        $thisForm = $("#formBBS_reply");
     }*/
    console.log && console.log("bbs_selectedId:" + bbs_selectedId);
    showMask();

    var options = {
        url: server_url + "/reply/json_add?cardid=" + bbs_selectedId,
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            if (json2.result == 1) {
                //添加帖子之后刷新论坛列表(默认定位第一页)
                var url = server_url + '/bbs/list?status=1';
                if (bbs_type != null) {
                    url = url + "&type=" + bbs_type;
                }
                var $bbs_reply_list = $("#bbs_reply_list");
                hideLoadPanel();
                //tabSelect(server_url +'/bbs/list?status=1',this,true);
                ajaxHtml(server_url + "/reply/list?cardid=" + cardid + "&targetView=bbs/reply&type=1&status=1&random22=" + Math.random(),
                    $bbs_reply_list);
                $.msgbox.show({
                    message: "评论成功",
                    icon: 'ok',
                    timeOut: 2000/*,
                     beforeHide: function(){
                     }*/
                });
                $thisForm.get(0).reset();
                closeSubPagePanel();
            } else if (json2.result == 53) {
                hideLoadPanel();
                $.msgbox.show({
                    message: "请输入您的评论.",
                    icon: 'no',
                    timeOut: tips_timeout/*,
                     beforeHide: function(){
                     }*/
                });
            } else {
                pleaseLoginFirst();
            }
        },
        error: function (er) {
            console.log(er);
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
bbs.query = function () {
    showMask();
    var url = server_url + "/bbs/list?status=1&random22=" + Math.random();
    formAjaxHtml(url, $exchangeDiv, $('#form_page'));
    if (bbs_type != null) {
        url = url + "&type=" + bbs_type;
    }
};
var tip = {};
tip.addTipDialog = function () {
    //showLoadPanel();
    ajaxSubPanel(server_url + "/news/add_front?targetView=tip/add");
    $subPagePanelTitle.html(subPagePanelTitle_tip);
};
tip.add_tip = function (self) {
    editor.sync();
    var $thisForm = getForm(self);
    /*if($thisForm.find("select").get(0).selectedIndex==0){
     alert("请先选择论坛栏目.");
     return false;
     }*/
    var $title = $thisForm.find("input[name=title]");
    var $content = $thisForm.find("textarea[name=content]");
    var $contacts = $thisForm.find("input[name=contacts]");//联系人
    var $contactWay = $thisForm.find("input[name=contactWay]");//联系方式
    var $pic = $thisForm.find("input[name=pic]");//隐藏域的图片
    if ($title.val() === '') {
        alertWarning($title, '请输入标题');
        return;
    }
    if ($content.val() === '') {
        alertWarning($content, '请输入报料内容');
        return;
    }
    if ($contacts.val() === '') {
        alertWarning($contacts, '请输入联系人');
        return;
    }
    if ($contactWay.val() === '') {
        alertWarning($contactWay, '请输入联系方式');
        return;
    }
    if ($pic.val() === '') {
        alertWarning($('#uploadFileBtn'), '请先上传图片');
        return;
    }
    showMask();
    if (!$.msgbox) {
        msgBox(jQuery);
    }
    var options = {
        url: server_url + "/news/json_add_tips",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            self.value = Constant_str_submit;
            if (json2.result == 1) {
                if (com.whuang.hsj.imgURL != null) {
                    URL.revokeObjectURL(com.whuang.hsj.imgURL);//free the memory;
                }
                $tip_list_right = $('#tip_list_right');
                //添加帖子之后刷新论坛列表(默认定位第一页)
                ajaxHtml(server_url + '/news/list?status=1&type=1&targetView=tip/list_common',
                    $tip_list_right);
                $.msgbox.show({
                    message: "添加成功",
                    icon: 'ok',
                    timeOut: tips_timeout/*,
                     beforeHide: function(){
                     }*/
                });
                $thisForm.get(0).reset();
                closeSubPagePanel();
            } else if (json2.result == 40) {
                $title.grumble({
                    text: '请输入标题',
                    angle: 180,
                    distance: 0,
                    showAfter: 100,
//				type : 'alt-',
                    hideAfter: 2000
                });
                hideLoadPanel();
                return;
            } else {
                //alert("请先登录");
                pleaseLoginFirst();
            }
        },
        error: function (er) {
            self.value = Constant_str_submit;
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
    self.value = "正在提交...";
    if (!$thisForm.ajaxSubmit) {
        formAjaxSubmit(jQuery);
    }
    $thisForm.ajaxSubmit(options);
    return false;
};
tip.ajaxTipDetailOnDialog = function (url, self, newsId) {
    ajaxDetailOnDialogCommon(url, self, newsId, "报料详情")
};
tip.query = function () {
    //var options = ajaxOption("orders/viewAll");
    /*var form2=$('#form_page');
     form2.action="/";
     form2.submit();
     */
    showMask();
    formAjaxHtml(server_url + "/news/list?type=1&status=1&targetView=tip/list&random22=" + Math.random(), $exchangeDiv, $('#form_page'));

};
//定时器对象  
var uploadProcessTimer = null;

//获取文件上传进度  
function getFileUploadProcess() {
    $.get('/upload/getFileProcessServlet', function (data) {
        $('#fileUploadProcess').html(data);
    });
}

function ajaxFileUpload() {
    //设置加载图标的显示  
    $('#loading').show();
    uploadProcessTimer = window.setInterval(getFileUploadProcess, 20);

    $.ajaxFileUpload
    ({
        url: '/upload/ajaxUploadServlet',
        secureuri: false,
        fileElementId: 'fileToUpload',
        dataType: 'json',
        data: {title: 'a'},
        success: function (data, status) {
            //清除定时器  
            if (uploadProcessTimer) {
                window.clearInterval(uploadProcessTimer);
            }
            $('#loading').hide();
            var message = data['message'];
            var code = data['code'];
            if (code != 200) {
                $('#fileUploadProcess').html('0%');
            }
            if (message) {
                alert(data.message);
            }
        },
        error: function (data, status, e) {
            //清除定时器  
            if (uploadProcessTimer) {
                window.clearInterval(uploadProcessTimer);
            }
            $('#loading').hide();
            //这里处理的是网络异常，返回参数解析异常，DOM操作异常  
            alert("上传发生异常");
        }
    })

    return false;
}
tip.ajaxTipDetail = function (url, aHref, cardid2) {
    showMask();
    var $tip_list_right = $("#bbs_list");
    ajaxHtml(url + "&random22=" + Math.random() + "&targetView=tip/detail", $tip_list_right);//page.js
    if (arguments.length > 2) {
        currentNewsId = cardid2;
    }
    //$("#tip_list_right span").html("论坛详情");
};
tip.addTipComment = function (newsId) {
    //showLoadPanel();
    //bbsId&&(bbs_selectedId=bbsId);
    ajaxSubPanel(server_url + "/comment/add_front?targetView=news/add_reply&targetId=" + newsId);
};
tip.add_tipReply = function (self) {
    if (currentNewsId == null) {
        alert("currentNewsId is null");
        return;
    }
    var $thisForm = getForm(self);
    showMask();
    if (!$.msgbox) {
        msgBox(jQuery);
    }
    var options = {
        url: server_url + "/comment/json_add",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            if (json2.result == 1) {
                //添加帖子之后刷新论坛列表(默认定位第一页)
                /*var url=server_url +'/comment/list?status=1';
                 if(bbs_type!=null){
                 url=url+"&type="+bbs_type;
                 }*/
                var $tip_reply_list = $("#tip_reply_list");
                hideLoadPanel();
                //tabSelect(server_url +'/bbs/list?status=1',this,true);
                ajaxHtml(server_url + "/comment/list?targetId=" + currentNewsId + "&targetView=tip/reply&type=1&status=1&random22=" + Math.random(),
                    $tip_reply_list);
                $.msgbox.show({
                    message: "添加成功",
                    icon: 'ok',
                    timeOut: 2000/*,
                     beforeHide: function(){
                     }*/
                });
                self.reset();
                closeSubPagePanel();
            } else {
                //alert("请先登录");
                pleaseLoginFirst();
            }
        },
        error: function (er) {
            console.log(er);
            $.msgbox.show({
                message: er.responseText,
                icon: 'warn',
                timeOut: tips_timeout/*,
                 beforeHide: function(){
                 }*/
            });
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
var pleaseLoginFirst = function () {
    if (!$.msgbox) {
        msgBox(jQuery);
    }
    /*$.msgbox.show({
        message: "请先登录",
        icon: 'warn',
        timeOut: (tips_timeout + 1000),
         beforeHide: function(){
             $login_trigger2.trigger('click');//弹出登录窗口
             hideCboxOverlay();
         }
     });*/
    hideLoadPanel();
    alert('请先登录');
};
var hideCboxOverlay = function () {
    $cboxOverlay.hide();
    var $controller2 = $('div.controller2');
    if ($controller2.hasClass('ui-modal-mask-blur')) {
        $controller2.removeClass('ui-modal-mask-blur');
    }
}
var video = {};
video.ajaxTVLive = function (url) {
    showMask();
    var $tip_list_right = $("#lives_list");
    if ($tip_list_right.length == 0) {
        $tip_list_right = $("div.main-content");
        $slider_carousel.hide();
    }
    ajaxSubPanel(url + "&random22=" + Math.random() + "&targetView=lives/live_play", function () {
        $("#subPagePanel .subContent").css("height", ($(window).height() - 90) + "px");
    });
    $subPagePanelTitle.html(subPagePanelTitle_liveTV);
    //video.ajaxBroadcast
};
video.ajaxBroadcast = function (url) {
    showMask();
    ajaxSubPanel(url + "&random22=" + Math.random(), function () {
        $("#subPagePanel .subContent").css("height", ($(window).height() - 90) + "px");
        $("#subPagePanel").show("normal");
    });
    $subPagePanelTitle.html(subPagePanelTitle_broadcast);
    $("#subPagePanel").show("normal");
};
video.query = function () {
    showMask();
    var url = server_url + "/video/list?status=1&type=1&random22=" + Math.random();
    formAjaxHtml(url, $exchangeDiv, $('#form_page'));
};
video.ajaxBroadCastDetailOnDialog = function (url,self,newsId) {
    ajaxDetailOnDialogCommon(url,self,newsId,"点播详情")
};
var pic = {};
pic.query = function () {
    //showLoadPanel(server_url_prefix+"static/images/loading/progress.gif");
    var url = server_url + "/pic_news/list2?targetView=/pic_news/list";
    formAjaxHtml(url, $exchangeDiv, $('#form_page'));

};
var anchorGoWhere = function (anchorName) {
    anchorGoWhereCommon($("#subPageBBS .subContent"), anchorName);
    //$("html,body").animate({scrollTop: $("#box").offset().top}, 1000);
};

var ios_waiting = function (self) {
    /*$(self).grumble( {//TODO
     text : '敬请期待',
     angle : 180,
     distance : 0,
     showAfter : 100,
     type : 'alt-',
     hideAfter : 100000
     });*/
    $.msgbox.show({
        message: "敬请期待",
        icon: 'ok',
        timeOut: tips_timeout/*,
         beforeHide: function(){
         }*/
    });
};

var ajaxUploadFile = function (self) {
    var $thisForm = getForm(self);
    var $uploadFile = $thisForm.find('input[type=file]');
    if (!com.whuang.hsj.isHasValue($uploadFile.val())) {
        alert("请选择要上传的文件.");
        return false;
    }
    var param = {};
    param.formatTypeInvalid = "您上传的格式不正确，仅支持jpg、jpeg、png、gif、bmp,请重新选择！";
    param.url = server_url + '/upload/upload?uploadFolder=WEB-INF/static/img/uploadimgs';
    param.success = function (data, status) {
        if (data && data.fullUrl) {
            $("input[name=pic]").val(data.relativePath);
            alert("上传成功");
        } else {
            alert("服务器故障，稍后再试！");
        }
    };
    param.error = function (data, status, e) {
        alert(e);
    };
    com.whuang.hsj.ajaxUploadFile($uploadFile.get(0).id/*'fileToUpload'*/, param);
};
var ajaxUploadAPK = function (self) {
    var $thisForm = getForm(self);
    var $uploadFile = $thisForm.find('input[type=file]');
    if (!com.whuang.hsj.isHasValue($uploadFile.val())) {
        alert("请选择要上传的文件.");
        return false;
    }
    var param = {};
    param.isValidateExt=false;
    //param.formatTypeInvalid = "您上传的格式不正确，仅支持jpg、jpeg、png、gif、bmp,请重新选择！";
    param.url = server_url + '/upload/upload?uploadFolder=upload/download/apk&needMD5=need';
    param.success = function (data, status) {
        if (data && data.fullUrl) {
            $("input[name=path]").val(data.fileName);
            $("textarea[name=md5]").val(data.md5);
            alert("上传成功");
        } else {
            alert("服务器故障，稍后再试！");
        }
    };
    param.error = function (data, status, e) {
        alert(e);
    };
    com.whuang.hsj.ajaxUploadFile($uploadFile.get(0).id/*'fileToUpload'*/, param);
};

var alertWarning = function ($title, message2) {
    $title.grumble({
        text: message2,
        angle: 180,
        distance: 0,
        showAfter: 100,
//				type : 'alt-',
        hideAfter: 2000
    });
};
/***
 * 访问统计数据
 * @param statisticsType
 * @param callback
 */
var ajaxStatistics = function (statisticsType, callback, canvasChartLineId, canvasChartBarId) {
    var options = {
        url: server_url + "/statistics/statis",
        type: "POST",
        dataType: 'json',
        data: {'statisticsType': statisticsType},
        success: function (json2) {
            if (callback) {
                callback(json2, statisticsType, canvasChartLineId, canvasChartBarId);
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };
    $.ajax(options);
};

var statistics = {};
//statistics.lineChartData = ;
var getLineChartData = function () {
    return {
        datasets: [
            {
                label: "活跃数",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)"
            },
            {
                label: "激活量",
                fillColor: "rgba(255,187,205,0.2)",
                strokeColor: "rgba(255,187,205,1)",
                pointColor: "rgba(255,187,205,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(255,187,205,1)"
            }
        ]

    };
}
statistics.barChartDayData = null;

statistics.lineChartDayWeek = null;
statistics.barChartDayWeek = null;

statistics.lineChartDayMonth = null;
statistics.barChartDayMonth = null;
var getbarChartData = function () {
    return {
        datasets: [
            {
                label: "活跃数",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                highlightFill: "rgba(220,220,220,0.75)",
                highlightStroke: "rgba(220,220,220,1)"
            },
            {
                label: "激活量",
                fillColor: "rgba(255,187,205,0.2)",
                strokeColor: "rgba(255,187,205,1)",
                highlightFill: "rgba(255,187,205,0.75)",
                highlightStroke: "rgba(255,187,205,1)"
            }
        ]

    };
}
var callbackDay = function (json22, statisticsType, canvasChartLineId, canvasChartBarId) {

    console.log(json22);
    var statisDataByDay = json22.statisDataByDay;
    var statisActivationDataByDay = json22.statisActivationDataByDay;
    var length = statisDataByDay.length;
    var chartDataByDay = [];
    var chartActivationDataByDay = [];
    var labels = [];
    for (var i = 0; i < length; i++) {
        var count_date = statisDataByDay[i];
        chartDataByDay.push(count_date[0]);
        labels.push(count_date[1]);
        var count_Activationdate = statisActivationDataByDay[i];
        if (count_Activationdate === undefined) {
            chartActivationDataByDay.push(0);
        } else {
            chartActivationDataByDay.push(count_Activationdate[0]);
        }
    }
    var lineChartData = getLineChartData();
    var barChartData = getbarChartData();
    lineChartData.labels = labels;
    lineChartData.datasets[0].data = chartDataByDay;
    lineChartData.datasets[1].data = chartActivationDataByDay;

    barChartData.labels = labels;
    barChartData.datasets[0].data = chartDataByDay;
    barChartData.datasets[1].data = chartActivationDataByDay;
    var canvasChartLine = document.getElementById(canvasChartLineId);
    var ctxLine = canvasChartLine.getContext("2d");
    var canvasChartBar = document.getElementById(canvasChartBarId);
    var ctxBar = canvasChartBar.getContext("2d");
    window.myLine = new Chart(ctxLine).Line(lineChartData, {
        responsive: true
    });
    statistics.barChartDayData = barChartData;
    //var myBarChart = new Chart(ctxBar).Bar(barChartData, {barShowStroke: true});
};

var callbackWeekMonth = function (json22, statisticsType /*canvasChartLineId, canvasChartBarId*/) {
    console.log(json22);
    var statisDataByDay = json22.counts;
    var statisActivationDataByDay = json22.countsActivation;
    var length = statisDataByDay.length;
    var chartDataByDay = [];
    var chartActivationDataByDay = [];
    var labels = [];
    for (var i = 0; i < length; i++) {
        var count_date = statisDataByDay[i];
        chartDataByDay.push(count_date['count']);
        labels.push(count_date['endDay']);
        var count_Activationdate = statisActivationDataByDay[i];
        if (count_Activationdate === undefined) {
            chartActivationDataByDay.push(0);
        } else {
            chartActivationDataByDay.push(count_Activationdate['count']);
        }
    }
    var lineChartData = getLineChartData();
    var barChartData = getbarChartData();
    lineChartData.labels = labels;
    lineChartData.datasets[0].data = chartDataByDay;
    lineChartData.datasets[1].data = chartActivationDataByDay;

    barChartData.labels = labels;
    barChartData.datasets[0].data = chartDataByDay;
    barChartData.datasets[1].data = chartActivationDataByDay;
    //var canvasChartLine = document.getElementById(canvasChartLineId);
    //var ctxLine = canvasChartLine.getContext("2d");
    //var canvasChartBar = document.getElementById(canvasChartBarId);
    //var ctxBar = canvasChartBar.getContext("2d");
    //window.myLine = new Chart(ctxLine).Line(lineChartData, {
    //    responsive: true
    //});
    if (statisticsType == 2) {
        statistics.lineChartDayWeek = lineChartData;
        statistics.barChartDayWeek = barChartData;
    } else {
        statistics.lineChartDayMonth = lineChartData;
        statistics.barChartDayMonth = barChartData;
    }

    //var myBarChart = new Chart(ctxBar).Bar(barChartData, {barShowStroke: true});
};

function clone(myObj) {
    if (typeof(myObj) != 'object') return myObj;
    if (myObj == null) return myObj;
    var myNewObj = new Object();
    for (var i in myObj) {
        if (myObj.hasOwnProperty(i)) {
            myNewObj[i] = clone(myObj[i]);
        }
    }
    return myNewObj;
}
function cloneObj(oldObj) { //复制对象方法
    if (typeof(oldObj) != 'object') return oldObj;
    if (oldObj == null) return oldObj;
    var newObj = new Object();
    for (var i in oldObj) {
        if (oldObj.hasOwnProperty(i)) {
            newObj[i] = cloneObj(oldObj[i]);
        }
    }
    return newObj;
};
function extendObj() { //扩展对象
    var args = arguments;
    if (args.length < 2) return;
    var temp = cloneObj(args[0]); //调用复制对象方法
    for (var n = 1; n < args.length; n++) {
        for (var i in args[n]) {
            if (args[n].hasOwnProperty(i)) {
            temp[i] = args[n][i];
            }
        }
    }
    return temp;
}
var admin = {};
admin.login = function (self) {
    var $username = $('#adminLogin input[name=username]');
    var $password = $('#adminLogin input[name=password]');
    if (!$password.grumble) {//也可以是$repassword,只是为了检查是否需要重新执行GrumbleBubbleJquery
        GrumbleBubbleJquery(jQuery, GrumbleBubble);
    }
    if ($username.val() === '') {
        $username.grumble({
            text: '请输入用户名',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    if ($password.val() === '') {
        $password.grumble({
            text: '请输入密码',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var $thisForm = null;
    if (arguments.length > 0) {
        $thisForm = getForm(self);
    }
    if ($thisForm == null || $thisForm.length == 0) {
        $thisForm = $("#adminLogin");
    }
    var $submitBtn = $thisForm.find("input[type=button]");
    var options = {
        url: server_url + "/admin/login",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            com.whuang.hsj.enAbled($submitBtn.get(0));
            $submitBtn.val(Constant_str_login);
            $("#subPagePanel").hide();
            hideLoadPanel();
            closeSubPagePanel();
            if (json2.result == 1) {
                $.msgbox.show({
                    message: "授权成功",
                    icon: 'ok',
                    timeOut: 2000/*,
                     beforeHide: function(){
                     }*/
                });
                $loginNav.hide();
                $logoutNav.show();
                $thisForm.get(0).reset();
                needAdminLogin = false;
                tabSelect(server_url + '/news/list?targetView=statistics/list&type=1&status=1&sort=1', this, false);
                anchorGoIndexTop('index_top');
            } else {
                //alert("用户名或密码不对");
                $username.grumble({
                    text: '用户名或密码不对',
                    angle: 180,
                    distance: 0,
                    showAfter: 100,
//				type : 'alt-',
                    hideAfter: 2000
                });
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
            hideLoadPanel();
            $submitBtn.val(Constant_str_login);
            com.whuang.hsj.enAbled($submitBtn.get(0));
        }
    };
    //采用Ajax 提交表单,页面不会跳转


    if ($thisForm.length == 0) {
        alert("can not get form ,maybe not pass on form object.");
        return;
    }
    $submitBtn.val("正在授权...");
    com.whuang.hsj.disAbled($submitBtn.get(0));
    showMask();
    if (!$thisForm.ajaxSubmit) {
        formAjaxSubmit(jQuery);
    }
    $thisForm.ajaxSubmit(options);

};
var version = {};
version.add = function (self) {
    var $username = $('#adminLogin input[name=username]');
    var $password = $('#adminLogin input[name=password]');
    if (!$password.grumble) {//也可以是$repassword,只是为了检查是否需要重新执行GrumbleBubbleJquery
        GrumbleBubbleJquery(jQuery, GrumbleBubble);
    }
    if ($username.val() === '') {
        $username.grumble({
            text: '请输入用户名',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    if ($password.val() === '') {
        $password.grumble({
            text: '请输入密码',
            angle: 180,
            distance: 0,
            showAfter: 100,
//				type : 'alt-',
            hideAfter: 2000
        });
        return;
    }
    var $thisForm = null;
    if (arguments.length > 0) {
        $thisForm = getForm(self);
    }
    if ($thisForm == null || $thisForm.length == 0) {
        $thisForm = $("#adminLogin");
    }
    var $submitBtn = $thisForm.find("input[type=button]");
    var options = {
        url: server_url + "/version/add",
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            com.whuang.hsj.enAbled($submitBtn.get(0));
            $submitBtn.val(Constant_str_login);
            //$("#subPagePanel").hide();
            //hideLoadPanel();
            //closeSubPagePanel();
            if (json2.result == 1) {
                $.msgbox.show({
                    message: "添加成功",
                    icon: 'ok',
                    timeOut: 2000/*,
                     beforeHide: function(){
                     }*/
                });
                alert('添加成功');
               // $loginNav.hide();
                //$logoutNav.show();
                $thisForm.get(0).reset();
            } else {
                //alert("用户名或密码不对");
                $username.grumble({
                    text: '用户名或密码不对',
                    angle: 180,
                    distance: 0,
                    showAfter: 100,
//				type : 'alt-',
                    hideAfter: 2000
                });
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
            //hideLoadPanel();
            //$submitBtn.val(Constant_str_login);
            //com.whuang.hsj.enAbled($submitBtn.get(0));
        }
    };
    //采用Ajax 提交表单,页面不会跳转


    if ($thisForm.length == 0) {
        alert("can not get form ,maybe not pass on form object.");
        return;
    }
    $submitBtn.val("正在添加...");
    com.whuang.hsj.disAbled($submitBtn.get(0));
    //showMask();
    if (!$thisForm.ajaxSubmit) {
        formAjaxSubmit(jQuery);
    }
    $thisForm.ajaxSubmit(options);

};