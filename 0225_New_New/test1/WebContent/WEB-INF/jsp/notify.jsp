<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通知一覧</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

<script>
<%@include file="./js/followcheck.js" %>
</script>
</head>
<body>

 	<c:forEach var="data" items="${requestbeen}">
	 	<form method="post" action="${data.servletName }">
	 		<img src="${data.requestUserIconPath}">No:${data.requestUserNo} ユーザ名:${data.requestUserName}さんに${data.type}されました (ツイートID:${data.tweetId})
	 		<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name="user_id" value="${data.requestUserNo}">
	 		<input type="hidden" name="tweet_id" value="${data.tweetId}">
	 		<input type="submit" value="jump">
	 	</form>
	 </c:forEach>

</body>
</html>