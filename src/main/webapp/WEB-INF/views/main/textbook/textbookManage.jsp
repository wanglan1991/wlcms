<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>

</head>

<body>
<div id="table">
  <div class='box-header' id="perm-header">
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="textbookTable"></table>
      </div>
    </div>  
  </div>
</div>


<div id="table2" style="display: none" >
  <div class='box-header' id="catalog-header">
    <div class='action'>
    </div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="catalogTable"></table>
      </div>
    </div>  
  </div>
</div>


<div  id='modal-addTextbook'  style="display: none" >
	<div style="margin-left:56px;">
		<h2>添加教材</h2>
		<div style="width:660px">
			<div style='width:300px'>
	        	<div class='control-group'>
	           	 	<label class='control-label'>年级</label>
	           		 <div class='controls'>
	               <select id='addGrade'></select>
	            </div>
	        </div>
	        <div class='control-group'  >
	            <label class='control-label'>科目</label>
	            <div class='controls'>
	             	<select id='addSubject'></select>
	            </div>
	        </div>
	         <div class='control-group'  >
	            <label class='control-label'>教材类型</label>
	            <div class='controls'>
	             	<select id='addTextbookType'></select>
	            </div>
	        </div>
	         <div class='control-group'  >
	            <label class='control-label'>出版社</label>
	            <div class='controls'>
	             	<select id='addPublisher'></select>
	            </div>
	        </div>
	        
	         <div class='control-group'>
	            <label class='control-label'>标题</label>
	            <div class='controls'>
	                <input  id='title'   maxlength='300'  placeholder='标题' type='text' />
	            </div>
	        </div>
	 </div>
	 <div style="width:300px;margin-left: 260px;margin-top: -325px;">
	         <div class='control-group'>
	            <label class='control-label'>摘要</label>
	            <div class='controls'>
	                <input  id='digest'   maxlength='300'  placeholder='摘要' type='text' />
	            </div>
	        </div>
	         <div class='control-group'>
	            <label class='control-label'>封面</label>
	            <div class='controls'>
	                <input id='imgUrl'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;' type='text' />
	            </div>
	        </div>
	        <div class='control-group'>
	            <label class='control-label'>作者</label>
	            <div class='controls'>
	                <input  id='author'  maxlength='15'  placeholder='作者' type='text' />
	            </div>
	        </div>
	         <div class='control-group'>
	            <label class='control-label'>录入人</label>
	            <div class='controls'>
	                <input  id='pushPerson'  maxlength='15'  placeholder='录入人' type='text' />
	            </div>
</div>
	        </div>
	        
	        <div style="margin-left: 140px;margin-top: 110px;">
	        <div id='addMsg' style='color:#b94a48;width:200px;height: 20px;margin-left: 28px;'></div>
	        <br>
	         <button type="button" id="btnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;
   	         <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
   	        </div>
	  </div> 


 <div id='addKnowledgeTree' style="width: 32%;margin-left: 61%; margin-top: -496px;display:none" >
        <h4>知识点Tree</h4>
    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
    <div>
       <input type="hidden"/>
       <input type="hidden" id="distributePermissionTreeHidden"/>
       <ul id="knowledgeTree" class="ztree" style="background: #f0f6e4;width:100%;height:361px;overflow-y:scroll;overflow-x:auto;"></ul>
    </div>
    </form>
 </div>      
 </div>     
</div>
<!-- 修改用户 -->
<div  id='modal-editTextbook' idtag style="display: none" >
	<div style="margin-left:56px;">
		<h2>修改教材</h2>
		<div style="width:660px">
			<div style='width:300px'>
	        	<div class='control-group'>
	           	 	<label class='control-label' id="editGradeTag" grade >年级</label>
	           		 <div class='controls'>
	               <select id='editGrade'></select>
	            </div>
	        </div>
	        <div class='control-group'  >
	            <label class='control-label' id="editSubjectTag" subject>科目</label>
	            <div class='controls'>
	             	<select id='editSubject'></select>
	            </div>
	        </div>
	         <div class='control-group'  >
	            <label class='control-label'>教材类型</label>
	            <div class='controls'>
	             	<select id='editTextbookType'></select>
	            </div>
	        </div>
	         <div class='control-group'  >
	            <label class='control-label'>出版社</label>
	            <div class='controls'>
	             	<select id='editPublisher'></select>
	            </div>
	        </div>
	        
	         <div class='control-group'>
	            <label class='control-label'>标题</label>
	            <div class='controls'>
	                <input  id='editTitle'   maxlength='300'  placeholder='标题' type='text' />
	            </div>
	        </div>
	 </div>
	 <div style="width:300px;margin-left: 260px;margin-top: -325px;">
	         <div class='control-group'>
	            <label class='control-label'>摘要</label>
	            <div class='controls'>
	                <input  id='editDigest'   maxlength='300'  placeholder='摘要' type='text' />
	            </div>
	        </div>
	         <div class='control-group'>
	            <label class='control-label'>封面</label>
	            <div class='controls'>
	                <input id='editImgUrl'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;' type='text' />
	            </div>
	        </div>
	        <div class='control-group'>
	            <label class='control-label'>作者</label>
	            <div class='controls'>
	                <input  id='editAuthor'  maxlength='15'  placeholder='作者' type='text' />
	            </div>
	        </div>
	         <div class='control-group'>
	            <label class='control-label'>录入人</label>
	            <div class='controls'>
	                <input  id='editPushPerson'  maxlength='15'  placeholder='录入人' type='text' />
	            </div>
</div>
	        </div>
	        
	        <div style="margin-left: 140px;margin-top: 110px;">
	        <div id='editMsg' style='color:#b94a48;width:200px;height: 20px;margin-left: 28px;'></div>
	        <br>
	         <button type="button" id="editBtnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;
   	         <button type="button" id="editBtnSubmit" class='btn btn-primary'>保存</button>
   	        </div>
	  </div> 


 <div id='editKnowledgeTree' knowledgePointArrTag style="width: 32%;margin-left: 61%; margin-top: -496px;display:none" >
        <h4>知识点Tree</h4>
    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
    <div>
       <input type="hidden"/>
       <input type="hidden" id="distributePermissionTreeHidden"/>
       <ul id="updateKnowledgeTree" class="ztree" style="background: #f0f6e4;width:100%;height:361px;overflow-y:scroll;overflow-x:auto;"></ul>
    </div>
    </form>
 </div>
    
 </div>     

</div>

<div class='modal hide fade' id='modal-addCatalog'  tabindex='-1'>
    <div class='modal-header'>
        <h3></h3>
    </div>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>目录/章节名称</label>
            <div class='controls'>
                <input id='addCatalogName'  required  maxlength='15' placeholder='目录或者章节名称' type='text' />
                <span id="addCatalogName-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>目录级别</label>
            <div class='controls'>
               <select id="addCatalogLevel"></select>
                 <span id="addCatalogLevel-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
            </div>
        </div>
	    <div class='control-group' id=addParent style="display:none">
	            <label class='control-label'>父级</label>
	            <div class='controls'>
	             <input required id="addParentId"  placeholder='parentId' maxlength="5" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" type="text" >
	                 <span id="addParentId-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
	            </div>
	    </div>
	    <div class='control-group'>
	            <label class='control-label'>简介</label>
	            <div class='controls'>
	               <textarea  class='span8' id="addIntroduction" placeholder='简介'></textarea>
	                 <span id="addIntroduction-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
	            </div>
	    </div>
	    <div class='control-group'>
	            <label class='control-label'>排序</label>
	            <div class='controls'>
	               <input required id="addOrder"  placeholder='排序' maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" type="text" >
	                 <span id="addOrder-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
	            </div>
	    </div>
    </div>
    <div class='modal-footer'>
    	<msg id='msg'></msg>
        <button type="button" id="catalogBtnClose" class='btn'>关闭</button>
        <button type="button" id="catalogBtnSubmit" class='btn btn-primary'>保存</button>
        <button type="button"  style="display:none" id="editCatalogSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>




<script>
  seajs.use(['base','main/textbook/textbookManage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
  
</script>
</body>
</html>
