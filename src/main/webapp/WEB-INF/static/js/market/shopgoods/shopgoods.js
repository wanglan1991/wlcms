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

//加载商品数据
function loadData(){
	var goodsName = $('#goods_name').val();
	var type1Id = $('#type1_id').val();
	var type2Id = $('#type2_id').val();
	jQuery('#shopGoodsTable').dataTable({
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
				{ "data": function(data,type,full){return data.goodsId}},
				{ "data": function(data,type,full){return data.price}},
				{ "data": function(data,type,full){
						if(data.stock==0){
							return '否';
						}else{
							return '是';
						}
					}
				},
				{ "data": function(data,type,full){
						if(data.available==0){
							return '否';
						}else{
							return '是';
						}
					}
				},
				{"data":  function(data,type,full) {
					return "<a href='javascript:void(0);' class='btn btn-sm btn-success' onclick='edit_goods(2,"+JSON.stringify(data)+")'>修改</a>&nbsp;" +
					"<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='delGoods(" + data.id + ",\"" + data.picUrl + "\")'>删除</a>"; }
				}
		],
			   		
		"bServerSide": true,
		"sAjaxSource": "shopgoods/listShopGoods",
		"fnServerData":function(sSource,aoData,fnCallback){
//			aoData.push({"name": "goodsName", "value":goodsName}); 
//			aoData.push({"name": "type1Id", "value":type1Id}); 
//			aoData.push({"name": "type2Id", "value":type2Id}); 
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
 * 返回
 */
function returnup(){
	menuClick(1);
}
/**
 * 打开导入窗口
 */
function excelImport(){
	$('#modal_inport').modal();
}

//表单验证和提交
jQuery('#excelImportForm').validate({
	highlight:function(element){
		jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	},
	success:function(element){
		jQuery(element).closest('.form-group').removeClass('has-error');
	},
	submitHandler:function(form){
		var url = 'shopgoods/excelImport';
		var options = {
				url:url,
				type:'post',
				//data:$("#excelFile").serialize(),     //此处表单序列化
				dataType:'json',
				success:function(data){
					if(data.success){
						//关闭模态窗口，并刷新数据
						$('#modal_inport').modal('hide');
						msg(1,'操作成功');
						loadData();
					}else{
						//错误提示
						msg(0,data.msg);
					}
				}				
		};
		$(form).ajaxSubmit(options);
	},
	rules:{
//		goodsName:{required:true},
		file:{required:true}
	},
	messages:{
		file:{required:'请选择一个excel文件'}
	},	
});

/**
 * 下载模板
 */
function download(){
	
}