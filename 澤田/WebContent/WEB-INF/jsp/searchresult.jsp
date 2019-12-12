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
<%@include file="./js/followcheck.js"%>
</script>
</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>検索結果</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>


	<table border="1">
		<tr><th>id</th><th>name</th></tr>
		<c:forEach var = "search" items = "${result}">
			<tr><td id="userid">${search.userId}</td><td>${search.userName}</td><td><input type="hidden" id="" class="${search.userNo}"
			 value="${search.userNo}"><input type="checkbox" id="${search.userNo}"  class="followbtn" ${search.check}></td></tr>
		</c:forEach>
	</table>
	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>

</body>
</html>