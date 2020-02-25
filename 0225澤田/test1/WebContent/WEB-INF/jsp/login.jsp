<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<style>
	<%@include file="./../css/login.css" %>
</style>
</head>
<body>
	<h1>ログイン</h1>
	<form method='post' action='login'autocomplete="">
		ユーザーID:<input type='text' name='name'><br>
		パスワード:<input type='text' name='pass'><br><br>

		<input type='submit' value='Login'>
	</form>
	<br>
	<form method = 'post' action = 'crateacountform'>
		<input type = 'submit' value ="アカウント作成はこちらです">
	</form>
	<form method = 'post' action = 'passwordreminderpage'>
		<input type = 'submit' value ="パスワードを忘れた方はこちら">
	</form>


</body>
</html>