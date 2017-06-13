<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>

</head>

<body>
<div id="table">
  <div class='box-header' id="perm-header">
    <div class='actions' id="textbook-actions"></div>
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
    <div class='action' id="catalog-action">
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

<div id="outline"  style="display: none">
<div class='box-header' id="catalog-header" >
   		 <button class='close' type='button' id='outlineClose' >x</button>
  </div>
  <button id="export" style="margin-left: 41px;margin-top: 40px;">excel下载</button>
		<div id="catalogOutline"  style="border: 2px solid #9898EC;overflow: scroll;margin-top:13px;height: 530px;width:950px; margin-left:41px;">
			<table id='outlineTable' width="100%"border="1"cellpadding="2"cellspacing="0">
			</table>
		</div>
		<div  id="cover" style="width:370px;margin-left:1031px; margin-top:-530px;">
		
		</div>
</div>

<div class='modal hide fade' id='modal-splitTextbook'  role='dialog' tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>			
		</div>
			<div class='modal-body' style='overflow-y: visible;'>
					
					<div id='Tree'>
					     <label class='control-label' style="color:blue">..请选择好需要分离的实例 ，然后点保存</label>
					    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
					    <div style="border: 1px solid #d0d0d0;">
					       <input type="hidden"/>
					       <input type="hidden" id="distributePermissionTreeHidden"/>
					       <ul id="catalogTree" class="ztree" style="background: #fbfbfb;width:97%;height:462px;overflow-y:scroll;overflow-x:auto;"></ul>
					    </div>
					    </form>
		 			</div> 		        	
			 </div>
			        
			 <div class='modal-footer' style="margin-top: 99px;">
			  	 <msg id="splitTextbookMsg" style="color:red"></msg>
		         <button type="button" id="splitTextbookNtnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;
	   	         <button type="button" id="splitTextbookBtnSubmit" class='btn btn-primary'>保存</button>
   	         </div>
   	        </div>



<div class='modal hide fade' id='modal-addbook' style="width:683px; height:791px;" role='dialog' tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>			
				合集 ——&nbsp;&nbsp;<input style="margin-top: -1px;" type="checkbox" id="isCollection">&nbsp;&nbsp;&nbsp;&nbsp;
				:&nbsp;&nbsp;热门度值 :&nbsp;&nbsp;<input type="text" id="isHot" value='0' onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="6" style='height: 12px;width: 38px;margin-top: 7px;'>
				排序类型 :&nbsp;&nbsp;<input type="text" id="typeOrderNo" value='a' maxlength="6" style='height: 12px;width: 38px;margin-top: 7px;'>
		</div>
			<div class='modal-body' style='width:300px;overflow-y: visible;'>
					<div class='control-group'>
			            <label class='control-label'>选课名称</label>
			            <div class='controls'>
			                <input  id='title'   maxlength='300'  placeholder='标题.....' type='text' />
			            </div>
			        </div>
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
		
			        <div class='control-group'>
			            <label class='control-label'>原价</label>
			            <div class='controls'>
			                <input  id='addDiscountAfterPrice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='￥....' type='text' />
			            </div>
			        </div> 
			        <div class='control-group'>
			            <label class='control-label'>折扣</label>
			            <div class='controls'>
			                <input  id='adddiscount'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='%....' type='text' />
			            </div>
			        </div> 
			         <div class='control-group'>
			            <label class='control-label'>折后价</label>
			            <div class='controls'>
			                <input  id='addprice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='￥....' type='text' />
			            </div>
			        </div> 
			         
					 <div class='control-group' style="display:none">
			            <label class='control-label'>封面</label>
			            <div class='controls'>
			             <input id='imgUrl'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;上传成功后自动回填文件名' type='text' />
			            </div>
			        </div>
			        <div class='control-group'>
			            <label class='control-label'>作者/名师</label>
			            <div class='controls'>
			                <select  id='author' style='width:216px'></select>
			            </div>
			        </div>
			       <div class='control-group'>
			            <label class='control-label'>摘要</label>
			            <div class='controls'>
			                <textarea  id='digest'   maxlength='300' style="height:55px;width:202%;" placeholder='摘要......' type='text' ></textarea>
			            </div>
			        </div>
			        <form id="upload"  enctype="multipart/form-data">
			                <input type="file" id="imgFile" name="imgFile" multiple="multiple" accept="image/gif,image/jpeg,image/png,image/jpg"	style='width: 216px;'required>
			                <button type="submit" id ="submitbutton">封面上传</button>
							</form>
					<div id='addKnowledgeTree' style="width: 103%;margin-left: 102%; margin-top: -660px;" >
					     <label class='control-label'>知识点</label>
					    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
					    <div style="border: 1px solid #d0d0d0;">
					       <input type="hidden"/>
					       <input type="hidden" id="distributePermissionTreeHidden"/>
					       <ul id="knowledgeTree" class="ztree" style="background: #fbfbfb;width:97%;height:462px;overflow-y:scroll;overflow-x:auto;"></ul>
					    </div>
					    </form>
		 			</div> 
			        	
			 </div>
			        
			 <div class='modal-footer' style="margin-top: 252px;">
			  	 <msg id="addMsg" style="color:red"></msg>
		         <button type="button" id="btnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;
	   	         <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
   	         </div>
   	        </div>



<div class='modal hide fade' id='modal-editTextbook' style="width:683px; height:791px;" role='dialog' tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>			
				合集 :&nbsp;&nbsp;<input style="margin-top: -1px;" type="checkbox" id="editIsCollection">&nbsp;&nbsp;&nbsp;&nbsp;
				:&nbsp;&nbsp;热门度值 :&nbsp;&nbsp;<input type="text" id="editIsHot" value='0' onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="6" style='height: 12px;width: 38px;margin-top: 7px;'>
				排序类型 :&nbsp;&nbsp;<input type="text" id="editTypeOrderNo" value='a' maxlength="6" style='height: 12px;width: 38px;margin-top: 7px;'>
				
		</div>
			<div class='modal-body' style='width:300px;overflow-y: visible;'>
					<div class='control-group'>
			            <label class='control-label'>选课名称</label>
			            <div class='controls'>
			                <input  id='editTitle'   maxlength='300'  placeholder='标题.....' type='text' />
			            </div>
			        </div>
			        
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
		
			         <div class='control-group'>
			            <label class='control-label'>原价</label>
			            <div class='controls'>
			                <input  id='editDiscountAfterPrice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='￥....' type='text' />
			            </div>
			        </div> 
			        <div class='control-group'>
			            <label class='control-label'>折扣</label>
			            <div class='controls'>
			                <input  id='editDiscount'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='%....' type='text' />
			            </div>
			        </div>
			        <div class='control-group'>
			            <label class='control-label'>折后价</label>
			            <div class='controls'>
			                <input  id='editPrice'  onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength='10'  placeholder='￥....' type='text' />
			            </div>
			        </div>  
				   
					 <div class='control-group' style="display:none">
			            <label class='control-label'>封面</label>
			            <div class='controls'>
			             <input id='editImgUrl'  maxlength='100'  placeholder='&lt; &frasl;img.png &frasl; &gt;上传成功后自动回填文件名' type='text' />
			            </div>
			        </div>
			        <div class='control-group'>
			            <label class='control-label'>作者/名师</label>
			            <div class='controls'>
			                <select  id='editAuthor' style='width:216px'></select>
			            </div>
			        </div>
			       <div class='control-group'>
			            <label class='control-label'>摘要</label>
			            <div class='controls'>
			                <textarea  id='editDigest'   maxlength='300' style="height:55px;width:202%;" placeholder='摘要......' type='text' ></textarea>
			            </div>
			        </div>
			        <form id="editUpload"  enctype="multipart/form-data">
			                <input type="file" id="editImgFile" name="imgFile" multiple="multiple"	accept="image/gif,image/jpeg,image/png,image/jpg" style='width: 216px;'required>
			                <button type="submit" id ="editSubmitbutton">封面上传</button>
							</form>
							
					<div id='editKnowledgeTree' knowledgePointArrTag style="width: 103%;margin-left: 102%; margin-top: -660px;" >
					     <label class='control-label'>知识点</label>
					    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
					    <div style="border: 1px solid #d0d0d0;">
					       <input type="hidden"/>
					       <input type="hidden" id="distributePermissionTreeHidden"/>
					       <ul id="updateKnowledgeTree" class="ztree" style="background: #fbfbfb;width:97%;height:462px;overflow-y:scroll;overflow-x:auto;"></ul>
					    </div>
					    </form>
		 			</div> 
			        	
			 </div>
			        
			 <div class='modal-footer' style="margin-top: 252px;">
			  	 <msg id="editMsg" style="color:red"></msg>
		         <button type="button" id="editBtnClose" class='btn'>关闭</button>&nbsp;&nbsp;&nbsp;
	   	         <button type="button" id="editBtnSubmit" class='btn btn-primary'>保存</button>
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
                <input id='addCatalogName'  required  maxlength='200' placeholder='目录或者章节名称' type='text' />
                <span id="addCatalogName-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
            </div>
        </div>
        <div class='control-group' id="catalogLevels">
            <label class='control-label'>目录/章节</label>
            <div class='controls'>
               <select id="addCatalogLevel"></select>
                 <span id="addCatalogLevel-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
            </div>
        </div>
	    <div class='control-group' id=addParent style="display:none">
	            <label class='control-label'>父目录</label>
	            <div class='controls'>
	              <select id="addParentId"></select>
	                 <span id="addParentId-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
	            </div>
	    </div>
	    
	     <div class='control-group' id=videoFile style="display:none">
	            <label class='control-label'>video文件名</label>
	            <div class='controls'>
	              <input id="videoFileName"  type="text"  required placeholder="视频文件名" maxlength="100">
	                 <span id="videoFileName-error" class="help-block error" style="font-size: 4px;color:#b94a48;"></span>
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



<script type="text/javascript"  src="/cms/assets/javascripts/outputExcel/tableExport.js">
</script>
<script type="text/javascript"  src="/cms/assets/javascripts/outputExcel/jquery.base64.js"></script>
<script>
  seajs.use(['base','main/textbook/textbookManage'],function(b,m){
	b.init();
    m.init('${ctx}');
    
  });
</script>
</body>
</html>
