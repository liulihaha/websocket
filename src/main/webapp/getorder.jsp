<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>获得订单</title>
<script type="text/javascript" src="/websocket/jQuery-2.2.2.js"></script>
<script type="text/javascript">
	var webSocket= new WebSocket("ws://"+"192.168.6.104:8080/websocket/"+"/ws");
	
	webSocket.onopen = function(event){
	    console.log("连接成功");
	};
	webSocket.onerror = function(event){
	    console.log("连接失败");
	};
	webSocket.onclose = function(event){
	    console.log("Socket连接断开");
	};
	webSocket.onmessage = function(event){
	    //接受来自服务器的消息
		var message = JSON.parse(event.data);
	    var text=$("div").text();
	    text+=message.messageText
	    $("div").text(text);
	}
	$("div").text();
</script>
</head>
<body>
<div></div>
</body>
</html>