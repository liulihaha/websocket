<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>提交订单</title>
<script type="text/javascript" src="/websocket/jQuery-2.2.2.js"></script>
<script type="text/javascript">
    //创建websocket连接
   
</script>
</head>
<body>
<input type="text"/><button type="button">点击提交</button>
<script type="text/javascript">
   $("button").click(function(){
	   $.ajax({
			  url:"/websocket/test3",
			  data:{
				  name:$("input").val()
			  },
			  success:function(data){
				  alert(data);
				  var webSocket = new WebSocket("ws://"+"192.168.6.104:8080/websocket/"+"/ws");
			  }
		   })
   })
</script>
</body>
</html>