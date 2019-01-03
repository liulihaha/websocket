<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SSM整合</title>
<script type="text/javascript" src="/smaven2/jQuery-2.2.2.js"></script>
</head>
<body>
   <h1>进来了</h1>
   <input type="text"/>
   <button type="button">点击连接</button>
   <script type="text/javascript">
      $("button").click(function(){
    	  var a=$("input").val();
    	  window.location.href="/smaven2/test?userid="+a;
      })
   </script>
</body>
</html>