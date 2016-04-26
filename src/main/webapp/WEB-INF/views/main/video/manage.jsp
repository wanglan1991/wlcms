<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title></title>
</head>
<body>
	<div class='box-header' id="video-header">
		<div class='actions'></div>
	</div>
	<div class='box-content box-no-padding'>
		<div class='responsive-table'>
			<div class='scrollable-area-x'>
				<table id="videoTable"></table>
			</div>
		</div>
	</div>

	<div class='modal hide fade' id='modal-Video' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div class='modal-body'>
			<div class='control-group'>
				<label class='control-label'>角色编码</label>
				<div class='controls'>
					<input class='span8' id='encoding' required name="encoding"
						maxlength='15' placeholder='角色编码' type='text' /> <span
						id="encoding-error" class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>角色名称</label>
				<div class='controls'>
					<input class='span8' id='name' required name='name' maxlength='15'
						placeholder='角色名称' type='text' /> <span id="neme-error"
						class="help-block error"></span>
				</div>
			</div>
		</div>
		<div class='modal-footer'>
			<msg id='msg'></msg>
			<button type="button" id="btnClose" class='btn'>关闭</button>

			<button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
		</div>
		</form>
	</div>

	<!-- 编辑视频基础信息模态框 -->
	<div class='modal hide fade' id='modal-editVideo' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div class='modal-body'>
			<div class='control-group'>
				<label class='control-label'>视频名称</label>
				<div class='controls'>
					<input type='hidden' id='editId' name="id" /> <input class='span8'
						id='editVideo' required name="encoding" maxlength='15'
						placeholder='请输入视频名称' type='text' /> <span id="editVideo-error"
						class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>简介</label>
				<div class='controls'>
					<input class='span8' id='editDigest' required name='name'
						maxlength='15' placeholder='用几句话介绍一下视频吧' type='text' /> <span
						id="editDigest-error" class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>运营商</label>
				<div class='controls'>
					<input class='span8' id='editIsp' required name='name'
						maxlength='15' placeholder='请输入运营商' type='text' /> <span
						id="editName-error" class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>视频文件名</label>
				<div class='controls'>
					<input class='span8' id='editFileName' required name='name'
						maxlength='15' placeholder='视频文件名' type='text' /> <span
						id="editFileName-error" class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>讲师</label>
				<div class='controls'>
					<input class='span8' id='editAuthor' required name='name'
						maxlength='15' placeholder='讲师' type='text' /> <span
						id="editAuthor-error" class="help-block error"></span>
				</div>
			</div>
			<div class='control-group'>
				<label class='control-label'>知识点</label> 
				<div class='controls'>
					<div class="col-sm-3">
						<select id='editKnowledge' style='width:200px'></select>
					</div>
				</div>
			</div>
		</div>
		<div class='modal-footer'>
			<msg id='editMsg'></msg>
			<button type="button" id="editBtnClose" class='btn'>关闭</button>
			<button  id="editBtnSubmit" class='btn btn-primary'>保存</button>
		</div>
	</div>



	<div class='modal hide fade' id='modal-DistributePermission'
		role='dialog' tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<form class='form validate-form'
			id='submit-distributePermissionTreeForm' method="post" role="form"
			style='margin-bottom: 0;'>
			<div class='modal-body'>
				<input type="hidden" id="roleId" /> <input type="hidden"
					id="distributePermissionTreeHidden" />
				<ul id="distributePermissionTree" class="ztree"
					style="background: #f0f6e4; width: 100%; height: 300px; overflow-y: scroll; overflow-x: auto;"></ul>
			</div>
			<div class='modal-footer'>
				<button type="button" id="btnDistributePermissionClose" class='btn'>关闭</button>
				<button type="button" id="btnDistributePermissionSubmit"
					class='btn btn-primary'>保存</button>
			</div>
		</form>
	</div>

	<div class='modal hide fade' id='modal-CheckPermission' role='dialog'
		tabindex='-1'>
		<div class='modal-header'>
			<button class='close' data-dismiss='modal' type='button'>&times;</button>
			<h3></h3>
		</div>
		<div class='modal-body'>
			<ul id="checkPermissionTree" class="ztree"
				style="background: #f0f6e4; width: 100%; height: 300px; overflow-y: scroll; overflow-x: auto;"></ul>
		</div>
		<div class='modal-footer'>
			<button type="button" id="btnCheckPermissionClose" class='btn'>关闭</button>
		</div>
	</div>
	<script type="text/javascript">
 $(document).ready(function() {
  $(".js-example-basic-single").select2();
});
</script>

<select class="js-example-basic-single">
  <option value="AL">Alabama</option>
  <option value="WY">Wyoming</option>
</select>
	
	<script>

	   
		seajs.use([ 'base', 'main/video/manage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</body>
</html>
