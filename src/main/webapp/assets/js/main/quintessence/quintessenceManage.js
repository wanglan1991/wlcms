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
			 if(base.perList.quintessence.check){
			$("#quintessence-header .actions")
					.append("<input autocomplete='off'  id='keyword'  placeholder='内容昵称' type='text' />&nbsp;&nbsp;" +
							"<select id='quintessenceType' style='width:6%'>" +
							"<option value='0'>类型..</option>" +
							"<option value='1'>组卷</option>" +
							"<option value='2'>课程</option><select/>" +
							"<a href='#' id='queryByCondition' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>");
			 }
			 if(base.perList.quintessence.add){
			$("#quintessence-header .actions")
					.append("&nbsp;&nbsp;<a href='#' id='addQuintessence'  class='btn btn-success btn-small' " +
							"style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
			 }
			 
			 if(base.perList.quintessence.del){
			$("#quintessence-header .actions")
			.append("&nbsp;&nbsp;<a href='#' id='delSeletcQuintessence'  class='btn btn-danger btn-small' " +
					"style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>批量删除</a>");
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
				title : '主键'		
			}, {
				field : 'name',
				title : '名称'
			},{
				field : 'type',
				title : '类型'
			},{
				field : 'totalCount',
				title : '数量'
			},{
				field : 'createTime',
				title : '创建时间'
				,formatter : F.crateTimeFormatter
			}, {
				field : 'publishDate',
				title : '到期时间'
				,formatter: F.publishDateFormatter
			} 	];
			
			//操作
			operateEvents={
					//删除指定精选
					'click .delQuintessence':function(e,value,row,index){	
			        	base.bootConfirm("是否确定删除？",function(){
			        		var ids = new Array();  
			        		ids.push(row.id);   
			        		F.delTeachers(row.id);	
			    		});
					}
			}
			
			
			 if(base.perList.quintessence.del){
				cols.push({
					align : 'center',
					title : '操作',
					events: operateEvents,
					formatter : F.operateFormatter
				});
			 }
		
			

			/**
			 * 用户列表
			 */
			F.table.init(F.basepath + '/quintessence/listPage', cols);
			
			
			
			/**
			 * 批量删除精选
			 */
			$('#delSeletcQuintessence').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个？",function(){
						F.delTeachers(ids);
						F.reload();
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除选项！"});
				}
			});
					

			/**
			 * 根据条件查询
			 */
			$('#queryByCondition').click(function() {
				query();
					})
				
					
			document.getElementById('quintessenceActions').onkeydown=keyDownSearch; 
    		function keyDownSearch(e) {  
    	        // 兼容FF和IE和Opera  
    	        var theEvent = e || window.event;  
    	        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
    	        if (code == 13) {   
    	        	query();	
    	        }  
    	    } 	
    		
    		function query(){
    			var name = $("#keyword").val();
				var contentType = $("#quintessenceType").val();
				var query_url = F.basepath + '/quintessence/listPage?name='
						+name+'&contentType='+contentType;
				$('#quintessenceTable').bootstrapTable('refresh', {
					url : query_url
				});
    		}	
					
					
			/**
			 * 添加精选
			 */
			$("#addQuintessence").click(function(){			
				core.openModel('addModal-Quintessence', '添加项目', function(){});
				
			})
			
			
			//监听类型选择加载内容选择
			$("#addQuintessenceType").change(function(){
				$("#quintessenceContent").empty();
				var contentType=$("#addQuintessenceType").val();
				if(contentType==-1){return}
				$.ajax({
					url:F.basepath+'/quintessence/contentList',
					type:"GET",
					data:{contentType:contentType},
					success:function(data){
						var html="";
						for(var i=0;i<data.value.length;i++){
							html+="<option value='"+data.value[i].id+"'>"+data.value[i].name+"</option>"
						}
						$("#quintessenceContent").append(html);
					}
				})
				$("#addContentSelect").show();
				$("#quintessenceContent").select2();
								
			});
			
			/**
			 * 提交新增每日精选
			 */
			$("#quintessenceBtnSubmit").click(function(){
				var contentType=$("#addQuintessenceType").val();
				var contentId =$("#quintessenceContent").val();
				var expireDate=$("#expireDate").val();
//				alert(expireDate);
				if(contentType<1){$("#quintessenceMsg").text("请选择类型!");return}
				if(contentId==null){$("#quintessenceMsg").text("请选择内容!");return}
				if(expireDate==''){$("#quintessenceMsg").text("请指定到期日期!");return}
				$.ajax({
					url:F.basepath+'/quintessence/add',
	        		type:'post',
	        		data:{contentType:contentType,contentId:contentId[0].toString(),type:expireDate},
	        		success:function(data){
	        			if(data.result==-1){
	        				$("#quintessenceMsg").text(data.msg);
	        			}else if(data.result>0){
	        				F.table.reload();
	        				clear();
	        			}	
	        		}
				})	
			});
			
			/**
			 * 关闭添加窗口
			 */
			$("#quintessenceBtnClose").click(function(){
				clear();
			})
			
			
		
			//清理器
			function clear(){
				core.closeModel('addModal-Quintessence');
				$("#expireDate").val('');
				$("#addQuintessenceType").val(-1);
				$("#addContentSelect").hide();
				$("#quintessenceContent").empty();
				$("#quintessenceMsg").text('');
			}

		},
		//根据id删除教师
		delTeachers:function(ids){
        	$.ajax({
        		url:F.basepath+'/quintessence/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        				F.table.reload();
        			}
        		}
        	})
        },
        reload:function(){
        	F.table.reload();
        },
        crateTimeFormatter : function(value, row, index){       	
        	return new Date(row.createTime).format("yyyy-MM-dd hh:mm");
        },
        publishDateFormatter : function(value, row, index){
        	return new Date(row.publishDate).format("yyyy-MM-dd");
        }	
        ,operateFormatter : function(value, row, index) {
			var _btnAction = "";
        if (base.perList.quintessence.del) {
			_btnAction += "<a class='delQuintessence btn btn-primary btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>"
			return _btnAction;
        }
		},
	
		
	};
	
});