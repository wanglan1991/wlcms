var path = "";
define(function(require, exports, module) {
	var base = require('base');
		core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('teacherTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			 if(base.perList.school.check){
			$("#school-header .actions")
					.append("<input autocomplete='off'  id='teacherName'  placeholder='teacher昵称' type='text' />&nbsp;&nbsp;" +
							"<select id='subjectOption' style='width:6%'><select/>&nbsp;&nbsp;<select id='phaseOption' style='width:6%'><select/>" +
							"<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
				core.getDictOptions("科目","subject","#subjectOption");
				core.getDictOptions("学段","phase","#phaseOption");
			 }
			 
//			 if(base.perList.school.add){
			$("#school-header .actions")
				.append("&nbsp;&nbsp;<a href='#' id='addTeacher'  class='btn btn-success btn-small' " +
						"style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
//			 }
//			 if(base.perList.school.del){
			$("#school-header .actions")
			.append("&nbsp;&nbsp;<a href='#' id='delTeacher'  class='btn btn-success btn-small' " +
					"style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>批量删除</a>");
//			 }
			/**
			 * 打开模态框
			 */
			$('#addTeacher').click(function() {
				core.openModel('addModal-teacher', '添加名师', function() {
					core.getRegionList("#addProvince",0);
					core.getDictOptions("年级","grade","#addGrade");
					core.getDictOptions("科目","subject","#addSubject");
				});
			});
			
			//监听省份选择
			$("#addProvince").change(function(){
				$("#addCity").empty();
				$("#addCounty").empty();
				$("#addSchool").empty();
				var province =$("#addProvince").val();
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				$("#addCounty").append("<option value='-1'>..县/区..</option>");
				if(province==-1){
					$("#addCity").append("<option value='-1'>..市..</option>");
					return;
				}
				core.getRegionList("#addCity",province);
			});
			//监听市选择
			$("#addCity").change(function(){
				$("#addCounty").empty();
				$("#addSchool").empty();
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				var city =$("#addCity").val();
				if(city ==-1){
					$("#addCounty").append("<option value='-1'>..县/区..</option>");
					return;
				}
				core.getRegionList("#addCounty",city);
			});
			//监听区/县选择
			$("#addCounty").change(function(){
				$("#addSchool").empty();
				var county =$("#addCounty").val();
				if(county ==-1){
					$("#addCounty").append("<option value='-1'>..县/区..</option>");
					return;
				}
				core.getSchoolList("#addSchool",county);
			});
			//关系添加模态框
			$('#btnClose').click(function(){
				core.closeModel('addModal-teacher');
				clear()
			});
			
//			清理器
			function clear(){
				$("#addProvince").empty();
				$("#addCity").empty();
				$("#addCounty").empty();
				$("#addSchool").empty();
				$("#addGrade").empty();
				$("#addSubject").empty();
				$("#addCounty").append("<option value='-1'>..县/区..</option>");
				$("#addCity").append("<option value='-1'>..市..</option>");
				$("#addSchool").append("<option value='-1'>..学校..</option>");
				
			}
			
			
			
			

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
				field : 'name',
				title : '昵称'
			},{
				field : 'username',
				title : '用户名'
			},{
				field : 'schoolName',
				title : '所在学校'
			},{
				field : 'subject',
				title : '科目'
			}, {
				field : 'phase',
				title : '学段'
			} 	];
			// 是否需要操作列
//			 if(base.perList.school.confine)
			cols.push({
				align : 'center',
				title : '操作',
				formatter : F.operateFormatter
			});

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/teacher/listPage', cols);
			
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
			$('#queryByCondition').click(function() {
						var name = $("#teacherName").val();
						var subjectNo = $("#subjectOption").val();
						var phaseNo = $("#phaseOption").val();
						var query_url = F.basepath + '/teacher/listPage?name='
								+name+'&subjectNo='+subjectNo+'&phaseNo='+phaseNo;
						$('#teacherTable').bootstrapTable('refresh', {
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