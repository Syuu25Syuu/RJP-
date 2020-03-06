<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>フォロワー一覧</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
		<%@include file="./test.css" %>
	<%--<%@include file="./../css/index.css" %>--%>
	<%@include file="./../css/index.css" %>
	<%@include file="./../css/head.css" %>
	<%@include file="./../css/profile.css" %>
</style>

<script charset="utf-8" >
	<%@include file="./js/likecheck.js" %>
	<%@include file="./js/rtcheck.js" %>
	<%@include file="./js/deleteTweet.js" %>
	<%@include file="./js/createTweet.js" %>
	<%@include file="./js/followcheck.js" %>
	<%@include file="./js/control.js" %>
</script>

<script>
	$(function(){
		var check_serialno = $(".check_serialno").val();
		if(check_serialno == "フォローしている人がまだいません"){

			$("table").remove();
			$("#check_content").text("フォローしている人がまだいません");

		}else if(check_serialno == "フォロワーがまだいません"){

			$("table").remove();
			$("#check_content").text("フォロワーがまだいません");

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


			<!-- プロフィール画面に飛ぶわよ！ -->
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
			<!--<c:forEach var="data" items="${result}" end = "0">
			<div class="ct">${data.notifyCount}</div>
			</c:forEach>-->
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
	<!-- いらなそうだから消した　0303 14:55 <form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>フォロワー一覧</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>-->



 	<!--<c:forEach var="data" items="${requestbeen}">
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">

	 		<input type = "submit" name = "user_name" value= " ${data.name}" class="button">
	 		<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button">

	    </form>
	 </c:forEach> -->

	 <table border="0">
		<c:forEach var = "data" items = "${requestbeen}">
			<form method = 'post' id="myFORM" action = 'showprofiles'>
				<div id="check_content">
					<tr>

						<td>
							<input type="image" src="${data.icon}" style = "width:40px; height:40px;">
						</td>
						<td>
							<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
						</td>
						<td>
							<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
						</td>
						<td>
							<input type = "submit" name = "user_name" value= " ${data.name}" class="button" style="font-size:20px; color:#fff;">
						</td>
						<td id="userid" class="userid">
							<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button userid">
						</td>
						<td>
							<input type="checkbox" id="${data.serialuserid}"  class="followbtn" ${data.checkFollow}>
							<label class="followbtnbefore"  for ="${data.serialuserid}"></label>
							<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>
						</td>
						<td>
							<input type="hidden" id="" class="${data.serialuserid} check_serialno"  value="${data.serialuserid}">
						</td>
						<!-- 多分フォローとか必要だけどわからんから放置20200303 14:19	 <td>
							<input type="hidden" id="" class="${search.userNo} check_serialno" value="${search.userNo}">
							<input type="checkbox" id="${search.userNo}"  class="followbtn" ${search.check}>
						</td> -->
					</tr>
				</div>
			</form>
		</c:forEach>
	</table>
	<div id="check_content"></div>

</body>
</html>