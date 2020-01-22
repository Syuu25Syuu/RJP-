<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>フォロワー</h1>
	<table border="1">
		<tr><th>id</th><th>name</th></tr>
		<c:forEach var = "search" items = "${result}" >
			<form action="directmessage" method="post">
				<tr><td id="userid">${search.userId}</td><td>${search.userName}</td><td><input type="button" id="" name="followed_no" class="${search.userNo}"value="${search.userNo}">
				<input type="hidden" name="followed_no" value="${search.userNo}">
				<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId" name="session_id">
				<input type="hidden" name="followed_name" value="${search.userName}">
				<input type="submit" id="${search.userNo}"  class="followbtn" value="チャットする"></td></tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>