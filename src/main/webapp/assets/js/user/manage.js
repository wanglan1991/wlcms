
var core='';
define(function (require, exports, module) {
        var base = require('base');
        core = require('core');
        // 通过 require 引入依赖
        var F = module.exports = {
            basepath: '',
            radioTree:{},
            tree:{},
            radioDeptTree:{},
            checkTree:{},
            table:new core.Table('userTable'),
            init:function(_basepath){
            	F.basepath = _basepath;
            	/**
                 * 是否具有添加用户权限
                 */
            	//加载查询框
//               	  if(base.perList.user.check){
              		$("#user-header .actions").append("<input autocomplete='off'  id='realName'  placeholder='请输入用户姓名' type='text' />&nbsp;&nbsp;<input autocomplete='off'  id='accountName'  placeholder='请输入账户名'" +
              				" type='text' />&nbsp;&nbsp;<span  id='dict_typeName'></span><a href='#' id='queryByAccount' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>查询</a>&nbsp;&nbsp;&nbsp;&nbsp;");
//              	}
            	//添加账户
//                if(base.perList.user.add){
                	$("#user-header .actions").append("<a href='#' id='delUsers' class='btn btn-danger btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-remove'></i>删除</a>");
//                }
                //删除账户
//                if(base.perList.user.del){
                	$("#user-header .actions").append("<a href='#' id='addUser' data-toggle='modal' class='btn btn-success btn-small' style='margin-left:5px;margin-bottom:11px'><i class='icon-plus'></i>添加</a>");
//                }	
           
                
                /**
                 * 加载树
                 */
            	F.treeLoad();
            	
            	operateEvents = {
        				/**
        				 *修改用户
        				 */
        		        'click .editUser': function (e, value, row, index) {
        		        	core.openModel('modal-EditUser','修改用户',function(){
        		        		$("#EditrealName").val(row.realName);
        		        		$("#Editcellphone").val(row.cellphone);
        		        		$("#EditrealName").attr("accountId",row.id);
        		        		$("#tatil").next("h3").html("编辑用户          "+row.userName);
        		        	
        		        	});
        		        },
        		        /**
        		         * 重置密码
        		         */
        		        'click .resetPwd':function(e,value,row,index) {
        		        	base.bootConfirm("您确定需要重置"+row.userName+"用户的密码？",function(){
	        		        	$.ajax({
	        		        		url: F.basepath+'/account/resetPwd',
	    		        			type:'POST',
	    		        			data:{id:row.id},
	    		        			success:function(data){
	    		        				if(data.result>0){
	    		        					alert("密码重置成功，为123456789  ！");
	    		        				}else{
	    		        					alert("重置失败！稍后再试")
	    		        				}
	    		        			}
	        		        	})
        		        	});
        		        },
        		        /**
        				 * 删除用户
        				 */
        		        'click .delUser': function (e, value, row, index) {
        		        	base.bootConfirm("是否确定删除？",function(){
        		        		$.ajax({
        		        			url: F.basepath+'/account/delete',
        		        			type:'POST',
        		        			data:{id:row.id},
        		        			success:function(data){
        		        				if(data.result>0){
        		        					F.reload();
        		        				}else{
        		        					alert("异常！")
        		        				}
        		        			}
        		        		});
        		    		});
        		        },
        		        /**
        				 * 启用或停用用户
        				 */
        		        'click .editDep': function (e, value, row, index) {	
        		        	$.ajax({
        		        		url: F.basepath+'/account/confine',
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
        		        /**
        				 * 分配角色
        				 */
        		        'click .distributeRole': function (e, value, row, index) {
        		        	var html='';
        		        	var total=0;
        		        	core.openModel('modal-UserRoleTree','角色赋值',function(){});
        		        	$.ajax({
    		        			url: F.basepath+'/account/roleList',
    		        			type:'POST',
    		        			success:function(data){
    		        				html="<select id='editRole'>";
    		        				for(var i=0;i<data.value.length;i++){
    		        					if(data.value[i].status==0){
    		        						continue;
    		        					}
    		        					total+=i;
    		        					html+="<option value="+data.value[i].id+">"+data.value[i].name+"</option>"
    		        					
    		        				}
	    		        				if(total==0){
					        				html+="<option>无任何可选角色</option>";
					        			
					        				}
	    		        				html+="</select>" 
	    		        					
    		        					$("#roleEdit").append(html);
	    		        				if(total==0){
	    		        					$("#editRole").css('color','red');
	    		        				}
    		        			}
    		        		});
        		        	$("#role").find("option[value='"+row.role+"']").attr("selected","true");
        		        	$("#modal-UserRoleTree").attr("role",row.id)
        		        }  
        		        
        		    };
            	
            	var cols = [
     	                    {
     	        		        checkbox:true
     	        		    }, {
     	        		        field: 'id',
     	        		        title: '主键'
     	        		    }, {
     	        		        field: 'realName',
     	        		        title: '姓名'
     	        		    },{
     	        		        field: 'userName',
     	        		        title: '用户名'
     	        		    },{
     	        		        field: 'cellphone',
     	        		        title: '手机号码'
     	        		    },{
     	        		        field: 'role',
     	        		        title: '角色ID',
     	        		       visible:false
     	        		    },{
     	    			        field: 'roleName',
     	    			        title: '角色'
     	    		        },{
     	    			        field: 'status',
     	    			        title: '状态',
     	    			       visible:false
     	    		        }];
     	        //是否需要操作列
//     	        if(base.perList.user.edit || base.perList.user.del || base.perList.user.edit_dep || base.perList.user.distribute_role)
     		        cols.push({
     			    	align: 'center',
     			        title: '操作',
     			        events: operateEvents,
     			        formatter:F.operateFormatter
     			    });
     	        
     	       /**
            	 * 用户列表
            	 */	
        		F.table.init(F.basepath+'/account/list',cols);
        		
        		/**
    			 * 批量删除
    			 */
    			$('#delUsers').click(function(){
    				var ids = F.table.getIdSelections();
    				if(ids!=null&&ids.length>0){
    					base.bootConfirm("是否确定删除选定的"+ids.length+"个用户？",function(){
    						$.ajax({
    							url: F.basepath+'/account/deletes',
    		        			type:'POST',
    		        			data:{ids:ids.toString()},
    		        			success:function(data){
    		        				if(data.result>0){
    		        					F.reload();
    		        				}else{
    		        					alert("异常！")
    		        				}
    		        			}					
    						});
    					});
    				}else{
    					base.bootAlert({"ok":false,"msg":"请选择你要删除的用户！"});
    				}
    			});
    			/**
    			 * 带参查询
    			 */
    			$('#queryByAccount').click(function(){
    				
    				var userName=$("#accountName").val();
    				var realName=$("#realName").val();
    				var url= F.basepath+'/account/list?userName='+userName+'&realName='+realName;
    				$("#userTable").bootstrapTable('refresh',{url:url});
    				
    			});

    			/**
    			 * 打开模态框
    			 */
    			$('#addUser').click(function(){
    				var html='';
    				var total=0;
    				core.openModel('modal-UserTree','新增用户',function(){
    					$.ajax({
		        			url: F.basepath+'/account/roleList',
		        			type:'POST',
		        			success:function(data){
	        				for(var i=0;i<data.value.length;i++){
	        					if(data.value[i].status==0){
	        						continue;
	        						
	        					}
	        					html+="<option value="+data.value[i].id+">"+data.value[i].name+"</option>"
	        					total+=i;
	        				}
		        			if(total==0){
		        				html+="<option>无任何可选角色</option>"
		        					$("#roles").css('color','red');
		        			}
		        					$("#roles").append(html);
		        			}
		        		});
    					
    				
    				});
    				return false;
    			});
    			
    			
    			
    			
    			/**
    			 * 关闭模态框 
    			 */
    			$('#btnClose').click(function(){
    				core.closeModel('modal-UserTree');
    				F.reload();
    			});
    			
    			$('#EditbtnClose').click(function(){
    				core.closeModel('modal-EditUser');
    				F.reload();
    			});
    			
    			$('#btnDeptClose').click(function(){
    				core.closeModel('modal-UserDeptTree');
    			});
    			
    			$('#btnRoleClose').click(function(){
    				$("#editRole").remove();
    				core.closeModel('modal-UserRoleTree');
    			});
    			
    			/**
    			 * 修改用户
    			 */
    			$("#EditbtnSubmit").click(function(){
    				var accountId=$("#EditrealName").attr("accountId");
    				var realName=$("#EditrealName").val();
    				var cellphone=$("#Editcellphone").val();
    				if(realName.length<1||cellphone.length<11){return };
    				$.ajax({
						url: F.basepath+'/account/editAccount',
	        			type:'POST',
	        			data:{id:accountId,cellphone:cellphone,realName:realName},
	        			success:function(data){
	        				if(data.result>0){
	        					core.closeModel('modal-EditUser');
	        					F.reload();
	        				}else{
	        					alert("操作异常！")
	        				}
	        			}
	        			
					});
    				
    				
    				
    				
    			});
    			/**
    			 * 提交添加用户
    			 */
    			$('#btnSubmit').click(function(){
    				var role=$("#roles").val();
    				var userName=$("#userName").val();
    				var password=$("#password").val();
    				var repassword=$("#repassword").val();
    				var cellphone=$("#cellphone").val();
    				var realName=$("#realName").val();
    					$.ajax({
    						url: F.basepath+'/account/addAccount',
    	        			type:'POST',
    	        			data:{role:role,userName:userName,password:password,cellphone:cellphone,realName:realName},
    	        			success:function(data){
    	        				if(data.result>0){
    	        					core.closeModel('modal-UserTree');
    	        					F.reload();
    	        				}else{
    	        					$("#userName-error").html(data.msg);
    	        					$("#userName-error").css('color','red');
    	        				}
    	        			}
    	        			
    					});
//    				}
    				
                });
    			
    			$('#btnDeptSubmit').click(function(){
    				F.deptSubmit();
    			});
    			
    			
    			/**
    			 * 修改用户角色
    			 */
    			$('#btnRoleSubmit').click(function(){
    				var id=$("#modal-UserRoleTree").attr("role");
    				var role=$("#editRole").val();
    				$.ajax({
    					url: F.basepath+'/account/roleEdit',
	        			type:'POST',
	        			dataType:'json',
	        			data:{id:id,role:role},
	        			success:function(data){
	        				if(data.result>0){
	            				core.closeModel('modal-UserRoleTree');
	            				$("#role").remove();
	            				F.reload();
	        				}
	        			}
    				})
    			});
        		
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
//            	if (base.perList.user.edit_dep) {
            		_btnAction += "<a class='editDep btn btn-primary btn-small' href='#' title='启用或停用' style='margin-left:5px'>"+(row.status==1?"停用":"启用")+"</a>";
//            	}
//            	if (base.perList.user.distribute_role) {
            		_btnAction += "<a class='distributeRole btn btn-info btn-small' href='#' title='配置角色' style='margin-left:5px'>配置角色</a>";
//            	}
//            	if (base.perList.user.edit) {
            		_btnAction += "<a data-toggle='modal' class='editUser btn btn-success btn-small' href='#' title='编辑用户' style='margin-left:5px'>编辑</a>";
//            	}
//            	if (base.perList.user.edit) {
                	_btnAction += "<a data-toggle='modal' class='resetPwd btn btn-success btn-small' href='#' style='margin-left:5px'>重置密码</a>";
//                	}
//            	if (base.perList.user.del) {
            		_btnAction += "<a class='delUser btn btn-danger btn-small' href='#' title='删除用户' style='margin-left:5px'>删除</a>";
//            	}
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
			}
			,treeLoad:function(){
            	F.tree = core.initTree("departTree",F.basepath+'/main/department/get-all-departments',F.onClick);         
            },
			
			

        }
    }
);