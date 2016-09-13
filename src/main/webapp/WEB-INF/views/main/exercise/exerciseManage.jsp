<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <div class='box-header' id="exercise-header">
    <div class='actions' id="exercise-actions"></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="exerciseTable" width='150'></table>
      </div>
    </div>
  </div>
<!-- 预览模态框 -->
<div class='modal hide fade' id='modal-preview' role='dialog' tabindex='-1' style= "width:45%;height:80%;overflow: auto;left: 42%;">
    <div class='modal-header'>
        <button class='close' id="addClose"  data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
   <div id="exerciseContent" style="margin-left: 56px;margin-top: 29px;">
    </div>
      	</div>
</div>

<!-- 添加模态框 -->
<div class='modal hide fade' id='modal-addExercise' role='dialog' tabindex='-1' style="width:65%;height:80%;left: 35%;">
    <div class='modal-header'>
        <button class='close' id="addClose"  data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
	            <div style='width:240px;height:450px;margin:30px;'>
	             <div class='controls'>
	               <select id='addCategoryOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='addTypeOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='addDifficultyOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='addGradeOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='addSubjectOption'><option value='0'>学科--</option></select>
	            </div>
				<div class="controls">
					<select id="addKnoeledgeOption"><option value='0'>知识点--</option></select>
				</div>
	              <div class='controls'>
	               <input type='text' id='author' placeholder='请输入作者....' maxlength='10'>
	            </div>
	             <div class='controls'>
	               <input type='text' id='orderNo' onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" placeholder='请输入排序....' maxlength='4'>
	            </div>
	            <textarea id="addAnalysis" rows="100" cols="50" placeholder='习题解析...' style="max-height:49%"></textarea>
            </div>
            <div style="margin-left: 36%;margin-top:-40%;width:62%;;height: 450px;">
             <div class='control-group'>
	            <div class='controls'>
	               
	                <form id="upload"  enctype="multipart/form-data">
					
					<input type="file" id="exeFile" name="exeFile" multiple="multiple" accept="image/gif,image/jpeg,image/png,image/jpg"	 required>
						 <span id="exeFile-error" class="help-block error"></span>
						<div><button type="submit" id ="submitbutton">图片上传</button><input type=button value="复制" onclick="jsCopy('url')">
						 <input id='url'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;图片文件名' type='text' /></div>
			</form>
	            </div>
	        </div>
	        
            	<textarea id='addExerciseContent' maxlengt='900' style='width: 90%;height:15%;margin-left: 0px;' placeholder='习题内容..........'></textarea>
            	<div id="answer">
            		 <p style='color:red;'>请使用,例如：“<b style='color:blue'>&lt;&frasl; img.png&frasl; &gt;</b>”来标注图片在习题内容以及习题答案中的显示位置。</p>
	            	<a class="icon-edit" style="margin-left:78%;color:blue"  id="addAnswer" href="javascript:void(0)">&nbsp;&nbsp;添加答案</a>
	            	<a class="icon-remove" style="margin-left:78%;color:blue"  id="removeAnswer" href="javascript:void(0)">&nbsp;&nbsp;删除答案</a>
            	</br></br></div>
            </div>
       
		<div style='height:40px;margin-top:60px;margin-left: 42%;color:#b94a48' id='msg'></div>	
		<div style="height:120px;margin-top:30px">
	    <span style="margin-left: 41%;width:100px">
	        <button type="button" id="btnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
	       </span>
      	</div>
</div>




<div class='modal hide fade' id='modal-editExercise' exerciseId='' role='dialog' tabindex='-1' style= "width:65%;height:80%;left: 35%;">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
	           <div style='width:240px;height:450px;margin:30px;'>
	            <div class='controls'>
	               <select id='editCategoryOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='editTypeOption'> </select>
	            </div>
	             <div class='controls'>
	               <select id='editDifficultyOption'></select>
	            </div>
	            <div class='controls'>
	               <select id='editGradeOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='editSubjectOption'></select>
	            </div>
	             <div class='controls'>
	               <select id='editKnoeledgeOption'></select>
	            </div>
	              <div class='controls'>
	               <input type='text' id='editAuthor' placeholder='请输入作者....' maxlength='10'>
	            </div>
	             <div class='controls'>
	               <input type='text' id='editOrderNo' onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" placeholder='请输入排序....' maxlength='4'>
	            </div>
	             <textarea id="editAnalysis" rows="100" cols="50" placeholder='习题解析...' style="max-height:49%"></textarea>
            </div>
           
            <div style="margin-left: 36%;margin-top:-40%;width:62%;;height: 450px;">
            
             <div class='control-group'>
	            <div class='controls'>
	               
	                <form id="editUpload" action="/cms/upload/imageUpload" method="post" enctype="multipart/form-data">
					<input type="file" id="editExeFile" name="editExeFile" multiple="multiple"	 required>
						 <span id="editExeFile-error" class="help-block error"></span>
						<div><button type="submit" id ="editSubmitbutton">图片上传</button><input type=button value="复制" onclick="jsCopy('editUrl')">
						 <input id='editUrl'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;图片文件名' type='text' /></div>
			</form>
	            </div>
	        </div>
	        

            	<textarea id='editExerciseContent' maxlengt='900' style='width: 90%;height:15%;margin-left: 0px;' placeholder='习题内容..........'></textarea>
            	<div id="editAnswer">
            		 <p style='color:red;'>请使用,例如：“<b style='color:blue'>&lt; &frasl;img.png &frasl;&gt;</b>”来标注图片在习题内容以及习题答案中的显示位置。</p>
	            	<a class="icon-edit" style="margin-left:78%;color:blue"  id="editAddAnswer" href="javascript:void(0)">&nbsp;&nbsp;添加答案</a>
	            	<a class="icon-remove" style="margin-left:78%;color:blue"  id="editRemoveAnswer" href="javascript:void(0)">&nbsp;&nbsp;删除答案</a>
            	</br></br></div>
            </div>
       
		<div style='height:40px;margin-top:60px;margin-left: 42%;color:#b94a48' id='editMsg'></div>	
		<div style="height:120px;margin-top:30px;">
	    <span style="margin-left: 41%;width:100px">
	        <button type="button" id="editBtnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <button type="button" id="editBtnSubmit" class='btn btn-primary'>保存</button>
	       </span>
      	</div>
</div>



<div class='modal hide fade' id='modal-impExercise' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
 <form action="/cms/upload/exercises" id="excelUpload" method="POST" enctype="multipart/form-data">   
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label' style='color:blue'>导入模板下载</label>
            <div class='controls'>
                <a href='exerciseTemplate.xlsx' style='color:red'><u>exerciseTemplate.xlsx</u></a>
            </div>
        </div>
    </div>
    <div class='modal-body'>
        <div class='control-group' id='editErderNo'>
        
            <label class='control-label'>导入习题</label>
             <div class='controls'>
	               <select id='uploadCategoryOption' ></select>
	            </div>
	             <div class='controls'>
	               <select id='uploadTypeOption' > </select>
	            </div>
	             <div class='controls'>
	               <select id='uploadDifficultyOption' ></select>
	            </div>
	            <div class='controls'>
	               <select id='uploadGradeOption' ></select>
	            </div>
	             <div class='controls'>
	               <select id='uploadSubjectOption' ></select>
	            </div>
				<div class="col-sm-9">
					<select id="uploadKnoeledgeOption" 
				style="width: 218.609px; height:30px" multiple="true"></select>
				<span id="knowledge-error" class="help-block error"></span>
				</div>
			
            	<div class='controls'>
                	<input type='file' id="fileData"  name="fileData" />
                </div>
                
                <button type="submit" id='uploadExercise' >上传</button>
            </div>
        </div>
		</form>  
		    <div class='modal-footer'>
		    	<msg id='msg'></msg>
		        <button type="button" id="impBtnClose" class='btn'>关闭</button>
		    </div>


    </div>

  



<script>
  seajs.use(['base','main/exercise/exerciseManage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
</script>
</body>
</html>
