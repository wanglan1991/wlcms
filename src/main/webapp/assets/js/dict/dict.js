define(function (require, exports, module) {
        var base = require('base');
        var core = require('core');
        // 通过 require 引入依赖
        var F = module.exports = {
            basepath: '',
            radioTree:{},
            tree:{},
            radioDeptTree:{},
            checkTree:{},
            table:new core.Table('dictTable'),
            init:function(_basepath){
            	F.basepath = _basepath;
            	
            	operateEvents = {
        				/**
        				 *修改用户
        				 */
        		        'click .editUser': function (e, value, row, index) {
        		        	core.openModel('modal-UserTree','修改用户',function(){
        		            	F.radioTree.load();
        		            	if(row!=null){
        		            		$('#id').val(row.id);
        		            		$('#account').val(row.account);
        		            		$('#password').val(row.password);
        		            		$('#repassword').val(row.password);
        		    				$("#"+F.radioTree.showId).val(row.dep_name);
        		    				$("#"+F.radioTree.hideId).val(row.dep_id);
        		            	}
        		        	});
        		        },
        		        /**
        				 * 删除用户
        				 */
        		        'click .delUser': function (e, value, row, index) {
        		        	base.bootConfirm("是否确定删除？",function(){
        		        		var ids = new Array();  
        		        		ids.push(row.id);   
        		    			F.delUser(ids);
        		    		});
        		        },
        		        /**
        				 * 修改部门
        				 */
        		        'click .editDep': function (e, value, row, index) {
        		        	core.openModel('modal-UserDeptTree','修改部门',function(){
        		            	F.radioDeptTree.load();
        		            	if(row!=null){
        		            		$('#userId').val(row.id);
        		    				$("#"+F.radioDeptTree.showId).val(row.dep_name);
        		    				$("#"+F.radioDeptTree.hideId).val(row.dep_id);
        		            	}
        		        	});
        		        },
        		        /**
        				 * 分配角色
        				 */
        		        'click .distributeRole': function (e, value, row, index) {
        		        	core.openModel('modal-UserRoleTree','角色分配',function(){
        		        		F.checkTree.load();
        		            	if(row!=null){
        		            		$('#userId').val(row.id);
        		    				$("#"+F.checkTree.showId).val(row.roleNames);
        		    				$("#"+F.checkTree.hideId).val(row.roleIds);
        		            	}
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
     	        		        field: 'value',
     	        		        title: '字典值'
     	        		    },{
     	    			        field: 'type',
     	    			        title: '字典类型'
     	    		        },{
     	    			        field: 'status',
     	    			        title: '状态'
     	    		        },{
     	    			        field: 'remark',
     	    			        title: '备注'
     	    		        }];
            	
     	       /**
            	 * 用户列表
            	 */	
        		F.table.init(F.basepath+'/dict/listDictPage',cols);
        		
        		/**
    			 * 批量删除
    			 */
    			
    			/**
    			 * 打开模态框
    			 */
    			
    			/**
    			 * 关闭模态框
    			 */
    			
    			
    			
    			/**
    			 * 提交表单
    			 */
    			
    			
        		
            },roleSubmit:function(){
            	var roles = $("#roleId").val();
            	var roleArray = roles.split(',');
            	var userId = $("#userId").val();
            	var data = {"userId":userId,"roleArray":roleArray};
            	var url = F.basepath+'/user/grant';
            	base.ajaxRequest(url,data,function(data, status){
            		 base.bootAlert(data);
                     if (data.ok) {
                     	core.closeModel('modal-UserRoleTree');
                     	F.reload();
                     }
            	},function(){
            		alert("异常");
            	});
            },
            deptSubmit:function(){
            	var url = F.basepath+'/user/edit-user-dep';
            	var options = {
                        success: F.showDeptResponse,      //提交后的回调函数
                        url: url,       //默认是form的action， 如果申明，则会覆盖
                        type: 'post',               //默认是form的method（get or post），如果申明，则会覆盖
                        dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型
                        clearForm: true,          //成功提交后，清除所有表单元素的值
                        timeout: 30000               //限制请求的时间，当请求大于3秒后，跳出请求
                    }
            	$('#userDept-form').ajaxForm(options);
            },showDeptResponse:function(data, status){
                base.bootAlert(data);
                if (data.ok) {
                	core.closeModel('modal-UserDeptTree');
                	F.reload();
                }
            },
            submit:function(){
            	var url = F.basepath+'/user/create';
            	if($("#id").val()!=null&&$("#id").val()!="")
            		url =F.basepath+'/user/edit';
            	var options = {
                        success: F.showResponse,      //提交后的回调函数
                        url: url,       //默认是form的action， 如果申明，则会覆盖
                        type: 'post',               //默认是form的method（get or post），如果申明，则会覆盖
                        dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型
                        clearForm: true,          //成功提交后，清除所有表单元素的值
                        timeout: 30000               //限制请求的时间，当请求大于3秒后，跳出请求
                    }
            	$('#submit-form').ajaxForm(options);
            },
            showResponse:function(data, status){
                base.bootAlert(data);
                if (data.ok) {
                	core.closeModel('modal-UserTree');
                	F.reload();
                }
            },
            operateFormatter:function (value, row, index) {
            	var _btnAction = "";
            	if (base.perList.user.edit_dep) {
            		_btnAction += "<a class='editDep btn btn-primary btn-small' href='#' title='分配部门' style='margin-left:5px'>分配部门</a>";
            	}
            	if (base.perList.user.distribute_role) {
            		_btnAction += "<a class='distributeRole btn btn-info btn-small' href='#' title='分配角色' style='margin-left:5px'>分配角色</a>";
            	}
            	if (base.perList.user.edit) {
            		_btnAction += "<a data-toggle='modal' class='editUser btn btn-success btn-small' href='#' title='编辑用户' style='margin-left:5px'>编辑</a>";
            	}
            	if (base.perList.user.del) {
            		_btnAction += "<a class='delUser btn btn-danger btn-small' href='#' title='删除用户' style='margin-left:5px'>删除</a>";
            	}
            	return _btnAction;
            },delUser:function(ids){
            	base.ajaxRequest(F.basepath+'/user/delete',{"userIds":ids},function(data){
            		base.ajaxSuccess(data);
            		F.reload();
            	},function(){
            		base.bootAlert({"ok":false,"msg":"网络异常"});
            	});
            },onClick :function(event, treeId, treeNode, clickFlag) {
				F.table.query({query: {'id':treeNode.id}});
			},reload:function(){
				F.tree.load();
				F.table.reload();
			},treeLoad:function(){
            	F.tree = core.initTree("departTree",F.basepath+'/main/department/get-all-departments',F.onClick);
            	F.tree.load();
            	if (base.perList.user.create||base.perList.user.edit) {
            		F.radioTree = core.initDropDownRadioTree('dep',F.basepath+'/main/department/get-all-departments');
            	}
            	if (base.perList.user.edit_dep) {
            		F.radioDeptTree = core.initDropDownRadioTree('depId',F.basepath+'/main/department/get-all-departments');
            	}
            	if (base.perList.user.distribute_role) {
            		F.checkTree = core.initDropDownCheckTree('roleId',F.basepath+'/main/role/get-all-roles');
            	}
            }
        }
    }
);