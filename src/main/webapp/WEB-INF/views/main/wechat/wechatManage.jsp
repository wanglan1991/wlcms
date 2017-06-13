<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title></title>
<style>
label.error{
color:red;
}
#formBody input{
width:320px;
}
</style>
</head>
<body >
	<div class='box-header' id="video-header">
		<div class='actions'>
		</div>
		<div id="formBody" >
				<div class='control-group'>
					<label class='control-label'>类型</label>
					<div class='controls'>
		               		<select id='publicNumberType'>
		               			<option value='0'>请选择公众号类型.....</option>
			               		<option value='1'>订阅号</option>
			               		<option value='2'>服务号</option>
			               		<option value='3'>企业号</option>
		               		</select>
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>appID</label>
					<div class='controls'>
						<input class='span8' id='appId' onkeyup="$('#serverUrl').val('http://112.74.105.4:8080/cms/wechat/'+$('#appId').val())" placeholder='请输入公众号appID' type='text' />
					</div>
				</div>
				<div class='control-group'>
					<label class='control-label'>server URL</label>
					<div class='controls'>
						<input class='span8' id='serverUrl' placeholder='请输入appID' type='text' />
					</div>
					<button type="button" id="copyUrl">复制</button>
				</div>
				<div class='control-group'>
					<label class='control-label'>appsecret</label>
					<div class='controls'>
						<input class='span8' id='appsecret'  placeholder='请输入appsecret' type='text' />
					</div>
				</div>
				
				

			</div>
	</div>
	<script>
		seajs.use([ 'base', 'main/wechatManage/wechatManage' ], function(b, m) {
		});
	</script>
</body>
</html>
