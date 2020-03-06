<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>ログインベース</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
	<%@include file="./../css/createaccount.css" %>
</style>
<script>
	/*function CheckPassword(){
	    // 入力値取得
	    var input1 = pass.value;
	    var input2 = confirm.value;
	    // パスワード比較
	    if(input1 != input2){
	        confirm.setCustomValidity("入力値が一致しません。");
	    }
	}*/

	window.onload = function(){
		var elm = document.getElementById("flg1");
		if(elm.value == "アカウントの作成に失敗しました"){
			alert(elm.value);
		}

	}





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
			var id = $("#id").val();
			var pass = $("#pass").val();
			var check_pass = $("#check_pass").val();

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
			}else if(id.indexOf("\\") != -1 ||
					id.indexOf("<") != -1 ||
					id.indexOf(">") != -1 ||
					id.indexOf("/") != -1 ||
					id.indexOf("'") != -1 ||
					id.indexOf('"') != -1 ||
					id.indexOf("&") != -1 ||
					id.indexOf(";") != -1){

				alert("ID欄の入力文字が不正です");
				return false;
			}else if(pass.indexOf("\\") != -1 ||
					pass.indexOf("<") != -1 ||
					pass.indexOf(">") != -1 ||
					pass.indexOf("/") != -1 ||
					pass.indexOf("'") != -1 ||
					pass.indexOf('"') != -1 ||
					pass.indexOf("&") != -1 ||
					pass.indexOf(";") != -1){

				alert("パスワード欄の入力文字が不正です");
				return false;
			}else if(check_pass.indexOf("\\") != -1 ||
					check_pass.indexOf("<") != -1 ||
					check_pass.indexOf(">") != -1 ||
					check_pass.indexOf("/") != -1 ||
					check_pass.indexOf("'") != -1 ||
					check_pass.indexOf('"') != -1 ||
					check_pass.indexOf("&") != -1 ||
					check_pass.indexOf(";") != -1){

				alert("パスワード欄の入力文字が不正です");
				return false;
			}else if(pass != check_pass){
				alert("パスワードが一致しません");
				return false;
			}else {
	            this.submit();
	        }
	    });
	});
</script>
<script>
	$(function(){
		var check = $("#check").val();
		if(check == "かぶってた"){
			alert("ユーザーIDが既に存在しました");
		}
	});
</script>
</head>

<body style="">
<h1>アカウント作成</h1>
<div style="">*次の文字は名前、ID、パスワードには使えません: \ / < > ' " & ;</div>
<form method="POST" action="createaccount" name="frml">
<div id="n">名前:<input type="text" name="name" id="name" placeholder="名前(30文字以内)" size="30" maxlength="30" required></div>

<br><div id="i">ID:<input type="text" pattern="^[0-9A-Za-z]+$" name="id" id="id" placeholder="ID(15文字以内)" size="30" maxlength="15" required>※半角英数字</div><br>




		<label>パスワード</label>
		<input type="password" pattern="^[0-9a-zA-Z]+$" name="pass" id="pass" placeholder="パスワード(8文字以上15文字以内)" size="30" minlength="8" maxlength="15" required>※半角英数字


	<br>

	<label>パスワード (再確認)</label>
	<input type="password"  name="confirm" id="check_pass" placeholder="パスワード再確認" size="30" maxlength="15" required>
	<br>



<br><div id="m">メール:<input type="email" name="mailadd" placeholder="メールアドレス" size="30" required>
</div>



		<br>


		<a id="m1">
              <img id="mypic1" name = "mypic1" src="" class="panel-img">
              <input type="hidden" id="mypic2" name = "mypic2" value="" class="panel-img">
         </a>
<div class="button">
	<div class="rink1 rl" onclick="slideshowR()">
		<img src="/test1/images/left.png">
	</div>
   	<div class="rink2 rl" onclick="slideshowL()">
   		<img src="/test1/images/right.png">
   	</div>
</div>
	<br>


<br><div id="soushin"><input type="submit" id="submit_button" value="送信" ></div>
</form>


	<input type="hidden" id = "flg1" type = "text" value="${sessionScope.flg}">
	<input type="hidden" id = "check" value="${requestbeen}">




</body>
</html>