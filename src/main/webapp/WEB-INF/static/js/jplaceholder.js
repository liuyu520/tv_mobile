/**
 * Created by huangweii on 2015/5/25.
 * see @ http://blog.csdn.net/hw1287789687/article/details/46654719
 */
if (window.console === undefined) {
    console = {
        log: function () {
        }, info: function () {
        }, debug: function () {
        }
    };
}
$.fn.setCursorPosition = function (option) {
    var settings = $.extend({
        index: 0
    }, option)
    return this.each(function () {
        var elem = this
        var val = elem.value
        var len = val.length
        var index = settings.index

        // 非input和textarea直接返回
        var $elem = $(elem)
        if (!$elem.is('input,textarea')) return
        // 超过文本长度直接返回
        if (len < index) return

        setTimeout(function () {
            elem.focus()
            if (elem.setSelectionRange) { // 标准浏览器
                elem.setSelectionRange(index, index)
            } else { // IE9-
                var range = elem.createTextRange()
                range.moveStart("character", -len)
                range.moveEnd("character", -len)
                range.moveStart("character", index)
                range.moveEnd("character", 0)
                range.select()
            }
        }, 10)
    })
};
var trim = function (str) { //
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

var startsWith = function (str, regex) {
    if (regex == undefined || str == undefined || (!str) || (!regex)) {
        return false;
    }
    return str.indexOf(regex) == 0;
};
/***
 * convert Decimal str into hex(must be two bit,eg:02,f5)<br>
 *     '153'-->99
 * @param str
 */
var to2Hex = function to2Hex(str) {
    var hex = parseInt(str).toString(16);
    if (hex.length === 1) {
        hex = '0' + hex;
    }
    return hex;
};
var cssColor2Hex = function (cssColor) {
    var stringObj = cssColor.replace(/RGB[\s]*\(([\w,\s]+)\)[\s]*/i, "$1");
    //console.log(stringObj);
    var arr = stringObj.split(',');
    var r = trim(arr[0]);
    var g = trim(arr[1]);
    var b = trim(arr[2]);
    var rHex = to2Hex(r);
    var gHex = to2Hex(g);
    var bHex = to2Hex(b);
    return (rHex + gHex + bHex);
};
/***
 *
 * @param hexColor : #ccc
 * @param cssColor : [string]rgb(153, 153, 153)
 * @returns {boolean}
 */
var compareColor = function compareColor(hexColor/*#789*/, cssColor/*rgb(153, 153, 153)*/) {
    if (typeof cssColor !== 'string') {
        return false;
    }
    if (hexColor === cssColor) {//IE8,jquery.css('color') will get '#ddd',but 'rgb(204, 204, 204)'
        return true;
    }
    if (startsWith(hexColor, '#')) {
        hexColor = hexColor.substr(1);//delete '#' in front
    }
    if (hexColor.length == 3) {//'789'-->'778899'
        hexColor = hexColor.substr(0, 1) + hexColor.substr(0, 1)
            + hexColor.substr(1, 1) + hexColor.substr(1, 1) + hexColor.substr(2, 1) + hexColor.substr(2, 1);
    }
    var cssResult = cssColor2Hex(cssColor);
    return (cssResult === hexColor);
};
/**
 * PlaceHolder组件
 * $(input).placeholder({
 *   word:     // @string 提示文本
 *   color:    // @string 文本颜色
 *   evtType:  // @string focus|keydown 触发placeholder的事件类型
 * })
 *
 * NOTE：
 *   evtType默认是focus，即鼠标点击到输入域时默认文本消失，keydown则模拟HTML5 placeholder属性在Firefox/Chrome里的特征，光标定位到输入域后键盘输入时默认文本才消失。
 *   此外，对于HTML5 placeholder属性，IE10+和Firefox/Chrome/Safari的表现形式也不一致，因此内部实现不采用原生placeholder属性
 */
$.fn.placeholder = function (option, callback) {
    var settings = $.extend({
        word: '', /*默认提示语,即文本框默认显示文字,例如"请输入密码" */
        color: '#ccc', /*默认提示语的字体颜色,即默认显示文字的颜色 */
        evtType: 'focus', /* 表示聚焦时默认提示语才消失 */
        maxLen: 0, /*文本框可输入的最长字符个数,0表示无限制  */
        minLen: 0, /*文本框输入的最短字符个数,0表示无限制  */
        normalFontColor: undefined, /* 正常输入文字时的字体颜色 */
        errorBorderClass: undefined, /*发生错误时生效的class*/
        keyup_callback: undefined,
        blur_callback: undefined, /*失效焦点的回调函数*/
        focus_callback: undefined, /*获得焦点的回调函数*/
        click_callback: undefined, /*单击事件的回调函数*/
        keydown_callback: undefined,
        isNumber: undefined, /* 是否是数字 */
        numberDefaultValue: '0', /*如果是数字,该属性才有效*/
        necessary: false, /*是否是必需,若是必需,则不能为空 */
        parentInputFocusClass: undefined/* 文本框父元素的class,默认为"inputFocus",用于文本框聚焦时,×一定显示,css形如.inputFocus i.inputClearBtn{
         display: block;
         } */,
        isAdapterInputclean: false/*默认值为false,不支持inputclean,只有当为true时上述参数parentInputFocusClass 才有效*/
    }, option);


    /***
     * 判断是否是默认提示语
     * @returns {boolean}
     */
    var isDefaultPlaceHolder = function ($that) {
        var txt = $that.val();
        if (txt == settings.word) {
            var color2 = $that.css('color');
            if (compareColor(settings.color, color2)) {//判断此刻输入框字体的颜色是不是placehold的颜色(灰色)
                return true;
            }
        }
        return false;
    };

    function bootstrap($that) {
        // some alias
        var word = settings.word;
        $that.data('placeholder_val_custom', word);//用于判断当前文本框中的内容是否是placeholder
        $that.data('placeholder_color_custom', settings.color);//用于判断当前文本框中的内容是否是placeholder
        var color = settings.color;
        /* 默认提示语的字体颜色 */
        var evtType = settings.evtType;
        if (settings.maxLen) {
            $that.attr('maxlength', settings.maxLen);
        }
        // default
        var defColor = $that.css('color');//正常情况下,字体的颜色
        if (settings.normalFontColor) {
            defColor = settings.normalFontColor;
        }
        var defVal = $that.val();

        if (defVal == '' || defVal == word) {
            $that.css({color: color}).val(word);
        } else {
            $that.css({color: defColor});
        }
        if (defVal == '') {//jianrong IE
            defVal = word;
        }
        function switchStatus(isDef) {
            if (isDef) {
                $that.val('').css({color: defColor});
//                    console.log('归零');
            } else {
                $that.val(word).css({color: color})
            }
        }

        function addClass2($obj, class2) {
            if (!$obj.hasClass(class2)) {
                $obj.addClass(class2);
            }
        }
        function asFocus() {
            $that.bind(evtType, function (e) {
                var txt = $that.val();
                if (txt == word) {
                    var color2 = $(this).css('color');
//                        console.log(typeof color2);
//                        console.log('settings:'+settings.color);
//                        console.log('color2:'+color2);
                    if (compareColor(settings.color, color2)) {//判断此刻输入框字体的颜色是不是placehold的颜色(灰色)
//                            console.log(color2);//rgb(204, 204, 204)
                        switchStatus(true);
                    }
                } else {//文本框中的值不是默认文本
                    $(this).css('color', defColor);
                }
                if (settings.errorBorderClass !== undefined) {//必须设置了settings.errorBorderClass,才执行removeClass
                    $that.removeClass(settings.errorBorderClass);
                }
                if (settings.isAdapterInputclean && settings.parentInputFocusClass) {
                    var $li = $that.parent();
                    if (!$li.hasClass(settings.parentInputFocusClass)) {
                        $li.addClass(settings.parentInputFocusClass);
                    }
                }
                if (settings.necessary) {
                    if (!txt) {
                        if ($that.hasClass(settings.errorBorderClass)) {
                            $that.removeClass(settings.errorBorderClass);
                        }
                    }
                }
                if (evtType == 'focus' && settings.focus_callback && typeof settings.focus_callback === 'function') {
                    e = e || window.event || arguments.callee.caller.arguments[0];
                    settings.focus_callback(e, this);
                }
                //聚焦时,若文本框中的值是settings.numberDefaultValue ,则清空文本框
                if (settings.isNumber !== undefined && settings.isNumber && settings.numberDefaultValue) {
                    if ($that.val() && $that.val() == settings.numberDefaultValue) {
                        $that.val('');
                    }
                }
            }).bind('blur', function (e) {
                var txt = $that.val();
                if (txt == '') {
                    //失去焦点时,若文本框中的值为空,则设置文本框值为settings.numberDefaultValue
                    if (settings.isNumber !== undefined && settings.isNumber && settings.numberDefaultValue) {
                        $that.val(settings.numberDefaultValue);
                    } else {
                    switchStatus(false)
                }
                }
                if (settings.isAdapterInputclean && settings.parentInputFocusClass) {
                    var $li = $that.parent();
                    $li.removeClass(settings.parentInputFocusClass);
                }
                if (settings.necessary) {
                    if (!txt) {
                        if (!$that.hasClass(settings.errorBorderClass)) {
                            $that.addClass(settings.errorBorderClass);
                        }
                    }
                }
                if (settings.blur_callback && typeof settings.blur_callback === 'function') {
                    e = e || window.event || arguments.callee.caller.arguments[0];
                    settings.blur_callback(e, this);
                }
                if (settings.minLen !== 0 && settings.errorBorderClass !== undefined && txt.length < settings.minLen) {
                    //文本框的字符个数不满足最小要求
                    if (!$that.hasClass(settings.errorBorderClass)) {
                    $that.addClass(settings.errorBorderClass);
                }
                }
            })
        }

        function asKeydown() {
            $that.bind('focus', function () {
                var elem = $that[0];
                var val = $that.val();
                if (val == word) {
                    setTimeout(function () {
                        // 光标定位到首位
                        $that.setCursorPosition({index: 0})
                    }, 10)
                }
            })
        }

        if (evtType == 'focus') {
            asFocus();
        } else if (evtType == 'keydown') {
            asKeydown();
        }
        /***
         * true:禁止默认事件
         * <br>false:不用禁止默认事件
         * @param maxLength2
         * @returns {boolean}
         */
        var maxLengthDeal = function (maxLength2) {
            if (typeof maxLength2 === 'number' && maxLength2 !== 0) {
                $that.blur();
                var val = $that.val();
                if (isDefaultPlaceHolder($that)) {
                    $that.focus();
                    return false;
                }
                $that.focus();
                if (val.length > maxLength2) {
                    $that.val(val.substr(0, maxLength2));
                    $that.focus();
                    return true;
                }
            }
            return false;
        };
        // keydown事件里处理placeholder
        $that.keydown(function (e) {
            var val = $that.val();
            if (val == word) {
                var color2 = $(this).css('color');
                if (compareColor(settings.color, color2)) {//判断此刻输入框字体的颜色是不是placehold的颜色(灰色)
                    switchStatus(true);
                }
            }
            maxLengthDeal(settings.maxLen);
            if (settings.keydown_callback && typeof settings.keydown_callback === 'function') {
                e = e || window.event || arguments.callee.caller.arguments[0];
                settings.keydown_callback(e, this);
            }

        }).keyup(function (e) {
            //var val = $that.val();
            //if (val == '') {
            //switchStatus(false);//@2015-05-28
            //$that.setCursorPosition({index: 0});
            //}
            maxLengthDeal(settings.maxLen);
            if (settings.keyup_callback && typeof settings.keyup_callback === 'function') {
                e = e || window.event || arguments.callee.caller.arguments[0];
                settings.keyup_callback(e, this);
            }
        }).keypress(function () {
            //console.log('keypress');
            if (maxLengthDeal(settings.maxLen)) {
                event.returnValue = false;
                return false;
            }
        });
        $that.bind('click', function () {
            var val = this.value;
//                console.log('defVal:' + defVal);
            if (val && val === defVal) {
                var color2 = $(this).css('color');
//                    console.log(typeof color2);
                if (compareColor(settings.color, color2)) {
                    $that.setCursorPosition({index: 0});
//                        console.log(color2);//rgb(204, 204, 204)
                    switchStatus(true);
                }

            }
            if (settings.click_callback && typeof settings.click_callback === 'function') {
                e = e || window.event || arguments.callee.caller.arguments[0];
                settings.click_callback(e, this);
            }
        })
    }

    return this.each(function () {
        var $elem = $(this)
        bootstrap($elem);
        if ($.isFunction(callback)) callback($elem)
    })
};
(function ($) {
    /***
     * 用于适配placeholder插件
     * @param v
     * @returns {*}
     */
    $.fn.textVal = function () {
        var defaultValue = this.data('placeholder_val_custom');
        var val = this.val();
        if (val && defaultValue && defaultValue == val) {
            var placeholderColor = this.data('placeholder_color_custom');
            var color2 = $(this).css('color');
            if (compareColor(placeholderColor, color2)) {
                return '';
            }
        }
        //$('#myDiv').html(this.data('placeholder_color_custom'));
        return val;
    };
    /***
     * 清空应用了placeholder插件的文本框
     * @returns {jQuery}
     */
    $.fn.clearVal = function () {
        this.each(function () {//this.value.length;
            var $that = $(this);
            var defaultValue = $that.data('placeholder_val_custom');
            var placeholderColor = $that.data('placeholder_color_custom');
            var val = $that.val();
            if (val) {
                if (defaultValue && placeholderColor) {
                    var color2 = $($that).css('color');
                    if (!compareColor(placeholderColor, color2)) {
                        $that.css({color: placeholderColor}).val(defaultValue);
                    }
                } else {
                    $that.val('');
                }
            }
        });//each

        return this;
    }
})(jQuery);