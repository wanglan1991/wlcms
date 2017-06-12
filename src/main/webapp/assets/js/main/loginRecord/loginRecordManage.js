var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('loginRecordTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
				$("#loginRecordActions")
						.append("<input autocomplete='off'  id='keyword'  placeholder='用户名、真实姓名、手机号码' type='text' />&nbsp;&nbsp;");
			
			// 定义表格的头
			var cols = [{
				field : 'accountName',
				title : '用户名'
			}, {
				field : 'realName',
				title : '真实姓名'
			}, {
				field : 'roleName',
				title : '角色',
			}, {
				field : 'cellphone',
				title : '手机号码'
			},{
				field : 'loginTime',
				title : '登录时间',
				formatter : F.crateTimeFormatter
			}, ];

			// 操作
			operateEvents = {
//				// 删除指定精选
//				'click .delQuintessence' : function(e, value, row, index) {
//					base.bootConfirm("是否确定删除？", function() {});
//				},// 显示或隐藏组卷
				
			}

//			if (base.perList.quintessence.del) {
//				cols.push({
//					align : 'center',
//					title : '操作',
//					events : operateEvents,
//					formatter : F.operateFormatter
//				});
//			}

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/loginRecord/listPage', cols);

//			//监听回车按下查询事件
			document.getElementById("loginRecordActions").onkeydown =keyDownSearch;
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
		
		
			
			function query() {		
				var keyword=$("#keyword").val();
				var query_url = F.basepath
				$('#loginRecordTable').bootstrapTable('refresh', {
					url : query_url+"/loginRecord/listPage?accountName="+keyword
				});
			}

		},
	
	
		reload : function() {
			F.table.reload();
		},
		crateTimeFormatter : function(value, row, index) {
			return new Date(row.loginTime).format("yyyy年MM月dd日 hh点mm分");
			
			
		},
		operateFormatter : function(value, row, index) {},

	};

});