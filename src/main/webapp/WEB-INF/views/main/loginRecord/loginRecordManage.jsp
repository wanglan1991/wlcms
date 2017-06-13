<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
<style>
label.error{
color:red;
}
</style>
</head>
<body>
	<div class='box-header' id="loginRecord-header">
		<div class='actions' id="loginRecordActions">	
				
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="loginRecordTable"></table>
			</div>
		</div>
	</div>
	
									

</body>
	
	<script>
		seajs.use([ 'base', 'main/loginRecord/loginRecordManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
