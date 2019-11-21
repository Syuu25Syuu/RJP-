<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>ホーム</h1>

	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？"></textarea>
		<input type="hidden" id = "user_sesssion" type = "text" value="${sessionScope.flg}">
		<input type='submit' value='ツイート'>
	</form>

	<table border="1">
		<tr><th>${sessionScope.user_name}</th><th>@${sessionScope.id}</th></tr>
		<c:forEach var = "tweet" items = "${sessionScope.tweet}">
			<tr><td>${tweet}</td><td>dddddddddddd</td></tr>
		</c:forEach>


	</table>




</body>
</html>