jQuery(document).ready(function(){
	$.ajaxSetup ({ 
	    cache: false //关闭AJAX相应的缓存 
	});
	
	//加载数据
	loadData();
	
	
});

function loadData(){
	var queryShopName = $('#queryShopName').val();
	var queryRegionName = $('#queryRegionName').val();
	var queryMobile = $('#queryMobile').val();
	jQuery('#shopTable').dataTable({
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
				{ "data": function(data,type,full){return data.shopName}},
				{ "data": function(data,type,full){return data.regionName}},
				{ "data": function(data,type,full){return data.shopTypeName}},
				{ "data": function(data,type,full){return data.telephone}},
				{ "data": function(data,type,full){return data.mobile}},
				{ "data": function(data,type,full){return data.minAmount}},
				{ "data": function(data,type,full){return data.address}},
				{ "data": function(data,type,full){return data.remark}},
				{ "data": function(data, type, full) {
					return "<button type='button' class='btn btn-sm btn-warning' data-toggle='modal' data-target='#modal_update' onclick=\"updateShop('"+data.id+"','"+data.shopName+"','"+data.regionName+"','"+data.shopTypeName+"','"+data.telephone+"','"+data.mobile+"','"+data.minAmount+"','"+data.wx_no+"','"+data.address+"')\">修改</button>  <a href='javascript:void(0);'<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='deleteShop(" + data.id + ")'>删除</a> " +
							"<a href='javascript:void(0);'<a href='javascript:void(0);' class='btn btn-sm btn-danger'  onclick='initShopGoods(" + data.id + ")'>商品管理</a>"; 
				}},
		],
			   		
		"bServerSide": true,
		"sAjaxSource": "shop/listShopPage",
		"fnServerData":function(sSource,aoData,fnCallback){
			aoData.push({"name": "shopName", "value":queryShopName}); 
			aoData.push({"name": "regionName", "value":queryRegionName}); 
			aoData.push({"name": "mobile", "value":queryMobile}); 
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

//打开新建窗口
$('#addShop').on('click', function(){
	$('#modal_add').modal();
	
//	$('#ratio').val(100); //初始化为100，认为是100%配送价格
	//初始化省，清空地域数据
	//加载省数据
	jQuery.ajax({
		url: "region/queryByParentId",
		data: {
			parentId : 1
		},
		dataType: "json", 
		async: false,
		success: function(results) {
			//先清空数据
			jQuery("#provinceId").empty();
			jQuery("#provinceId").prepend("<option value=''>--请选择省份--</option>");
			$.each(results, function(index, value) {
				jQuery("#provinceId").append("<option value='" + value.id + "'>" + value.regionName + "</option>");
				//alert(index+' '+value.id);
			});
		} 
	}); 
	
	jQuery('#provinceId').select2();
	
	jQuery("#cityId").empty();
	jQuery("#cityId").prepend("<option value=''>---请选择市---</option>");
	jQuery('#cityId').select2();
	
	jQuery("#areaId").empty();
	jQuery("#areaId").prepend("<option value=''>---请选择区---</option>");
	jQuery('#areaId').select2();
	
	jQuery("#regionId").empty();
	jQuery("#regionId").prepend("<option value=''>---请选择街道---</option>");
	jQuery('#regionId').select2();
	
	//初始化商店类型下拉框
	jQuery.ajax({
		url: "dict/queryDictByCondition",
		data: {
			type : 'shop_type'
		},
		dataType: "json", 
		async: false,
		success: function(results) {
			//先清空数据
			jQuery("#shopTypeId").empty();
			jQuery("#shopTypeId").prepend("<option value=''>--请选择商店类型--</option>");
			$.each(results, function(index, value) {
				jQuery("#shopTypeId").append("<option value='" + value.id + "'>" + value.name + "</option>");
			});
		} 
	});
	
	jQuery('#shopTypeId').select2();
	
});

//级联，根据省显示市
$("#provinceId").change(function(){
	var param = {};
	param["parentId"] = $("#provinceId").val();
	var url = "region/queryByParentId";
	
	jQuery.ajax({
		url: url, 
		data: param,
		dataType: "json", 
		async: false,
		success: function(results) { 
			//先清空数据
			jQuery("#cityId").empty();
			jQuery("#cityId").prepend("<option value=''>---请选择市---</option>");
			$.each(results, function(index, value) {
				jQuery("#cityId").append("<option value='" + value.id + "'>" + value.regionName + "</option>");
				//alert(index+' '+value.id);
			});
		} 
	}); 
	
	jQuery('#cityId').select2();
});

//级联，根据市显示区
$("#cityId").change(function(){
	var param = {};
	param["parentId"] = $("#cityId").val();
	var url = "region/queryByParentId";
	
	jQuery.ajax({
		url: url, 
		data: param,
		dataType: "json", 
		async: false,
		success: function(results) { 
			//先清空数据
			jQuery("#areaId").empty();
			jQuery("#areaId").prepend("<option value=''>---请选择区---</option>");
			$.each(results, function(index, value) {
				jQuery("#areaId").append("<option value='" + value.id + "'>" + value.regionName + "</option>");
				//alert(index+' '+value.id);
			});
		} 
	}); 
	
	jQuery('#areaId').select2();
});

//级联，根据区显示街道
$("#areaId").change(function(){
	var param = {};
	param["parentId"] = $("#areaId").val();
	var url = "region/queryByParentId";
	
	jQuery.ajax({
		url: url, 
		data: param,
		dataType: "json", 
		async: false,
		success: function(results) { 
			console.info(results);
			//先清空数据
			jQuery("#regionId").empty();
			jQuery("#regionId").prepend("<option value=''>---请选择街道---</option>");
			$.each(results, function(index, value) {
				console.info(results);
				jQuery("#regionId").append("<option value='" + value.id + "'>" + value.regionName + "</option>");
				//alert(index+' '+value.id);
			});
		} 
	}); 
	jQuery('#regionId').select2();
});

//表单验证和提交
jQuery('#addShopForm').validate({
	highlight:function(element){
		jQuery(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	},
	success:function(element){
		jQuery(element).closest('.form-group').removeClass('has-error');
	},
	submitHandler:function(form){
		var url = 'shop/addShop';
		var options = {
				url:url,
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.msg == "保存成功"){
						//关闭模态窗口，并刷新数据
						$('#modal_add').modal('hide')
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
		shopName:{required:true},
		regionId:{required:true},
		shopTypeId:{required:true},
		mobile:{
				required:true,
				isMobile:true,
				},
		minAmount:{
			required:true,
			inputPrice:true
		},
		adress:{required:true},
	},
	messages:{
		shopName:{required:'商店名称不能为空'},
		regionId:{required:'所属地域不能为空'},
		shopTypeId:{required:'商店类型不能为空'},
		mobile:{
			required:'手机号码不能为空',
			isMobile:'请输入正确的手机号码'
		},
		minAmount:{
			required:'起送价格不能为空',
			inputPrice:'请输入整数或者两位小数'
		}
		
	},	
});

//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//加载修改时的信息
function updateShop(id,shopName,regionName,shopTypeName,telephone,mobile,minAmount,wx_no,address) {
	if(id == null || id=="") {
		alert("请选择要更新的行！");;
		return;
	}else {
		$("#updateId").val(id);
		$("#updateShopName").val(shopName);
		$("#updateRegionName").val(regionName);
		$("#updateShopTypeName").val(shopTypeName);
		$("#updateTelephone").val(telephone);
		$("#updateMobile").val(mobile);
		$("#updateMinAmount").val(minAmount);
		$("#updateWx_no").val(wx_no);
		$("#updateAddress").val(address);
	}
}

//提交修改
function submitUpdate() {
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	
	var updateId = $('#updateId').val();
	var updateShopName = $('#updateShopName').val();
	var updateTelephone = $('#updateTelephone').val();
	var updateMobile = $('#updateMobile').val();
	var updateMinAmount = $('#updateMinAmount').val();
	var updateWx_no = $('#updateWx_no').val();
	var updateAddress = $('#updateAddress').val();
	var updateRemark = $('#updateRemark').val();
	
	if(updateId == null || updateId == "") {
		alert("请选择要更新的行！");
		return;
	}else if(updateShopName == null || updateShopName == "") {
		alert("商店名称不能为空！");
		return;
	}else if(updateMobile == null || updateMobile == "") {
		alert("联系手机不能为空！");
		return;
	}else if(!reg.test(updateMobile)) {
		alert("请输入正确的手机号码！");
		return;
	}else if(updateMinAmount == null || updateMinAmount == "") {
		alert("起送金额不能为空！");
		return;
	}else if(isNaN(updateMinAmount)) {
		alert("起送金额必须是数字！");
		return;
	}else if(updateAddress == null || updateAddress == "") {
		alert("地址不能为空！");
		return;
	}else {
		$.ajax({
			url : 'shop/updateShop',// 跳转到 controller
			data : {
				updateId : updateId,
				updateShopName : updateShopName,
				updateTelephone : updateTelephone,
				updateMobile : updateMobile,
				updateMinAmount : updateMinAmount,
				updateWx_no : updateWx_no,
				updateAddress : updateAddress,
				updateRemark : updateRemark
			},
			type : 'post',
			async: true,
			cache : false,
			dataType : 'json',
			success : function(result) {
				if(result == 0) {
					alert("修改失败，请重试！");
				}else {
					$('#modal_update').modal('hide');
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

//删除
function deleteShop(id) {
	if(id == null || id=="") {
		alert("请选择要删除的行！");;
		return;
	}else {
		if(confirm("确定删除") != true) {
			return;
		}
		$.ajax({
			url : 'shop/deleteShop',
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

/**
 * 加载商品管理页面
 * @param shopId
 */
function initShopGoods(shopId){
	var url = 'shopgoods/toListShopGoods?shopId=' + shopId;
	var text = '商品管理';
	var class_name='fa fa-info';
	//用admin_menu.js中的loadView来加载商品管理页面
	loadView(url, text, class_name)
}
