<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
<style>
label.error{
color:red;
}

#modal-editTestpaper input,textarea{
width: 400px;
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
	
									
<div class='modal hide fade' id='modal-editTestpaper' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body'>
     
     	 <div class='control-group'>
            <label class='control-label'>组卷标题</label>
            <div class='controls'> 
				<input type="text" id="testpaperTitle" placeholder="请输入组卷名称标题名称！">
            </div>  
        </div>
        <div class='control-group'>
            <label class="col-sm-3 control-label">组卷简介</label>
			 <div class='controls'> 
				<textarea rows="5" cols="5" id="testpaperDigest" ></textarea>
            </div>  
        </div>
        <div class='control-group'>
            <label class='control-label'>难易度</label>
            <div class='controls'> 
				<select id="testpaperDifficulty"></select>
            </div>  
	     </div>
     
    </div>
   
   
    <div class='modal-footer'>
    	<msg id='quintessenceMsg' style='color:red'></msg>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" data-dismiss="modal" class='btn'>关闭</button>
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
