<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ほーむ</h1>
	<p>セッションは${sessionScope.token.sessionToken}</p>
	<form method = 'post' action = 'createtweet'>
	<label for="kanso">ツイートする：</label><br>
		<textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required></textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type='submit' value='ツイート'>

	</form>

 <c:forEach var="data" items="${result}">
    <div><c:out value="${data.name}"/>＠<c:out value="${data.id}"/></div>
    <div><c:out value="${data.tweet}"/></div>
    <form method = "post" action = 'liketweet'>
    	<input type = "submit" value ="いいね">いいね数<c:out value="${data.likecounter}"/>
    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
    	<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">
    </form>
    <br><br>
  </c:forEach>


</body>
</html>