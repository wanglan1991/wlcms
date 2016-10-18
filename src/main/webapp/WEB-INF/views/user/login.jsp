<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>EKT-CMS</title>
    <%@include file="/WEB-INF/views/include/baseCSS.jsp" %>
</head>
<body class='contrast-blue sign-in contrast-background'>
<div id='wrapper' style='margin-top: 160px;'>
    <div class='application'>
        <div class='application-content'>
         <img  src="http://ekt.oss-cn-shenzhen.aliyuncs.com/headPicture/cms-logo.png">
        </div>
    </div>
    <div class='controls'>
        <div class='form-wrapper'>
            <h1 class='text-center'></h1>
            <form accept-charset="UTF-8"  class="validate-form" role="form"  method="post" id="login-form">
            <div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
                <div class='row-fluid'>
                	<div class='control-group'>
	                	<div class='controls'>
		                    <div class='span12 icon-over-input'>		                  
		                        <input class="span12 form-control" required name="userName" placeholder="账 号" type="text" />
		                        <i class='icon-user muted'></i>
		                    </div>
                   		</div>
                    </div>
                </div>
                <div class='row-fluid'>
                	<div class='control-group'>
	                	<div class='controls'>
		                    <div class='span12 icon-over-input'>
		                        <input class="span12 form-control" required name="password" placeholder="密 码" type="password" />
		                        <i class='icon-lock muted'></i>
		                    </div>
                    	</div>
                    </div>
                </div>
               
                <label class="checkbox"  style="color: #fff;" for="rememberMe"><input id="rememberMe" name="rememberMe" type="checkbox" value="1" />
                  	  记住我
                </label>
                <button class="btn btn-block" id="submit" type="submit">登 录</button>
            </form>
        </div>
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
    
    document.onreadystatechange = function () {//监听dom加载事件 document.readyState == 'loaded' ||'interactive'||'complete'
        if (document.readyState == 'complete') { //完全加载
        	 document.body.style.display = "block";
        }
    }
    
</script>
