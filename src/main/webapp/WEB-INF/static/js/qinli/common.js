$(function () {

    var browser = (function () {
        var ua = navigator.userAgent.toLowerCase(), sys = null, s;
        (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? sys = {type: 'ie', version: s[1]} :
            (s = ua.match(/msie ([\d.]+)/)) ? sys = {type: 'ie', version: s[1]} :
                (s = ua.match(/firefox\/([\d.]+)/)) ? sys = {type: 'firefox', version: s[1]} :
                    (s = ua.match(/chrome\/([\d.]+)/)) ? sys = {type: 'chrome', version: s[1]} :
                        (s = ua.match(/opera.([\d.]+)/)) ? sys = {type: 'opera', version: s[1]} :
                            (s = ua.match(/version\/([\d.]+).*safari/)) ? sys = {type: 'safari', version: s[1]} : 0;
        sys.isMobile = !!ua.match(/AppleWebKit.*Mobile.*/) || !!ua.match(/(iPhone|iPod|Android|ios|iPad)/i);
        return sys;
    })();

    /*检测IE*/
    if (browser.type == "ie" && browser.version < 8) {
        location.href = "update2.html";
    }

    /*判断谷歌27*/
    if (browser.type == 'chrome' && browser.version <= 27) {
        $('.font_scale8, .font_scale10').addClass('font_adjust');
    }

    /*======返回顶部======*/
    /*$(window).scroll(function() {
     var scroH = $(this).scrollTop();
     if (scroH > 150)
     $('#to_top').fadeIn(300);
     else
     $('#to_top').fadeOut(300);
     })
     $('#to_top').click(function(){
     $('body,html').animate({scrollTop:0},300);
     })*/


})