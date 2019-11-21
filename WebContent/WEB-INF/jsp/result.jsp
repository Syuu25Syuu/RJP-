<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>

</script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>よおこそ</h1>


	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？"></textarea>
		<input type="hidden" id = "user_sesssion" type = "text" value="${sessionScope.flg}">
		<input type='submit' value='ツイート'>

	</form>

	<p>セッションは${sessionScope.result}</p>
</body>
</html>