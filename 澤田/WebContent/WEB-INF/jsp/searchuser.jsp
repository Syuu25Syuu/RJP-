<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>セッションは${sessionScope.token.sessionToken}</p>
	<form action="search" method="post">
		<input type="text" name="id" placeholder="ユーザIDを入力" >
		<input type="submit" value="検索">
	</form>
</body>
</html>