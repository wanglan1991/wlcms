// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
   
    
    var C = module.exports = {
            basepath: '',
            catalogTable:new core.Table('catalogTable'),
            init: function (_basepath) {
                C.basepath = _basepath;
                

              //删除目录
	     },delCatalogs:function(ids){
	    	  $.ajax({
	    		  url:C.basepath+'/cms/catalog/delete',
	    		  type:"POST",
	    		  data:{ids:ids.toString()},
	    		  success:function(data){
	    			  if(data.result>0){
	    				  C.reload();
	    			  }else{
	    				  alert("异常 ！")
	    			  }
	    		  }
	    	  });
	       },reload:function(){
	       	C.catalogTable.reload();
	       }, operateFormatter:function (value, row, index) {
	        	var _btnAction = "";
	        	if (base.perList.textbook.editCatalog) {
	        		_btnAction += "<a data-toggle='modal' class='editCatalog btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>";
	        	}
	        	
	        	if (base.perList.textbook.delCatalog) {
	        		_btnAction += "<a class='delCatalog btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>";
	        	}
	        	return _btnAction;
	        } 
	       
	    };
    
    var F = module.exports = {
        basepath: '',
        table:new core.Table('textbookTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
          	
            /**
             * 添加教材
             */
            if(base.perList.textbook.check){
            	$("#perm-header .actions").append("<input autocomplete='off'  id='name'  placeholder='请输入教材名称' type='text' />&nbsp;&nbsp;" +
				"<select id='grade'></select>&nbsp;&nbsp;" +
				"<select id='subject'></select>&nbsp;&nbsp;" +
				"<a href='#' id='query' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
            	getDictOptions("年级","grade","#grade");
            	getDictOptions("科目","subject","#subject");
            	
            	
            }	
            //加载可选类型列表00        	
            if(base.perList.textbook.add){
            	$("#perm-header .actions").append("<a href='#' id='addTextbook' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
            }
            /**
             * 是否具有删除教材
             */
            if(base.perList.textbook.del){
            	$("#perm-header .actions").append("<a href='#' id='delTextbooks' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
            }
            	
            	
	          if(base.perList.textbook.checkCatalog){
	        	$("#catalog-header .action").append("<input autocomplete='off' id=catalogName    placeholder='请输入名称' type='text' />&nbsp;&nbsp;" +
	    				"<select id='catalogLevel'></select>&nbsp;&nbsp;" +
	    				"<select id='parentOptions' style='display:none;'></select>&nbsp;&nbsp;" +
		    			"<a href='javascript:void(0)' id='catalogQuery' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		        		 getDictOptions("目录级别","catalogLevel","#catalogLevel");
		        		 
	        	 }
		      if(base.perList.textbook.delCatalog){
		        $("#catalog-header .action").append("<a href='#' id='delCatalog' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
		      }
		      if(base.perList.textbook.addCatalog){
		        $("#catalog-header .action").append("<a href='#' id='addCatalog' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
		       }
		        $("#catalog-header .action").append("<button class='close' type='button' id='catalogClose' >x</button>");
            	
            	
            	
            
            
          //加载字典
            function getDictOptions(type,dictType,idOrClass){
            	var optionsHtml="<option value='0'>-- 请选择"+type+" --</option>";
            	$.ajax({
            		url:F.basepath+'/cms/dict/queryDictByCondition',
            		type:"GET",
            		data:{typeEncoding:dictType,status:1},
            		success:function(data){
            			for(var i=0;i<data.value.length;i++){
            				optionsHtml+="<option value='"+data.value[i].id+"'    >"+data.value[i].value+"</option>"
            			}
            			$(idOrClass).append(optionsHtml);
            		}
            	});	
            }
           //获取名师
           function getFamousTeachers(idOrClass){
        	   var optionsHtml="<option value='0'>-- 请选择作者 --</option>";
        	   $.ajax({
           		url:F.basepath+'/cms/dict/famousTeacher',
           		type:"GET",
           		success:function(data){
           			for(var i=0;i<data.value.length;i++){
           				optionsHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].name+"</option>"
           			}
           			$(idOrClass).append(optionsHtml);
           			$(idOrClass).select2();
           		}
           	});	
           }
           
           function getEditFamousTeachers(idOrClass,str){
        	   var optionsHtml="<option value='0'>-- 请选择作者 --</option>";
        	   $.ajax({
           		url:F.basepath+'/cms/dict/famousTeacher',
           		type:"GET",
           		success:function(data){
           			for(var i=0;i<data.value.length;i++){
           				if(str==data.value[i].name){
           					optionsHtml+="<option value='"+data.value[i].id+"' selected='true'>"+data.value[i].name+"</option>"
           				}else{
           					optionsHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].name+"</option>"
           				}
           				
           			}
           			$(idOrClass).append(optionsHtml);
           			$(idOrClass).select2();
           		}
           	});	
        	   
        	   
           }
        	   
        	   
        	   
           
            
            //加载知识点
           function knowledgeOptions(subject,grade,idOrClass){
        	   $(idOrClass).empty();
        	   var html="";
        		$.ajax({
        			url:'/cms/textbook/knowledgeList',
        			type:"get",
        			data:{subjectNo:subject,gradeNo:grade},
        			success:function(data){
        				if(data.value.length<1){
        					html+="<option value='-1'> -- 无 -- <option>";
        				}
        				for(var i=0;i<data.value.length;i++){
        					html+="<option value='"+data.value[i].id+"'>"+data.value[i].title+"</option>"
        				}
        				$(idOrClass).append(html);
        			}
        		})
           }
           //加载编辑字典
           function getEditDictOptions(type,dictType,selectId,value){
           	var optionsHtml="<option value='0'>-- 请选择"+type+" --</option>";
           	$.ajax({
           		url:F.basepath+'/cms/dict/queryDictByCondition',
           		type:"GET",
           		data:{typeEncoding:dictType,status:1},
           		success:function(data){
           			for(var i=0;i<data.value.length;i++){
           				if(data.value[i].id==value){
           					optionsHtml+="<option value='"+data.value[i].id+"' selected='true' >"+data.value[i].value+"</option>"
           				}else{
           					optionsHtml+="<option value='"+data.value[i].id+"'  >"+data.value[i].value+"</option>"
           				}
           			}
           			$(selectId).append(optionsHtml);
           		}
           	});	
           }
            
	        operateEvents = {
	        	//停用或启用目录	
	        	'click .confineCatalog': function(e, value, row, index){
	        		$.ajax({
		        		url:F.basepath+'/cms/catalog/confine',
		        		type:'POST',
		        		data:{id:row.id,status:row.status==1?0:1},
		        		success:function(data){
		        			if(data.result>0){
		        				C.reload();
		        			}else{
		        				alert("异常！ 稍后再试");
		        			}
		        		}
		        	});
            	},
            	 /**
				 * 单个删除目录
				 */
		        'click .delCatalog': function (e, value, row, index) {
		        	base.bootConfirm("是否确定删除？",function(){
		        		var ids = new Array();  
		        		ids.push(row.id);   
		    			C.delCatalogs(ids);
		    		});
		        },
		        
		        /**
		         * 编辑目录信息 开启模态框
		         */
		        'click .editCatalog':function(e, value, row, index){
		        	
		        	core.openModel('modal-addCatalog','编辑目录/章节',function(){
		        	});
		        	 $("#addParent").attr("catalogLevel",row.catalogLevel);
		        	 $("#modal-addCatalog").attr("idTag",row.id);
		        	 $("#addCatalogLevel").empty();
					 $("#catalogBtnSubmit").hide();
					 $("#editCatalogSubmit").show();
					 $("#addCatalogName").val(row.catalogName);
					 $("#catalogLevels").hide();
					 if(row.catalogLevel>51){
					 $("#videoFile").show();
					 }
					 $("#videoFileName").val(row.videoFileName);
					 $("#addIntroduction").val(row.introduction);
					 $("#addOrder").val(row.orderNo);
					 $("#addCatalogName").attr("catalogName",row.catalogName);

					
					
		        },
	        		
            	/**
				 *打开修改模态框
				 */	
		        'click .editTextbook': function (e, value, row, index) {
		        	$("#modal-editTextbook").show();
		        	$("#table").hide();
		        	$("#editKnowledgeTree").show();
		        	$("#editGradeTag").attr('grade',row.gradeNo);
		        	$("#editSubjectTag").attr('subject',row.subjectNo);
		        	$("#modal-editTextbook").attr("idTag",row.id);
		        	$("#editKnowledgeTree").attr("knowledgePointArrTag",row.knowledgePointArr);
		        	getEditFamousTeachers("#editAuthor",row.author);
		        	getEditDictOptions("年级","grade","#editGrade",row.gradeNo);
		        	getEditDictOptions("科目","subject","#editSubject",row.subjectNo);
		        	getEditDictOptions("教材类型","textbookType","#editTextbookType",row.textbookTypeNo);
		        	getEditDictOptions("出版社","publish","#editPublisher",row.publisherNo);
		        	$("#editTitle").val(row.title);
		        	$("#editDigest").val(row.digest);
		        	$("#editImgUrl").val(row.imgUrl);
		        	$("#editAuthor").val(row.author);
		        	$("#editPushPerson").val(row.pushPerson);
		        	core.editloadKnowledgeTree(F.basepath+'/cms/textbook/editKnowledgeTree',row.gradeNo,row.subjectNo,row.knowledgePointArr,"#updateKnowledgeTree");
		        		
		        },
		        /**
		         * 停用或启用
		         */
		        'click .confine':function(e,value,row,index){
		        	$.ajax({
		        		url:F.basepath+'/cms/textbook/confine',
		        		type:'get',
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
				 * 删除教材
				 */
		        'click .delTextbook': function (e, value, row, index) {
		        	base.bootConfirm("是否确定删除？",function(){
		        		var ids = new Array();  
		        		ids.push(row.id);   
		    			F.delTextbook(ids);
		    		});
		        },
		        /**
		         * 查看目录大纲
		         */
		        'click .outline':function(e, value, row, index){
		        	$("#table").hide();
		        	$("#outline").show();
		        	var imgUrl=row.imgUrl.replace("</","<img src='http://ekt.oss-cn-shenzhen.aliyuncs.com/");
		        	var imgUrl2=imgUrl.replace("/>","'>");
		        	$("#cover").append(imgUrl2);
		        	var table="<tr><td colspan='7'><h4>"+row.title+"</h4></td></tr>" +
		        			"<tr>" +
		        				"<td><b>章/排序</b></td>" +
		        				"<td><b>章/名称</b></td>" +
		        				"<td><b>章/描述</b></td>" +
		        				"<td><b>节/排序</b></td>"+
		        				"<td><b>节/名称</b></td>" +
		        				"<td><b>节/描述</b></td>" +
		        				"<td><b>视频文件名</b></td>" +
		        			"</tr>";
		        	$.ajax({
		        		url:F.basepath+'/cms/catalog/outline',
		        		type:'get',
		        		data:{textbookId:row.id},
		        		success:function(data){
		        			if(data.value.length<1){
		        				table+="<td style='color:red'colspan='7' >等待录入目录结构！</td>";
		        			}
		        			for(var i=0;i<data.value.length;i++){
		        				table+="<tr>" +
			        						"<td rowspan='"+data.value[i].sonCatalogs.length+"'>"+data.value[i].order_no+"</td>" +
			        						"<td rowspan='"+data.value[i].sonCatalogs.length+"'>"+data.value[i].catalog_name+"</td>" +
			        						"<td rowspan='"+data.value[i].sonCatalogs.length+"'>"+data.value[i].introduction+"</td>";
		        				if(data.value[i].sonCatalogs.length<1){
		        					 table+="<td style='color:red'>未创建</td>" +
			        						"<td style='color:red'>未创建</td>" +
			        						"<td style='color:red'>未创建</td>" +
			        						"<td style='color:red'>未创建</td>" +
				        				"</tr>";
		        				}else{
		        					table+="<td>"+data.value[i].sonCatalogs[0].order_no+"</td>" +
		        						   "<td>"+data.value[i].sonCatalogs[0].catalog_name+"</td>" +
		        						   "<td>"+data.value[i].sonCatalogs[0].introduction+"</td>" +
		        						   "<td>"+data.value[i].sonCatalogs[0].video_file_name+"</td>" +
		        						"</tr>";
		        								
		        							}
		        						
		        					var sonCatalogs = data.value[i].sonCatalogs;
		        					if(sonCatalogs.length>0){
		        						
		        				
			        					for(var j=1;j<sonCatalogs.length;j++){
			        						table+="<tr>"+
					        							"<td>"+sonCatalogs[j].order_no+"</td>"+
					        							"<td>"+sonCatalogs[j].catalog_name+"</td>"+
					        							"<td>"+data.value[i].sonCatalogs[0].introduction+"</td>" +
					        							"<td>"+sonCatalogs[j].video_file_name+"</td>"+
				        							"</tr>"
			        					}
		        					}
		        			}
		        			$("#outlineTable").append(table);
		        			
		        		}
		        		

		        	});
		        },
		        /**
				 * 加载目录结构
				 */
		        'click .category': function (e, value, row, index) {
//		        	隐藏教材
		        	$("#table").hide();
		        	$("#table2").attr("catalogIdTag",row.id);
		        	$('.page-header h1').find('span').text(row.title);
//		        	C.catalogTable.reload();
		        	 var cols = [
		 	                    {
		 	        		        checkbox:true
		 	        		    }, {
		 	        		        field: 'catalogName',
		 	        		        title: '章/节 -名称'
		 	        		    },{
		 	        		        field: 'levelName',
		 	        		        title: '章/节'
		 	        		    },{
		 	        		        field: 'introduction',
		 	        		        title: '简介'
		 	        		    }];
		        	
		 	        //是否需要操作列
		 	        if(base.perList.textbook.editCatalog||
		 	           base.perList.textbook.delCatalog)
		 		        cols.push({
		 			    	align: 'center',
		 			        title: '操作',
		 			        events: operateEvents,
		 			        formatter:C.operateFormatter
		 			    });
		 	        //解决回调刷新问题
		 	       $("#catalogTable").bootstrapTable('refresh',{url:F.basepath+'/cms/catalog/pageList?textbookId='+row.id});
		 	       //加载目录列表
		     		C.catalogTable.init(F.basepath+'/cms/catalog/pageList?textbookId='+row.id,cols);   	
			        $("#table2").show();
	
	        	}

		    };
	        
	        var cols = [
	                    {
	        		        checkbox:true
	        		    },{
	        		        field: 'title',
	        		        title: '名称'
	        		    },{
	        		        field: 'digest',
	        		        title: '摘要'
	        		    },{
	        		        field: 'author',
	        		        title: '作者'
	        		    },{
	        		        field: 'pushPerson',
	        		        title: '录入人'
	        		    }];
	        //是否需要操作列
	        		if(base.perList.textbook.outline||
		 	           base.perList.textbook.confine||
		 	           base.perList.textbook.edit||
		 	           base.perList.textbook.del||
		 	           base.perList.textbook.catalog)
		        cols.push({
			    	align: 'center',
			        title: '操作',
			        events: operateEvents,
			        formatter:F.operateFormatter
			    });
        		
    		F.table.init(F.basepath+'/cms/textbook/pageList',cols);
    		
    		/**
    		 * 带参查询教材
    		 */
    		$('.actions #query').click(function(){
    			var title=$("#name").val();
    			var grade=$("#grade").val();
    			var subject=$("#subject").val();
    			var url= F.basepath+'/cms/textbook/pageList?title='+title+'&gradeNo='+grade+"&subjectNo="+subject;
				$("#textbookTable").bootstrapTable('refresh',{url:url});	
    		});
    		
    		/**
    		 * 带参查询目录
    		 */
    		$("#catalogQuery").click(function(){
    			var catalogName = $("#catalogName").val();
    			var catalogLevel = $("#catalogLevel").val();
    			var parentId = $("#parentOptions").val()==null?0:$("#parentOptions").val();
    			var textbookId = $("#table2").attr("catalogIdTag");
    			var url= F.basepath+'/cms/catalog/pageList?textbookId='+textbookId+'&catalogName='+catalogName+'&catalogLevel='+catalogLevel+'&parentId='+parentId;
    			$("#catalogTable").bootstrapTable('refresh',{url:url});	
    		})
    		
    		//导出目录大纲excel
			$("#export").click(function(){
				 $('#outlineTable').tableExport({ type: 'excel', separator: ';', escape: 'false' });		 
			});
			    		
    		
			/**
			 * 显示新增框
			 */
			$('#addTextbook').click(function(){
				$("#table").hide();
				$("#modal-addTextbook").show();
				getDictOptions("年级","grade","#addGrade");
				getDictOptions("科目","subject","#addSubject");
				getDictOptions("教材类型","textbookType","#addTextbookType");
				getDictOptions("出版社","publish","#addPublisher");
				getFamousTeachers("#author");
				
			});
			//监听科目的选择
			$("#addSubject").change(function(){
				var grade = $("#addGrade").val();
				var subject = $("#addSubject").val();
				if(grade!=0&&subject!=0){
					core.loadKnowledgeTree(F.basepath+'/cms/textbook/knowledgeTree',subject,grade,"#knowledgeTree");
					$("#addKnowledgeTree").show();
				}
				
			});
			//监听年级的选择
			$("#addGrade").change(function(){
				var grade = $("#addGrade").val();
				var subject = $("#addSubject").val();
				if(grade!=0&&subject!=0){
					core.loadKnowledgeTree(F.basepath+'/cms/textbook/knowledgeTree',subject,grade,"#knowledgeTree");
					$("#addKnowledgeTree").show();
				}
				
			});
			//编辑时监听科目的选择
			$("#editSubject").change(function(){
				var grade = $("#editGrade").val();
				var subject = $("#editSubject").val();
				var gradeTag=$("#editGradeTag").attr("grade");
				var subjectTag=$("#editSubjectTag").attr("subject");
				var knowledgeTree =$("#editKnowledgeTree").attr("knowledgePointArrTag");
				if(gradeTag==grade&&subjectTag==subject){
					core.editloadKnowledgeTree(F.basepath+'/cms/textbook/editKnowledgeTree',
							grade,subject,knowledgeTree,"#updateKnowledgeTree");
					return ;
				}
				if(grade!=0&&subject!=0){
					core.loadKnowledgeTree(F.basepath+'/cms/textbook/knowledgeTree',subject,grade,"#updateKnowledgeTree");
				}
				
			});
			//监听年级的选择
			$("#editGrade").change(function(){
				var grade = $("#editGrade").val();
				var subject = $("#editSubject").val();
				var gradeTag=$("#editGradeTag").attr("grade");
				var subjectTag=$("#editSubjectTag").attr("subject");
				var knowledgeTree =$("#editKnowledgeTree").attr("knowledgePointArrTag");
				if(gradeTag==grade&&subjectTag==subject){
					core.editloadKnowledgeTree(F.basepath+'/cms/textbook/editKnowledgeTree',
							grade,subject,knowledgeTree,"#updateKnowledgeTree");
					return ;
				}
				if(grade!=0&&subject!=0){
					core.loadKnowledgeTree(F.basepath+'/cms/textbook/knowledgeTree',subject,grade,"#updateKnowledgeTree");
					
				}
				
			});
			
			//监听新增章节输入视频文件名光标移出事件
			$("#videoFileName").blur(function(){
				var videoFileName=$("#videoFileName").val();
				var validationInfo = "";
				$.ajax({
					url:F.basepath+'/cms/catalog/validationVideo',
	        		type:'post',
	        		data:{videoFileName:videoFileName},
	        		success:function(data){
	        			if(data.value.length==0){
	        				validationInfo+="无使用记录！";
	        				
	        			}else{
	        				validationInfo+="该video在"
		        			for(var i=0;i<data.value.length;i++){
		        				if(i!=0){validationInfo+="， "}
		        				validationInfo+="《"+data.value[i].textbookName
								+"》教材的《"+data.value[i].catalogParent
								+"》章《"+data.value[i].catalogName+"》节";
		        			}
	        				validationInfo+="中出现过。"
	        			}
	        			$("#videoFileName-error").text(validationInfo);
	        			$("#videoFileName-error").css("color","blue");
	        		}
				});
				
			});
			
			//监听目录选择
			$("#catalogLevel").change(function(){
				var catalogLevel = $("#catalogLevel").val();
				if(catalogLevel!=0&&catalogLevel!=51){
				$("#parentOptions").show();
				var id = $("#table2").attr("catalogIdTag");
		        	 var optionHtml='';
	        		 $.ajax({
							url:F.basepath+'/cms/catalog/parentList',
			        		type:'post',
			        		data:{levelNo:51,textbookId:id},
			        		success:function(data){
			        			if(data.value.length<1){
			        				optionHtml+="<option value='-1'>暂无父级可选,请预先添加父级</option>";
			        			}
			        			for(var i=0;i<data.value.length;i++){
			        				if(i==0){
			        					optionHtml+="<option value='0'> --请选择父级-- </option>"
			        				}
			        				optionHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].catalog_name+"</option>";
			        			}
			        			$("#parentOptions").append(optionHtml);
			        		}
						}) 
				}else{
					$("#parentOptions").hide();
					$("#parentOptions").empty();
				}
			});
			//关闭目录页面
			$("#catalogClose").click(function(){
				$('.page-header h1').find('span').text("教材");
				$("#catalogName").val('');
				$("#catalogLevel").val(0);
				$("#table").show();
				$("#table2").hide();
				
			});
			//开启添加目录模态框
			$("#addCatalog").click(function(){
				core.openModel('modal-addCatalog','添加章/节');
				 getDictOptions("目录级别","catalogLevel","#addCatalogLevel");
				 $("#catalogLevels").show();
			});
			//关闭查看目录章节结构
			$("#outlineClose").click(function(){
				$("#outline").hide();
				$("#table").show();
				$("#cover").empty();
				$("#outlineTable").empty();
				$("#outlineTable h4").text('');
			});
			//关闭添加目录模态框
			$("#catalogBtnClose").click(function(){
				catalogClear();
			});
			//编辑目录提交
			$("#editCatalogSubmit").click(function(){
				var catalogLevel = $("#addParent").attr("catalogLevel");
				var id = $("#modal-addCatalog").attr("idTag");
				var textbookId = $("#table2").attr("catalogIdTag");
				var catalogName = $("#addCatalogName").val();
				var introduction = $("#addIntroduction").val();
				var oldCatalogName = $("#addCatalogName").attr("catalogName");
				var videoFileName = $("#videoFileName").val();
				var order = $("#addOrder").val();
				if(catalogName.length<1){$("#addCatalogName-error").text("章/节 名称不能为空！");return;};
				if(order.length<1){$("#addOrder-error").text("排序不能为空！");return;};
				if(catalogLevel>51&&videoFileName.length<1){$("#videoFileName-error").text("视频名称不能为空！");return;};
				var datas={
						id:id,
						catalogName:catalogName,
						introduction:introduction,
						orderNo:order,
						oldCatalogName:oldCatalogName,
						textbookId:textbookId,
						videoFileName:videoFileName
				};
				$.ajax({
					url:F.basepath+'/cms/catalog/update',
	        		type:'post',
	        		data:datas,
	        		success:function(data){
	        			if(data.result==-1){
	        				$("#addCatalogName-error").text(data.msg);
	        				$("#addCatalogName").val('');
	        			}
	        			else if(data.result>0){
	        				C.catalogTable.reload();
	        				catalogClear();
	        			}else{
	        				catalogClear();
	        				alert("异常！");
	        			}
	        		}	
				});
			})
			
			/**
			 * 关闭新增框
			 */
			$('#btnClose').click(function(){
				clear();
			});
			
			$('#editBtnClose').click(function(){
				core.closeModel('modal-editPerm');
				editClear();
			});
			
			$("#editCatalogBtnClose").click(function(){
				core.closeModel('modal-editCatalogContent');
			});
			//清理器
			function clear(){
				$('#modal-addTextbook').hide();
				$("#table").show();
				$("#addGrade").empty();
				$("#addSubject").empty();
				$("#addTextbookType").empty();
				$("#addPublisher").empty();
				$("#title").val("");
				$("#digest").val("");
				$("#imgUrl").val("");
				$("#author").empty();
				$("#pushPerson").val("");
				$("#knowledgeTree").empty();
				$("#addKnowledgeTree").hide();
				$("#addMsg").text("");
				$("#imgFile").val('');
			}
			
			function editClear(){
				$('#modal-editTextbook').hide();
				$("#table").show();
				$("#editGrade").empty();
				$("#editSubject").empty();
				$("#editTextbookType").empty();
				$("#editPublisher").empty();
				$("#editTitle").val("");
				$("#editDigest").val("");
				$("#editImgUrl").val("");
				$("#editAuthor").empty();
				$("#editPushPerson").val("");
				$("#editMsg").text("");
			}
			
			function catalogClear(){
				core.closeModel('modal-addCatalog');
				$("#addCatalogName").val('');
				$("#addCatalogLevel").val(0);
				$("#addParentId").val('');
				$("#addIntroduction").val('');
				$("#addOrder").val('');
				$("#addParent").hide();
				$("#addCatalogName-error").text('');
				$("#addCatalogLevel-error").text('');
				$("#addOrder-error").text('');
				$("#addParentId-error").text('');
				$("#addCatalogLevel").empty();
				$("#addCatalogName").removeAttr("catalogName");
				$("#editCatalogSubmit").hide();
				$("#catalogBtnSubmit").show();
				$("#videoFile").hide();
				$("#videoFileName").val('');
				$("#videoFileName-error").text('');
			};
			
			/**
			 * 提交新增目录
			 */
			$("#catalogBtnSubmit").click(function(){
				var catalogIdTag = $("#table2").attr("catalogIdTag");
				var catalogName = $("#addCatalogName").val();
				var catalogLevel = $("#addCatalogLevel").val();
				var parentId =	$("#addParentId").val();
				var introduction = $("#addIntroduction").val();
				var videoFileName = $("#videoFileName").val();
				var order = $("#addOrder").val();
				if(catalogName.length<1){$("#addCatalogName-error").text("章/节 名称不能为空！");return;};
				if(catalogLevel==0){$("#addCatalogLevel-error").text("目录级别为必选项！");return;};
				if(catalogLevel!=0&&catalogLevel!=51&&parentId.length<1){$("#addParentId-error").text("父级Id不能为空！");return;};
				if(catalogLevel!=0&&catalogLevel!=51&&videoFileName.length<1){$("#videoFileName-error").text("视频文件名不能为空！");return;};
				if(parentId==-1){$("#addParentId-error").text("请先添加父级 ！");return;}
				if(order.length<1){$("#addOrder-error").text("排序不能为空 ！");return;};
				var datas={
						textbookId:catalogIdTag,
						catalogName:catalogName,
						catalogLevel:catalogLevel,
						parentId:catalogLevel==51?0:parentId,
						introduction:introduction,
						orderNo:order,
						videoFileName:videoFileName
				};
				
				$.ajax({
					url:F.basepath+'/cms/catalog/add',
	        		type:'post',
	        		data:datas,
	        		success:function(data){
	        			if(data.result==-1){
	        				$("#addCatalogName-error").text(data.msg);
	        				$("#addCatalogName").val('');
	        			}
	        			else if(data.result>0){
	        				if(confirm("添加成功，是否继续添加？")){
	        					$("#addCatalogName").val('');
	        					$("#addIntroduction").val('');
	        					$("#addOrder").val('');
	        					$("#videoFileName").val('');
	        					C.catalogTable.reload();
	        				}else{
	        				C.catalogTable.reload();
	        				catalogClear();
	        				}
	        			}else{
	        				catalogClear();
	        				alert("异常！");
	        			}
	        		}	
				});
			});
			
			/**
			 * 删除目录
			 */
			$('#delCatalog').click(function(){
				var ids = C.catalogTable.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"章/节？此操作将会删除您选定的章/节中的所有内容！",function(){
						C.delCatalogs(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的章！"});
				}
			});
			/**
			 * 监听目录选择
			 */
			$("#addCatalogLevel").change(function(){
				var catalogLevel = $("#addCatalogLevel").val();
				var id =$("#table2").attr("catalogIdTag");
				$("#addParentId").empty();
				if(catalogLevel!=0&&catalogLevel!=51){
					$("#addParent").show(); 
					 $("#videoFile").show();
					var optionHtml='';
					$.ajax({
						url:F.basepath+'/cms/catalog/parentList',
		        		type:'post',
		        		data:{levelNo:catalogLevel-1,textbookId:id},
		        		success:function(data){
		        			if(data.value.length<1){
		        				optionHtml+="<option value='-1'>暂无父级可选,请预先添加父级</option>";
		        			}
		        			for(var i=0;i<data.value.length;i++){
		        				optionHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].catalog_name+"</option>";
		        			}
		        			$("#addParentId").append(optionHtml);
		        		}
					}) 
				}else{
					$("#addParent").hide();
					 $("#videoFile").hide();
				}
			});
			
			
			/**
			 * 提交新增教材
			 */
			$('#btnSubmit').click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("knowledgeTree");				
				var arrId='';
				var arrName='';
			if(treeObj!=null){
				var nodes = treeObj.transformToArray(treeObj.getNodes()); 
			
				for (var i = 0; i < nodes.length; i++) { 
					if(nodes[i].checked==true){
						if(nodes[i].id==0){
							continue;
						}
						arrId+=nodes[i].id+',';
						arrName+=nodes[i].name+',';
					}
				}
			}
			
				var gradeNo =$("#addGrade").val();
				var subjectNo=$("#addSubject").val();
				var textbookTypeNo = $("#addTextbookType").val();
				var publisherNo = $("#addPublisher").val();
				var title = $("#title").val();
				var digest = $("#digest").val();
				var imgUrl = $("#imgUrl").val();
				var author = $("#author").val();
				var pushPerson = $("#pushPerson").val();
				var phaseNo = gradeNo==19?61:gradeNo==20?61:gradeNo==21?61:60;
				if(gradeNo==0){$("#addMsg").text("请选择年级！");return}
				if(subjectNo==0){$("#addMsg").text("请选择科目！");return}
				if(textbookTypeNo==0){$("#addMsg").text("请选择教材类型！");return}
				if(publisherNo==0){$("#addMsg").text("请选择出版社！");return}
				if(title.length<1){$("#addMsg").text("标题不能为空！");return}
				if(digest.lengt<1){$("#addMsg").text("摘要不能为空！");return}
				if(imgUrl.length<1){$("#addMsg").text("图片不能为空！");return}
				if(pushPerson.length<1){$("#addMsg").html("录入人不能为空！");return}
				var datas ={
							phaseNo:phaseNo,
							gradeNo:gradeNo,
							subjectNo:subjectNo,
							textbookTypeNo:textbookTypeNo,
							publisherNo:publisherNo,
							title:title,
							digest:digest,
							imgUrl:imgUrl,
							authorId:author,
							pushPerson:pushPerson,
							knowledgePointArr:arrId,
							knowledgePointArrVal:arrName
															}
				
				$.ajax({
	        		url:F.basepath+'/cms/textbook/add',
	        		type:'post',
	        		data:datas,
	        		success:function(data){
	        			if(data.result>0){
	        				F.reload();
	        				clear();
	        			}
	        		}
	        	})
				
				
			});
			/**
			 * 修改提交教材
			 */
			$("#editBtnSubmit").click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("updateKnowledgeTree");				
				var arrId='';
				var arrName='';
				if(treeObj!=null){
					var nodes = treeObj.transformToArray(treeObj.getNodes()); 
					for (var i = 0; i < nodes.length; i++) { 
						if(nodes[i].checked==true){
							if(nodes[i].id==0){
								continue;
							}
							arrId+=nodes[i].id+',';
							arrName+=nodes[i].name+',';
						}
					}
				}
				var id = $("#modal-editTextbook").attr("idTag");
				var gradeNo =$("#editGrade").val();
				var phaseNo = gradeNo==19?61:gradeNo==20?61:gradeNo==21?61:60;
				var subjectNo=$("#editSubject").val();
				var textbookTypeNo = $("#editTextbookType").val();
				var publisherNo = $("#editPublisher").val();
				var title = $("#editTitle").val();
				var digest = $("#editDigest").val();
				var imgUrl = $("#editImgUrl").val();
				var author = $("#editAuthor").val();
				var pushPerson = $("#editPushPerson").val();
				if(gradeNo==0){$("#editMsg").text("请选择年级！");return}
				if(subjectNo==0){$("#editMsg").text("请选择科目！");return}
				if(textbookTypeNo==0){$("#editMsg").text("请选择教材类型！");return}
				if(publisherNo==0){$("#editMsg").text("请选择出版社！");return}
				if(title.length<1){$("#editMsg").text("标题不能为空！");return}
				if(digest.lengt<1){$("#editMsg").text("摘要不能为空！");return}
				if(imgUrl.length<1){$("#editMsg").text("图片不能为空！");return}
				if(pushPerson.length<1){$("#editMsg").html("录入人不能为空！");return}
				var datas ={
						id:id,
						phaseNo:phaseNo,
						gradeNo:gradeNo,
						subjectNo:subjectNo,
						textbookTypeNo:textbookTypeNo,
						publisherNo:publisherNo,
						title:title,
						digest:digest,
						imgUrl:imgUrl,
						authorId:author,
						pushPerson:pushPerson,
						knowledgePointArr:arrId,
						knowledgePointArrVal:arrName
											};
				
				$.ajax({
					url:F.basepath+'/cms/textbook/update',
					type:"POST",
					data:datas,
					success:function(data){
						if(data.result>0){
							F.reload();
							editClear();
						}
					}
				});
				
			});
			/**
			 * 批量删除教材
			 */
			$('#delTextbooks').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"本教材？此操作将会删除您所选定的教材中的所有内容！",function(){
						F.delTextbook(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的教材！"});
				}
			});
			
			
			
			//删除教材教材
        },delTextbook:function(ids){
        	$.ajax({
        		url:F.basepath+'/cms/textbook/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        				F.reload();
        			}
        		}
        	})
        //刷新表单
        },reload:function(){
        	F.table.reload();
        	
        }
        ,
        operateFormatter:function (value, row, index) {
        	var _btnAction = "";
        	
        	if (base.perList.textbook.outline) {
            	_btnAction += "<a class='outline btn btn-primary btn-small' href='#' title='目录大纲' style='margin-left:5px'>目录大纲</a>";
            	}
        	if (base.perList.textbook.confine) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
        	}
        	if (base.perList.textbook.edit) {
        		_btnAction += "<a data-toggle='modal' class='editTextbook btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.textbook.del) {
        		_btnAction += "<a class='delTextbook btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>";
        	}
        	if(base.perList.textbook.catalog){
        		_btnAction += "<a class='category btn btn-primary btn-small' href='#' title='编辑目录章节' style='margin-left:5px'>编辑目录章节</a>";
        	}
        		return _btnAction;
        } 
        
    };
    jQuery(document).ready(function() { 
	//上传表单验证和提交 新增
	//图片上传到阿里云OSS 
	$(function() {
		$("#upload").ajaxForm({
			//图片上传的文件夹
			
			data :
			{key:"textBook/",
			fileName:"imgFile"},
			beforeSend : function() {
			
			$("#submitbutton").attr("disabled","disalbed");
			},
			success : function(data) {
				$("#submitbutton").attr("disabled",false);
				//提交成功后调用
				if (data.value!=null) {
					alert(data.value.msg);
					if(data.value.status == 0){
						var file = $("#imgFile").val();
						var fileName = "</textBook/" + getFileName(file)+"/>";
						var imgUrl = $("#imgUrl").val(fileName);
					}
				} else {
					alert("上传异常 ")
				}
			}
		});
	});
	
	//上传表单验证和提交  编辑
	//图片上传到阿里云OSS
	$(function() {
		$("#editUpload").ajaxForm({
			//图片上传的文件夹
			
			data :
			{key:"textBook/",
			 fileName:"editImgFile"},
			beforeSend : function() {
			
			$("#editSubmitbutton").attr("disabled","disalbed");
			},
			success : function(data) {
				$("#editSubmitbutton").attr("disabled",false);
				//提交成功后调用
				if (data.value!=null) {
					alert(data.value.msg);
					if(data.value.status == 0){
						var file = $("#editImgFile").val();
						var fileName = "</textBook/" + getFileName(file)+"/>";
						var editImgUrl = $("#editImgUrl").val(fileName);
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
});
