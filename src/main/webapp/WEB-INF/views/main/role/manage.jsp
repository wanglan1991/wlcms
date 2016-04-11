<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 2015/8/28
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<<<<<<< HEAD
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
=======
>>>>>>> upstream/master
<html>
<head>
  <title></title>
</head>
<body>
<div class='span12 box bordered-box blue-border' style='margin-bottom:0;'>
  <div class='box-header' id="role-header">
    <div class='title'>角色设置</div>
<<<<<<< HEAD
    	<div class="actions">
    		<a href="#" id="addRole" data-toggle="modal" class="btn btn-success btn-small" style="margin-left:5px">
    		<i class="icon-plus"></i>添加</a>
    		<a href="#" id="delRoles" class="btn btn-danger btn-small" style="margin-left:5px">
    		<i class="icon-remove"></i>删除</a>
    	</div>
=======
>>>>>>> upstream/master
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
<<<<<<< HEAD
<!--         <table id="roleTable"></table> -->
				<div class="fixed-table-container" style="height: 261px; padding-bottom: 40px;">
					<div class="fixed-table-header" style="margin-right: 0px;">
						<table class="table table-hover" id="table"  style="width: 1608px;">
							
								<tr>
									<td class="bs-checkbox " style="widtd: 36px; " data-field="0" tabindex="0"> 
										<div class="td-inner ">
											<input name="btSelectAll" type="checkbox">
										</div>
										<div class="fht-cell">
										</div>
									</td>
									<td style="" data-field="key" tabindex="0">
										<b>角色名称</b>
									</td>
									<td style="" data-field="name" tabindex="0">
									 	<b>角色编码</b> 
									</td>
									<td style="" data-field="name" tabindex="0">
									  	<b>状态</b>  
									</td>
									<td style="" data-field="name" tabindex="0">
									   	 <b>创建时间</b>
									</td>
									<td style="" data-field="name" tabindex="0">
									    <div class="td-inner "><b>操作</b></div>
										<div class="fht-cell"></div>
									</td>
								</tr>
								<c:if test="${list!=null}">
									<c:forEach var="l" items="${list}">
										<tr data-index='0'>
											<td class='bs-checkbox'>
												<input data-index='0' name='btSelectItem' type='checkbox'>
											</td>
											<td style=''>${l.name}</td>
											<td style=''>${l.encoding}</td>
											<td style=''><div class="switch" data-on="primary" data-off="info"><input type="checkbox" checked /></div></td>
											<td style=''><fmt:formatDate value="${l.createTime}" type="date" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
											<td style='text-align: center; '>
												<a class='distributePermission btn btn-primary btn-small' href='#' title='菜单授权' style='margin-left:5px'>授权</a>
												<a class='checkPermission btn btn-info btn-small' href='#' title='查看授权' style='margin-left:5px'>查看</a>
												<a class='editRole btn btn-success btn-small'  href="javascript:edit('${l.id}','${l.name}','${l.encoding}')"  title='编辑角色' style='margin-left:5px'>编辑</a>
												<a class='delRole btn btn-danger btn-small' href='javascript:del(${l.id})' title='删除角色' style='margin-left:5px'>删除</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${list==null}">
									<tr>
										<td cowspan='3'>当前没有查询任何记录！</td>
									</tr>
								</c:if>
						</table>
					</div>
				</div>
=======
        <table id="roleTable"></table>
>>>>>>> upstream/master
      </div>
    </div>
  </div>
</div>

<div class='modal hide fade' id='modal-Role' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
<<<<<<< HEAD
    <form class='form validate-form' id='submit-form'  role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>角色名称</label>
            <div class='controls'>
                <input class='span8' id='roleName'  placeholder='角色名称' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>角色编码</label>
            <div class='controls'>
                <input class='span8' id='roleEncoding'  placeholder='角色编码' type='text' />
=======
    <form class='form validate-form' id='submit-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>角色键值</label>
            <div class='controls'>
            	<input type='hidden' id='id' name="id"/>
                <input class='span8' id='key' required name="key" placeholder='角色键值' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>角色名称</label>
            <div class='controls'>
                <input class='span8' id='name' required name='name' placeholder='角色名称' type='text' />
>>>>>>> upstream/master
            </div>
        </div>
    </div>
    <div class='modal-footer'>
<<<<<<< HEAD
    	  <msg style="color:red" id="msg" ></msg>
        <button type="button" id="btnClose" class='btn'>关闭</button>
        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
=======
        <button type="button" id="btnClose" class='btn'>关闭</button>
        <button type="submit" id="btnSubmit" class='btn btn-primary'>保存</button>
>>>>>>> upstream/master
    </div>
    </form>
</div>

<<<<<<< HEAD
<div class='modal hide fade' id='edit-Role' roleId role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-form'  role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label' >角色名称</label>
            <div class='controls'>
                <input class='span8' id='Name'  placeholder='角色名称' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>角色编码</label>
            <div class='controls'>
                <input class='span8' id='Encoding'  placeholder='角色编码' type='text' />
            </div>
        </div>
    </div>
    <div class='modal-footer'>
    	  <msg style="color:red" id="msg1" ></msg>
        <button type="button" data-dismiss="modal" >关闭</button>
        <button type="button" id="btnSubmit1" class='btn btn-primary'>保存</button>
    </div>
    </form>
	
</div>





=======
>>>>>>> upstream/master
<div class='modal hide fade' id='modal-DistributePermission' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-distributePermissionTreeForm' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
       <input type="hidden" id="roleId"/>
       <input type="hidden" id="distributePermissionTreeHidden"/>
       <ul id="distributePermissionTree" class="ztree" style="background: #f0f6e4;width:100%;height:300px;overflow-y:scroll;overflow-x:auto;"></ul>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnDistributePermissionClose" class='btn'>关闭</button>
        <button type="button" id="btnDistributePermissionSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>

<div class='modal hide fade' id='modal-CheckPermission' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
       <ul id="checkPermissionTree" class="ztree" style="background: #f0f6e4;width:100%;height:300px;overflow-y:scroll;overflow-x:auto;"></ul>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnCheckPermissionClose" class='btn'>关闭</button>
    </div>
</div>
<script>
<<<<<<< HEAD
//引入模块
var core;
define(function (require, exports, module) {
	core = require('core');
});	

     
seajs.use(['base','main/role/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
//打开新增角色
$("#addRole").on('click',function(){
	core.openModel('modal-Role','新增角色');
	return false;
})
//提交角色
$("#btnSubmit").on('click',function(){
	var name=$("#roleName").val();
	var encoding=$("#roleEncoding").val();
	if(name.length<1||encoding<1){
		$("#msg").html("角色名称或角色编码不能为空！");
		return ;
	}else{
		$("#msg").html("");
	}
	
	$.ajax({
		url:"${ctx}/role/add",
		type:"POST",
		data:{name:name,encoding:encoding},
		success:function(data){
			if(data.result>0){
				core.closeModel('modal-Role');
				window.location.href="/cms/role/manage";
			}else{
				$("#msg").html(data.msg);
			}
		}
	})
		
})
//删除角色
function del(id){
	var r=confirm("确定要删除吗？");
	if(r){
		$.ajax({
			url:"${ctx}/role/delete",
			type:"POST",
			data:{id:id},
			success:function(data){
				window.parent.frames[ "roleTable"].location.reload()
			}
		})		
	}
}
//弹出编辑模态框
function edit(id,name,encoding){
 	core.openModel('edit-Role','编辑角色');
	$("#Name").val(name);
	$("#Encoding").val(encoding);
	$("#edit-Role").attr("roleId",id);
}
//保存角色
$("#btnSubmit1").on("click",function(){
		var name=$("#Name").val();
		var encoding=$("#Encoding").val();
		var id=$("#edit-Role").attr("roleId");
		if(Name.length<1||Encoding<1){
			$("#msg1").html("角色名称或角色编码不能为空！");
			return ;
		}else{
			$("#msg1").html("");
		}
		$.ajax({
			url:"${ctx}/role/update",
			type:"POST",
			data:{id:id,name:name,encoding:encoding},
			success:function(data){
				if(data.result>0){
					core.closeModel('edit-Role');
					window.location.href="/cms/role/manage";
				}else{
					$("#msg1").html(data.msg);
				}
			}
		})		
})
	
	
	
	
	
	
	



=======
  seajs.use(['base','main/role/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
>>>>>>> upstream/master
</script>
</body>
</html>
