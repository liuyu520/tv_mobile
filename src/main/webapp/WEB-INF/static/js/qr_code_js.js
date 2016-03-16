lastScrollY = 0;
var graySrc = false;
var InterTime = 1;
var maxWidth = -1;
var minWidth = -151;
var numInter = 8;
var BigInter;
var SmallInter;
var o = null;
var i = 0;
kuzhan = function (id, _top, _left) {
    var me = id.charAt ? document.getElementById(id) : id, d1 = document.body, d2 = document.documentElement;
    d1.style.height = d2.style.height = '100%';
    me.style.top = _top ? _top + 'px' : 0;
    me.style.left = _left + "px";
    me.style.position = 'absolute';
    me.style.display = 'block';
    setInterval(function () {
        me.style.top = parseInt(me.style.top) + (Math.max(d1.scrollTop, d2.scrollTop) + _top - parseInt(me.style.top)) * 0.1 + 'px';
    }, 10 + parseInt(Math.random() * 20));
    return arguments.callee;
};
$(document).ready(function () {
    var html = '';
    html += '<div id="kuzhan" style="z-index:20;display:none;" onmouseover="toBig()" onmouseout="toSmall()">';
    html += '    <div class="services">';
    html += '        <div class="con" style="margin-left:2px;">';
    html += '        	<div style="color: #f00"  >android客户端下载地址</div>  <img  style="width:200px;" src="http://hbjltv.com/static/img/qrcode.png" > ';

    html += '        </div>';
    html += '    </div>';
    html += '    <a href="" title="android客户端下载地址" class="wx" target="_blank">android客户端下载地址</a>';
    html += '    <div class="Obtn"  onmouseover=toBig()  ></div>';
    html += '</div>';

    $(document.body).append(html);

    o = document.getElementById("kuzhan");
    i = parseInt(o.style.left);

    kuzhan('kuzhan', 210, -152);
});

function Big() {
    console.log("mouseover");
    if (parseInt(o.style.left) < maxWidth) {
        i = parseInt(o.style.left);
        i += numInter;
        o.style.left = i + "px";
        if (i == maxWidth)
            clearInterval(BigInter);
    }
    if (!graySrc) {
        $(o).find("img").each(function () {
            $(this).attr("src", $(this).attr("Original"));
        });
        graySrc = true;
    }
}
function toBig() {
    console.log("mouseover");
    clearInterval(SmallInter);
    clearInterval(BigInter);
    BigInter = setInterval(Big, InterTime);
}
function Small() {
    if (parseInt(o.style.left) > minWidth) {
        i = parseInt(o.style.left);
        i -= numInter;
        o.style.left = i + "px";

        if (i == minWidth)
            clearInterval(SmallInter);
    }
}
function toSmall() {
    clearInterval(SmallInter);
    clearInterval(BigInter);
    SmallInter = setInterval(Small, InterTime);

}