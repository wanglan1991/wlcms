var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	// 通过 require 引入依赖
	
	var F = module.exports = {
		table : new core.Table('apiUserTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			
			if (base.perList.ektUser.check) {
				$("#apiUserActions").append("<input autocomplete='off'  id='keyword'  placeholder='用户名、昵称、手机号码' type='text' />&nbsp;&nbsp;"
					+ "&nbsp;&nbsp;<select id='apiUserStatus'  style='width:7%'>"
					+ "<option value='-1'>用户状态...</option>"
					+ "<option value='0'>正常</option>"
					+ "<option value='1'>封停</option></select>"
					+ "&nbsp;&nbsp;活动:<input style='margin-bottom: 5px;' type='checkbox' id='activity'>&nbsp;&nbsp;"
					+"<a href='#' id='queryUserByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
			}
			
			if(base.perList.ektUser.generateUser){
				$("#apiUserActions").append("&nbsp;&nbsp;<a href='#' style='margin-left:5px;margin-bottom:11px' class='generateEktUser btn btn-success btn-small' ><i class='icon-plus'></i>添加用户</a>");
			}
			
			if(base.perList.ektUser.batchGenerateUser){
				$("#apiUserActions").append("<a href='#' id='impApiUser' data-toggle='modal' class='btn btn-primary btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>批量生成用户</a>");
				
			}
			
			//批量导入 生成用户
			$("#impApiUser").bind('click',function(){
				core.openModel('modal-impApiUser',function(){
					$("#fileData").val('');
					$("#impResult textarea").val('');
				});
			});
			
			
			
			
			//添加二课堂账号模态框
			$(".generateEktUser").bind('click',function(){
				core.openModel('modal-generateEktUser',function(){
					 $("#generateEktUser-msg").html('');
					 $("#telephone").val('');
				});
			});
			
			//提交新增
			$("#generateEktUser-btnSubmit").click(function(){
				 $("#generateEktUser-msg").html('');
				var phone =$("#telephone").val();
				if(phone && /^1[3|4|5|7|8]\d{9}$/.test(phone)){
					$.ajax({
						 url : F.basepath + '/user/generateUser',
						type : 'POST',
						data:{telephone:phone},
						success:function(data){
						   if(data.result==1){
							   $("#generateEktUser-msg").html(phone+"的用户已存在，无法被添加！");
						   }else if(data.result==2){
							   F.table.reload();
							   $(".btnClose").click();
						   }else{
							   $("#generateEktUser-msg").html("创建用户失败！稍后再试");
						   }
						}
					});
				  
				
					
				}else{
					$("#telephone").val('');
					 $("#generateEktUser-msg").html('请输入一个有效格式的手机号码');
				}
				
			});
			
			
			
			$("#uploadFile").click(function(){
	    		$("#excelUpload").ajaxForm({
	    			url:"/cms/user/batchGenerate",
	    			type:"post",
	    			data:$('#excelUpload').serialize(),// 你的formid
	    			beforeSend : function() {
	    				$("#modal-impApiUser").append("<div id='waitForUpload'background-color:azure style='opacity: 0.6;position: absolute;" +
								"position: absolute;z-index=4; width: 100%;height: 100%;margin-top:-113%'>" +
								"<div style='margin-left: 42%;margin-top: 47%;'><h3><b>uploading...</b>" +
								"</h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
	    			},
	    			success : function(data){
	    				F.reload();
	    				$("#waitForUpload").remove();
	    				$("#impResult textarea").val('');
	    				$("#uploadMsg").html('');
	    				if(data.result==1){
	    					$("#impResult textarea").val(data.msg);
	    				}else{
	    					$("#uploadMsg").html('后端服务器异常！');
	    				}
	    				
	 	
	    			}   		
	    	});
	    		
	    	});

			// 定义表格的头
			var cols = [ {

				checkbox : true
			}, {
				field : 'id',
				title : '用户id'
			}, {
				field : 'username',
				title : '用户名'
			}, {
				field : 'nickname',
				title : '昵称'
			}, {
				field : 'telephone',
				title : '手机号码',
			}, {
				field : 'registerType',
				title : '注册方式',
			}, {
				field : 'registerDate',
				title : '注册日期',
				formatter : F.crateTimeFormatter
			}, {
				field : 'credits',
				title : '积分'
			}, {
				field : 'roleName',
				title : '角色'
			} ];

			internalUserEvents={
					'click .bt-on-off' : function(e, value, row, index) {
						if((this.className).search('clicked')<0){
							$(this).addClass('clicked');
							
						}else{
							$(this).removeClass('clicked');
						}
					var checked =row.isReal==1?0:1;
					$.ajax({
						url : F.basepath + '/user/isReal',
						type : 'POST',
						data:{
							ektUserId:row.id,
							isReal:checked
						},
						success : function(data) {
							if(data.result==0){
								alert("修改失败请稍后再试！")
								$(this).removeClass('clicked');
							}else{
								F.table.reload();
							}
						}
					});

				},
			}
			
			// 操作
			operateEvents = {
				
				'click .editUserInfo' :function(e, value, row, index){
					core.openModel('modal-editUserInfo',"正在编辑用户账号:["+row.username+"]的信息",function(e, value, row, index){
						
					});
					
					
					
				},	
				// 停用或启用用户
				'click .confineEktUser' : function(e, value, row, index) {
					base.bootConfirm(
							row.status == 1 ? "请确定是否封停该用户，此操作将会导致该用户无法登陆？"
									: "请确定是否解除该用户的封停状态？", function() {
								F.confine(row.id);
							});

				},// 编辑用户root权限

				'click .editEktUserPermission' : function(e, value, row, index) {
					core.openModel('modal-ektUserRootEdit', '正在编辑二课堂用户账号:['
							+ row.username + ']的权限', function() {
						core.diyTree(F.basepath + '/user/treePermission', {
							userId : row.id
						}, "#ektUserPermission");
						$("#getUserId").val(row.id);
						$("#couponId").val(row.couponId);
					});
				},
				'click .giftCourse' : function(e, value, row, index) {
					core.openModel('modal-giftCourse', '赠送给用户:[账号:'
							+ row.username + ']', function() {
						core.diyTree(F.basepath + '/user/giftCourse/list', {
							userId : row.id
						}, "#giftCourseTree");
						$("#giftCourse_getUserId").val(row.id);
						$("#giftCourse_couponId").val(row.couponId);
					});
				},
				/**
				 * 生成CMS账户
				 */
				'click .generateCmsAccount' : function(e, value, row, index) {
					core.openModel('modal-generateCmsAccount', '请选择二课堂账号:'
							+ row.username + ' 的CMS角色', function() {
						$("#ektUserRole").empty();
						$("#ektUserRole").attr("ektUserId", row.id);
						var html = '';
						var total = 0;
						$.ajax({
							url : F.basepath + '/account/roleList',
							type : 'POST',
							success : function(data) {

								for (var i = 0; i < data.value.length; i++) {
									if (data.value[i].status == 0) {
										continue;
									}
									total += i;
									html += "<option value=" + data.value[i].id
											+ ">" + data.value[i].name
											+ "</option>"
								}

								$("#ektUserRole").append(html);
							}
						});
					});

				},
				//用户事项管理
				'click .api_user_business':function(e, value, row, index){
					$("#businessUserRealname").val('');
					$("#businessUsername").removeAttr("businessId");
					$("#businessUsername").removeAttr("uId");
					$("#businessUsername").val('');
					$("#businessType").val('');
					$("#businessUserTelephone").val('');
					$("#businessUserCommitTime").val('');
					$("#businessUserRemark").val('');
					$("#businessUserAcceptanceContent").val('');
					$.ajax({
						url : F.basepath + '/user/business',
						type : 'POST',
						data:{userId:row.id},
						success : function(data) {
							if(data.result!=1){
								F.table.reload();
							}else{
								core.openModel('modal_api_user_business', "用户  "+data.value.username);
								$("#businessUserRealname").val(data.value.realname==null?"无":data.value.realname);
								$("#businessUsername").val(data.value.username);
								$("#businessType").val(data.value.businessType);
								$("#businessUsername").attr("businessId",data.value.businessId);
								$("#businessUsername").attr("uId",row.id);
								if(row.untreatedCount==0){
									$("#submitRecord").click();
								}
								$("#businessUserTelephone").val(data.value.telephone);
								$("#businessUserCommitTime").val(new Date(data.value.createTime).format("yyyy年MM月dd日 hh点mm分"));
								$("#businessUserRemark").val(data.value.remark);
								$("#businessUserAcceptanceContent").val(data.value.acceptanceContent);
								if(data.value.acceptanceStatus>0){
									$("#businessBtnSubmit").hide();
								}else{
									$("#businessBtnSubmit").show();
								}
							}
						}
					});
					
					
					
					
				}
			}
			/**
			 * 生成CMS账户
			 */
			$("#generateCmsAccountSave").click(function() {
				var userId = $("#ektUserRole").attr("ektUserId");
				var roleId = $("#ektUserRole").val();
				$.ajax({
					url : F.basepath + '/user/generate/cmsAccount',
					type : 'post',
					data : {
						userId : userId,
						roleId : roleId
					},
					success : function(data) {
						if (data.result > 0) {
							$("#generateCmsAccountBtnClose").click();
							F.table.reload();
						} else {
							alert("生成失败！");
						}

					}
				});

			})
				
			/**
			 * 用户事务提交
			 */
			$("#businessBtnSubmit").click(function(){
				var businessId = $("#businessUsername").attr("businessId");
				var acceptanceContent = core.Strim($("#businessUserAcceptanceContent").val());
				if(acceptanceContent.length<1){$("#businessMsg").html("处理内容不能为空！");return;}else{$("#businessMsg").html("")}
				var content =$("#businessUserAcceptanceContent").val();
				$.ajax({
					url : F.basepath + '/user/business/acceptance',
					type : 'post',
					data : {businessId : businessId,
							acceptanceContent : content},
					success : function(data) {
						$("#submitRecord").click();
						if(data.result>0){
							F.table.reload();
						}else{
							alert("系统异常 请稍后再试！");
						}
						
						
					}
				});
				
			});

			
			
			/**
			 * 获取用户的历史提交记录
			 */
			$("#submitRecord").click(function(){
				$("#businessRecordBody").empty();
				$("#businessRecord").show();
				$("#currentBody").hide();
				var userId = $("#businessUsername").attr("uId");
				var html = "";
				$.ajax({
					url : F.basepath + '/user/business/list',
					type : 'post',
					data : {userId : userId},
					success : function(data) {
						F.rows=data.value.length;
						for(var i=0;i<data.value.length;i++){
							html+="<tr class='readRow "+data.value[i].businessId+"' businessId='"+data.value[i].businessId+"'><td>"+(i+1)+"</td>" +
									"<td>"+(new Date(data.value[i].createTime).format("yy-MM-dd hh:mm"))+"</td>" +
									"<td>"+data.value[i].businessType+"</td>" +
									"<td>"+(data.value[i].acceptanceStatus>0?"已办理":"待办理")+"</td>" +
									"<td>"+(new Date(data.value[i].acceptanceTime).format("yy-MM-dd hh:mm"))+"</td>" +
									"<td>"+(data.value[i].accountRealname==null?"无":data.value[i].accountRealname)+"</td></tr>" 
						}
						$("#businessRecordBody").append(html)
						
						$(".readRow").click(function(){
							$("#currentBusiness").click();
							var businessId =  $(this).attr("businessId");
							$.ajax({
								url : F.basepath + '/user/business',
								type : 'POST',
								data:{businessId:businessId},
								success : function(data) {
										$("#businessUserRealname").val(data.value.realname==null?"无":data.value.realname);
										$("#businessUsername").val(data.value.username);
										$("#businessUsername").attr("businessId",data.value.businessId);
										$("#businessUsername").attr("uId",data.value.userId);
										$("#businessUserTelephone").val(data.value.telephone);
										$("#businessUserCommitTime").val(new Date(data.value.createTime).format("yyyy年MM月dd日 hh点mm分"));
										$("#businessUserRemark").val(data.value.remark);
										$("#businessUserAcceptanceContent").val(data.value.acceptanceContent);
										if(data.value.acceptanceStatus>0){
											$("#businessBtnSubmit").hide();
										}else{
											$("#businessBtnSubmit").show();
										}
									
								}
							});
							
							
							
							
						})
					}
				});
			});
			
			
			$("#currentBusiness").click(function(){
				$("#currentBody").show();
				$("#businessRecord").hide();
			});
			
			if (base.perList.ektUser.internalUser) {
				cols.push({
					align : 'left',
					title : '内部用户',
					events : internalUserEvents,
					formatter : F.internalUserFormatter
				});
				
			}
			
			if (base.perList.ektUser.generateAccount
					|| base.perList.ektUser.confine
					|| base.perList.ektUser.permission
					|| base.perList.ektUser.editUserInfo) {
				cols.push({
					align : 'left',
					title : '操作',
					events : operateEvents,
					formatter : F.operateFormatter
				});
				
			}

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/user/listPage', cols);

			/**
			 * 根据条件查询
			 */
			$('#queryUserByCondition').click(function() {
				query();
			})

			// 监听回车按下查询事件
			document.getElementById("apiUserActions").onkeydown = keyDownSearch;
			function keyDownSearch(e) {
				// 兼容FF和IE和Opera
				var theEvent = e || window.event;
				var code = theEvent.keyCode || theEvent.which
						|| theEvent.charCode;
				if (code == 13) {
					query();
				} else {
				}
			}

			// 查询
			function query() {
				var username = $("#keyword").val();
				var status = $("#apiUserStatus").val();
				var activity = $("#activity").attr('checked')=='checked'?1:0;//是否为合集
				var query_url = F.basepath + '/user/listPage?username='+ username+"&activity="+activity;
				if (status > -1 && status < 2) {
					query_url += "&status=" + status;
				}
				$('#apiUserTable').bootstrapTable('refresh', {
					url : query_url
				});

			}

			// 保存二课堂用户权限
			$("#ektUserRootBtnSubmit").click(function() {
				var result = core.getDiyTreeResult("ektUserPermission");
				var userId = $("#getUserId").val();
				var couponId=$("#couponId").val();
				
				$("#ektUserRootBtnClose").click();
				$.ajax({
					url : F.basepath + '/user/permission/edit',
					type : 'post',
					data : {
						userId : userId,
						ids : JSON.stringify(result),
						couponId:couponId==""?0:couponId
					},
					success : function(data) {
					}
				})

			});
			
			//配置赠送课程
			$("#giftCourseBtnSubmit").click(function() {
				var result = core.getDiyTreeResult("giftCourseTree");
				var userId = $("#giftCourse_getUserId").val();
				var couponId=$("#giftCourse_couponId").val();
				
				$("#giftCourseBtnClose").click();
				$.ajax({
					url : F.basepath + '/user/giftCourse/edit',
					type : 'post',
					data : {
						userId : userId,
						ids : JSON.stringify(result),
						couponId:couponId==""?0:couponId
					},
					success : function(data) {
					}
				})

			});

			// 停用用户
		},
		confine : function(id) {
			$.ajax({
				url : F.basepath + '/user/confine',
				type : 'post',
				data : {
					id : id
				},
				success : function(data) {
					if (data.result > 0) {
						F.table.reload();
					} else {
						alert("服务器异常！请重新登录后再尝试该操作。");
					}
				}
			})

		},
		reload : function() {
			F.table.reload();
		},
		crateTimeFormatter : function(value, row, index) {
			return new Date(row.registerDate).format("yyyy年MM月dd日 hh点mm分");
		},
		
		
		internalUserFormatter :function(value, row, index){
			var	_btnAction="" ;
			if(row.isReal==1){
				_btnAction+="<a class='bt-on-off clicked'>"
			}else{
				_btnAction+="<a class='bt-on-off'>"
			}
			_btnAction+="<div class='bt-cont'><span class='bt-on'>ON</span><span class='bt-circ'></span><span class='bt-off'>OFF</span></div></a>";
				return _btnAction
		},
		
		operateFormatter : function(value, row, index) {
			var _btnAction = "";
			if (base.perList.ektUser.transactional&&(row.untreatedCount > 0 || row.treatedCount > 0)) {
				_btnAction += "<a class='api_user_business btn "
						+ (row.untreatedCount > 0 ? "btn-primary"
								: "btn-success")
						+ " btn-small' href='#' title='事项' style='margin-left:5px'>"
						+ (row.untreatedCount > 0 ? "有" + row.untreatedCount
								+ "个待办理事务</a>" : "已办理" + row.treatedCount
								+ "个事务");
			}
			if (base.perList.ektUser.generateAccount && row.telephone != null
					&& row.accountId == null) {
				_btnAction += "<a class='generateCmsAccount btn btn-success btn-small' href='#' title='生成CMS账户' style='margin-left:5px'>生成CMS账户</a>";
			}

			if (base.perList.ektUser.confine) {
				_btnAction += "<a class='confineEktUser btn "
						+ (row.status == 1 ? "btn-success btn-small"
								: "btn btn-danger btn-small")
						+ "btn-primary btn-small' href='#' title='正常或封停' style='margin-left:5px'>"
						+ (row.status == 1 ? "正常" : "封停") + "</a>";

			}
			if (base.perList.ektUser.permission) {
				_btnAction += "<a class='editEktUserPermission btn btn-success btn-small' href='#' title='编辑用户权限' style='margin-left:5px'>编辑EKT权限</a>";
			}
			
			if (base.perList.ektUser.giftCourse) {
				_btnAction += "<a class='giftCourse btn btn-primary btn-small' href='#' title='赠送课程' style='margin-left:5px'>赠送课程</a>";
			}
			if (base.perList.ektUser.editUserInfo) {
				_btnAction += "<a class='editUserInfo btn btn-primary btn-small' href='#' title='编辑用户信息' style='margin-left:5px'>编辑用户信息</a>";
			}
			
			return _btnAction;
		},
		

	};
	
	
	
});