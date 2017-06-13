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
				$("#apiUserActions")
						.append(
								"<input autocomplete='off'  id='keyword'  placeholder='用户名、昵称、手机号码' type='text' />&nbsp;&nbsp;"
										+ "&nbsp;&nbsp;<select id='apiUserStatus'  style='width:7%'>"
										+ "<option value='-1'>用户状态...</option>"
										+ "<option value='1'>正常</option>"
										+ "<option value='0'>封停</option></select>"
										+ "<a href='#' id='queryUserByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");

			}

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
						core.commonTree(F.basepath + '/user/treePermission', {
							userId : row.id
						}, "#ektUserPermission");
						$("#getUserId").val(row.id);
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
					|| base.perList.ektUser.permission) {
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
				var query_url = F.basepath + '/user/listPage?username='
						+ username;
				if (status > -1 && status < 2) {
					query_url += "&status=" + status;
				}
				$('#apiUserTable').bootstrapTable('refresh', {
					url : query_url
				});

			}

			// 保存用户权限
			$("#ektUserRootBtnSubmit").click(function() {
				var arrIds = core.getTreeIds("ektUserPermission");
				var userId = $("#getUserId").val();
				$("#ektUserRootBtnClose").click();
				$.ajax({
					url : F.basepath + '/user/permission/edit',
					type : 'post',
					data : {
						userId : userId,
						ids : arrIds
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
			
			return _btnAction;
		},
		

	};
	
	
	
});