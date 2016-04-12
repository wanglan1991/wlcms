<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>权限管理系统</title>
    <%@include file="/WEB-INF/views/include/baseCSS.jsp" %>
</head>
<body class='contrast-blue sign-in contrast-background'>
<div id='wrapper'>
    <div class='application'>
        <div class='application-content'>
            <a href="login1.jsp"><div class='icon-heart'></div>
                <span>权限管理系统</span>
            </a>
        </div>
    </div>
    <div class='controls'>
        <div class='caret'></div>
        <div class='form-wrapper'>
            <h1 class='text-center'>登 录</h1>
            <form accept-charset="UTF-8"  class="validate-form" role="form"  method="post" id="login-form">
            <div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
                <div class='row-fluid'>
                	<div class='control-group'>
	                	<div class='controls'>
		                    <div class='span12 icon-over-input'>
		                        <input class="span12 form-control" required name="userName" placeholder="账 号" type="text" value="admin" />
		                        <i class='icon-user muted'></i>
		                    </div>
                   		</div>
                    </div>
                </div>
                <div class='row-fluid'>
                	<div class='control-group'>
	                	<div class='controls'>
		                    <div class='span12 icon-over-input'>
		                        <input class="span12 form-control" required name="password" placeholder="密 码" type="password" value="123456" />
		                        <i class='icon-lock muted'></i>
		                    </div>
                    	</div>
                    </div>
                </div>
                <%-- <div class='row-fluid'>
                	<div class='control-group'>
	                	<div class='controls'>
		                    <div class='span12 icon-over-input'>
		                        <input class="span12 form-control" maxlength="4" required name="verifycode" placeholder="验证码" type="text" value="" />
		                        <i class='icon-credit-card muted'></i>
		                    </div>
	                    </div>
                    </div>
                </div>
	            <div class='row-fluid'>
	                <div class='span12 icon-over-input'>
	                    <img id="verifyCodeImg" src="${ctx}/user/authCode"/><a href="javascript:void(0)">换一张</a>
	                </div>
	            </div> --%>
                <label class="checkbox" for="rememberMe"><input id="rememberMe" name="rememberMe" type="checkbox" value="1" />
                  	  记住我
                </label>
                <button class="btn btn-block" id="submit" type="submit">登 录</button>
            </form>
            <div class='text-center'>
                <hr class='hr-normal' />
                <a href="forgot_password">忘记密码?</a>
            </div>
        </div>
    </div>
    <div class='login-action text-center'>
        <a href="sign_up.html"><i class='icon-user'></i>
            加入我们?
            <strong>注 册</strong>
        </a>
    </div>
</div>
</body>
</html>
<%@include file="/WEB-INF/views/include/baseJS.jsp" %>
<script type="text/javascript">
    $(function () {
        seajs.config({
            base: "${ctxAssets}/js/",
            alias: {
            }
        });
        //加载首页
        seajs.use(['base','user/login'], function (b,login) {
            b.init();
            login.init('${ctx}');
        });
    });
</script>
