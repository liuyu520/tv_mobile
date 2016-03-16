/**
 * Created by huangweii on 2015/5/25.
 * see @ http://blog.csdn.net/hw1287789687/article/details/46654719
 */
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
        word: '',
        color: '#ccc',
        evtType: 'focus',
        maxLen: 0, /*文本框可输入的最长字符个数,0表示无限制  */
        minLen: 0, /*文本框输入的最短字符个数,0表示无限制  */
        normalFontColor: undefined, /* 正常输入文字时的字体颜色 */
        errorBorderClass: undefined,
        keyup_callback: undefined,
        blur_callback: undefined,
        focus_callback: undefined,
        click_callback: undefined,
        keydown_callback: undefined
    }, option);

    function bootstrap($that) {
        // some alias
        var word = settings.word;
        var color = settings.color;
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
                if (evtType == 'focus' && settings.focus_callback && typeof settings.focus_callback === 'function') {
                    e = e || window.event || arguments.callee.caller.arguments[0];
                    settings.focus_callback(e);
                }
            }).bind('blur', function (e) {
                var txt = $that.val();
                if (txt == '') {
                    switchStatus(false)
                }
                if (settings.blur_callback && typeof settings.blur_callback === 'function') {
                    e = e || window.event || arguments.callee.caller.arguments[0];
                    settings.blur_callback(e);
                }
                if (settings.minLen !== 0 && settings.errorBorderClass !== undefined && txt.length < settings.minLen) {
                    //文本框的字符个数不满足最小要求
                    $that.addClass(settings.errorBorderClass);
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
        var maxLengthDeal = function (maxLength2) {
            if (typeof maxLength2 === 'number' && maxLength2 !== 0) {
                $that.blur();
                var val = $that.val();
                $that.focus();
                if (val.length > maxLength2) {
                    $that.val(val.substr(0, maxLength2));
                    $that.focus();
                }
            }
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
                settings.keydown_callback(e);
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
                settings.keyup_callback(e);
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
                settings.click_callback(e);
            }
        })
    }

    return this.each(function () {
        var $elem = $(this)
        bootstrap($elem);
        if ($.isFunction(callback)) callback($elem)
    })
};
