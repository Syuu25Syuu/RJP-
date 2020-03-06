<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	<%@include file="./../css/start.css" %>
</style>
<script>

</script>
<meta charset="UTF-8">
<title>ログイン成功</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
	$(function(){
		$("form").submit();
	})
</script>
</head>
<body>
	<!-- <form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>スタート</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form> -->
	<div name="user_session" value="${result[0].sessionToken}" hidden></div>
	<form method = 'post' action = 'comebackhome'>
		<input type="hidden" name = "user_session" value="${sessionScope.token.sessionToken}">
	</form>
</body>
</html>