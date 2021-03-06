<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リプライ</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./test.css" %>
</style>


<script>
	<%@include file="./js/likecheck.js" %>
</script>

</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>リプライ画面</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form method = 'post' action='logout'>
		<input type = "submit" value = "ログアウト">
	</form>

	<a href="searchuser">アカウント検索ページへ</a>

	<form method = 'post' action = 'viewmylike'>
		<input type = "submit" value = "いいね一覧">
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<form action="search_tweet" method="post">
		<input type="text" name="tweetWord" placeholder="検索したいツイートを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>


	<p>セッションは${sessionScope.token.sessionToken}</p>

	<form method = 'post' action = 'createtweet'>
		<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type='submit' value='ツイート'>
	</form>

<!-- 返信元を表示 -->


<!-- リプライを表示 -->

   	<c:forEach var="data2" items="${result}">


		<div id = "parentId">${data2.parentUserId}</div>
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data2.childSerialNo}">
	 		<input type = "submit" name = "user_id" value= " ${data2.childUserName}  ＠${data2.childUserId}" class="button">

	    </form>


	    <div>${data2.childTweetContent}</div>


		 <form method = "post" action = 'liketweet'>

	    	いいねはこちら→<INPUT type="checkbox" id="${data.tweetId}"  class="likebtn" ${data.checklike}>
			<div id ="likecount"></div>
	    	<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
	    	<div id = "sessionToken">${sessionScope.token.sessionToken}</div>

	    </form>

	    <br><br>
  </c:forEach>



</body>
</html>