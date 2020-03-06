<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./../css/serchresult.css" %>
</style>
<script>
	$(function(){
		$('form').submit(function() {
			var pass = $("#pass").val();
			var check_pass = $("#check_pass").val();

			if(pass.indexOf("\\") != -1 ||
					pass.indexOf("<") != -1 ||
					pass.indexOf(">") != -1 ||
					pass.indexOf("/") != -1 ||
					pass.indexOf("'") != -1 ||
					pass.indexOf('"') != -1 ||
					pass.indexOf("&") != -1 ||
					pass.indexOf(";") != -1){

				alert("パスワード欄の入力文字が不正です");
				return false;
			}else if(check_pass.indexOf("\\") != -1 ||
					check_pass.indexOf("<") != -1 ||
					check_pass.indexOf(">") != -1 ||
					check_pass.indexOf("/") != -1 ||
					check_pass.indexOf("'") != -1 ||
					check_pass.indexOf('"') != -1 ||
					check_pass.indexOf("&") != -1 ||
					check_pass.indexOf(";") != -1){

				alert("パスワード欄の入力文字が不正です");
				return false;
			}else if(pass != check_pass){
				alert("パスワードが一致しません");
				return false;
			}else {
	            this.submit();
	        }
	    });
	});
</script>
</head>
<body>
	<h1>パスワード変更</h1>
	<form method="post" action="changepasswordPR">
		<div style="">*8文字以上15文字以下で入力してください</div>
		<div style="">*次の文字はパスワードには使えません: \ / < > ' " & ;</div>
		<input type="password" id="pass" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="新たなパスワードを入力してください" size="30" maxlength="15" required>
		<br>
		<input type="password"  name="confirm" id="check_pass" placeholder="パスワード再確認" size="30" maxlength="15" required>
		<input type="hidden" name="userid" value="${requestbeen}">
		<input type="submit" value="送信">
	</form>
</body>
</html>