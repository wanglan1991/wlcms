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
	<div class='modal hide fade' id='modal-Knowledge' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
			<div id="info"></div>
			<div class='modal-body'>
			<div class='control-group'>
					<label class='control-label'>年级</label>
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='grade' name='grade' ></select>
		                </div>
					</div>
				</div>
				
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<div class='controls'>
						<div class='input-append'>
		               		<select id='subject' name='subject'></select>
		                </div>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>排序起点值</label>
					<div class='controls'>
						<div class='input-append'>
		               		<input id='startOrderNo' placeholder='建议预留以便今后扩展' onkeyup="this.value=this.value.replace(/[^0-9-]+/,'')" maxlength="11" name="startOrderNo" type='text' />
		                </div>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>知识点</label>
					<div class='controls'>
						<input  id='title' name="title" placeholder='如多个请使用英文状态下的“，”作为分隔符 '
							type='text' />
					</div>
				</div>
			</div>
			<div class='modal-footer'>
				<msg id='msg'></msg>
				<button type="button" id="btnClose" class='btn'>关闭</button>
				<button id="btnSubmit" class='btn btn-primary'>保存</button>
			</div>
	</div>
	<!-- 编辑字典模态框 -->
	<div class='modal hide fade' id='modal-EditKnowledge' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h4></h4>
		</div>
				<div class='modal-body'>
				<div class='control-group'>

					<label class='control-label'>知识点</label>
					<input type="hidden" id="editKnowledgeId" />
					<div class='controls'>
						<input class='span8' id='editKnowledgeTitle'  placeholder='知识点' 
							type='text' />
							<span id="edit-title-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>序号</label>
					<div class='controls'>
						<input class='span8' id='editKnowledgeOrderNo' 
							placeholder='序号' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>年级</label>
					<input type="hidden" id="editKnowledgeGradeNo" />
					<div class='controls'>
						 <div class='input-append'>
		               		<select id='EditGrade'></select>
		                </div>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>科目</label>
					<input type="hidden" id="editKnowledgeSubjectNo" />
					<div class='controls'>
						<div class='input-append'>
		               		<select id='EditSubject'>
		               		</select>
		                </div>
					</div>
				</div>
			</div>
			<div class='modal-footer'>
				<msg id='edit-msg' style="color:red"></msg>
				<button type="button" id="EditbtnClose" class='btn'>关闭</button>
				<button  id="EditKnowledgeSubmit" class='btn'>保存</button>
			</div>
		
	</div>
	


	<script>
		seajs.use([ 'base', 'dict/knowledge' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
