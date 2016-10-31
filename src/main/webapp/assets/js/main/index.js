// 所有模块都通过 define 来定义
define(function (require, exports, module) {
    var base = require('base');
    base.init()
     var core = require('core');
    // 通过 require 引入依赖
    var F = module.exports = {
        basepath: '',
        icons:base.icons,
        init: function (_basepath) {
            F.basepath = _basepath;
            //菜单导航初始化
            $.ajax( {});
        }
        ,
        findMenus: function (menus) {}
    };
    
    $("#userCenter").click(function(){
    	core.openModel('modal-userCenter','个人信息编辑',function(){
    	}); 	
		$.ajax({
	  		  url:'/cms/account/info',
	  		  type:"POST",
	  		  success:function(data){
	  			$("#username").val(data.value.userName);
	  			$("#realName").val(data.value.realName);
	  			$("#telephone").val(data.value.cellphone);
	  			$("#roleName").val(data.value.roleName);
	  		  }
	    	})
    });

    $("#updatePassword").click(function(){
    	$("#pwd").show();
    })
    
    
    //修改密码校验
    $("#validPwd").click(function(){
    	var currentPwd = $("#currentPwd").val();
    	$.ajax({
  		  url:'/cms/account/validPassword',
  		  type:"POST",
  		  data:{password:currentPwd},
  		  success:function(data){
  			 if(data.result>0){
  				$("#pwd").hide();
  				$("#editPwd").show();
  			 }else{
  				 $("#currentPwd").val();
  				$("#userCenterMsg").html(data.msg);
  			 }
  			 
  		  }
    	})
    	
    	
    });
    
    
    //提交新密码
    $("#submitPwd").click(function(){
    	var password = $("#newPwd").val();
    	if(password.length<6){$("#userCenterMsg").html("密码长度至少大于6位！");return }
    	$.ajax({
    		  url:'/cms/account/updatePassword',
    		  type:"POST",
    		  data:{password:password},
    		  success:function(data){  
    			  if(data.result>0){
    				  window.location.href="/cms/user/exit";
    			  }else{
    				  $("#userCenterMsg").html("数据库异常！请与管理员联系")
    			  }
    		  }
      	});
    });
    
    //提交修改个人信息
    $("#submitAccountInfoBtn").click(function(){
    		var realname = $("#realName").val();
			var telephone = $("#telephone").val();
			$.ajax({
	    		  url:'/cms/account/updateAccountInfo',
	    		  type:"POST",
	    		  data:{realName:realname,cellphone:telephone},
	    		  success:function(data){  
	    			  if(data.result>0){
	    				  core.closeModel('modal-userCenter');
	    			  }else{
	    				  $("#userCenterMsg").html("操作数据库异常！请与管理员联系")
	    			  }
	    		  }
	      	});
    	
    });
    
});
