<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
</head>
<body>
	<script>
		//自分が送信したメッセージの色が変わる
		function color(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージ送信者とsessionNoの値があっていればコメント部分の背景色の変更(時間は色変わらない)
			if($(this).children('li').eq('3').text() == sessionid){
				$(this).children(".dmcontent").css("background-color","lightgreen");
			}
		}
		//自分が送信したメッセージの位置が右にずれる
		function shift(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
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
			console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージ送信者とsessionNoの値が違っていればアイコン画像を付ける
			if($(this).children('li').eq('3').text() != sessionid){
				var imgPath=$(this).children('li').eq('6').children('img').attr('src');
				$(this).append('<li><img src="'+imgPath+'" width="30px" height="30px"></li>');

			}
		}
		//メッセージに改行追加(15文字ごと)できてないーーーーーーーーーーーーーーーーーーーーーーーーー
		function a(){
			//sessionNoを取得
			var sessionid=$("#sessionId").val();
			//<ul>のなかの3番目(0からスタート)の<li>のタグボディの値を取得
			console.log("送信者のシリアルナンバー:"+$(this).children('li').eq('3').text());
			//メッセージの長さ取得
			var messagelength=$(this).children('li').eq('2').text().length;

			if(messagelength > 15){
				var count=0;
				var message;
				/*for(var i=15; i > messagelength; i=i+15){
					var messageplus=messagelength.substr(a,i);
					messageplus+='\r\n';
					count=i+1;
					message+=messageplus;
				}*/
				console.log($(this).children('li').eq('2').text().substr(0,15)+'\r\xnaaa');
				$(this).children('li').eq('2').text($(this).children('li').eq('2').text()+'\r\naaa');
			}
		}
		//ページロード時
		$(function(){
			//<ul>のなかをeachで順番に<li>があるだけ処理する
			$('ul').each(color);
			$('ul').each(shift);
			$('ul').each(append);
			$('ul').each(a);
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
				//送信したときにメッセージのテキストボックス内
				//の値を消す(見た目的に)
				$('#message').val("");
				//Ajaxを使って取得したパラメータをサーブレットに送信
				$.ajax({
					url: "SendMessageDMAjaxServlet",
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
			});
		});
		//新しいメッセージが来ているか1秒ごとに
		//確認しに行くWebsocketを使ってる
		$(function() {
			//スクロールを一番下にずらすときに使う
			var lastscroll=$('.chatplace').scrollTop();

    		setInterval(function(){
				//websocketのurlとwebsocketのインスタンスを生成
    			var url = "ws://172.19.4.78:8888/test1/WebSocketServer";
  	    	  	var ws = new WebSocket(url);
				//送信者、受信者のシリアルナンバーと
				//現在の最後のメッセージの送信時間を取得する
  	    	  	var senduser=$('#sessionId').val();
				var receiveuser=$('#requestId').val();
				var lastmessagedmtime=$("ul").last().children().eq(5).text().replace('.0', '');

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
  	    	  			$(".chatplace").append('<ul class="chatdata">'+
	  	    	  									'<li id="userid" hidden>'+b[0]+'</li>'+
	  	    	  									'<li hidden>'+b[1]+'</li>'+
		  	    	  								'<li class="dmcontent">'+b[2]+'</li>'+
		  	    	  								'<li hidden>'+b[3]+'</li>'+
		  	    	  								'<li hidden>'+b[4]+'</li>'+
		  	    	  								'<li>'+b[5]+'</li>'+
	  	    	  								'</ul>'
	  	    	  		);
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
					//チャット部分のスクロールを一番下にする
					//$('.chatplace').animate({scrollTop: $('.chatplace')[0].scrollHeight}, 0);
  	    	  	};
				//websocketserverに取得した値を送る
  	    	  	ws.onopen = function() {
  	    	    	ws.send(senduser+","+receiveuser+","+lastmessagedmtime);
  	    	    	console.log("送信しました");
  	    	  	};
    		},1000);
	    });
	</script>
	<h1>相手の名前:${requestScope.requestbeen[1] }</h1>
	<div class="chatplace">
		<c:forEach var = "dm" items = "${result}">
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

	<input type="text" value="${sessionScope.token.sessionToken }" id="sessionId">
	<input type="text" value="${requestScope.requestbeen[0] }" id="requestId">
	<br><br><br><br>
	<div class="Footer">
		<input type="text" id="message" placeholder="メッセージを入力" required>
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
			background-color:#F9F9F9;
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
	</style>
</body>
</html>