<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <div class='box-header' id="exercise-header">
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="exerciseTable"></table>
      </div>
    </div>
  </div>

<div class='modal hide fade' id='modal-addExercise' role='dialog' tabindex='-1' style= "width:60%;height:80%;left: 35%;">
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
	            <div style='width:240px;height:450px;margin:30px;'>
	            <div class='controls'>
	               <select id='addGradeOption'></select>
	                <span id="addGrade-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addCategoryOption'></select>
	                <span id="addCategory-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addTypeOption'> </select>
	                <span id="addType-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addDifficultyOption'></select>
	                <span id="addDifficulty-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addSubjectOption'></select>
	                <span id="addSubject-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addKnoeledgeOption'></select>
	                <span id="addKnoeledge-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <select id='addpublisherOption'></select>
	                <span id="addpublisher-error" class="help-block error"></span>
	            </div>
	              <div class='controls'>
	               <input type='text' id='author' placeholder='请输入作者....' maxlength='10'>
	                <span id="author-error" class="help-block error"></span>
	            </div>
	             <div class='controls'>
	               <input type='text' id='orderNo' onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" placeholder='请输入排序....' maxlength='4'>
	                <span id="author-error" class="help-block error"></span>
	            </div>
            </div>
           
            <div style="margin-left: 30%;margin-top:-42%;width:63%;;height: 450px;">
            	<textarea id='exerciseContent' maxlengt='900' style='width: 79%;height:25%;margin-left: 0px;' placeholder='习题内容..........'></textarea>
            	<div id="answer">
            		 <p style='color:red;'>请使用,例如：“<b style='color:blue'>&lt; &frasl;img.png &frasl; &gt;</b>”来标注图片在习题内容以及习题答案中的显示位置。</p>
	            	<a class="icon-edit" style="margin-left:70%;color:blue"  id="addAnswer" href="javascript:void(0)">&nbsp;&nbsp;添加答案</a>
	            	<a class="icon-remove" style="margin-left:70%;color:blue"  id="removeAnswer" href="javascript:void(0)">&nbsp;&nbsp;删除答案</a>
            	</br></br></div>
            </div>
       
		<div style='height:40px;margin-top:130px;margin-left: 42%;' id='msg'></div>	
		<div style="height:120px;">
	    <span style="margin-left: 41%;width:100px">
	        <button type="button" id="btnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
	       </span>
      	</div>
</div>



<div class='modal hide fade' id='modal-editPerm' Perm='' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
         <div class='control-group'>
            <label class='control-label'>名称</label>
            <div class='controls'>
                <input class='span8' id='editPermName' required name='editPermName' maxlength='15'  placeholder='名称' type='text' />
                 <span id="editPermName-error" class="help-block error"></span>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>key值</label>
            <div class='controls'>
                <input class='span8' id='editKey' required name='editKey' maxlength='30'  placeholder='key值' type='text' />
                 <span id="editKey-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>URL</label>
            <div class='controls'>
                <input class='span8' id='editValue' required name='value' maxlength='30'  placeholder='url' type='text' />
                 <span id="editUrl-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group' id='editErderNo'>
            <label class='control-label'>排序</label>
            <div class='controls'>
                <input class='span8' id='editOrder' required name='order' maxlength='4'  placeholder='order' type='text' />
                 <span id="editOrder-error" class="help-block error"></span>
            </div>
        </div>
        <div class="control-group" id="editIcons">
            <label class="control-label">菜单图片</label>
            <div class="controls">
                <input class="span8" id="editIcon" name="editIcon" placeholder="菜单图片" type="text" style="display:none;">
                  <span id="editIcon-error" class="help-block error"></span>
            </div>
        </div> 
    </div>
    <div class='modal-footer'>
    	<msg id='msg'></msg>
        <button type="button" id="editBtnClose" class='btn'>关闭</button>

        <button type="button" id="editBtnSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>





<script>
  seajs.use(['base','main/exercise/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
</script>
</body>
</html>
