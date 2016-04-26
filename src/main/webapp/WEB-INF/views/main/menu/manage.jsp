<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<div class='span3 box bordered-box blue-border' style='margin-bottom:0;margin-left:0px'>
  <div class='box-header'>
    <div class='title'>菜单树</div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-y'>
       	<ul id="menuTree" class="ztree" style="height:365px;"></ul>
      </div>
    </div>
  </div>
</div>

<div class='span9 box bordered-box blue-border' style='margin-bottom:0;'>
  <div class='box-header' id="menu-header">
    <div class='title'>菜单设置</div>
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="menuTable"></table>
      </div>
    </div>
  </div>
</div>
<div class='modal hide fade' id='modal-Menu' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-form' method="post" role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>上级菜单</label>
            <div class='controls'>
                <div class='input-append'>
               		<input type='hidden' id='id' name="id"/>
                    <input class='span6' id='parentId' name="parentId" type='hidden' />
                </div>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>菜单名称</label>
            <div class='controls'>
                <input class='span8' id='name' required name="name" placeholder='菜单名称' type='text' />
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>菜单图片</label>
            <div class='controls'>
                <input class='span8' id='icon' name="icon" placeholder='菜单图片' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>菜单URL</label>
            <div class='controls'>
                <input class='span8' id='url' name="url" placeholder='菜单地址' type='text' />
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>排序</label>
            <div class='controls'>
                <input class='span8' id='order' required name='order' digits="true" placeholder='排序' type='text' />
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
       <input type="hidden" id="menuId"/>
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
  seajs.use(['base','main/menu/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
</script>
</body>
</html>
