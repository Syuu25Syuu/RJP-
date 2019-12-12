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
		$(function(){
			//trにつけたclassをeachで順番にtrがあるだけ処理する
			$(".senduser").each(function(){
				//sessionNoを取得
				var sessionid=$("#sessionId").val();
				//n番目のtrのなかの3番目(0からスタート)のtdのタグボディの値を取得
				console.log("送信者のシリアルナンバー:"+$(this).children('td').eq('3').text());
				//メッセージ送信者とsessionNoの値があっていれば背景色の変更
				if($(this).children('td').eq('3').text() == sessionid){
					$(this).css("background-color","lightgreen");
				}
			})
		});
		//DMのAjax--------------------
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

		$(function(){
			$('#reload').on("click",function(){
				$.get("directmessage").done(function(data, textStatus, jqXHR) {
				    const doc = new DOMParser().parseFromString(data, 'text/html');
				    $('#chatplace').html(doc.querySelector('#chatplace').innerHTML);
				  });
			});
		});

	</script>
	<h1>DM</h1>
	<table border="1" id="chatplace">
		<tr><th>id</th><th>name</th><th>content</th></tr>
		<c:forEach var = "dm" items = "${result}">
			<tr class="senduser"><td id="userid">${dm.userId}</td><td>${dm.userName}</td><td>${dm.dmContent}</td>
			<td>${dm.sendUserNo}</td><td>${dm.receiveUserNo}</td><td>${dm.dmTime }</td></tr>
		</c:forEach>
	</table>
	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId">
	<input type="hidden" value="${requestScope.requestbeen }" id="requestId">
	<input type="text" id="message" placeholder="メッセージを入力">
	<input type="submit" id="messageSubmit">
	<input type="button" id="reload" value="テーブル更新">
</body>
</html>