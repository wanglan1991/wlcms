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
            if(base.perList.exercise.check){
            	$("#exercise-header .actions").append("<input autocomplete='off'  id='keyword'  placeholder='请输入习题内容' type='text' />&nbsp;&nbsp;" +
            	"<input autocomplete='off'  id='knoeledgeContent'  placeholder='请输入知识点' type='text' />&nbsp;&nbsp;"+
            	"<select  id='categoryOption' style='width:6%'></select>&nbsp;&nbsp;" +
            	"<select  id='typeOption' style='width:6%'></select>&nbsp;&nbsp;" +
            	"<select  id='difficultyOption' style='width:6%'></select>&nbsp;&nbsp;" +
            	"<select  id='gradeOption' style='width:6%'></select>&nbsp;&nbsp;" +
            	"<select  id='subjectOption' style='width:6%'><option value='0'>-- 无 --</option></select>&nbsp;&nbsp;" +
            	"<a href='#' id='queryByExercise' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");            	
            	core.getDictOptions("题型","exerciseType","#typeOption");
            	core.getDictOptions("题类","category","#categoryOption");
            	core.getDictOptions("年级","grade","#gradeOption");
            	core.getDictOptions("难易度","difficulty","#difficultyOption");
            
            }	
            //加载可选类型列表00        	
            if(base.perList.exercise.add){
            	$("#exercise-header .actions").append("<a href='#' id='addExercise' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
            }
            /**
             * 批量导入习题
             */																								 
            if(base.perList.exercise.import){
            	$("#exercise-header .actions").append("<a href='#' id='impExercise' data-toggle='modal' class='btn btn btn-primary btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>导入习题</a>");
            }
            /**
             * 是否具有删除权限权限
             */
            if(base.perList.exercise.del){
            	$("#exercise-header .actions").append("<a href='#' id='delExercises' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>批量删除</a>");
            }
            
            //加载字典
            function getDictOptions(type,dictType,selectId){
            	var optionsHtml="<option value='0'>--"+type+"--</option>";
            	$.ajax({
            		url:F.basepath+'/cms/dict/queryDictByCondition',
            		type:"GET",
            		data:{typeEncoding:dictType},
            		success:function(data){
            			for(var i=0;i<data.value.length;i++){
            				optionsHtml+="<option value='"+data.value[i].id+"'    >"+data.value[i].value+"</option>"
            			}
            			$(selectId).append(optionsHtml);
            		}
            	});	
            }
            

       
            //添加中加载学科
            function addSubjectOption(gradeNo,idOrClass){
            	if(gradeNo==0){return}
            	var subjectOption="";
            	$(idOrClass).empty();
            	$.ajax({
	        		url:F.basepath+"/cms/exercise/subjectList",
	        		type:"GET",
	        		data:{gradeNo:gradeNo},
	        		success:function(data){
	        			if(data.value.length<1){
	        				subjectOption+="<option value = '0'>无 ---</option>";
	        			}
	        			for(var i=0;i<data.value.length;i++){
	        					if(i==0){
	        						if(idOrClass=='#subjectOption'){
	        							subjectOption+="<option value=0>所有---</option>";
	        						}else{
	        							subjectOption+="<option value=0>学科---</option>";
	        						}
	        					}
	        					subjectOption+="<option value = '"+data.value[i].subjectNo+"'>"+data.value[i].subject+"</option>";
	        			}
	        			$(idOrClass).append(subjectOption);
	        		}
	        	
            		
            	});
            	
            }
            
            
            
//      	加载知识点
	        function knoeledge(subjectNo,idOrClass){
	        	var knowledgesOption ="";
	        	$(idOrClass).empty();
	        	$.ajax({
	        		url:F.basepath+"/cms/knowledge/list",
	        		type:"GET",
	        		data:{subjectNo:subjectNo},
	        		success:function(data){
	        			if(data.value.length<1){
	        				knowledgesOption+="<option value = '0'>-- 无 --</option>";
	        			}
	        			for(var i=0;i<data.value.length;i++){
	        				var lt=new RegExp("<","g");
	        				var gt=new RegExp(">","g");
	        				var val=data.value[i].title.replace(lt,"&lt;").replace(gt,"&gt;");
	        				knowledgesOption+="<option value = '"+data.value[i].id+"'>"+val+"</option>"
	        			}
	        			$(idOrClass).append(knowledgesOption);
	        		}
	        	})
	        }
            
            
            function getKnoeledgeOption(id,subjectNo,gradeNo){
            	if(subjectNo==0||gradeNo==0){return}
	        	$(id).empty();
	        	var knowledgesOption ="";
	        	$.ajax({
	        		url:F.basepath+"/cms/knowledge/list",
	        		type:"GET",
	        		data:{subjectNo:subjectNo,gradeNo:gradeNo},
	        		success:function(data){
	        			if(data.value.length<1){
	        				knowledgesOption+="<option value = '0'>无 ----</option>";
	        			}
	        			for(var i=0;i<data.value.length;i++){
	        				if(i==0){
	        				knowledgesOption+="<option value = '0'>知识点----</option>";
	        				};
	        				var lt=new RegExp("<","g");
	        				var gt=new RegExp(">","g");
	        				var vals=data.value[i].title.replace(lt,"&lt;").replace(gt,"&gt;");
	        				knowledgesOption+="<option value = '"+data.value[i].id+"'>"+vals+"</option>";
	        				
	        			}
	        			
	        			$(id).append(knowledgesOption);
	        		}
	        	})
   		}
            
            function editGetKnoeledgeOption(id,subjectNo,gradeNo,knowledgeId){
	        	$(id).empty();
	        	var knowledgesOption ="";
	        	$.ajax({
	        		url:F.basepath+"/cms/knowledge/list",
	        		type:"GET",
	        		data:{subjectNo:subjectNo,gradeNo:gradeNo},
	        		success:function(data){
	        			if(data.value.length<1){
	        				knowledgesOption+="<option value = '0'>-- 无 --</option>";
	        			}
	        			for(var i=0;i<data.value.length;i++){
	        				var lt=new RegExp("<","g");
	        				var gt=new RegExp(">","g");
	        				var vals=data.value[i].title.replace(lt,"&lt;").replace(gt,"&gt;");
	        				if(data.value[i].id==knowledgeId){
	        					knowledgesOption+="<option value = '"+data.value[i].id+"' selected='true' >"+vals+"</option>"
	        				}else{
	        				knowledgesOption+="<option value = '"+data.value[i].id+"'>"+vals+"</option>"
	        				}
	        			}
	        			$(id).append(knowledgesOption);
	        		}
	        	})
   		}
            
          
	        
	        operateEvents = {
	        		/**
	        		 * 预览习题
	        		 */
	        		'click .preview':function(e, value,row,index){
	        			$("#exerciseContent").empty();
	        			core.openModel('modal-preview','习题预览',function(){
	        				var reg= new RegExp('<//','g');
	        				var reg1= new RegExp('//>','g');
	        				var enter=new RegExp("\n","g");
	        				var blank=new RegExp(" ","g");
	        				var content=row.content.replace(blank,"&nbsp;");
	        					content=content.replace(enter,"<br>");
	        				    content=content.replace(reg,"</h3><img  src='http://ekt.oss-cn-shenzhen.aliyuncs.com/");
	        				var html="<div style='margin-left:4px;width:91%;'><h3>"+content.replace(reg1,"'><h3>")+"</h3></div><br><br>";
		        				$.ajax({
		        					url:F.basepath+"/cms/exercise/answer",
		        	        		type:"GET",
		        	        		data:{exerciseId:row.id},
		        	        		success:function(data){
		        	        			for(var i=0;i<data.value.length;i++){
		        	        				var content1 = data.value[i].contents.replace(blank,"&nbsp");
		        	        					content1 =content1.replace(enter,"<br>");
		        	        					content1 =content1.replace(reg,"<img style='min-width:13px' src='http://ekt.oss-cn-shenzhen.aliyuncs.com/");
		        	        				
		        	        				html+="<span><h4>"+data.value[i].option+".&nbsp;&nbsp;"+content1.replace(reg1,"'>")+
		        	        				"&nbsp;&nbsp;"+(data.value[i].isTrue==1?"<b style='color:blue'>√</b>":"<b style='color:red'>×</b>")+"</h4></span><br>";
		        	        			}
		        	        			$("#exerciseContent").append(html);
		        	        			var analysis = row.analysis.replace(blank,"&nbsp;");
		        	        				analysis = analysis.replace(enter,"<br>")
		        	        				analysis ="<h4>"+analysis.replace(reg,"</h4><img style='min-width:13px' src='http://ekt.oss-cn-shenzhen.aliyuncs.com/");
		        	        			$("#exerciseAnalysis").html(analysis.replace(reg1,"'><h4>")+"</h4>");
		        	        		}
		        					
		        				});
	        			
	        			});
	        			
	        		},
	        		
				/**
				 *打开修改模态框
				 */
		        'click .editExercise': function (e, value, row, index) {
		        	$("#editAnswer").find("div").remove();
		        	$("#editKnoeledgeOption").val("");
		        	core.openModel('modal-editExercise','修改习题',function(){
		        		core.getEditDictOptions("年级","grade","#editGradeOption",row.gradeNo);
		        		core.getEditDictOptions("题类","category","#editCategoryOption",row.categoryNo);
		        		core.getEditDictOptions("题型","exerciseType","#editTypeOption",row.typeNo);
		        		core.getEditDictOptions("学科","subject","#editSubjectOption",row.subjectNo);
		        		core.getEditDictOptions("难易度","difficulty","#editDifficultyOption",row.difficultyNo);
		        		if(row.knowledgeId!=null){
		        			editGetKnoeledgeOption("#editKnoeledgeOption",row.subjectNo,row.gradeNo,row.knowledgeId);
		        		}
		        		
		        		$("#modal-editExercise").attr('exerciseid',row.id);
		        		$("#editAnalysis").val(row.analysis);
		        		$("#editExerciseContent").val(row.content);
		        		$("#editAuthor").val(row.author);
		        		$("#editOrderNo").val(row.orderNo);
		        		var answerHtml="";
		        		$.ajax({
		        			url:F.basepath+'/cms/exercise/answer',
			        		type:'POST',
			        		data:{exerciseId:row.id},
			        		success:function(data){
			        			for(var i=0;i<data.value.length;i++){
			        				answerHtml+=" <div class='controls"+data.value[i].option+"' option='"+data.value[i].option+"' id='editAnswerOption'>"+data.value[i].option+"：&nbsp;&nbsp;" +
			        						"<textarea class='span8'   style='width:52%;'  required name='editAnswer' maxlength='900'  placeholder='答案..'>"+data.value[i].contents+"</textarea>&nbsp;&nbsp;isTrue:";
			        						if(data.value[i].isTrue==1){
			        						answerHtml+="<input style='margin-top:-1px' checked='checked'  name='editIsTrue' isTrue='0' type='checkbox' />";
			        						}else{
			        						answerHtml+="<input style='margin-top:-1px'  name='editIsTrue' isTrue='0' type='checkbox' />";	
			        						}
			        						answerHtml+="</div>";
			        			}
			        			$("#editAnswer").append(answerHtml);
			        		}
		        		});
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
				 * 删除习题
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
	        		    	align: 'center',
	        		        field: 'id',
	        		        title: '主键',
	        		    },{
	        		        field: 'content',
	        		        title: '习题内容',
	        		        formatter:F.contentFormatter
	        		    },{
	        		        field: 'knowledges',
	        		        title: '知识点'
	        		    },{
	        		    	field: 'accountRealName',
	        		    	title: '更新者'
	        		    }];
	        //是否需要操作列
	        if(base.perList.exercise.edit||base.perList.exercise.confine||base.perList.exercise.del)
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
    			query();
    		});
    		function query(){
    			var content = $("#keyword").val();
    			var gradeNo = $("#gradeOption").val();
    			var categoryNo = $("#categoryOption").val();
    			var difficultyNo = $("#difficultyOption").val();
    			var subjectNo = $("#subjectOption").val();
    			var knoeledge = $("#knoeledgeContent").val();
    			var typeNo =$("#typeOption").val();
    			var url= F.basepath+'/cms/exercise/pageList?content='+content
    			+'&gradeNo='+gradeNo+"&categoryNo="+categoryNo+"&difficultyNo="
    			+difficultyNo+"&subjectNo="+subjectNo+"&knowledges="+knoeledge+"&typeNo="+typeNo;
				$("#exerciseTable").bootstrapTable('refresh',{url:url});
    		}
    		//监听键盘事件
//    		document.onkeydown=keyDownSearch; 
    		document.getElementById('exercise-actions').onkeydown=keyDownSearch; 
    		function keyDownSearch(e) {  
    	        // 兼容FF和IE和Opera  
    	        var theEvent = e || window.event;  
    	        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
    	        if (code == 13) {   
    	        	query();	
    	        }  
    	    } 
			/**
			 * 打开模态框
			 */
			$('#addExercise').click(function(){
				$("#answer").find("div").remove();
				core.openModel('modal-addExercise','新增习题',function(){
					core.getDictOptions("题类","category","#addCategoryOption");
	            	core.getDictOptions("题型","exerciseType","#addTypeOption");
	            	core.getDictOptions("年级","grade","#addGradeOption");	    
	            	core.getDictOptions("难易度","difficulty","#addDifficultyOption");
					});
					var tag=4;
					for(var i=0;i<tag;i++){
						var option =i==0?'A':i==1?"B":i==2?"C":i==3?"D":i==4?"E":"F";
					$("#answer").append(" <div class='controls"+option+"' option='"+option+"' id='answerOption'>"+option+"：&nbsp;&nbsp;<textarea class='span8'  style='width:52%;' required name='answer' maxlength='900'  placeholder='答案..'></textarea>" +
					"&nbsp;&nbsp;isTrue:<input style='margin-top:-1px' name='isTrue' isTrue='0' type='checkbox' /></div>");
				}
				
				
				
			});
			/**
			 * 打开导入习题模态框
			 */
			$('#impExercise').click(function(){
				$("#waitForUpload").remove();
				$("#uploadCategoryOption").empty();
				$("#uploadTypeOption").empty();
				$("#uploadGradeOption").empty();
				$("#uploadDifficultyOption").empty();
				$("#uploadSubjectOption").empty();
				$("#uploadKnoeledgeOption").empty();
				core.openModel('modal-impExercise','导入习题',function(){
					getDictOptions("题类","category","#uploadCategoryOption");
					getDictOptions("题型","exerciseType","#uploadTypeOption");
					getDictOptions("年级","grade","#uploadGradeOption")
					getDictOptions("难易度","difficulty","#uploadDifficultyOption");
					$("#uploadSubjectOption").append("<option value='0' >-- 等待选择年级 --</option>");
	            	$("#uploadKnoeledgeOption").append("<option value='0' >-- 等待选择学科 --</option>")
				});
			});
			/**
			 * 关闭习题导入框
			 */
			$('#impBtnClose').click(function(){
				 core.closeModel('modal-impExercise');
				 clearUpload();
				 
			});
			/**
			 * 关闭习题预览框
			 */
			$('#addClose').click(function(){
				$("#exerciseContent").empty();
			});
			
			//增加习题答案输入框 
			$("#addAnswer").click(function(){
				var input = $("#answer").find("div").length
				var option =input==0?'A':input==1?"B":input==2?"C":input==3?"D":input==4?"E":"F";
				if(input>5){return; }
				$("#answer").append(" <div class='controls"+option+"' option='"+option+"' id='answerOption'>"+option+"：&nbsp;&nbsp;<textarea class='span8'  style='width:52%;' required name='answer' maxlength='900'  placeholder='答案..'></textarea>" +
						"&nbsp;&nbsp;isTrue:<input style='margin-top:-1px' name='isTrue' isTrue='0' type='checkbox' /></div>");
			});
//			增加编辑习题答案输入框
			$("#editAddAnswer").click(function(){
				var input = $("#editAnswer").find("div").length
				var option =input==0?'A':input==1?"B":input==2?"C":input==3?"D":input==4?"E":"F";
				if(input>5){return; }
				$("#editAnswer").append(" <div class='controls"+option+"' option='"+option+"' id='editAnswerOption'>"+option+"：&nbsp;&nbsp;<textarea class='span8'  style='width:52%;' required name='editAnswer' maxlength='900'  placeholder='答案..' type='text'></textarea>" +
						"&nbsp;&nbsp;isTrue:<input style='margin-top:-1px' name='editIsTrue' editIsTrue='0' type='checkbox' /></div>");
			});
//			删除习题答案输入框
			$("#removeAnswer").click(function(){
				var input = $("#answer").find("div").length
				if(input==0){return ;}
				var option =input==1?'A':input==2?"B":input==3?"C":input==4?"D":input==5?"E":"F";
				$(".controls"+option).remove();
				});
//			删除编辑习题答案输入框
			$("#editRemoveAnswer").click(function(){
				var input = $("#editAnswer").find("div").length
				if(input==0){return ;}
				var option =input==1?'A':input==2?"B":input==3?"C":input==4?"D":input==5?"E":"F";
				$(".controls"+option).remove();
				});
			
//			/**
//			 * 加载学科
//			 */
			$("#gradeOption").change(function(){
				var grade = $("#gradeOption").val();
				addSubjectOption(grade,"#subjectOption");
			});
//			
			$("#addGradeOption").change(function(){
				var grade = $("#addGradeOption").val();
				addSubjectOption(grade,"#addSubjectOption");				
			});

			$("#uploadGradeOption").change(function(){
				var grade = $("#uploadGradeOption").val();
				addSubjectOption(grade,"#uploadSubjectOption");	
			})
//			
			$("#addSubjectOption").change(function(){
				var subject= $("#addSubjectOption").val();
				var grade = $("#addGradeOption").val();
					getKnoeledgeOption("#addKnoeledgeOption",subject,grade);
			})

			 /**
			  * 加载编辑知识点
			  */
			 $("#editSubjectOption").change(function(){
				 	var subjectNo = $("#editSubjectOption").val();
				 	var gradeNo = $("#editGradeOption").val();
				 	getKnoeledgeOption("#editKnoeledgeOption",subjectNo,gradeNo);
//				 	knoeledge(subjectNo,"#editKnoeledgeOption");
			 });
			 
			 /**
			  * 加载上传知识点
			  */
			 $("#uploadSubjectOption").change(function(){
				 	var subjectNo = $("#uploadSubjectOption").val();
				 	knoeledge(subjectNo,"#uploadKnoeledgeOption");
//				 	$("#uploadKnoeledgeOption").select2();
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
				$("#addSubjectOption").empty();
				$("#addSubjectOption").append("<option value='0'>科目----</option>");
				$("#addKnoeledgeOption").empty();
				$("#addKnoeledgeOption").append("<option value='0'>知识点----</option>");
				clear();
			});
			/**
			 * 关闭编辑模态框
			 */   
			$('#editBtnClose').click(function(){
				clearEdit();
			});
			$('.close').click(function(){
				clearEdit();
				clear();
			});
			//清理表单
			function clear(){
				$("#addGrade-error").html('');
				$("#addCategory-error").html('');	
				$("#addType-error").html('');
				$("#addDifficulty-error").html('');
				$("#addSubject-error").html('');
				$("#addKnoeledge-error").html('');
				$("#msg").html('');
				$("#exeFile").val('');
				$("#url").val('');
				$("#addExerciseContent").val('');
				$("#author").val('');
				$("#orderNo").val('');
				$("#answer").find("div").remove();
				$("#addAnalysis").val('');
				$("#addCategoryOption").val(0);
				$("#addTypeOption").val(0);
				$("#addDifficultyOption").val(0);
				$("#addGradeOption").val(0);
				$("#addSubjectOption").val(0);
				$("#addKnoeledgeOption").val(0)
				core.closeModel('modal-addExercise');	
			}
			//清理 编辑表单
			function clearEdit(){
				$("#editGrade-error").html('');
				$("#editCategory-error").html('');	
				$("#editType-error").html('');
				$("#editDifficulty-error").html('');
				$("#editSubject-error").html('');
				$("#editKnoeledge-error").html('');
				$("#editAnalysis").val('');
				$("#editMsg").html('');
				$("#editExeFile").val('');
				$("#editUrl").val('');
				$("#editExerciseContent").val('');
				$("#editAuthor").val('');
				$("#editOrderNo").val('');
				$("#editAnswer").find("div").remove();
				core.closeModel('modal-editExercise');	
			}
			
			//清理 上传表单
			function clearUpload(){
				$("#fileData").val("");
				$("#result textarea").val('');
				$("#result").hide();
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
			//监听文件是否已经上传
			$('#impFile').bind('input propertychange', function() {  
				if($("#impFile").val().length>0){
					$("#filePath").val($('#impFile').val());
				}
			});  
			
			
			/**
			 * 新增提交
			 */
			$('#btnSubmit').click(
					function() {
						var grade = $("#addGradeOption").val();
						var category = $("#addCategoryOption").val();
						var type = $("#addTypeOption").val();
						var difficulty = $("#addDifficultyOption").val();
						var subject = $("#addSubjectOption").val();
						var knowledgeId=$("#addKnoeledgeOption").val();
						var knowledges =$("#addKnoeledgeOption").find('option:selected').text()
						var author = $("#author").val();
						var content = $("#addExerciseContent").val();
						var order =$("#orderNo").val();
						var answerLength = $("#answer").find("div").length;
						var analysis =$("#addAnalysis").val();
						if(grade==0){$("#msg").html("请选择年级！"); return;}
						if(category==0){$("#msg").html("请选择题类！"); return;}
						if(type==0){$("#msg").html("请选择题型！"); return;}
						if(difficulty==0){$("#msg").html("请选择难易度！"); return;}
						if(subject==0){$("#msg").html("请选择科目！"); return;}
						if(category==92&&knowledgeId==0){$("#msg").html("请选择知识点！"); return;}
						if(content.length<5){$("#msg").html("习题内容不能为空！不能小于5个字符"); return;}
						if(answerLength<2){$("#msg").html("至少2个答案！"); return;}
				var answerList=new Array();	
				var length=0;
				var list=new Array();
				var isTrueTag=0;
				$("#answer div").each(function(){
					var answer=$(this).find("[name='answer']").val();
					length=answer.length;
					if(answer.length<1){$("#msg").html("答案内容不能为空！"); return false;}
					var isTrue=$(this).find("[name='isTrue']").attr('checked')=='checked'?1:0;
					list.push(isTrue);
					var option=$(this).attr('option');
					var value={option:option,isTrue:isTrue,contents:answer};
					answerList.push(value);
				  });
				if(length<1){return;}
				
				if(!contains(list, 1)){
					 $("#msg").html("需要选择正确答案！"); 
					 return;
				}
				
				var exercise = {
					gradeNo : grade,
					categoryNo : category,
					typeNo : type,
					difficultyNo : difficulty,
					subjectNo : subject,
					knowledgeId : knowledgeId==0?null:knowledgeId,
					knowledges:knowledgeId==0?"":knowledges,
					author : author,
					content : content,
					orderNo:order,
					answerList:answerList,
					analysis:analysis
				}
				$.ajax({
					url:F.basepath+'/cms/exercise/addExercise',
					type:'POST',
					data:exercise,
					success:function(data){
						if(data.result>0){
							if(confirm("提交成功是否继续添加!")){
								$("#modal-addExercise input").val('');    
								$("#modal-addExercise textarea").val('');  
								$("#addExerciseContent").val('');
								$("#addAnalysis").val('');
								$("#answer input[name=isTrue]").removeAttr("checked");
								$("#msg").html('');
								F.reload();
							}else{
								clear();
								F.reload();
							}
							
						}
						
						
					}
				})
            });
//			提交修改
			$("#editBtnSubmit").click(function(){
					var grade = $("#editGradeOption").val();
					var category = $("#editCategoryOption").val();
					var type = $("#editTypeOption").val();
					var difficulty = $("#editDifficultyOption").val();
					var subject = $("#editSubjectOption").val();
					var knowledgeId = $("#editKnoeledgeOption").val();
					var knowledges =$("#editKnoeledgeOption").find('option:selected').text();
					var analysis = $("#editAnalysis").val();
					var author = $("#editAuthor").val();
					var content = $("#editExerciseContent").val();
					
					var order =$("#editOrderNo").val();
					var id = $("#modal-editExercise").attr('exerciseid');
					var answerLength = $("#editAnswer").find("div").length;
//					if(grade==0){$("#editMsg").html("请选择年级！");return;}
//					if(category==0){$("#editMsg").html("请选择题类！");return;}
					if(type==0){$("#editMsg").html("请选择题型！");return;}
					if(difficulty==0){$("#editMsg").html("请选择难易度！");return;}
					if(subject==0){$("#editMsg").html("请选择科目！");return;}
					if(category==92&&knowledgeId==0){$("#editMsg").html("请选择知识点！");return;}
					if(content.length<5){$("#editMsg").html("习题内容不能为空！不能小于5个字符");return;}
					if(answerLength<2){$("#editMsg").html("至少2个答案！");return;}
			var answerList=new Array();	
			var length=0;
			var list=new Array();
			var isTrueTag=0;
			$("#editAnswer div").each(function(){
				var answer=$(this).find("[name='editAnswer']").val();
				length=answer.length;
				if(answer.length<1){$("#editMsg").html("答案内容不能为空！");return false;}
				var isTrue=$(this).find("[name='editIsTrue']").attr('checked')=='checked'?1:0;
				list.push(isTrue);
				var option=$(this).attr('option');
				var value={option:option,isTrue:isTrue,contents:answer};
				answerList.push(value);
			  });
			if(length<1){return;}
			
			if(!contains(list, 1)){
				 $("#editMsg").html("需要选择正确答案！");
				 return;
			}
			
			var exercise = {
				id:id,
				gradeNo : grade,
				categoryNo : category,
				typeNo : type,
				difficultyNo : difficulty,
				subjectNo : subject,
				knowledgeId : knowledgeId,
				knowledges : knowledges,
				author : author,
				content : content,
				orderNo:order,
				answerList:answerList,
				analysis:analysis
			}
			$.ajax({
				url:F.basepath+'/cms/exercise/editExercise',
				type:'POST',
				data:exercise,
				success:function(data){
					clearEdit();
					F.reload();
				}
			})
	
				
			});
			
			$('#uploadExercise').click(function(){
//				var file = $("#impFile").val();
//				$.ajax({
//					url:F.basepath+'/cms/upload/exercises',
//					type:"POST",
//					data:{temple:file}
//					
//				})
				
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
        },
        contentFormatter:function(value, row, index){
        	var content =row.content;
        	if(content.length>45){
        		content = content.substring(0,45)+"....";
        	}
        	return content;
        }	
        ,
        operateFormatter:function (value, row, index) {        	
        	var _btnAction = "";
        	if(base.perList.exercise.preview){
        	_btnAction += "<a data-toggle='modal' class='preview btn btn-success btn-small' href='#' title='预览' style='margin-left:5px'>预览</a>";
        	}
        	if (base.perList.exercise.confine) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
        	}
        	if (base.perList.exercise.edit) {
        		_btnAction += "<a data-toggle='modal' class='editExercise btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.exercise.del) {
        		_btnAction += "<a class='delExercise btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>";
        	}
        	return _btnAction;
        } 
    };
    
    jQuery(document).ready(function(){
    	$(function() {
    		$("#upload").ajaxForm({
    			//图片上传的文件夹
    			url:"/cms/upload/imageUpload",
    			type:"post",
    			data:$('#upload').serialize(),// 你的formid
    			data:{key:"exercise/",fileName:"exeFile"},
    			beforeSend : function() {
    				$("#submitbutton").attr("disabled","disalbed");
    			},
    			success : function(data) {
    				$("#submitbutton").attr("disabled",false);
    				//提交成功后调用
    				if (data.value!=null) {
    					if(data.value.status == 0){
    						var file = $("#exeFile").val();
    						var fileName = "<//exercise/" + core.getFileName(file)+"//>";
    						$("#url").val(fileName);
    					}
    				} else {
    					alert("上传异常 ")
    				}
    				$("#exeFile").val('');
    			}
    		});
    	});
    	
    	
    	//上传表单
    	
//    	获取id素组
    	function knowledgeIdArr(){
    		var arrKnoeledge=$("#uploadKnoeledgeOption").find('option:selected');
			var knowledgeIds='';
			if(arrKnoeledge!=null){
				for(var i=0; i<arrKnoeledge.length;i++){
					knowledgeIds+=$(arrKnoeledge[i]).val()+',';
				}
			}	
			return knowledgeIds;
		}
//    	获取知识点文本素组
		function knowledgeArr(){
			var arrKnoeledge=$("#uploadKnoeledgeOption").find('option:selected');
			var knowledges='';
			if(arrKnoeledge!=null){
				for(var i=0; i<arrKnoeledge.length;i++){
					knowledges +=$(arrKnoeledge[i]).text()+',';
				}
			}	
			return knowledges;
		}			    	
    	
//    	
    	$("#uploadExercise").click(function(){
    		$("#excelUpload").ajaxForm({
    			url:"/cms/upload/exercises",
    			type:"post",
    			data:$('#excelUpload').serialize(),// 你的formid
    			beforeSend : function() {
    				$("#modal-impExercise").append("<div id='waitForUpload'background-color:azure style='opacity: 0.6;position: absolute;" +
							"position: absolute;z-index=4; width: 100%;height: 100%;margin-top:-60%'>" +
							"<div style='margin-left: 40%;margin-top: 25%;'><h3><b>uploading...</b>" +
							"</h3><img src='/cms/assets/images/upload.gif' style='max-width: 19%;'/></div></div>");
    			},
    			success : function(data){
    				F.reload();
    				$("#waitForUpload").remove();
    				$("#result textarea").val('');
    				$("#uploadMsg").html('');
    				$("#result").show();
    				if(data.result==-1){
    					$("#result textarea").val(data.msg);
    				}else if(data.result==0){
    					$("#uploadMsg").html(data.msg);
    				}
 	
    			}   		
    	});
    		
    	});
    	
    	//上传表单验证和提交  编辑
    	//图片上传到阿里云OSS
    	$(function() {
    		$("#editUpload").ajaxForm({
    			url:"/cms/upload/imageUpload",
    			type:"post",
    			data:$('#editUpload').serialize(),// 你的formid
    			data :{key:"exercise/",fileName:"editExeFile"},
    			beforeSend : function() {
    			
    			$("#editSubmitbutton").attr("disabled","disalbed");
    			},
    			success : function(data) {
    				$("#editSubmitbutton").attr("disabled",false);
    				//提交成功后调用
    				if (data.value!=null) {
    					
    					if(data.value.status == 0){
    						var file = $("#editExeFile").val();
    						var fileName = "<//exercise/" + core.getFileName(file)+"//>";
    						$("#editUrl").val(fileName);
    					}
    				} else {
    					alert("上传异常 ")
    				}
    				$("#editExeFile").val('');
    			}
    		});
    	});
    	
    	
    });
});
		//复制上传图片文件名
    	 function jsCopy(id){ 
    	        var e=document.getElementById(id);//对象是content 
    	        e.select(); //选择对象 
    	        document.execCommand("Copy"); //执行浏览器复制命令 
    	    } 
    	 