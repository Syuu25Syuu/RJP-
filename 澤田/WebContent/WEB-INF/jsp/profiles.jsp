<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール画面</title>

<style>
	<%@include file="./test.css" %>
</style>


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


	<c:forEach var="data" items="${result}">
    	<form method = 'post' action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
	 		<input type = "submit" name = "user_id" value= " ${data.name}  ＠${data.id}" class="button">

	    </form>
    	<div><c:out value="${data.tweet}"/></div>

    	<form method = "post" action = 'liketweet'>

	    	<input type = "submit" id = "check" value = "${data.checklike}">いいね数<c:out value="${data.likecounter}"/>

	    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">

    	</form>

    <br><br>
  </c:forEach>

</body>
</html>