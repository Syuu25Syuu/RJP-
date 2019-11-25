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
	<p>セッションは${sessionScope.result.sessionToken}</p>
	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？"></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.result.sessionToken}">
		<input type='submit' value='ツイート'>

	</form>


	<table border="1">
		<tr><th>${result.name}</th><th>@${result.id}</th></tr>
		<c:forEach var = "tweet" items = "${result.tweet}">
			<tr><td>${result.tweet}</td><td>dddddddddddd</td></tr>
		</c:forEach>


	</table>




</body>
</html>