<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	<%@include file="./../css/serchresult.css" %>
</style>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>

</script>
</head>
<body>

	<h1>検索結果</h1>

			<c:forEach var = "search" items = "${requestbeen}">
				<form method="post" action="confirmationPR">
					<img src="${search.userIcon }">
					<p class="username">${search.userName}</p>
					<p class="userid">@${search.userId}</p>
					<input type="hidden" name="userid" value="${search.userId}"><input type="submit" class="btn" value="確認画面へ">
				</form>
			</c:forEach>

</body>
</html>