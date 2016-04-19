define(function (require, exports, module) {

	var base = require('base');
	var E = module.exports = {
		/**
		 * TREE
		 */
		CoreTree:function(_treeId,_url,_setting){
			this.treeId = _treeId;
			this.url = _url;
        	this.load=function(_data,_hiddenId){
        		$.ajax({  
        		     url:_url,
        		     type:'post',
        		     data:_data,
        		     cache:false,  
        		     dataType:'json',  
        		     success:function(data,status) { 
        		    	 if (data.ok) {
        		    		 var zNodes = data.data;
        		    		 $.fn.zTree.init($("#"+_treeId),_setting, zNodes);
        		    		 if(_hiddenId!=null && _hiddenId.length>0){
        		    			 var v="";
        		    			 for(var i=0;i<zNodes.length;i++){ 
        		    				 if(zNodes[i].checked==true)
        		    					 v+=zNodes[i].id + ","; 
         						} 
         						if (v.length > 0 ) v = v.substring(0, v.length-1);
         						$('#'+_hiddenId).val(v);
        		    		 }
        		    	 }
        		      },  
        		      error : function() {  
        		         
        		      }  
        		});
        	}
        	return this;
        },
        DropDownTree:function(_treeId,_showId,_hideId,_url,_setting){
			this.showId = _showId;
			this.hideId = _hideId;
			E.CoreTree.call(this,_treeId,_url,_setting);  
        },
		initTree: function (_treeId,_url,_onClick) {
            var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: _onClick
				}
			};
            var coreTree = new E.CoreTree(_treeId,_url,setting);
            return coreTree;
        },
        initRadioTree:function(_treeId,_url,_hiddenId){
        	
        	var _onCheck = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
        		var nodes = zTree.getCheckedNodes(true),id="";
				for (var i=0, l=nodes.length; i<l; i++) {
					id += nodes[i].id + ",";
				}
				if (id.length > 0 ) id = id.substring(0, id.length-1);
				if(_hiddenId!=null&&_hiddenId.length>0)
					$("#"+_hiddenId).val(id);
				else
					$("#"+_treeId).data(id);
        	};
        	
        	var _onClick = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
        	};
        	
        	var setting = {
        			check: {
    					enable: true,
    					chkStyle: "radio",
    					radioType: "all"
    				},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				callback: {
    					onClick: _onClick,
    					onCheck: _onCheck
    				}
        		};
        	var coreTree = new E.CoreTree(_treeId,_url,setting);
            return coreTree;
        },
        initCheckTree:function(_treeId,_url,_hiddenId){
        	var _beforeClick = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
        	};
        	
        	var _onCheck = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
        		var nodes = zTree.getCheckedNodes(true),id="";
				for (var i=0, l=nodes.length; i<l; i++) {
					id += nodes[i].id + ",";
				}
				if (id.length > 0 ) id = id.substring(0, id.length-1);
				if(_hiddenId!=null&&_hiddenId.length>0)
					$("#"+_hiddenId).val(id);
				else
					$("#"+_treeId).data(id);
        	};
        	
        	var setting = {
        			check: {
        				enable: true,
        				chkDisabledInherit: true
        			},
        			data: {
        				simpleData: {
        					enable: true
        				}
        			},
    				callback: {
    					beforeClick: _beforeClick,
    					onCheck: _onCheck
    				}
        		};
        	var coreTree = new E.CoreTree(_treeId,_url,setting);
            return coreTree;
        },
        initDropDownRadioTree:function(_hiddenId,_url){
        	var guid = base.guid();
        	var _treeId = 'tree_'+guid;
        	var _menuId = 'menu_'+guid;
        	var _showId = 'show_'+guid;
        	var _menuBtn = 'menuBtn_'+guid;
        	$('#'+_hiddenId).css({"display":"none"});
        	$("#"+_hiddenId).after('<input class="span8" id="'+_showId+'" required placeholder="请选择" type="text" />'
            +'<button id="'+_menuBtn+'" type="button">'
            	+'<i class="icon-building"></i>'
            +'</button>');
        	$("body").append('<div id="'+_menuId+'" style="display:none; position: absolute;border: 1px solid #617775;z-index:1051">'
        			+'<ul id="'+_treeId+'" class="ztree" style="margin-top:0;margin-top: 0px;background: #f0f6e4;width:160px;height:180px;overflow-y:scroll;overflow-x:auto;"></ul>'
            +'</div>');
        	var _onClick = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
        	};
        	var _onCheck = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
        		var nodes = zTree.getCheckedNodes(true),v = "",id="";
				for (var i=0, l=nodes.length; i<l; i++) {
					v += nodes[i].name + ",";
					id += nodes[i].id + ",";
				}
				if (v.length > 0 ) v = v.substring(0, v.length-1);
				if (id.length > 0 ) id = id.substring(0, id.length-1);
				$("#"+_showId).val(v);
				$("#"+_hiddenId).val(id);
				hide();
        	};
        	
        	function hide() {
    			$("#"+_menuId).fadeOut("fast");
    			$("body").unbind("mousedown", onBodyDown);
    		}
        	
        	function queryParams(params) {  //配置参数  
			    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的  
//			      pageSize: params.limit,   //页面大小  
//			      pageNumber: params.pageNumber,  //页码  
			      value : $("#q_dict_value").val(),  
			      type: $("#q_dict_type").val(),  
			    };  
			    return temp;  
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
        	
        	var checkNode =  function(){
        		var z_tree = $.fn.zTree.getZTreeObj(_treeId);
        		var val = $("#"+_hiddenId).val();
        		if(val!=null&&val.length>0){
        			var node = z_tree.getNodeByParam("id", val, null);
            		z_tree.checkNode(node, true, null, true);
        		}
        		
        	}
        	
        	$('#'+_showId).click(function(){
        		checkNode();
				show();
			});
        	
        	$('#'+_menuBtn).click(function(){
        		checkNode();
				show();
			});
        	
        	var setting = {
    				check: {
    					enable: true,
    					chkStyle: "radio",
    					radioType: "all"
    				},
    				view: {
    					dblClickExpand: false
    				},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				callback: {
    					onClick: _onClick,
    					onCheck: _onCheck
    				}
    			};
        	 var dropDownTree = new E.DropDownTree(_treeId,_showId,_hiddenId,_url,setting);
             return dropDownTree;
        },
        initDropDownCheckTree:function(_hiddenId,_url){
        	var guid = base.guid();
        	var _treeId = 'tree_'+guid;
        	var _menuId = 'menu_'+guid;
        	var _showId = 'show_'+guid;
        	var _menuBtn = 'menuBtn_'+guid;
        	$('#'+_hiddenId).css({"display":"none"});
        	$("#"+_hiddenId).after('<input class="span8" id="'+_showId+'" required placeholder="请选择" type="text" />'
            +'<button id="'+_menuBtn+'" type="button">'
            	+'<i class="icon-building"></i>'
            +'</button>');
        	$("body").append('<div id="'+_menuId+'" style="display:none; position: absolute;border: 1px solid #617775;z-index:1051">'
        			+'<ul id="'+_treeId+'" class="ztree" style="margin-top:0;margin-top: 0px;background: #f0f6e4;width:160px;height:180px;overflow-y:scroll;overflow-x:auto;"></ul>'
            +'</div>');
        	var _beforeClick = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
        	};
        	var _onCheck = function(){
        		var zTree = $.fn.zTree.getZTreeObj(_treeId);
        		var nodes = zTree.getCheckedNodes(true),v = "",id="";
				for (var i=0, l=nodes.length; i<l; i++) {
					v += nodes[i].name + ",";
					id += nodes[i].id + ",";
				}
				if (v.length > 0 ) v = v.substring(0, v.length-1);
				if (id.length > 0 ) id = id.substring(0, id.length-1);
				$("#"+_showId).val(v);
				$("#"+_hiddenId).val(id);
        	};
        	
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
        	
        	var checkNode =  function(){
        		var z_tree = $.fn.zTree.getZTreeObj(_treeId);
        		var roleIds = $("#"+_hiddenId).val();
        		if(roleIds!=null&&roleIds.length>0){
        			var roles = roleIds.split(',');
        			for(var i=0;i<roles.length;i++){
            			var node = z_tree.getNodeByParam("id", roles[i]!=null?roles[i]:"0", null);
	            		z_tree.checkNode(node, true, null, true);
            		}
        		}
        	}
        	
        	$('#'+_showId).click(function(){
        		checkNode();
				show();
			});
        	
        	$('#'+_menuBtn).click(function(){
        		checkNode();
				show();
			});
        	
        	var setting = {
    				check: {
    					enable: true,
    					chkboxType: {"Y":"", "N":""}
    				},
    				view: {
    					dblClickExpand: false
    				},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				callback: {
    					beforeClick: _beforeClick,
    					onCheck: _onCheck
    				}
    			};
        	 var dropDownTree = new E.DropDownTree(_treeId,_showId,_hiddenId,_url,setting);
             return dropDownTree;
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
        	if(callback)callback();
        },
        /**
		 * Table
		 */
        Table:function(_tableId){
        	this.tableId=_tableId;
        	this.init = function(_url,_cols){
        		alert("init");
        		$('#'+_tableId).bootstrapTable({
        			locale:'zh-CN',
        			height:320,
        			sidePagination:'server',
        			pagination:true,
        			pageList:'[10, 20, 30]',
        			url: _url,
        			method:'get',
        			searchAlign: "left",//查询框对齐方式
                    queryParamsType: "limit",//查询参数组织方式
                    queryParams: function getParams(params) {
                        //params obj
                        params.other = "语文";
                        return params;
                    },
        			search: true,//是否显示右上角的搜索框  
                    searchOnEnterKey: true,//回车搜索
                    showRefresh: true,//刷新按钮
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
>>>>>>> upstream/master
})