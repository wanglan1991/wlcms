define(function(require, exports, module) {
	var base = require('base');
	core = require('core');
	var editor =null;
	// 通过 require 引入依赖
	var F = module.exports = {
		init : function(_basepath) {
			var html ="<a href='#' id='messageSend' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>短信群发</a>&nbsp;&nbsp;"
				+"<a href='#' id='mailSend' class='btn  btn-small' style='margin-left:5px;margin-bottom:11px'>邮件群发</a>&nbsp;&nbsp;&nbsp;&nbsp;"
			$("#promotionToolsActions").append(html);
			
			
			
			
			//短信群发
			$("#messageSend").click(function(){
				$("#mail_send").hide();
				$("#message_send").show();
			});
			//邮件群发
			$("#mailSend").click(function(){
				if(editor==null){
					editor = core.setUeditor("mailContainer",'450','%80');
				}
				
				$("#message_send").hide();
				$("#mail_send").show();
			});
			
			/**
			 * 监听号码簿上传事件
			 */
			$("#sendSubmit").click(function(){
				if($("#message_content").val().length==0){
					alert("短信内容不能为空！");
					return ;
				}
				if($("#sendToCellphoneNumbers").val().length==0){
					alert("请选择上传通讯录列表再尝试提交！");
					return ;
				}
				
				if(confirm("确定要提交吗？")){
					$("#cellphoneNumbersUpload").ajaxForm({
						url:"/cms/promotion/sendMessage",
						type:"post",
						data:$('#cellphoneNumbersUpload').serialize(),
						beforeSend : function() {},
						success : function(data){
							
						}  });
					
				}
			});
			
			//发送邮件
			$("#mailSendSubmit").click(function(){
				var title = $("#mailTitle").val();
				var mailAddress = $("#sendToMaills").val();
				var content = editor.getContent();
				if(title.length<1){alert("邮件标题不能为空！");return ;}
				if(mailAddress.length<1){alert("收件地址不能为空！");return;}
				if(content.length<1){alert("邮件内容不能为空！");return;}
				
				
				
				$.ajax({
					url:"/cms/promotion/sendMaill",
					type:"post",
					data:{title:title,content:content,mallAddress:mailAddress}
					 });
			});
			
			
			
			
			$("#message_content").click(function(){
				
			var length =$("#message_content").val().length;
			if(length==0){
				$("#message_msg").html('');
			}
				$("#message_msg").html("已输入"+length+"个字符串，还可以输入"+(69-length)+"个字符串。");
			})
			
		}
		

	};
	

});