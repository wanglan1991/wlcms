<%--
  Created by IntelliJ IDEA.
  User: zhuyanqiong
  Date: 16-4-16
  Time: 上午8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
<%-- <script src='${ctxAssets}/js/dict/dictFormValidation.js' type='text/javascript'></script> --%>
<style>
label.error{
color:red;
}
</style>
</head>
<body>
	<!-- <div class='span9 box bordered-box blue-border' style='margin-bottom:0;'> -->

	<div class='box-header' id="school-header">
		<div class='actions'>	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="SchoolTable"></table>
			</div>
		</div>
	</div>
	
	<script>
		seajs.use([ 'base', 'dict/school' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
