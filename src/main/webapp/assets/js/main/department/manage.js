// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        tree:{},
        radioTree:{},
        table:new core.Table('departTable'),
        init: function (_basepath) {
            F.basepath = _basepath;
            /**
             * 是否具有添加部门权限
             */
            if(base.perList.department.create){
            	$("#depart-header .actions").append("<a href='#' id='addDepart' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px'><i class='icon-plus'></i>添加</a>");
            }
            /**
             * 是否具有删除部门权限
             */
            if(base.perList.department.del){
            	$("#depart-header .actions").append("<a href='#' id='delDeparts' class='btn btn-danger btn-small' style='margin-left:5px'><i class='icon-remove'></i>删除</a>");
            }
			
			/**
			 * 请求部门数据
			 */
	        F.treeLoad();
	        
	        operateEvents = {
				/**
				 *修改部门
				 */
		        'click .editDepart': function (e, value, row, index) {
		        	core.openModel('modal-DepartTree','修改部门',function(){
		        		F.radioTree.load();
		        		if(row!=null){
		            		$('#id').val(row.id);
		            		$('#name').val(row.name);
		            		$('#order').val(row.order);
		            		$("#"+F.radioTree.showId).val(row.parentName!=null?row.parentName:'所有部门');
		    				$("#"+F.radioTree.hideId).val(row.parentId);
		            	}
		        	});
					return false;
		        },
		        /**
				 * 删除部门
				 */
		        'click .delDepart': function (e, value, row, index) {
		        	base.bootConfirm("是否确定删除？",function(){
		        		var ids = new Array();  
		        		ids.push(row.id);   
		    			F.delDepart(ids);
		    		});
		        }
		    };
	        
	        var cols = [
	                    {
	        		        checkbox:true
	        		    }, {
	        		        field: 'name',
	        		        title: '部门名称'
	        		    }, {
	        		        field: 'order',
	        		        title: '排序'
	        		    },{
	    			        field: 'parentId',
	    			        title: '上级部门',
	    			        visible:false
	    		        },{
	    			        field: 'id',
	    			        title: '部门主键',
	    			        visible:false
	    		        },{
	    			        field: 'parentName',
	    			        title: '上级部门',
	    			        visible:false
	    		        }];
	        //是否需要操作列
	        if(base.perList.department.edit||base.perList.department.del)
		        cols.push({
			    	align: 'center',
			        title: '操作',
			        events: operateEvents,
			        formatter:F.operateFormatter
			    });
        		
    		F.table.init(F.basepath+'/main/department/get-list-departments',cols);
    		
			/**
			 * 打开模态框
			 */
			$('#addDepart').click(function(){
				core.openModel('modal-DepartTree','新增部门',function(){
					F.radioTree.load();
				});
			});
			
			/**
			 * 关闭模态框
			 */
			$('#btnClose').click(function(){
				core.closeModel('modal-DepartTree');
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
			$('#delDeparts').click(function(){
				var ids = F.table.getIdSelections();
				if(ids!=null&&ids.length>0){
					base.bootConfirm("是否确定删除选定的"+ids.length+"个部门？",function(){
						F.delDepart(ids);
					});
				}else{
					base.bootAlert({"ok":false,"msg":"请选择你要删除的部门！"});
				}
			});

        },submit:function(){
        	var url = F.basepath+'/main/department/create';
        	if($("#id").val()!=null&&$("#id").val()!="")
        		url =F.basepath+'/main/department/edit';
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
        },delDepart:function(ids){
        	base.ajaxRequest(F.basepath+'/main/department/del',{"depIds":ids},function(data){
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
            	core.closeModel('modal-DepartTree');
            	F.reload();
            }
        },reload:function(){
        	F.tree.load();
        	F.table.reload();
        },onClick:function(event, treeId, treeNode, clickFlag) {
			F.table.query({query: {'id':treeNode.id}});
		},treeLoad:function(){
			F.tree = core.initTree("departTree",F.basepath+'/main/department/get-all-departments',F.onClick);
        	F.tree.load();
        	if (base.perList.department.edit||base.perList.department.create) {
        		F.radioTree = core.initDropDownRadioTree("parentId",F.basepath+'/main/department/get-all-departments');
        	}
        },
        operateFormatter:function (value, row, index) {
        	var _btnAction = "";
        	if (base.perList.department.edit) {
        		_btnAction += "<a data-toggle='modal' class='editDepart btn btn-success btn-small' href='#' title='编辑部门' style='margin-left:5px'>编辑</a>";
        	}
        	if (base.perList.department.del) {
        		_btnAction += "<a class='delDepart btn btn-danger btn-small' href='#' title='删除部门' style='margin-left:5px'>删除</a>";
        	}
        	return _btnAction;
        } 
    };
});
