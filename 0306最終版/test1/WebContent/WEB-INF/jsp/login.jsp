<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./../css/login.css" %>
</style>

<script>
	$(function(){
		$('form').submit(function() {
			var check_name = $("#check_name").val();
			var check_pass = $("#check_pass").val();

			if(check_name.indexOf("\\") != -1 ||
					check_name.indexOf("<") != -1 ||
					check_name.indexOf(">") != -1 ||
					check_name.indexOf("/") != -1 ||
					check_name.indexOf("'") != -1 ||
					check_name.indexOf('"') != -1 ||
					check_name.indexOf("&") != -1 ){

				alert("ID欄の入力文字が不正です");
				return false;
			}else if(check_pass.indexOf("\\") != -1 ||
					check_pass.indexOf("<") != -1 ||
					check_pass.indexOf(">") != -1 ||
					check_pass.indexOf("/") != -1 ||
					check_pass.indexOf("'") != -1 ||
					check_pass.indexOf('"') != -1 ||
					check_pass.indexOf("&") != -1 ){

				alert("パスワード欄の入力文字が不正です");
				return false;
			}else{
				this.submit();
			}
		});
	});
</script>
</head>
<body>
	<h1>ログイン</h1>
	<form method='post' action='login'autocomplete="">
		ユーザーID:<input type='text' name='name' id="check_name" maxlength="15" required><br>
		パスワード:<input type='text' name='pass' id="check_pass" maxlength="15" required><br><br>

		<input type='submit' value='Login' id="submit_button">
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