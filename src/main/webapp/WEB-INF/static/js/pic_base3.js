function getCookie(e) {
    var t = document.cookie.match("(^|;) ?" + e + "=([^;]*)(;|$)");
    return t ? t[2] : null
}
function setCookie(e, t, o) {
    var i = new Date;
    i.setTime(i.getTime() + 936e5 * (o || 365)), document.cookie = e + "=" + t + ";path=/;expires=" + i.toGMTString()
}
function wopen(e, t, o, i) {
    i = i || "", t = t || 640, o = o || 350;
    var n = (window.screenLeft ? window.screenLeft : window.screenX) + ww / 2 - t / 2, r = (window.screenTop ? window.screenTop : window.screenY) + wh / 2 - o / 2 - 100;
    return 0 >= r && (r = 20), window.open(e, i, "menubar=no,toolbar=no,resizable=yes,scrollbars=yes,width=" + t + ",height=" + o + ",top=" + r + ",left=" + n), !1
}
function resized() {
    ww = window.innerWidth, wh = window.innerHeight;
    if ($.colorbox == undefined) {
        return;
    }
    var e = $.colorbox.settings;
    $.colorbox.resize({
        width: ww > e.maxWidth ? e.maxWidth : e.width,
        height: wh > e.maxHeight ? e.maxHeight : e.height
    }), $(".autosize").trigger("autosize.resize"), 595 > ww && $("#comments-tags-md").html() ? $("#comments-tags-md .container").appendTo("#comments-tags-sm") : ww > 594 && $("#comments-tags-sm").html() && $("#comments-tags-sm .container").appendTo("#comments-tags-md")
}
function show_message(e, t, o, i) {
    $(".message_box").remove();
    var n = $('<div class="message_box" onclick="$(this).remove();"><span style="opacity:.8;float:right;margin-right:8px;cursor:pointer;font-size:20px;line-height:1">×</span>' + e + "</div>");
    t && n.addClass(t), i ? n.css("position", "relative").prependTo("body") : n.appendTo("body"), n.delay(o || 3e3).slideUp()
}
!function (e) {
    e.extend(e.fn, {
        livequery: function (t, o, i) {
            var n, r = this;
            return e.isFunction(t) && (i = o, o = t, t = void 0), e.each(e.livequery.queries, function (e, a) {
                return r.selector != a.selector || r.context != a.context || t != a.type || o && o.$lqguid != a.fn.$lqguid || i && i.$lqguid != a.fn2.$lqguid ? void 0 : (n = a) && !1
            }), n = n || new e.livequery(this.selector, this.context, t, o, i), n.stopped = !1, n.run(), this
        }, expire: function (t, o, i) {
            var n = this;
            return e.isFunction(t) && (i = o, o = t, t = void 0), e.each(e.livequery.queries, function (r, a) {
                n.selector != a.selector || n.context != a.context || t && t != a.type || o && o.$lqguid != a.fn.$lqguid || i && i.$lqguid != a.fn2.$lqguid || this.stopped || e.livequery.stop(a.id)
            }), this
        }
    }), e.livequery = function (t, o, i, n, r) {
        return this.selector = t, this.context = o, this.type = i, this.fn = n, this.fn2 = r, this.elements = [], this.stopped = !1, this.id = e.livequery.queries.push(this) - 1, n.$lqguid = n.$lqguid || e.livequery.guid++, r && (r.$lqguid = r.$lqguid || e.livequery.guid++), this
    }, e.livequery.prototype = {
        stop: function () {
            var e = this;
            this.type ? this.elements.unbind(this.type, this.fn) : this.fn2 && this.elements.each(function (t, o) {
                e.fn2.apply(o)
            }), this.elements = [], this.stopped = !0
        }, run: function () {
            if (!this.stopped) {
                var t = this, o = this.elements, i = e(this.selector, this.context), n = i.not(o);
                this.elements = i, this.type ? (n.bind(this.type, this.fn), o.length > 0 && e.each(o, function (o, n) {
                    e.inArray(n, i) < 0 && e.event.remove(n, t.type, t.fn)
                })) : (n.each(function () {
                    t.fn.apply(this)
                }), this.fn2 && o.length > 0 && e.each(o, function (o, n) {
                    e.inArray(n, i) < 0 && t.fn2.apply(n)
                }))
            }
        }
    }, e.extend(e.livequery, {
        guid: 0, queries: [], queue: [], running: !1, timeout: null, checkQueue: function () {
            if (e.livequery.running && e.livequery.queue.length)for (var t = e.livequery.queue.length; t--;)e.livequery.queries[e.livequery.queue.shift()].run()
        }, pause: function () {
            e.livequery.running = !1
        }, play: function () {
            e.livequery.running = !0, e.livequery.run()
        }, registerPlugin: function () {
            e.each(arguments, function (t, o) {
                if (e.fn[o]) {
                    var i = e.fn[o];
                    e.fn[o] = function () {
                        var t = i.apply(this, arguments);
                        return e.livequery.run(), t
                    }
                }
            })
        }, run: function (t) {
            void 0 != t ? e.inArray(t, e.livequery.queue) < 0 && e.livequery.queue.push(t) : e.each(e.livequery.queries, function (t) {
                e.inArray(t, e.livequery.queue) < 0 && e.livequery.queue.push(t)
            }), e.livequery.timeout && clearTimeout(e.livequery.timeout), e.livequery.timeout = setTimeout(e.livequery.checkQueue, 50)
        }, stop: function (t) {
            void 0 != t ? e.livequery.queries[t].stop() : e.each(e.livequery.queries, function (t) {
                e.livequery.queries[t].stop()
            })
        }
    }), e.livequery.registerPlugin("append", "prepend", "after", "before", "wrap", "attr", "removeAttr", "addClass", "removeClass", "toggleClass", "empty", "remove", "html"), e(function () {
        e.livequery.play()
    })
}(jQuery), !function (e) {
    "use strict";
    "function" == typeof define && define.amd ? define(["jquery"], e) : e("undefined" != typeof jQuery ? jQuery : window.Zepto)
}(function (e) {
    "use strict";
    function t(t) {
        var o = t.data;
        t.isDefaultPrevented() || (t.preventDefault(), e(t.target).ajaxSubmit(o))
    }

    function o(t) {
        var o = t.target, i = e(o);
        if (!i.is("[type=submit],[type=image]")) {
            var n = i.closest("[type=submit]");
            if (0 === n.length)return;
            o = n[0]
        }
        var r = this;
        if (r.clk = o, "image" == o.type)if (void 0 !== t.offsetX)r.clk_x = t.offsetX, r.clk_y = t.offsetY; else if ("function" == typeof e.fn.offset) {
            var a = i.offset();
            r.clk_x = t.pageX - a.left, r.clk_y = t.pageY - a.top
        } else r.clk_x = t.pageX - o.offsetLeft, r.clk_y = t.pageY - o.offsetTop;
        setTimeout(function () {
            r.clk = r.clk_x = r.clk_y = null
        }, 100)
    }

    function i() {
        if (e.fn.ajaxSubmit.debug) {
            var t = "[jquery.form] " + Array.prototype.join.call(arguments, "");
            window.console && window.console.log ? window.console.log(t) : window.opera && window.opera.postError && window.opera.postError(t)
        }
    }

    var n = {};
    n.fileapi = void 0 !== e("<input type='file'/>").get(0).files, n.formdata = void 0 !== window.FormData;
    var r = !!e.fn.prop;
    e.fn.attr2 = function () {
        if (!r)return this.attr.apply(this, arguments);
        var e = this.prop.apply(this, arguments);
        return e && e.jquery || "string" == typeof e ? e : this.attr.apply(this, arguments)
    }, e.fn.ajaxSubmit = function (t) {
        function o(o) {
            var i, n, r = e.param(o, t.traditional).split("&"), a = r.length, s = [];
            for (i = 0; a > i; i++)r[i] = r[i].replace(/\+/g, " "), n = r[i].split("="), s.push([decodeURIComponent(n[0]), decodeURIComponent(n[1])]);
            return s
        }

        function a(i) {
            for (var n = new FormData, r = 0; r < i.length; r++)n.append(i[r].name, i[r].value);
            if (t.extraData) {
                var a = o(t.extraData);
                for (r = 0; r < a.length; r++)a[r] && n.append(a[r][0], a[r][1])
            }
            t.data = null;
            var s = e.extend(!0, {}, e.ajaxSettings, t, {
                contentType: !1,
                processData: !1,
                cache: !1,
                type: l || "POST"
            });
            t.uploadProgress && (s.xhr = function () {
                var o = e.ajaxSettings.xhr();
                return o.upload && o.upload.addEventListener("progress", function (e) {
                    var o = 0, i = e.loaded || e.position, n = e.total;
                    e.lengthComputable && (o = Math.ceil(i / n * 100)), t.uploadProgress(e, i, n, o)
                }, !1), o
            }), s.data = null;
            var c = s.beforeSend;
            return s.beforeSend = function (e, o) {
                o.data = t.formData ? t.formData : n, c && c.call(this, e, o)
            }, e.ajax(s)
        }

        function s(o) {
            function n(e) {
                var t = null;
                try {
                    e.contentWindow && (t = e.contentWindow.document)
                } catch (o) {
                    i("cannot get iframe.contentWindow document: " + o)
                }
                if (t)return t;
                try {
                    t = e.contentDocument ? e.contentDocument : e.document
                } catch (o) {
                    i("cannot get iframe.contentDocument: " + o), t = e.document
                }
                return t
            }

            function a() {
                function t() {
                    try {
                        var e = n(v).readyState;
                        i("state = " + e), e && "uninitialized" == e.toLowerCase() && setTimeout(t, 50)
                    } catch (o) {
                        i("Server abort: ", o, " (", o.name, ")"), s(C), $ && clearTimeout($), $ = void 0
                    }
                }

                var o = d.attr2("target"), r = d.attr2("action"), a = "multipart/form-data", c = d.attr("enctype") || d.attr("encoding") || a;
                _.setAttribute("target", p), (!l || /post/i.test(l)) && _.setAttribute("method", "POST"), r != h.url && _.setAttribute("action", h.url), h.skipEncodingOverride || l && !/post/i.test(l) || d.attr({
                    encoding: "multipart/form-data",
                    enctype: "multipart/form-data"
                }), h.timeout && ($ = setTimeout(function () {
                    b = !0, s(k)
                }, h.timeout));
                var u = [];
                try {
                    if (h.extraData)for (var f in h.extraData)h.extraData.hasOwnProperty(f) && u.push(e.isPlainObject(h.extraData[f]) && h.extraData[f].hasOwnProperty("name") && h.extraData[f].hasOwnProperty("value") ? e('<input type="hidden" name="' + h.extraData[f].name + '">').val(h.extraData[f].value).appendTo(_)[0] : e('<input type="hidden" name="' + f + '">').val(h.extraData[f]).appendTo(_)[0]);
                    h.iframeTarget || g.appendTo("body"), v.attachEvent ? v.attachEvent("onload", s) : v.addEventListener("load", s, !1), setTimeout(t, 15);
                    try {
                        _.submit()
                    } catch (m) {
                        var w = document.createElement("form").submit;
                        w.apply(_)
                    }
                } finally {
                    _.setAttribute("action", r), _.setAttribute("enctype", c), o ? _.setAttribute("target", o) : d.removeAttr("target"), e(u).remove()
                }
            }

            function s(t) {
                if (!w.aborted && !E) {
                    if (q = n(v), q || (i("cannot access response document"), t = C), t === k && w)return w.abort("timeout"), void T.reject(w, "timeout");
                    if (t == C && w)return w.abort("server abort"), void T.reject(w, "error", "server abort");
                    if (q && q.location.href != h.iframeSrc || b) {
                        v.detachEvent ? v.detachEvent("onload", s) : v.removeEventListener("load", s, !1);
                        var o, r = "success";
                        try {
                            if (b)throw"timeout";
                            var a = "xml" == h.dataType || q.XMLDocument || e.isXMLDoc(q);
                            if (i("isXml=" + a), !a && window.opera && (null === q.body || !q.body.innerHTML) && --H)return i("requeing onLoad callback, DOM not available"), void setTimeout(s, 250);
                            var l = q.body ? q.body : q.documentElement;
                            w.responseText = l ? l.innerHTML : null, w.responseXML = q.XMLDocument ? q.XMLDocument : q, a && (h.dataType = "xml"), w.getResponseHeader = function (e) {
                                var t = {"content-type": h.dataType};
                                return t[e.toLowerCase()]
                            }, l && (w.status = Number(l.getAttribute("status")) || w.status, w.statusText = l.getAttribute("statusText") || w.statusText);
                            var c = (h.dataType || "").toLowerCase(), u = /(json|script|text)/.test(c);
                            if (u || h.textarea) {
                                var d = q.getElementsByTagName("textarea")[0];
                                if (d)w.responseText = d.value, w.status = Number(d.getAttribute("status")) || w.status, w.statusText = d.getAttribute("statusText") || w.statusText; else if (u) {
                                    var p = q.getElementsByTagName("pre")[0], m = q.getElementsByTagName("body")[0];
                                    p ? w.responseText = p.textContent ? p.textContent : p.innerText : m && (w.responseText = m.textContent ? m.textContent : m.innerText)
                                }
                            } else"xml" == c && !w.responseXML && w.responseText && (w.responseXML = D(w.responseText));
                            try {
                                z = A(w, c, h)
                            } catch (x) {
                                r = "parsererror", w.error = o = x || r
                            }
                        } catch (x) {
                            i("error caught: ", x), r = "error", w.error = o = x || r
                        }
                        w.aborted && (i("upload aborted"), r = null), w.status && (r = w.status >= 200 && w.status < 300 || 304 === w.status ? "success" : "error"), "success" === r ? (h.success && h.success.call(h.context, z, "success", w), T.resolve(w.responseText, "success", w), f && e.event.trigger("ajaxSuccess", [w, h])) : r && (void 0 === o && (o = w.statusText), h.error && h.error.call(h.context, w, r, o), T.reject(w, "error", o), f && e.event.trigger("ajaxError", [w, h, o])), f && e.event.trigger("ajaxComplete", [w, h]), f && !--e.active && e.event.trigger("ajaxStop"), h.complete && h.complete.call(h.context, w, r), E = !0, h.timeout && clearTimeout($), setTimeout(function () {
                            h.iframeTarget ? g.attr("src", h.iframeSrc) : g.remove(), w.responseXML = null
                        }, 100)
                    }
                }
            }

            var c, u, h, f, p, g, v, w, x, y, b, $, _ = d[0], T = e.Deferred();
            if (T.abort = function (e) {
                    w.abort(e)
                }, o)for (u = 0; u < m.length; u++)c = e(m[u]), r ? c.prop("disabled", !1) : c.removeAttr("disabled");
            if (h = e.extend(!0, {}, e.ajaxSettings, t), h.context = h.context || h, p = "jqFormIO" + (new Date).getTime(), h.iframeTarget ? (g = e(h.iframeTarget), y = g.attr2("name"), y ? p = y : g.attr2("name", p)) : (g = e('<iframe name="' + p + '" src="' + h.iframeSrc + '" />'), g.css({
                    position: "absolute",
                    top: "-1000px",
                    left: "-1000px"
                })), v = g[0], w = {
                    aborted: 0,
                    responseText: null,
                    responseXML: null,
                    status: 0,
                    statusText: "n/a",
                    getAllResponseHeaders: function () {
                    },
                    getResponseHeader: function () {
                    },
                    setRequestHeader: function () {
                    },
                    abort: function (t) {
                        var o = "timeout" === t ? "timeout" : "aborted";
                        i("aborting upload... " + o), this.aborted = 1;
                        try {
                            v.contentWindow.document.execCommand && v.contentWindow.document.execCommand("Stop")
                        } catch (n) {
                        }
                        g.attr("src", h.iframeSrc), w.error = o, h.error && h.error.call(h.context, w, o, t), f && e.event.trigger("ajaxError", [w, h, o]), h.complete && h.complete.call(h.context, w, o)
                    }
                }, f = h.global, f && 0 === e.active++ && e.event.trigger("ajaxStart"), f && e.event.trigger("ajaxSend", [w, h]), h.beforeSend && h.beforeSend.call(h.context, w, h) === !1)return h.global && e.active--, T.reject(), T;
            if (w.aborted)return T.reject(), T;
            x = _.clk, x && (y = x.name, y && !x.disabled && (h.extraData = h.extraData || {}, h.extraData[y] = x.value, "image" == x.type && (h.extraData[y + ".x"] = _.clk_x, h.extraData[y + ".y"] = _.clk_y)));
            var k = 1, C = 2, S = e("meta[name=csrf-token]").attr("content"), j = e("meta[name=csrf-param]").attr("content");
            j && S && (h.extraData = h.extraData || {}, h.extraData[j] = S), h.forceSync ? a() : setTimeout(a, 10);
            var z, q, E, H = 50, D = e.parseXML || function (e, t) {
                    return window.ActiveXObject ? (t = new ActiveXObject("Microsoft.XMLDOM"), t.async = "false", t.loadXML(e)) : t = (new DOMParser).parseFromString(e, "text/xml"), t && t.documentElement && "parsererror" != t.documentElement.nodeName ? t : null
                }, I = e.parseJSON || function (e) {
                    return window.eval("(" + e + ")")
                }, A = function (t, o, i) {
                var n = t.getResponseHeader("content-type") || "", r = "xml" === o || !o && n.indexOf("xml") >= 0, a = r ? t.responseXML : t.responseText;
                return r && "parsererror" === a.documentElement.nodeName && e.error && e.error("parsererror"), i && i.dataFilter && (a = i.dataFilter(a, o)), "string" == typeof a && ("json" === o || !o && n.indexOf("json") >= 0 ? a = I(a) : ("script" === o || !o && n.indexOf("javascript") >= 0) && e.globalEval(a)), a
            };
            return T
        }

        if (!this.length)return i("ajaxSubmit: skipping submit process - no element selected"), this;
        var l, c, u, d = this;
        "function" == typeof t ? t = {success: t} : void 0 === t && (t = {}), l = t.type || this.attr2("method"), c = t.url || this.attr2("action"), u = "string" == typeof c ? e.trim(c) : "", u = u || window.location.href || "", u && (u = (u.match(/^([^#]+)/) || [])[1]), t = e.extend(!0, {
            url: u,
            success: e.ajaxSettings.success,
            type: l || e.ajaxSettings.type,
            iframeSrc: /^https/i.test(window.location.href || "") ? "javascript:false" : "about:blank"
        }, t);
        var h = {};
        if (this.trigger("form-pre-serialize", [this, t, h]), h.veto)return i("ajaxSubmit: submit vetoed via form-pre-serialize trigger"), this;
        if (t.beforeSerialize && t.beforeSerialize(this, t) === !1)return i("ajaxSubmit: submit aborted via beforeSerialize callback"), this;
        var f = t.traditional;
        void 0 === f && (f = e.ajaxSettings.traditional);
        var p, m = [], g = this.formToArray(t.semantic, m);
        if (t.data && (t.extraData = t.data, p = e.param(t.data, f)), t.beforeSubmit && t.beforeSubmit(g, this, t) === !1)return i("ajaxSubmit: submit aborted via beforeSubmit callback"), this;
        if (this.trigger("form-submit-validate", [g, this, t, h]), h.veto)return i("ajaxSubmit: submit vetoed via form-submit-validate trigger"), this;
        var v = e.param(g, f);
        p && (v = v ? v + "&" + p : p), "GET" == t.type.toUpperCase() ? (t.url += (t.url.indexOf("?") >= 0 ? "&" : "?") + v, t.data = null) : t.data = v;
        var w = [];
        if (t.resetForm && w.push(function () {
                d.resetForm()
            }), t.clearForm && w.push(function () {
                d.clearForm(t.includeHidden)
            }), !t.dataType && t.target) {
            var x = t.success || function () {
                };
            w.push(function (o) {
                var i = t.replaceTarget ? "replaceWith" : "html";
                e(t.target)[i](o).each(x, arguments)
            })
        } else t.success && w.push(t.success);
        if (t.success = function (e, o, i) {
                for (var n = t.context || this, r = 0, a = w.length; a > r; r++)w[r].apply(n, [e, o, i || d, d])
            }, t.error) {
            var y = t.error;
            t.error = function (e, o, i) {
                var n = t.context || this;
                y.apply(n, [e, o, i, d])
            }
        }
        if (t.complete) {
            var b = t.complete;
            t.complete = function (e, o) {
                var i = t.context || this;
                b.apply(i, [e, o, d])
            }
        }
        var $ = e("input[type=file]:enabled", this).filter(function () {
            return "" !== e(this).val()
        }), _ = $.length > 0, T = "multipart/form-data", k = d.attr("enctype") == T || d.attr("encoding") == T, C = n.fileapi && n.formdata;
        i("fileAPI :" + C);
        var S, j = (_ || k) && !C;
        t.iframe !== !1 && (t.iframe || j) ? t.closeKeepAlive ? e.get(t.closeKeepAlive, function () {
            S = s(g)
        }) : S = s(g) : S = (_ || k) && C ? a(g) : e.ajax(t), d.removeData("jqxhr").data("jqxhr", S);
        for (var z = 0; z < m.length; z++)m[z] = null;
        return this.trigger("form-submit-notify", [this, t]), this
    }, e.fn.ajaxForm = function (n) {
        if (n = n || {}, n.delegation = n.delegation && e.isFunction(e.fn.on), !n.delegation && 0 === this.length) {
            var r = {s: this.selector, c: this.context};
            return !e.isReady && r.s ? (i("DOM not ready, queuing ajaxForm"), e(function () {
                e(r.s, r.c).ajaxForm(n)
            }), this) : (i("terminating; zero elements found by selector" + (e.isReady ? "" : " (DOM not ready)")), this)
        }
        return n.delegation ? (e(document).off("submit.form-plugin", this.selector, t).off("click.form-plugin", this.selector, o).on("submit.form-plugin", this.selector, n, t).on("click.form-plugin", this.selector, n, o), this) : this.ajaxFormUnbind().bind("submit.form-plugin", n, t).bind("click.form-plugin", n, o)
    }, e.fn.ajaxFormUnbind = function () {
        return this.unbind("submit.form-plugin click.form-plugin")
    }, e.fn.formToArray = function (t, o) {
        var i = [];
        if (0 === this.length)return i;
        var r, a = this[0], s = this.attr("id"), l = t ? a.getElementsByTagName("*") : a.elements;
        if (l && !/MSIE [678]/.test(navigator.userAgent) && (l = e(l).get()), s && (r = e(':input[form="' + s + '"]').get(), r.length && (l = (l || []).concat(r))), !l || !l.length)return i;
        var c, u, d, h, f, p, m;
        for (c = 0, p = l.length; p > c; c++)if (f = l[c], d = f.name, d && !f.disabled)if (t && a.clk && "image" == f.type)a.clk == f && (i.push({
            name: d,
            value: e(f).val(),
            type: f.type
        }), i.push({name: d + ".x", value: a.clk_x}, {
            name: d + ".y",
            value: a.clk_y
        })); else if (h = e.fieldValue(f, !0), h && h.constructor == Array)for (o && o.push(f), u = 0, m = h.length; m > u; u++)i.push({
            name: d,
            value: h[u]
        }); else if (n.fileapi && "file" == f.type) {
            o && o.push(f);
            var g = f.files;
            if (g.length)for (u = 0; u < g.length; u++)i.push({
                name: d,
                value: g[u],
                type: f.type
            }); else i.push({name: d, value: "", type: f.type})
        } else null !== h && "undefined" != typeof h && (o && o.push(f), i.push({
            name: d,
            value: h,
            type: f.type,
            required: f.required
        }));
        if (!t && a.clk) {
            var v = e(a.clk), w = v[0];
            d = w.name, d && !w.disabled && "image" == w.type && (i.push({
                name: d,
                value: v.val()
            }), i.push({name: d + ".x", value: a.clk_x}, {name: d + ".y", value: a.clk_y}))
        }
        return i
    }, e.fn.formSerialize = function (t) {
        return e.param(this.formToArray(t))
    }, e.fn.fieldSerialize = function (t) {
        var o = [];
        return this.each(function () {
            var i = this.name;
            if (i) {
                var n = e.fieldValue(this, t);
                if (n && n.constructor == Array)for (var r = 0, a = n.length; a > r; r++)o.push({
                    name: i,
                    value: n[r]
                }); else null !== n && "undefined" != typeof n && o.push({name: this.name, value: n})
            }
        }), e.param(o)
    }, e.fn.fieldValue = function (t) {
        for (var o = [], i = 0, n = this.length; n > i; i++) {
            var r = this[i], a = e.fieldValue(r, t);
            null === a || "undefined" == typeof a || a.constructor == Array && !a.length || (a.constructor == Array ? e.merge(o, a) : o.push(a))
        }
        return o
    }, e.fieldValue = function (t, o) {
        var i = t.name, n = t.type, r = t.tagName.toLowerCase();
        if (void 0 === o && (o = !0), o && (!i || t.disabled || "reset" == n || "button" == n || ("checkbox" == n || "radio" == n) && !t.checked || ("submit" == n || "image" == n) && t.form && t.form.clk != t || "select" == r && -1 == t.selectedIndex))return null;
        if ("select" == r) {
            var a = t.selectedIndex;
            if (0 > a)return null;
            for (var s = [], l = t.options, c = "select-one" == n, u = c ? a + 1 : l.length, d = c ? a : 0; u > d; d++) {
                var h = l[d];
                if (h.selected) {
                    var f = h.value;
                    if (f || (f = h.attributes && h.attributes.value && !h.attributes.value.specified ? h.text : h.value), c)return f;
                    s.push(f)
                }
            }
            return s
        }
        return e(t).val()
    }, e.fn.clearForm = function (t) {
        return this.each(function () {
            e("input,select,textarea", this).clearFields(t)
        })
    }, e.fn.clearFields = e.fn.clearInputs = function (t) {
        var o = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
        return this.each(function () {
            var i = this.type, n = this.tagName.toLowerCase();
            o.test(i) || "textarea" == n ? this.value = "" : "checkbox" == i || "radio" == i ? this.checked = !1 : "select" == n ? this.selectedIndex = -1 : "file" == i ? /MSIE/.test(navigator.userAgent) ? e(this).replaceWith(e(this).clone(!0)) : e(this).val("") : t && (t === !0 && /hidden/.test(i) || "string" == typeof t && e(this).is(t)) && (this.value = "")
        })
    }, e.fn.resetForm = function () {
        return this.each(function () {
            ("function" == typeof this.reset || "object" == typeof this.reset && !this.reset.nodeType) && this.reset()
        })
    }, e.fn.enable = function (e) {
        return void 0 === e && (e = !0), this.each(function () {
            this.disabled = !e
        })
    }, e.fn.selected = function (t) {
        return void 0 === t && (t = !0), this.each(function () {
            var o = this.type;
            if ("checkbox" == o || "radio" == o)this.checked = t; else if ("option" == this.tagName.toLowerCase()) {
                var i = e(this).parent("select");
                t && i[0] && "select-one" == i[0].type && i.find("option").selected(!1), this.selected = t
            }
        })
    }, e.fn.ajaxSubmit.debug = !1
}), function (e, t, o) {
    function i(o, i, n) {
        var r = t.createElement(o);
        return i && (r.id = Z + i), n && (r.style.cssText = n), e(r)
    }

    function n() {
        return o.innerHeight ? o.innerHeight : e(o).height()
    }

    function r(t, o) {
        o !== Object(o) && (o = {}), this.cache = {}, this.el = t, this.value = function (t) {
            var i;
            return void 0 === this.cache[t] && (i = e(this.el).attr("data-cbox-" + t), void 0 !== i ? this.cache[t] = i : void 0 !== o[t] ? this.cache[t] = o[t] : void 0 !== V[t] && (this.cache[t] = V[t])), this.cache[t]
        }, this.get = function (t) {
            var o = this.value(t);
            return e.isFunction(o) ? o.call(this.el, this) : o
        }
    }

    function a(e) {
        var t = C.length, o = (B + e) % t;
        return 0 > o ? t + o : o
    }

    function s(e, t) {
        return Math.round((/%/.test(e) ? ("x" === t ? S.width() : n()) / 100 : 1) * parseInt(e, 10))
    }

    function l(e, t) {
        return e.get("photo") || e.get("photoRegex").test(t)
    }

    function c(e, t) {
        return e.get("retinaUrl") && o.devicePixelRatio > 1 ? t.replace(e.get("photoRegex"), e.get("retinaSuffix")) : t
    }

    function u(e) {
        "contains"in x[0] && !x[0].contains(e.target) && e.target !== w[0] && (e.stopPropagation(), x.focus())
    }

    function d(e) {
        d.str !== e && (x.add(w).removeClass(d.str).addClass(e), d.str = e)
    }

    function h(t) {
        B = 0, t && t !== !1 && "nofollow" !== t ? (C = e("." + et).filter(function () {
            var o = e.data(this, J), i = new r(this, o);
            return i.get("rel") === t
        }), B = C.index(O.el), -1 === B && (C = C.add(O.el), B = C.length - 1)) : C = e(O.el)
    }

    function f(o) {
        e(t).trigger(o), st.triggerHandler(o)
    }

    function p(o) {
        var n;
        if (!K) {
            if (n = e(o).data(J), O = new r(o, n), h(O.get("rel")), !P) {
                P = Y = !0, d(O.get("className")), x.css({
                    visibility: "hidden",
                    display: "block",
                    opacity: ""
                }), j = i(lt, "LoadedContent", "width:0; height:0; overflow:hidden; visibility:hidden"), b.css({
                    width: "",
                    height: ""
                }).append(j), F = $.height() + k.height() + b.outerHeight(!0) - b.height(), R = _.width() + T.width() + b.outerWidth(!0) - b.width(), M = j.outerHeight(!0), N = j.outerWidth(!0);
                var a = s(O.get("initialWidth"), "x"), l = s(O.get("initialHeight"), "y"), c = O.get("maxWidth"), p = O.get("maxHeight");
                O.w = (c !== !1 ? Math.min(a, s(c, "x")) : a) - N - R, O.h = (p !== !1 ? Math.min(l, s(p, "y")) : l) - M - F, j.css({
                    width: "",
                    height: O.h
                }), U.position(), f(tt), O.get("onOpen"), W.add(E).hide(), x.focus(), O.get("trapFocus") && t.addEventListener && (t.addEventListener("focus", u, !0), st.one(rt, function () {
                    t.removeEventListener("focus", u, !0)
                })), O.get("returnFocus") && st.one(rt, function () {
                    e(O.el).focus()
                })
            }
            var m = parseFloat(O.get("opacity"));
            w.css({
                opacity: m === m ? m : "",
                cursor: O.get("overlayClose") ? "pointer" : "",
                visibility: "visible"
            }).show(), O.get("closeButton") ? L.html(O.get("close")).appendTo(b) : L.appendTo("<div/>"), v()
        }
    }

    function m() {
        x || (G = !1, S = e(o), x = i(lt).attr({
            id: J,
            "class": e.support.opacity === !1 ? Z + "IE" : "",
            role: "dialog",
            tabindex: "-1"
        }).hide(), w = i(lt, "Overlay").hide(), q = e([i(lt, "LoadingOverlay")[0], i(lt, "LoadingGraphic")[0]]), y = i(lt, "Wrapper"), b = i(lt, "Content").append(E = i(lt, "Title"), H = i(lt, "Current"), A = e('<button type="button"/>').attr({id: Z + "Previous"}), I = e('<button type="button"/>').attr({id: Z + "Next"}), D = i("button", "Slideshow"), q), L = e('<button type="button"/>').attr({id: Z + "Close"}), y.append(i(lt).append(i(lt, "TopLeft"), $ = i(lt, "TopCenter"), i(lt, "TopRight")), i(lt, !1, "clear:left").append(_ = i(lt, "MiddleLeft"), b, T = i(lt, "MiddleRight")), i(lt, !1, "clear:left").append(i(lt, "BottomLeft"), k = i(lt, "BottomCenter"), i(lt, "BottomRight"))).find("div div").css({"float": "left"}), z = i(lt, !1, "position:absolute; width:9999px; visibility:hidden; display:none; max-width:none;"), W = I.add(A).add(H).add(D)), t.body && !x.parent().length && e(t.body).append(w, x.append(y, z))
    }

    function g() {
        function o(e) {
            e.which > 1 || e.shiftKey || e.altKey || e.metaKey || e.ctrlKey || (e.preventDefault(), p(this))
        }

        return x ? (G || (G = !0, I.click(function () {
            U.next()
        }), A.click(function () {
            U.prev()
        }), L.click(function () {
            U.close()
        }), w.click(function () {
            O.get("overlayClose") && U.close()
        }), e(t).bind("keydown." + Z, function (e) {
            var t = e.keyCode;
            P && O.get("escKey") && 27 === t && (e.preventDefault(), U.close()), P && O.get("arrowKey") && C[1] && !e.altKey && (37 === t ? (e.preventDefault(), A.click()) : 39 === t && (e.preventDefault(), I.click()))
        }), e.isFunction(e.fn.on) ? e(t).on("click." + Z, "." + et, o) : e("." + et).live("click." + Z, o)), !0) : !1
    }

    function v() {
        var t, n, r, a = U.prep, u = ++ct;
        if (Y = !0, X = !1, f(at), f(ot), O.get("onLoad"), O.h = O.get("height") ? s(O.get("height"), "y") - M - F : O.get("innerHeight") && s(O.get("innerHeight"), "y"), O.w = O.get("width") ? s(O.get("width"), "x") - N - R : O.get("innerWidth") && s(O.get("innerWidth"), "x"), O.mw = O.w, O.mh = O.h, O.get("maxWidth") && (O.mw = s(O.get("maxWidth"), "x") - N - R, O.mw = O.w && O.w < O.mw ? O.w : O.mw), O.get("maxHeight") && (O.mh = s(O.get("maxHeight"), "y") - M - F, O.mh = O.h && O.h < O.mh ? O.h : O.mh), t = O.get("href"), Q = setTimeout(function () {
                q.show()
            }, 100), O.get("inline")) {
            var d = e(t);
            r = e("<div>").hide().insertBefore(d), st.one(at, function () {
                r.replaceWith(d)
            }), a(d)
        } else O.get("iframe") ? a(" ") : O.get("html") ? a(O.get("html")) : l(O, t) ? (t = c(O, t), X = new Image, e(X).addClass(Z + "Photo").bind("error", function () {
            a(i(lt, "Error").html(O.get("imgError")))
        }).one("load", function () {
            u === ct && setTimeout(function () {
                var t;
                e.each(["alt", "longdesc", "aria-describedby"], function (t, o) {
                    var i = e(O.el).attr(o) || e(O.el).attr("data-" + o);
                    i && X.setAttribute(o, i)
                }), O.get("retinaImage") && o.devicePixelRatio > 1 && (X.height = X.height / o.devicePixelRatio, X.width = X.width / o.devicePixelRatio), O.get("scalePhotos") && (n = function () {
                    X.height -= X.height * t, X.width -= X.width * t
                }, O.mw && X.width > O.mw && (t = (X.width - O.mw) / X.width, n()), O.mh && X.height > O.mh && (t = (X.height - O.mh) / X.height, n())), O.h && (X.style.marginTop = Math.max(O.mh - X.height, 0) / 2 + "px"), C[1] && (O.get("loop") || C[B + 1]) && (X.style.cursor = "pointer", X.onclick = function () {
                    U.next()
                }), X.style.width = X.width + "px", X.style.height = X.height + "px", a(X)
            }, 1)
        }), X.src = t) : t && z.load(t, O.get("data"), function (t, o) {
            u === ct && a("error" === o ? i(lt, "Error").html(O.get("xhrError")) : e(this).contents())
        })
    }

    var w, x, y, b, $, _, T, k, C, S, j, z, q, E, H, D, I, A, L, W, O, F, R, M, N, B, X, P, Y, K, Q, U, G, V = {
        html: !1,
        photo: !1,
        iframe: !1,
        inline: !1,
        transition: "elastic",
        speed: 300,
        fadeOut: 300,
        width: !1,
        initialWidth: "600",
        innerWidth: !1,
        maxWidth: !1,
        height: !1,
        initialHeight: "450",
        innerHeight: !1,
        maxHeight: !1,
        scalePhotos: !0,
        scrolling: !0,
        opacity: .9,
        preloading: !0,
        className: !1,
        overlayClose: !0,
        escKey: !0,
        arrowKey: !0,
        top: !1,
        bottom: !1,
        left: !1,
        right: !1,
        fixed: !1,
        data: void 0,
        closeButton: !0,
        fastIframe: !0,
        open: !1,
        reposition: !0,
        loop: !0,
        slideshow: !1,
        slideshowAuto: !0,
        slideshowSpeed: 2500,
        slideshowStart: "start slideshow",
        slideshowStop: "stop slideshow",
        photoRegex: /\.(gif|png|jp(e|g|eg)|bmp|ico|webp|jxr|svg)((#|\?).*)?$/i,
        retinaImage: !1,
        retinaUrl: !1,
        retinaSuffix: "@2x.$1",
        current: "image {current} of {total}",
        previous: "previous",
        next: "next",
        close: "close",
        xhrError: "This content failed to load.",
        imgError: "This image failed to load.",
        returnFocus: !0,
        trapFocus: !0,
        onOpen: !1,
        onLoad: !1,
        onComplete: !1,
        onCleanup: !1,
        onClosed: !1,
        rel: function () {
            return this.rel
        },
        href: function () {
            return e(this).attr("href")
        },
        title: function () {
            return this.title
        }
    }, J = "colorbox", Z = "cbox", et = Z + "Element", tt = Z + "_open", ot = Z + "_load", it = Z + "_complete", nt = Z + "_cleanup", rt = Z + "_closed", at = Z + "_purge", st = e("<a/>"), lt = "div", ct = 0, ut = {}, dt = function () {
        function e() {
            clearTimeout(a)
        }

        function t() {
            (O.get("loop") || C[B + 1]) && (e(), a = setTimeout(U.next, O.get("slideshowSpeed")))
        }

        function o() {
            D.html(O.get("slideshowStop")).unbind(l).one(l, i), st.bind(it, t).bind(ot, e), x.removeClass(s + "off").addClass(s + "on")
        }

        function i() {
            e(), st.unbind(it, t).unbind(ot, e), D.html(O.get("slideshowStart")).unbind(l).one(l, function () {
                U.next(), o()
            }), x.removeClass(s + "on").addClass(s + "off")
        }

        function n() {
            r = !1, D.hide(), e(), st.unbind(it, t).unbind(ot, e), x.removeClass(s + "off " + s + "on")
        }

        var r, a, s = Z + "Slideshow_", l = "click." + Z;
        return function () {
            r ? O.get("slideshow") || (st.unbind(nt, n), n()) : O.get("slideshow") && C[1] && (r = !0, st.one(nt, n), O.get("slideshowAuto") ? o() : i(), D.show())
        }
    }();
    e[J] || (e(m), U = e.fn[J] = e[J] = function (t, o) {
        var i, n = this;
        if (t = t || {}, e.isFunction(n))n = e("<a/>"), t.open = !0; else if (!n[0])return n;
        return n[0] ? (m(), g() && (o && (t.onComplete = o), n.each(function () {
            var o = e.data(this, J) || {};
            e.data(this, J, e.extend(o, t))
        }).addClass(et), i = new r(n[0], t), i.get("open") && p(n[0])), n) : n
    }, U.position = function (t, o) {
        function i() {
            $[0].style.width = k[0].style.width = b[0].style.width = parseInt(x[0].style.width, 10) - R + "px", b[0].style.height = _[0].style.height = T[0].style.height = parseInt(x[0].style.height, 10) - F + "px"
        }

        var r, a, l, c = 0, u = 0, d = x.offset();
        if (S.unbind("resize." + Z), x.css({
                top: -9e4,
                left: -9e4
            }), a = S.scrollTop(), l = S.scrollLeft(), O.get("fixed") ? (d.top -= a, d.left -= l, x.css({position: "fixed"})) : (c = a, u = l, x.css({position: "absolute"})), u += O.get("right") !== !1 ? Math.max(S.width() - O.w - N - R - s(O.get("right"), "x"), 0) : O.get("left") !== !1 ? s(O.get("left"), "x") : Math.round(Math.max(S.width() - O.w - N - R, 0) / 2), c += O.get("bottom") !== !1 ? Math.max(n() - O.h - M - F - s(O.get("bottom"), "y"), 0) : O.get("top") !== !1 ? s(O.get("top"), "y") : Math.round(Math.max(n() - O.h - M - F, 0) / 2), x.css({
                top: d.top,
                left: d.left,
                visibility: "visible"
            }), y[0].style.width = y[0].style.height = "9999px", r = {
                width: O.w + N + R,
                height: O.h + M + F,
                top: c,
                left: u
            }, t) {
            var h = 0;
            e.each(r, function (e) {
                return r[e] !== ut[e] ? void(h = t) : void 0
            }), t = h
        }
        ut = r, t || x.css(r), x.dequeue().animate(r, {
            duration: t || 0, complete: function () {
                i(), Y = !1, y[0].style.width = O.w + N + R + "px", y[0].style.height = O.h + M + F + "px", O.get("reposition") && setTimeout(function () {
                    S.bind("resize." + Z, U.position)
                }, 1), e.isFunction(o) && o()
            }, step: i
        })
    }, U.resize = function (e) {
        var t;
        P && (e = e || {}, e.width && (O.w = s(e.width, "x") - N - R), e.innerWidth && (O.w = s(e.innerWidth, "x")), j.css({width: O.w}), e.height && (O.h = s(e.height, "y") - M - F), e.innerHeight && (O.h = s(e.innerHeight, "y")), e.innerHeight || e.height || (t = j.scrollTop(), j.css({height: "auto"}), O.h = j.height()), j.css({height: O.h}), t && j.scrollTop(t), U.position("none" === O.get("transition") ? 0 : O.get("speed")))
    }, U.prep = function (o) {
        function n() {
            return O.w = O.w || j.width(), O.w = O.mw && O.mw < O.w ? O.mw : O.w, O.w
        }

        function s() {
            return O.h = O.h || j.height(), O.h = O.mh && O.mh < O.h ? O.mh : O.h, O.h
        }

        if (P) {
            var u, h = "none" === O.get("transition") ? 0 : O.get("speed");
            j.remove(), j = i(lt, "LoadedContent").append(o), j.hide().appendTo(z.show()).css({
                width: n(),
                overflow: O.get("scrolling") ? "auto" : "hidden"
            }).css({height: s()}).prependTo(b), z.hide(), e(X).css({"float": "none"}), d(O.get("className")), u = function () {
                function o() {
                    e.support.opacity === !1 && x[0].style.removeAttribute("filter")
                }

                var i, n, s = C.length;
                P && (n = function () {
                    clearTimeout(Q), q.hide(), f(it), O.get("onComplete")
                }, E.html(O.get("title")).show(), j.show(), s > 1 ? ("string" == typeof O.get("current") && H.html(O.get("current").replace("{current}", B + 1).replace("{total}", s)).show(), I[O.get("loop") || s - 1 > B ? "show" : "hide"]().html(O.get("next")), A[O.get("loop") || B ? "show" : "hide"]().html(O.get("previous")), dt(), O.get("preloading") && e.each([a(-1), a(1)], function () {
                    var o, i = C[this], n = new r(i, e.data(i, J)), a = n.get("href");
                    a && l(n, a) && (a = c(n, a), o = t.createElement("img"), o.src = a)
                })) : W.hide(), O.get("iframe") ? (i = t.createElement("iframe"), "frameBorder"in i && (i.frameBorder = 0), "allowTransparency"in i && (i.allowTransparency = "true"), O.get("scrolling") || (i.scrolling = "no"), e(i).attr({
                    src: O.get("href"),
                    name: (new Date).getTime(),
                    "class": Z + "Iframe",
                    allowFullScreen: !0
                }).one("load", n).appendTo(j), st.one(at, function () {
                    i.src = "//about:blank"
                }), O.get("fastIframe") && e(i).trigger("load")) : n(), "fade" === O.get("transition") ? x.fadeTo(h, 1, o) : o())
            }, "fade" === O.get("transition") ? x.fadeTo(h, 0, function () {
                U.position(0, u)
            }) : U.position(h, u)
        }
    }, U.next = function () {
        !Y && C[1] && (O.get("loop") || C[B + 1]) && (B = a(1), p(C[B]))
    }, U.prev = function () {
        !Y && C[1] && (O.get("loop") || B) && (B = a(-1), p(C[B]))
    }, U.close = function () {
        P && !K && (K = !0, P = !1, f(nt), O.get("onCleanup"), S.unbind("." + Z), w.fadeTo(O.get("fadeOut") || 0, 0), x.stop().fadeTo(O.get("fadeOut") || 0, 0, function () {
            x.hide(), w.hide(), f(at), j.remove(), setTimeout(function () {
                K = !1, f(rt), O.get("onClosed")
            }, 1)
        }))
    }, U.remove = function () {
        x && (x.stop(), e[J].close(), x.stop(!1, !0).remove(), w.remove(), K = !1, x = null, e("." + et).removeData(J).removeClass(et), e(t).unbind("click." + Z).unbind("keydown." + Z))
    }, U.element = function () {
        return e(O.el)
    }, U.settings = V)
}(jQuery, document, window), !function (e) {
    var t, o = {
        className: "autosizejs",
        id: "autosizejs",
        append: "\n",
        callback: !1,
        resizeDelay: 10,
        placeholder: !0
    }, i = '<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>', n = ["fontFamily", "fontSize", "fontWeight", "fontStyle", "letterSpacing", "textTransform", "wordSpacing", "textIndent", "whiteSpace"], r = e(i).data("autosize", !0)[0];
    r.style.lineHeight = "99px", "99px" === e(r).css("lineHeight") && n.push("lineHeight"), r.style.lineHeight = "", e.fn.autosize = function (i) {
        return this.length ? (i = e.extend({}, o, i || {}), r.parentNode !== document.body && e(document.body).append(r), this.each(function () {
            function o() {
                var t, o = window.getComputedStyle ? window.getComputedStyle(h, null) : !1;
                o ? (t = h.getBoundingClientRect().width, (0 === t || "number" != typeof t) && (t = parseFloat(o.width)), e.each(["paddingLeft", "paddingRight", "borderLeftWidth", "borderRightWidth"], function (e, i) {
                    t -= parseFloat(o[i])
                })) : t = f.width(), r.style.width = Math.max(t, 0) + "px"
            }

            function a() {
                var a = {};
                if (t = h, r.className = i.className, r.id = i.id, c = parseFloat(f.css("maxHeight")), e.each(n, function (e, t) {
                        a[t] = f.css(t)
                    }), e(r).css(a).attr("wrap", f.attr("wrap")), o(), window.chrome) {
                    var s = h.style.width;
                    h.style.width = "0px", h.offsetWidth, h.style.width = s
                }
            }

            function s() {
                var e, n;
                t !== h ? a() : o(), r.value = !h.value && i.placeholder ? f.attr("placeholder") || "" : h.value, r.value += i.append || "", r.style.overflowY = h.style.overflowY, n = parseFloat(h.style.height), r.scrollTop = 0, r.scrollTop = 9e4, e = r.scrollTop, c && e > c ? (h.style.overflowY = "scroll", e = c) : (h.style.overflowY = "hidden", u > e && (e = u)), e += p, n !== e && (h.style.height = e + "px", r.className = r.className, m && i.callback.call(h, h), f.trigger("autosize.resized"))
            }

            function l() {
                clearTimeout(d), d = setTimeout(function () {
                    var e = f.width();
                    e !== v && (v = e, s())
                }, parseInt(i.resizeDelay, 10))
            }

            var c, u, d, h = this, f = e(h), p = 0, m = e.isFunction(i.callback), g = {
                height: h.style.height,
                overflow: h.style.overflow,
                overflowY: h.style.overflowY,
                wordWrap: h.style.wordWrap,
                resize: h.style.resize
            }, v = f.width(), w = f.css("resize");
            f.data("autosize") || (f.data("autosize", !0), ("border-box" === f.css("box-sizing") || "border-box" === f.css("-moz-box-sizing") || "border-box" === f.css("-webkit-box-sizing")) && (p = f.outerHeight() - f.height()), u = Math.max(parseFloat(f.css("minHeight")) - p || 0, f.height()), f.css({
                overflow: "hidden",
                overflowY: "hidden",
                wordWrap: "break-word"
            }), "vertical" === w ? f.css("resize", "none") : "both" === w && f.css("resize", "horizontal"), "onpropertychange"in h ? "oninput"in h ? f.on("input.autosize keyup.autosize", s) : f.on("propertychange.autosize", function () {
                "value" === event.propertyName && s()
            }) : f.on("input.autosize", s), i.resizeDelay !== !1 && e(window).on("resize.autosize", l), f.on("autosize.resize", s), f.on("autosize.resizeIncludeStyle", function () {
                t = null, s()
            }), f.on("autosize.destroy", function () {
                t = null, clearTimeout(d), e(window).off("resize", l), f.off("autosize").off(".autosize").css(g).removeData("autosize")
            }), s())
        })) : this
    }
}(jQuery || $), !function (e) {
    e.fn.caret = function (e) {
        var t = this[0], o = "true" === t.contentEditable;
        if (0 == arguments.length) {
            if (window.getSelection) {
                if (o) {
                    t.focus();
                    var i = window.getSelection().getRangeAt(0), n = i.cloneRange();
                    return n.selectNodeContents(t), n.setEnd(i.endContainer, i.endOffset), n.toString().length
                }
                return t.selectionStart
            }
            if (document.selection) {
                if (t.focus(), o) {
                    var i = document.selection.createRange(), n = document.body.createTextRange();
                    return n.moveToElementText(t), n.setEndPoint("EndToEnd", i), n.text.length
                }
                var e = 0, r = t.createTextRange(), n = document.selection.createRange().duplicate(), a = n.getBookmark();
                for (r.moveToBookmark(a); 0 !== r.moveStart("character", -1);)e++;
                return e
            }
            return 0
        }
        if (-1 == e && (e = this[o ? "text" : "val"]().length), window.getSelection)o ? (t.focus(), window.getSelection().collapse(t.firstChild, e)) : t.setSelectionRange(e, e); else if (document.body.createTextRange)if (o) {
            var r = document.body.createTextRange();
            r.moveToElementText(t), r.moveStart("character", e), r.collapse(!0), r.select()
        } else {
            var r = t.createTextRange();
            r.move("character", e), r.select()
        }
        return o || t.focus(), e
    }
}(jQuery), !function (e) {
    e.fn.autoComplete = function (t) {
        var o = e.extend({}, e.fn.autoComplete.defaults, t);
        return "string" == typeof t ? (this.each(function () {
            var o = e(this);
            "destroy" == t && (e(window).off("resize.autocomplete", o.updateSC), o.off("keydown.autocomplete keyup.autocomplete"), o.data("autocomplete") ? o.attr("autocomplete", o.data("autocomplete")) : o.removeAttr("autocomplete"), e(o.data("el")).remove(), o.removeData("el").removeData("autocomplete"))
        }), this) : this.each(function () {
            function t(e) {
                var t = n.val();
                if (n.cache[t] = e, e.length && t.length >= o.minChars) {
                    var r = "";
                    for (i = 0; i < e.length; i++)r += o.renderItem(e[i], t);
                    n.sc.html(r), n.updateSC(0)
                } else n.sc.hide()
            }

            var n = e(this);
            n.sc = e('<div class="autocomplete-suggestions"></div>').addClass(o.menuClass), n.data("el", n.sc).data("autocomplete", n.attr("autocomplete")), n.attr("autocomplete", "off"), n.cache = {}, n.last_val = "", n.updateSC = function (t, o) {
                if (n.sc.css({
                        top: n.offset().top + n.outerHeight(),
                        left: n.offset().left,
                        width: n.outerWidth()
                    }), !t && (n.sc.show(), n.sc.maxHeight || (n.sc.maxHeight = parseInt(n.sc.css("max-height"))), n.sc.suggestionHeight || (n.sc.suggestionHeight = e(".autocomplete-suggestion", n.sc).first().outerHeight()), n.sc.suggestionHeight))if (o) {
                    var i = n.sc.scrollTop(), r = o.offset().top - n.sc.offset().top;
                    r + n.sc.suggestionHeight - n.sc.maxHeight > 0 ? n.sc.scrollTop(r + n.sc.suggestionHeight + i - n.sc.maxHeight) : 0 > r && n.sc.scrollTop(r + i)
                } else n.sc.scrollTop(0)
            }, e(window).on("resize.autocomplete", n.updateSC), n.sc.appendTo("body"), n.sc.on("mouseleave.autocomplete", ".autocomplete-suggestion", function () {
                e(".autocomplete-suggestion.selected").removeClass("selected")
            }), n.sc.on("mouseenter.autocomplete", ".autocomplete-suggestion", function () {
                e(".autocomplete-suggestion.selected").removeClass("selected"), e(this).addClass("selected")
            }), n.sc.on("mousedown.autocomplete", ".autocomplete-suggestion", function () {
                var t = e(this).data("val");
                n.val(t), o.onSelect(t), setTimeout(function () {
                    n.focus()
                }, 10)
            }), n.blur(function () {
                n.last_val = n.val(), n.sc.hide()
            }), n.on("keydown.autocomplete", function (t) {
                if (40 == t.which && n.sc.html()) {
                    var o, i = e(".autocomplete-suggestion.selected", n.sc);
                    return i.length ? (o = i.next(".autocomplete-suggestion"), o.length ? (i.removeClass("selected"), n.val(o.addClass("selected").data("val"))) : (i.removeClass("selected"), n.val(n.last_val), o = 0)) : (o = e(".autocomplete-suggestion", n.sc).first(), n.val(o.addClass("selected").data("val"))), n.updateSC(0, o), !1
                }
                if (38 == t.which && n.sc.html()) {
                    var o, i = e(".autocomplete-suggestion.selected", n.sc);
                    if (i.length) {
                        var o = i.prev(".autocomplete-suggestion");
                        o.length ? (i.removeClass("selected"), n.val(o.addClass("selected").data("val"))) : (i.removeClass("selected"), n.val(n.last_val), o = 0)
                    } else o = e(".autocomplete-suggestion", n.sc).last(), n.val(o.addClass("selected").data("val"));
                    return n.updateSC(0, o), !1
                }
                27 == t.which && n.val(n.last_val).sc.hide()
            }), n.on("keyup.autocomplete", function (r) {
                if (!~e.inArray(r.which, [27, 38, 40, 37, 39])) {
                    var a = n.val();
                    if (a.length >= o.minChars) {
                        if (a != n.last_val) {
                            if (n.last_val = a, clearTimeout(n.timer), o.cache) {
                                if (a in n.cache)return void t(n.cache[a]);
                                for (i = 1; i < a.length - o.minChars; i++) {
                                    var s = a.slice(0, a.length - i);
                                    if (s in n.cache && !n.cache[s].length)return void t([])
                                }
                            }
                            n.timer = setTimeout(function () {
                                o.source(a, t)
                            }, o.delay)
                        }
                    } else n.last_val = a, n.sc.hide()
                }
            })
        })
    }, e.fn.autoComplete.defaults = {
        source: 0,
        minChars: 3,
        delay: 100,
        cache: 1,
        menuClass: "",
        renderItem: function (e, t) {
            var o = new RegExp("(" + t.split(" ").join("|") + ")", "gi");
            return '<div class="autocomplete-suggestion" data-val="' + e + '">' + e.replace(o, "<b>$1</b>") + "</div>"
        },
        onSelect: function () {
        }
    }
}(jQuery), function (e) {
    function t() {
        var t = e("script:first"), o = t.css("color"), i = !1;
        if (/^rgba/.test(o))i = !0; else try {
            i = o != t.css("color", "rgba(0, 0, 0, 0.5)").css("color"), t.css("color", o)
        } catch (n) {
        }
        return i
    }

    function o(t, o, i) {
        var n = "rgb" + (e.support.rgba ? "a" : "") + "(" + parseInt(t[0] + i * (o[0] - t[0]), 10) + "," + parseInt(t[1] + i * (o[1] - t[1]), 10) + "," + parseInt(t[2] + i * (o[2] - t[2]), 10);
        return e.support.rgba && (n += "," + (t && o ? parseFloat(t[3] + i * (o[3] - t[3])) : 1)), n += ")"
    }

    function i(e) {
        var t, o;
        return o = (t = /#([0-9a-fA-F]{2})([0-9a-fA-F]{2})([0-9a-fA-F]{2})/.exec(e)) ? [parseInt(t[1], 16), parseInt(t[2], 16), parseInt(t[3], 16), 1] : (t = /#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])/.exec(e)) ? [17 * parseInt(t[1], 16), 17 * parseInt(t[2], 16), 17 * parseInt(t[3], 16), 1] : (t = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(e)) ? [parseInt(t[1]), parseInt(t[2]), parseInt(t[3]), 1] : (t = /rgba\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9\.]*)\s*\)/.exec(e)) ? [parseInt(t[1], 10), parseInt(t[2], 10), parseInt(t[3], 10), parseFloat(t[4])] : [0, 0, 0, 0]
    }

    e.extend(!0, e, {support: {rgba: t()}});
    var n = ["color", "backgroundColor", "borderBottomColor", "borderLeftColor", "borderRightColor", "borderTopColor", "outlineColor"];
    e.each(n, function (t, n) {
        e.Tween.propHooks[n] = {
            get: function (t) {
                return e(t.elem).css(n)
            }, set: function (t) {
                var r = t.elem.style, a = i(e(t.elem).css(n)), s = i(t.end);
                t.run = function (e) {
                    r[n] = o(a, s, e)
                }
            }
        }
    }), e.Tween.propHooks.borderColor = {
        set: function (t) {
            var r = t.elem.style, a = [], s = n.slice(2, 6);
            e.each(s, function (o, n) {
                a[n] = i(e(t.elem).css(n))
            });
            var l = i(t.end);
            t.run = function (t) {
                e.each(s, function (e, i) {
                    r[i] = o(a[i], l, t)
                })
            }
        }
    }
}(jQuery), !function (e) {
    function t(e, o, n, r) {
        function a(e) {
            n.maxRows && u > n.maxRows || n.truncate && e ? f[s][0].hide() : (f[s][5] && (f[s][4].attr("src", f[s][5]), f[s][5] = ""), f[s][0].css({
                width: l,
                height: m
            }).show())
        }

        var s, l, c = 1, u = 1, d = e.width(), f = [], p = 0, m = n.rowHeight;
        for (i = 0; i < o.length; i++)if (f.push(o[i]), p += o[i][3] + n.margin, p >= d) {
            for (c = d / p, m = Math.ceil(n.rowHeight * c), exact_w = 0, s = 0; s < f.length; s++)l = Math.ceil(f[s][3] * c), exact_w += l + n.margin, exact_w > d && (l -= exact_w - d + 1), a();
            f = [], p = 0, u++
        }
        for (s = 0; s < f.length; s++)l = Math.floor(f[s][3] * c), h = Math.floor(n.rowHeight * c), a(!0);
        r || d == e.width() || t(e, o, n, !0)
    }

    e.fn.flexImages = function (o) {
        var i = e.extend({container: ".item", object: "img", rowHeight: 180, maxRows: 0, truncate: !1}, o);
        return this.each(function () {
            var o = e(this), n = e(i.container, o), r = [], a = n.eq(0), s = (new Date).getTime();
            i.margin = a.outerWidth(!0) - a.innerWidth(), n.each(function () {
                var t = parseInt(e(this).data("w")), o = parseInt(e(this).data("h")), n = t * (i.rowHeight / o), a = e(this).find(i.object);
                r.push([e(this), t, o, n, a, a.data("src")])
            }), t(o, r, i), e(window).off("resize.flexImages" + o.data("flex-t")), e(window).on("resize.flexImages" + s, function () {
                t(o, r, i)
            }), o.data("flex-t", s)
        })
    }
}(jQuery), window.linkify = function () {
    var e = "[a-z\\d.-]+://", t = "(?:(?:[0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(?:[0-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])", o = "(?:(?:[^\\s!@#$%^&*()_=+[\\]{}\\\\|;:'\",.<>/?]+)\\.)+", i = "(?:at|biz|com|ch|co|de|edu|es|eu|fr|gov|info|it|ly|me|mobi|nl|net|org|to|uk|us|ws)", n = "(?:" + o + i + "|" + t + ")", r = "(?:[;/][^#?<>\\s]*)?", a = "(?:\\?[^#<>\\s]*)?(?:#[^<>\\s]*)?", s = "\\b" + e + "[^<>\\s]+", l = "\\b" + n + r + a + "(?!\\w)", c = "mailto:", u = "(?:" + c + ")?[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" + n + a + "(?!\\w)", d = new RegExp("(?:" + s + "|" + l + "|" + u + ")", "ig"), h = new RegExp("^" + e, "i"), f = {
        "'": "`",
        ">": "<",
        ")": "(",
        "]": "[",
        "}": "{",
        "»": "«",
        "›": "‹"
    };
    return function (e, t) {
        t = t || {};
        for (var o, i, n, r, a, s, l, u, p, m, g, v, w = "", x = []; o = d.exec(e);)if (n = o[0], s = d.lastIndex, l = s - n.length, !/[\/:]/.test(e.charAt(l - 1))) {
            do u = n, v = n.substr(-1), g = f[v], g && (p = n.match(new RegExp("\\" + g + "(?!$)", "g")), m = n.match(new RegExp("\\" + v, "g")), (p ? p.length : 0) < (m ? m.length : 0) && (n = n.substr(0, n.length - 1), s--)), n = n.replace(/(?:[!?.,:;'"]|(?:&|&amp;)(?:lt|gt|quot|apos|raquo|laquo|rsaquo|lsaquo);)$/, function (e) {
                return s -= e.length, ""
            }); while (n.length && n !== u);
            r = n, h.test(r) || (r = (-1 !== r.indexOf("@") ? r.indexOf(c) ? c : "" : r.indexOf("irc.") ? r.indexOf("ftp.") ? "http://" : "ftp://" : "irc://") + r), a != l && (x.push([e.slice(a, l)]), a = s), x.push([n, r])
        }
        for (x.push([e.substr(a)]), i = 0; i < x.length; i++)text = x[i][0], r = x[i][1], target = text.indexOf(window.location.hostname) >= 0 ? "" : 'target="_blank" ', w += r ? "<a " + target + 'href="' + r + '">' + text + "</a>" : text;
        return w || e
    }
}();
var ww, wh, pure_menu;
"ontouchstart"in window ? ($(".pure-dropdown>a").click(function () {
    pure_menu = $(this).parent();
    var e = pure_menu.toggleClass("pure-menu-open").find(".pure-menu-children").css("top", pure_menu.height());
    return pure_menu.offset().left + e.outerWidth() > ww && ww > e.outerWidth() ? e.css("left", pure_menu.width() - e.outerWidth()) : e.css("left", 0), !1
}), $(document).mousedown(function (e) {
    !pure_menu || pure_menu.is(e.target) || pure_menu.has(e.target).length || ($(".pure-dropdown").removeClass("pure-menu-open"), pure_menu = !1)
})) : ($(".pure-dropdown>a").mouseenter(function () {
    pure_menu = $(this).parent();
    var e = pure_menu.toggleClass("pure-menu-open").find(".pure-menu-children").css("top", pure_menu.height());
    return pure_menu.offset().left + e.outerWidth() > ww && ww > e.outerWidth() ? e.css("left", pure_menu.width() - e.outerWidth()) : e.css("left", 0), !1
}), $(".pure-dropdown").mouseleave(function () {
    $(this).removeClass("pure-menu-open"), pure_menu = !1
})), $("#trigger_mobile_menu").click(function () {
    if (!$("#mobile_menu").html()) {
        var e = "";
        $(".mm_inc").each(function () {
            e += '<a href="' + this.href + '">' + $(this).text() + "</a>", $(this).hasClass("mm_sep") && (e += "<hr>")
        }), $("#mobile_menu").html(e)
    }
    return $("#mobile_menu").slideToggle(200), !1
});
var ajax_anim = !1, loadingBar = $('<div id="loadingBar"><dt></dt><dd></dd></div>');
loadingBar.appendTo("body").hide(), $.ajaxSetup({
    beforeSend: function () {
        ajax_anim && (loadingBar.css("width", 0), loadingBar.show())
    }, error: function () {
        ajax_anim && loadingBar.css("width", "102%").addClass("error").fadeOut(500, function () {
            loadingBar.removeClass("error").hide()
        })
    }, complete: function (e, t) {
        "error" != t && loadingBar.hide(), ajax_anim = !1
    }, xhr: function () {
        var e = new window.XMLHttpRequest;
        try {
            e.upload.addEventListener("progress", function (e) {
                e.lengthComputable && loadingBar.css("width", 2 + 100 * e.loaded / e.total + "%")
            }, !1), e.addEventListener("progress", function (e) {
                e.lengthComputable && loadingBar.css("width", 2 + 100 * e.loaded / e.total + "%")
            }, !1)
        } catch (t) {
        }
        return e
    }
}), $(document).on("submit", ".ajax_form", function (e) {
    e.preventDefault();
    var form = $(this), t = form.data("target") ? $(form.data("target")) : form;
    ajax_anim = !0, form.ajaxSubmit({
        success: function (re) {
            "script:" == re.substring(0, 7) ? eval(re.substring(7)) : form.data("replace") ? t.replaceWith(re) : t.html(re), resized()
        }
    })
});
var resizeTimer, cboxOptions = {
    width: "95%",
    height: "95%",
    maxWidth: 640,
    maxHeight: 0,
    speed: 180,
    fadeOut: 150,
    closeButton: !1,
    scrolling: !1,
    trapFocus: !1,
    opacity: .5,
    onCleanup: function () {
        $.extend($.colorbox.settings, cboxOptions)
    }
};
$.extend($.colorbox.settings, cboxOptions), $(window).resize(function () {
    clearTimeout(resizeTimer), resizeTimer = setTimeout(resized, 200)
}), $(document).on({
    mousedown: function (e) {
        e.preventDefault();
        var t = $("#colorbox").offset(), o = e.pageX - t.left, i = e.pageY - t.top;
        $(document).on("mousemove.drag", function (e) {
            $("#colorbox").offset({top: e.pageY - i, left: e.pageX - o})
        })
    }, mouseup: function () {
        $(document).unbind("mousemove.drag")
    }
}, "#colorbox h6"), $(document).on("click contextmenu", ".modal", function () {
    var w = $(this).data("w");
    return (null == $(this).data("confirm") || confirm($(this).data("confirm"))) && $.post(this.href || $(this).data("href"), function (re) {
        "script:" == re.substring(0, 7) ? eval(re.substring(7)) : (void 0 != w && ($.colorbox.settings.maxWidth = w), $.colorbox({
            html: re,
            height: "auto",
            onComplete: function () {
                setTimeout(resized, 100), $("#cboxContent h6").prepend('<a href="javascript:$.colorbox.close();">×</a>')
            }
        }))
    }), !1
}), $(document).on("click", ".ajax", function () {
    var link = $(this);
    return (null == link.data("confirm") || confirm(link.data("confirm") || I18N_DELETE)) && (ajax_anim = !0, $.post(this.href || $(this).data("href"), function (re) {
        "script:" == re.substring(0, 7) ? eval(re.substring(7)) : (link.data("target") ? $(link.data("target")) : link).html(re)
    })), !1
}), $(document).on("click dblclick", ".remove_link", function (e) {
    if (null != $(this).data("dblclick") && "click" == e.type)return !1;
    if (null == $(this).data("confirm") || confirm($(this).data("confirm") || I18N_DELETE)) {
        var t = $($(this).data("target"));
        t.fadeOut(200, function () {
            t.remove()
        }), $.post(this.href || $(this).data("href"))
    }
    return !1
}), $(document).on("click", "a[data-confirm]:not(.ajax, .modal, .remove_link)", function () {
    return confirm($(this).data("confirm") || I18N_DELETE) ? void 0 : !1
});
var dd_box, max_zindex = 99999;
$(document).on("click", ".dd_box", function () {
    max_zindex += 1;
    var e = $(this);
    if (dd_box = e.next("div"), dd_box.is(":visible"))dd_box.hide(), dd_box = !1; else {
        $(".dd_box+div").hide();
        var t = e.position().top, o = (t + e.outerHeight(), e.position().left);
        if ((e.data("left") || e.offset().left + dd_box.outerWidth() > ww && e.offset().left + e.outerWidth() - dd_box.outerWidth() > 0) && (o += e.outerWidth() - dd_box.outerWidth()), e.data("up") || e.offset().top + e.outerHeight() + dd_box.outerHeight() > $(document).scrollTop() + wh && e.offset().top - dd_box.outerHeight() > 0)var i = e.position().top - dd_box.outerHeight() - 5; else var i = e.position().top + e.outerHeight() + 5;
        dd_box.unbind().css({position: "absolute", top: i + "px", left: o + "px", zIndex: max_zindex}).show()
    }
    return !1
}), $(document).click(function (e) {
    !dd_box || dd_box.is(e.target) || dd_box.has(e.target).length || (dd_box.hide(), dd_box = !1)
});
var hover_timeout, no_preview = getCookie("no_preview");
$(document).on("mouseenter", ".preview_img", function (e) {
    var o = $(this), i = e.pageX, n = e.pageY;
    if (!no_preview || null != o.data("force-preview")) {
        var r = o.attr("title");
        r && (o.data("title", r), o.attr("title", "")), hover_timeout = setTimeout(function () {
            var e = o.data("width"), r = o.data("height"), a = r, s = o.data("cut"), c = o.data("title");
            if (s && (a += s), !(e + 30 > ww || r + 30 > wh || e + 30 > wh || r + 30 > ww)) {
                //显示当前的open_preview_img 之前,先把原来的open_preview_img 隐藏掉
                $(".open_preview_img").remove();
                preview = $('<div class="open_preview_img" onclick="$(this).remove();" style="z-index:' + (max_zindex + 10) + ';max-width:' + (e + 4) + '"px><div style="position:relative;overflow:hidden;width:' + (e + 2) + "px;height:" + (r + 2) + 'px"><img style="width:' + e + "px;height:" + a + 'px" src="' + o.attr("src") + '"><img style="width:' + e + 'px" src="' + o.data("url") + '"></div>' + (c ? "<em>" + c + "</em>" : "") + "</div>").prependTo("body");
                var u = preview.outerWidth(), d = preview.outerHeight(), h = o.offset(), f = h.left, p = h.top, m = o.width(), g = o.height(), v = f + m / 2, w = p + g / 2, x = $(document).scrollTop();
                v > ww / 2 && f >= u ? (l = f - 15 - u, t = w - d / 2) : ww / 2 >= v && ww >= f + m + u ? (l = f + 15 + m, t = w - d / 2) : w > x + wh / 2 ? (t = p - 15 - d, l = v - u / 2) : (t = p + 15 + g, l = v - u / 2), t < x ? t = x + 5 : t + d > x + wh && (t = x + wh - d - 5), l < 0 ? l = 5 : l + u > ww && (l = ww - u - 5), i > l && i < l + u && n > t && n < t + d ? preview.css({
                    display: "block",
                    top: t,
                    left: l
                }).mouseleave(function () {
                    $(this).remove()
                }) : (preview.css({display: "block", top: t, left: l}), o.one("mouseleave", function () {
                    $(".open_preview_img").remove()
                }))
            }
        }, 350)
    }
}), $(document).on("mouseleave", ".preview_img", function () {
    hover_timeout && clearTimeout(hover_timeout)
});
var ua = navigator.userAgent.toLowerCase(), is_ie = ~ua.indexOf("msie ") || ~ua.indexOf("trident/"), is_safari = ~ua.indexOf("safari") && !~ua.indexOf("chrome");
$(function () {
    function e() {
        var e = $(".fixed_grid").width(), t = Math.floor(e / 160), o = Math.floor((e - 160 * t) / (t - 1)) + 11;
        return 480 > e ? void $(".fixed_grid a, #sponsored_images a").css("margin-right", 10) : ($(".fixed_grid a").css("margin-right", o), $(".fixed_grid > a:nth-child(" + t + "n)").css("margin-right", 0), $("#sponsored_images a").css("margin-right", o - 1), void $("#sponsored_images > a:nth-child(" + t + "n)").css("margin-right", 0))
    }

    if (resized(), $("#header label").click(function () {
            $(this).closest("form").attr("action", "/" + LANG + "/" + $(this).data("t") + "/").find('[type="text"]').focus()
        }), $(".search_xl .image_type_filter label").click(function () {
            $("#image_type").html($(this).text())
        }), (is_ie || is_safari) && $("[placeholder]").livequery(function () {
            $(this).focus(function () {
                var e = $(this);
                e.hasClass("placeholder") && e.caret(0)
            }).click(function () {
                var e = $(this);
                e.hasClass("placeholder") && e.caret(0)
            }).keydown(function () {
                var e = $(this);
                e.hasClass("placeholder") && (e.val(""), e.removeClass("placeholder"))
            }).blur(function () {
                var e = $(this);
                ("" == e.val() || e.val() == e.attr("placeholder")) && e.addClass("placeholder").val(e.attr("placeholder"))
            }).blur().parents("form").submit(function () {
                $(this).find("[placeholder]").each(function () {
                    $(this).val() == $(this).attr("placeholder") && $(this).val("")
                })
            })
        }), $(".autosize").livequery(function () {
            $(this).autosize({
                placeholder: 0, callback: function () {
                    setTimeout(resized, 100)
                }
            })
        }), ~$.inArray(/*LANG*/'zh', ["de", "en", "es", "fr", "it", "ja", "ko", "pt"]) && $('.search_xl input[name="q"]').autoComplete({
            onSelect: function () {
                $(".search_xl").submit()
            }, source: function (e, t) {
                try {
                    xhr.abort()
                } catch (o) {
                }
                xhr = $.ajax({
                    url: "/es_suggest/",
                    type: "POST",
                    dataType: "json",
                    data: JSON.stringify({my_suggest: {text: e, completion: {field: "suggest_" + LANG, size: 8}}}),
                    success: function (e) {
                        var o = [];
                        $.each(e.my_suggest[0].options, function () {
                            o.push(this.text)
                        }), t(o)
                    }
                })
            }
        }), $(document).on("mousedown", "[data-go]", function () {
            return $(this).attr("href", "/go/?t=" + $(this).data("go")), -1 == $(this).data("go").indexOf(window.location.hostname) && "/" != $(this).data("go").charAt(0) && $(this).attr("target", "_blank"), !1
        }), $("#toTop").click(function () {
            $("body,html").animate({scrollTop: 0})
        }), $(window).scroll(function () {
            $(this).scrollTop() > 480 ? $("#toTop").fadeIn() : $("#toTop").fadeOut("slow")
        }), $(document).on("click", ".translate", function () {
            var e = "http://translate.google.de/#auto|" + LANG + "|" + encodeURIComponent($($(this).data("el")).text().substring(0, 999));
            return wopen(e, 800, 500, "translate")
        }), $(".linkify").each(function () {
            $(this).html(linkify($(this).html()))
        }), $("#photo_list").length) {
        $("#paginator_clone").html($(".paginator").clone()), no_preview || $(".toggle_preview").prop("checked", !0), $(document).on("click", ".toggle_preview", function () {
            no_preview = !$(this).is(":checked"), setCookie("no_preview", no_preview ? 1 : "", 365), $(".toggle_preview").prop("checked", !no_preview)
        });
        var t = getCookie("g_rated");
        t && $(".toggle_g_rated").prop("checked", !0), $(document).on("click", ".toggle_g_rated", function () {
            t = $(this).is(":checked"), setCookie("g_rated", t ? 1 : "", 365), $(".toggle_g_rated").prop("checked", t)
        }), $("#photo_grid").length ? ($("#photo_grid").flexImages(), $(".flex_grid.sponsored").flexImages({maxRows: 1})) : (e(), $(window).resize(e))
    } else $("#photo_show").length ? (setTimeout(function () {
        $("#photo_container").removeClass("init")
    }, 3500), $("#photo_container").mouseleave(function () {
        $(this).removeClass("init")
    }), $("#contrast, #dimmer").click(function () {
        $("#header .pure-menu").toggleClass("dimmed"), $("#dimmer").fadeToggle(300)
    }), $("#photo_container img").click(function () {
        $("#photo_container .bubble").fadeToggle(150)
    }), $("#download tr").click(function () {
        $("#download tr").removeClass("selected");
        var e = $(this).addClass("selected").find("input").eq(0);
        e.prop("checked", !0);
        var t = e.val(), o = dl_urls[t];
        $(".dl_btn").attr("href", o + "?attachment"), $(".view_btn").attr("href", o), show_captcha && "640" != t || !authenticated && ~$.inArray(t, ["full", "psd", "ai", "svg"]) ? $(".dl_btn, .view_btn").addClass("modal") : $(".dl_btn, .view_btn").removeClass("modal")
    }), $(".dl_btn, .view_btn").click(function () {
        ww > 380 && (!function (e, t, o) {
            var i, n = e.getElementsByTagName(t)[0];
            e.getElementById(o) || (i = e.createElement(t), i.id = o, i.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=380275075435187&version=v2.0", n.parentNode.insertBefore(i, n))
        }(document, "script", "facebook-jssdk"), function () {
            var e = document.createElement("script");
            e.type = "text/javascript", e.async = !0, e.src = "https://apis.google.com/js/plusone.js";
            var t = document.getElementsByTagName("script")[0];
            t.parentNode.insertBefore(e, t)
        }()), $(this).hasClass("modal") || $("#thanks").fadeIn(), $("#photo_container .bubble").fadeOut(150)
    }), $("#download tr:not(.no_default)").last().click(), $("#similar_photos .flex_grid").flexImages({
        rowHeight: 85,
        maxRows: 1
    }), $("#similar_photos_sidebar .flex_grid").flexImages({
        rowHeight: 85,
        maxRows: 2
    }), $(".flex_grid.sponsored").flexImages({
        rowHeight: 100,
        maxRows: 2
    })) : $(".signup_form.new").length && $.get("/" + LANG + "/accounts/register/ets/", function (e) {
        $(".signup_form .data").each(function () {
            var t = "f-" + e + "-" + $(this).attr("name");
            $(this).attr("name", t) || $(this).prop("name", t)
        }), $(".signup_form.new").show()
    });
    $("#account_tabs").length && $.getScript("/static/js/base_auth.js")
});