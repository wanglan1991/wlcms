define(function (require, exports, module) {

	var base = require('base');
	var E = module.exports = {
		/**
		 * TREE
		 */
		CoreTree:function(_treeId,_url,_setting){
			this.treeId = _treeId;
			this.url = _url;
        	this.load=function(_data,_hiddenId){}
        	return this;
        },
        
        //公共字典opitons
        getDictOptions:function(type,dictType,selectId){
        	$(selectId).empty();
        	var optionsHtml="<option value='0'>-- "+type+" --</option>";
        	$.ajax({
        		url:'/cms/dict/queryDictByCondition',
        		type:"GET",
        		data:{typeEncoding:dictType},
        		success:function(data){
        			for(var i=0;i<data.value.length;i++){
        				optionsHtml+="<option value='"+data.value[i].id+"'>"+data.value[i].value+"</option>"
        			}
        			$(selectId).append(optionsHtml);
        		}
        	});	
        },
        //地域字典options
        getRegionList:function(idOrClass,parentCode){
			var html="";
			$.ajax({
				url :'/cms/region/getRegionList',
				type : 'POST',
				data : {parentCode:parentCode},
				success: function(data){
					for(var i=0;i<data.value.length;i++){
						html+="<option value='"+data.value[i].area_code+"'>"+data.value[i].area_name+"</option>"
					}
					$(idOrClass).append(html);
				} 
			})
			
		},
		//学校字典options
		getSchoolList:function(idOrClass,cityCode){
			var html=""; 
			$.ajax({
				url:'/cms/school/schoolList',
				type:'GET',
				data:{cityCode:cityCode},
				success:function(data){
					if(data.value.length<1){
						html+="<option value='-1'>该地区学校还暂未录入！</option>"
						$(idOrClass).css('color','red');
					}
					for(var i=0;i<data.value.length;i++){
						html+="<option value='"+data.value[i].id+"'>"+data.value[i].school_name+"</option>";
						$(idOrClass).css('color','#3c3c3c');
					}
					$(idOrClass).append(html);
				}
			})
			
		},
        
        
        /**
         * 加载权限树
         */
         loadTree:function(_url,_roleId){          	 
        	 var setting = {
     				check: {
     					enable: true,
     					chkboxType:{ "Y" : "ps", "N" : "ps" }
     				},
     				view: {
     					dblClickExpand: false
     				},
     				data: {
     					simpleData: {
     						enable: true
     					}
     				}
     			};
			        	  $.ajax({
			         		 url:_url,
			         		 type:'GET',
			         		 data:{roleId:_roleId},
			         		 success:function(data){
			         			 $.fn.zTree.init($("#distributePermissionTree"), setting, data.value)
			         		 }
			         	 })
         },
         	/**
         	 * 加载知识点树
         	 */
	         loadKnowledgeTree:function(_url,subjectNo,gradeNo,idOrClass){   
//	        	 alert(_url+"-----"+subjectNo+"-----"+gradeNo+"----"+idOrClass);
	        	 var setting = {
	     				check: {
	     					enable: true,
	     					chkboxType:{ "Y" : "ps", "N" : "ps" }
	     				},
	     				view: {
	     					dblClickExpand: false
	     				},
	     				data: {
	     					simpleData: {
	     						enable: true
	     					}
	     				}
	     			};
				        	  $.ajax({
				         		 url:_url,
				         		 type:'GET',
				         		 data:{subjectNo:subjectNo,gradeNo:gradeNo},
				         		 success:function(data){
				         			 $.fn.zTree.init($(idOrClass), setting, data.value)
				         		 }
				         	 })
	         },
	         /**
	         	 * 加载教材知识点树
	         	 */
	         editloadKnowledgeTree:function(_url,gradeNo,subjectNo,knowledgePointArr,idOrClass){   
		        	 var setting = {
		     				check: {
		     					enable: true,
		     					chkboxType:{ "Y" : "ps", "N" : "ps" }
		     				},
		     				view: {
		     					dblClickExpand: false
		     				},
		     				data: {
		     					simpleData: {
		     						enable: true
		     					}
		     				}
		     			};
					        	  $.ajax({
					         		 url:_url,
					         		 type:'get',
					         		 data:{gradeNo:gradeNo,subjectNo:subjectNo,knowledgePointArr:knowledgePointArr},
					         		 success:function(data){
					         			 $.fn.zTree.init($(idOrClass), setting, data.value)
					         		 }
					         	 })
		         },
        /**
		 * MODAL
		 */
        openModel:function(modelId,title,callback){
        	base.validateClear();
        	var form = $('.validate-form');
        	form.clearForm();
        	form.find("input:hidden").each(function(i,item){  
                $(item).val("");  
            }) 
        	$('#'+modelId+' .modal-header h3').html(title);
        	if(callback)callback();
        	$('#'+modelId).modal('show');

        },
        closeModel:function(modelId,callback){
        	$('#'+modelId).modal('hide');
        	if(callback);
        },
        /**
		 * Table
		 */
        Table:function(_tableId){
        	this.tableId=_tableId;
        	this.init = function(_url,_cols){
        		$('#'+_tableId).bootstrapTable({
        			locale:'zh-CN',
        			height:490,
        			sidePagination:'server',
        			pagination:true,
        			pageList:'[10, 20, 30]',
        			url: _url,
        			method:'get',
        			searchAlign: "left",//查询框对齐方式
                    queryParamsType: "limit",//查询参数组织方式
        			search: false,//是否显示右上角的搜索框  
                    searchOnEnterKey: true,//回车搜索
                    showRefresh: false,//刷新按钮
        		    columns: _cols
        		});
        		
        	}
        	
        	this.query = function(_query){
        		$('#'+_tableId).bootstrapTable('refresh', _query);
        	}
        	this.getIdSelections = function() {
    	        return $.map($('#'+_tableId).bootstrapTable('getSelections'), function (row) {
    	            return row.id
    	        });
    	    }
        	
        	this.reload = function(){
            	$('#'+_tableId).bootstrapTable('refresh',{silent: true});
            }
        },
        /**
		 * Picture Select
		 */
        PicSelect:function(_hiddenId){
        	var guid = base.guid();
        	var _menuId = 'menu_'+guid;
        	var _showId = 'show_'+guid;
        	var _menuBtn = 'menuBtn_'+guid;
        	var icons = base.icons;
        	$('#'+_hiddenId).css({"display":"none"});
        	var icon="icon-th";
        	if($('#'+_hiddenId).val()!=null&&$('#'+_hiddenId).val()!="")
        		icon = $('#'+_hiddenId).val();
        	$("#"+_hiddenId).after('<i id="'+_showId+'" class="'+icon+'"/>'
            +'<button id="'+_menuBtn+'" type="button" style="margin-left:30px">'
            	+'<i class="icon-building"></i>'
            +'</button>');
        	var pic = '<div id="'+_menuId+'" style="display:none; position: absolute;border: 1px solid #617775;z-index:1051;background: #f0f6e4;width:250px;height:180px;overflow-y:auto;overflow-x:auto;"><table>';
        	$.each(icons,function(i,item){
        		if(i%15==0)
        			pic+='<tr>';
        		pic+='<td> <i class="'+item+'"/></td>';
        		if(i%15==14)
        			pic+='</tr>';
        	});
        	pic+='</table></div>';
        	$("body").append(pic);
        	
        	this.hid=_hiddenId;
        	this.sid=_showId;
        	this.set = function(hid,sid,pic){
        		if(pic!=null&&pic!=""){
        			$('#'+hid).val(pic);
            		$("#"+sid).attr("class", pic);
        		}else{
        			$('#'+hid).val("");
            		$("#"+sid).attr("class", "icon-th");
        		}
        	}
        	
        	$("#"+_menuId).find("i").hover(function(){
        		$(this).css("color","#f34541");
        		},function(){
        		$(this).css("color","black");
        	});
        	
        	$("#"+_menuId).find("i").click(function(){
        		var color = $(this).attr("class");
        		$("#"+_showId).attr("class", color);
        		$("#"+_hiddenId).val(color);
        		hide();
        	});
        	
        	function hide() {
    			$("#"+_menuId).fadeOut("fast");
    			$("body").unbind("mousedown", onBodyDown);
    		}
        	
        	var show=function (){
        		var _input = $('#'+_showId);
        		var _offset = $('#'+_showId).offset();
    			$("#"+_menuId).css({left:_offset.left+"px", top:_offset.top + _input.outerHeight()+"px"}).slideDown("fast");
    			$("body").bind("mousedown", onBodyDown);
    		}
        	
        	var onBodyDown = function (event) {
    			if (!(event.target.id == _menuBtn || $(event.target).parents("#"+_menuBtn).length>0 ||event.target.id == _menuId || $(event.target).parents("#"+_menuId).length>0)) {
    				hide();
    			}
    		}
        	
        	$('#'+_showId).click(function(){
				show();
			});
        	
        	$('#'+_menuBtn).click(function(){
				show();
			});
        }
	}
})