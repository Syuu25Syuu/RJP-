<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>ログインベース</title>

<script>
function CheckPassword(){
    // 入力値取得
    var input1 = pass.value;
    var input2 = confirm.value;
    // パスワード比較
    if(input1 != input2){
        confirm.setCustomValidity("入力値が一致しません。");
    }
}

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
</head>

<body style="">
<form method="POST" action="createaccount" name="frml">
<div id="n">名前:<input type="text" name="name"  placeholder="名前" required></div>

<br><div id="i">ID:<input type="text" pattern="^[0-9A-Za-z]+$" name="id" placeholder="ID" required>※半角英数</div><br>





		<label>パスワード</label>
		<input type="password"  name="pass" id="pass" placeholder="パスワード" required>



	<br>

	<label>パスワード (再確認)</label>
	<input type="password"  name="confirm" placeholder="パスワード再確認"  required>


<br><div id="m">メール:<input type="text" name="mailadd" placeholder="メールアドレス" required>
</div>



		<br>

	  <p class="rink1" onclick="slideshowR()">←</p>
    <p class="rink2" onclick="slideshowL()">→</p>

		<a id="m1">
              <img id="mypic1" name = "mypic1" src="" class="panel-img">
              <input type="hidden" id="mypic2" name = "mypic2" value="" class="panel-img">
         </a>


	<br>


<br><div id="soushin"><input type="submit" onclick="CheckPassword()" value="送信" ></div>
</form>


	<input type="hidden" id = "flg1" type = "text" value="${sessionScope.flg}">





</body>
</html>