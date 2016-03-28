jQuery(document).ready(function(){
	jQuery('#demoTable').dataTable({
		responsive:true,
		"fnDrawCallback": function(oSettings) {
			jQuery('#TotalTable_paginate ul').addClass('pagination-active-danger');
			// DataTables Length to Select2
			jQuery('div.dataTables_length select').removeClass('form-control input-sm');
			jQuery('div.dataTables_length select').css({width: '60px'});
			jQuery('div.dataTables_length select').select2({
				minimumResultsForSearch: -1
			}); 
		},
		"bLengthChange":false,//每页显示的记录数
		"bPaginate": true,  //是否显示分页
		"bSort": false, //是否支持排序功能
		"bInfo": true, //是否显示表格信息
		"bProcessing":true,	//是否显示加载过程信息
		"bStateSave":true,	//是否开启客户端状态记录
		"bFilter":false,	//是否启用搜索
		"aLengthMenu": [[5,10, 25, 50], [5,10, 25, 50]],	//定义每页显示数量
		//"sPaginationType": "full_numbers",//数字翻页样式
		"sScrollX": "100%",
		"sScrollXInner": "100%",
		"bScrollCollapse": true,
		"bServerSide": true,
		"sAjaxSource": "static/demoPage.txt",
		"aoColumns":[
					{ "sTitle":"标题1","data": function(data,type,full){return data.projectName}},
					{ "sTitle":"标题2","data": function(data,type,full){return data.issuerName}},
					{ "sTitle":"标题3","data": function(data,type,full){return data.money}},
					{ "sTitle":"标题4","data": function(data,type,full){return data.annualRate}},
					{ "sTitle":"标题5","data": function(data,type,full){return data.deadline}},
					{ "sTitle":"标题6","data": function(data,type,full){return data.process}},
					{ "sTitle":"标题7","data": function(data,type,full){return data.station}},			
					],
			"fnServerData":function(sSource,aoData,fnCallback){
						//aoData.push({"name": "projecttype", "value":"0"}); 
						//aoData.push({"name": "issurname", "value":"0"}); 
						//aoData.push({"name": "rate", "value":"0"}); 
						//aoData.push({"name": "deadline", "value":"0"}); 
				$.ajax({
					'dataType':'json',
					'type':'GET',
					'url':sSource,
					'data':aoData,
					'success':fnCallback
				});
			},
						
			language: {
					"url":"static/js/dataTable_Chinese.json"
			} //多语言配置
							
		});
});