var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	
	// 通过 require 引入依赖
	var F = module.exports = {
//		basepath : '',
		table : new core.Table('dictTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询字典权限
			 */
			// if(base.perList.dict.query){
			$("#dict-header .actions")
					.append(
							"<input autocomplete='off'  id='q_dict_value' name='q_dict_value' placeholder='请输入字典值' type='text' />&nbsp;&nbsp;<span  id='dict_typeName'></span><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");

			$.ajax({
						url : F.basepath + "/dict/typeNameList",
						type : "GET",
						success : function(data) {
							html = "<select  id='q_dict_type'> <option value=''>--请选择--</option>";
							for (var i = 0; i < data.value.length; i++) {
								if (data.value[i].status == 0) {
									continue;
								}
								html += "<option value="
										+ data.value[i].typeEncoding + ">"
										+ data.value[i].typeName + "</option>"

							}
							html += "</select>"

							$("#dict_typeName").append(html);
						}
					})

			// }
			/**
			 * 是否具有添加字典权限
			 */
			// if(base.perList.dict.create){
			$("#dict-header .actions")
					.append(
							"<a href='#' id='addDict' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			// }

			/**
			 * 是否具有删除字典权限
			 */
			// if(base.perList.dict.del){
			// $("#dict-header .actions")
			// .append(
			// "<a href='#' id='delDicts' class='btn btn-danger btn-small'
			// style='margin-left:5px;margin-bottom:11px'><i
			// class='icon-remove'></i>删除</a>");
			//			
			// }
			// $("#dict-header .actions").append("<a href='#' id='delDicts'
			// class='btn btn-danger btn-small' style='margin-left:5px'><i
			// class='icon-remove'></i>查询</a>");
			//
			// $("#dict-header .actions").append("<a href='#' id='delDicts'
			// class='btn btn-danger btn-small' style='margin-left:5px'><i
			// class='icon-remove'></i>查询</a>");
			// }
			/**
			 * 加载树
			 */

			operateEvents = {
				/**
				 * 修改字典 取出字典原有值
				 */
				'click .editDict' : function(e, value, row, index) {
					core.openModel('modal-EditDict', '修改字典', function() {
						$("#EditValue").val(row.value);
						$("#EditParentID").val(row.parentId);
						$("#EditTypeEncoding").val(row.typeEncoding);
						$("#EditRemark").val(row.remark);
						$("#EditTypeName").val(row.typeName);
						$("#EditValue").attr("valueId", row.id);
						$("#tatil").next("h3").html(
								"编辑字典          " + row.value);

					});
				},
				/**
				 * 删除用户
				 */
				'click .delDict' : function(e, value, row, index) {
					base.bootConfirm("是否确定删除？", function() {
						$.ajax({
							url : F.basepath + '/dict/delete',
							type : 'POST',
							data : {
								id : row.id
							},
							success : function(data) {
								if (data.result > 0) {
									F.table.reload();
								} else {
									alert("异常！")
								}
							}
						});
					});
				},
				/**
				 * 启用或停用用户
				 */
				'click .startDict' : function(e, value, row, index) {
					$.ajax({
						url : F.basepath + '/dict/confine',
						type : 'POST',
						data : {
							id : row.id,
							status : row.status == 1 ? 0 : 1
						},
						success : function(data) {
							console.log(data.result)
							if (data.result > 0) {
								F.table.reload();
							} else {
								alert("操作失败！")
							}
						}
					})

				},

			};
			//			

			// 定义表格的头
			var cols = [ {

				checkbox : true
			}, {
				field : 'id',
				title : '主键'
			}, {
				field : 'value',
				title : '字典值'
			}, {
				field : 'typeEncoding',
				title : '字典类型'
			}, {
				field : 'status',
				title : '状态',
				visible : false
			}, {
				field : 'typeName',
				title : '字典类型名称'
			}, {
				field : 'remark',
				title : '备注'
			} ];
			// 是否需要操作列
			// if(base.perList.user.edit || base.perList.user.del ||
			// base.perList.user.edit_dep || base.perList.user.distribute_role)
			cols.push({
				align : 'center',
				title : '操作',
				events : operateEvents,
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/dict/pageList', cols);

			/**
			 * 批量删除
			 */
			$('#delDicts').click(
					function() {
						var ids = F.table.getIdSelections();
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个用户？",
									function() {
										$.ajax({
											url : F.basepath + '/dict/deletes',
											type : 'POST',
											data : {
												ids : ids.toString()
											},
											success : function(data) {
												if (data.result > 0) {
													F.table.reload();
												} else {
													alert("异常！")
												}
											}
										});
									});
						} else {
							base.bootAlert({
								"ok" : false,
								"msg" : "请选择你要删除的用户！"
							});
						}
					});

			/**
			 * 打开模态框
			 */
			$('#addDict').click(function() {
				var html = '';
				core.openModel('modal-DictTree', '新增字典', function() {
					// 加载role的选择框
					// $.ajax({
					// url: F.basepath+'/dict/dictList',
					// type:'POST',
					// success:function(data){
					// for(var i=0;i<data.value.length;i++){
					// html+="<option
					// value="+data.value[i].id+">"+data.value[i].name+"</option>"
					// }
					// $("#roles").append(html);
					// }
					// });

				});
				return false;
			});

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(
					function() {
						var value = $("#q_dict_value").val();
						var typeName = $("#q_dict_type").val();
						var query_url = F.basepath + '/dict/pageList?value='
								+ value + '&typeEncoding=' + typeName;
						$('#dictTable').bootstrapTable('refresh', {
							url : query_url
						});
					}),

			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function() {
				//关闭模态框时清除报错信息
				$("#value-error").html('');
				$("#svalue-error").html('');
				$("#typeEncoding-error").html('');
				$("#typeName-error").html('');
				core.closeModel('modal-DictTree');
				F.table.reload();
			});

			$('#EditbtnClose').click(function() {
				//关闭模态框时清除报错信息
				$("#EditValue-error").html('');
				$("#edit-value-error").html('');
				$("#EditTypeEncoding-error").html('');
				$("#EditTypeName-error").html('');
				core.closeModel('modal-EditDict');
				F.table.reload();
			});

		},

		operateFormatter : function(value, row, index) {
			var _btnAction = "";
			// if (base.perList.user.edit_dep) {
			_btnAction += "<a class='startDict btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
					+ (row.status == 1 ? "停用" : "启用") + "</a>";
			// }
			//            
			// if (base.perList.user.edit) {
			_btnAction += "<a data-toggle='modal' class='editDict btn btn-success btn-small' href='#' title='编辑用户' style='margin-left:5px'>编辑</a>";
			// }

			// if (base.perList.user.del) {
			// _btnAction += "<a class='delDict btn btn-danger btn-small'
			// href='#' title='删除用户' style='margin-left:5px'>删除</a>";
			// }
			return _btnAction;
		},
		delDict : function(id) {
			console.log(id);
			base.ajaxRequest(F.basepath + '/dict/delete', {
				"id" : id
			}, function(data) {
				base.ajaxSuccess(data);
				F.table.reload();
			}, function() {
				base.bootAlert({
					"ok" : false,
					"msg" : "网络异常"
				});
			});
		},
		onClick : function(event, treeId, treeNode, clickFlag) {
			F.table.query({
				query : {
					'id' : treeNode.id
				}
			});
		},
		
	}
	
	
	jQuery(document).ready(function() { 
		/**
		 * 表单验证 提交修改字典
		 */
		$('#Editsubmit-form').validate({				
			submitHandler:function(form){
				alert("Editsubmit-form");
					var value = $("#EditValue").val();
					var valueId = $("#EditValue").attr("valueId");
					var parentId = $("#EditParentID").val();
					var typeEncoding = $("#EditTypeEncoding").val();
					var typeName = $("#EditTypeName").val();
					var remark = $("#EditRemark").val();
					$.ajax({
						url : F.basepath + '/dict/editDict',
						type : 'POST',
						data : {
							id : valueId,
							value : value,
							parentId : parentId,
							typeEncoding : typeEncoding,
							typeName : typeName,
							remark : remark
						},
						success : function(data) {
							if (data.result > 0) {
								core.closeModel('modal-EditDict');								
								F.table.reload();
								
							} else {
								$("#edit-value-error").html(data.msg);
								$("#edit-value-error").css('color', 'red');

							}
						}

					});
			},
		rules:{
			EditValue:{required:true},
			EditTypeEncoding:{required:true},
			EditTypeName:{required:true},
		},
		messages:{
			EditValue: '字典名称不能为空',
			EditTypeEncoding:'字典类型编码不能为空',
			EditTypeName:'字典类型名称不能为空'
		},
			
		});
		/**
		 * 表单验证 提交添加字典
		 */
		$('#submit-form').validate({				
		submitHandler:function(form){
			var value = $("#value").val();
				var parentId = $("#parentId").val();
				var typeEncoding = $("#typeEncoding").val();
				var typeName = $("#typeName").val();
				var remark = $("#remark").val();
				$.ajax({
				url :  F.basepath + '/dict/addDict',
				type : 'POST',
				data : {
					value : value,
					parentId : parentId,
					typeEncoding : typeEncoding,
					typeName:typeName,
					remark : remark
				},
				dataType: "json", 
				success : function(data) {
					if (data.result > 0) {
						core.closeModel('modal-DictTree');
						F.table.reload();
						
					}
					 else{
					 $("#svalue-error").html(data.msg);
					 $("#svalue-error").css('color','red');
					 }
				}

			});
		},
	rules:{
		value:{required:true},
		typeEncoding:{required:true},
		typeName:{required:true},
	},
	messages:{
		value: '字典名称不能为空',
		typeEncoding:'字典类型编码不能为空',
		typeName:'字典类型名称不能为空'
	},
		
	});

	}); 
	
	
	
});