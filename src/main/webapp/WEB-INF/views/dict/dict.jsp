<%--
  User: chenxin
  Date: 16-4-14
  Time: 上午8:40
  desc: 数据字典页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class='span3 box bordered-box blue-border' style='margin-bottom:0;margin-left:0px'>
  <div class='box-header'>
    <div class='title'>部门树</div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-y'>
       	<ul id="departTree" class="ztree" style="height:365px;"></ul>
      </div>
    </div>
  </div>
</div>

<div class='span9 box bordered-box blue-border' style='margin-bottom:0;'>
  <div class='box-header' id="user-header">
    <div class='title'>用户管理</div>
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="dictTable"></table>
<!-- 			<table data-toggle="table" data-url="http://localhost:8080/cms/assets/js/dict/data1.json" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"> -->
<!-- 			    <thead> -->
<!-- 			    <tr> -->
<!-- 			        <th data-field="state" data-checkbox="true"></th> -->
<!-- 			        <th data-field="id" data-align="right" data-sortable="true">Item ID</th> -->
<!-- 			        <th data-field="value" data-align="center" data-sortable="true">Item Name</th> -->
<!-- 			        <th data-field="type" data-sortable="true">Item Price</th> -->
<!-- 			    </tr> -->
<!-- 			    </thead> -->
<!-- 			</table> -->
      </div>
    </div>
  </div>
</div>

<div class='modal hide fade' id='modal-UserTree' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>用户部门</label>
            <div class='controls'>
                <div class='input-append'>
               		<input type='hidden' id='id' name="id"/>
                    <input class='span6' id='dep' name="dep" type='hidden' />
                </div>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>账户</label>
            <div class='controls'>
                <input class='span8' id='account' required name="account" placeholder='账户名' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>密码</label>
            <div class='controls'>
                <input class='span8' id='password' required rangelength="[5,20]" name='password' placeholder='密码' type='password' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>重复密码</label>
            <div class='controls'>
                <input class='span8' id='repassword' required equalTo="#password" placeholder='重复密码' type='password' />
            </div>
        </div>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnClose" class='btn'>关闭</button>
        <button type="submit" id="btnSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>


<div class='modal hide fade' id='modal-UserDeptTree' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='userDept-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>用户部门</label>
            <div class='controls'>
                <div class='input-append'>
               		<input type='hidden' id='userId' name="userId"/>
                    <input class='span6' id='depId' name="depId" type='hidden' />
                </div>
            </div>
        </div>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnDeptClose" class='btn'>关闭</button>
        <button type="submit" id="btnDeptSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>

<div class='modal hide fade' id='modal-UserRoleTree' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='userRole-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>用户角色</label>
            <div class='controls'>
                <div class='input-append'>
               		<input type='hidden' id='userId' name="userId"/>
                    <input class='span6' id='roleId' name="roleId" type='hidden' />
                </div>
            </div>
        </div>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnRoleClose" class='btn'>关闭</button>
        <button type="button" id="btnRoleSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>

<script>
  seajs.use(['base','dict/dict'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
// 	$('#dictTable').bootstrapTable({
// 		  method: 'post',
// 		  url: 'http://localhost:8080/cms/assets/js/dict/data1.json',
// 		  dataType: "json",
// 		  striped: true,	 //使表格带有条纹
// 		  pagination: true,	//在表格底部显示分页工具栏
// 		  pageSize: 22,
// 		  pageNumber: 1,
// 		  pageList: [10, 20, 50, 100, 200, 500],
// 		  idField: "id",  //标识哪个字段为id主键
// 		  showToggle: false,   //名片格式
// 		  cardView: false,//设置为True时显示名片（card）布局
// 		  showColumns: true, //显示隐藏列  
// 		  showRefresh: true,  //显示刷新按钮
// 		  singleSelect: true,//复选框只能选择一条记录
// 		  search: false,//是否显示右上角的搜索框
// 		  clickToSelect: true,//点击行即可选中单选/复选框
// 		  sidePagination: "server",//表格分页的位置
// 		  queryParams: queryParams, //参数
// 		  queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求
// 		  toolbar: "#toolbar", //设置工具栏的Id或者class
		
		
		
//             columns: [{
//  		        checkbox:true
//  		    }, {
//  		        field: 'id',
//  		        title: '主键'
//  		    }, {
//  		        field: 'value',
//  		        title: '字典值'
//  		    },{
// 			        field: 'type',
// 			        title: '字典类型'
// 		        },{
// 			        field: 'status',
// 			        title: '状态'
// 		        },{
// 			        field: 'remark',
// 			        title: '备注'
// 	        } ],
// 	        silent: true,  //刷新事件必须设置
// 	        formatLoadingMessage: function () {
// 	            return "请稍等，正在加载中...";
// 	          },
// 	          formatNoMatches: function () {  //没有匹配的结果
// 	            return '无符合条件的记录';
// 	          },
// 	          onLoadError: function (data) {
// 	            $('#reportTable').bootstrapTable('removeAll');
// 	          },
// 	          onClickRow: function (row) {
// 	            window.location.href = "/qStock/qProInfo/" + row.ProductId;
// 	          },
//         });

</script>
</body>
</html>
