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
		table : new core.Table('SchoolTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			 if(base.perList.school.check){
			$("#school-header .actions")
					.append(
							"<input autocomplete='off'  id='q_k_school' name='q_k_school' placeholder='学校名称' type='text' />&nbsp;&nbsp;<input autocomplete='off'  id='q_k_title' name='q_k_title' placeholder='省/市/县/区' type='text' /><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");

			 }
			
			/**
			 * 加载树
			 */

			operateEvents = {
				
				/**
				 * 启用或停用知识点
				 */
				'click .startSchool ' : function(e, value, row, index) {
					$.ajax({
						url : F.basepath + '/school/confine',
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
				field : 'schoolName',
				title : '学校名称'
			}, {
				field : 'cityCode',
				title : '地域id',
				visible : false
			},{
				field : 'cityName',
				title : '地域'
			}, {
				field : 'address',
				title : '地址',
				visible : false
			} ,{
				field : 'contact',
				title : '联系人',
				visible : false
			} ,{
				field : 'status',
				title : '状态',
				visible : false
			} ];
			// 是否需要操作列
			 if(
			 base.perList.school.confine)
			cols.push({
				align : 'center',
				title : '操作',
				events : operateEvents,
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/school/listPage', cols);

		

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(
					function() {
						var schoolName = $("#q_k_school").val();
						var cityName = $("#q_k_title").val();
						var query_url = F.basepath + '/school/listPage?schoolName='
								+ schoolName + '&cityName=' + cityName;
						$('#SchoolTable').bootstrapTable('refresh', {
							url : query_url
						});
					})

	


			
		
		},

		operateFormatter : function(value, row, index) {
			var _btnAction = "";
			 if (base.perList.school.confine) {
			_btnAction += "<a class='startSchool btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
					+ (row.status == 1 ? "停用" : "启用") + "</a>";
			 }
			return _btnAction;
		},
	
		
	}
	
});