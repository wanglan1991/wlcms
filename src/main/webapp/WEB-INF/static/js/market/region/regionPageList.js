jQuery(document).ready(function() {
	$.ajaxSetup({
		cache : false
	// 关闭AJAX相应的缓存
	});
	
	//加载数据
	loadRegion('country','province');
	loadData();
	
	//此处自定义方法，验证地域级别不能为空或-1
	jQuery.validator.addMethod("needLevel", function(value, element, param) {
		if($(param).val() == -1 || value==''){
			return false;
		}
		return true;
	}, $.validator.format("地域级别不能为空!"));
	
	//表单验证和提交
	jQuery('#addRegionForm').validate({
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
	
});

//加载地域列表
function loadData() {
	var regionName = $('#queryRegionName').val();
	var level = $('#queryLevel').val();
	jQuery('#RegionListTable').dataTable(
			{
				responsive : true,
				"bDestroy":true,
				"fnInitComplete": function() {
		            this.fnAdjustColumnSizing(true);
		            },
				"fnDrawCallback" : function(oSettings) {
					jQuery('#toPayTable_paginate ul').addClass(
							'pagination-active-danger');
					jQuery('div.dataTables_length select').removeClass(
							'form-control input-sm');
					jQuery('div.dataTables_length select').css({
						width : '60px'
					});
					jQuery('div.dataTables_length select').select2({
						minimumResultsForSearch : -1
					});
				},
				"bLengthChange" : false,// 每页显示的记录数
				"bPaginate" : true, // 是否显示分页
				"bSort" : false, // 是否支持排序功能
				"bInfo" : true, // 是否显示表格信息
				"bProcessing" : false, // 是否显示加载过程信息
				"bStateSave" : false, // 是否开启客户端状态记录
				"bFilter" : false, // 是否启用搜索
				// "aLengthMenu": [[5,10, 25, 50], [5,10, 25, 50]],
				// //定义每页显示数量
				"iDisplayLength" : 15, // 页大小，默认是10
				"sPaginationType" : "full_numbers",// 数字翻页样式
				"sScrollX" : "100%",
				"sScrollXInner" : "100%",
				"bScrollCollapse" : true,
				"bServerSide" : true,
				"sAjaxSource" : "region/listRegionPage",
				"columns" : [
				    {"sTitle" : "地域名称","data" : function(data, type, full) {return data.regionName} }, 
					{"sTitle" : "父ID","data" : function(data, type, full) {return data.parentId} },
					{"sTitle" : "父级地域","data" : function(data, type, full) {return data.parentName} },
					{"sTitle" : "地域级别","data" : function(data, type, full) {return data.levelName} },
					{"sTitle" : "地域级别ID","data" : function(data, type, full) {return data.level} },
					{"sTitle":"操作","data":  function(data, type, full) {
							return "<button type='button' class='btn btn-sm btn-warning' data-toggle='modal' data-target='#updateRegionModal' onclick=\"updateRegion('"+data.id+"','"+data.regionName+"','"+data.parentName+"','"+data.levelName+"')\">修改</button>  <a href='javascript:void(0);'<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='deleteRegion(" + data.id + ")'>删除</a> "; 
						}},
					],
				"fnServerData" : function(sSource, aoData, fnCallback) {
					aoData.push({"name": "regionName", "value":regionName});
					aoData.push({"name": "level", "value":level});
					$.ajax({
						'dataType' : 'json',
						'type' : 'POST',
						'url' : sSource,
						'data' : aoData,
						'success' : fnCallback
					});
				},

				language : {
					"url" : "static/js/dataTable_Chinese.json"

				}
			// 多语言配置

			});
}

//删除单条地域数据
function deleteRegion(id) {
	if(id == null || id=="") {
		alert("请选择要删除的行！");;
		return;
	}else {
		if(confirm("确定删除") != true) {
			return;
		}
		$.ajax({
			url : 'region/deleteRegion',// 跳转到 controller
			data : {
				id : id
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(result) {
				if(result == 0) {
					alert("删除失败，请重试！");
				}else {
					alert("删除成功！");
					loadData();
				}
			},
			error : function() {
				alert("服务器忙，请稍后再试！");
			}
		});
	}
}

//修改单条地域时加载数据
function updateRegion(id,regionName,parentName,levelName) {
	if(id == null || id=="") {
		alert("请选择要更新的行！");;
		return;
	}else {
		$('#updateId').val(id);
		$('#updateRegionName').val(regionName);
		$('#updateParentName').val(parentName);
		$('#updateLevelName').val(levelName);
	}
	
}

//提交修改
function submitUpdate() {
	var updateRegionName = $('#updateRegionName').val();
	var updateId = $('#updateId').val();
	if(updateId == null || updateId == "") {
		alert("请选择要更新的行！");;
		return;
	}else if(updateRegionName == null || updateRegionName == "") {
		alert("地域名称不能为空！");;
		return;
	}else {
		$.ajax({
			url : 'region/updateRegion',// 跳转到 controller
			data : {
				updateId : updateId,
				updateRegionName : updateRegionName
			},
			type : 'post',
			async: true,
			cache : false,
			dataType : 'json',
			success : function(result) {
				if(result == 0) {
					alert("修改失败，请重试！");
				}else {
					$('#updateRegionModal').modal('hide');
					alert("修改成功！");
					loadData();
				}
			},
			error : function() {
				alert("服务器忙，请稍后再试！");
			}
		});
	}
}

//地域级联
function loadRegion(para1, para2) {
	var region = $("#" + para1).val();
	if (region == '-1') {
		if (para1 == 'country') {
			$("#province").empty();
			$("#province").append("<option value='-1'>--请选择--</option>");
			$("#city").empty();
			$("#city").append("<option value='-1'>--请选择--</option>");
			$("#area").empty();
			$("#area").append("<option value='-1'>--请选择--</option>");
			$("#street").empty();
			$("#street").append("<option value='-1'>--请选择--</option>");
		}
		if (para1 == 'province') {
			$("#city").empty();
			$("#city").append("<option value='-1'>--请选择--</option>");
			$("#area").empty();
			$("#area").append("<option value='-1'>--请选择--</option>");
			$("#street").empty();
			$("#street").append("<option value='-1'>--请选择--</option>");
		}
		if (para1 == 'city') {
			$("#area").empty();
			$("#area").append("<option value='-1'>--请选择--</option>");
			$("#street").empty();
			$("#street").append("<option value='-1'>--请选择--</option>");
		}
		if (para1 == 'area') {
			$("#street").empty();
			$("#street").append("<option value='-1'>--请选择--</option>");
		}
		return;
	} else {
		$.ajax({
			url : 'region/loadCity',// 跳转到 action  
			data : {
				para : region
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				$("#" + para2).empty();
				$("#" + para2)
						.append("<option value='-1'>--请选择--</option>");
				$.each(data, function(index, item) {
					$("#" + para2).append(
							"<option value='"+item.id+"'>"
									+ item.regionName + "</option>");
				});
			},
			error : function() {
				alert("服务器忙，请稍后再试！");
			}
		});
	}
}


$(function () { $('#addRegionModal').on('show.bs.modal', function () {
		$("#regionName").val(null);
		loadRegion('country','province');
	})
 });

$(function () { $('#updateRegionModal').on('hide.bs.modal', function () {
	$('#updateId').val(null);
	$('#updateRegionName').val(null);
	$('#updateParentName').val(null);
	$('#updateLevelName').val(null);
})
});

