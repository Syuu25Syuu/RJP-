<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>

</script>
<meta charset="UTF-8">
<title>ログイン成功</title>

</head>
<body>
	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>よおこそ</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>
	<form method = 'post' action='logout'>
		<input type = "submit" value = "ログアウト">
	</form>

	<form action="searchuser" method="post">
		<input type="text" name="id" placeholder="ユーザIDを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>

		<form method = 'post' action = 'viewmylike'>
		<input type = "submit" value = "いいね一覧">
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form method = 'post' action = 'viewmyRT'>
		<input type = "submit" value = "RT一覧">
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form action="search_tweet" method="post">
		<input type="text" name="tweetWord" placeholder="検索したいツイートを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>


	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？"></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type='submit' value='ツイート'>

	</form>
	<!-- DM -->
	<form method="post" action="followershowDM">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="DMページへ">
	</form>
	<!-- ランキング -->
	<form method="post" action="ranking">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="good">
		<input type="submit" value="ランキングページへ">
	</form>
	<p>セッションは${sessionScope.token.sessionToken}</p>

</body>
</html>