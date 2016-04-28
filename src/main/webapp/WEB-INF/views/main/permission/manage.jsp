<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <div class='box-header' id="perm-header">
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="permTable"></table>
      </div>
    </div>
  </div>

<div class='modal hide fade' id='modal-addPerm' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>权限类型</label>
            <div class='controls'>
               <select id='addPermType'>
               		<option value='-1'>--新增类型--</option>
               	 	<option value='1'>模块</option>
               	 	<option value='2'>页面</option>
               	 	<option value='3'>按钮</option>
               </select>
                <span id="PermType-error" class="help-block error"></span>
            </div>
        </div>
        <div class='control-group' id="permissionParent" style="display: none;">
            <label class='control-label'>父级</label>
            <div class='controls'>
             	<select id='permPrent'></select>
                 <span id="permPrent-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>名称</label>
            <div class='controls'>
                <input class='span8' id='permName' required name='permName' maxlength='15'  placeholder='名称' type='text' />
                 <span id="permName-error" class="help-block error"></span>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>key值</label>
            <div class='controls'>
                <input class='span8' id='key' required name='key'  maxlength='30'  placeholder='key值' type='text' />
                 <span id="key-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>URL</label>
            <div class='controls'>
                <input class='span8' id='value' required name='value' maxlength='30'   placeholder='url' type='text' />
                 <span id="url-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group' id='orderNo'>
            <label class='control-label'>排序</label>
            <div class='controls'>
                <input class='span8' id='order' required name='order'  maxlength='4' onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"  placeholder='order' type='text' />
                 <span id="order-error" class="help-block error"></span>
            </div>
        </div>
        <div class="control-group" id="icons" style="display: none;">
            <label class="control-label">菜单图片</label>
            <div class="controls">
                <input class="span8" id="icon" name="icon" placeholder="菜单图片" type="text" style="display:none;">
                  <span id="icon-error" class="help-block error"></span>
            </div>
        </div> 
    </div>
    <div class='modal-footer'>
    	<msg id='msg'></msg>
        <button type="button" id="btnClose" class='btn'>关闭</button>

        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>



<div class='modal hide fade' id='modal-editPerm' Perm='' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <div class='modal-body'>
         <div class='control-group'>
            <label class='control-label'>名称</label>
            <div class='controls'>
                <input class='span8' id='editPermName' required name='editPermName' maxlength='15'  placeholder='名称' type='text' />
                 <span id="editPermName-error" class="help-block error"></span>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>key值</label>
            <div class='controls'>
                <input class='span8' id='editKey' required name='editKey' maxlength='30'  placeholder='key值' type='text' />
                 <span id="editKey-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>URL</label>
            <div class='controls'>
                <input class='span8' id='editValue' required name='value' maxlength='30'  placeholder='url' type='text' />
                 <span id="editUrl-error" class="help-block error"></span>
            </div>
        </div>
         <div class='control-group' id='editErderNo'>
            <label class='control-label'>排序</label>
            <div class='controls'>
                <input class='span8' id='editOrder' required name='order' maxlength='4'  placeholder='order' type='text' />
                 <span id="editOrder-error" class="help-block error"></span>
            </div>
        </div>
        <div class="control-group" id="editIcons">
            <label class="control-label">菜单图片</label>
            <div class="controls">
                <input class="span8" id="editIcon" name="editIcon" placeholder="菜单图片" type="text" style="display:none;">
                  <span id="editIcon-error" class="help-block error"></span>
            </div>
        </div> 
    </div>
    <div class='modal-footer'>
    	<msg id='msg'></msg>
        <button type="button" id="editBtnClose" class='btn'>关闭</button>

        <button type="button" id="editBtnSubmit" class='btn btn-primary'>保存</button>
    </div>
    </form>
</div>





<script>
  seajs.use(['base','main/permission/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
</script>
</body>
</html>
