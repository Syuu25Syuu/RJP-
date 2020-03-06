<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更画面</title>
<style>
	body{background-color:#15202B;color:#ffffff;}
	input{background-color:#15202B;color:#ffffff;}
	textarea{background-color:#15202B;color:#ffffff;}
</style>
<style>
	.imgchangebutton{
		display: -webkit-flex;
	}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
	var pics_src =["/test1/images/icon/ice.png","/test1/images/icon/banana.png","/test1/images/icon/game.png","/test1/images/icon/kungfu.png","/test1/images/icon/man.png","/test1/images/icon/tree.png","/test1/images/icon/truck.png","/test1/images/icon/pirates.png","/test1/images/icon/torch.png","/test1/images/icon/bank.png","/test1/images/icon/pc.png","/test1/images/icon/peaches.png","/test1/images/icon/dna.png","/test1/images/icon/japan.png","/test1/images/icon/beard.png","/test1/images/icon/glasses.png","/test1/images/icon/injection.png","/test1/images/icon/wolf.png","/test1/images/icon/camera.png","/test1/images/icon/gun.png"];



	var num1 = 0;




	window.onload = function () {    //ページ読み込み時に実行される
	        GOD1(2);
	        num3 = 0;
	};

		function GOD1(num2){
	       num1 = num2;
	       slideshowL();
	  	};

	function slideshowL(){
	     console.log(num1);
	    if (num1 == 19) {
	        num1 = 0;
	    }
	    else {
	        num1 ++;

	    }
	         console.log("Lだよ"+num1);

	    document.getElementById("mypic1").src=pics_src[num1];
	    document.getElementById("mypic2").value=pics_src[num1];


	}


	function slideshowR(){
	     console.log("Rだよ"+num1);
	    if (num1 == 0) {
			num1 =19;
	    }
	    else {
	        num1 --;
	    }
	         console.log(num1);

	         document.getElementById("mypic1").src=pics_src[num1];
	         document.getElementById("mypic2").value=pics_src[num1];

	    }




</script>
<script>
	$(function(){
		$('form').submit(function() {
			var name = $("#name").val();

			if(name.indexOf("\\") != -1 ||
					name.indexOf("<") != -1 ||
					name.indexOf(">") != -1 ||
					name.indexOf("/") != -1 ||
					name.indexOf("'") != -1 ||
					name.indexOf('"') != -1 ||
					name.indexOf("&") != -1 ||
					name.indexOf(";") != -1){

				alert("名前欄の入力文字が不正です");
				return false;
			}else if($('textarea').val().indexOf("'") != -1){
				alert("自己紹介欄に ' は入力できません");
				return false;
			}else{
	            this.submit();
	        }
	    });
	});
</script>
<script>
	$(function () {

	    $(document).keydown(function(event){

	        // クリックされたキーコードを取得する
	        var keyCode = event.keyCode;

	        // F5 の場合は falseをリターン
	        if(keyCode == 116) {
	            console.log("F5");
	            return false;
	        }

	        // バックスペースキーを制御する
	        if(keyCode == 8){
	            console.log("Backspace");
	            return false;
	        }
	    });
		//戻るボタンの向こう(historyback)セッションが残ってるから
		history.pushState(null, null, null);
		$(window).on("popstate", function (event) {
		  if (!event.originalEvent.state) {
		    history.pushState(null, null, null);
		    return;
		  }
		});
	});
</script>
</head>
<body>
	<form method = 'post' action = 'showprofiles' id = 'showprofiles'>
		<div onclick = "document.getElementById('showprofiles').submit();"><h1>⇦</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "user_id" value="${sessionScope.token.sessionToken}">
	</form>

<c:forEach var="data" items="${result}" end = "0">

	<div style="">*次の文字は名前には使えません: \ / < > ' " & ;</div>
	<div style="">*次の文字は自己紹介には使えません: '</div>
	<form method = "post" action="changeprofile"id = "profchangeform">
		名前:<br><input type = "text" id="name" name = "user_name" value= " ${data.name}" class="button"><br><br>
		自己紹介<br><textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required >${data.profile}</textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<input type="hidden" name = "user_id" value="${sessionScope.token.sessionToken}">
		<br>

		<div class="imgchangebutton">
		    <div class="rink1 rl" onclick="slideshowR()">
				<img src="/test1/images/left.png">
			</div>
			<p>　</p>
		   	<div class="rink2 rl" onclick="slideshowL()">
		   		<img src="/test1/images/right.png">
		   	</div>
		</div>

		<a id="m1">
              <img id="mypic1" name = "mypic1" src="" class="panel-img">
              <input type="hidden" id="mypic2" name = "mypic2" value="" class="panel-img">
         </a>


	<br>


		<input type="submit" value="変更する" >
	</form>

</c:forEach>
</body>
</html>