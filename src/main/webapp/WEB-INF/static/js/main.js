/**
 * Created by Administrator on 2015/6/23.
 */
console.log('appstore main.js');
//var gov_server_url = 'http://127.0.0.1:8080/tv_mobile/stub';
var gov_server_url = 'http://i.chanjet.com';
var basePath = 'http://www.static.chanjet.com';
var invoiceSelectedIndex;
var partnerFullName;
var partnerId;
var storagePrice;
var userPeriodPrice;
var totalPrice;
var $main_content = null;
var orgFullName;
var selectedOrgId;
var order_owner = {};
var invoiceSelectedIndex;
var $header;
var $floating_layer;
var userCountVal;
var expansion_storageVal;
var willEndTime;
var default_userPeriod = 12;
var orderNo;
var productId = 250;
var trialInfoBean;
$.ajax({
    url: 'http://cia.chanapp.chanjet.com/internal_api/authorizeByJsonp?client_id=chanjetapp',
    type: 'GET',
    dataType: 'jsonp',
    data: {jsonp: true},
    success: function (obj) {
        console.log('cia jsonp:', obj);
        if (obj.code) {
            after_login();
        }
        else if (obj.auth_code) {
            authCode = obj.auth_code;
            before_login();
        }
    }
})

var after_login = function () {
    console.log('after_login');
    $('body').delegate('ul.com-enable li.default', 'click', function () {
        var $self = $(this);
        unSelectAllRadio();
        $self.addClass("selected").removeClass('disable');
        submitBtnTrigger();
    });
    $('body').delegate('span.no-ivoice-icon', 'click', function () {
        console.log('click invoice');
        var $self = $(this);
        var $parent = $self.parent();
        if (!$parent.hasClass('invoice-infor')) {
            $('div.invoice-infor').removeClass('invoice-infor').addClass('no-invoice');
            $parent.addClass('invoice-infor').removeClass('no-invoice');
        }
    });
    //1:跳转到收银台;2:支付结果;3:试用
    if (window.actionType == 1) {
        console.log('跳转到收银台');
        autoJumpPay();
    } else if (window.actionType == 2) {
        console.log('支付结果');
        var options = {
            url: gov_server_url + '/store/orderStatus?queryType=orderNo&params=["' + orderNo + '"]',
            type: "POST",
            dataType: 'json',
            success: function (json2) {
                console.log(json2);
                if (!json2.result) {
                    console.log(json2.errorMessage);
                    console.log(json2.error);
                }
                var value2 = json2.value[0];

                var orderStatus = value2.orderStatus;
                if (orderStatus == 2 || orderStatus == 3 || orderStatus == 4) {
                    var templateHandleOrderResult = function (html2, templateData) {
                        var evalText = doT.template(html2);
                        $main_content.html(evalText(templateData));
                    };
                    xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/pay_succ.html', $header, templateHandleOrderResult, null, value2);
                } else {

                }

            },
            error: function (er) {
                console.log(er);
                alert(er.responseText);
            }
        };
        $.ajax(options);
    } else if (window.actionType == 3) {
        console.log('试用');
        //autoJumpPay();
        orgList4Trial();
    } else {
        layoutStepOne();
    }
    $main_content = $('#main_content');
};
var autoJumpPay = function () {
    console.log('orderNo:' + orderNo)
    var options = {
        url: gov_server_url + "/store/startPayOrder?orderNo=" + orderNo,
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            console.log(json2);

            var orderInfoMap = json2.orderInfoMap;
            var html2 = [];
            orderInfoMap.sign = json2.sign;
            orderInfoMap.plain = json2.plain;
            orderInfoMap.signType = 0;
            orderInfoMap.gateId = '';
            var $order_form = $('<form style="display: block;" action="http://59.151.72.42:2917/gateway/gatewayManage.do?action=paymentManage" id="order_form" method="POST"> </form>')
            orderNo = json2.orderNo;
            console.log('orderNo:' + orderNo);
            orderInfoMap.version = 'v1.0';
            for (var i in orderInfoMap) {
                console.log(i + '-->' + orderInfoMap[i]);
                var val = orderInfoMap[i];
                if (i == 'sign') {
                    i = 'signMsg';
                }
                if (i == 'payeeName') {
                    continue;
                }
                if (i == 'productNum') {
                    continue;
                }
                if (i == 'payeeBankName') {
                    continue;
                }
                if (i == 'payeeBankType') {
                    continue;
                }
                if (i == 'payeeBankAccount') {
                    continue;
                }
                html2.push('<label>' + i + ':</label> <input  name="' + i + '" value="' + val + '" >');
            }
            $order_form.html(html2.join('<br>')).append('<input type="submit" value="提交" >');
            $main_content.append($order_form);
            $order_form.submit();
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };
    $.ajax(options);
}
var before_login = function () {
    console.log('before_login');
    location.href = gov_server_url + '/login?app=appstore&callback=' + gov_server_url + '/store/index.html';
};
var layoutStepOne = function () {
    $header = $('#header');
    $floating_layer = $('#floating_layer');
    xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/header.html', $header, null, null, null);


    var templateHandle = function (html2, templateData) {
        var evalText = doT.template(html2);
        $main_content.html(evalText(templateData));
        var orgAppRelList = templateData.orgAppRelList;
        var productPrice = templateData.productPrice;
        storagePrice = productPrice.normalPriceDto.storagePrice;
        userPeriodPrice = productPrice.normalPriceDto.userPeriodPrice;

        var $userCount = $('input[name=userCount]');
        var $expansion_storage = $('input[name=expansion_storage]');
        $userCount.keyup(function (event) {
            eventUtil.onlyNumberKeyUp(event);
            submitBtnTrigger();
        });
        $expansion_storage.keyup(function (event) {
            eventUtil.onlyNumberKeyUp(event);
            submitBtnTrigger();
        });
        var $partnerCode = $('input[name=partnerCode]');
        $partnerCode.keyup(checkPartnerTrigger);

        //设置用户名和用户头像
        $('.head-name').text(templateData.userNickName);
        var userPictUrl = templateData.userPictUrl;
        if (!userPictUrl) {
            userPictUrl = basePath + '/chanjet/images/workbench/user.png';
        }
        $('#headPictrue').attr('src', userPictUrl)
    };
    xhr.get('/appstore/orderContent', 'productId=' + productId, function (data) {
        console.log(data);
        data.productPrice.userPeriod = default_userPeriod;//默认使用时长
        data.productPrice.endTime = willEndTime;
        xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/select_serviceitem.html', $header, templateHandle, null, data);
    }, function (er) {
        console.log(er);
        if (!er.result) {
            console.log('请重新登录');
            before_login();
        }
    });

};

var setErrerMessageAndFocus = function ($error_span, $orgFullName, message) {
    $error_span.html(message);
    $orgFullName.focus();
}
var createOrg = function (self) {
    var $orgFullName = $('input[name=orgFullName]');
    var orgFullName_val = $orgFullName.val();
    var $error_create_org = $('#error_create_org');
    if (!orgFullName_val) {
        setErrerMessageAndFocus($error_create_org, $orgFullName, '请输入企业名称');
        return;
    }
    if (!trim(orgFullName_val)) {
        setErrerMessageAndFocus($error_create_org, $orgFullName, '企业名称不能为空格');
        return;
    }
    var options = {
        url: gov_server_url + "/organization/create",
        data: {'orgFullName': orgFullName_val},
        type: "POST",
        dataType: 'jsonp',
        success: function (json2) {
            console.log(json2);
            if (json2.result) {
                console.log('add success');
                selectedOrgId = json2.orgId;
                var html = '<ul class="company-list com-enable" data-orgid="' + json2.orgId + '" >' +
                    '<li class="choice-box selected"></li>' +
                    '<li class="company-name"><span>' + orgFullName_val + '</span></li>' +
                    '<li class="company-state"><span>未购买</span></li>' +
                    '</ul>';
                unSelectAllRadio();
                $(self).parent().parent().before(html)
                //$('#orgListDiv').prepend(html);
                submitBtnTrigger();//触发检查"提交订单"按钮是否可用
                $orgFullName.val('');
                $error_create_org.html('');//清除错误提示
            } else {
                //$error_create_org.html();
                setErrerMessageAndFocus($error_create_org, $orgFullName, json2.errorMessage);
            }

        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);
};
var unSelectAllRadio = function () {
    var $choice_box = $('#orgListDiv li.selected');
    $choice_box.removeClass('selected').addClass('default');
};
var order_submit_step1 = function (self) {
    var $self = $(self);
    if ($self.hasClass('disable')) {//判断按钮是否可以点击
        return;
    }
    checkOrderForm();
    console.log('partnerFullName:' + partnerFullName);
    console.log('partnerId:' + partnerId);
    var $selectedOrgLi = $('#orgListDiv ul li.choice-box.selected');
    selectedOrgId = $selectedOrgLi.parent().data('orgid');
    console.log('selectedOrgId:' + selectedOrgId);
    orgFullName = $selectedOrgLi.next().text();
    console.log('org FullName:' + orgFullName);
    console.log('storagePrice:' + storagePrice);
    console.log('userPeriodPrice:' + userPeriodPrice);
    var $partnerCode = $('input[name=partnerCode]');
    var partnerCodeVal = $partnerCode.val();
    if (partnerCodeVal) {//当partnerCodeVal 为空 或者查询到经销商时,才能提交
        var options = {
            url: gov_server_url + "/store/findPartner",
            type: "POST",
            dataType: 'jsonp',
            data: {"code": partnerCodeVal}, /*bug#CHANJET-474*/
            success: function (json2) {
                console.log(json2);
                if (json2.value && json2.value.orgFullName) {
                    window.partnerFullName = json2.value.orgFullName;
                    window.partnerId = json2.value.partnerId;
                    $('#partnerFullName').text('服务商：' + partnerFullName);
                    $('#partnerFullName').removeClass('warning');
                    layoutStepTwo();
                } else {
                    $('#partnerFullName').text('不存在');
                    $('#partnerFullName').addClass('warning');

                }

                //json2.result=false;

            },
            error: function (er) {
                console.log(er);
                alert(er.responseText);
            }
        };

        $.ajax(options);
    } else {
        layoutStepTwo();
    }

    return false;
};
var order_submit_step2 = function () {
    var $selectedInvoiceDiv = $('div.invoice .invoice-infor ');
    invoiceSelectedIndex = $selectedInvoiceDiv.data('index');
    console.log(invoiceSelectedIndex);
    var orderSubmitData = {};
    orderSubmitData.orderItemId = '001';
    orderSubmitData.itemType = 1;
    orderSubmitData.orgId = selectedOrgId;
    orderSubmitData.orgFullName = orgFullName;
    orderSubmitData.productId = window.productId;
    orderSubmitData.priceId = 610  /*258*/;
    orderSubmitData.pricePolicy = 0;
    orderSubmitData.priceType = 1;
    orderSubmitData.period = default_userPeriod;
    orderSubmitData.user = userCountVal;
    orderSubmitData.storage = expansion_storageVal;
    orderSubmitData.payType = 1;
    orderSubmitData.orderTotal = totalPrice;
    orderSubmitData.needInvoice = false;
    orderSubmitData.productName = 'customer';//不能为中文
    if (window.invoiceSelectedIndex != -1) {
        var invoiceItem = invoiceInfoDtos[invoiceSelectedIndex];
        orderSubmitData.title = invoiceItem.title;
        orderSubmitData.receiver = invoiceItem.receiver;
        orderSubmitData.address = invoiceItem.address;
        orderSubmitData.phone = invoiceItem.phone;
        orderSubmitData.personal = false;
    }
    var options = {
        url: gov_server_url + "/store/createOrder?charset=utf-8",
        data: orderSubmitData,
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            console.log(json2);

            var templatePayHandle = function (html2, templateData) {
                var evalText = doT.template(html2);
                $main_content.html(evalText(templateData));
            };
            var data = {};
            data.totalPrice = window.totalPrice;
            //data.totalPrice=window.totalPrice;
            window.orderNo = json2.orderNo;
            data.orderNo = window.orderNo;
            xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/pay.html', $main_content, templatePayHandle, null, data);
            //$order_form.submit();
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };
    $.ajax(options);
}
var templateHandleInvoice = function (html2, templateData) {
    var $html2 = $(html2);
    var htmlTemplate = $html2.find('#invoiceListDot').html();
    var evalText = doT.template(html2);
    var productInfo2 = {};
    productInfo2.productName = '客户管家';
    productInfo2.serviceItem = '<p>有效期：一年</p><p>用户数：' + userCountVal + '人</p><p>储存空间：' + expansion_storageVal + 'G</p>';
    productInfo2.willEndTime = window.willEndTime;
    productInfo2.oldPrice = window.totalPrice;
    productInfo2.realPrice = window.totalPrice;
    templateData.productInfo = productInfo2;
    $main_content.html(evalText(templateData));
    invoiceInfoDtos = templateData.invoiceInfoDtos;
    $('#invoiceListDiv').html($('#invoiceListDot').html());
    $('#invoiceListDot').text(htmlTemplate);

};
layoutStepTwo = function () {
    console.log('进入步骤二');
    var data = {
        'userPeriod': 1/*TODO */,
        'productId': productId
    };
    xhr.get('/store/stepTwo', data, function (data) {
        console.log(data);
        order_owner.orgFullName = window.orgFullName;
        order_owner.buyer = '张三';//TODO
        order_owner.orderType = 2;//1:试用,2购买
        if (partnerId) {
            order_owner.partnerFullName = partnerFullName;
        }
        data.order_owner = order_owner;
        willEndTime = data.willEndTime;
        xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/order_confirm_invoice.html', $main_content, templateHandleInvoice, null, data);
        xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/popup_invoice.html', $floating_layer, null, repaintSelect, null);
    });
};
/***
 * 触发检查"提交订单"按钮是否可用
 */
var submitBtnTrigger = function () {
    console.log('submitBtnTrigger');
    var isValid = checkOrderForm();
    var $submitOrderOneStep = $('#submitOrderOneStep');
    if (isValid) {
        $submitOrderOneStep.addClass('enable').removeClass('disable');
    } else {
        $submitOrderOneStep.removeClass('enable').addClass('disable');
    }
    if (window.actionType == 3) {//试用
        var $trialSubmit = $('#trialSubmit');
        var $selectedOrg = $('ul.com-enable li.choice-box.selected');
        //console.log($selectedOrg.length);
        if ($selectedOrg.length == 0) {
            console.log('没有选中的');
        } else {
            console.log('有选中的');
            $trialSubmit.addClass('enable').removeClass('disable');
        }
    }
};
var closeInvoiceDialog = function () {
    $('div.popup').hide();
    $('div.popup-bg').hide();
};
/****
 * 决定[提交]按钮是否可用
 * @returns {boolean}
 */
var checkOrderForm = function () {
    var $selectedOrg = $('ul.com-enable li.choice-box.selected');
    //console.log($selectedOrg.length);
    if ($selectedOrg.length == 0) {
        return false;
    }

    var $userCount = $('input[name=userCount]');
    var $expansion_storage = $('input[name=expansion_storage]');
    userCountVal = $userCount.val();
    expansion_storageVal = $expansion_storage.val();
    console.log('用户数:' + userCountVal);
    console.log('扩容空间:' + expansion_storageVal);
    if (!userCountVal || !expansion_storageVal) {
        return false;
    }
    if (userCountVal === '0') {
        console.log('用户数不能为零');
        return false;
    }
    if (Number(userCountVal) > 200) {//目前只支持200人以内的团队
        return false;
    }
    var userCountValNumber = Number(userCountVal);
    window.totalPrice = userCountValNumber * userPeriodPrice * default_userPeriod + Number(expansion_storageVal) * storagePrice;
    var freeStoragePerUser = $('#freeStoragePerUserLabel').text();
    var totalfreeStoragePerUser = Number(freeStoragePerUser) * userCountValNumber;
    $('#totalFreeStoragePerUserLabel').text(totalfreeStoragePerUser);
    if (window.totalPrice) {
        $('#totalPriceSpan').text('￥' + window.totalPrice + '.00');
    }
    return true;
};
var checkPartnerTrigger = function () {
    var $partnerCode = $('input[name=partnerCode]');
    var isValid = checkPartnerCode($partnerCode);
    var $checkPartnerCodeHref = $('#checkPartnerCodeHref');
    if (isValid) {
        $checkPartnerCodeHref.removeClass('disable');
    } else {
        $checkPartnerCodeHref.addClass('disable');
        $('#partnerFullName').html('');//清空错误提示
    }
};
var checkPartnerCode = function ($partnerCode) {
    if (!$partnerCode) {
        $partnerCode = $('input[name=partnerCode]');
    }
    var partnerCodeVal = $partnerCode.val();
    if (partnerCodeVal) {
        return true;
    } else {
        return false;
    }
};
var queryPartnerInfo = function (self) {
    var $self = $(self);
    if ($self.hasClass('disable')) {//判断按钮是否可以点击
        return;
    }
    console.log('query Partner');
    var $partnerCode = $('input[name=partnerCode]');
    var partnerCodeVal = $partnerCode.val();
    var options = {
        url: gov_server_url + "/store/findPartner",
        type: "POST",
        data: {"code": partnerCodeVal},
        dataType: 'jsonp',
        success: function (json2) {
            console.log(json2);
            if (json2.value) {
                window.partnerFullName = json2.value.orgFullName;
                window.partnerId = json2.value.partnerId;
                $('#partnerFullName').text('服务商：' + partnerFullName);
            } else {
                $('#partnerFullName').text('没有查询到服务商');
            }

            //json2.result=false;

        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);
};
var showInvoiceDialog = function () {
    $('div.popup').show();
    $('div.popup-bg').show();
};
var showInvoiceError = function (message) {
    $('#error_invoice_span').text(message);
};
var dealInvoiceError = function ($formItem, message) {
    if (!$formItem.val()) {
        showInvoiceError(message);
        $formItem.focus();
    }
};
var store = {};
var query = function () {
    var options = {
        url: gov_server_url + "/store/payResult",
        type: "POST",
        data: {"orderNo": orderNo},
        dataType: 'jsonp',
        success: function (json2) {
            console.log(json2);
            if (json2.payResult) {
                if (store.setTimeoutHook) {
                    clearInterval(store.setTimeoutHook);//清除定时器
                }
                if (json2.payResult == '00') {
                    console.log('支付完成');
                    closeInvoiceDialog();//关闭浮层
                    location.href = gov_server_url + '/store/index.html?actionType=2&orderNo=' + orderNo

                } else {
                    console.log('支付失败');
                    $('#pay_success').hide();
                    $('#pay_failed').show();
                }

            } else {
                //console.log('结果还没有出来,继续轮询');
            }
        },
        error: function (er) {
            console.log(er);
            //alert(er.responseText);
        }
    };

    $.ajax(options);
};
var jumpPayCashier = function () {

    var queryPayResult = function (html2, templateData) {//回调方法
        //$floating_layer.html(html2);
        //轮询访问支付结果
        store.setTimeoutHook = setInterval(query, 2000);
        $('.popup-bg').show();
    };
    xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/are_paying.html', $floating_layer, null, queryPayResult, null);
    window.open(gov_server_url + '/store/index.html?actionType=1&orderNo=' + orderNo);
};
var reTryPay = function () {
    window.open(gov_server_url + '/store/index.html?actionType=1&orderNo=' + orderNo);
    store.setTimeoutHook = setInterval(query, 2000);
    $('#pay_success').show();
    $('#pay_failed').hide();
};
var saveInvoiceClick = function (self) {
    var $select_info_invoice_provinc = $('#select_info_invoice_provinc');
    var $select_info_invoice_city = $('#select_info_invoice_city');
    var $select_info_invoice_county = $('#select_info_invoice_county');
    var $address_detail = $('input[name=address_detail]');
    var $receiver = $('input[name=receiver]');
    var $phone = $('input[name=phone]');
    var $invoice_title = $('input[name=invoice_title]');
    var invoice_title_val = $invoice_title.val();
    var phone_val = $phone.val();
    var receiver_val = $receiver.val();
    var address_suffix = $select_info_invoice_provinc.text() + $select_info_invoice_city.text() + $select_info_invoice_county.text() + $address_detail.val();
    console.log('address:' + address_suffix);
    console.log('receiver:' + receiver_val);
    console.log('phone:' + phone_val);
    console.log('invoice_title:' + invoice_title_val);
    if (!invoice_title_val) {
        dealInvoiceError($invoice_title, '请输入合法的企业名称22');
        return;
    }
    if (!$address_detail.val()) {
        dealInvoiceError($address_detail, '请输入详细地址');
        return;
    }
    if (!receiver_val) {
        dealInvoiceError($receiver, '请输入收件人');
        return;
    }
    if (!phone_val) {
        dealInvoiceError($phone, '请输入联系方式');
        return;
    }
    var data = {
        "title": invoice_title_val,
        "type": 1,
        address: address_suffix,
        receiver: receiver_val,
        phone: phone_val
    }
    var options = {
        url: gov_server_url + "/store/newInvoice?charset=utf-8",
        data: data,
        type: "POST",
        dataType: 'jsonp',
        success: function (json2) {
            console.log(json2);
            if (json2.result) {
                console.log('add invoice success');
                closeInvoiceDialog();
                queryInvoiceList();
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);
};
var queryInvoiceList = function () {
    /* var data = {
     "title": invoice_title_val,
     "type": 1,
     address: address_suffix,
     receiver: receiver_val,
     phone: phone_val
     };*/
    var options = {
        url: gov_server_url + "/store/findInvoice?charset=utf-8",
        type: "POST",
        dataType: 'jsonp',
        success: function (json2) {
            console.log(json2);
            if (json2.result && json2.invoiceInfoDtos) {
                console.log('query invoice success');
                window.invoiceInfoDtos = json2.invoiceInfoDtos;
                var length = invoiceInfoDtos.length;
                if (length) {
                    var $invoiceListDiv = $('#invoiceListDiv');
                    var $invoiceListDot = $('#invoiceListDot');
                    /*var html=[];
                     for(var i=0;i<length;i++){
                     html.push()
                     }*/
                    var tmplate = $invoiceListDot.text();//IE8中返回空字符串
                    if (!tmplate) {
                        console.log('$invoiceListDot.text() return null');
                        tmplate = $invoiceListDot.html();
                    }
                    //console.log(tmplate);
                    var evalText = doT.template(tmplate);
                    $invoiceListDiv.html(evalText(json2));
                }
            }
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);
};
var orgList4Trial = function () {
    xhr.get('/appstore/orderContent', 'productId=' + window.productId, function (data) {
        trialInfoBean = data.productPrice.trialInfoBean;
        console.log(trialInfoBean);
        var $orgList = $('#orgListDiv');
        var $orgListTemplate = $('#orgListTemplate');
        /*var html=[];
         for(var i=0;i<length;i++){
         html.push()
         }*/
        window.userCountVal = trialInfoBean.trialAmount.user;
        window.expansion_storageVal = trialInfoBean.trialAmount.storage;
        window.default_userPeriod = trialInfoBean.trialAmount.period;
        var $serviceItem = $('#serviceItem');
        var $serviceItemTemplate = $('#serviceItemTemplate');


        var templateHandle22 = function (html2, templateData) {
            var evalText = doT.template(html2);
            $main_content.html(evalText(templateData));
        };
        xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/trial.html', $header, templateHandle22, null, data.orgAppRelList);
    });

};
doTry = function (self) {
    var $self = $(self);
    if ($self.hasClass('disable')) {//判断按钮是否可以点击
        console.log('不可点击');
        return;
    }
    var $selectedOrgLi = $('#orgListDiv ul li.choice-box.selected');
    selectedOrgId = $selectedOrgLi.parent().data('orgid');
    console.log('selectedOrgId:' + selectedOrgId);
    orgFullName = $selectedOrgLi.next().text();
    var orderSubmitData = {};
    orderSubmitData.orderItemId = '001';
    orderSubmitData.itemType = 1;
    orderSubmitData.orgId = selectedOrgId;
    orderSubmitData.orgFullName = orgFullName;
    orderSubmitData.productId = window.productId;
    orderSubmitData.priceId = 610 /*258*/;
    orderSubmitData.pricePolicy = 0;
    orderSubmitData.priceType = 2;
    orderSubmitData.period = trialInfoBean.trialAmount.period;
    orderSubmitData.user = trialInfoBean.trialAmount.user;//window.userCountVal;
    orderSubmitData.storage = trialInfoBean.trialAmount.storage;// window.expansion_storageVal;
    orderSubmitData.payType = 1;
    orderSubmitData.orderTotal = 0;
    orderSubmitData.needInvoice = false;
    orderSubmitData.productName = 'customer';//不能为中文
    orderSubmitData.isTrial = true;//不能为中文

    var options = {
        url: gov_server_url + "/store/createOrder",
        data: orderSubmitData,
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            console.log(json2);

            var templatePayHandle = function (html2, templateData) {
                var data2 = {};
                var items = [];
                var amountInfoBean = {};
                var amountInfoBean2 = {};
                amountInfoBean2.amountInfoBean = amountInfoBean;
                amountInfoBean.amount = {};
                amountInfoBean.amount.user = trialInfoBean.trialAmount.user;
                amountInfoBean.amount.storage = trialInfoBean.trialAmount.storage;

                items.push(amountInfoBean2);
                data2.items = items;
                data2.orgFullName = window.orgFullName;
                data2.endTime = templateData.endTime;
                var evalText = doT.template(html2);
                $main_content.html(evalText(data2));
            };
            var data = {};
            data.totalPrice = window.totalPrice;
            //data.totalPrice=window.totalPrice;
            window.orderNo = json2.orderNo;
            data.orderNo = window.orderNo;

            xhr.ajaxHtml(basePath + '/chanjet/js/appstore/template/trial_succ.html', $main_content, templatePayHandle, null, json2);
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };
    $.ajax(options);

};
/* $(function () {
 after_login();
 })*/
