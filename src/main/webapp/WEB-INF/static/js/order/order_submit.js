/**
 * Created by Administrator on 2015/7/2.
 */
var server_url="http://i.chanjet.com";
var submitOrder = function () {
    $order_form = $('#order_form');
    var options = {
        url: server_url+"/store/createOrder?charset=utf-8",
        data: "orderItemId=001&itemType=1&orgId=90000728881&productId=45&priceId=258&pricePolicy=0&priceType=1&period=1&user=1&storage=0&payType=1&orderTotal=99&needInvoice=false&productName=11&access_token=8052c0e6-b89d-462c-b836-3c6793b4da0b&appKey=c3e6ac00-96db-11e4-a5ed-180373f5f6fc&client_id=chanjetapp",
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
            $order_form.html(html2.join('<br>'));
            //$order_form.submit();
        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);

    //console.log($order_form);
    /*$submitBtn.val("正在注册...");
     com.whuang.hsj.disAbled($submitBtn.get(0));
     showMask();
     if (!$thisForm.ajaxSubmit) {
     formAjaxSubmit(jQuery);
     }*/
}

var queryOrder = function () {
    var $orgId = $('input[name=orgId]');
    var $orderId = $('input[name=orderId]');
    var orgIdVal = $orgId.val();
    var orderIdVal = $orderId.val();
    var queryType;
    var params;
    if (orderIdVal) {
        queryType = 'orderNo';
        params = orderIdVal;
    } else if (orgIdVal) {
        queryType = 'orgId';
        params = orgIdVal;
    }
    var $queryResultDiv = $('#queryResultDiv');

    var options = {
        url: server_url+"/store/findAllOrder",
        data: 'queryType=' + queryType + '&params=["' + params + '"]&access_token=78e53d1b-e326-4c43-b081-7169bb5f9e73&appKey=c3e6ac00-96db-11e4-a5ed-180373f5f6fc&client_id=chanjetapp',
        type: "POST",
        dataType: 'json',
        success: function (json2) {
            console.log(json2);
            if (json2.result) {
                var value22 = json2.value;
                var length22 = value22.length;
                var html = [];
                html.push('<table border="1"><tr><th>序号</th><th>订单号</th><th>商品id</th><th>创建时间</th><th>开始时间</th><th>截止时间</th><th>状态</th><th>总价</th><th>服务项</th><th>企业id</th><th>是否需要发票</th><th>用户ID</th></tr>');
                for (var i = 0; i < length22; i++) {
                    html.push(getNewsListItem(value22[i], (i + 1)));
                }
                html.push('</table>');
                $queryResultDiv.html(html.join(''));
            } else {
                $queryResultDiv.html('发生错误:' + JSON.stringify(json2));
            }

        },
        error: function (er) {
            console.log(er);
            alert(er.responseText);
        }
    };

    $.ajax(options);
};

var getNewsListItem = function (oneValue, index2) {
    var trHtml = '<tr><td>';
    trHtml += index2;
    trHtml += '</td><td>';
    trHtml += oneValue.orderNo;
    trHtml += '</td><td>';
    trHtml += oneValue.productId;
    trHtml += '</td><td>';
    trHtml += oneValue.createTime;
    trHtml += '</td><td>';
    if (oneValue.startTime) {
        trHtml += oneValue.startTime;
    }
    trHtml += '</td><td>';
    if (oneValue.endTime) {
        trHtml += oneValue.endTime;
    }
    trHtml += '</td><td>';
    var orderStatusStr;
    if (oneValue.orderStatus == 1) {
        orderStatusStr = '<font color="red">未支付</font> ';
    } else if (oneValue.orderStatus == 2) {
        orderStatusStr = '<font color="green" >已支付</font>';
    } else if (oneValue.orderStatus == 3) {
        orderStatusStr = '<font color="green" >已发货</font>';
    } else if (oneValue.orderStatus == 20) {
        orderStatusStr = '<font color="red" >已取消</font>';
    }
    else if (oneValue.orderStatus == 19) {
        orderStatusStr = '<font color="red" >提交但未支付</font>';
    }
	console.log(oneValue.orderStatus)
    trHtml += orderStatusStr;
    trHtml += '</td><td>';
    trHtml += oneValue.orderTotal;
    trHtml += '</td><td>';
    var serviceItem;
    var amountInfoBean22 = oneValue.items[0].amountInfoBean;
    if (oneValue.priceType == 1) {
        serviceItem = '用户数：' + amountInfoBean22.amount.user + '用户,<br>使用期限:' + amountInfoBean22.amount.period + '年<br>'
            + amountInfoBean22.amount.storage + 'T';
    } else if (oneValue.priceType == 2) {

    } else if (oneValue.priceType == 3) {
        serviceItem = '套餐:' + amountInfoBean22.suitName;
    }
    trHtml += serviceItem;
    trHtml += '</td><td>';
    trHtml += oneValue.orgId;
    trHtml += '</td><td>';
    var isNeedInvoice;
    if (oneValue.needInvoice) {
        isNeedInvoice = '需要发票';
    } else {
        isNeedInvoice = '无发票';
    }
    trHtml += isNeedInvoice;

    trHtml += '</td><td>';
    trHtml += oneValue.userId;
    trHtml += '</td></tr>';
    return trHtml;
};
