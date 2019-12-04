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
	<form method = 'post' action='logout'>
		<input type = "submit" value = "ログアウト">
	</form>

		<form method = 'post' action = 'viewmylike'>
		<input type = "submit" value = "いいね一覧">
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form action="search_tweet" method="post">
		<input type="text" name="tweetWord" placeholder="検索したいツイートを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>

	<a href="searchuser">アカウント検索ページへ</a>


	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？"></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type='submit' value='ツイート'>

	</form>
	<p>セッションは${sessionScope.token.sessionToken}</p>

</body>
</html>