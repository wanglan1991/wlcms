// 所有模块都通过 define 来定义
define(function(require, exports, module) {
	var base = require('base');
	var core = require('core');
	// 通过 require 引入依赖
	var F = module.exports = {
		basepath : '',
		checkPermissionTree : {},
		distributePermissionTree : {},
		table : new core.Table('videoTable'),
		init : function(_basepath) {
			F.basepath = _basepath;

			/**
			 * 是否具有查询权限
			 */
			if (base.perList.video.check) {
				$("#video-header .actions")
						.append(
								"<input autocomplete='off'  id='q_k_knowledge' name='q_k_knowledge'  placeholder='请输入知识点' type='text' />&nbsp;&nbsp;<select  id='q_k_grade' class='select2-chosen' style='width:200px'></select>&nbsp;&nbsp;<select  id='q_k_subject' class='select2-chosen' style='width:200px'></select><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");				
				//初始化知识点查询框
//				knowldgeSelect({gradeNo:0,subjectNo:0},'#q_k_knowledge');
			}
			/**
			 * 是否具有删除角色权限
			 */
			if (base.perList.video.del) {
				$("#video-header .actions")
						.append(
								"<a href='#' id='delVideos' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
				
			}

			/**
			 * 是否具有添加角色权限
			 */
			if (base.perList.video.create) {
				$("#video-header .actions")
						.append(
								"<a href='#' id='addVideo' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			}

			/**
			 * 年级和学科下拉框加载 
			 * param : 查询参数 
			 * selectId ： select元素ID
			 */
			var dictList = function(url, param, selectId) {
				// 查询条件的下拉框给出提示信息
				if (selectId == "#q_k_grade" || selectId == "#q_k_subject") {
					$(selectId).prepend("<option value='0' >" + "--请选择"+ (param == 'grade' ? "年级--" : "学科--")+ "</option>");
				} else {
					var html = '';
				}
				$.ajax({
					url : F.basepath + url,
					data : param,
					type : 'POST',	
					success : function(data) {
						for (var i = 0; i < data.value.length; i++) {
							$(selectId).append("<option	value=" + data.value[i].id + ">"+ data.value[i].value + "</option>");
						}
					}
				});
//				jQuery( selectId).select2();
			}
			dictList("/cms/dict/queryDictByCondition" , {typeEncoding:"grade"} ,"#q_k_grade");
			dictList("/cms/dict/queryDictByCondition" , {typeEncoding:"subject"} ,"#q_k_subject");
			/**
			 * 级联，根据年级和学科缩小知识点范围 
			 */
			function setknowldgeSelect(){
				var gradeId = $("#q_k_grade").val();
				var subjectId=$("#q_k_subject").val();
				knowldgeSelect({gradeNo : gradeId,subjectNo : subjectId},"#q_k_knowledge");
			};
			$("#q_k_grade").change(function(){
				setknowldgeSelect();
			});
			$("#q_k_subject").change(function(){
				setknowldgeSelect();
			});
			
			/**
			 * 知识点下拉框加载
			 * param : 查询参数 
			 * selectId ： select元素ID
			 */
			function knowldgeSelect(param,selectId){
				var url = "/cms/knowledge/queryByCondition";
				jQuery("#" + selectId).empty();
				if(selectId=="#q_k_knowledge")
				$(selectId).prepend("<option value='0' >--请输入知识点--</option>");
				var html='';
				jQuery.ajax({
					url: url, 
					data:param,
					dataType: "json", 
					async: false,
					success : function(data) {
						for (var i = 0; i < data.value.length; i++) {
							jQuery("#" + selectId).append("<option	value=" + data.value[i].id + ">"+ data.value[i].title + "</option>");
						}
					}
				}); 
				
				jQuery("#" + selectId).select2();
			}
			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(
					alert("sss"),
					function() {
						var knowledge = $("#q_k_knowledge").val();
						var gradeNo = $("#q_k_grade").val();
						var subjectNo=$("#q_k_subject").val();
						alert(knowledge);
						var query_url = F.basepath + '/cms/video/listPage?knowledge='
								+ knowledge + '&gradeNo=' + gradeNo+'&subjectNo='+subjectNo;
						$('#videoTable').bootstrapTable('refresh', {
							url : query_url
						});
					}),			
			operateEvents = {
				/**
				 * 修改角色
				 */
				'click .editVideo' : function(e, value, row, index) {
					core.openModel('modal-editVideo', '修改视频     '
							+ row.videoName, function() {
						if (row != null) {
							$('#editId').val(row.id);
							$('#editVideo').val(row.videoName);
							$('#editDigest').val(row.digest);
							$('#editIsp').val(row.isp);
							$('#editFileName').val(row.fileName);
							$('#editAuthor').val(row.author);
							$("#editKnowledge option [value='"+row.knowledgeId+"']").attr('selected',true);
							knowldgeSelect({gradeNo:0,subjectNo:0},'editKnowledge');
						}
					});
				},
				/**
				 * 删除角色
				 */
				'click .delVideo' : function(e, value, row, index) {
					base.bootConfirm("是否确定删除？", function() {
						var ids = new Array();
						ids.push(row.id);
						F.delVideo(ids);
					});
				},
				/**
				 * 菜单授权
				 */
				/**
				 * 启用或停用用户
				 */
				'click .confine' : function(e, value, row, index) {
					$.ajax({
						url : F.basepath + '/cms/video/confine',
						type : 'POST',
						data : {
							id : row.id,
							status : row.status == 1 ? 0 : 1
						},
						success : function(data) {
							if (data.result > 0) {
								F.reload();
							} else {
								alert("操作失败！")
							}
						}
					})

				},

			/**
			 * 查看权限
			 */

			};

			var cols = [ {
				checkbox : true
			}, {
				field : 'id',
				title : '主键'
			}, {
				field : 'videoName',
				title : '视频名称'

			}, {
				field : 'knowledgeId',
				title : '知识点',
				visible : false
			}, {
				field : 'knowledge',
				title : '知识点'
			}, {
				field : 'grade',
				title : '年级'

			}, {
				field : 'subject',
				title : '学科'
			}, {
				field : 'duration',
				title : '时长'
			}, {
				field : 'digest',
				title : '简介'
			}, {
				field : 'author',
				title : '讲师'

			}, {
				field : 'url',
				title : '链接'
			}, {
				field : 'isp',
				title : '运营商'
			} ];
			// 是否需要操作列
			if (base.perList.video.edit || base.perList.video.del
					|| base.perList.video.confine )
				cols.push({
					align : 'center',
					title : '操作',
					events : operateEvents,
					formatter : F.operateFormatter
				});

			/**
			 * 角色列表
			 */
			F.table.init(F.basepath + '/cms/video/listPage', cols);

			/**
			 * 批量删除
			 */
			$('#delVideos').click(
					function() {
						var ids = F.table.getIdSelections();
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个角色？",
									function() {
										F.delVideo(ids);
									});
						} else {
							base.bootAlert({
								"ok" : false,
								"msg" : "请选择你要删除的角色！"
							});
						}
					});

			/**
			 * 打开模态框
			 */
			$('#addVideo').click(function() {
				core.openModel('modal-Video', '新增角色');
				return false;
			});

			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function() {
				$("#name").val('');
				$("#encoding").val('');
				$("#encoding-error").html('');
				$("#msg").html('');
				core.closeModel('modal-Video');
			});
			$('#editBtnClose').click(function() {
				$("#editName").val('');
				$("#editEncoding").val('');
				$("#editId").val('');
				$("#editEncoding-error").html('');
				core.closeModel('modal-editVideo');

			});

			$('#btnDistributePermissionClose').click(function() {
				core.closeModel('modal-DistributePermission');
			});

			$('#btnCheckPermissionClose').click(function() {
				core.closeModel('modal-CheckPermission');
			});

		},
		
		delVideo : function(ids) {
			$.ajax({
				url : F.basepath + '/cms/video/delete',
				type : 'post',
				data : {
					ids : ids.toString()
				},
				success : function(data) {
					if (data.result > 0) {
						F.reload();
					} else if (data.result == 0) {
						base.bootAlert({
							"ok" : false,
							"msg" : "网络异常"
						});
					}
				}
			})
		},
		reload : function() {
			F.table.reload();
		},

		operateFormatter : function(value, row, index) {
			var _btnAction = "";
			if (base.perList.video.confine) {
				_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
						+ (row.status == 1 ? "停用" : "启用") + "</a>";
			}
			if (base.perList.video.grant) {
				_btnAction += "<a class='distributePermission btn btn-primary btn-small' href='#' title='菜单授权' style='margin-left:5px'>授权</a>";
			}
			if (base.perList.video.edit) {
				_btnAction += "<a data-toggle='modal' class='editVideo btn btn-success btn-small' href='#' title='编辑角色' style='margin-left:5px'>编辑</a>";
			}
			if (base.perList.video.del) {
				_btnAction += "<a class='delVideo btn btn-danger btn-small' href='#'  title='删除角色' style='margin-left:5px'>删除</a>";
			}
			return _btnAction;
		}
	};
		
	jQuery(document).ready(function() { 
		/**
		 * 表单验证 提交修改知识点
		 */
		$('#Editsubmit-form').validate({				
			submitHandler:function(form){
					var id = $("#editId").val();
					var videoName = $("#editVideo").val();
					var digest = $("#editDigest").val();
					var isp = $("#editIsp").val();
					var fileName = $("#editFileName").val();
					var author=$("#editAuthor").val();
					var knowledgeId=$("#editKnowledge").val();
					alert(knowledgeId);
					$.ajax({
						url : F.basepath + '/video/editVideo',
						type : 'POST',
						data : {
							id : id,
							videoName : videoName,
							digest : digest,
							isp : isp,
							author : author,
							fileName : fileName,
						knowledgeId:knowledgeId
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
			editVideo:{required:true},
			editAuthor:{required:true},
			editKnowledge:{required:true},
		},
		messages:{
			editVideo: '视频名称不能为空',
			editAuthor:'讲师名字不能为空',
			editKnowledge:'请选择视频对应的知识点',
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