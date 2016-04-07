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
            /**
             * 是否具有添加权限权限
             */
            if(base.perList.permission.create){
            	$("#perm-header .actions").append("<a href='#' id='addPerm' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px'><i class='icon-plus'></i>添加</a>");
            }
            /**
             * 是否具有删除权限权限
             */
            if(base.perList.permission.del){
            	$("#perm-header .actions").append("<a href='#' id='delPerms' class='btn btn-danger btn-small' style='margin-left:5px'><i class='icon-remove'></i>删除</a>");
            }
			
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
	        		        field: 'name',
	        		        title: '权限名称'
	        		    }, {
	        		        field: 'key',
	        		        title: '权限key'
	        		    }, {
	        		        field: 'order',
	        		        title: '排序'
	        		    },{
	    			        field: 'parentId',
	    			        title: '上级权限',
	    			        visible:false
	    		        },{
	    			        field: 'id',
	    			        title: '权限主键',
	    			        visible:false
	    		        },{
	    			        field: 'parentName',
	    			        title: '上级权限',
	    			        visible:false
	    		        }];
	        //是否需要操作列
	        if(base.perList.permission.edit||base.perList.permission.del)
		        cols.push({
			    	align: 'center',
			        title: '操作',
			        events: operateEvents,
			        formatter:F.operateFormatter
			    });
        		
    		F.table.init(F.basepath+'/main/get-show-permissions',cols);
    		
			/**
			 * 打开模态框
			 */
			$('#addPerm').click(function(){
				core.openModel('modal-PermTree','新增权限',function(){
					F.radioTree.load();
				});
			});
			
			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function(){
				core.closeModel('modal-PermTree');
			});
			
			/**
			 * 提交表单
			 */
			$('#btnSubmit').click(function(){
				F.submit();
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
        	base.ajaxRequest(F.basepath+'/main/permission/del',{"permIds":ids},function(data){
        		base.ajaxSuccess(data);
        		F.reload();
        	},function(){
        		base.bootAlert({"ok":false,"msg":"网络异常"});
        	});
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
        	if (base.perList.permission.edit) {
        		_btnAction += "<a data-toggle='modal' class='editPerm btn btn-success btn-small' href='#' title='编辑权限' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.permission.del) {
        		_btnAction += "<a class='delPerm btn btn-danger btn-small' href='#' title='删除权限' style='margin-left:5px'>删除</a>";
        	}
        	return _btnAction;
        } 
    };
});
