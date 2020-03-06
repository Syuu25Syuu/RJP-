<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通知一覧</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./test.css" %>
	<%--<%@include file="./../css/index.css" %>--%>
	<%@include file="./../css/index.css" %>
	<%@include file="./../css/head.css" %>

</style>
<script>
	<%@include file="./js/followcheck.js" %>
	<%@include file="./js/control.js" %>
</script>

<script>
	$(function(){
		var notifycount = $(".ct").text();
		if(notifycount < 0){
			$(".ct").text("0");
			$("#check_content").text("まだ通知がありません");
		}
	});
</script>
</head>
<body>

<div class="header">

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><img src="/test1/images/pakutter.png"></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

	<div class="button">
		<form method = "post" action = "comebackhome" id = "comebackhome">
			<div class="bn" onclick = "document.getElementById('comebackhome').submit();">
				<img src="/test1/images/home.png">
			</div>
			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		</form>


		<!-- プロフィール画面に飛ぶわよ！ -->
		<form method = "post" action = "showprofiles" id = "showprofiles">
			<div onclick = "document.getElementById('showprofiles').submit();"><img src="${sessionScope.token.icon}" class="a"></div>
 			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
 			<input type="hidden" name = "user_id" type = "text" value="${sessionScope.token.sessionToken}">
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
			<!-- 通知 -->
		<form method="post" action="notify" id="notify">
			<div class="bn" onclick = "document.getElementById('notify').submit();">
				<img src="/test1/images/notification.png">
			</div>
			<input type="hidden" name="session_id" value="${sessionScope.token.sessionToken}">
		</form>
		<div class="ct">${requestbeen[0].notifyCount}</div>
	</div>

	<div class="serch">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
		<form action="search" method="post">
			<div class="sr">
				<label>ユーザ<input type="radio" name="select" value="user" checked></label>
				<label>ツイート<input type="radio" name="select" value="tweet"></label>
			</div>
			<div class="src">
				<input type="text" name="keyword" placeholder="キーワードを入力" size="25">
				<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
				<input type="submit" value="&#xf002">
			</div>
		</form>
	</div>
</div>

<div class="page_top"><a href="#"></a></div>
<div class="ma"><p> </p></div>

 	<c:forEach var="data" items="${requestbeen}">
 		<div id="check_content">
		 	<form method="post" action="${data.servletName }">
		 		<img src="${data.requestUserIconPath}" style = "width:50px; height:50px;">
		 		<div style="font-size:20px;">${data.requestUserName}さんに${data.type}されました
			 		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
			 		<input type="hidden" name="user_id" value="${data.requestUserNo}">
			 		<input type="hidden" name="tweet_id" value="${data.tweetId}">
			 		<input type="submit" value="みにいく⇒">
		 		</div>
		 	</form>
		 	<hr>
	 	</div>
	 </c:forEach>

</body>
</html>