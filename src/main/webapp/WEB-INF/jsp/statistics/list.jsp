<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<div class="column">


    <div id="chartTabs">
        <ul>
            <li><a href="#tabs1">按天</a></li>
            <li><a href="#tabs2">按周</a></li>
            <li><a href="#tabs3">按月</a></li>
        </ul>
        <div id="tabs1">
            <div id="accordionByDay">

                <h3 id="accordionTabBar1">线形图</h3>

                <div id="accordionTab1" style="width:90%;">
                    <canvas id="canvasDayLine" height="300" width="600"></canvas>
                </div>

                <h3 id="accordionTabBar2">柱状图</h3>

                <div id="accordionTab2" style="width:90%;">
                    <canvas id="canvasDayBar" height="300" width="600"></canvas>
                </div>
                <%--
                                <h3 id="accordionTabBar3">Tincidunt Massa.</h3>

                               <div id="accordionTab3">
                                    <p>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus
                                        pellentesque purus in massa. Aenean in pede. Phasellus ac liberoac tellus pellentesque semper.
                                        Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo
                                        purus venenatis dui.</p>
                                </div>

                                <h3 id="accordionTabBar4">Quisque lobortis.</h3>

                                <div id="accordionTab4">
                                    <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netuset malesuada fames ac turpis
                                        egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
                                        Aenean lacinia mauris vel est.</p>
                                </div>--%>

            </div>
        </div>
        <div id="tabs2">
            <div id="accordionByWeek">

                <h3 id="accordionWeekTabBar1">线形图</h3>

                <div id="accordionWeekTab1" style="width:90%;">
                    <canvas id="canvasWeekLine" height="300" width="600"></canvas>
                </div>

                <h3 id="accordionWeekTabBar2">柱状图</h3>

                <div id="accordionWeekTab2" style="width:90%;">
                    <canvas id="canvasWeekBar" height="300" width="600"></canvas>
                </div>
            </div>
        </div>
        <div id="tabs3">
            <div id="accordionByMonth">

                <h3 id="accordionMonthTabBar1">线形图</h3>

                <div id="accordionMonthTab1" style="width:90%;">
                    <canvas id="canvasMonthLine" height="300" width="600"></canvas>
                </div>

                <h3 id="accordionMonthTabBar2">柱状图</h3>

                <div id="accordionMonthTab2" style="width:90%;">
                    <canvas id="canvasMonthBar" height="300" width="600"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" language="JavaScript">
    var buildBarChart = function (isLine, canvasDayBar, data) {
        var canvasChartBar = document.getElementById(canvasDayBar);
        var ctxBar = canvasChartBar.getContext("2d");
        if (isLine) {
            window.myLine = new Chart(ctxBar).Line(data, {
                responsive: true
            });
        } else {
            var myBarChart = new Chart(ctxBar).Bar(data, {barShowStroke: true});
        }

    };
    var currentAccordionDayTabBar = null;

    var currentAccordionWeekTabBar = null;
    var currentAccordionWeekTabLine = null;

    var currentAccordionMonthTabBar = null;
    var currentAccordionMonthTabLine = null;

    var needBuildDay = true;

    var needBuildLineWeek = true;
    var needBuildBarWeek = true;

    var needBuildLineMonth = true;
    var needBuildBarMonth = true;

    $(function () {
        ajaxStatistics(1, callbackDay, 'canvasDayLine', 'canvasDayBar');
        ajaxStatistics(2, callbackWeekMonth, 'canvasWeekLine', 'canvasWeekBar');
        ajaxStatistics(3, callbackWeekMonth, 'canvasWeekLine', 'canvasWeekBar');
        jQuery("#accordionByDay").accordion({
            heightStyle: "content",
            activate: function (event) {
//                console.log("activate");
				event=event|| window.event; 
                console.log(event);
                if (needBuildDay && currentAccordionDayTabBar == 'accordionTabBar2') {
                    buildBarChart(false, 'canvasDayBar', statistics.barChartDayData);
                    console.log("build day bar");
                    needBuildDay = false;
                }
            },
            beforeActivate: function (event) {//激活之前就要渲染图表
            	event=event|| window.event||arguments.callee.caller.arguments[0];
//                console.log("beforeActivate");
                var eventTarget = event.srcElement ? event.srcElement : event.target;
                currentAccordionDayTabBar = eventTarget.id;
//                console.log(currentAccordionTabBar);//h3标签的id

            }
        });
        jQuery("#accordionByWeek").accordion({
            heightStyle: "content",
            activate: function (event) {
            	event=event|| window.event||arguments.callee.caller.arguments[0];
//                console.log("activate");
                console.log(event);
                if (needBuildBarWeek && currentAccordionWeekTabBar == 'accordionWeekTabBar2') {
                    buildBarChart(false, 'canvasWeekBar', statistics.barChartDayWeek);
                    console.log("build week bar");
                    needBuildBarWeek = false;
                }
            },
            beforeActivate: function (event) {//激活之前就要渲染图表
            	event=event|| window.event||arguments.callee.caller.arguments[0];
//                console.log("beforeActivate");
                var eventTarget = event.srcElement ? event.srcElement : event.target;
                currentAccordionWeekTabBar = eventTarget.id;
//                console.log(currentAccordionTabBar);//h3标签的id

            }
        });
        jQuery("#accordionByMonth").accordion({
            heightStyle: "content",
            activate: function (event) {
            	event=event|| window.event||arguments.callee.caller.arguments[0];
                if (needBuildBarMonth && currentAccordionMonthTabBar == 'accordionMonthTabBar2') {
                    buildBarChart(false, 'canvasMonthBar', statistics.barChartDayMonth);
                    console.log("build month bar");
                    needBuildBarMonth = false;
                }
            },
            beforeActivate: function (event) {//激活之前就要渲染图表
            	event=event|| window.event||arguments.callee.caller.arguments[0];
//                console.log("beforeActivate");
                var eventTarget = event.srcElement ? event.srcElement : event.target;
                currentAccordionMonthTabBar = eventTarget.id;
//                console.log(currentAccordionTabBar);//h3标签的id

            }
        });


        jQuery("#chartTabs").tabs({
            hide: 300,
            show: 1000,
            activate: function (event) {
            	event=event|| window.event||arguments.callee.caller.arguments[0];
                console.log("activate");
                console.log(event);
                if (needBuildLineWeek && currentAccordionWeekTabLine == 'ui-id-2') {
                    buildBarChart(true, 'canvasWeekLine', statistics.lineChartDayWeek);
                    console.log("build line");
                    needBuildLineWeek = false;
                } else if (needBuildLineMonth && currentAccordionWeekTabLine == 'ui-id-3') {
                    buildBarChart(true, 'canvasMonthLine', statistics.lineChartDayMonth);
                    console.log("build month line");
                    needBuildLineMonth = false;
                }
            },
            beforeActivate: function (event) {
            	event=event|| window.event||arguments.callee.caller.arguments[0];
                console.log("beforeActivate22");
                var eventTarget = event.srcElement ? event.srcElement : event.target;
                currentAccordionWeekTabLine = eventTarget.id;
                console.log(currentAccordionWeekTabLine);


            }/*,
             beforeLoad: function(){
             console.log("beforeLoad")
             },
             load: function(){
             console.log("load")
             }*/
        });

    });
</script>
