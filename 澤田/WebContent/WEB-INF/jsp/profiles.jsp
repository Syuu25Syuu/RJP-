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


	<c:forEach var="data" items="${result}">
    	<form method = 'post' action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
	 		<input type = "submit" name = "user_id" value= " ${data.name}  ＠${data.id}" class="button">

	    </form>
    	<div><c:out value="${data.tweet}"/></div>

    	 <form method = "post" action = 'createRT'>
	    	<input type = "submit" id = "checkRT" value = "${data.checkRT}">RT数<c:out value="${data.countRT}"/>
	    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">
	    </form>

	    <form method = "post" action = 'liketweet'>

	    	いいねはこちら→<INPUT type="checkbox" id="${data.tweetId}"  class="likebtn" ${data.checklike}>
			<div id ="likecount"></div>
	    	<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    	<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
	    	<div id = "sessionToken">${sessionScope.token.sessionToken}</div>
			ツイートIDさんは${data.tweetId}
	    </form>

    <br><br>
  </c:forEach>

</body>
</html>