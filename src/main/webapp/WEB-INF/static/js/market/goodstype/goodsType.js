jQuery(document).ready(function(){
	$.ajaxSetup ({ 
	    cache: false //关闭AJAX相应的缓存 
	});
	jQuery('#type_level').select2();
	$("#parentDiv").hide();
	
	//加载数据
	loadData();
	
	//表单验证和提交
	jQuery('#addGoodsTypeForm').validate({
		highlight:function(element){
			jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		success:function(element){
			jQuery(element).closest('.form-group').removeClass('has-error');
		},
		submitHandler:function(form){
			var action_flag = $('#action_flag').val();
			var url = 'goodstype/addGoodsType';//新增url
			if(action_flag==2){
				url = 'goodstype/updateGoodsType';//修改url
			}
			//alert(url);
			
			var options = {
					url:url,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.success){
							//关闭模态窗口，并刷新数据
							$('#modal_edit').modal('hide')
							msg(1,data.msg);
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
			typeName:{required:true},
			level:{required:true},
			//parentId:{needValue:'#level'}
			parentId:{required:true}
		},
		messages:{
			typeName:{required:'类型名称不能为空'},
			level:{required:'级别不能为空'},
			parentId:{required:'父节点不能为空'}
		},	
	});
});

//加载数据，查询数据
function loadData(){
	var typeName = $('#type_name').val();
	var level = $('#type_level').val();
	jQuery('#goodsTypeListTable').dataTable({
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
				{ "data": function(data,type,full){return data.typeName}},
				{ "data": function(data,type,full){return data.parentId}},
				{ "data": function(data,type,full){return data.level}},
				{"data":  function(data, type, full) {return "<a href='javascript:void(0);' class='btn btn-sm btn-success' onclick='edit_goodsType(2,"+data.id+",\"" + data.typeName + "\"," + data.parentId + "," + data.level + ")'>修改</a>&nbsp;" +
						"<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='delGoodsType(" + data.id + ")'>删除</a>"; }},
		],
			   		
		"bServerSide": true,
		"sAjaxSource": "goodstype/listGoodsTypePage",
		"fnServerData":function(sSource,aoData,fnCallback){
			aoData.push({"name": "typeName", "value":typeName}); 
			aoData.push({"name": "level", "value":level}); 
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

function edit_goodsType(action_flag, id,typeName,parentId,level){
		$('#modal_edit').modal();
		$('#action_flag').val(action_flag);
		if(action_flag==1){
				//新增
				$('#edit_title').html('新增商品类型');
				$('#id').val('');
				$('#typeName').val('');
				setLevelData(1);
				$('#parentId').empty();
				$('#parentDiv').hide();
		}else if(action_flag==2){
				//修改
				$('#edit_title').html('修改商品类型');
				$('#id').val(id);
				$('#typeName').val(typeName);
				setLevelData(level);
				
				if(level==2){
					showParentIdDiv(parentId);
				}else{
					$('#parentId').empty();
					$('#parentDiv').hide();
				}
				//alert('parentId:' + parentId);
				//$('#parentId').val(parentId);
		}else{
				$('#edit_title').html('未知的动作请求');
		}
}

//分类发生变化，显示和隐藏父节点选项
function levelChange(obj){
	//alert(obj.value);
	if(obj.value==1){
		$("#parentDiv").hide();
	}else{
		showParentIdDiv(0);
	}
}

//显示父节点选择框，并且给父节点下拉框赋值
function showParentIdDiv(pid){
	$("#parentDiv").show();
	
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
			jQuery("#parentId").empty();
			jQuery("#parentId").prepend("<option value=''>---请选择---</option>");
			$.each(results, function(index, value) {
				if(pid!=0){
					if(pid == value.id){
						//alert('find the same value');
						jQuery("#parentId").append("<option value='" + value.id + " ' selected='true'>" + value.text + "</option>");
					}else{
						jQuery("#parentId").append("<option value='" + value.id + " '>" + value.text + "</option>");
					}
				}else{
					jQuery("#parentId").append("<option value='" + value.id + " '>" + value.text + "</option>");
				}
			});
		} 
	}); 
	
	//$('#parentId').val(pid);//设置为选中
	
	jQuery('#parentId').select2({
		maximumInputLength:10,
		formatInputTooShort: "请输入类型名称",
        formatNoMatches: "没有匹配到数据",
        formatSearching: "查询中..."
	});
}

$('#btnAddGoodsType1').on('click', function(){
	if($('#typeName').val()==''){
		$('#info').fadeOut('slow');
		$('#info').empty().text('错误：类型名称不能为空');
		$('#info').removeClass('alert alert-success').addClass('alert alert-danger');
		$('#info').fadeIn('slow');
		return false;
	}
	if($('#level').val()==''){
		$('#info').fadeOut('slow');
		$('#info').empty().text('错误：类型级别不能为空');
		$('#info').removeClass('alert alert-success').addClass('alert alert-danger');
		$('#info').fadeIn('slow');
		return false;
	}
	if($('#level').val()==2){
		if($('#parentId').val()==''){
			$('#info').fadeOut('slow');
			$('#info').empty().text('错误：父节点不能为空');
			$('#info').removeClass('alert alert-success').addClass('alert alert-danger');
			$('#info').fadeIn('slow');
			return false;
		}
	}
	
	$('#info').empty();
	$('#info').removeClass('alert alert-danger');
});

/**
 * 删除数据，确认窗口
 * @param id
 */
function delGoodsType(id){
	c_msg('提示','确认删除吗？该操作不可逆！');
	$("#confirm_delete #btnConfirm").attr('onclick','del_GoodsType('+id+');');
}
/**
 * 真实删除数据的地方
 * @param id
 */
function del_GoodsType(id){
	$.ajax({
		url : 'goodstype/deleteGoodsType',// 跳转到 controller
		data : {
			id : id
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
 * 初始化分类信息
 * @param level：设置选中那个项
 */
function setLevelData(level){
	var type_level = [{id:1,text:'一级分类'},{id:2,text:'二级分类'}];
	$('#level').val(level);
	jQuery('#level').select2({
		minimumResultsForSearch:-1,  //设置是否需要查询
		data:type_level 
	});
}