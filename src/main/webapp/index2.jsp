<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>websocket</title>
<script type="text/javascript" src="/smaven2/jQuery-2.2.2.js"></script>
<script type="text/javascript">
    var webSocket = new WebSocket("ws://"+"localhost:8080/smaven2/"+"/ws");
    
	webSocket.onopen = function(event){
	    console.log("连接成功");
	    console.log(event);
	};
	webSocket.onerror = function(event){
	    console.log("连接失败");
	    console.log(event);
	};
	webSocket.onclose = function(event){
	    console.log("Socket连接断开");
	    console.log(event);
	};
	webSocket.onmessage = function(event){
	    //接受来自服务器的消息
	    var x=$("div").html();
	    
		var message = JSON.parse(event.data);
		x+="<br>"+message.messageText;
		$("div").html(x);
	}
</script>
</head>
<body>
<div></div>
<input type="text"/><button type="button">登录</button>
<script type="text/javascript">
   $("button").click(function(){
	   console.info("1");
	   var data = {messageText:$("input").val(),toId:2};//新建data对象，并规定属性名与相应的值
	   webSocket.send(JSON.stringify(data));//将对象封装成JSON后发送至服务器
	   console.info("2");
   })
</script>
</body>
</html>