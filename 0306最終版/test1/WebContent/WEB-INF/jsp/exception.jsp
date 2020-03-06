<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./../css/login.css" %>
</style>

</head>
<body>
	<div>
		<p>エラーが発生しました</p>
		<p>ログインをし直してください</p>
	</div>

	<form action="/test1" >
		<input type="submit" value="ログインページへ">
	</form>




</body>
</html>