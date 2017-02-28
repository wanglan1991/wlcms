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
										+"<a href='#' id='queryUserByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
				
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
				field : 'sex',
				title : '性别'
			}, {
				field : 'telephone',
				title : '手机号码',
			}, {
				field : 'registerType',
				title : '注册方式',
			}, {
				field : 'registerDate',
				title : '注册日期',
				formatter:F.crateTimeFormatter
			},{
				field : 'credits',
				title : '积分'
			}];

			// 操作
			operateEvents = {
				// 停用或启用用户
				'click .confineEktUser' : function(e, value, row, index) {
					base.bootConfirm(row.status==1?"请确定是否封停该用户，此操作将会导致该用户无法登陆？":
						"请确定是否解除该用户的封停状态？", function() {
						F.confine(row.id);
					});
					
				},// 编辑用户root权限
				
				'click .editEktUserPermission' : function(e, value, row, index) {	
					core.openModel('modal-ektUserRootEdit', '正在编辑二课堂用户账号:['+row.username+']的权限', function() {
						core.commonTree(F.basepath+'/user/treePermission',{userId:row.id},"#ektUserPermission");
						$("#getUserId").val(row.id);
					});	
				}
			}

			if (base.perList.quintessence.del) {
				cols.push({
					align : 'center',
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
			
			//监听回车按下查询事件
			document.getElementById("apiUserActions").onkeydown =keyDownSearch;
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
		
			
			
			
			
			//查询
			function query() {
				var username = $("#keyword").val();
				var status =$("#apiUserStatus").val();
					var query_url = F.basepath+ '/user/listPage?username='+username;
					if(status>-1&&status<2){
						query_url+="&status="+status;
					}
					$('#apiUserTable').bootstrapTable('refresh', {url : query_url});
				
			}
			
			//保存用户权限
			$("#ektUserRootBtnSubmit").click(function(){
				var arrIds = core.getTreeIds("ektUserPermission");
				var userId =$("#getUserId").val();
				$("#ektUserRootBtnClose").click();
				$.ajax({
					url : F.basepath + '/user/permission/edit',
					type : 'post',
					data : {
						userId: userId,
						ids:arrIds
					},
					success : function(data) {}
				})
				
			});
			
			
			

			//停用用户
		},confine : function(id) {
			$.ajax({
				url : F.basepath + '/user/confine',
				type : 'post',
				data : {
					id: id
				},
				success : function(data) {
					if (data.result > 0) {
						F.table.reload();
					}else{
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
		operateFormatter : function(value, row, index) {
			var _btnAction = "";

			if (base.perList.ektUser.confine) {
				_btnAction += "<a class='confineEktUser btn "
						+ (row.status == 1 ? "btn-success btn-small"
								: "btn btn-danger btn-small")
						+ "btn-primary btn-small' href='#' title='正常或封停' style='margin-left:5px'>"
						+ (row.status == 1 ? "正常" : "封停") + "</a>";
		
			}
			if (base.perList.ektUser.permission) {
				_btnAction += "<a class='editEktUserPermission btn btn-success btn-small' href='#' title='编辑用户权限' style='margin-left:5px'>编辑权限</a>";
			return _btnAction;
			}
		},

	};

});