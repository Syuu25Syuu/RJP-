<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更画面</title>
</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>変更</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

<c:forEach var="data" items="${result}" end = "0">

	<form method = "post" action="changeprofile">
		名前:<br><input type = "text" name = "user_name" value= " ${data.name}" class="button"><br><br>
		自己紹介<br><textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required >${data.profile}</textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<br>
		<input type="submit" value="変更する">
	</form>

</c:forEach>
</body>
</html>