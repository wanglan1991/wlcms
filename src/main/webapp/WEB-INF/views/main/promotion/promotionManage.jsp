<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
<style>
label.error{
color:red;
}
#message_send{
    min-height: 593px;
/*      border: solid;  */
     width: 588px;
}


#message_send textarea {
    width: 311px;
}
#promotionToolsActions input{
margin-top: 27px;

}
#message_send p{
height: 22px;
}

#mail_send input{
    width: 600px;
}
</style>
</head>
<body>
	<div class='box-header' >
		<div class='actions' id="promotionToolsActions">	
		</div>
	</div>
	
	<div id="message_send">
	
	<form  id="cellphoneNumbersUpload"  enctype="multipart/form-data">  
	<textarea rows="4"  name="messageContent" placeholder="请输入短信内容。最多只能输入69个字符" maxlength="69" id="message_content"></textarea><br>
	<p id="message_msg"></p>
	<input type="file" name="fileData" id="sendToCellphoneNumbers" accept=".xlsx,.xls">
	<button type="submit" id="sendSubmit">提交</button>
	</form>  
	</div>
	
	<div id="mail_send" style="display:none">
		收件地址：<input type="text" placeholder="收件人邮件地址，多个请使用英文状态下的 “,” 隔开。 "  id="sendToMaills"><br>
		邮件标题：<input type="text" placeholder="请输入邮件标题..." id="mailTitle"><br>
		 <script id="mailContainer"   placeholder="请输入邮件标题..." name="content" type="text/plain"></script>
		<button type="button" id="mailSendSubmit">发送</button>
	</div>
	

</body>
	
	<script>
		seajs.use([ 'base', 'main/promotion/promotionManage' ], function(b, m) {
			b.init();
			m.init('${ctx}');
		});
	</script>
</html>
