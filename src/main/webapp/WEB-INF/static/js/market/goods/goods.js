jQuery(document).ready(function(){
	$.ajaxSetup ({ 
	    cache: false //关闭AJAX相应的缓存 
	});
	
	//加载数据
	loadData();
	
	//加载一级分类数据
	setType1Select("type1_id",0);
	
	jQuery("#type2_id").empty();
	jQuery("#type2_id").prepend("<option value=''>---请选择二级分类---</option>");
	jQuery('#type2_id').select2();
	
});

function loadData(){
	var base_path = $('#base_path').val();
	var goodsName = $('#goods_name').val();
	var type1Id = $('#type1_id').val();
	var type2Id = $('#type2_id').val();
	jQuery('#goodsTable').dataTable({
		responsive:true,
		"bDestroy":true,
		"fnInitComplete": function() {
            this.fnAdjustColumnSizing(true);
            },
         "fnDrawCallback": function(oSettings) {
        		jQuery('#tenderTable_paginate ul').addClass('pagination-active');
        		// DataTables Length to Select2
        		jQuery('div.dataTables_length select').removeClass('form-control input-sm');
        		jQuery('div.dataTables_length select').css({width: '60px'});
        		jQuery('div.dataTables_length select').select2({
        				minimumResultsForSearch: -1
        			}); 			
        		},
		"bLengthChange":false,// 每页显示的记录数
		"bPaginate": true,  // 是否显示分页
		"bSort": false, // 是否支持排序功能
		"bInfo": true, // 是否显示表格信息
		"bProcessing":false,	// 是否显示加载过程信息
		"bStateSave":false,	// 是否开启客户端状态记录
		"bFilter":false,	// 是否启用搜索
		//"aLengthMenu": [[5,10, 25, 50], [5,10, 25, 50]],	//定义每页显示数量
		"iDisplayLength":15,  //页大小，默认是10
		"sPaginationType": "full_numbers",//数字翻页样式
		"sScrollX": "100%",
		"sScrollXInner": "100%",
		"bScrollCollapse": true,
		"columns": [
				{ "data": function(data,type,full){return data.goodsName}},
				{ "data": function(data,type,full){return data.type1Name}},
				{ "data": function(data,type,full){return data.type2Name}},
				{ "data": function(data,type,full){return data.defaultPrice}},
				{ "data": function(data,type,full){return data.ratio + '%'}},
				{ "data": function(data,type,full){
						return '<img src="' + base_path + data.picUrl + '" width="30px" height="30px" >'
					}
				},
				{"data":  function(data,type,full) {
					return "<a href='javascript:void(0);' class='btn btn-sm btn-success' onclick='edit_goods(2,"+JSON.stringify(data)+")'>修改</a>&nbsp;" +
					"<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='delGoods(" + data.id + ",\"" + data.picUrl + "\")'>删除</a>"; }
				}
		],
			   		
		"bServerSide": true,
		"sAjaxSource": "goods/listGoodsPage",
		"fnServerData":function(sSource,aoData,fnCallback){
			aoData.push({"name": "goodsName", "value":goodsName}); 
			aoData.push({"name": "type1Id", "value":type1Id}); 
			aoData.push({"name": "type2Id", "value":type2Id}); 
			$.ajax({
				'dataType':'json',
				'type':'post',
				'url':sSource,
				'data':aoData,
				'success':fnCallback
			});
		},
		
		language: {
		"url":"static/js/dataTable_Chinese.json"
	
	} // 多语言配置
			
	});	
}

//级联，显示二级分类
$("#type1_id").change(function(){
	var parentId = $("#type1_id").val();
	setType2Select(parentId,"type2_id",0);
});

/**
 * 打开新增或者修改窗口
 * @param flag：1，新增；2，修改
 * @param data：加载的数据信息
 */
function edit_goods(flag,data){
	$('#modal_add').modal();
	$('#action_flag').val(flag);
	
	if(flag==1){
		//新增
		$('#addGoodsForm')[0].reset();//重置表单
		$('#modal-title').html('新增商品');
		$('#picTitle').html("上传图片：");
		
		$('#ratio').val(100); //初始化为100，认为是100%配送价格
		$('#oldImg').hide();
		//初始化一级分类
		setType1Select("type1Id",0);
		$("#type2Id").empty();
		$("#type2Id").prepend("<option value=''>---请选择二级分类---</option>");
		$('#type2Id').select2();
	}else if(flag==2){
		var path = '';
		var base_path = $('#base_path').val();
		//修改
		$('#modal-title').html('修改商品');
		$('#picTitle').html("重新上传：");
		//alert(data.id);
		//加载数据信息
		$('#id').val(data.id);
		$('#goodsName').val(data.goodsName);
		$('#defaultPrice').val(data.defaultPrice);
		$('#ratio').val(data.ratio);
		$('#picUrl').val(data.picUrl);
		$('#oldPicUrl').val(data.picUrl);//旧图片的地址，传到后台request接收，和picUrl对比，如果不一样，删除旧文件，保存新数据
		path = base_path + data.picUrl;
		$('#oldImg').show();
		$('#old_img').attr('src', path);
		
		//给图片上传插件显示已经上传的图片
		//先清空已有的图片，还有遇到bug问题，以后再解决
//		var mockFile = {name: '', size :123};
//		myDropzone.emit("addedfile",mockFile);
//		myDropzone.emit("thumbnail", mockFile, path);
//		myDropzone.createThumbnailFromUrl(file, path, callback, crossOrigin);
//		var existingFileCount = 1;
//		myDropzone.options.maxFiles = myDropzone.options.maxFiles - existingFileCount;
		
		
		//初始化一级分类
		setType1Select("type1Id",data.type1Id);
		//初始化二级分类
		setType2Select(data.type1Id,"type2Id",data.type2Id);
	}else{
		//未知的操作类型
	}
}

//级联，显示二级分类
$("#type1Id").change(function(){
	var parentId = $("#type1Id").val();
	setType2Select(parentId,"type2Id",0);
});

//表单验证和提交
jQuery('#addGoodsForm').validate({
	highlight:function(element){
		jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	},
	success:function(element){
		jQuery(element).closest('.form-group').removeClass('has-error');
	},
	submitHandler:function(form){
		var action_flag = $('#action_flag').val();
		var url = 'goods/addGoods';//新增
		if(action_flag==2){
			//修改
			url = 'goods/updateGoods';
		}
		//var url = 'www.baidu.com';
		var options = {
				url:url,
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.success){
						//关闭模态窗口，并刷新数据
						$('#modal_add').modal('hide');
						msg(1,'操作成功');
						/**
						 * 当模态窗口完全隐藏时，触发该事件，临时使用，使得页面重新加载（刷新页面），并不只是刷新数据
						 */
						$('#modal_add').on('hidden.bs.modal',function(){
							menuClick(4);
						});
//						myDropzone.removeAllFiles();
						//loadData();
						//清空文件上传控件里面的图片
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
		goodsName:{required:true},
		type1Id:{required:true},
		type2Id:{required:true},
		defaultPrice:{
			required:true,
			inputPrice:true
		},
		ratio:{
				required:true,
				isNaN:true,
				gt:'0',
				lt:'100'
				}
	},
	messages:{
		goodsName:{required:'商品名称不能为空'},
		type1Id:{required:'一级分类不能为空'},
		type2Id:{required:'二级分类不能为空'},
		defaultPrice:{
			required:'默认价格不能为空',
			inputPrice:'请输入整数或者两位小数'
		},
		ratio:{
			required:'配送金额比例不能为空',
			isNaN:'请输入整数',
			gt:'必须大于0',
			lt:'必须小于或等于100'
		}
	},	
});

/**
 * 初始化文件上传控件
 */
var pic="";
var myDropzone= new Dropzone("div#pic_url", {
	url:"goods/picUpload",
	maxFiles:1, //上传文件数
	maxFilesize:1, //文件大小限制，单位M
	addRemoveLinks: true,
	acceptedFiles:".jpg,.png,.gif",  //限制文件类型
	uploadMultiple:true,
	dictDefaultMessage:"请选择要上传的图片文件",
	dictInvalidFileType:"只允许上传jpg,png,gif类型的文件",
	dictFileTooBig:"文件大小超过1MB，无法上传",
	dictMaxFilesExceeded:"超过最大可上传的文件数量",
	dictRemoveFile:"移除",
	autoProcessQueue: true,
	parallelUploads: 100,
	init: function() {
        this.on("removedfile", function(file) {
			if(file.status=="success"){
			    var param={};
				var ul="goods/deletePic";
				param["fileLink"]= file._removelink;
                $.post(ul,param,function(data){
                	if(data==1){
                		//删除成功
                		pic = pic.replace(file._removelink,"");
    	                if(pic=="null"){
    	                	pic="";
    		            }
    	                $("#picUrl").val(pic);
                	}
                });
            }
            if(file.status=="failure"){
                this.options.maxFiles = myDropzone.options.maxFiles - 1;
            } 
        });
        this.on("successmultiple", function(files, responseText) {
	        var data = eval('('+responseText+')');
	        for(var i=0;i<data.length;i++){
	            if(data[i].station=="false"){
	                files[i].previewTemplate.appendChild(document.createTextNode("上传失败！"));
	                this.options.maxFiles = myDropzone.options.maxFiles + 1;
	                files[i].status="failure";
	            }else{
	                files[i]._removelink  = data[i].msg;
    		        files[i]._removeLink.textContent="删除";
    		        files[i].previewTemplate.appendChild(document.createTextNode("上传成功！"));  
    		        pic=pic+data[i].msg; 
    		        $("#picUrl").val(pic);
//    		        myDropzone.removeFile(files[i]);
	            }
	        }
	    });
    }
});

function delGoods(id,picurl){
	c_msg('提示','确认删除吗？该操作不可逆！');
	$("#confirm_delete #btnConfirm").attr('onclick','del_Goods(' + id + ',\'' + picurl + '\');');
}

function del_Goods(id,picurl){
//	alert(picurl);
	$.ajax({
		url : 'goods/deleteGoods',// 跳转到 controller
		data : {
			id : id,
			picurl : picurl
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if(result == 0) {
				msg(0, "删除失败，请重试！");
			}else {
				msg(1, "删除成功！");
				loadData();
				//删除成功后，移除onclick事件
				$('#btnConfirm').attr('onclick', '');
			}
		},
		error : function() {
			msg(0,"服务器忙，请稍后再试！");
		}
	});
}

/**
 * 加载一级分类数据
 * @param obj：加载对象
 * @param selectVal：select的值，初始化为0，编辑修改的时候，为数据库保存的值
 */
function setType1Select(obj,selectVal){
	//加载一级分类数据
	var param = {};
	param["parentId"] = 0;
	var url = "goodstype/queryByParentId";
	
	jQuery.ajax({
		url: url, 
		data: param,
		dataType: "json", 
		async: false,
		success: function(results) { 
			//先清空数据
			jQuery("#" + obj).empty();
			jQuery("#" + obj).prepend("<option value=''>---请选择一级分类---</option>");
			$.each(results, function(index, value) {
				if(selectVal!=0){
					if(selectVal == value.id){
						jQuery("#" + obj).append("<option value='" + value.id + " ' selected='true'>" + value.text + "</option>");
					}else{
						jQuery("#" + obj).append("<option value='" + value.id + " '>" + value.text + "</option>");
					}
				}else{
					jQuery("#" + obj).append("<option value='" + value.id + " '>" + value.text + "</option>");
				}
			});
		} 
	}); 
	
	jQuery("#" + obj).select2();
}

/**
 * 加载二级分类数据
 * @param parentId：一级分类id
 * @param obj：加载对象
 * @param selectVal：select的值，初始化为0，编辑修改的时候，为数据库保存的值
 */
function setType2Select(parentId,obj,selectVal){
	var param = {};
	param["parentId"] = parentId;
	var url = "goodstype/queryByParentId";
	
	jQuery.ajax({
		url: url, 
		data: param,
		dataType: "json", 
		async: false,
		success: function(results) { 
			//先清空数据
			jQuery("#" + obj).empty();
			jQuery("#" + obj).prepend("<option value=''>---请选择二级分类---</option>");
			$.each(results, function(index, value) {
				if(selectVal!=0){
					if(selectVal == value.id){
						jQuery("#" + obj).append("<option value='" + value.id + " ' selected='true'>" + value.text + "</option>");
					}else{
						jQuery("#" + obj).append("<option value='" + value.id + " '>" + value.text + "</option>");
					}
				}else{
					jQuery("#" + obj).append("<option value='" + value.id + " '>" + value.text + "</option>");
				}
			});
		} 
	}); 
	
	jQuery("#" + obj).select2();
}

/**
 * 生成模板数据
 */
function createExcelModel(){
	c_msg('提示','确认重新生成模板？');
	$("#confirm_delete #btnConfirm").attr('onclick','createExcel();');
}

function createExcel(){
	$.ajax({
		url : 'goods/createExcelModel',// 跳转到 controller
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if(result.success) {
				msg(1, result.msg);
			}else {
				msg(0, result.msg);
			}
		},
		error : function() {
			msg(0,"服务器忙，请稍后再试！");
		}
	});
}
