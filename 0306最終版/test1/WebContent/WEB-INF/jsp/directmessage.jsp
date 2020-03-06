<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/push.js/0.0.11/push.min.js" ></script>
	<style>
	<%@include file="./test.css" %>
	<%--<%@include file="./../css/index.css" %>--%>
	<%@include file="./../css/index.css" %>
	<%@include file="./../css/head.css" %>

</style>
<script>
	<%@include file="./js/control.js" %>
</script>
</head>
<body>
	<script>
		//自分が送信したメッセージの色が変わる
		function color(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			//console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージ送信者とsessionNoの値があっていればコメント部分の背景色の変更(時間は色変わらない)
			if($(this).children('li').eq('3').text() == sessionid){
				$(this).children(".dmcontent").css("background-color","#1DA1F2");
			}
		}
		//自分が送信したメッセージの位置が右にずれる
		function shift(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			//console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージ送信者とsessionNoの値があっていればコメント部分を右にずらす
			if($(this).children('li').eq('3').text() == sessionid){
				$(this).css("margin","20px 0 0 700px");

			}
		}
		//相手が送信したメッセージにだけアイコン画像を付ける
		function append(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			//console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージ送信者とsessionNoの値が違っていればアイコン画像を付ける
			if($(this).children('li').eq('3').text() != sessionid){
				var imgPath=$(this).children('li').eq('6').children('img').attr('src');
				$(this).append('<li><img src="'+imgPath+'" width="30px" height="30px"></li>');
			}
		}
		//ページロード時
		$(function(){
			//<ul>のなかをeachで順番に<li>があるだけ処理する
			$('ul').each(color);
			$('ul').each(shift);
			$('ul').each(append);



			//チャット部分のスクロールを一番下にする
			$('.chatplace').animate({scrollTop: $('.chatplace')[0].scrollHeight}, 0);
		});
		//DM送信のAjax--------------------
		$(function(){
			//送信ボタンを押されたら起動
			$('#messageSubmit').on('click',function(){
				//入力されたメッセージ、送信者・受信者のシリアルナンバーを取得
				var content=$('#message').val();
				var senduser=$('#sessionId').val();
				var receiveuser=$('#requestId').val();
				console.log(content);
				console.log(senduser);
				console.log(receiveuser);



				//メッセージ欄がからだったら
				//アラートを出して送らせない
				//tmpcontentは80行目以降は使わない
				//メッセージの中身が改行だけか
				//判定するよう
				var tmpcontent = content;
				//console.log("tmpcontentのながさ"+tmpcontent.length);
				tmpcontent = tmpcontent.replace( /\n/g,"");
				if(!content || tmpcontent.length == 0){
					alert("メッセージを入力してください");
				}else if(content.indexOf("'") != -1){
					alert("メッセージに ' は入力できません");
				}else{
					/*
					//メッセージ欄があれば送る処理
					//ここから1行を15文字以内にするコード-----------------------------------------------
					//splitで1行ごとに分けて配列にいれる
					var list = content.split("\n");
					//要素を１つずつループで回す
					for(var i=0; i < list.length; i++){
						//allは要素の中の全ての文字
						//(例)'あいうえおかきくけこ'
						var all = "";

						//lastは要素内のもじのsubstrでとった
						//後半部分を保持する
						//定義時は要素をそのまま入れる(一時的に)
						var last = list[i];

						//要素内の文字全てを見るまでループ
						while(last.length > 0){
							//要素内の残っている文字を
							//前から15文字で区切り
							//その後ろに改行コードを入れる
							var tmp = last.substr(0,15);
							last = last.substr(15);
							all += tmp+"\n";
						}
						//整理し終わったら配列に戻す
						list[i] = all;
						//要素内がからだったら改行コードを入れる
						if(list[i] == ""){
							list[i] = "\n";
						}
					}
					console.log(list);
					var sortcontent = "";
					//配列の中身を一つの文字列にする
					//最後の要素に入っている改行コードは
					//自動でなくなるみたい
					for(var i=0; i < list.length; i++){
						sortcontent += list[i];
					}
					sortcontent = sortcontent.slice(0,-1);*/
					//-----------------------------------------------------------
					//エスケープ処理
					/*
					content = content.replace(/&amp;/g,"&amp;amp;");
					content = content.replace(/&lt;/g,"&amp;lt;");
					content = content.replace(/&gt;/g,"&amp;gt;");
					content = content.replace(/&quot;/g,"&amp;quot;");
					content = content.replace(/&apos;/g,"&amp;apos;");
					content = content.replace(/"/g,'\"');
					content = content.replace(/'/g,"\'");
					content = content.replace(/</g,"&lt;");
					content = content.replace(/>/g,"&gt;");*/

					//ここから改行を反映するためのコード----------------------------
					//改行が反映されるようにする(jspに)
					//メッセージ内容を最後まで見て
					//改行コード("\n")があれば<br>に変換する
					//content = content.replace( /\n/g,"<br>");
					//---------------------------------------------------------------
					//送信したときにメッセージのテキストボックス内
					//の値を消す(見た目的に)
					$('#message').val("");
					//Ajaxを使って取得したパラメータをサーブレットに送信
					$.ajax({
						url: "sendmessage",
						type: "POST",
						data: {content : content,sendUser : senduser,receiveUser : receiveuser}
					}).done(function (result) {
						// 通信成功時のコールバック
						console.log("成功");
					}).fail(function () {
						// 通信失敗時のコールバック
						alert("読み込み失敗");
					}).always(function (result) {
						// 常に実行する処理
					});
				}
			});
		});
		//新しいメッセージが来ているか1秒ごとに
		//確認しに行くWebsocketを使ってる
		$(function() {
			//スクロールを一番下にずらすときに使う
			var lastscroll=$('.chatplace').scrollTop();

    		setInterval(function(){
				//websocketのurlとwebsocketのインスタンスを生成
    			var url = "ws://172.19.8.247:8888/test1/WebSocketServer";
  	    	  	var ws = new WebSocket(url);
				//送信者、受信者のシリアルナンバーと
				//現在の最後のメッセージの送信時間を取得する
  	    	  	var senduser=$('#sessionId').val();
				var receiveuser=$('#requestId').val();
				var lastmessagedmtime=$("ul").last().children().eq(5).text().replace('.0', '');
				if(lastmessagedmtime == ""){
					lastmessagedmtime = "2020-01-01 00:00:00";
				}

				//websocketserverから受け取った値をページに出力
  	    	  	ws.onmessage = function(receive) {
					//１つのメッセージのデータを"|"で区切っているので
					//メッセージを１つずつ格納
  	    	  		var a=receive.data.split("|");
  	    	  		for(var i=0; i<a.length-1; i++){
						//メッセージの送信者や受信者を","で区切っているので
						//送信者、受信者、メッセージ、送信時間などを格納
  	    	  			var b=a[i].split(",");
						//取得したデータを追加する
						//エスケープ処理
						b[2] = b[2].replace(/&amp;/g,"&amp;amp;");
						b[2] = b[2].replace(/&lt;/g,"&amp;lt;");
						b[2] = b[2].replace(/&gt;/g,"&amp;gt;");
						b[2] = b[2].replace(/&quot;/g,"&amp;quot;");
						b[2] = b[2].replace(/&apos;/g,"&amp;apos;");
						b[2] = b[2].replace(/&nbsp;/g,"&amp;nbsp;");
						b[2] = b[2].replace(/"/g,'\"');
						b[2] = b[2].replace(/'/g,"\'");
						b[2] = b[2].replace(/</g,"&lt;");
						b[2] = b[2].replace(/>/g,"&gt;");
						b[2] = b[2].replace( /\n/g,"<br>");

  	    	  			$(".chatplace").append('<ul class="chatdata">'+
	  	    	  									'<li id="userid" hidden>'+b[0]+'</li>'+
	  	    	  									'<li hidden>'+b[1]+'</li>'+
		  	    	  								'<li class="dmcontent">'+b[2]+
		  	    	  								'<li hidden>'+b[3]+'</li>'+
		  	    	  								'<li hidden>'+b[4]+'</li>'+
		  	    	  								'<li>'+b[5]+'</li>'+
	  	    	  								'</ul>'
	  	    	  		);
						//console.log(b[2]);
						//自分がツイートした場合は現在のスクロールの位置に依存せずに
						//スクロールを一番下にずらす
  	    	  			if(b[3]==senduser){
  	    	  				$('.chatplace').animate({scrollTop: $('.chatplace')[0].scrollHeight}, 0);
  	    	  			}
						//相手からメッセージを受信した場合は
						//現在のスクロールの位置によってスクロールをずらすorずらさない
  	    	  			else{
  	    	  				//相手が送信したメッセージにだけアイコン画像を付ける
  	    	  				$('.chatplace > ul >li:last').append('<li><img src="'+b[6]+'" width="30px" height="30px"></li>');
  	    	  				//現在のスクロールの位置が一番下の場合は
  	    	  				//スクロールを一番下にずらす
  	    	  				if(lastscroll <= $('.chatplace').scrollTop()){
  	    	  					$('.chatplace').animate({scrollTop: $('.chatplace')[0].scrollHeight}, 0);
  	    	  					lastscroll=$('.chatplace').scrollTop();
  	    	  				}
  	    	  				//現在のスクロール位置が一番下より上の場合は
	    	  				//現在のスクロール位置をkeepする
  	    	  				else{
  	    	  					//処理なし
  	    	  				}
  	    	  			}
  	    	  		}
					//自分の送信したメッセージの色を変える
  	    	  		$("ul").each(color);
  	    	  		$('ul').each(shift);
  	    	  	};
				//websocketserverに取得した値を送る
  	    	  	ws.onopen = function() {
  	    	    	ws.send(senduser+","+receiveuser+","+lastmessagedmtime);
  	    	    	console.log("送信しました");
  	    	  	};
    		},1000);
	    });
	</script>

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

	<h1>${requestScope.requestbeen[0].receiveusername }</h1>
	<div class="chatplace">
		<c:forEach var = "dm" items = "${requestbeen}">
			<ul class="chatdata">
				<li id="userid" hidden>${dm.userId}</li>
				<li hidden>${dm.userName}</li>
				<li class="dmcontent">${dm.dmContent}</li>
				<li hidden>${dm.sendUserNo}</li>
				<li hidden>${dm.receiveUserNo}</li>
				<li>${dm.dmTime}</li>
				<li hidden><img src="${dm.userIcon}" width="30px" height="30px"><li>
			</ul>
		</c:forEach>
	</div>

	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId">
	<input type="hidden" value="${requestScope.requestbeen[0].receiveUserNo }" id="requestId">
	<br><br><br><br>
	<div class="Footer">
		<!-- <input type="text" id="message" placeholder="メッセージを入力" required> -->
		<textarea rows="2" cols="30" maxlength="100" name="textareacontent" id="message" placeholder="メッセージを入力" style="background-color:#15202B;"></textarea>
		<input type="submit" id="messageSubmit">
	</div>
	<style>
		/*メッセージ入力欄部分を固定する*/
		.Footer {
			position: fixed; /* フッターを固定する */
			bottom: 0; /* 上部から配置の基準位置を決める */
			left: 0; /* 左から配置の基準位置を決める */
			width: 100%; /* フッターの横幅を指定する */
			height: 35px; /* フッターの高さを指定する */
			padding:10px; /* フッター内側の余白を指定する(上下左右) */
			background-color: #31a9ee; /* フッターの背景色を指定する */
			color: #FFFFFF; /* フッターのフォントの色を指定する */
		}
		/*チャットする部分にスクロールバーを付ける*/
		.chatplace {
			overflow:auto;
			width:1000px;
			height:400px;
			padding:5px;
			border:1px solid #000;
			background-color: #15202B;
			color:#000;
			font-size:12px;
		}
		/*上記でつけたスクロールバーを見えなくする(なんとなく見栄え)*/
		.chatplace::-webkit-scrollbar {  /* Chrome, Safari 対応 */
        	display:none;
        }
        ul {
        	list-style: none;
        }
        .dmcontent{
        	/*今のとこ全角2文字で改行*/
        	width:120px;
        	/*半角英数も改行されるようにする*/
        	word-break: break-all;
        	/*文字サイズ*/
        	font-size:12px
        }
        /*textarea(メッセージ入力場所)スクロールバーを見えなくする(なんとなく見栄え)*/
		textarea::-webkit-scrollbar {  /* Chrome, Safari 対応 */
        	display:none;
        }
        textarea{
        	resize: none;
        }
	</style>
</body>
</html>