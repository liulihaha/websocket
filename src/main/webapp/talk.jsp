<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>大型真人聊天室</title>
<script type="text/javascript" src="/websocket/jQuery-2.2.2.js"></script>
<script type="text/javascript">
    //创建websocket连接
    var webSocket ;
</script>
</head>
<body>
<input type="text" placeholder="请输入用户名" id="user"/><button type="button" id="btn1">点击加入聊天室</button><br>
<input type="text" placeholder="请输入消息" id="msg"/><button type="button" id="btn2">点击发送</button>
<textarea style="width: 100%;height: 500px"></textarea>
<script type="text/javascript">
    $("#btn1").click(function(){
    	$.ajax({
    		url:"/websocket/test2",
    		data:{
    			name:$("#user").val(),
    		},
    		success:function(data){
    			webSocket= new WebSocket("ws://"+"192.168.6.104:8080/websocket/"+"/ws");
    		    
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
    				var text=$("textarea").val();
    			    text+=message.messageText+"\r\n";
    			    $("textarea").val(text);
    			}
    		}
    	})
    })
    
    $("#btn2").click(function(){
    	 var data = {messageText:$("#msg").val()};//新建data对象，并规定属性名与相应的值
  	     webSocket.send(JSON.stringify(data));//将对象封装成JSON后发送至服务器
    })
</script>
</body>
</html>