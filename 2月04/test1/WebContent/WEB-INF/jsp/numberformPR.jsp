<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="keyconfirmationPR">
		<input type="text" name="key" placeholder="メールに届いた数字を入力してください">
		<input type="hidden" name="userid" value="${requestScope.requestbeen[0]}">
		<input type="submit" value="送信">
	</form>
	<div>間違えた回数:${requestbeen[1]}</div>
	<div id=""><font color="#ff0000">*入力を3回間違えると自動的にユーザー検索ページに戻ります</font></div>
</body>
</html>