$(function () {
    $('.login_btn').click(function (event) {
        $('.reg_f').css('display', 'none');
        if ($('.log_f').css('display') == "none") {
            $('.log_f').slideDown(300);
        } else {
            $('.log_f').slideUp(300);
        }
        var e = event || window.event;
        e.stopPropagation();
    });

    $('.reg_btn').click(function (event) {
        $('.log_f').css('display', 'none');
        if ($('.reg_f').css('display') == "none") {
            $('.reg_f').slideDown(300);
        } else {
            $('.reg_f').slideUp(300);
        }
        var e = event || window.event;
        e.stopPropagation();
    });

    $(document).click(function (event) {
        if (com.whuang.hsj.isInDiv(event, $('.log_f').get(0), false)) {
            $('.reg_f').css('display', 'none');
            $('.log_f').css('display', 'none');
        }

    });
    var params = getQueryParams();
    var news_sort = params.sort;
    if (news_sort || typeof news_sort === "number") {
        $('#type_' + news_sort).addClass('current');
    }
    $('.header_r').hover(function (event) {
        console.log('click');
        var $ul = $('.welcome ul');
        $ul.toggle('normal');
    })
});
window.onload = function () {
    var iframeHtml = '<iframe name="sinaWeatherTool"' +
        'src="http://weather.news.sina.com.cn/chajian/iframe/weatherStyle0.html?city=监利"' +
        'width="200" height="20" marginwidth="0" marginheight="0" hspace="0" vspace="0"' +
        'frameborder="0" scrolling="no"></iframe>';
    $('.header').prepend(iframeHtml);
};