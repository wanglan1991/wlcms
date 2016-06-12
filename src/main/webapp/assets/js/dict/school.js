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
					.append("<input autocomplete='off'  id='q_k_school' name='q_k_school' placeholder='学校名称' type='text' />&nbsp;&nbsp;<input autocomplete='off'  id='q_k_title' name='q_k_title' placeholder='省/市/县/区' type='text' /><a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
			 }
			 if(base.perList.school.add){
			$("#school-header .actions")
				.append(
						"<a href='#' id='addSchool' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			 }
			 if(base.perList.school.del){
			$("#school-header .actions")
			.append(
					"<a href='#' id='delSchool' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>批量删除</a>");
			 }
			/**
			 * 打开模态框
			 */
			$('#addSchool').click(function() {
				core.openModel('addModal-school', '添加学校', function() {
					getRegionList("#province",0,"省")
				});
			});
			
			$('#btnClose').click(function(){
				core.closeModel('addModal-school');
				clear()
			});
			
//			清理器
			function clear(){
				$("#province").empty();
				$("#city").empty();
				$("#county").empty();
				$("#city-div").hide();
				$("#county-div").hide();
				$("#schoolName").val('');
				$("#school-div").show();
				$("#btnSubmit").show();
				$("#city-error").text("");
				$("#county-error").text("");
				$("#schoolName-error").text("");
			}
			
			
			
			//加载地区
			function getRegionList(idOrClass,parentCode,region){
				
				var html="<option value='-1'>...请选择"+region+"...</option>";
				$.ajax({
					url : F.basepath + '/region/getRegionList',
					type : 'POST',
					data : {parentCode:parentCode},
					success: function(data){
						for(var i=0;i<data.value.length;i++){
							html+="<option value='"+data.value[i].area_code+"'>"+data.value[i].area_name+"</option>"
						}
						
						$(idOrClass).append(html);
					} 
				})
				
			}
			$("#province").change(function(){
				$("#city-div").show();
				$("#city").empty();
				var province =$("#province").val();
				getRegionList("#city",province,"市");
				$("#county-div").hide();
			})
			
			$("#city").change(function(){
				$("#county-div").show();
				$("#county").empty();
				var city =$("#city").val();
				getRegionList("#county",city,"区/县");
				$("#school-div").show();
				$("#btnSubmit").show();
				var city =$("#city").val();
				if(city==-1){
					$("#county-div").hide();
				}
				
				
			})


			//批量删除学校
			$('#delSchool').click(
					function() {
						var ids = F.table.getIdSelections();
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个学校？",
									function() {
								delSchool(ids);
									});
						} else {
							base.bootAlert({
								"ok" : false,
								"msg" : "请选择你要删除的学校！"
							});
						}
					});		

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
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/school/listPage', cols);
			
			/**
			 * 添加学校提交表单
			 */
			
			$("#btnSubmit").click(function(){
			var county = $("#county").val();
			var schoolNameArr=$("#schoolName").val();
			var county =$("#county").val();
			if(city<1){
				$("#city-error").text("请选择市！");
				$("#city-error").css("color","red");
				return 
			}
			if(county<1){
				$("#county-error").text("请选择区或者县！");
				$("#county-error").css("color","red");
				return 
			}
			if(schoolNameArr.length<1||schoolNameArr==""){
				$("#schoolName-error").text("学校名称不能为空");
				$("#schoolName-error").css("color","red");
			}
			$.ajax({
				url : F.basepath + '/school/add',
				type : 'POST',
				data : {cityCode:county,schoolNameArr:schoolNameArr },
				success : function(data) {
					if (data.result > 0) {
						var r= confirm("成功添加"+data.result+"个，"+(data.msg==null?"":data.msg)+" 是否继续添加")
						if(r){
							$("#schoolName").val("");
							F.table.reload();
						}else{
							core.closeModel('addModal-school');
							clear()
							F.table.reload();
						}
					} else {
						alert("该学校已存在！")
					}
				}
			});
			})
			
			/**
			 * 删除学校
			 */
			 function delSchool(ids) {
					$.ajax({
						url : F.basepath + '/school/delete',
						type : 'post',
						data : {
							ids : ids.toString()
						},
						success : function(data) {
							if (data.result > 0) {
								F.table.reload();
							} else if (data.result == 0) {
								base.bootAlert({
									"ok" : false,
									"msg" : "网络异常"
								});
							}
						}
					})
				}
					

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