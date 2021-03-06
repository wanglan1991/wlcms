// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        checkPermissionTree:{},
        distributePermissionTree:{},
        table:new core.Table('roleTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
            
            
            /**
             * 是否具有删除角色权限
             */
            if(base.perList.role.del){
            	$("#role-header .actions").append("<a href='#' id='delRoles' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
            }
            
            /**
             * 是否具有添加角色权限
             */
            if(base.perList.role.create){
            	$("#role-header .actions").append("<a href='#' id='addRole' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
            }
            
           
            
            operateEvents = {
    				/**
    				 *修改角色
    				 */
    		        'click .editRole': function (e, value, row, index) {
    		        	core.openModel('modal-editRole','修改角色     '+row.encoding,function(){
    		            	if(row!=null){
    		            		$('#editId').val(row.id);
    		            		$('#editEncoding').val(row.encoding);
    		            		$('#editName').val(row.name);
    		            	}
    		        	});
    		        },
    		        /**
	    			 * 删除角色
	    			 */
    		        'click .delRole': function (e, value, row, index) {   		  
    		        	base.bootConfirm("是否确定删除？",function(){
    		        		var ids = new Array();  
    		        		ids.push(row.id);   
    		    			F.delRole(ids);
    		    		});
    		        },
    		       
    		        /**
    				 * 启用或停用用户
    				 */
    		        'click .confine': function (e, value, row, index) {	
    		        	$.ajax({
    		        		url: F.basepath+'/cms/role/confine',
    		        		type:'POST',
    		        		data:{id:row.id,status:row.status==1?0:1},
    		        		success:function(data){
    		        			if(data.result>0){        		        				
    		        				F.reload();
    		        			}else{
    		        				alert("操作失败！")
    		        			}
    		        		}
    		        	})
    		        	
    		        },
    		        'click .distributePermission': function (e, value, row, index) {
    		        	core.openModel('modal-DistributePermission','菜单授权',function(){
    		            	if(row!=null){
    		            		var url=F.basepath+'/cms/role/tree'
    		            		$("#roleId").val(row.id)
    		            		core.loadTree(url.toString(),row.id);
    		            	}
    		        	});
    		        },
    		        /**
    		         * 查看权限
    		         */
    		       
    		    };
            
            var cols = [
 	                    {
 	        		        checkbox:true
 	        		    }, {
 	        		        field: 'id',
 	        		        title: '主键'
 	        		    }, {
 	        		        field: 'name',
 	        		        title: '角色名称'
 	        		        	
 	        		    },{
 	    			        field: 'encoding',
 	    			        title: '角色编码'
 	    		        }];
 	        //是否需要操作列
 	        if(base.perList.role.edit || base.perList.role.del || base.perList.role.confine || base.perList.role.grant)
 		        cols.push({
 			    	align: 'center',
 			        title: '操作',
 			        events: operateEvents,
 			        formatter:F.operateFormatter
 			    });
 	        
 	       /**
        	 * 角色列表
        	 */	
 	       F.table.init(F.basepath+'/cms/role/pageList',cols);
    		
    		/**
			 * 批量删除
			 */
			$('#delRoles').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个角色？",function(){
						F.delRole(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的角色！"});
				}
			});
    		
    		/**
			 * 打开模态框
			 */
			$('#addRole').click(function(){
				core.openModel('modal-Role','新增角色');
				return false;
			});
			
			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function(){
				$("#name").val('');
				$("#encoding").val('');
				$("#encoding-error").html('');
				$("#msg").html('');
				core.closeModel('modal-Role');
			});
			$('#editBtnClose').click(function(){
				$("#editName").val('');
				$("#editEncoding").val('');
				$("#editId").val('');
				$("#editEncoding-error").html('');
				core.closeModel('modal-editRole');
				
			});
			
			$('#btnDistributePermissionClose').click(function(){
				core.closeModel('modal-DistributePermission');
			});
			
			$('#btnCheckPermissionClose').click(function(){
				core.closeModel('modal-CheckPermission');
			});
			
			/**
			 * 提交表单
			 */
			$('#btnSubmit').click(function(){
				var name=$("#name").val();
				var encoding=$("#encoding").val();
				if(encoding.lenght<1||name.length<1){
					$("#msg").html("角色编码以及角色名称不能为空！");$("#msg").css('color','#b94a48');
					return}
				$.ajax({
					url:F.basepath+"/cms/role/addRole",
					type:"post",
					data:{name:name,encoding:encoding},
					success:function(data){
						if(data.result>0){
							F.reload();
							$("#name").val('');
							$("#encoding").val('');
							$("#encoding-error").html('');
							$("#msg").html('');
							core.closeModel('modal-Role');
						}else if(data.result==-1){
							$("#encoding-error").html(data.msg);
							$("#encoding-error").css('color','#b94a48');
						}else {
							core.closeModel('modal-Role');
							alert("异常！")
						}
					}
				})
				
            });
			/**
			 * 编辑角色
			 */
			$('#editBtnSubmit').click(function(){
				var id=$("#editId").val();
				var name=$("#editName").val();
				var encoding=$("#editEncoding").val();
				if(encoding.lenght<1||name.length<1){
					$("#editMsg").html("角色编码以及角色名称不能为空！");$("#editMsg").css('color','#b94a48');
					return}
				$.ajax({
					url:F.basepath+"/cms/role/editRole",
					type:"post",
					data:{id:id,name:name,encoding:encoding},
					success:function(data){
						if(data.result>0){
							F.reload();
							$("#encoding-error").html('');
							$("#editMsg").html('');
							$("#editEncoding-error").html('');
							core.closeModel('modal-editRole');
						}else if(data.result==-1){
							$("#editEncoding-error").html(data.msg);
							$("#editEncoding-error").css('color','#b94a48');
						}else {
							core.closeModel('modal-editRole');
							alert("异常！")
						}
					}
				})
				
            });
			//提交树
			$('#btnDistributePermissionSubmit').click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("distributePermissionTree");
				var roleId = $("#roleId").val();
				var arr=''
				var nodes = treeObj.transformToArray(treeObj.getNodes()); 
				for (var i = 0; i < nodes.length; i++) { 
					if(nodes[i].checked==true){
						arr+=nodes[i].id+',';
					}
				}
				$.ajax({
					url:F.basepath+"/cms/role/permissions",
					type:'POST',
					data:{permissionStr:arr,roleId:roleId},
					success:function(data){}
				});
				core.closeModel('modal-DistributePermission');
			});
			
        },

        delRole:function(ids){
        	$.ajax({
        		url:F.basepath+'/cms/role/delete',
        		type:'post',
        		data:{ids:ids.toString()},
        		success:function(data){
        			if(data.result>0){
        				F.reload();
        			}else if(data.result==0){
        				base.bootAlert({"ok":false,"msg":"网络异常"});
        			}
        		}
        	})
        },reload:function(){
        	F.table.reload();
        },operateFormatter:function (value, row, index) {
        	var _btnAction = "";
        	if (base.perList.role.confine&&row.id!=1) {
        	_btnAction += "<a class='confine btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
        	}
        	if (base.perList.role.grant) {
        		_btnAction += "<a class='distributePermission btn btn-primary btn-small' href='#' title='菜单授权' style='margin-left:5px'>授权</a>";
        	}
        	if (base.perList.role.edit) {
        		_btnAction += "<a data-toggle='modal' class='editRole btn btn-success btn-small' href='#' title='编辑角色' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.role.del&&row.id!=1) {
        		_btnAction += "<a class='delRole btn btn-danger btn-small' href='#'  title='删除角色' style='margin-left:5px'>删除</a>";
        	}
        	return _btnAction;
        }
    };

});