<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./test.css" %>
	<%--<%@include file="./../css/index.css" %>--%>
	<%@include file="./../css/index.css" %>
	<%@include file="./../css/head.css" %>

</style>

<script charset="utf-8" >
	<%@include file="./js/likecheck.js" %>
	<%@include file="./js/rtcheck.js" %>
	<%@include file="./js/deleteTweet.js" %>
	<%@include file="./js/createTweet.js" %>
	<%@include file="./js/showTweet.js" %>
</script>

<script>
	$(function(){
		$(".open_button").on("click",function(){
			var rpval = $(this).attr("id");
			rpval = rpval.replace(/rp/g,"");
			//alert(rpval);
			$('input[name="replyid"]').val(rpval);
			//alert($('input[name="replyid"]').val());
		})
	});
</script>

</head>
<body>
<div class="header">

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>ホーム画面</h1></div>
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
			<c:forEach var="data" items="${requestbeen}" end = "0">
				<div onclick = "document.getElementById('showprofiles').submit();"><img src="${data.profImage}" class="a"></div>
	 			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 			<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
			</c:forEach>
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
	</div>

	<div class="serch">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- <form action="search_tweet" method="post" class="sr">
			<input type="text" size="25" name="tweetWord" placeholder="キーワード検索">
			<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
			<input type="submit" value="&#xf002">
		</form>-->
		<form action="search" method="post">
		<label>ユーザ<input type="radio" name="select" value="user" checked></label>
		<label>ツイート<input type="radio" name="select" value="tweet"></label>
		<input type="text" name="keyword" placeholder="キーワードを入力" >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="submit" value="検索">
	</form>
	</div>

	<!-- 通知 -->
	<form method="post" action="notify">
		<input type="hidden" name="session_id" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name="check_value" value="">
		<input type="submit" value="通知ページへ">
	</form>
	<div>${requestbeen[1]}</div>

</div>

<div class="tweet">
	<form method = 'post' action = 'UploadServlet' enctype="multipart/form-data" id = "upForm">
		<label for="kanso"></label><br>
		<textarea class="box color"  name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required></textarea>
		<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="file" name="inputFile" accept="image/*" id = "fileUp">
		<input type='submit' value='ツイート' class="tweetbutton color">
	</form>
</div>


<c:forEach var="data" items="${result}" >
	<div class="tweetdata" id = "tweetdata${data.tweetId}" >
 		<div class="rtuser">${data.rtuser}</div>
	 	<form method = 'post' id="myFORM" action = 'showprofiles'>

	 		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">

	 		<!-- ここを変えた(12-13) -->
			<input type="image" src= "${data.icon}" class="pro">
	 		<input type = "submit" name = "user_name" value= " ${data.name}" class="button username">
	 		<input type = "submit" name = "user_id" value= " ＠${data.id}" class="button userid">
	 		<!-- 以上 -->
	 		${data.tweetdate}

	    </form>

		<!-- ツイートに添付されている画像 -->
		<!-- imgタグごとServletで送信しちゃってる -->
	   <img src = "${data.tweetImg}" class = "tweetImgage ${data.tweetImageNone} " >

	    <br>

		<!-- ツイート本文 -->
		<!-- ボタンに変えたよ -->

		<form method = 'post' action = 'viewtweet' id ="showTweetForm${data.tweetId}Tweet">
	    	<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "tweet_id" type = "text" value="${data.tweetId}">
	    	<button class="button usertweet" id ="${data.tweetId}Tweet" >${data.tweet}</button>
	    </form>

	    <br>

	    <form method = "post" action = 'liketweet'>
	    	<div class="heart">
	    		<input type="checkbox" id="${data.tweetId}like"  class="likebtn" ${data.checklike} value="${data.tweetId}">
	    		<label class="ht" for="${data.tweetId}like"></label>
				<div id ="likecount" class = "likecount">${data.countLike}</div>
	    		<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    		<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
				<div id = "sessionToken">${sessionScope.token.sessionToken}</div>
			</div>
	    </form>

	    <form method = "post" action = 'rttweet'>
	    	<div class="retweet">
	    		<input type="checkbox" id="${data.tweetId}rt"  class="rtbtn retweetafter" ${data.checkRT} value="${data.tweetId}">
				<label class="retweetbefore" for="${data.tweetId}rt"></label>
				<div id ="rtcount" class ="rtcount">${data.countRT}</div>
	    		<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	    		<input type="hidden" name = "tweetID" type = "text" value="${data.tweetId}">
	    	</div>
	    </form>





	    <div class="replyballoon">
	    	<!-- リプライ数を表示。雑に置いてごめんなさい -->
	    	<div id ="replytcount" class ="rpcount">${data.countReply}</div>
	    	<!-- リプライ数表示　終わり -->
	    	<input id="balloon" class="balloon" type="checkbox">
			<div class="replybox">
				<label for="balloon" class="rep"></label>
				<div class="reply">

				<label for="balloon" class="close_button">×</label>	<!-- ここを変えればよいっぽい？ -->
					<div>
						<form method = 'post' action = 'replytweet'class = "replycontent">
							<textarea class="box color repbox" name ="replycontent" id="replycontent" cols="40" rows="4" maxlength="150" placeholder="返信をツイート" required></textarea>
							<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
							<input type="hidden" name = "replyid" type = "text" value="${data.tweetId}">
							<input type='submit' value='返信' class="replaybutton color">
						</form>
   					</div>
				</div>
			</div>
		</div>

		 <!-- 2月5日　削除ボタン -->

	    	<input type = "image" src = "/test1/images/garbage.png" class = "deletebtn" id = "${data.tweetId}" ${data.mytweetCheck}>
	    	<input type = "hidden" value = "削除しますか?" class = "deletetweets" id = "${data.tweetId}delete">



		<div class="balloon">
			<label for="balloon" class="open_button" id="${data.tweetId}rp">
			</label>
		</div>
	<br>
	</div>
</c:forEach>




</body>
</html>