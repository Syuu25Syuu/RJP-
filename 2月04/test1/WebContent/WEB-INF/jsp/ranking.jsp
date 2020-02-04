<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキング画面</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./test.css" %>
</style>

<script>
	<%@include file="./js/likecheck.js" %>
	<%@include file="./js/rtcheck.js" %>
</script>


</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>ランキング画面</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>


	<form method = 'post' action='logout'>
		<input type = "submit" value = "ログアウト">
	</form>

	<form method = 'post' action = 'viewmylike'>
		<input type = "submit" value = "いいね一覧">
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form action="searchuser" method="post">
		<input type="text" name="id" placeholder="ユーザIDを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>

	<form action="search_tweet" method="post">
		<input type="text" name="tweetWord" placeholder="検索したいツイートを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>

	<form method = 'post' action = 'createtweet'>
		<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type='submit' value='ツイート'>
	</form>

	<form method="post" action="ranking">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="good">
		<input type="submit" value="今までのいいね順">
	</form>
	<form method="post" action="ranking">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="goodnew">
		<input type="submit" value="1時間内のいいね順">
	</form>
	<form method="post" action="ranking">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="rt">
		<input type="submit" value="リツイート数順">
	</form>
	<form method="post" action="ranking">
		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="rtnew">
		<input type="submit" value="1時間内のリツイート数順">
	</form>
 	<c:forEach var="data" items="${requestbeen}" begin="0" end="19" step="1" varStatus="status">
 		<p>${status.count}</p>
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">

	 		<!-- ここを変えた(12-13) -->
	 		<input type = "submit" name = "user_name" value= " ${data.name}" class="button">
	 		<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button">
	 		<!-- 以上 -->

	    </form>

		<form method = 'post' action = 'viewtweet'>
	    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">
	    	<input type = "submit"  value= "${data.tweet}" class="button">
	    </form>

	     <div class = "reply"  >
			<form method = 'post' action = 'replytweet'>
				<textarea name ="replycontent" id="replycontent" cols="40" rows="4" maxlength="150" placeholder="返信をツイート" required></textarea>
				<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
				<input type="hidden" name = "replyid" type = "text" value="${data.tweetId}">

				<input type='submit' value='返信'>
			</form>
	    </div>



	    <form method = "post" action = 'liketweet'>

	    	いいねはこちら→<INPUT type="checkbox" id="${data.tweetId}"  class="likebtn" ${data.checklike}>
			<div id ="likecount"></div>
	    	<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
			<p hidden>ツイートIDは${data.tweetId}だよ</p>
			<div id = "sessionToken" hidden>${sessionScope.token.sessionToken}</div>

	    </form>

		<br>

	     <form method = "post" action = 'rttweet'>

	    	RTはこちら→<INPUT type="checkbox" id="${data.tweetId}"  class="rtbtn" ${data.checkRT}>
			<div id ="rtcount"></div>
	    	<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">

	    </form>

	    <br><br>
  </c:forEach>



</body>
</html>