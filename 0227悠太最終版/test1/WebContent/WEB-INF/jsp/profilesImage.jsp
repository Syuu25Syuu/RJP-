<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール画面</title>
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
	<%@include file="./js/changeProf.js" %>
	<%@include file="./js/showTweet.js" %>
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
		<div class="ct">${requestbeen[1]}</div>
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


	<c:forEach var="data" items="${result}" end = "0">


	<!-- プロフィールのツイート切り替え用のform -->

	<form id = "likeform" method = "post" action = "showproflike">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">

	</form>

	<form id = "tweetform" method = 'post' action = 'showprofiles' >
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "user_id" type = "text" value="${data.serialuserid}">
		<input type="hidden" name = "image_path" id = "image_path" type = "text" value="${data.profImage}">
	</form>

	<form id = "profchangeform" method = 'post' action='goChangeProfile'>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "image_path" id = "image_path" type = "text" value="${data.profImage}">
	</form>



	<!-- ここまで -->


		<img id="mypic1" name = "mypic1" src="${data.profImage}" class="panel-img">

		<!-- 名前とID表示 -->
		<h2>${data.profUserName}</h2>
		<h5>@${data.profUserId}</h5>

		<!-- フォロワーボタン -->
		<input type="checkbox" id="${data.serialuserid}"  class="${data.followbtn}" ${data.checkFollow}>
		<label class="${data.followbtn}before"  for ="${data.serialuserid}"></label>
		<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>


		<!-- プロフィールの文 -->
		<h3>${data.profile}</h3>

		<form method = 'post' action = 'showFollows'>
			<input type = "submit" value="${data.countFollows}" class = "count">
			<p>フォロー中</p>
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
		</form>

		<form method = 'post' action = 'showFollowers'>
			<input type = "submit" value="${data.countFollowers}" class = "count">
			<p>フォローされています</p>
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
		</form>

	</c:forEach>

	<br><br>

	<form id = "tweetform" method = "post" action = "showproftweet">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.serialuserid}">
	</form>


	<div class="tabs">
	<c:forEach var="data" items="${result}" end = "0">
	<form method = "post" id = "showMoveForm" action = "showprofiles" >
	  <input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	  <input type="hidden" name = "user_id" type = "text" value="${data.profChangeId}">
	  <input id="all" type="radio" name="tab_item" class="changeProfPage" ${data.tabCheck_tweet}>
	  <label class="tab_item" for="all">ツイート</label>
	 </form>

	  <input id="programming" class="changeProfImage" type="radio" name="tab_item" ${data.tabCheck_image}>
	  <label class="tab_item" for="programming">画像</label>


	   <form id = "likeform" method = "post" action = "showproflike">
			<input type="hidden" name = "sessionToken" type = "text" value="${sessionScope.token.sessionToken}">
	 		<input type="hidden" name = "serialuserid" type = "text" value="${data.profChangeId}">
	 		<input id="design" class="changeProfLike" type="radio" name="tab_item"  ${data.tabCheck_like}>
	  		<label class="tab_item" for="design">いいね</label>
	  </form>

	 </c:forEach>
	 <div class="tab_content" id="all_content">
	    <div class="tab_content_description">

	    </div>
	  </div>
	  <div class="tab_content" id="programming_content">
	  <c:forEach var="data" items="${result}"  end = "0">
	  	<p class ="${data.notnullPo }">画像はありません</p>
	    <div class="tab_content_description ${data.nullPo}">
	   </c:forEach>

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
				<label for="balloon" class="open_button">
				</label>
			</div>
		<br>
		</div>
	</c:forEach>
	    </div>
	  </div>
	  <div class="tab_content" id="design_content">
	    <div class="tab_content_description">


	    </div>
	  </div>
	</div>

	<br><br>

</body>
</html>