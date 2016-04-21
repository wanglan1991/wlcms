// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        tree:{},
        radioTree:{},
        table:new core.Table('permTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
            core.PicSelect('icon');	
            /**
             * 是否具有添加权限权限
             */
            	$("#perm-header .actions").append("<input autocomplete='off'  id='name'  placeholder='请输入名称' type='text' />&nbsp;&nbsp;<select  id='permType' style='width:8%'>" +
				"</select>&nbsp;&nbsp;<span  id='perm_nameame'></span><a href='#' id='queryByPerm' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
            	//加载可选类型列表
            	$.ajax({
            		url:F.basepath+'/cms/permission/typeList',
            		type:"POST",
            		success:function(data){
            		var typeHtml="<option value='-1'>--请选择类型--</option>";
            		for(var i=0;i<data.value.length;i++){ typeHtml+="<option value='"+data.value[i].level+"'>"+data.value[i].type+"</option>";}
            		$("#permType").append(typeHtml);
            		}
            	});	
            	          	
//            if(base.perList.permission.create){
            	$("#perm-header .actions").append("<a href='#' id='addPerm' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
//            }
            /**
             * 是否具有删除权限权限
             */
//            if(base.perList.permission.del){
            	$("#perm-header .actions").append("<a href='#' id='delPerms' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
//            }
//            	//触发二级菜单
//            	$("#permType").change(function(){
//            		$("#permPid").empty();
//            		var type=$("#permType").val();
//            		if(type==2||type==-1){
//            		$("#permPid").append("<option value='-1'>--无--</option>");
//            		return ;}
//            		
            		
//            	});
            	
			
			/**
			 * 请求权限数据
			 */
	        F.treeLoad();
	        
	        operateEvents = {
				/**
				 *修改权限
				 */
		        'click .editPerm': function (e, value, row, index) {
		        	core.openModel('modal-PermTree','修改权限',function(){
		        		F.radioTree.load();
		        		if(row!=null){
		            		$('#id').val(row.id);
		            		$('#name').val(row.name);
		            		$('#key').val(row.key);
		            		$('#order').val(row.order);
		            		$("#"+F.radioTree.showId).val(row.parentName!=null?row.parentName:'所有权限');
		    				$("#"+F.radioTree.hideId).val(row.parentId);
		            	}
		        	});
					return false;
		        },
		        /**
		         * 停用或启用
		         */
		        'click .confine':function(e,value,row,index){
		        	$.ajax({
		        		url:F.basepath+'/cms/permission/confine',
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
				 * 删除权限
				 */
		        'click .delPerm': function (e, value, row, index) {
		        	base.bootConfirm("是否确定删除？",function(){
		        		var ids = new Array();  
		        		ids.push(row.id);   
		    			F.delPerm(ids);
		    		});
		        }
		    };
	        	
	        
	        var cols = [
	                    {
	        		        checkbox:true
	        		    }, {
	        		        field: 'id',
	        		        title: '主键'
	        		    }, {
	        		        field: 'name',
	        		        title: '名称'
	        		    }, {
	        		        field: 'key',
	        		        title: 'key值'
	        		    },{
	    			        field: 'value',
	    			        title: 'URL',
	    		        },{
	    			        field: 'type',
	    			        title: '类型',
	    		        },{
	    			        field: 'pid',
	    			        title: '父级id',
	    		        },{
	    			        field: 'status',
	    			        title: '状态',
	    			        visible:false
	    		        }];
	        //是否需要操作列
//	        if(base.perList.permission.edit||base.perList.permission.del)
		        cols.push({
			    	align: 'center',
			        title: '操作',
			        events: operateEvents,
			        formatter:F.operateFormatter
			    });
        		
    		F.table.init(F.basepath+'/cms/permission/pageList',cols);
    		
    		/**
    		 * 带参查询
    		 */
    		$('#queryByPerm').click(function(){
    			var name=$("#name").val();
    			var level=$("#permType").val();
    			var url= F.basepath+'/cms/permission/pageList?name='+name+'&level='+level;
				$("#permTable").bootstrapTable('refresh',{url:url});	
    		});
    		
			/**
			 * 打开模态框
			 */
			$('#addPerm').click(function(){
				core.openModel('modal-addPerm','新增权限',function(){
					$("#icons").show();
								});
			});
			$("#addPermType").change(function (){
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
	        					pidHtml+="<option value='"+data.value[i].pid+"'>"+data.value[i].name+"</option>";
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
			
			function clear(){
				core.closeModel('modal-addPerm');
				$("#addPermType").val(-1);
				$("#permissionParent").hide();
				$("#permName").val('');
				$("#key").val('');
				$("#value").val('');
				$("#order").val('');
				$("#PermType-error").html("");
				$("#permName-error").html("");
				$("#key-error").html("");
				$("#url-error").html("");
				$("#icon-error").html("");
				$("#icon").val('');
			}
			
			
			
			
			
			
			/**
			 * 提交表单
			 */
			$('#btnSubmit').click(function(){
				var type=$("#addPermType").val();
				var pid=$("#permPrent").val();
				var name=$("#permName").val();
				var key=$("#key").val();
				var value=$("#value").val();
				var order=$("#order").val();
				var icon=$("#icon").val();
				if(type==-1){$("#PermType-error").html("请选择类型!");$("#PermType-error").css("color","#b94a48");return ;}
				if(name.length<1){$("#permName-error").html("请输入名称!");$("#permName-error").css("color","#b94a48");return ;}
				if(key.length<1){$("#key-error").html("请输入key!");$("#key-error").css("color","#b94a48");return ;}
				if(value.length<1){$("#url-error").html("请输入URL!");$("#url-error").css("color","#b94a48");return ;}
				if(icon.length<1){$("#icon-error").html("请选择图标!");$("#icon-error").css("color","#b94a48");return ;}
			
				$.ajax({
					url:F.basepath+'/cms/permission/addPermission',
					type:'post',
					data:{type:type==1?"模块":type==2?"页面":"按钮",pid:pid==null?0:pid,name:name,key:key,value:value,order:order,icon:icon},
					success:function(data){
						if(data.result>0){
							clear();
							F.reload();
						}
					}
				})
				
            });
			
			/**
			 * 批量删除
			 */
			$('#delPerms').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个权限？",function(){
						F.delPerm(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的权限！"});
				}
			});

        },submit:function(){
        	var url = F.basepath+'/main/permission/create';
        	if($("#id").val()!=null&&$("#id").val()!="")
        		url =F.basepath+'/main/permission/edit';
        	var options = {
        			beforeSubmit: F.showRequest,
                    success: F.showResponse,      //提交后的回调函数
                    url: url,       //默认是form的action， 如果申明，则会覆盖
                    type: 'post',               //默认是form的method（get or post），如果申明，则会覆盖
                    dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型
                    clearForm: true,          //成功提交后，清除所有表单元素的值
                    timeout: 30000               //限制请求的时间，当请求大于3秒后，跳出请求
                }
        	$('#submit-form').ajaxForm(options);
        },delPerm:function(ids){
        	$.ajax({
        		url:F.basepath+'/cms/permission/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        				F.reload();
        			}
        		}
        	})
        	
        },showRequest:function showRequest(formData, jqForm, options){
            return true; 
        },showResponse:function(data, status){
            base.bootAlert(data);
            if (data.ok) {
            	core.closeModel('modal-PermTree');
            	F.reload();
            }
        },reload:function(){
        	F.tree.load();
        	F.table.reload();
        },onClick:function(event, treeId, treeNode, clickFlag) {
			F.table.query({query: {'id':treeNode.id}});
		},treeLoad:function(){
			F.tree = core.initTree("permTree",F.basepath+'/main/get-all-permissions',F.onClick);
        	F.tree.load();
        	if (base.perList.permission.edit||base.perList.permission.create) {
        		F.radioTree = core.initDropDownRadioTree("parentId",F.basepath+'/main/get-all-permissions');
        	}
        },
        operateFormatter:function (value, row, index) {
        	var _btnAction = "";
//        	if (base.perList.permission.edit) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
//        	}
//        	if (base.perList.permission.edit) {
        		_btnAction += "<a data-toggle='modal' class='editPerm btn btn-success btn-small' href='#' title='编辑权限' style='margin-left:5px'>编辑</a>";
//        	}
//        	if (base.perList.permission.del) {
        		_btnAction += "<a class='delPerm btn btn-danger btn-small' href='#' title='删除权限' style='margin-left:5px'>删除</a>";
//        	}
        	return _btnAction;
        } 
    };
});
