<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./test.css" %>
	<%--<%@include file="./../css/index.css" %>--%>
	<%@include file="./../css/index.css" %>
	<%@include file="./../css/head.css" %>
	<%@include file="./../css/profile.css" %>

</style>
<script>
	<%@include file="./js/followcheck.js" %>
	<%@include file="./js/showTweet.js" %>
	<%@include file="./js/control.js" %>
</script>
<script>
	$(function(){
		var check_serialno = $(".check_serialno").val();
		if(check_serialno == "検索結果がない"){
			$("table").remove();
			$("#check_content").text("検索結果がありません");
		}
	});
</script>
</head>
<body>

	<div class="header">
		<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
			<div onclick = "document.getElementById('comebackhome').submit();"><img src="/test1/images/pakutter.png"></div>
			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		</form>

		<div class="button">
			<form method = "post" action = "comebackhome" id = "comebackhome">
				<div class="bn" onclick = "document.getElementById('comebackhome').submit();">
					<img src="/test1/images/home.png">
				</div>
				<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
			</form>


			<form method = "post" action = "showprofiles" id = "showprofiles">
				<div onclick = "document.getElementById('showprofiles').submit();"><img src="${sessionScope.token.icon}" class="a"></div>
	 			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 			<input type="hidden" name = "user_id" type = "text" value="${sessionScope.token.sessionToken}">
			</form>

			<form method = "post" action = "followershowDM" id = "followershowDM">
				<div class="bn" onclick = "document.getElementById('followershowDM').submit();">
					<img src="/test1/images/dm.png">
				</div>
				<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
			</form>

			<form method="post" action="ranking" id = "ranking">
				<div class="bn" onclick = "document.getElementById('ranking').submit();">
					<img src="/test1/images/rank.png">
				</div>
				<input type="hidden" name="user_session" value="${sessionScope.token.sessionToken}">
				<input type="hidden" name="check_value" value="good">
			</form>
				<!-- 通知 -->
			<form method="post" action="notify" id="notify">
				<div class="bn" onclick = "document.getElementById('notify').submit();">
					<img src="/test1/images/notification.png">
				</div>
				<input type="hidden" name="session_id" value="${sessionScope.token.sessionToken}">
				<input type="hidden" name="check_value" value="">
			</form>
			<div class="ct">${requestbeen[0].notifyCount}</div>
		</div>

		<div class="serch">
			<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
			<form action="search" method="post">
				<div class="sr">
					<label>ユーザ<input type="radio" name="select" value="user" checked></label>
					<label>ツイート<input type="radio" name="select" value="tweet"></label>
				</div>
				<div class="src">
					<input type="text" name="keyword" placeholder="キーワードを入力" size="25">
					<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
					<input type="submit" value="&#xf002">
				</div>
			</form>
		</div>
	</div>
	<div class="page_top"><a href="#"></a></div>
	<div class="ma"><p> </p></div>

	<table border="0">
		<c:forEach var = "search" items = "${requestbeen}">
			<div id="check_content">
				<tr>
				<form method = 'post' id="myFORM${search.userNo}" action = 'showprofiles'>
					<td><div class = "usertProf" id="${search.userNo}I"><img src = "${search.userIcon}" style = "width:30px; height:30px;" id="${search.userNo}I"></div></td>
					<td style="font-size:20px;"><div class = "usertProf" id="${search.userNo}I">${search.userName}</div></td>
					<td id="userid" class="userid"><div class = "usertProf" id="${search.userNo}I">@${search.userId}</div></td>
					<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 				<input type="hidden" name = "user_id" type = "text" value="${search.userNo}">
				</form>
					<td>
						<input type="checkbox" id="${search.userNo}"  class="followbtn" ${search.check}>
						<label class="followbtnbefore"  for ="${search.userNo}"></label>
						<input type="hidden" id="" class="${search.userNo} check_serialno" value="${search.userNo}">
					</td>

				</tr>
			</div>
		</c:forEach>
	</table>
	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>

</body>
</html>