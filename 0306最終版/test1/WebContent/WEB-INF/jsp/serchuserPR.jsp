<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	<%@include file="./../css/serchuser.css" %>
</style>
</head>
<body>
	<h1>ユーザ検索</h1>
	<form action="serchuserPR" method="post">
		<input type="email" name="mailaddress" placeholder="メールアドレスを入力" required>
		<input type="submit" value="検索">
	</form>
</body>
</html>