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
	
	
<div class='modal hide fade' id='modal-editUserInfo' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form action="#">
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>真实姓名</label>
            <div class='controls'>
             <input class='span8' id='telephone' required  maxlength='12' placeholder='昵称' type='text' />
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>性别</label>
            <div class='controls'>
             <select id='sex' class='span8'>
             	<option value='男'>男</option>
             	<option value='女'>女</option>
             </select>
            </div>
        </div>
          <div class='control-group'>
            <label class='control-label'>昵称</label>
            <div class='controls'>
             <input class='span8' id='nickname' Disabled='Disabled'  maxlength='12' placeholder='昵称' type='text' />
            </div>
        </div>
    </div>
    </form>
    <div class='modal-footer'>
    	<msg id="editUserInfo-msg" style='color:red'></msg>
        <button type="button" data-dismiss="modal"  class='btnClose btn' >关闭</button>
		<button id="editUserInfo-btnSubmit"  class='btn btn-primary'>提交</button>
    </div>
</div>	

<div class='modal hide fade' id='modal_api_user_business' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
               		<button id="submitRecord">历史记录</button>
               		<button id="currentBusiness">当前事务</button>
       
       <div class='modal-body' id="businessRecord" style='max-height: 635px; display:none'>
       	<table width="100%" border="2" cellspacing="5" cellpadding="5">
       		<thead>
       		<tr>
       			<th>序列</th>
       			<th>提交时间</th>
       			<th>事务类型</th>
       			<th>处理状态</th>
       			<th>处理时间</th>
       			<th>处理人</th>
       		</tr>
       		</thead>
       		<tbody id="businessRecordBody">
       		</tbody>
       	</table>
       
       </div>
       
       
       
       
    <div class='modal-body' id="currentBody" style='max-height: 635px'>
    
    	<div class='control-group' >
            <label class='control-label'>事务类型</label>
            <div class='controls'>
               		<input  disabled='disabled' id="businessType" required  type='text' />
            </div>
        </div>
    	<div class='control-group' >
            <label class='control-label'>真实姓名</label>
            <div class='controls'>
               		<input  disabled='disabled' id="businessUserRealname" required  type='text' />
            </div>
        </div>
        <div class='control-group' >
            <label class='control-label'>用户名</label>
            <div class='controls'>
               		<input disabled='disabled' id='businessUsername' required  type='text' />
            </div>
        </div>
        <div class='control-group' >
            <label class='control-label'>手机号码</label>
            <div class='controls'>
               		<input disabled='disabled' id='businessUserTelephone' required  type='text' />
            </div>
        </div>
        <div class='control-group' >
            <label class='control-label'>发起时间</label>
            <div class='controls'>
               		<input disabled='disabled' id='businessUserCommitTime' required  type='text' />
            </div>
        </div> 
         <div class='control-group' >
            <label class='control-label'>备注 </label>
            <div class='controls'>
               		<textarea  disabled='disabled' id='businessUserRemark'  rows="5" style="width:97%" required  />
            </div>
        </div>   
          <div class='control-group' >
            <label class='control-label'>办理内容</label>
            <div class='controls'>
               		<textarea  style="width:97%"  rows="5" required  maxlength="690" id='businessUserAcceptanceContent' />
            </div>
        </div>
        
       
         
    </div>
    <div class='modal-footer'>
        <msg id="businessMsg" style="color:red"></msg>
        <button type="button" id="btnClose" data-dismiss='modal' class='btn'>关闭</button>
        <button type="button" id="businessBtnSubmit" class='btn btn-primary'>提交</button>
    </div>
</div>
	
	
									
<div class='modal hide fade' id='modal-ektUserRootEdit' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body' style="max-height: 425px;">
     
     	 <div class='control-group'>
            <label class='control-label' style='color:red'>1.请务必要谨慎操作，以免带来不必要的麻烦！<br>2.默认过期时间为2个月，不配置时间为不过期。</label>
            <div class='controls'>
            	<input id="couponId" type="hidden">
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


<div class='modal hide fade' id='modal-giftCourse' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body' style="max-height: 425px;">
     
     	 <div class='control-group'>
            <label class='control-label' style='color:red'>1.请务必要谨慎操作，以免带来不必要的麻烦！<br>2.默认过期时间为2个月，不配置时间为不过期。</label>
            <div class='controls'>
            	<input id="giftCourse_couponId" type="hidden">
            	<input id="giftCourse_getUserId" type="hidden" >
				<ul id="giftCourseTree" class="ztree" style="background:
						 #f0f6e4;width:98%;height:361px;overflow-y:scroll;overflow-x:auto;"></ul>
            </div>  
        </div>
    
    </div>
    <div class='modal-footer'>
        <button type="button" id="giftCourseBtnClose"  data-dismiss='modal' class='btn'>关闭</button>
        <button type="button" id="giftCourseBtnSubmit"  class='btn btn-primary'>保存</button>
    </div>
</div>
	
	
	<div class='modal hide fade' id='modal-generateCmsAccount' role='dialog' tabindex='-1'">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
     <div class='modal-body'>
     
     	 <div class='control-group'>
            <label class='control-label' style='color:red'>请务必要谨慎操作，以免带来不必要的麻烦！</label>
            <div class='controls'>
            	<select id="ektUserRole"></select>
            </div>  
        </div>
    
    </div>
    <div class='modal-footer'>
        <button type="button" id="generateCmsAccountBtnClose" data-dismiss='modal' class='btn'>关闭</button>
        <button type="button" id="generateCmsAccountSave"  class='btn btn-primary'>生成</button>
    </div>
</div>
	
	
<div class='modal hide fade' id='modal-generateEktUser' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3>生成用户</h3>
    </div>
    <form action="#">
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>手机号码</label>
            <div class='controls'>
             <input class='span8' id='telephone' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required  maxlength='11' placeholder='手机号码' type='text' />
            </div>
        </div>
    </div>
    </form>
    <div class='modal-footer'>
    	<msg id="generateEktUser-msg" style='color:red'></msg>
        <button type="button" data-dismiss="modal"  class='btnClose btn' >关闭</button>
		<button id="generateEktUser-btnSubmit"  class='btn btn-primary'>提交</button>
    </div>
</div>

<div class='modal hide fade' id='modal-impApiUser' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3>批量生成用户</h3>
    </div>
 <form  id="excelUpload"  enctype="multipart/form-data">   
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label' style='color:blue'>模板下载</label>
            <div class='controls'>
                <a href='template.xlsx' style='color:red'><u>template.xlsx</u></a>
            </div>
        </div>
    </div>
    <div class='modal-body'>
        <div class='control-group' id='editErderNo'>
            <label class='control-label'>导入excel</label>
		
            	<div class='controls'>
                	<input type='file' id="fileData"  name="fileData" accept=".xlsx" required/>
                </div>
                
                <button type="submit" id='uploadFile' >上传</button>
            </div>
            </br>
            <div  id="impResult">
            	 <label class='control-label'>上传报告</label>
            	  <textarea style='width:98%;min-height:300px;'></textarea>
            </div>
           
        </div>
		</form>  
		    <div class='modal-footer'>
		    	<msg id='uploadMsg' style='color:red'></msg>
		        <button type="button" data-dismiss='modal' class='btn'>关闭</button>
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
