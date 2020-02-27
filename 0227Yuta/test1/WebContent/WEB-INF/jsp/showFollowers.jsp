<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フォロワー一覧</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>


</style>

<script charset="utf-8" >
	<%@include file="./js/likecheck.js" %>
	<%@include file="./js/rtcheck.js" %>
	<%@include file="./js/deleteTweet.js" %>
	<%@include file="./js/createTweet.js" %>
</script>


</head>
<body>
<div class="header">

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>ホーム画面</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<div class="button">
		<form method = "post" action = "comebackhome" id = "comebackhome">
			<div class="bn" onclick = "document.getElementById('comebackhome').submit();">
				<img src="/test1/images/home.png">
			</div>
			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		</form>


		<form method = "post" action = "showprofiles" id = "showprofiles">
			<c:forEach var="data" items="${requestbeen}" end = "0">
				<div onclick = "document.getElementById('showprofiles').submit();"><img src="${data.profImage}" class="a"></div>
	 			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 			<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
			</c:forEach>
		</form>

		<form method = "post" action = "followershowDM" id = "followershowDM">
			<div class="bn" onclick = "document.getElementById('followershowDM').submit();">
				<img src="/test1/images/dm.png">
			</div>
			<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
		</form>

		<form method="post" action="ranking" id = "ranking">
			<div class="bn" onclick = "document.getElementById('ranking').submit();">
				<img src="/test1/images/rank.png">
			</div>
			<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
			<input type="hidden" name="check_value" value="good">
		</form>
	</div>

	<div class="serch">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- <form action="search_tweet" method="post" class="sr">
			<input type="text" size="25" name="tweetWord" placeholder="キーワード検索">
			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
			<input type="submit" value="&#xf002">
		</form>-->
		<form action="search" method="post">
		<label>ユーザ<input type="radio" name="select" value="user" checked></label>
		<label>ツイート<input type="radio" name="select" value="tweet"></label>
		<input type="text" name="keyword" placeholder="キーワードを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>
	</div>

	<!-- 通知 -->
	<form method="post" action="notify">
		<input type="hidden" name="session_id" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="">
		<input type="submit" value="通知ページへ">
	</form>
	<div>${requestbeen[1]}</div>

</div>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>フォロワー一覧</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>



 	<c:forEach var="data" items="${result}">
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">

	 		<input type = "submit" name = "user_name" value= " ${data.name}" class="button">
	 		<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button">

	    </form>
	 </c:forEach>

</body>
</html>