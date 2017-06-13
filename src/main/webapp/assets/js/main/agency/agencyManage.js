var path = "";
define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	// 查询参数
	var value = "";
	var type = "";
	// 通过 require 引入依赖
	var F = module.exports = {
		table : new core.Table('agencyTable'),
		init : function(_basepath) {
			F.basepath = _basepath;
			/**
			 * 是否具有查询知识点权限
			 */
			if (base.perList.news.check) {
				$("#agencyActions")
						.append(
								"<input autocomplete='off'  id='keyword'  placeholder='标题或内容' type='text' />&nbsp;&nbsp;"
										+"<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>"
										+"<a href='#' id='addNews' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
				
			}
			/*
			 * 
			 * 批量删除动态
			 */
			if (base.perList.news.del) {
				$("#agencyActions").append("&nbsp;&nbsp;<a href='#' id='delSelectNews'  class='btn btn-danger btn-small' "
										+ "style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>批量删除</a>");
			}
			
			//初始化富文本框
			var editor = core.setUeditor("container",'550','%100');
			
			// 定义表格的头
			var cols = [ {

				checkbox : true
			}, {
				field : 'id',
				title : '主键'
			}, {
				field : 'title',
				title : '代理商名称'
			}, {
				field : 'titleImageUrl',
				title : '手机号码'
			},{
				field : 'titleImageUrl',
				title : '上线'
			},{
				field : 'titleImageUrl',
				title : '下线数量'
			}, {
				field : 'createTime',
				title : '￥个人销售总额',
			},{
				field : 'createTime',
				title : '￥下线销售总额',
			},{
				field : 'createTime',
				title : '注册代理商时间',
				formatter:F.crateTimeFormatter
			}];

			// 操作
			operateEvents = {
				// 停用或启用用户
				'click .confineEktUser' : function(e, value, row, index) {
					base.bootConfirm(row.status==1?"请确定是否停用该资讯？确定后该资讯将不会在www.aiekt.com上展示。":
						"请确定是否启用该资讯？确定后该资讯将会在www.aiekt.com上展示。", function() {
						F.confine(row.id);
					});
					
				},// 编辑动态
				
				'click .editNews' : function(e, value, row, index) {	
					$("#newsTableHead").hide();
					$("#addNewsContainer").show();
					$("#title").val(row.title);
					$("#imgUrl").val(row.titleImageUrl);
					$("#imgUrl").attr("newsId",row.id);
					editor.setContent(row.content);
				}
			}

			if (base.perList.news.confine||base.perList.news.edit) {
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
			F.table.init(F.basepath + '/agency/listPage', cols);

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(function() {
				query();
			})
			
			//监听回车按下查询事件
			document.getElementById("distributorActions").onkeydown =keyDownSearch;
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
				var title = $("#keyword").val();
					var query_url = F.basepath+ '/news/listPage?title='+title;
				
					$('#distributorTable').bootstrapTable('refresh', {url : query_url});
				
			}
			
			//添加动态
			$("#addNews").click(function(){
				$("#newsTableHead").hide();
				$("#addNewsContainer").show();
			});
			
			//关闭添加页面
			$(".shutdown").click(function(){
				$("#newsTableHead").show();
				$("#addNewsContainer").hide();
				$("#title").val('');
				$("#imgFile").val('');
				$("#imgUrl").val('');
				editor.setContent('');
			});
			
			
			//保存动态
			$("#saveNews").click(function(){
				var newsId =$("#imgUrl").attr("newsId");
				var title =$("#title").val();
				var titleImage=$("#imgUrl").val();
				var content= editor.getContent();
				var postUrl ="";
				if(newsId==undefined){
					postUrl = F.basepath + '/news/addNews';
				}else{
					postUrl =F.basepath + '/news/editNews'
				}
		
				if(title.length<1){alert("标题不能为空！");return ;}
				if(titleImage.length<1){alert("标题图片不能为空！");return ;}
				if(content.length<1){alert("内容不能为空！");return ;}
				
				$.ajax({
					url : postUrl,
					type : 'post',
					data : {
						id:newsId,
						title:title,
						titleImageUrl:titleImage,
						content:content
					},
					success : function(data) {
						if (data.result > 0) {
							$(".shutdown").click();
							F.table.reload();
						}else{
							alert("服务器异常！请重新登录后再尝试该操作。");
						}
					}
				})
				
				
			});
			
			/**
			 * 批量删除精选
			 */
			$('#delSelectNews').click(
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
			
			

			//停用用户
		},confine : function(id) {
			$.ajax({
				url : F.basepath + '/news/confine',
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
		// 根据id删除动态
		delTeachers : function(ids) {
			$.ajax({
				url : F.basepath + '/news/del',
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
		crateTimeFormatter : function(value, row, index) {
			return new Date(row.createTime).format("yyyy年MM月dd日 hh点mm分");
		},
		operateFormatter : function(value, row, index) {
			var _btnAction = "";

				
			if (base.perList.news.confine) {
				_btnAction += "<a class='confineEktUser btn "
						+ (row.status == 1 ? 
								 "btn btn-danger btn-small":"btn-success btn-small")
						+ "btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"
						+ (row.status == 1 ? "停用" : "启用") + "</a>";
		
			}
			
			if (base.perList.news.edit) {
				_btnAction += "<a class='editNews btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>";
			return _btnAction;
			}
		},

	};

});



jQuery(document).ready(function() {
	$(function() {
		$("#upload").ajaxForm({
			//图片上传的文件夹
			url:"/cms/upload/imageUpload",
			type:"post",
			data :{key:"images/",fileName:"imgFile"},
			beforeSend : function() {
			
			$("#submitbutton").attr("disabled","disalbed");
			},
			success : function(data) {
				$("#submitbutton").attr("disabled",false);
				//提交成功后调用
				if (data.value!=null) {
					if(data.value.status == 0){
						var file = $("#imgFile").val();
						var fileName = "http://ekt.oss-cn-shenzhen.aliyuncs.com/images/" + getFileName(file);
						var imgUrl = $("#imgUrl").val(fileName);
						alert("上传成功");
					}
				} else {
					alert("上传异常 ")
				}
			}
		});
	});
	
	
	//获取带后缀名的文件名
	function getFileName(o){
	    var pos=o.lastIndexOf("\\");
	    return o.substring(pos+1);  
	}	
	
	
});