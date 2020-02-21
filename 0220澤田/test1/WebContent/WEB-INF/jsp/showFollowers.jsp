<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フォロワー一覧</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

<script>
<%@include file="./js/followcheck.js" %>
</script>
</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>フォロワー一覧</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>



 	<c:forEach var="data" items="${result}">
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">

	 		<input type = "submit" name = "user_name" value= " ${data.name}" class="button">
	 		<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button">

	    </form>
	 </c:forEach>

</body>
</html>