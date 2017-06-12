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
							"<input autocomplete='off'  id='q_k_title' name='q_k_title' placeholder='请输入知识点' type='text' />" +
							"&nbsp;&nbsp;<select  id='q_k_grade' data-placeholder='请选择年级'></select>" +
							"&nbsp;&nbsp;<select  id='q_k_subject'></select>" +
							"<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
					core.getDictOptions("年级","grade","#q_k_grade");
					core.getDictOptions("学科","subject","#q_k_subject");		
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
			 	core.getDictOptions("年级","grade","#EditGrade");
				core.getDictOptions("学科","subject","#EditSubject");	
			 /**
			  * 提交
			  */
			$("#btnSubmit").click(function(){
				var gradeNo =$("#grade").val();
				var subjectNo = $("#subject").val();
				var startOrderNo=$("#startOrderNo").val();
				var title =$("#title").val();
				
				if(gradeNo<1){$("#msg").text("请选择年级！");$("#msg").css('color',"red");return;}
				if(subjectNo<1){$("#msg").text("请选择学科！");$("#msg").css('color',"red");return;}
				if(startOrderNo<1){$("#msg").text("请给一个排序起点值！");$("#msg").css('color',"red");return;}
				if(title.length<1){$("#msg").text("请输入知识点！");$("#msg").css('color',"red");return;}
				$.ajax({
					url : F.basepath + '/knowledge/addKnowledge',
					type : 'POST',
					data : {
						gradeNo:gradeNo,
						subjectNo:subjectNo,
						title:title,
						orderNo:startOrderNo
					},
					success : function(data) {
						if(data.result>0){
							F.table.reload();
							if(confirm(data.msg+",是否继续添加  ？")){								
								$("#title").val('');
							}else{
								core.closeModel('modal-Knowledge');
								$("#title").val('');
								$("#startOrderNo").val('')
							}
						}else{
							alert("异常！稍后再尝试 ")
						}
						
						
					}
				})
				
			});

			operateEvents = {
				/**
				 * 修改知识点 取出知识点原有值
				 */
				'click .editKnowledge' : function(e, value, row, index) {
					core.openModel('modal-EditKnowledge', '修改知识点', function() {
						$("#editKnowledgeId").val(row.id);
						$("#editKnowledgeTitle").val(row.title);
						$("#editKnowledgeOrderNo").val(row.orderNo);
						$("#editKnowledgeGradeNo").val(row.gradeNo);
						$("#editKnowledgeSubjectNo").val(row.subjectNo);
						$("#EditGrade option[value='"+row.gradeNo+"']").attr("selected",true);
						$("#EditSubject option[value='"+row.subjectNo+"']").attr("selected",true);
						$("#tatil").next("h4").html(
								"<b>编辑知识点</b>   " + row.title);

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
			 * 修改知识点提交
			 */
			
			
			$("#EditKnowledgeSubmit").click(function(){
				var id = $("#editKnowledgeId").val();
				var title = $("#editKnowledgeTitle").val();
				var orderNo = $("#editKnowledgeOrderNo").val();
				var gradeNo = $("#EditGrade").val();
				var subjectNo = $("#EditSubject").val();
				
				if(core.trim(title).length<1){$("#edit-msg").html("知识点名称不能为空！");return;}
				if(gradeNo==0){$("#edit-msg").html("请选择年级！");return;}
				if(subjectNo==0){$("#edit-msg").html("请选择学科！");return;}
				$.ajax({
					url : F.basepath + '/knowledge/editKnowledge',
					type : 'POST',
					data : {id:id,title:title,orderNo:orderNo,gradeNo:gradeNo,subjectNo:subjectNo},
					success : function(data) {
						if (data.result > 0) {
							$("#edit-msg").html("");
							F.table.reload();
							core.closeModel('modal-EditKnowledge');
						} else {
							alert("异常！")
						}
					}
				
					
					
				})
				
				
				
			});
			
			/**
			 * 打开模态框
			 */
			$('#addKnowledge').click(function() {
				core.openModel('modal-Knowledge', '新增知识点', function() {
					core.getDictOptions("年级","grade","#grade");
					core.getDictOptions("学科","subject","#subject");
					
					
				});
				return false;
			});

			
			
			document.getElementById("knowledge-header").onkeydown =keyDownSearch;
			function keyDownSearch(e) {
				// 兼容FF和IE和Opera
				var theEvent = e || window.event;
				var code = theEvent.keyCode || theEvent.which
						|| theEvent.charCode;
				if (code == 13) {
					query();
				}else{
				}
			}	
			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(function(){
				query();
			});
			
			 function query() {
					var title = $("#q_k_title").val();
					var gradeNo = $("#q_k_grade").val();
					var subjectNo=$("#q_k_subject").val();
					var query_url = F.basepath + '/knowledge/listPage?title='
							+ title + '&gradeNo=' + gradeNo+'&subjectNo='+subjectNo;
					$('#KnowledgeTable').bootstrapTable('refresh', {
						url : query_url
					});
				}

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
		
});