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
	<div class='box-header' id="quintessence-header">
		<div class='actions' id="quintessenceActions">	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="quintessenceTable"></table>
			</div>
		</div>
	</div>
	
									
<div class='modal hide fade' id='addModal-Quintessence' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body'>
     
     	 <div class='control-group'>
            <label class='control-label'>类型</label>
            <div class='controls'>
				<select id="addQuintessenceType">
					<option value='-1'>请选择..</option>
					<option value='1'>组卷</option>
					<option value='2'>选课</option>
					<option value='3'>文集</option>
				</select>
            </div>  
        </div>
      <div id="addContentSelect" style="display:none">   
        <div class='control-group'>
            <label class="col-sm-3 control-label">内容</label>
			<div class="col-sm-9" style="width:225px">
				<select id="quintessenceContent" style="width:220px; height:30px" multiple="true"></select>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>到期日期</label>
            <div class='controls'> 
				<input type="date" id="expireDate" >
            </div>  
	     </div>
     </div> 
    </div>
   
   
    <div class='modal-footer'>
    	<msg id='quintessenceMsg' style='color:red'></msg>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" id="quintessenceBtnClose" class='btn'>关闭</button>
        <button type="button" id="quintessenceBtnSubmit" class='btn btn-primary'>保存</button>
    </div>
</div>
	
	
	
	

</body>
	
	<script>
		seajs.use([ 'base', 'main/quintessence/quintessenceManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
