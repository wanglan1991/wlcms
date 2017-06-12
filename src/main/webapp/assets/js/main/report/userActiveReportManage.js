var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('userActiveReportTable'),
		init : function(_basepath) {
			F.basepath = _basepath;

			if (1==1) {
				$("#userActiveReportActions")
						.append("&nbsp;&nbsp;<a href='#' id='mothQuery'  class='btn  btn-small' "
										+ "style='margin-left:5px;margin-bottom:11px'>月统计</a>");
			}
			if (1==1) {
				$("#userActiveReportActions")
						.append("&nbsp;&nbsp;<a href='#' id='dayQuery'  class='btn  btn-small' "
										+ "style='margin-left:5px;margin-bottom:11px'>日统计</a>");
			}
			
			
			
			
			// 定义表格的头
			var cols = [{
				field : 'date',
				title : '日期',
				formatter : F.crateTimeFormatter
			}, {
				field : 'userCount',
				title : '总用户 /人'
			}, {
				field : 'increaseUserCount',
				title : '新增用户 /人'
			}, {
				field : 'loginUserCount',
				title : '登录用户 /人',
			}, {
				field : 'loginTimes',
				title : '登录总 /次'
			}, {
				field : 'webLoginTimes',
				title : 'web端登录 /次'
			}, {
				field : 'appLoginTimes',
				title : 'app端登录 /次'
			}, {
				field : 'payUserCount',
				title : '购买 /人',
			}, {
				field : 'payTimes',
				title : '购买 /次'
			} , {
				field : 'videoUseUserCount',
				title : '视频点播 /人'
			} , {
				field : 'videoUseTimes',
				title : '视频点播 /次'
			} , {
				field : 'exerciseUseUserCount',
				title : '习题使用 /人'
			} , {
				field : 'newsUseUserCount',
				title : '动态使用 /人'
			} , {
				field : 'messageUseUserCount',
				title : '消息中心使用 /人'
			} ];

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
			F.table.init(F.basepath + '/userActiveReport/listPage', cols);

//			//监听回车按下查询事件
//			document.getElementById("userActiveReportActions").onkeydown =keyDownSearch;
//			function keyDownSearch(e) {
//				// 兼容FF和IE和Opera
//				var theEvent = e || window.event;
//				var code = theEvent.keyCode || theEvent.which
//						|| theEvent.charCode;
//				if (code == 13) {
//					query();
//				}else{
//				}
//			}	
		$("#mothQuery").click(function(){
			query(2);//按月
		});
		$("#dayQuery").click(function(){
			query(1)//按日
		});
			
			function query(dayOrMonthOrYear) {			
				var query_url = F.basepath
				$('#userActiveReportTable').bootstrapTable('refresh', {
					url : query_url+"/userActiveReport/listPage?dayOrMonthOrYear="+dayOrMonthOrYear
				});
			}

		},
	
	
		reload : function() {
			F.table.reload();
		},
		crateTimeFormatter : function(value, row, index) {
			if(row.dayOrMonthOrYear==1){
				return new Date(row.date).format("yyyy年MM月dd日");
			}else if(row.dayOrMonthOrYear==2){
				return new Date(row.date).format("yyyy年MM月");
			}
			
		},
		operateFormatter : function(value, row, index) {},

	};

});