var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('quintessenceTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			if (base.perList.quintessence.check) {
				$("#quintessence-header .actions")
						.append(
								"<input autocomplete='off'  id='keyword'  placeholder='组卷名称或知识点名称' type='text' />&nbsp;&nbsp;"
										+ "<select id='quintessenceType' style='width:6%'>"
										+ "<option value='0'>类型...</option>"
										+ "<option value='2'>精选组卷</option>"
										+ "<option value='3'>题库组卷</option></select>"
										+ "<select id='subject' style='width:6%'></select>"
										+ "<select id='grade' style='width:6%'></select>"
										+ "<select id='difficulty' style='width:6%'></select>"
										+ "<select id='teachers' style='width:6%'></select>"
										+ "<select id='examineStatus' style='width:6%'>" +
												"<option value='2'>状态...</option>" +
												"<option value='0'>隐藏...</option>" +
												"<option value='1'>显示...</option></select>"
										+ "<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
				teacherList("#teachers");
				core.getDictOptions("科目", "subject", "#subject");
				core.getDictOptions("年级", "grade", "#grade");
				core.getDictOptions("难易度", "difficulty", "#difficulty");
			}

			if (base.perList.quintessence.del) {
				$("#quintessence-header .actions")
						.append("&nbsp;&nbsp;<a href='#' id='delSeletcQuintessence'  class='btn btn-danger btn-small' "
										+ "style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>批量删除</a>");
			}
			
			/**
			 * teacher列表
			 */
			function teacherList(idOrClass) {
				var html = "<option value='0'>teacher...</option>";
				$.ajax({
					url : F.basepath + '/quintessence/teacherList',
					type : 'post',
					success : function(data) {
						for (var i = 0; i < data.value.length; i++) {
							html += "<option value='" + data.value[i].userId
									+ "'>" + data.value[i].nickname + "【"
									+ data.value[i].username + "】</option>"

						}
						$(idOrClass).append(html);
					}
				})
			}

			/**
			 * 打开模态框
			 */
			$('#addQuintessence').click(function() {
				core.openModel('addModal-quintessence', '添加', function() {
				});
			});

			// 定义表格的头
			var cols = [ {

				checkbox : true
			}, {
				field : 'id',
				title : '组卷ID'
			}, {
				field : 'testpaperName',
				title : '组卷名称'
			}, {
				field : 'type',
				title : '组卷类型'
			}, {
				field : 'grade',
				title : '年级',
			}, {
				field : 'subject',
				title : '科目'
			}, {
				field : 'difficulty',
				title : '难度'
			}, {
				field : 'exerciseCount',
				title : '习题数量'
			}, {
				field : 'createTime',
				title : '创建时间',
				formatter : F.crateTimeFormatter
			}, {
				field : 'realName',
				title : '更新人'
			} ];

			// 操作
			operateEvents = {
				// 删除指定精选
				'click .delQuintessence' : function(e, value, row, index) {
					base.bootConfirm("是否确定删除？", function() {
						var ids = new Array();
						ids.push(row.id);
						F.delTeachers(row.id);
					});
				},// 显示或隐藏组卷
				'click .confineQuintessence' : function(e, value, row, index) {					
						var ids = new Array();
						ids.push(row.id);
						F.confine(ids);
					
				},//编辑精选组卷
				'click .editQuintessence' : function(e, value, row, index){
					core.openModel('modal-editTestpaper',row.testpaperName,function(){
						$("#modal-editTestpaper").attr("t_id",row.id);
						$("#testpaperTitle").val(row.testpaperName);
						$("#testpaperDigest").val(row.digest);
						core.getEditDictOptions("难易度","difficulty","#testpaperDifficulty",row.difficultyNo);
		        	});
				}
			}
				cols.push({
					align : 'left',
					title : '操作',
					events : operateEvents,
					formatter : F.operateFormatter
				});
			

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/quintessence/listPage', cols);

			/**
			 * 批量删除精选
			 */
			$('#delSeletcQuintessence').click(
					function() {
						var ids = F.table.getIdSelections();
						console.log(ids);
						if (ids != null && ids.length > 0) {
							base.bootConfirm("是否确定删除选定的" + ids.length + "个？",
									function() {
										F.delTeachers(ids);
										F.reload();
									});
						} else {
							base.bootAlert({
								"ok" : false,
								"msg" : "请选择你要删除选项！"
							});
						}
					});
			
			
			/**
			 * 编辑保存每日精选组卷
			 */
			$("#quintessenceBtnSubmit").click(function(){
				var title = $("#testpaperTitle").val();//标题
				var digest = $("#testpaperDigest").val();//简介
				var difficulty = $("#testpaperDifficulty").val();//难易度value
				var testpaperId = $("#modal-editTestpaper").attr("t_id");//组卷id
			
				if(core.trim(title).length==0){$("#quintessenceMsg").html("试卷标题不能为空！");return;}else{$("#quintessenceMsg").html("")}
				if(core.trim(digest).length==0){$("#quintessenceMsg").html("试卷简介不能为空！");return;}else{$("#quintessenceMsg").html("")}
				if(difficulty==0){$("#quintessenceMsg").html("试卷难易度必须！");return;}else{$("#quintessenceMsg").html("")}
				
				$.ajax({
					url : F.basepath + '/quintessence/edit',
					type : 'post',
					data : {
						id:testpaperId,
						testpaperName:title,
						digest:digest,
						difficultyNo:difficulty
					},
					success : function(data) {
						if (data.result > 0) {
							$("#quintessenceMsg").next().click();//关闭模态框
							F.table.reload();
						}else{
							alert("修改失败！");
						}
					}
				})
				
				
			})
			
			
			
			
			
			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(function() {
				query();
			})

			//监听回车按下查询事件
			document.getElementById("quintessenceActions").onkeydown =keyDownSearch;
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
				var examineStatus=$("#examineStatus").val();
				var testpaperName = $("#keyword").val();
				var typeNo = $("#quintessenceType").val();
				var teachers = $("#teachers").val();
				var subject = $("#subject").val();
				var grade = $("#grade").val();
				var difficulty = $("#difficulty").val();
				var query_url = F.basepath
						+ '/quintessence/listPage?testpaperName='
						+ testpaperName + '&typeNo=' + typeNo + "&userId="
						+ teachers + "&subjectNo=" + subject + "&gradeNo="
						+ grade + "&difficultyNo=" + difficulty+"&examineStatus="+examineStatus;
				$('#quintessenceTable').bootstrapTable('refresh', {
					url : query_url
				});
			}

		},
		// 根据id删除组卷
		delTeachers : function(ids) {
			$.ajax({
				url : F.basepath + '/quintessence/del',
				type : 'post',
				data : {
					ids : ids.toString()
				},
				success : function(data) {
					if (data.result > 0) {
						F.table.reload();
					}
				}
			})
		},
		confine : function(ids) {
			$.ajax({
				url : F.basepath + '/quintessence/confine',
				type : 'post',
				data : {
					ids : ids.toString()
				},
				success : function(data) {
					if (data.result > 0) {
						F.table.reload();
					}
				}
			})

		},
		reload : function() {
			F.table.reload();
		},
		crateTimeFormatter : function(value, row, index) {
			return new Date(row.createTime).format("yyyy年MM月dd日 hh点mm分");
		},
		operateFormatter : function(value, row, index) {
			var _btnAction = "";

			if (base.perList.quintessence.del) {
				_btnAction += "<a class='confineQuintessence btn "
						+ (row.examineStatus == 1 ? "btn-success btn-small"
								: "btn btn-danger btn-small")
						+ "btn-primary btn-small' href='#' title='显示或者隐藏' style='margin-left:5px'>"
						+ (row.examineStatus == 1 ? "显示" : "隐藏") + "</a>"
			}
			
			if (base.perList.quintessence.del) {
				_btnAction += "<a class='delQuintessence btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>"
			}
			
			if (base.perList.quintessence.edit&&row.typeNo==2&&row.examineStatus == 1) {
				_btnAction += "<a class='editQuintessence btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>"
			}
			
			return _btnAction;
		},

	};

});