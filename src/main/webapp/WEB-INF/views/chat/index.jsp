<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页[spring-websocket demo]</title>
<style type="text/css">
body {background: #E2E2E2; font-size:13.5px;}
div[message] { background: #5170ad; width: 400px; height: 600px; border: 1px solid #ccc; color: #fff; overflow-y: auto; }
ul, li { list-style-type: square;}
</style>
</head>
<body>
    <div message>
        <ul>
        </ul>
    </div>
<%@include file="/WEB-INF/views/include/baseJS.jsp" %>
<script type="text/javascript">
	var PATH = "${ctx}";
	var websocket = null;
	if (window['WebSocket'])
		// ws://host:port/project/websocketpath
		websocket = new WebSocket("ws://" + window.location.host + PATH + '/websocket');
	else
		websocket = new SockJS("http://"+ window.location.host + PATH + '/websocket/socketjs');
	
	websocket.onopen = function(event) {
		console.log('open', event);
	};
	
	websocket.onmessage = function(event) {
		console.log('message', event.data);
		$('div[message] > ul').append('<li>' + event.data + '</li>');
	};
</script>
</body>
</html>
