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
					<table>
						<tr><td><img src="${search.userIcon}" width="50px" height="50px"></td>
						<td class="username">${search.userName}</td>
						<td class="userid">@${search.userId}</td>
						<td><input type="hidden" name="userid" value="${search.userId}"></td>
						<td><input type="submit" class="btn" value="確認画面へ"></td></tr>
					</table>
					<hr/>
				</form>
			</c:forEach>

</body>
</html>