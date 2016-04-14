<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 15-9-3
  Time: 上午8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<!-- <div class='span9 box bordered-box blue-border' style='margin-bottom:0;'> -->
  <div class='box-header' id="user-header">
    <div class='actions'></div>
  </div>
  <div class='box-content box-no-padding'>
    <div class='responsive-table'>
      <div class='scrollable-area-x'>
        <table id="userTable"></table>
      </div>
    </div>
  </div>
<!-- </div> -->

<div class='modal hide fade' id='modal-UserTree' role='dialog' tabindex='-1'>
    <div class='modal-header'>
        <button class='close' data-dismiss='modal' type='button'>&times;</button>
        <h3></h3>
    </div>
    <form class='form validate-form' id='submit-form'  role="form"  style='margin-bottom: 0;'>
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>用户角色</label>
            <div class='controls'>
                <div class='input-append'>
               		<select id='roles'></select>
                </div>
            </div>
        </div>
        <div class='control-group'>
            <label class='control-label'>真实姓名</label>
            <div class='controls'>
                <input class='span8' id='realName' rangelength="[3,10]" required  placeholder='真实姓名' type='text' />
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>手机号码</label>
            <div class='controls'>
                <input class='span8' id='cellphone' required rangelength="[11,20]"  placeholder='手机号码' type='text' />
            </div>
        </div>
         <div class='control-group'>
            <label class='control-label'>账户</label>
            <div class='controls'>
                <input class='span8' id='userName' required name="account" placeholder='账户名' type='text' />
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
        <button type="button" id="btnSubmit" class='btn btn-primary'>保存</button>
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
    <div class='modal-body'>
        <div class='control-group'>
            <label class='control-label'>请选择用户角色</label>
            <div class='controls' id="roleEdit">
<!--                 <div class='input-append'> -->
<!--                		<input type='hidden' id='userId' name="userId"/> -->
<!--                     <input class='span6' id='roleId' name="roleId" type='hidden' /> -->
<!--                 </div> -->
            </div>
        </div>
    </div>
    <div class='modal-footer'>
        <button type="button" id="btnRoleClose" class='btn'>关闭</button>
        <button type="button" id="btnRoleSubmit" class='btn btn-primary'>保存</button>
    </div>
</div>

<script>
  seajs.use(['base','user/manage'],function(b,m){
	b.init();
    m.init('${ctx}');
  });
  
  
  
</script>
</body>
</html>
