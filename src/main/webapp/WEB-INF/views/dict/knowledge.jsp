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
</head>
<body>
	<!-- <div class='span9 box bordered-box blue-border' style='margin-bottom:0;'> -->
	<div class='box-header' id="knowledge-header">
		<div class='actions'>	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="KnowledgeTable"></table>
			</div>
		</div>
	</div>
	<!-- </div> -->
	<!-- 新增字典模态框 -->
	<div class='modal hide fade' id='modal-KnowledgeTree' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<form class='form validate-form' id='submit-form' role="form"
			style='margin-bottom: 0;'>
			<div id="info"></div>
			<div class='modal-body'>
				<div class='control-group'>
					<label class='control-label'>知识点</label>
					<div class='controls'>
						<input class='span8' id='title' name="title" placeholder='知识点' 
							type='text' />
							<span id="stitle-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>序号</label>
					<div class='controls'>
						<input class='span8' id='orderNo' placeholder='序号' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>年级</label>
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='grade'></select>
		                </div>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<div class='controls'>
						<div class='input-append'>
		               		<select id='subject'></select>
		                </div>
				</div>
			</div>
			<div class='modal-footer'>
				<msg id='msg'></msg>
				<button type="button" id="btnClose" class='btn'>关闭</button>
				<button id="btnSubmit" class='btn btn-primary'>保存</button>
			</div>
		</form>
	</div>
	<!-- 编辑字典模态框 -->
	<div class='modal hide fade' id='modal-EditKnowledge' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<form class='form validate-form' id='Editsubmit-form' role="form"
			style='margin-bottom: 0;'>
			<div class='modal-body'>
				<div class='control-group'>
					<label class='control-label'>知识点</label>
					<input id="id" type="hidden"/>
					<div class='controls'>						
						<input class='span8' id='EditTitle' name="EditTitle" 
							required placeholder='知识点' type='text' />
							<span id="edit-title-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>序号</label>
					<div class='controls'>
						<input class='span8' id='EditOrderNo' name="EditOrderNo"
							placeholder='序号' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型</label>
					<div class='controls'>
						<input class='span8' id='EditType' name="EditType" placeholder='类型'
							required type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型名称</label>
					<div class='controls'>
						<input class='span8' id='EditTypeName' required placeholder='字典类型'
							type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>备注</label>
					<div class='controls'>
						<input class='span8' id='EditRemark' name="remark"
							placeholder='备注' type='text' />
					</div>
				</div>
			</div>
			<div class='modal-footer'>
				<msg id='edit-msg'></msg>
				<button type="button" id="EditbtnClose" class='btn'>关闭</button>
				<button type="button" id="EditbtnSubmit" class='btn btn-primary'>保存</button>
			</div>
		</form>
	</div>
	<script type="text/javascript"> 
	
	
	
	jQuery(document).ready(function() { 
		$('#submit-form').validate({
//			$('#modal-DictTree').validate({
		
			submitHandler:function(form){
				alert(111111);
				var value = $("#value").val();
				var parentId = $("#parentId").val();
				var typeEncoding = $("#typeEncoding").val();
				var typeName = $("#typeName").val();
				var remark = $("#remark").val();
				var options = {
						url : F.basepath + '/knowledge/addKnowledge',
						type:'post',
						dataType:'json',
						data : {
							value : value,
							parentId : parentId,
							typeEncoding : typeEncoding,
							typeName : typeName,
							remark : remark
						},
						success:function(data){
								if (data.result > 0) {
									core.closeModel('modal-KnowledgeTree');
									F.table.reload();
								} else{
								//错误提示
								$('#info').fadeOut('slow');
								$('#info').empty().text(data.msg);
								$('#info').removeClass('alert alert-success').addClass('alert alert-danger');
								$('#info').fadeIn('slow');
							}
						}				
				};
				$(form).ajaxSubmit(options);
			},
		rules:{
			value:{required:true},
			typeEncoding:{required:true},
		},
		messages:{
			value:{required:'字典名称不能为空'},
			typeEncoding:'字典编码不能为空',
		},
			
		});
	}); 
 	</script>


	<script>
		seajs.use([ 'base', 'dict/knowledge' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
