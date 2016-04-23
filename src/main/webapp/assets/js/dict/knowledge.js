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
		table : new core.Table('KnowledgeTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			 if(base.perList.knowledge.check){
			$("#knowledge-header .actions")
					.append(
							"<input autocomplete='off'  id='q_k_title' name='q_k_title' placeholder='请输入知识点' type='text' />&nbsp;&nbsp;<select  id='q_k_grade' data-placeholder='请选择年级'></select>&nbsp;&nbsp;<select  id='q_k_subject'></select><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");

			 }
			/**
			 * 是否具有添加知识点权限
			 */
			 if(base.perList.knowledge.create){
			$("#knowledge-header .actions")
					.append(
							"<a href='#' id='addKnowledge' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			 }

			/**
			 * 是否具有删除知识点权限
			 */
			 if(base.perList.knowledge.del){
			 $("#knowledge-header .actions")
			 .append(
			 "<a href='#' id='delKnowledges' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
						
			 }
			
			/**
			 * 加载树
			 */

			operateEvents = {
				/**
				 * 修改知识点 取出知识点原有值
				 */
				'click .editKnowledge' : function(e, value, row, index) {
					core.openModel('modal-EditKnowledge', '修改知识点', function() {
						$("#EditId").val(row.id);
						$("#EditTitle").val(row.title);
						$("#EditOrderNo").val(row.orderNo);
						$("#EditGradeNo").val(row.gradeNo);
						$("#EditGrade").val(row.grade);
						$("#EditSubjectNo").val(row.subjectNo);
						$("#EditGrade     option[value='"+row.gradeNo+"']").attr("selected",true);
						$("#EditSubject   option[value='"+row.subjectNo+"']").attr("selected",true);
						$("#tatil").next("h3").html(
								"编辑知识点         " + row.title);

					});
				},
				/**
				 * 删除知识点
				 */
				'click .delKnowledge' : function(e, value, row, index) {
					base.bootConfirm("是否确定删除？", function() {
						$.ajax({
							url : F.basepath + '/knowledge/delete',
							type : 'POST',
							data : {
								ids : row.id.toString()
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
				 * 启用或停用知识点
				 */
				'click .startKnowledge ' : function(e, value, row, index) {
					$.ajax({
						url : F.basepath + '/knowledge/confine',
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
				title : '主键',
				visible : false
			}, {
				field : 'title',
				title : '知识点'
			}, {
				field : 'gradeNo',
				title : '年级ID',
				visible : false
			}, {
				field : 'grade',
				title : '年级'
			} ,{
				field : 'subjectNo',
				title : '科目ID',
				visible : false
			}, {
				field : 'subject',
				title : '科目'
			} ,{
				field : 'orderNo',
				title : '排序'
			}, {
				field : 'status',
				title : '状态',
				visible : false
			} ];
			// 是否需要操作列
			 if(base.perList.knowledge.edit || base.perList.knowledge.del ||
			 base.perList.knowledge.confine)
			cols.push({
				align : 'center',
				title : '操作',
				events : operateEvents,
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/knowledge/listPage', cols);

			/**
			 * 批量删除
			 */
			$('#delKnowledges').click(
					function() {
						var ids = F.table.getIdSelections();
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个知识点？",
									function() {
										$.ajax({
											url : F.basepath + '/knowledge/delete',
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
								"msg" : "请选择你要删除的知识点！"
							});
						}
					});
			
			/**
			 * 加载下拉框
			 * param : 查询条件
			 * selectId ： select元素ID
			 */
			var dictList= function(url,param ,selectId) {
				//查询条件的下拉框给出提示信息
				if(selectId=="#q_k_grade" || selectId=="#q_k_subject" ){
					var html = "<option value='0' >"+"--请选择"+(param == 'grade' ? "年级--" : "学科--")+"</option>";
				}else{
					var html='';
				}
				$.ajax({
			 url: F.basepath+ url,
			 data : {typeEncoding:param},
			 type:'POST',
			 success:function(data){
			 for(var i=0;i<data.value.length;i++){
			 html+="<option	value="+data.value[i].id+">"+data.value[i].value+"</option>";
			 }
			 $(selectId).append(html);
			 }
			 });
			}
			dictList("/dict/queryDictByCondition" , "grade" ,"#EditGrade");
			dictList("/dict/queryDictByCondition" , "subject" ,"#EditSubject");
			dictList("/dict/queryDictByCondition" , "grade" ,"#q_k_grade");
			dictList("/dict/queryDictByCondition" , "subject" ,"#q_k_subject");
			dictList("/dict/queryDictByCondition" , "grade" ,"#grade");
			dictList("/dict/queryDictByCondition" , "subject" ,"#subject");
			/**
			 * 打开模态框
			 */
			$('#addKnowledge').click(function() {
				
				core.openModel('modal-Knowledge', '新增知识点', function() {
					
				});
				return false;
			});

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(
					function() {
						var title = $("#q_k_title").val();
						var gradeNo = $("#q_k_grade").val();
						var subjectNo=$("#q_k_subject").val();
						var query_url = F.basepath + '/knowledge/listPage?title='
								+ title + '&gradeNo=' + gradeNo+'&subjectNo='+subjectNo;
						$('#KnowledgeTable').bootstrapTable('refresh', {
							url : query_url
						});
					}),

			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function() {
				//关闭模态框时清除报错信息
				$("#title-error").html('');
				$("#stitle-error").html('');
				$("#orderNo-error").html('');
				core.closeModel('modal-Knowledge');
				F.table.reload();
			});

			$('#EditbtnClose').click(function() {
				//关闭模态框时清除报错信息
				$("#EditTitle-error").html('');
				$("#EditOrderNo-error").html('');
				core.closeModel('modal-EditKnowledge');
				F.table.reload();
			});

			
		
		},

		operateFormatter : function(value, row, index) {
			var _btnAction = "";
			 if (base.perList.knowledge.confine) {
			_btnAction += "<a class='startKnowledge btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
					+ (row.status == 1 ? "停用" : "启用") + "</a>";
			 }
			            
			 if (base.perList.knowledge.edit) {
			_btnAction += "<a data-toggle='modal' class='editKnowledge btn btn-success btn-small' href='#' title='编辑知识点' style='margin-left:5px'>编辑</a>";
			 }

			 if (base.perList.knowledge.del) {
			 _btnAction += "<a class='delKnowledge btn btn-danger btn-small' href='#' title='删除知识点' style='margin-left:5px'>删除</a>";
			 }
			return _btnAction;
		},
		delKnowledge : function(id) {
			base.ajaxRequest(F.basepath + '/knowledge/delete', {
				"ids" : id.toString()
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
		 * 表单验证 提交修改知识点
		 */
		$('#Editsubmit-form').validate({				
			submitHandler:function(form){
					var id = $("#EditId").val();
					var title = $("#EditTitle").val();
					var orderNo = $("#EditOrderNo").val();
					var gradeNo = $("#EditGrade").val();
					var subjectNo = $("#EditSubject").val();
					$.ajax({
						url : F.basepath + '/knowledge/editKnowledge',
						type : 'POST',
						data : {
							id : id,
							title : title,
							orderNo : orderNo,
							gradeNo : gradeNo,
							subjectNo : subjectNo
						},
						success : function(data) {
							if (data.result > 0) {
								core.closeModel('modal-EditKnowledge');								
								F.table.reload();
								
							} else {
								$("#edit-title-error").html(data.msg);
								$("#edit-title-error").css('color', 'red');

							}
						}

					});
			},
		rules:{
			EditTitle:{required:true},
			EditOrderNo:{required:true},
		},
		messages:{
			EditTitle: '知识点不能为空',
			EditOrderNo:'请给知识点标序',
		},
			
		});
		/**
		 * 表单验证 提交添加知识点
		 */
		$('#submit-form').validate({
		submitHandler:function(form){
			var title = $("#title").val();
			var orderNo = $("#orderNo").val();
			var gradeNo = $("#grade").val();
			var subjectNo = $("#subject").val();
				$.ajax({
				url :  F.basepath + '/knowledge/addKnowledge',
				type : 'POST',
				data : {
					title : title,
					orderNo : orderNo,
					gradeNo : gradeNo,
					subjectNo : subjectNo
				},
				dataType: "json", 
				success : function(data) {
					if (data.result > 0) {
						core.closeModel('modal-Knowledge');
						F.table.reload();
						
					}
					 else{
					 $("#stitle-error").html(data.msg);
					 $("#stitle-error").css('color','red');
					 }
				}

			});
		},
	rules:{
		title:{required:true},
		orderNo:{required:true},
		grade:{required:true},
		subject:{required:true},
	},
	messages:{
		title: '知识点不能为空',
		orderNo:'请给知识点标序',
		grade:'请选择所属年级',
		subject:'请选择所属学科'
	},
		
	});
			
	}); 
	
	
	
});