<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 2015/8/28
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>服务器监控</title>
</head>
<body>
<div class='span3 box bordered-box blue-border' style='margin-bottom:0;margin-left:0px'>
  <div class='box-header'>
    <div class='title'>服务器名称</div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-y'>
       	<ul id="monitorTree" class="ztree" style="height:365px;"></ul>
      </div>
    </div>
  </div>
</div>

<div class='span9 box bordered-box blue-border' style='margin-bottom:0;'>
  <div class='box-header' id="monitor-header">
    <div class='title'>服务器</div>
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <div id='monitor-table'><table id="monitorTable"></table></div>
        <div id='monitor-chart'></div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript"> 
$(function () {
  seajs.config({
    base: "${ctxAssets}/js/",
    alias: {
    }
  });
  seajs.use(['base','main/monitor/manage'], function (b,m) {
    b.init();
    m.init('${ctx}');
  });
});
</script>
</body>
</html>
