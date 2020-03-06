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

</head>
<body>

	<h1>検索結果</h1>

	<c:forEach var = "search" items = "${requestbeen}">
		<table>
			<tr><td><img src="${search.userIcon }" width="50px" height="50px"></td>
			<td class="username">${search.userName}</td>
			<td class="userid">@${search.userId}</tr>
		</table>
	</c:forEach>

	<div id="">上記のユーザーにパスワード変更用のメールを送信します</div>

	<form method="post" action="mailPR">
		<input type="hidden" name="userid" value="${requestbeen.get(0).userId}">
		<input type="submit" value="メール送信">
	</form>

</body>
</html>