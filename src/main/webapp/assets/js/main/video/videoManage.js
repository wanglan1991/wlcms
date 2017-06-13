// 所有模块都通过 define 来定义
define(function(require, exports, module) {
	var base = require('base');
	var core = require('core');
	// 通过 require 引入依赖
	var F = module.exports = {
		// 视播放用的应用 ID
		app_id : '1252120034',
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
								"<input autocomplete='off'  id='q_k_select' name='q_k_select'  placeholder='知识点、视频名称' type='text' />" +
								"&nbsp;&nbsp;<select  id='q_k_grade' class='select2-chosen' style='width:200px'></select>" +
								"&nbsp;&nbsp;<select  id='q_k_subject' class='select2-chosen' style='width:200px'></select>" +
								"&nbsp;&nbsp;<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");				
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
			 * 年级、学科下拉框加载 param : 查询参数 selectId ： select元素ID
			 */
			
			core.getDictOptions('年级','grade',"#q_k_grade");
			core.getDictOptions('学科','subject',"#q_k_subject");
			$("#knowledge").select2();
			$("#editKnowledge").select2();
			
			
		
			
			/**
			 * 级联，根据年级和学科缩小知识点范围
			 */
			function setknowldgeSelect(gradeNo,subjectNo,Knowledge){
				
				var gradeId=0;
				var subjectId=0
				if($(gradeNo).val()>0 ){
					gradeId=$(gradeNo).val()
				}else{
					return ;
				}
				if($(subjectNo).val()>0 ){
					 subjectId=$(subjectNo).val()
				}else{
					return ;
				}
			
				knowldgeSelect('/cms/knowledge/queryByCondition',{gradeNo : gradeId,subjectNo : subjectId},Knowledge);
			};
			
			$("#editGrade").change(function(){
				setknowldgeSelect('#editGrade','#editSubject','#editKnowledge');
			});
			$("#editSubject").change(function(){
				setknowldgeSelect('#editGrade','#editSubject','#editKnowledge');
			});
			
			$("#grade").change(function(){
				
				setknowldgeSelect('#grade','#subject','#knowledge');
			});
			$("#subject").change(function(){
			
				setknowldgeSelect('#grade','#subject','#knowledge');
			});

			
			/**
			 * 知识点下拉框加载 param : 查询参数 参数是为了让年级和科目级联知识点 现在只在新增和修改的模态框中使用知识点下拉框
			 * 没有级联 selectId ： select元素ID
			 */
			var knowldgeSelect=function(url,param,selectId){
				if(param.gradeNo==0||param.subjectNo==0){return;}
				$(selectId).empty();
				$.ajax({
					url: url, 
					data:param,
					type:"get", 
					success : function(data) {
						var html="";
						for (var i = 0; i < data.value.length; i++) {
							html+="<option value=" + data.value[i].id + ">"+ data.value[i].title + "</option>"
						}
						
						$(selectId).append(html);
						$(selectId).select2();
						console.log(html);
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
						var query_url = F.basepath + '/cms/video/listPage?&videoName=' +videoName+'&knowledge='
								+ knowledge + '&gradeNo=' + gradeNo+'&subjectNo='+subjectNo;
						
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
						knowldgeSelect('/cms/knowledge/queryByCondition',{gradeNo : row.gradeNo,subjectNo : row.subjectNo},"#editKnowledge");
// $.fn.modal.Constructor.prototype.enforceFocus = function () { };
						
						$("#editVideoImg").removeAttr("disabled"); 
						$("#editUploadVideoImgButton").removeAttr("disabled"); 
						$("#edit-msg").html('');
						$("#editVideoFile").val('');
						$("#editVideoKey").val('');
						$("#editVideoFileSub").val('');
						$("#editSubVideoKey").val('');
						if (row != null) {
							$('#editId').val(row.id);
							$('#editVideo').val(row.videoName);
							$('#editDigest').val(row.digest);
							$('#editUrl').val(row.url);
							$('#editIsp').val(row.isp);
							$("#editPrice").val(row.price);
							$("#editDiscount").val(row.discount);
							$('#editVideoFileName').val(row.fileName);
							$('#editOldVideoFileName').val(row.fileName);
							$('#editStatus').val(row.status);
							$("#editVideoImgUrl").val(row.imageUrl);
							// 设置knowledgeId的默认选中值
// if(row.knowledgeId!=null&&row.knowledgeId.length>1){
// var ids=row.knowledgeId.split(",");
// for(var i=0;i<ids.length;i++){
// if(ids[i]!=""){
// $('#editKnowledge').val(ids[i]).trigger('change');
// }
// }
// }
// $("#editKnowledge
// option[value='"+row.knowledgeId+"']").attr("selected","selected");
// $("#editKnowledge").select2();
							core.getEditDictOptions("年级","grade","#editGrade",row.gradeNo);
							core.getEditDictOptions("学科","subject","#editSubject",row.subjectNo);
						}
					});
					
					
				},
//			
				
				/**
				 * 删除视频
				 */
				'click .delVideo' : function(e, value, row, index) {
					base.bootConfirm("是否确定删除？", function() {
						var ids = new Array();
						ids.push(row.id);
						F.delVideo(ids);
					});
				},
				
				/**
				 * 生成选课
				 */
				'click .createTextbook': function(e, value, row, index){
					var videoId =  row.id;
					$.ajax({
						url : F.basepath + '/cms/video/createTextbook',
						type:"post",
						data:{id:videoId},
						success:function(data){
							if (data.result > 0) {
								F.reload();
							} else {
								alert("操作失败！请与管理员联系！")
							}
						}
						
					})
					
					
					
				},
//				
				
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
				'click .videoPlay' : function(e, value, row, index) {
					core.openModel('modal-playVideo',"正在播放视频："+row.videoName,(function(){
							 var option = {
										"auto_play": "0",
										"file_id": row.videoKey,
										"app_id": "1252219724",
										"disable_full_screen": 0,
										// "disable_drag":1,
										"width": 650,
										"height": 400
									};
					player = new qcVideo.Player("id_video_container",option);
					})() );
				} ,
				/**
				 * 编辑视频配套习题
				 */
				'click .editVideoExercise' : function(e, value, row, index){
					$('#modal-editVideoExercise').attr('videoId',row.id);
					$("#exerciseTree").empty();
					$("#exerciseSubject").empty();
					$("#exerciseKnoeledge").empty();
					
					core.openModel('modal-editVideoExercise',"编辑视频："+row.videoName+"配套习题",(function(){
						if(row.exerciseSubjectNo!=0&&row.exerciseGradeNo!=0&&row.exerciseKnoeledgeId!=0){
							core.getEditDictOptions("年级","grade","#exerciseGrade",row.exerciseGradeNo);
							core.getEditDictOptions("科目","subject","#exerciseSubject",row.exerciseSubjectNo);
							core.editGetKnoeledgeOption("#exerciseKnoeledge",row.exerciseGradeNo,row.exerciseSubjectNo,row.exerciseKnoeledgeId);
							core.commonTree(F.basepath+'/cms/video/exerciseTree',
								{subjectNo:row.exerciseSubjectNo,
		            			    gradeNo:row.exerciseGradeNo,
		            				knowledgeId:row.exerciseKnoeledgeId,
		            				id:row.id},"#exerciseTree");
		            	}else{
		            		core.getDictOptions("年级","grade","#exerciseGrade");
		            		$("#exerciseSubject").append("<option value='0'>学科----</option>");
							$("#exerciseKnoeledge").append("<option value='0'>知识点----</option>");
		            	}
					}));
				} ,
				/**
				 * 视频转码
				 */
				'click .transcodeVideo' : function(e, value, row, index) {
					
					$.ajax({
						url : F.basepath + '/cms/vodCloud/transcode',
						type : 'POST',
						data : {
							fileId : row.videoKey,
							type :1
						},
						success : function(data) {
							if (data.msg != "") {
								alert(data.msg);
							} else {
								alert("异常！！")
							}
							F.reload();
						}
					})									
					if(row.subVideoKey!=null){
						$.ajax({
							url : F.basepath + '/cms/vodCloud/transcode',
							type : 'POST',
							data : {
								fileId : row.subVideoKey,
								type : 2
							},
							success : function(data) {}
						})
					}
				},
				'click .createTestpaper' :function(e, value, row, index){
					$(".createTestpaper."+row.id).remove();
					$.ajax({
						url : F.basepath + '/cms/video/createTestpaper',
						type : 'POST',
						data : {
							id : row.id
						},
						success : function(data) {
							if(data.result>0){
								F.reload();
							}else{
								alert("系统发生异常！");
							}
							
						}
					})
					
					
				}
				
			
			};

			var cols = [ {
				checkbox : true,
			}, {
				field : 'id',
				title : '主键',
			}, {
				field : 'videoName',
				title : '视频名称'

			}, {
				field : 'fileName',
				title : '视频文件名'

			}, {
				field : 'knowledgeId',
				title : '知识点id',
				visible : false
			},  {
				field : 'grade',
				title : '年级'

			}, {
				field : 'subject',
				title : '学科'
			}, {
				field : 'duration',
				title : '时长'
			},{
				field : 'price',
				title : '价格 ￥'
			},{
				field : 'discount',
				title : '折扣 %'
			},{
				field : 'authorId',
				title : '讲师id',
				visible : false
			},{
				field : 'gradeNo',
				title : '年级id',
				visible : false
			},{
				field : 'subjectNo',
				title : '科目id',
				visible : false
			}, {
				field : 'author',
				title : '更新者'
			},
			{
				field :'videoKey',
				title : '视频key',
				visible : false
			},
			{
				field : 'isp',
				title : '运营商',
				visible : false
			},{
				field : 'transcodeStatus',
				title : '转码状态',
				visible : false
			}];
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
			
			// 添加知识点
			 function getKnoeledgeOption(id,subjectNo,gradeNo){
	            	if(subjectNo==0||gradeNo==0){return}
		        	$(id).empty();
		        	var knowledgesOption ="";
		        	$.ajax({
		        		url:F.basepath+"/cms/knowledge/list",
		        		type:"GET",
		        		data:{subjectNo:subjectNo,gradeNo:gradeNo},
		        		success:function(data){
		        			if(data.value.length<1){
		        				knowledgesOption+="<option value = '0'>无 ----</option>";
		        			}
		        			for(var i=0;i<data.value.length;i++){
		        				if(i==0){
		        				knowledgesOption+="<option value = '0'>知识点----</option>";
		        				};
		        				knowledgesOption+="<option value = '"+data.value[i].id+"'>"+data.value[i].title+"</option>"
		        			}
		        			$(id).append(knowledgesOption);
		        		}
		        	})
	   		}
			 // 添加中加载学科
            function addSubjectOption(gradeNo,idOrClass){
            	if(gradeNo==0){return}
            	var subjectOption="";
            	$(idOrClass).empty();
            	$.ajax({
	        		url:F.basepath+"/cms/exercise/subjectList",
	        		type:"GET",
	        		data:{gradeNo:gradeNo},
	        		success:function(data){
	        			if(data.value.length<1){
	        				subjectOption+="<option value = '0'>无 ---</option>";
	        			}
	        			for(var i=0;i<data.value.length;i++){
	        					if(i==0){
	        							subjectOption+="<option value=0>学科---</option>";
	        					}
	        					subjectOption+="<option value = '"+data.value[i].subjectNo+"'>"+data.value[i].subject+"</option>";
	        			}
	        			$(idOrClass).append(subjectOption);
	        		}
	        	});
            }
			
			/**
			 * 监听视频习题编辑
			 */
			$('#exerciseGrade').change(function(){
				var gradeNo = $("#exerciseGrade").val();
				var subjectNo = $("#exerciseSubject").val();
				addSubjectOption(gradeNo,"#exerciseSubject");
				getKnoeledgeOption("#exerciseKnoeledge",subjectNo,gradeNo);
			});
			
			/**
			 * 监听年级选择
			 */
			$('#exerciseSubject').change(function(){
				var subjectNo = $("#exerciseSubject").val();
				var gradeNo = $("#exerciseGrade").val();
				getKnoeledgeOption("#exerciseKnoeledge",subjectNo,gradeNo);
			});
			
			$('#exerciseKnoeledge').change(function(){
				var url=F.basepath+'/cms/video/exerciseTree';
            		var obj={
            				subjectNo:$("#exerciseSubject").val(),
            				gradeNo:$("#exerciseGrade").val(),
            				knowledgeId:$("#exerciseKnoeledge").val(),
            				id:$("#modal-editVideoExercise").attr('videoId')
            		}
          		core.commonTree(url.toString(),obj,"#exerciseTree");
			});
			
			/**
			 * 修改视频信息
			 */
			$("#editbtnSubmit").click(function(){
				var videoId = $('#editId').val();
				var videoName = $("#editVideo").val();
				var digest = $("#editDigest").val();
				var gradeNo = $("#editGrade").val();
				var subjectNo = $("#editSubject").val();
				var price = $("#editPrice").val();
				var discount = $("#editDiscount").val();
				var videoImageUrl =$("#editVideoImgUrl").val();
				var arrKnoeledge=$("#editKnowledge").find('option:selected');
				var videoKey =$("#editVideoKey").val();
				var videoSubKey =$("#editSubVideoKey").val();
				var videoFileName = $("#editVideoFileName").val();
				var editOldVideoFileName = $("#editOldVideoFileName").val();
				var knowledgeId='';
				var arr='';
				if(arrKnoeledge!=null){
				
					for(var i=0; i<arrKnoeledge.length;i++){
						knowledgeId+=$(arrKnoeledge[i]).val()+',';
						arr +=$(arrKnoeledge[i]).text()+',';
					}
				}				
				if(videoName.length<1){$("#edit-msg").html("视频名称不能为空！");return;}
				if(videoName.videoFileName<1){$("#edit-msg").html("视频文件名不能为空！");return;}
				if(digest.length<1){$("#edit-msg").html("摘要不能为空！");return;}
				if(gradeNo==0){$("#edit-msg").html("年级不能为空！");return;}
				if(subjectNo==0){$("#edit-msg").html("学科不能为空！");return;}
				if(price.length<1){$("#edit-msg").html("价格为必填项！");return;}
				if(discount.length<1){$("#edit-msg").html("折扣为必填项！");return;}
				if(videoImageUrl.length<1){$("#edit-msg").html("必须填写该截图URL！");return;}
				
				$.ajax({

					url :  F.basepath + '/cms/video/editVideo',
					type : 'POST',
					data : {
						id:videoId,
						price:price,
						discount:discount,
						videoName : videoName,
						digest : digest,
						gradeNo : gradeNo,
						subjectNo : subjectNo,                                                                                                    
						imageUrl:videoImageUrl,
						knowledgeId : knowledgeId,
						knowledge : arr,
						videoKey:videoKey,
						subVideoKey:videoSubKey,
						fileName:videoFileName,
						oldFileName:editOldVideoFileName
					},
					dataType: "json", 
					success : function(data) {
						if (data.result > 0) {
							core.closeModel('modal-editVideo');
							F.table.reload();
						}
					}
				});
				
//				完整视频转码
				if(videoKey.length>0){
					$.ajax({
						url : F.basepath + '/cms/vodCloud/transcode',
						type : 'POST',
						data : {fileId : videoKey,type :1}		
					});
				}
//				试看视频转码
				if(videoSubKey.length>0){
					$.ajax({
						url : F.basepath + '/cms/vodCloud/transcode',
						type : 'POST',
						data : {fileId : videoSubKey,type :2}		
					});
				}
				
			});
			
			
			/**
			 * 修改视频配套习题提交
			 */
			$("#editVideoExerciseSubmit").click(function(){
					var arrIds = core.getTreeIds("exerciseTree");
					$.ajax({
						url : F.basepath+'/cms/video/updateVideoExercise',
						type : 'POST',
						data : {arr:arrIds,videoId:$("#modal-editVideoExercise").attr('videoId')},
						dataType: "json", 
						success : function(data) {
							
								F.table.reload();
						
						}
					});
					core.closeModel('modal-editVideoExercise');
					$("#exerciseTree").empty();
				});
			
			
			/**
			 * 批量删除
			 */
			$('#delVideos').click(
					function() {
						var ids = F.table.getIdSelections();
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个视频？",
									function() {
										F.delVideo(ids);
									});
						} else {
							base.bootAlert({
								"ok" : false,
								"msg" : "请选择你要删除的视频！"
							});
						}
					});
		

			/**
			 * 打开模态框
			 */
			// 新增视频模态框
			$('#addVideo').click(function() {
				core.openModel('modal-Video', '新增视频',
						function(){
					$.fn.modal.Constructor.prototype.enforceFocus = function () { };
				}
						);
				core.getDictOptions('年级','grade',"#grade");
				core.getDictOptions('学科','subject',"#subject");
				$("#subVideoKey").val('');
				$("#videoFileSub").val('');
				$("#qsubmitbutton").removeAttr("disabled");
				$("#videoFile").removeAttr("disabled");	
				$("#videoImg").removeAttr("disabled");
				$("#uploadVideoImgButton").removeAttr("disabled");
				$("#addPrice").val('');
				$("#videoImgUrl").val('');
				return false;
			});
			
		
			// 新增视频提交
			$('#btnSubmit').click(function(){
				var videoName=$("#videoName").val();
				var videoKey=$("#videoKey").val();
				var subVideoKey = $("#subVideoKey").val();
				var digest=$("#digest").val();
				var gradeNo=$("#grade").val();
				var subjectNo=$("#subject").val();
				var arrKnoeledge=$("#knowledge").find('option:selected');
				var videoFileName = $("#videoFileName").val();
				var price = $("#addPrice").val();
				var discount = $("#addDiscount").val();
				var videoUrl =$("#videoUrl").val();
				var videoImgUrl=$("#videoImgUrl").val();
				var knowledgeId='';
				var arr='';
				if(arrKnoeledge!=null){
				
					for(var i=0; i<arrKnoeledge.length;i++){
						knowledgeId+=$(arrKnoeledge[i]).val()+',';
						arr +=$(arrKnoeledge[i]).text()+',';
					}
				}
				var videoKey=$("#videoKey").val();
				if(discount.length<1){$("#msg").html("折扣为必填项!");return ;}
				if(price.length<1){$("#msg").html("价格为必填项!");return ;}		
				if(videoName.length<1){$("#msg").html("请输入视频名称!");return ;}
				if(gradeNo==0){$("#msg").html(" 请选择年级!");return;}
				if(subjectNo==0){$("#msg").html(" 请选择科目!");return;}
				if(videoImgUrl.length<10){$("#msg").html("请给该视频上传截图!");return;}
				if(videoFileName==""||videoKey==""){$("#msg").html(" 请上传视频!");return;}
				
				$.ajax({
					url :  F.basepath + '/cms/video/addVideo',
					type : 'POST',
					data : {
						price:price,
						discount:discount,
						imageUrl:videoImgUrl,
						videoName : videoName,
						videoKey : videoKey,
						digest : digest,
						gradeNo : gradeNo,
						subjectNo : subjectNo,
						knowledgeId : knowledgeId,
						knowledge : arr,
						fileName : videoFileName,
						subVideoKey:subVideoKey,
						isp : "tencent"
					},
					dataType: "json", 
					success : function(data) {
						if (data.result > 0) {
							core.closeModel('modal-Video');
							clear();
							F.table.reload();
							
						}
						
					}

				});
				
            });
			
			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function() {
				clear();
				core.closeModel('modal-Video');
			});
			$('#EditbtnClose').click(function() {
				// 关闭模态框 清除报错信息
				$("#editVideo-error").html('');
				$("#editUrl-error").html('');
				$("#editAuthor-error").html('');
				core.closeModel('modal-editVideo');		
			});
			// 关闭编辑配套习题模态框
			$("#editVideoExerciseClose").click(function(){
				core.closeModel('modal-editVideoExercise');
				
			})
			
			// 新增清除页面数据
			function clear(){
				// 关闭模态框时清除所有错误提示
				$("#msg").html('');
				// 清除页面输入值
				$("#videoName").val('');
				$("#videoFileName").val('');
				$("#digest").val('');
				$("#videoKey").val('');
				$("#knowledge").select2().empty('');
				$("#knowledge").select2().empty('');
				$("#knowledge").select2().empty('');
				$("#videoFile").val('');
				$("#videoImgUrl").val('');
				$("#videoImg").val('');
				$("#subVideoKey").val('');
				$("#videoFileSub").val('');
				$("#addPrice").val('');
			}
			
			

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
			
			
			if(base.perList.video.createTextbook&&row.exerciseCount>0&&row.hasTestpaper==0){
				_btnAction += "<a class='createTestpaper "+row.id+" btn btn-success btn-small' href='#'  title='生成题库组卷' style='margin-left:5px'>生成组卷</a>";
				
			}
			if(base.perList.video.createTextbook&&row.textbookId==0){
				_btnAction += "<a class='createTextbook btn btn-success btn-small' href='#'  title='生成选课' style='margin-left:5px'>生成选课</a>";
				
			}
			if(base.perList.video.editVideoExercise&&row.transcodeStatus==2){
				_btnAction += "<a class='editVideoExercise btn btn-primary btn-small' href='#'  title='编辑配套习题' style='margin-left:5px'>编辑配套习题</a>";
				
			}
			if (base.perList.video.play&&row.transcodeStatus==2) {
				_btnAction += "<a class='videoPlay btn btn-primary btn-small' href='#'  title='点播' style='margin-left:5px'>点播</a>";
			}
			
			
			if (base.perList.video.confine&&row.transcodeStatus==2) {
				_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
						+ (row.status == 1 ? "停用" : "启用") + "</a>";
			}
			if (base.perList.video.edit&&row.transcodeStatus==2) {
				_btnAction += "<a data-toggle='modal' class='editVideo btn btn-success btn-small' href='#' title='编辑视频' style='margin-left:5px'>编辑</a>";
			}
			if (base.perList.video.del&&row.transcodeStatus==2) {
				_btnAction += "<a class='delVideo btn btn-danger btn-small' href='#'  title='删除角色' style='margin-left:5px'>删除</a>";
			}
			if (base.perList.video.transcode) {
				if(row.transcodeStatus==0){
					_btnAction += "<a data-toggle='modal' class='transcodeVideo btn btn-success btn-small' href='#' title='点击转码' style='margin-left:5px'>转码</a>";
				}else if(row.transcodeStatus==1){
					_btnAction += "<a data-toggle='modal' class='transcodeVideo btn btn-success btn-small' href='#' disabled='disabled' title='转码中..' style='margin-left:5px'>转码中</a>";
				}
			}
			return _btnAction;
		}
	};
	
	
		
	jQuery(document).ready(function() { 
		
		
		
		
		
		
		
		
		// 上传表单验证和提交
		// 腾讯云上传
		$(function() {
			$("#qupload").ajaxForm({
				// 定义返回JSON数据，还包括xml和script格式
				url:"/cms/vodCloud/upload",
				type:'post',
				beforeSend : function() {
				$("#msg").html('');
				$("#modal-Video").append("<div id='waitForUpload'background-color:azure style='opacity: 0.6;position: absolute;" +
				"position: absolute;z-index=4; width: 100%;height: 100%;margin-top: -111%'>" +
				"<div style='margin-left: 40%;margin-top: 37%;'><h3><b>uploading...</b></h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
				},
				success : function(data) {
					// 提交成功后调用
					$("#waitForUpload").remove();
					if (data.result==1) {
						$("#videoKey").val(data.value.fileId);
						$("#videoFileName").val(data.value.fileName.substring(0,data.value.fileName.length-4));
						$("#videoUrl").val(data.value.url);
						alert("上传成功了");
					} else {
						$("#msg").html(data.msg);
					}
				}
			});
		});
		
		
		//编辑上传视频
		$(function() {
			$("#editQupload").ajaxForm({
				// 定义返回JSON数据，还包括xml和script格式
				url:"/cms/vodCloud/upload",
				type:'post',
				beforeSend : function() {
				$("#editMsg").html('');
				$("#modal-editVideo").append("<div id='editWaitForUpload'background-color:azure style='opacity: 0.6;position: absolute;" +
				"position: absolute;z-index=4; width: 100%;height: 100%;margin-top: -111%'>" +
				"<div style='margin-left: 40%;margin-top: 37%;'><h3><b>uploading...</b></h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
				},
				success : function(data) {
					// 提交成功后调用
					$("#editWaitForUpload").remove();
					if (data.result==1) {
						$("#editVideoKey").val(data.value.fileId);
						alert("上传成功了");
					} else {
						$("#edit-msg").html(data.msg);
					}
				}
			
				
			});
		});
		
		//编辑上传试看视频
		$(function() {
			$("#editQuploadSub").ajaxForm({
				// 定义返回JSON数据，还包括xml和script格式
				url:"/cms/vodCloud/upload",
				type:'post',
				beforeSend : function() {
				$("#editSubMsg").html('');
				$("#modal-editVideo").append("<div id='editWaitForUploadSub'background-color:azure style='opacity: 0.6;position: absolute;" +
				"position: absolute;z-index=4; width: 100%;height: 100%;margin-top: -111%'>" +
				"<div style='margin-left: 40%;margin-top: 37%;'><h3><b>uploading...</b></h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
				},
				success : function(data) {
					// 提交成功后调用
					$("#editWaitForUploadSub").remove();
					if (data.result==1) {
						$("#editSubVideoKey").val(data.value.fileId);
						alert("上传成功了");
					} else {
						$("#edit-msg").html(data.msg);
					}
				}
			
				
			});
		});
		

		
		$(function() {
			$("#quploadSub").ajaxForm({
				// 定义返回JSON数据，还包括xml和script格式
				url:"/cms/vodCloud/upload",
				type:'post',
				beforeSend : function() {
				$("#msg").html('');
				$("#modal-Video").append("<div id='waitForUploadSub'background-color:azure style='opacity: 0.6;position: absolute;" +
				"position: absolute;z-index=4; width: 100%;height: 100%;margin-top: -111%'>" +
				"<div style='margin-left: 40%;margin-top: 37%;'><h3><b>uploading...</b></h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
				},
				success : function(data) {
					// 提交成功后调用
					$("#waitForUploadSub").remove();
					if (data.result==1) {
						$("#subVideoKey").val(data.value.fileId);
						alert("上传成功了");
						
					} else {
						$("#msg").html(data.msg);
					}
				}
			});
		});
		
// 编辑上传
		
		
		$(function() {
			$("#editUploadVideoImg").ajaxForm({
				// 图片上传的文件夹
				url:"/cms/upload/imageUpload",
				type:"post",
				data :{key:"textBook/",fileName:"imgFile"},
				success : function(data) {
					// 提交成功后调用
					if (data.value!=null) {
						if(data.value.status == 0){
							var file = $("#editVideoImg").val();
							var fileName = "http://ekt.oss-cn-shenzhen.aliyuncs.com/textBook/" + getFileName(file);
							var imgUrl = $("#editVideoImgUrl").val(fileName);
							alert("上传成功了");
						}
					} else {
						alert("上传异常 ")
					}
				}
			});
		});
		
		
		

		
		
		
// 新增上传
		$(function() {
			$("#uploadVideoImg").ajaxForm({
				// 图片上传的文件夹
				url:"/cms/upload/imageUpload",
				type:"post",
				data :{key:"textBook/",fileName:"imgFile"},
				success : function(data) {
					// 提交成功后调用
					if (data.value!=null) {
						if(data.value.status == 0){
							var file = $("#videoImg").val();
							var fileName = "http://ekt.oss-cn-shenzhen.aliyuncs.com/textBook/" + getFileName(file);
							var imgUrl = $("#videoImgUrl").val(fileName);
							alert("上传成功了");
						}
					} else {
						alert("上传异常 ")
					}
				}
			});
		});
		
		// 获取带后缀名的文件名
		function getFileName(o){
		    var pos=o.lastIndexOf("\\");
		    return o.substring(pos+1);  
		}
		
		
		
	}); 
	
});