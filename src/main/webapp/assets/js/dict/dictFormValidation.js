function validateForm() {
	//表单验证和提交
	jQuery('#submit-form').validate({
		highlight:function(element){
			jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		success:function(element){
			jQuery(element).closest('.form-group').removeClass('has-error');
		},
		submitHandler:function(form){
			var url = 'region/addRegion';
			var options = {
					url:url,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.msg == "保存成功"){
							//关闭模态窗口，并刷新数据
							$('#addRegionModal').modal('hide')
							loadData();
						}else{
							//错误提示
							$('#info').fadeOut('slow');
							$('#info').empty().text(data.msg);
							$('#info').removeClass('alert alert-success').addClass('alert alert-danger');
							$('#info').fadeIn('slow');
						}
					}				
			};
			$(form).ajaxSubmit(options);
		},
		rules:{
			regionName:{required:true},
			level:{needLevel:'#level'},
//			parentId:{needValue:'#level'}
		},
		messages:{
			regionName:{required:'地域名称不能为空'},
			level:{required:'级别不能为空'},
//			parentId:{needValue:'父节点不能为空'}
		},	
	});
}	