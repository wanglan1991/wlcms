<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 2015/8/28
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <div class='box-header' id="role-header">
    <div class='title'>角色设置</div>
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="roleTable"></table>
      </div>
    </div>
  </div>

<div class='modal hide fade' id='modal-Role' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>角色编码</label>
            <div class='controls'>
            	<input type='hidden' id='id' name="id"/>
                <input class='span8' id='encoding' required name="encoding" maxlength='15' placeholder='角色键值' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>角色名称</label>
            <div class='controls'>
                <input class='span8' id='name' required name='name' maxlength='15'  placeholder='角色名称' type='text' />
            </div>
        </div>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnClose" class='btn'>关闭</button>
        <button type="submit" id="btnSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>

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
  seajs.use(['base','main/role/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
</script>
</body>
</html>
