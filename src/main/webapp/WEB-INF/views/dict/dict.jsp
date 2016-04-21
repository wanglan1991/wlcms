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
	<div class='box-header' id="dict-header">
		<div class='actions'>	
		
		</div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="dictTable"></table>
			</div>
		</div>
	</div>
	<!-- </div> -->
	<!-- 新增字典模态框 -->
	<div class='modal hide fade' id='modal-DictTree' role='dialog'
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
					<label class='control-label'>字典值</label>
					<div class='controls'>
						<input class='span8' id='value' name="value" placeholder='字典值' 
							type='text' />
							<span id="svalue-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>父级ID</label>
					<div class='controls'>
						<input class='span8' id='parentId' placeholder='父级ID' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型</label>
					<div class='controls'>
						<input class='span8' id='typeEncoding' name="typeEncoding"  placeholder='字典类型'
							type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型名称</label>
					<div class='controls'>
						<input class='span8' id='typeName' name="typeName"  placeholder='字典类型名称'
							type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>备注</label>
					<div class='controls'>
						<input class='span8' id='remark' placeholder='备注' type='text' />
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
	<div class='modal hide fade' id='modal-EditDict' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' id="tatil" data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<form class='form validate-form' id='Editsubmit-form' role="form"
			style='margin-bottom: 0;'>
			<div class='modal-body'>
				<div class='control-group'>
					<label class='control-label'>字典值</label>
					<input type="hidden" id="id" />
					<div class='controls'>

						<input class='span8' id='EditValue' name="EditValue"
							 placeholder='字典值' type='text' />
							<span id="edit-value-error" class="help-block error"></span>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>父级ID</label>
					<div class='controls'>
						<input class='span8' id='EditParentID' name="EditParentID"
							placeholder='父级ID' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型</label>
					<div class='controls'>

						<input class='span8' id='EditTypeEncoding' name="EditTypeEncoding" placeholder='类型'

							 type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>类型名称</label>
					<div class='controls'>

						<input class='span8' id='EditTypeName'  name='EditTypeName' placeholder='字典类型'
							type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>备注</label>
					<div class='controls'>
						<input class='span8' id='EditRemark' name="EditRemark"
							placeholder='备注' type='text' />
					</div>
				</div>
			</div>
			<div class='modal-footer'>
				<msg id='edit-msg'></msg>
				<button type="button" id="EditbtnClose" class='btn'>关闭</button>
				<button id="EditbtnSubmit" class='btn btn-primary'>保存</button>
			</div>
		</form>
	</div>
	<script>
		seajs.use([ 'base', 'dict/dict' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
