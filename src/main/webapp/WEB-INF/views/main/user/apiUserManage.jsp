<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
	<div class='box-header' >
		<div class='actions' id="apiUserActions">	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="apiUserTable"></table>
			</div>
		</div>
	</div>
	
									
<div class='modal hide fade' id='modal-ektUserRootEdit' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body'>
     
     	 <div class='control-group'>
            <label class='control-label' style='color:red'>请务必要谨慎操作，以免带来不必要的麻烦！</label>
            <div class='controls'>
            	<input id="getUserId" type="hidden" >
				<ul id="ektUserPermission" class="ztree" style="background:
						 #f0f6e4;width:98%;height:361px;overflow-y:scroll;overflow-x:auto;"></ul>
            </div>  
        </div>
    
    </div>
    <div class='modal-footer'>
        <button type="button" id="ektUserRootBtnClose"  data-dismiss='modal' class='btn'>关闭</button>
        <button type="button" id="ektUserRootBtnSubmit"  class='btn btn-primary'>保存</button>
    </div>
</div>
	
	
	
	

</body>
	
	<script>
		seajs.use([ 'base', 'main/user/apiUserManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
