/***
 * get browser type
 * @param jQuery
 */
var checkBrowser = function (jQuery) {

    if (jQuery.browser) return;

    jQuery.browser = {};
    jQuery.browser.mozilla = false;
    jQuery.browser.webkit = false;
    jQuery.browser.opera = false;
    jQuery.browser.msie = false;

    var nAgt = navigator.userAgent;
    jQuery.browser.name = navigator.appName;
    jQuery.browser.fullVersion = '' + parseFloat(navigator.appVersion);
    jQuery.browser.majorVersion = parseInt(navigator.appVersion, 10);
    var nameOffset, verOffset, ix;

// In Opera, the true version is after "Opera" or after "Version"
    if ((verOffset = nAgt.indexOf("Opera")) != -1) {
        jQuery.browser.opera = true;
        jQuery.browser.name = "Opera";
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 6);
        if ((verOffset = nAgt.indexOf("Version")) != -1)
            jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
    }
// In MSIE, the true version is after "MSIE" in userAgent
    else if ((verOffset = nAgt.indexOf("MSIE")) != -1) {
        jQuery.browser.msie = true;
        jQuery.browser.name = "Microsoft Internet Explorer";
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 5);
    }
// In Chrome, the true version is after "Chrome"
    else if ((verOffset = nAgt.indexOf("Chrome")) != -1) {
        jQuery.browser.webkit = true;
        jQuery.browser.name = "Chrome";
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 7);
    }
// In Safari, the true version is after "Safari" or after "Version"
    else if ((verOffset = nAgt.indexOf("Safari")) != -1) {
        jQuery.browser.webkit = true;
        jQuery.browser.name = "Safari";
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 7);
        if ((verOffset = nAgt.indexOf("Version")) != -1)
            jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
    }
// In Firefox, the true version is after "Firefox"
    else if ((verOffset = nAgt.indexOf("Firefox")) != -1) {
        jQuery.browser.mozilla = true;
        jQuery.browser.name = "Firefox";
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
    }
// In most other browsers, "name/version" is at the end of userAgent
    else if ((nameOffset = nAgt.lastIndexOf(' ') + 1) <
        (verOffset = nAgt.lastIndexOf('/'))) {
        jQuery.browser.name = nAgt.substring(nameOffset, verOffset);
        jQuery.browser.fullVersion = nAgt.substring(verOffset + 1);
        if (jQuery.browser.name.toLowerCase() == jQuery.browser.name.toUpperCase()) {
            jQuery.browser.name = navigator.appName;
        }
    }
// trim the fullVersion string at semicolon/space if present
    if ((ix = jQuery.browser.fullVersion.indexOf(";")) != -1)
        jQuery.browser.fullVersion = jQuery.browser.fullVersion.substring(0, ix);
    if ((ix = jQuery.browser.fullVersion.indexOf(" ")) != -1)
        jQuery.browser.fullVersion = jQuery.browser.fullVersion.substring(0, ix);

    jQuery.browser.majorVersion = parseInt('' + jQuery.browser.fullVersion, 10);
    if (isNaN(jQuery.browser.majorVersion)) {
        jQuery.browser.fullVersion = '' + parseFloat(navigator.appVersion);
        jQuery.browser.majorVersion = parseInt(navigator.appVersion, 10);
    }
    jQuery.browser.version = jQuery.browser.majorVersion;
};
checkBrowser(jQuery);
var GrumbleBubbleInit = function (a, k) {
    var l = {
        type: "",
        text: "",
        top: 0,
        left: 0,
        angle: 45,
        size: 50,
        distance: 50,
        template: '<div class="grumble" style="display:none;filter:progid:DXImageTransform.Microsoft.Matrix(sizingMethod=\'auto expand\')">&#160;</div>',
        textTemplate: '<div class="grumble-text" style="display:none;"><div class="outer"><div class="inner">{text}</div></div></div>',
        context: null
    };
    k.GrumbleBubble = function (j) {
        this.options = a.extend({}, l, j);
        this.context = a(this.options.context || a("body"));
        this.css = {};
        this.create()
    };
    k.GrumbleBubble.prototype =
    {
        create: function () {
            var j = k.GrumbleBubble.prototype.tmpl;
            this.bubble = a(j(this.options.template));
            this.text = a(j(this.options.textTemplate, {text: this.options.text}));
            this.prepare()
        }, setBubbleRotation: function () {
        this.rotateDeg = this.options.angle - 45;
        this.rotateDeg < 0 && (this.rotateDeg += 360)
    }, prepare: function () {
        var j = this.bubble.get(0).parentNode;
        this.setBubbleRotation();
        this.applyStyles();
        j !== this.context && this.append();
        this.rotate()
    }, applyStyles: function () {
        this.setPosition();
        this.css.width = this.options.size;
        this.css.height = this.options.size;
        this.text.css(this.css).addClass("grumble-text" + this.options.size);
        this.bubble.css(this.css).addClass(this.options.type + "grumble" + this.options.size);
        this.realLeft = this.css.left;
        this.realTop = this.css.top
    }, setPosition: function () {
        var j = this.options.angle / -360, a = this.options.size / 2, f = this.options.size * this.options.size, f = Math.sqrt(f + f) / 2, d = this.options.left - a - Math.sin(j * 2 * Math.PI) * (this.options.distance + f);
        this.css.top = this.options.top + a - Math.cos(j * 2 * Math.PI) * (this.options.distance +
        f) - this.options.size;
        this.css.left = d
    }, append: function () {
        var a = this.context;
        this.bubble.appendTo(a);
        this.text.appendTo(a)
    }, rotate: function () {
        a.browser && a.browser.msie === !0 && k.document.documentMode < 10 ? this.ieRotate() : this.cssRotate()
    }, cssRotate: function () {
        this.bubble.css({
            "-moz-transform": "rotate(" + this.rotateDeg + "deg)",
            "-webkit-transform": "rotate(" + this.rotateDeg + "deg)",
            "-o-transform": "rotate(" + this.rotateDeg + "deg)",
            transform: "rotate(" + this.rotateDeg + "deg)"
        })
    }, ieRotate: function () {
        var a = this.rotateDeg * (Math.PI *
            2 / 360), h = Math.cos(a), a = Math.sin(a), f = this.bubble.get(0);
        f.filters.item(0).M11 = h;
        f.filters.item(0).M12 = -a;
        f.filters.item(0).M21 = a;
        f.filters.item(0).M22 = h;
        h = this.bubble.width();
        a = this.bubble.height();
        this.bubble.css({
            left: this.css.left - (h - this.options.size) / 2,
            top: this.css.top - (a - this.options.size) / 2
        })
    }, adjust: function (j) {
        a.extend(this.options, j);
        this.prepare()
    }, tmpl: function (a, h, f) {
        for (var d in h)h[d] === null && (h[d] = ""), typeof h[d] === "object" && h[d].length && (h[d] = h[d].join(", ")), a = a.replace(RegExp("{" +
        d + "}", "g"), f ? escape(h[d]) : h[d]);
        return a
    }
    }
};
GrumbleBubbleInit($, window);
var GrumbleBubbleJquery = function (a, k) {
    function l(a, d) {
        var e, c;
        d ? (c = Number(a.css("margin-top").replace("px", "")) || 0, e = a.position(), e.top += c + d.scrollTop() + a.height()) : (e = a.offset(), e.top += a.height());
        e.left += a.width() / 2;
        return e
    }

    function j(f, d, e) {
        var c = a('<div style="position:absolute;visibility:hidden;width:' + f + 'px;">' + e + "</div>").appendTo(a(document.body)), h = c.outerHeight() * 2 + f * 0.2, b = a.inArray(f, d);
        c.remove();
        return h >= f && d[++b] ? j(d[b], d, e) : f
    }

    var h = [];
    if (!a.browser) {
        checkBrowser(jQuery);
    }
    a.fn.grumble = function (f, d) {
        //console.log(f);
        var that2 = this;//added by whuang
        if (that2.focus) {//added by whuang
            that2.focus();
        }
        return typeof f === "string" ? (this.trigger({
            type: f +
            ".bubble", adjustments: d
        }), this) : this.each(function () {
            var e = a(this), c = a.extend({}, a.fn.grumble.defaults, f, e.data("grumble") || {}), d = j(c.size, c.sizeRange, c.text), b, g, i, n, m;
            c.useRelativePositioning && (m = e.offsetParent());
            n = l(e, m);
            c.top = n.top;
            c.left = n.left;
            if (a.data(this, "hazGrumble"))return e.grumble("adjust", f), e.grumble("show"), !0;
            /*else a.data(this, "hazGrumble", !0);*///modified by whuang
            i = {
                init: function () {
                    b = new k({
                        text: c.text,
                        top: c.top,
                        left: c.left,
                        angle: c.angle,
                        size: d,
                        distance: c.distance,
                        type: c.type,
                        context: m
                    });
                    c.hasHideButton &&
                    this.addButton();
                    h.push({
                        grumble: b, button: g, onHide: function () {
                            i.isVisible = !1;
                            a(document.body).unbind("click.bubble");
                            i.doOnBeginHideCallback();
                            i.doOnHideCallback()
                        }
                    });
                    this.showBubble();
                    this.prepareEvents()
                }, addButton: function () {
                    var o = k.prototype.tmpl;
                    g = a(o(c.buttonTemplate, {hideText: c.buttonHideText})).css({
                        left: b.realLeft + d - 10,
                        top: b.realTop + d - 10
                    }).insertAfter(b.text)
                }, rePositionButton: function () {
                    g && g.css({left: b.realLeft + d - 10, top: b.realTop + d - 10})
                }, createFxQueue: function () {
                    b.bubble.queue("fx");
                    b.text.queue("fx");
                    b.bubble.delay(c.showAfter);
                    b.text.delay(c.showAfter);
                    g && g.delay(c.showAfter)
                }, showBubble: function () {
                    i.isVisible != !0 && (c.showAfter && i.createFxQueue(), a.browser.msie === !0 ? (b.bubble.queue("fx", function (a) {
                        b.bubble.show();
                        a()
                    }), b.text.queue("fx", function (a) {
                        b.text.show();
                        a()
                    }), g && g.queue("fx", function (a) {
                        g.show();
                        a()
                    })) : (b.bubble.fadeTo("fast", 1), b.text.fadeTo("fast", 1), g && g.fadeTo("fast", 1)), b.bubble.queue("fx", function (a) {
                        i.isVisible = !0;
                        (c.hideOnClick || c.hasHideButton) && i.hideOnClick();
                        i.doOnShowCallback();
                        a()
                    }), c.hideAfter && i.hideBubble())
                }, hideBubble: function () {
                    b.bubble.delay(c.hideAfter);
                    b.text.delay(c.hideAfter);
                    b.bubble.queue("fx", function (a) {
                        i.doOnBeginHideCallback();
                        a()
                    });
                    a.browser.msie === !0 ? (b.bubble.queue("fx", function (a) {
                        b.bubble.hide();
                        a()
                    }), b.bubble.queue("fx", function (a) {
                        b.text.hide();
                        a()
                    }), g && g.queue("fx", function (a) {
                        g.hide();
                        a()
                    })) : (b.bubble.fadeOut(), b.text.fadeOut(), g && g.fadeOut());
                    b.bubble.queue("fx", function (a) {
                        i.isVisible = !1;
                        i.doOnHideCallback();
                        a()
                    })
                }, doOnBeginHideCallback: function () {
                    c.onBeginHide(b,
                        g)
                }, doOnHideCallback: function () {
                    c.onHide(b, g)
                }, doOnShowCallback: function () {
                    c.onShow(b, g)
                }, hideOnClick: function () {
                    setTimeout(function () {
                        var c = function () {
                            i.hideBubble(b, g);
                            a(document.body).unbind("click.bubble", c)
                        };
                        a(document.body).bind("click.bubble", c)
                    }, 1E3)
                }, prepareEvents: function () {
                    a(window).bind("resize.bubble", function () {
                        var a;
                        a = l(e, m);
                        b.adjust({top: a.top, left: a.left});
                        i.rePositionButton()
                    });
                    e.bind("hide.bubble", function () {
                        i.hideBubble(b, g)
                    });
                    e.bind("adjust.bubble", function (a) {
                        a.adjustments &&
                        typeof a.adjustments === "object" && b.adjust(a.adjustments)
                    });
                    e.bind("show.bubble", function () {
                        i.showBubble(b, g)
                    });
                    e.bind("delete.bubble", function () {
                        b.bubble.hide().remove();
                        b.text.hide().remove();
                        g && g.hide().remove();
                        for (var a = h.length, c = 0; c < a; c++)if (b === h[c].grumble) {
                            h.splice(c, 1);
                            break
                        }
                        e.removeData("hazGrumble")
                    })
                }
            };
            i.init()
        })
    };
    a.fn.grumble.defaults = {
        text: "",
        angle: 45,
        size: 50,
        sizeRange: [50, 100, 150, 200],
        distance: 0,
        type: "",
        useRelativePositioning: !1,
        showAfter: 0,
        hideAfter: !1,
        hideOnClick: !1,
        hasHideButton: !1,
        buttonTemplate: '<div class="grumble-button" style="display:none" title="{hideText}">x</div>',
        buttonHideText: "Hide",
        onHide: function () {
        },
        onShow: function () {
        },
        onBeginHide: function () {
        }
    };
    a(document).bind("keyup.bubble", function (f) {
        f.keyCode === 27 && a.each(h, function (a, e) {
            e.grumble.bubble.clearQueue().hide();
            e.grumble.text.clearQueue().hide();
            e.button && e.button.clearQueue().hide();
            e.onHide()
        })
    })
};
GrumbleBubbleJquery(jQuery, GrumbleBubble);