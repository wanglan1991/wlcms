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
	
	<div class='modal hide fade' id='addModal-school' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>省</label>
            <div class='controls'>
            	<select id='province'></select>
                <span id="province-error" class="help-block error"></span>
            </div>
        </div>
        <div class='control-group' id="city-div" style='display:none'>
            <label class='control-label'>市</label>
            <div class='controls'>
            	 <select id='city'></select>
                 <span id="city-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group' id="county-div" style='display:none'>
            <label class='control-label'>区/县</label>
            <div class='controls'>
            	 <select id='county'></select>
                 <span id="county-error" class="help-block error"></span>
            </div>
        </div>
          <div class='control-group' id="school-div" style='display:none'>
            <label class='control-label'>学校名称</label>
            <div class='controls'>
            	 <input type="text" id='schoolName' Maxlength='3000' placeholder="如果是多个学校请使用逗号隔开"/>
                 <span id="schoolName-error" class="help-block error"></span>
            </div>
        </div>
    </div>
    <div class='modal-footer'>
    	<msg id='msg'></msg>
        <button type="button" id="btnClose" class='btn'>关闭</button>

        <button type="button" id="btnSubmit" style='display:none' class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>
	
	
	
	
	
	
	
	
	
	

</body>
	
	<script>
		seajs.use([ 'base', 'dict/school' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
