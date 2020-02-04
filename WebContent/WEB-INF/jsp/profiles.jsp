<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール画面</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>


<style>
	<%@include file="./test.css" %>
</style>

<script>
	<%@include file="./js/likecheck.js" %>
	<%@include file="./js/rtcheck.js" %>
	<%@include file="./js/followcheck.js" %>
</script>

</head>
<body>


	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>プロフィール画面</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

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




	<c:forEach var="data" items="${result}" end = "0">


	<!-- プロフィールのツイート切り替え用のform -->

	<form id = "likeform" method = "post" action = "showproflike">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">

	</form>

	<form id = "tweetform" method = 'post' action = 'showprofiles' >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
		<input type="hidden" name = "image_path" id = "image_path" type = "text" value="${data.profImage}">
	</form>

	<form id = "profchangeform" method = 'post' action='goChangeProfile'>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "image_path" id = "image_path" type = "text" value="${data.profImage}">
	</form>



	<!-- ここまで -->


		<img id="mypic1" name = "mypic1" src="${data.profImage}" class="panel-img">

		<!-- 名前とID表示 -->
		<h2>${data.profUserName}</h2>
		<h5>@${data.profUserId}</h5>

		<!-- フォロワーボタン -->
		<input type="checkbox" id="${data.serialuserid}"  class="${data.followbtn}" ${data.checkFollow}>
		<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>


		<!-- プロフィールの文 -->
		<h3>${data.profile}</h3>

		<form method = 'post' action = 'showFollows'>
			<input type = "submit" value="${data.countFollows}フォロー中">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
		</form>

		<form method = 'post' action = 'showFollowers'>
			<input type = "submit" value="${data.countFollowers}フォローされています">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
		</form>
　
	</c:forEach>

	<br><br>

	<form id = "tweetform" method = "post" action = "showproftweet">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
	</form>


	<button type="submit" form = "tweetform" >ツイート</button>    <button type="submit" form = "likeform" >いいね</button>

	<br><br>


	<c:forEach var="data" items="${result}">
		 ${data.rtuser}

		 <img id="mypic1" name = "mypic1" src="${data.icon}" class="panel-img">

    	<form method = 'post' action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
	 		<input type = "submit" name = "user_id" value= " ${data.name}  ＠${data.id}" class="button">

	    </form>


		<form method = 'post' action = 'viewtweet'>
	    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">
	    	<input type = "submit"  value= "${data.tweet}" class="button">
	    </form>


	    <form method = "post" action = 'liketweet'>

	    	いいねはこちら→<INPUT type="checkbox" id="${data.tweetId}"  class="likebtn" ${data.checklike}>
			<div id ="likecount"></div>
	    	<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
	    	<div id = "sessionToken">${sessionScope.token.sessionToken}</div>

	    </form>

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