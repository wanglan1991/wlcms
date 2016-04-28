// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        tree:{},
        radioTree:{},
        table:new core.Table('exerciseTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
            /**
             * 是否具有添加权限权限
             */
            if(base.perList.permission.check){
            	$("#exercise-header .actions").append("<input autocomplete='off'  id='keyword'  placeholder='请输入习题内容' type='text' />&nbsp;&nbsp;" +
            	"<select  id='gradeOption' style='width:10%'></select>&nbsp;&nbsp;" +
            	"<select  id='categoryOption' style='width:10%'></select>&nbsp;&nbsp;" +
            	"<select  id='typeOption' style='width:10%'></select>&nbsp;&nbsp;" +
            	"<select  id='difficultyOption' style='width:10%'></select>&nbsp;&nbsp;" +
            	"<select  id='subjectOption' style='width:10%'></select>&nbsp;&nbsp;" +
            	"<select  id='knoeledgeOption' style='width:10%'><option value='0'>-- 无 --</option></select>&nbsp;&nbsp;" +
            	"<a href='#' id='queryByExercise' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
            	getDictOptions("年级","grade","#gradeOption");
            	getDictOptions("题型","exerciseType","#typeOption");
            	getDictOptions("学科","subject","#subjectOption");
            	getDictOptions("题类","category","#categoryOption");
            	getDictOptions("难易度","difficulty","#difficultyOption");
            
            }	
            //加载可选类型列表00        	
            if(base.perList.permission.create){
            	$("#exercise-header .actions").append("<a href='#' id='addExercise' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
            }
            /**
             * 是否具有删除权限权限
             */
            if(base.perList.permission.del){
            	$("#exercise-header .actions").append("<a href='#' id='delExercises' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
            }
            
            
            //加载字典
            function getDictOptions(type,dictType,selectId){
            	var optionsHtml="<option value='0'>-- 请选择"+type+" --</option>";
            	$.ajax({
            		url:F.basepath+'/cms/dict/queryDictByCondition',
            		type:"GET",
            		data:{typeEncoding:dictType},
            		success:function(data){
            			for(var i=0;i<data.value.length;i++){
            				optionsHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].value+"</option>"
            			}
            			$(selectId).append(optionsHtml);
            		}
            	});	
            }
            //加载知识点
            $("#subjectOption").change(function(){
            	var subjectNo = $("#subjectOption").val();
            	getKnoeledgeOption("#knoeledgeOption",subjectNo);
            	
            });
            
            function getKnoeledgeOption(id,subjectNo){
	        	$(id).empty();
	        	var knowledgesOption ="";
	        	$.ajax({
	        		url:F.basepath+"/cms/knowledge/listPage",
	        		type:"GET",
	        		data:{subjectNo:subjectNo},
	        		success:function(data){
	        			if(data.rows.length<1){
	        				knowledgesOption+="<option value = '0'>-- 无 --</option>";
	        			}
	        			for(var i=0;i<data.rows.length;i++){
	        				if(i==0){
	        				knowledgesOption+="<option value = '0'>-- 所有 --</option>";
	        				};
	        				knowledgesOption+="<option value = '"+data.rows[i].id+"'>"+data.rows[i].title+"</option>"
	        			}
	        			$(id).append(knowledgesOption);
	        		}
	        	})
        
   			
   			
   		}
         
			
            
//			
//			/**
//			 * 请求权限数据
//			 */
//	        F.treeLoad();
	        
	        operateEvents = {
				/**
				 *打开修改模态框
				 */
		        'click .editPerm': function (e, value, row, index) {
		        	core.openModel('modal-editPerm','修改权限',function(){
//		        		$("#editPermType").find("option[value='"+row.level+"']").attr("selected","true");
		        		if(row.level==1){$("#editPermissionParent").hide()}
		        		$("#editPermName").val(row.name);
		        		$("#editKey").val(row.key);
		        		$("#editValue").val(row.value);
		        		$("#editOrder").val(row.orderNo);
		        		$("#editIcon").val(row.icon);
		        		$("#modal-editPerm").attr('Perm',row.id);
		        		
		        	});
				
		        },
		        /**
		         * 停用或启用
		         */
		        'click .confine':function(e,value,row,index){
		        	$.ajax({
		        		url:F.basepath+'/cms/exercise/confine',
		        		type:'GET',
		        		data:{id:row.id,status:row.status==1?0:1},
		        		success:function(data){
		        			if(data.result>0){
		        				F.reload();
		        			}else{
		        				alert("异常！ 稍后再试");
		        			}
		        		}
		        	});
		        },
		        /**
				 * 删除权限
				 */
		        'click .delExercise': function (e, value, row, index) {
		        	base.bootConfirm("是否确定删除？",function(){
		        		var ids = new Array();  
		        		ids.push(row.id);   
		    			F.delExercise(ids);
		    		});
		        }
		    };
	        	
	        var cols = [
	                    {
	        		        checkbox:true
	        		    },{
	        		        field: 'id',
	        		        title: '主键'
	        		    },{
	        		        field: 'content',
	        		        title: '习题内容'
	        		    },{
	    			        field: 'subject',
	    			        title: '科目'
	    		        },{
	    			        field: 'grade',
	    			        title: '年级'
	    		        },{
	    			        field: 'type',
	    			        title: '类型'
	    		        },{
	    			        field: 'category',
	    			        title: '题型'
	    		        },{
	    			        field: 'difficulty',
	    			        title: '难易度'
	    		        },{
	        		        field: 'knoeledge',
	        		        title: '知识点'
	        		    },{
	    			        field: 'publisher',
	    			        title: '出版社'
	    		        },{
	    			        field: 'author',
	    			        title: '作者'
	    		        }];
	        //是否需要操作列
	        if(base.perList.permission.edit||base.perList.permission.del||base.perList.permission.confine)
		        cols.push({
			    	align: 'center',
			        title: '操作',
			        events: operateEvents,
			        formatter:F.operateFormatter
			    });
        		
    		F.table.init(F.basepath+'/cms/exercise/pageList',cols);
    		
    		/**
    		 * 带参查询
    		 */
    		$('#queryByExercise').click(function(){
    			var content = $("#keyword").val();
    			var gradeNo = $("#gradeOption").val();
    			var categoryNo = $("#categoryOption").val();
    			var difficultyNo = $("#difficultyOption").val();
    			var subjectNo = $("#subjectOption").val();
    			var knoeledgeId = $("#knoeledgeOption").val();
    			var typeNo =$("#typeOption").val();
    			var url= F.basepath+'/cms/exercise/pageList?content='+content
    			+'&gradeNo='+gradeNo+"&categoryNo="+categoryNo+"&difficultyNo="
    			+difficultyNo+"&subjectNo="+subjectNo+"&knoeledgeId="+knoeledgeId+"&typeNo="+typeNo;
				$("#exerciseTable").bootstrapTable('refresh',{url:url});	
    		});
    		
			/**
			 * 打开模态框
			 */
			$('#addExercise').click(function(){
				core.openModel('modal-addExercise','新增习题',function(){
					getDictOptions("年级","grade","#addGradeOption");
					getDictOptions("题类","category","#addCategoryOption");
	            	getDictOptions("题型","exerciseType","#addTypeOption");
	            	getDictOptions("学科","subject","#addSubjectOption");
	            	getDictOptions("难易度","difficulty","#addDifficultyOption");
	            	getDictOptions("出版社","publish","#addpublisherOption");
	            	$("#addKnoeledgeOption").append("<option value='0' >-- 等待选择学科 --</option>");
								});
			});
			//增加习题答案输入框
			$("#addAnswer").click(function(){
				var input = $("#answer").find("div").length
				var option =input==0?'A':input==1?"B":input==2?"C":input==3?"D":input==4?"E":"F";
				if(input>5){return; }
				$("#answer").append(" <div class='controls"+option+"' option='"+option+"' id='answerOption'>"+option+"：&nbsp;&nbsp;<input class='span8'  style='width:52%;' required name='answer' maxlength='900'  placeholder='答案..' type='text' />" +
						"&nbsp;&nbsp;isTrue:<input style='margin-top:-1px' name='isTrue' isTrue='0' type='checkbox' /><span id='editKey-error' class='help-block error'></span></div>");
			});
			$("#removeAnswer").click(function(){
				var input = $("#answer").find("div").length
				if(input==0){return ;}
				var option =input==1?'A':input==2?"B":input==3?"C":input==4?"D":input==5?"E":"F";
				$(".controls"+option).remove();
				});
			
			
			
			/**
			 * 加载知识点
			 */
			 $("#addSubjectOption").change(function(){
	            	var subjectNo = $("#addSubjectOption").val();
	            	$("#addKnoeledgeOption").empty();
		        	var knowledgesOption ="";
		        	$.ajax({
		        		url:F.basepath+"/cms/knowledge/listPage",
		        		type:"GET",
		        		data:{subjectNo:subjectNo},
		        		success:function(data){
		        			if(data.rows.length<1){
		        				knowledgesOption+="<option value = '0'>-- 无 --</option>";
		        			}
		        			for(var i=0;i<data.rows.length;i++){
		        				knowledgesOption+="<option value = '"+data.rows[i].id+"'>"+data.rows[i].title+"</option>"
		        			}
		        			$("#addKnoeledgeOption").append(knowledgesOption);
		        		}
		        	})
	        
	   			
	   			
	   		
	            	
	            });
			 
			$("#addPermType").change(function (){
				$("#icons").show();
				$("#orderNo").show();
				$("#permPrent").empty();
				$("#permissionParent").show();
				var type=$("#addPermType").val();
				if(type==-1||type==1){$("#permissionParent").hide();return}
					$.ajax({
	        			url:F.basepath+'/cms/permission/pidList',
	        			type:"GET",
	        			data:{type:type==2?1:2},
	        			success:function(data){
	        				var pidHtml='';
	        				for(var i=0;i<data.value.length;i++){
	        					pidHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].name+"</option>";
	        				}
	        				$("#permPrent").append(pidHtml);
	        			}
        			
        		});
				
				
			});
			
			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function(){
				clear();
			
			});
			$('#editBtnClose').click(function(){
				core.closeModel('modal-editExercise');
			});
			//清理表单
			function clear(){
				$("#addGradeOption").empty();
				$("#addGrade-error").html('');
				$("#addCategoryOption").empty();
				$("#addCategory-error").html('');	
				$("#addTypeOption").empty();
				$("#addType-error").html('');
				$("#addDifficultyOption").empty();
				$("#addDifficulty-error").html('');
				$("#addSubjectOption").empty();
				$("#addSubject-error").html('');
				$("#addKnoeledgeOption").empty();
				$("#addKnoeledge-error").html('');
				$("#addpublisherOption").empty();
				$("#addpublisher-error").html('');
				$("#msg").html('');
				$("#exerciseContent").val('');
				$("#author").val('');
				$("#orderNo").val('');
				$("#answer").find("div").remove();
				core.closeModel('modal-addExercise');	
			}
			//查询是否包含
			function contains(a, obj) {
			    var i = a.length;
			    while (i--) {
			       if (a[i] === obj) {
			           return true;
			       }
			    }
			    return false;
			}
			
			
			
			/**
			 * 提交新增
			 */
			$('#btnSubmit').click(
					function() {
						var grade = $("#addGradeOption").val();
						var category = $("#addCategoryOption").val();
						var type = $("#addTypeOption").val();
						var difficulty = $("#addDifficultyOption").val();
						var subject = $("#addSubjectOption").val();
						var knoeledge = $("#addKnoeledgeOption").val();
						var publisher = $("#addpublisherOption").val();
						var author = $("#author").val();
						var content = $("#exerciseContent").val();
						var order =$("#orderNo").val();
						var answerLength = $("#answer").find("div").length;
						if(grade==0){$("#msg").html("请选择年级！");$("#msg").css('color','#b94a48');return;}
						if(category==0){$("#msg").html("请选择题类！");$("#msg").css('color','#b94a48');return;}
						if(type==0){$("#msg").html("请选择题型！");$("#msg").css('color','#b94a48');return;}
						if(difficulty==0){$("#msg").html("请选择难易度！");$("#msg").css('color','#b94a48');return;}
						if(subject==0){$("#msg").html("请选择科目！");$("#msg").css('color','#b94a48');return;}
						if(knoeledge==0){$("#msg").html("请选择知识点！");$("#msg").css('color','#b94a48');return;}
						if(publisher==0){$("#msg").html("请选择出版社！");$("#msg").css('color','#b94a48');return;}
						if(content.length<5){$("#msg").html("习题内容不能为空！不能小于5个字符");$("#msg").css('color','#b94a48');return;}
						if(answerLength<4){$("#msg").html("至少4个答案！");$("#msg").css('color','#b94a48');return;}
				var answerList=new Array();	
				var length=0;
				var list=new Array();
				var isTrueTag=0;
				$("#answer div").each(function(){
					var answer=$(this).find("[name='answer']").val().replace(/\s+/g, "");
					length=answer.length;
					if(answer.length<1){$("#msg").html("答案内容不能为空！");$("#msg").css('color','#b94a48');return false;}
					var isTrue=$(this).find("[name='isTrue']").attr('checked')=='checked'?1:0;
					list.push(isTrue);
					var option=$(this).attr('option');
					var value={option:option,isTrue:isTrue,contents:answer};
					answerList.push(value);
				  });
				if(length<1){return;}
				
				if(!contains(list, 1)){
					 $("#msg").html("需要选择正确答案！");$("#msg").css('color','#b94a48');
					 return;
				}
				
				var exercise = {
					gradeNo : grade,
					categoryNo : category,
					typeNo : type,
					difficultyNo : difficulty,
					subjectNo : subject,
					knoeledgeId : knoeledge,
					publisherNo : publisher,
					author : author,
					content : content,
					orderNo:order,
					answerList:answerList
				}
				
				$.ajax({
					url:F.basepath+'/cms/exercise/addExercise',
					type:'POST',
					data:exercise,
					success:function(data){
						clear();
						F.reload();
					}
				})
            });
			
			/**
			 * 批量删除
			 */
			$('#delExercises').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个权限？",function(){
						F.delExercise(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的权限！"});
				}
			});

        },delExercise:function(ids){
        	$.ajax({
        		url:F.basepath+'/cms/exercise/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        				F.reload();
        			}
        		}
        	})
        	
        }
        	,reload:function(){
        	F.table.reload();
        }

        ,
        operateFormatter:function (value, row, index) {
        	var _btnAction = "";
        	if (base.perList.permission.confine) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
        	}
        	if (base.perList.permission.edit) {
        		_btnAction += "<a data-toggle='modal' class='editPerm btn btn-success btn-small' href='#' title='编辑权限' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.permission.del) {
        		_btnAction += "<a class='delExercise btn btn-danger btn-small' href='#' title='删除权限' style='margin-left:5px'>删除</a>";
        	}
        	return _btnAction;
        } 
    };
});
