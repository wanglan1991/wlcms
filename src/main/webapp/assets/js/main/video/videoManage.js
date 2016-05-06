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
								"<input autocomplete='off'  id='q_k_select' name='q_k_select'  placeholder='知识点、视频名称' type='text' />&nbsp;&nbsp;<select  id='q_k_grade' class='select2-chosen' style='width:200px'></select>&nbsp;&nbsp;<select  id='q_k_subject' class='select2-chosen' style='width:200px'></select>&nbsp;&nbsp;<select  id='q_k_author' class='select2-chosen' style='width:200px'></select>&nbsp;&nbsp;<input autocomplete='off'  id='q_k_isp' name='q_k_isp'  placeholder='可运营商查询' type='text' /><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");				
				//初始化知识点查询框
//				knowldgeSelect({gradeNo:0,subjectNo:0},'#q_k_select');
			}
			/**
			 * 是否具有删除权限
			 */
			if (base.perList.video.del) {
				$("#video-header .actions")
						.append(
								"<a href='#' id='delVideos' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
				
			}

			/**
			 * 是否具有添加权限
			 */
			if (base.perList.video.create) {
				$("#video-header .actions")
						.append(
								"<a href='#' id='addVideo' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			}
			
			/**
			 * 年级、学科下拉框加载 
			 * param : 查询参数 
			 * selectId ： select元素ID
			 */
			var dictList = function(url, param, selectId) {
				// 查询条件的下拉框给出提示信息
				if (selectId == "#q_k_grade" || selectId == "#q_k_subject") {
					$(selectId).prepend("<option value='0' >" + "--请选择"+ (param == 'grade' ? "年级--" : "学科--")+ "</option>");
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
			}
			dictList("/cms/dict/queryDictByCondition" , {typeEncoding:"grade"} ,"#q_k_grade");
			dictList("/cms/dict/queryDictByCondition" , {typeEncoding:"subject"} ,"#q_k_subject");
//			accountList("/cms/account/listAccountByRole" , {role:"teacher"} ,"#q_k_author");
			
			/**
			 * 讲师下拉框加载 
			 * param : 查询参数 
			 * selectId ： select元素ID
			 */
			var accountList = function(url, param, selectId) {
				// 查询条件的下拉框给出提示信息
					$(selectId).prepend("<option value='0' >" + "--请选择讲师--"+ "</option>");
				$.ajax({
					url : F.basepath + url,
					data : param,
					type : 'POST',	
					success : function(data) {
						for (var i = 0; i < data.value.length; i++) {
							$(selectId).append("<option	value=" + data.value[i].id + ">"+ data.value[i].realName + "</option>");
						}
					}
				});
			}
			accountList("/cms/account/listAccountByRole" , {role:"teacher"} ,"#q_k_author");
			/**
			 * 级联，根据年级和学科缩小知识点范围 
			 */
			function setknowldgeSelect(){
				var gradeId = $("#q_k_grade").val();
				var subjectId=$("#q_k_subject").val();
				knowldgeSelect({gradeNo : gradeId,subjectNo : subjectId},"#q_k_select");
			};
			$("#q_k_grade").change(function(){
				setknowldgeSelect();
			});
			$("#q_k_subject").change(function(){
				setknowldgeSelect();
			});

			
			/**
			 * 知识点下拉框加载
			 * param : 查询参数 参数是为了让年级和科目级联知识点  现在只在新增和修改的模态框中使用知识点下拉框 没有级联
			 * selectId ： select元素ID
			 */
			var knowldgeSelect=function(url,param,selectId){
				
				jQuery("#" + selectId).empty();
				jQuery("#" + selectId).prepend("<option value='-1'>--请选择知识点--</option>");
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
				
			}
			
			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(
					function() {
						var videoName=$("#q_k_select").val();
						var knowledge = $("#q_k_select").val();
						var gradeNo = $("#q_k_grade").val();
						var subjectNo=$("#q_k_subject").val();
						var author = $("#q_k_author").val();
						var isp = $("#q_k_isp").val();
						var query_url = F.basepath + '/cms/video/listPage?&videoName=' +videoName+'&knowledge='
								+ knowledge + '&gradeNo=' + gradeNo+'&subjectNo='+subjectNo+'&author='+author+'&isp='+isp;
						
						$('#videoTable').bootstrapTable('refresh', {
							url : query_url
						});
					});
		
				
			operateEvents = {
				/**
				 * 修改视频基础信息
				 */
				'click .editVideo' : function(e, value, row, index) {
					core.openModel('modal-editVideo', '修改视频     '
							+ row.videoName, function() {
						alert("dd");
						$.fn.modal.Constructor.prototype.enforceFocus = function () { };
						if (row != null) {
							knowldgeSelect('/cms/knowledge/queryByCondition',{gradeNo:0,subjectNo:0},'editKnowledge');
							$('#editId').val(row.id);
							$('#editVideo').val(row.videoName);
							$('#editDigest').val(row.digest);
							$('#editUrl').val(row.url);
							$('#editIsp').val(row.isp);
							$('#editFileName').val(row.fileName);
							$('#editAuthor').val(row.author);
							$('#editStatus').val(row.status);
							//设置select2的默认选中值
							$("#editKnowledge option[value='"+row.knowledgeId+"']").attr("selected",true);
							$("#editKnowledge").select2();
						}
					});
				},
//			
				
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
				 * 调用播放器
				 */
				'click .playVideo' : function(e, value, row, index) {
					alert("sss");
					core.openModel('modal-playVideo', 
						 function(){
//					player = new qcVideo.Player(
//							//页面放置播放位置的元素 ID
//							"id_video_container",
//							{
//							//视频 ID (必选参数)
//							"file_id" : "14651978969257805698",
//							//应用 ID (必选参数)，同一个账户下的视频，该参数是相同的
//							"app_id" : "1252102229 "
//								
//							});
					});
				},

			
			};

			var cols = [ {
				checkbox : true
			}, {
				field : 'id',
				title : '主键',
				visible : false
			}, {
				field : 'videoName',
				title : '视频名称'

			}, {
				field : 'knowledgeId',
				title : '知识点id',
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
			},{
				field : 'author_id',
				title : '讲师id',
				visible : false
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
			//新增视频模态框
			$('#addVideo').click(function() {
				core.openModel('modal-Video', '新增视频',
						function(){
					$('#modal-Video').modal().css({
						height:'800px'
					})
					$.fn.modal.Constructor.prototype.enforceFocus = function () { };
				}
						);
				accountList("/cms/account/listAccountByRole" , {role:"teacher"} ,"#author");
				knowldgeSelect('/cms/knowledge/queryByCondition',{gradeNo:0,subjectNo:0},'knowledge');
				$('#knowledge').select2();
				return false;
			});
			
			//新增视频提交
			$('#btnSubmit').click(function(){
				alert("提交");
				var videoName=$("#videoName").val();
				var videoId=$("#videoId").val();
				var digest=$("#digest").val();
				var url=$("#url").val();
				var isp=$("#isp").val();
				var fileName=$("#fileName").val();
				var authorId=$("#author").val();
				var knowledgeId=$("#knowledge").val();
//				var duration=$("#duration").val();
				if(videoName.length<1){$("#videoName-error").html("请输入视频名称!");$("#videoName-error").css("color","#b94a48");return ;}
				if(fileName.length<1){$("#fileName-error").html("请先选择视频上传!");$("#fileName-error").css("color","#b94a48");return ;}
				if(authorId==0){$("#author-error").html("请选择讲师!");$("#author-error").css("color","#b94a48");return ;}
				if(knowledgeId==-1){$("#knowledge-error").html("请选择所属知识点!");$("#knowledge-error").css("color","#b94a48");return ;}
				if(url.length<1){$("#url-error").html("请先选择视频上传!");$("#url-error").css("color","#b94a48");return ;}
			
				$.ajax({
					url :  F.basepath + '/cms/video/addVideo',
					type : 'POST',
					data : {
						videoName : videoName,
						videoId : videoId,
						digest : digest,
						url : url,
						isp : isp,
						fileName:fileName,
						authorId : authorId,
//						duration:duration,
						knowledgeId:knowledgeId
					},
					dataType: "json", 
					success : function(data) {
						if (data.result > 0) {
							core.closeModel('modal-Video');
							F.table.reload();
							
						}
						 else{
						 $("#stitle-error").html(data.msg);
						 $("#stitle-error").css('color','red');
						 }
					}

				});
				
            });
			
			/*传点播文件 ID 的方式*/
			$('#playVideo').click(function(){
//				alert("play");
//				player = new qcVideo.Player(
//						//页面放置播放位置的元素 ID
//						"id_video_container",
//						{
//						//视频 ID (必选参数)
//						"file_id" : "14651978969257805698",
//						//应用 ID (必选参数)，同一个账户下的视频，该参数是相同的
//						"app_id" : "1252102229 "
//							
//						});
			});

			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function() {
				$("#videoName-error").html('');
				$("#url-error").html('');
				$("#author-error").html('');
				$("#duration-error").html('');
				$("#knowledge-error").html('');
				$("#msg").html('');
				$("#videoFile").val('');
				core.closeModel('modal-Video');
			});
			$('#EditbtnClose').click(function() {
				//关闭模态框 清除报错信息
				$("#editVideo-error").html('');
				$("#editUrl-error").html('');
				$("#editAuthor-error").html('');
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
			if (base.perList.video.edit) {
				_btnAction += "<a data-toggle='modal' class='editVideo btn btn-success btn-small' href='#' title='编辑视频' style='margin-left:5px'>编辑</a>";
			}
			if (base.perList.video.del) {
				_btnAction += "<a class='delVideo btn btn-danger btn-small' href='#'  title='删除角色' style='margin-left:5px'>删除</a>";
			}
//			if (base.perList.video.del) {
				_btnAction += "<a class='playVideo btn btn-primary btn-small' href='#'  title='播放视频' style='margin-left:5px'>播放</a>";
//			}
			return _btnAction;
		}
	};
	
	
		
	jQuery(document).ready(function() { 
		/**
		 * 表单验证 提交修改视频基础信息
		 */
		$('#Editsubmit-form').validate({				
			submitHandler:function(form){
					var id = $("#editId").val();
					var videoName = $("#editVideo").val();
					var digest = $("#editDigest").val();
					var url=$("#editUrl").val();
					var isp = $("#editIsp").val();
					var fileName = $("#editFileName").val();
					var author=$("#editAuthor").val();
					var knowledgeId=$("#editKnowledge").val();
					var status=$("#editStatus").val();
					$.ajax({
						url : F.basepath + '/cms/video/editVideo',
						type : 'POST',
						data : {
						id : id,
						videoName : videoName,
						digest : digest,
						url : url,
						isp : isp,
						author : author,
						fileName : fileName,
						knowledgeId : knowledgeId
						},
						success : function(data) {
							if (data.result > 0) {
								core.closeModel('modal-editVideo');								
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
			editUrl:{required:true},
			
		},
		messages:{
			editVideo: '视频名称不能为空',
			editAuthor:'讲师名字不能为空',
			editUrl:'视频url不能为空',
			
		},
			
		});
		
		//上传表单验证和提交
		$(function() {
			$("#upload").ajaxForm({
				//定义返回JSON数据，还包括xml和script格式
//				dataType : 'json',
				beforeSend : function() {
					//表单提交前做表单验证
					if($("#videoFile").val()=="")
					{
					alert("请先上传文件");
					return;
					}
				
				$("#submitbutton").attr("disabled","disalbed");
				alert("submit");
				},
				success : function(data) {
					alert("functionData");
					$("#submitbutton").attr("disabled","");
					//提交成功后调用
					if (data.value!=null) {
						alert(data.value.fileName);
						$("#videoId").val(data.value.videoId);
						$("#fileName").val(data.value.fileName);
						$("#url").val(data.value.url);
//						window.location.href = "/cms/user/listUser";
					} else {
						alert("导入失败");
						$("#videoFile-error").html(data.msg);
//						$("#errorMsg").html(data.data).show();
					}
				}
			});
		});
		
	}); 
	
});