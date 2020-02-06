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

</head>
<body>
    <div class="slide">
        <img src="/test1/images/home.png">
        <img src="/test1/images/rank.png">
        <img src="/test1/images/home.png">
        <img src="/test1/images/rank.png">
    </div>
	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>スタート</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>
</body>
</html>