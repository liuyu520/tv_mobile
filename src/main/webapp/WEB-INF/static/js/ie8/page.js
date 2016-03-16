/**
 * Created by Administrator on 2015/6/24.
 */
var CONST_STATIC_RESOURCE_URL = 'http://www.static.chanjet.com';
var CONST_GOV_SERVER_URL = 'http://i.chanjet.com';

jQuery.support.cors = true;
if (window.console === undefined) {
    console = {
        log: function () {
        }, info: function () {
        }, debug: function () {
        }
    };
}
/***
 * 动态加载javascript 脚本文件
 * @param url
 * @param callback
 */
function loadJs(url, callback) {
    var done = false;
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.language = 'javascript';
    script.charset = "utf-8";
    script.src = url;
    //script.setAttribute('src', url);
    script.onload = script.onreadystatechange = function () {
        if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
            done = true;
            script.onload = script.onreadystatechange = null;
            if (callback) {
                callback.call(script);
            }
        }
    };
    document.getElementsByTagName("head")[0].appendChild(script);
};
/***
 * 使用 Ajax 动态加载javascript 脚本文件
 * @param url
 * @param callback
 */
function xhrLoadJS(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('get', url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status >= 200 && xhr.status < 300 || xhr.status == 304/*页面未修改*/) {
                var script = document.createElement('script');
                script.type = 'text/script';
                script.charset = "utf-8";
                script.text = xhr.responseText;
                eval(xhr.responseText);  // 执行代码
                document.body.appendChild(script);
                if (callback && typeof callback === 'function') {
                    //callback();
                    callback.call(script);
                }
            }
        }
    };
    xhr.send(null);
}

/***
 * 更新主要div dom
 * @param $jqueryDiv
 * @param html2 : ajax请求返回的html页面
 * @param templateHandle
 * @param templateData
 */
function updateHtml($jqueryDiv, html2, templateHandle, templateData) {
    if (arguments.length > 2 && templateHandle && typeof templateHandle === 'function') {
        html2 = templateHandle(html2, templateData);
    }
    if (html2) {
        $jqueryDiv.html(html2);
    } else {
        console.log('html is undefined.');
    }

}

var xhr = {
    get: function (apiAdrress, data, success, error) {
        if (!apiAdrress) {
            return false;
        }
        if (!startWith(apiAdrress, CONST_GOV_SERVER_URL)) {
            apiAdrress = CONST_GOV_SERVER_URL + apiAdrress;
        }
        $.ajax({
            url: apiAdrress,
            type: 'GET',
            data: data,
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    if (success && typeof success === 'function') {
                        success(data);
                    }
                } else {
                    error && error(data);
                }
            },
            error: error
        })
    },

    post: function (apiAdrress, data, success, error) {
        if (!apiAdrress) {
            return false;
        }
        if (!startWith(apiAdrress, CONST_GOV_SERVER_URL)) {
            apiAdrress = CONST_GOV_SERVER_URL + apiAdrress;
        }
        $.ajax({
            url: apiAdrress,
            type: 'POST',
            data: data,
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    if (success && typeof success === 'function') {
                        success(data);
                    }
                } else {
                    error && error(data);
                }
            },
            error: error
        })
    },
    /***
     * 请求静态html 模板
     * @param url
     * @param $jqueryDiv : 四个主要div之一
     * @param templateHandle : 自定义,用于使用Dot js模板函数
     * @param callback : 用于实现模板之后,绑定事件
     * @param templateData : cia的返回数据
     */
    ajaxHtml: function (url, $jqueryDiv, templateHandle, callback, templateData) {
        xhr.ajaxHtmlCommon(url, $jqueryDiv, null, templateHandle, callback, templateData);
    },
    /***
     * ajax 请求静态html文件
     * @param url
     * @param $jqueryDiv
     * @param data
     * @param callback : 回调函数,updateHtml 方法之后执行
     */
    ajaxHtmlCommon: function (url, $jqueryDiv, requestData, templateHandle, callback, templateData) {
        var argument_length = arguments.length;
        var isHasCallback = (argument_length > 4 && callback && typeof callback === 'function');
        var options22 = {
            url: url,
            type: "GET",
            timeout: 18000,
            dataType: 'html',
            success: function (html) {
                updateHtml($jqueryDiv, html, templateHandle, templateData);
                /* var $formInput = jqueryObj.find('textarea:first');//让subContent 中的textarea聚焦
                 if ($formInput.length != 0) {//先判断能不能获取到textarea
                 $formInput.get(0).focus();
                 }*/
                if (isHasCallback) {
                    callback($jqueryDiv, html);
                }
            },
            error: function (er) {
                if (er.statusText == 'timeout') {
                    updateHtml($jqueryDiv, "<div style='color: red'>连接服务器超时！</div>");
                } else {
                    var errorMessage2;
                    if (er.responseText) {
                        errorMessage2 = er.responseText;
                    } else {
                        errorMessage2 = er.statusText;
                    }
                    console.log('error:' + errorMessage2 + ' , status code:' + er.status);
                    updateHtml($jqueryDiv, errorMessage2, templateHandle, templateData);
                }
                if (isHasCallback) {
                    callback($jqueryDiv, er);
                }
            }
        };
        if (argument_length > 2 && requestData != null && requestData != undefined) {
            options22.data = requestData;
            options22.type = "POST";
        }
        $.ajax(options22);
    }
};

var startWith = function (str, regex) {
    if (regex == undefined || str == undefined) {
        return false;
    }
    return str.indexOf(regex) == 0;
};
var formAjaxHtml = function (url, jqueryObj, form) {
    var options = {
        url: url,
        type: "POST",
        dataType: 'html',
        success: function (html) {
            //  	jqueryObj.html(html);
            // hideLoadPanel();
            updateHtml(jqueryObj, html)
        },
        error: function (er) {
            alert(er);
            alert(er.responseText);
        }
    };
    //采用Ajax 提交表单,页面不会跳转
    if (form.length == 0) {
        alert("没有找到表单");
        return;
    }
    if (form && form.ajaxSubmit) {
        form.ajaxSubmit(options);
    }
};
var objectUtil = {
    clone: function (src) {
        var target = {};
        for (var i in src) {
            if (typeof src[i] === 'object') {
                target[i] = arguments.callee(src[i]);

            } else {
                target[i] = src[i];
            }
        }
        return target;
    },
    extend: function (fn, option) {
        var fnPrototype = fn.prototype;
        for (var i in option) {
            if (typeof option[i] === 'object') {
                fnPrototype[i] = clone(option[i]);

            } else {
                fnPrototype[i] = option[i];
            }
        }
    }
};
var getForm = function (formElement) {
    var $that = $(formElement).parent();
    var max = 6;//limit the depth
    var fieldsetElement = null;//form element
    var tagName = null;//html tag name
    while ((fieldsetElement = $that.get(0)) && fieldsetElement.tagName !== undefined
    && (tagName = fieldsetElement.tagName.toLowerCase()) !== 'form' && max > 0) {
        if (tagName === 'fieldset') {//html5 new tag
            $that = $(fieldsetElement.form);
            break;
        }
        $that = $that.parent();
        max--;
    }
    //console.log(max);
    return $that;
};
var locationUtil = {
    /***
     * make dialog in center
     */
    centerJQueryPos: function ($div22, isApplyVertical, isIncludeScroll) {
        var width = $div22.width();
        var height = $div22.height();


        var left = (getInner().width - width) / 2 + com.whuang.hsj.getScroll().left;
        var param = {'left': left};
        if (arguments.length === 1 || isApplyVertical) {//Vertical direction
            var top = (getInner().height - height) / 2;
            if (isIncludeScroll) {
                top = top + com.whuang.hsj.getScroll().top;
            }
            param['top'] = top;
        }
        $div22.css(param);
    },//centerJQueryPos
    centerX: function ($div22) {
        centerJQueryPos($div22, false, false);
    },
    centerXY: function ($div22) {
        centerJQueryPos($div22, true, false);
    }
}
var getInner = (function () {
    // alert(typeof window.innerWidth !== 'undefined');
    if (typeof window.innerWidth !== 'undefined') {//Notice:'undefined' is right
        return function () {
            return {
                width: window.innerWidth,
                height: window.innerHeight
            }
        }
    } else {
        return function () {
            return {
                width: document.documentElement.clientWidth,
                height: document.documentElement.clientHeight
            }
        }
    }
})();
/***
 format date or time
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "H+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
var eventUtil = {
    /***
     * [0-9]<br>
     *     12:ok;1.2:error
     * @param event
     * @returns {boolean}
     */
    onlyIntegerKeyPress: function onlyIntegerKeyPress(event) {
        event = event || window.event || arguments.callee.caller.arguments[0];
        //console.log(event);
        var charCode2;
        if ('charCode' in event) {//IE7 and IE8 no charCode
            charCode2 = event.charCode;
        } else {
            //console.log('no charCode');
            charCode2 = event.keyCode;
        }
        //console.log(charCode2);
        if (event.keyCode === 8/*back*/ || event.keyCode === 13/*Enter*/ || event.keyCode === 9/*Tab*/ || event.keyCode === 37/*<- */ || event.keyCode === 39/* ->*/) {
            return true;
        } else if (charCode2 < 48 || charCode2 > 57) {/*0-9*/
            event.returnValue = false;
            return false;
        } else {
            return true;
        }
    },
    /***
     * [0-9] or .
     * @param event
     * @returns {boolean}
     * @constructor
     */
    onlyNumberKeyPress: function (event) {
        event = event || window.event || arguments.callee.caller.arguments[0];
//        console.log(event);
        var charCode2;
        if ('charCode' in event) {//IE7 and IE8 no charCode
            charCode2 = event.charCode;
        } else {
            //console.log('no charCode');
            charCode2 = event.keyCode;
        }
        if (event.keyCode === 8/*back*/ || event.keyCode === 13/*Enter*/ || event.keyCode === 9/*Tab*/ || event.keyCode === 37/*<- */ || event.keyCode === 39/* ->*/) {
            return true;
        } else if ((charCode2 < 48 || charCode2 > 57) && charCode2 !== 46/*.*/) {
            event.returnValue = false;
            return false;
        } else {
            return true;
        }
    },
    /***
     * [a-zA-Z]
     * @param event
     * @returns {boolean}
     */
    onlyAlphaKeyPress: function (event) {
        event = event || window.event || arguments.callee.caller.arguments[0];
//        console.log(event);
        var charCode2;
        if ('charCode' in event) {//IE7 and IE8 no charCode
            charCode2 = event.charCode;
        } else {
            //console.log('no charCode');
            charCode2 = event.keyCode;
        }
        //console.log(charCode2);
        if (event.keyCode === 8/*back*/ || event.keyCode === 13/*Enter*/ || event.keyCode === 9/*Tab*/ || event.keyCode === 37/*<- */ || event.keyCode === 39/* ->*/) {
            return true;
        } else if (charCode2 < 65 || ( charCode2 > 90 && charCode2 < 97) || charCode2 > 122) {
            event.returnValue = false;
            return false;
        } else {
            return true;
        }
    },

    onlyAlphaNumberKeyPress: function (event) {
        event = event || window.event || arguments.callee.caller.arguments[0];
//        console.log(event);
        var charCode2;
        if ('charCode' in event) {//IE7 and IE8 no charCode
            charCode2 = event.charCode;
        } else {
            //console.log('no charCode');
            charCode2 = event.keyCode;
        }
        if (event.keyCode === 8/*back*/ || event.keyCode === 13/*Enter*/ || event.keyCode === 9/*Tab*/ || event.keyCode === 37/*<- */ || event.keyCode === 39/* ->*/) {
            return true;
        } else if (charCode2 < 48 || ( charCode2 > 57 && charCode2 < 65) || ( charCode2 > 90 && charCode2 < 97) || charCode2 > 122) {
            event.returnValue = false;
            return false;
        } else {
            return true;
        }
    },

    getEventKeyCode: function getEventKeyCode(event) {
        event = event || window.event || arguments.callee.caller.arguments[0];
        var charCode2;
        if ('charCode' in event) {//IE7 and IE8 no charCode
            charCode2 = event.charCode;
        } else {
            //console.log('no charCode');
            charCode2 = event.keyCode;
        }
        return charCode2;
    },
    /*
     * 只能输入数字,可以有小数点
     *
     */
    onlyNumberKeyUp: function (event) {
        if (event === undefined) {
            event = window.event;
        }
        var obj = event.srcElement ? event.srcElement : event.target;
        var pattern = /[^\d\.]/ig;
        if (pattern.test(obj.value)) {
            var i = getCursortPosition(event);
            obj.value = obj.value.replace(pattern, '');
            setCaretPosition(event, i);
        }
    }
};
/*******************************************************************************
 * 获取光标位置
 *
 * @param ctrl
 * @returns {Number}
 */
getCursortPosition = function (event) {// 获取光标位置函数
    if (event === undefined || event === null) {
        event = arguments.callee.caller.arguments[0] || window.event;
    }
    var obj = event.srcElement ? event.srcElement : event.target;
    var CaretPos = 0;	// IE Support
    if (document.selection) {
        obj.focus();
        var Sel = document.selection.createRange();
        Sel.moveStart('character', -obj.value.length);
        CaretPos = Sel.text.length;
    } else if (obj.selectionStart || obj.selectionStart == '0') {
        // Firefox support
        CaretPos = obj.selectionStart;
    }

    return (CaretPos);
};

/*******************************************************************************
 * 设置光标位置
 *
 * @param ctrl
 * @returns {Number}
 */
setCaretPosition = function (event, pos) {// 设置光标位置函数
    if (event === undefined || event === null) {
        event = arguments.callee.caller.arguments[0] || window.event;
    }
    var obj = event.srcElement ? event.srcElement : event.target;
    if (pos > 0) {
        pos = pos - 1;//因为把不匹配的字符删除之后,光标会往后移动一个位置
    }
    if (obj.setSelectionRange) {
        obj.focus();
        obj.setSelectionRange(pos, pos);
    } else if (obj.createTextRange) {
        var range = obj.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
};
trim = function (str) { //
    if (typeof str === "object") {
        return str;
    }
    if (str == null || str == "" || str == undefined) {
        return str;
    }
    if (typeof str === "number") {
        return str;
    }
    return str.replace(/(^\s*)|(\s*$)/g, "");
};