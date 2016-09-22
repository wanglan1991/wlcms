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
        <h3></h3>是否是名师&nbsp;&nbsp;<input type='checkbox' id='isFamous'/>
    </div>
    <div class='modal-body' style="max-height:618px;">
    <div style="width: 240px;">
    	<div class='control-group'>
            <label class='control-label'></label>
            	
        </div>
        <div class='control-group'>
            <label class='control-label'>姓名</label>
            <div class='controls'>
            	<input type='text' id='addName' placeholder="姓名"/>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>性别</label>
            <div class='controls'>
            	<select id='addSex'><option value='1'>男</option><option value='0'>女</option></select>
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
            <label class='control-label'>平台用户</label>
            <div class='controls'>
            	<input type="text" id="user" userId='0' placeholder="用户名" />
            </div>
        </div>
    </div>
    <div style="width: 260px;height: 289px;margin-left: 251px;margin-top: -302px;">
    <img alt="" id="headPic" src="http://ekt.oss-cn-shenzhen.aliyuncs.com/headPicture/ironman.png" style="width: 216px;height: 237px;">
    <div class='control-group'>
            <div class='controls' >
             <form id="upload"  enctype="multipart/form-data">
					<input type="file" id="imgFile" name="imgFile" accept="image/gif,image/jpeg,image/png,image/jpg"	style='width: 216px;'required><br>
						<button type="submit" id="submitbutton">点击上传</button>
					</form>
            </div>
        </div>
       
    </div>
    <div style="margin-top: 15px;">
         <div class='control-group'>
            <label class='control-label'>个性签名</label>
            <div class='controls'>
            	<textarea id="motto" placeholder="个性签名" style="width:445px;" ></textarea>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>名师信息</label>
            <div class='controls'>
            	<textarea id="info" placeholder="名师信息" style="width:445px;"></textarea>
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
       
         
    </div>
    <div class='modal-footer'>
    	<msg id='msg' style='color:red'></msg>
        <button type="button" id="btnClose" class='btn'>关闭</button>
        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
    </div>
</div>

		
		
		
								
<div class='modal hide fade' id='addModal-Honour' role='dialog' tabindex='-1' style="width:822px;margin-left:-410px;">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div id='honours' style="height:510px;">
    	<div id="honourHead" style="margin-left:595px;margin-top:15px;">
	    	<button type="button" id="addHonour" class='btn'>添加荣誉</button>  
	    	<button type="button" id="delHonour" class='btn' disabled='disabled'>删除荣誉</button> 
    	</div> 
    </div>
   
    <div class='modal-footer'>
    	<msg id='honourMsg' style='color:red'></msg>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" id="HonourBtnClose" class='btn'>关闭</button>
        <button type="button" id="HonourBtnSubmit" class='btn btn-primary'>保存</button>
    </div>
</div>
	
	
	
	
	
	
	
	
	
	

</body>
	
	<script>
		seajs.use([ 'base', 'main/teacher/teacherManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
