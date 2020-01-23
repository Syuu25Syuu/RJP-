<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>

</script>
</head>
<body>

	<h1>検索結果</h1>


	<table border="1">
		<tr><th>name</th><th>id</th></tr>
			<c:forEach var = "search" items = "${requestbeen}">
			<form method="post" action="confirmationPR">
				<tr>
					<td>${search.userName}</td>
					<td>${search.userId}</td>
					<td><input type="hidden" name="userid" value="${search.userId}"><input type="submit" class="btn" value="確認画面へ"></td>
				</tr>
			</form>
			</c:forEach>
	</table>

</body>
</html>