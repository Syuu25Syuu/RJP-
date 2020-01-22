<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>パスワード変更</h1>
	<form method="post" action="changepasswordPR">
		<input type="text" name="password" placeholder="新たなパスワードを入力してください">
		<input type="hidden" name="userid" value="${requestbeen}">
		<input type="submit" value="送信">
	</form>
</body>
</html>