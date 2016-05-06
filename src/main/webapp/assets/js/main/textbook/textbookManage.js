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
	        	if (base.perList.permission.confine) {
	        	_btnAction += "<a class='confineCatalog btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
	        	}
	        	if (base.perList.permission.edit) {
	        		_btnAction += "<a data-toggle='modal' class='editCatalog btn btn-success btn-small' href='#' title='编辑' style='margin-left:5px'>编辑</a>";
	        	}
	        	if (base.perList.permission.del) {
	        		_btnAction += "<a class='delCatalog btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>";
	        	}
	        		_btnAction += "<a class='knowledgeCategory btn btn-primary btn-small' href='#' title='查看目录' style='margin-left:5px'>知识点目录</a>";
	        	return _btnAction;
	        } 
	       
	    };
    
    var F = module.exports = {
        basepath: '',
        table:new core.Table('textbookTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
          	
            /**
             * 是否具有添加权限权限
             */
//            if(base.perList.permission.check){
            	$("#perm-header .actions").append("<input autocomplete='off'  id='name'  placeholder='请输入教材名称' type='text' />&nbsp;&nbsp;" +
				"<select id='grade'></select>&nbsp;&nbsp;" +
				"<select id='subject'></select>&nbsp;&nbsp;" +
				"<a href='#' id='query' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
            	getDictOptions("年级","grade","#grade");
            	getDictOptions("科目","subject","#subject");
            	
            	
//            }	
            //加载可选类型列表00        	
//            if(base.perList.permission.create){
            	$("#perm-header .actions").append("<a href='#' id='addTextbook' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
//            }
            /**
             * 是否具有删除权限权限
             */
//            if(base.perList.permission.del){
            	$("#perm-header .actions").append("<a href='#' id='delTextbooks' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
//            }
            	
            	
//	          if(base.perList.permission.edit){
	        	$("#catalog-header .action").append("<input autocomplete='off' id=catalogName    placeholder='请输入名称' type='text' />&nbsp;&nbsp;" +
	    				"<select id='catalogLevel'></select>&nbsp;&nbsp;" +
		    			"<a href='javascript:void(0)' id='catalogQuery' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
		        		 getDictOptions("目录级别","catalogLevel","#catalogLevel");
//	        	 }
//		      if(base.perList.permission.del){
		        $("#catalog-header .action").append("<a href='#' id='delCatalog' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
//
//		      if(base.perList.permission.create){
		        $("#catalog-header .action").append("<a href='#' id='addCatalog' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
//		       }
		        $("#catalog-header .action").append("<button class='close' type='button' id='catalogClose' >x</button>");
            	
            	
            	
            
            
          //加载字典
            function getDictOptions(type,dictType,selectId){
            	var optionsHtml="<option value='0'>-- 请选择"+type+" --</option>";
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
           		data:{typeEncoding:dictType},
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
		        	
		        	core.openModel('modal-addCatalog','添加目录/章节',function(){
		        	});
		        	 $("#modal-addCatalog").attr("idTag",row.id);
		        	 $("#addCatalogLevel").empty();
					 $("#catalogBtnSubmit").hide();
					 $("#editCatalogSubmit").show();
					 $("#addCatalogName").val(row.catalogName);
					 getEditDictOptions("目录级别","catalogLevel","#addCatalogLevel",row.catalogLevel);
					 $("#addIntroduction").val(row.introduction);
					 $("#addOrder").val(row.orderNo);
					 if(row.catalogLevel!=51){
						 $("#addParent").show();
						 $("#addParentId").val(row.parentId);
					 }
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
				 * 加载目录结构
				 */
		        'click .category': function (e, value, row, index) {
//		        	隐藏教材
		        	$("#table").hide();
		        	$("#table2").attr("catalogIdTag",row.id);
//		        	C.catalogTable.reload();
		        	 var cols = [
		 	                    {
		 	        		        checkbox:true
		 	        		    }, {
		 	        		        field: 'id',
		 	        		        title: '主键'
		 	        		    }, {
		 	        		        field: 'catalogName',
		 	        		        title: '目录/章节 -名称'
		 	        		    },{
		 	        		        field: 'levelName',
		 	        		        title: '目录级别'
		 	        		    },{
		 	        		        field: 'introduction',
		 	        		        title: '简介'
		 	        		    }];
		 	        //是否需要操作列
		 	        if(base.perList.permission.edit||base.perList.permission.del||base.perList.permission.confine)
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
	        		    }, {
	        		        field: 'id',
	        		        title: '主键'
	        		    }, {
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
	        if(base.perList.permission.edit||base.perList.permission.del||base.perList.permission.confine)
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
    			var textbookId = $("#table2").attr("catalogIdTag");
    			var url= F.basepath+'/cms/catalog/pageList?textbookId='+textbookId+'&catalogName='+catalogName+'&catalogLevel='+catalogLevel;
    			$("#catalogTable").bootstrapTable('refresh',{url:url});	
    		})
    		
    		
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
			//关闭目录页面
			$("#catalogClose").click(function(){
				$("#catalogName").val('');
				$("#catalogLevel").val(0);
				$("#table").show();
				$("#table2").hide();
			});
			//开启添加目录模态框
			$("#addCatalog").click(function(){
				core.openModel('modal-addCatalog','添加目录/章节');
				 getDictOptions("目录级别","catalogLevel","#addCatalogLevel");
			});
			
			//关闭添加目录模态框
			$("#catalogBtnClose").click(function(){
				catalogClear();
			});
			//编辑目录提交
			$("#editCatalogSubmit").click(function(){
				var id = $("#modal-addCatalog").attr("idTag");
				var catalogName = $("#addCatalogName").val();
				var catalogLevel = $("#addCatalogLevel").val();
				var parentId =	$("#addParentId").val();
				var introduction = $("#addIntroduction").val();
				var order = $("#addOrder").val();
				if(catalogName.length<1){$("#addCatalogName-error").text("目录/章节 名称不能为空！");return;};
				if(catalogLevel==0){$("#addCatalogLevel-error").text("目录级别为必选项！");return;};
				if(catalogLevel!=0&&catalogLevel!=51&&parentId.length<1){$("#addParentId-error").text("父级Id不能为空！");return;};
				if(order.length<1){$("#addOrder-error").text("排序不能为空！");return;};
				var datas={
						id:id,
						catalogName:catalogName,
						catalogLevel:catalogLevel,
						parentId:catalogLevel==51?0:parentId,
						introduction:introduction,
						orderNo:order
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
				editClear();
			});
			
			$('#editBtnClose').click(function(){
				core.closeModel('modal-editPerm');
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
				$("#author").val("");
				$("#pushPerson").val("");
				$("#knowledgeTree").empty();
				$("#addKnowledgeTree").hide();
				$("#addMsg").text("");
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
				$("#editAuthor").val("");
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
				var order = $("#addOrder").val();
//				alert(catalogName+"----"+catalogLevel+"------"+parentId+"----"+introduction+"------"+order);
				if(catalogName.length<1){$("#addCatalogName-error").text("目录/章节 名称不能为空！");return;};
				if(catalogLevel==0){$("#addCatalogLevel-error").text("目录级别为必选项！");return;};
				if(catalogLevel!=0&&catalogLevel!=51&&parentId.length<1){$("#addParentId-error").text("父级Id不能为空！");return;};
				if(order.length<1){$("#addOrder-error").text("排序不能为空！");return;};
				var datas={
						textbookId:catalogIdTag,
						catalogName:catalogName,
						catalogLevel:catalogLevel,
						parentId:catalogLevel==51?0:parentId,
						introduction:introduction,
						orderNo:order
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
	        				C.catalogTable.reload();
	        				catalogClear();
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
					base.bootConfirm("是否确定删除选定的"+ids.length+"个目录？",function(){
						C.delCatalogs(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的权限！"});
				}
			});
			/**
			 * 监听目录选择
			 */
			$("#addCatalogLevel").change(function(){
				var catalogLevel = $("#addCatalogLevel").val();
				if(catalogLevel!=0&&catalogLevel!=51){
					$("#addParent").show();
				}else{
					$("#addParent").hide();
				}
			});
			

			
			/**
			 * 提交新增教材
			 */
			$('#btnSubmit').click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("knowledgeTree");				
				var arr=''
			if(treeObj!=null){
				var nodes = treeObj.transformToArray(treeObj.getNodes()); 
			
				for (var i = 0; i < nodes.length; i++) { 
					if(nodes[i].checked==true){
						if(nodes[i].id==0){
							continue;
						}
						arr+=nodes[i].id+',';
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
				if(gradeNo==0){$("#addMsg").text("请选择年级！");return}
				if(subjectNo==0){$("#addMsg").text("请选择科目！");return}
				if(textbookTypeNo==0){$("#addMsg").text("请选择教材类型！");return}
				if(publisherNo==0){$("#addMsg").text("请选择出版社！");return}
				if(title.length<1){$("#addMsg").text("标题不能为空！");return}
				if(digest.lengt<1){$("#addMsg").text("摘要不能为空！");return}
				if(imgUrl.length<1){$("#addMsg").text("图片不能为空！");return}
				if(author.length<1){$("#addMsg").text("作者不能为空！");return}
				if(pushPerson.length<1){$("#addMsg").html("录入人不能为空！");return}
				
				
				
				
				var datas ={
							gradeNo:gradeNo,
							subjectNo:subjectNo,
							textbookTypeNo:textbookTypeNo,
							publisherNo:publisherNo,
							title:title,
							digest:digest,
							imgUrl:imgUrl,
							author:author,
							pushPerson:pushPerson,
							knowledgePointArr:arr
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
				var arr=''
				if(treeObj!=null){
					var nodes = treeObj.transformToArray(treeObj.getNodes()); 
					for (var i = 0; i < nodes.length; i++) { 
						if(nodes[i].checked==true){
							if(nodes[i].id==0){
								continue;
							}
							arr+=nodes[i].id+',';
						}
					}
				}
				var id = $("#modal-editTextbook").attr("idTag");
				var gradeNo =$("#editGrade").val();
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
				if(author.length<1){$("#editMsg").text("作者不能为空！");return}
				if(pushPerson.length<1){$("#editMsg").html("录入人不能为空！");return}
				var datas ={
						id:id,
						gradeNo:gradeNo,
						subjectNo:subjectNo,
						textbookTypeNo:textbookTypeNo,
						publisherNo:publisherNo,
						title:title,
						digest:digest,
						imgUrl:imgUrl,
						author:author,
						pushPerson:pushPerson,
						knowledgePointArr:arr
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
					base.bootConfirm("是否确定删除选定的"+ids.length+"本教材？",function(){
						F.delTextbook(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的权限！"});
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
        	if (base.perList.permission.confine) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
        	}
        	if (base.perList.permission.edit) {
        		_btnAction += "<a data-toggle='modal' class='editTextbook btn btn-success btn-small' href='#' title='编辑权限' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.permission.del) {
        		_btnAction += "<a class='delTextbook btn btn-danger btn-small' href='#' title='删除' style='margin-left:5px'>删除</a>";
        	}
        		_btnAction += "<a class='category btn btn-primary btn-small' href='#' title='查看目录' style='margin-left:5px'>查看目录</a>";
        	return _btnAction;
        } 
    };
    
    
});
