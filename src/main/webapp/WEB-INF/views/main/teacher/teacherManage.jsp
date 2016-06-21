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
				<table id="teacherTable"></table>
			</div>
		</div>
	</div>
	
	<div class='modal hide fade' id='addModal-teacher' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>姓名</label>
            <div class='controls'>
            	<input type='text' id='addName' placeholder="姓名"/>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>性别</label>
            <div class='controls'>
            	<select id='addSex'><option value='男'>男</option><option value='女'>女</option></select>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>年级</label>
            <div class='controls'>
            	<select id='addGrade'></select>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>学科</label>
            <div class='controls'>
            	<select id='addSubject'></select>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>头像</label>
            <div class='controls'>
            	<input type="file" id="addHeadPicture" name='img' style='width:216px'/>
            	<button type="submit">点击上传</button>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>学校</label>
            <div class='controls'>
            	<select id="addProvince" style='width: 85px;'><option value='-1'>..省份..</option></select>
            	<select id="addCity" style='width: 92px;'><option value='-1'>..市..</option></select>
            	<select id="addCounty" style='width: 85px;'><option value='-1'>..县/区..</option></select>
            	<select id="addSchool" style='width: 182px;'><option value='-1'>..学校..</option></select>
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
		seajs.use([ 'base', 'main/teacher/teacherManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
