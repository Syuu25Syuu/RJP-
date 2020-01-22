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


<!-- 2020/01/16 さわだ画像選択追加 ーーーーーーーーーーーーーーーーー-->
<!--上段のキャラクターが配置されているエリア  -->
	<div id="container-top-characters" class="site-width">

	    <!-- for属性の値とinputタグのid属性の値を同じにすることで、ブラウザでラベルをクリックした際に、inputタグをクリックしたのと同じ動作が可能になる -->
	    <!-- labelタグにはjsで使うクラス「js-panel-select」を用意しておく-->
	    <label for="ice" class="panel panel-ceo js-panel-select">
	        <!-- ラジオボタンを用意する-->
	        <input type="radio" name="ceo-select" value="0" id="ice">
	        <img class="panel-img" src="/test1/images/icon/ice.png">
	    </label>
	    <label for="banana" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="1" id="banana">
	        <img class="panel-img" src="/test1/images/icon/banana.png">
	    </label>
	    <label for="game" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="2" id="game">
	        <img class="panel-img" src="/test1/images/icon/game.png">
	    </label>
	    <label for="kungfu" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="kungfu">
	        <img class="panel-img" src="/test1/images/icon/kungfu.png">
	    </label>
	    <label for="man" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="man">
	        <img class="panel-img" src="/test1/images/icon/man.png">
	    </label>
	    <label for="tree" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="tree">
	        <img class="panel-img" src="/test1/images/icon/tree.png">
	    </label>
	    <label for="truck" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="truck">
	        <img class="panel-img" src="/test1/images/icon/truck.png">
	    </label>
	    <label for="pirates" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="pirates">
	        <img class="panel-img" src="/test1/images/icon/pirates.png">
	    </label>
	    <label for="torch" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="torch">
	        <img class="panel-img" src="/test1/images/icon/torch.png">
	    </label>
	    <label for="bank" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="bank">
	        <img class="panel-img" src="/test1/images/icon/bank.png">
	    </label>
	</div>

	<!--下段のキャラクターが配置されているエリア  -->
	<div id="container-bottom-characters" class="site-width">
	    <label for="pc" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="3" id="pc">
	        <img class="panel-img" src="/test1/images/icon/pc.png">
	    </label>
	    <label for="peaches" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="peaches">
	        <img class="panel-img" src="/test1/images/icon/peaches.png">
	    </label>
	    <label for="dna" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="dna">
	        <img class="panel-img" src="/test1/images/icon/dna.png">
	    </label>
	    <label for="japan" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="japan">
	        <img class="panel-img" src="/test1/images/icon/japan.png">
	    </label>
	    <label for="beard" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="beard">
	        <img class="panel-img" src="/test1/images/icon/beard.png">
	    </label>
	    <label for="glasses" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="glasses">
	        <img class="panel-img" src="/test1/images/icon/glasses.png">
	    </label>
	    <label for="injection" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="injection">
	        <img class="panel-img" src="/test1/images/icon/injection.png">
	    </label>
	    <label for="wolf" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="wolf">
	        <img class="panel-img" src="/test1/images/icon/wolf.png">
	    </label>
	    <label for="camera" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="camera">
	        <img class="panel-img" src="/test1/images/icon/camera.png">
	    </label>
	    <label for="gun" class="panel panel-ceo js-panel-select">
	        <input type="radio" name="ceo-select" value="4" id="gun">
	        <img class="panel-img" src="/test1/images/icon/gun.png">
	    </label>
	</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>

	////キャラクター選択
	//クラス js-panel-select のDOMを変数に入れる
	var $selectPanel = $('.js-panel-select');

	//変数$selectPanelの要素をクリックしたとき
	$selectPanel.on('click', function(e){
	    //変数$selectPanelの要素のCSSを変更して、枠線を透明にする（前回クリックしたときの赤い枠線を消すため）
	    $selectPanel.css('border', '5px rgba(0,0,0,0) solid');
	    //クリックした要素のCSSを変更して枠線を赤くする
	    $(this).css('border', '5px red solid');

	    var imgSrc = $(this).children('img').attr('src');
	    $(this).children('input').val(imgSrc);

	    alert($(this).children('input').val());

	});

</script>
<style>
		/*
	======================================
	キャラクター選択画面
	======================================
	*/
	.characters-select-area img{
	    width: 100%;
	}
	.character-area{
	    float: left;
	}
	input[type=radio] {
	    display: none;/*ラジオボタンを見えないようにするCSS*/
	}
	.panel-ceo{
	    width: 10vw;
	    display: block;
	    float: left;
	    box-sizing: border-box;
	}
	#container-top-characters{
	    margin: 0 auto;
	    width: 100vw;
	    overflow: hidden;
	}
	#container-bottom-characters{
	    margin: 0 auto;
	    width: 100vw;
	    overflow: hidden;
	}
	.btn{
	    width: 50vw;
	    margin: 0 auto;
	    display: block;
	    font-size: 6vw;
	}
	.js-panel-select{
	    border: 5px rgba(0,0,0,0) solid;/*キャラクターパネルのデフォルトのCSS*/
	}
	.js-btn-permit{
	    background-color: yellow ;/*活性のときのボタンのCSS*/
	}
</style>
<!-- ここまで -->

<br><div id="soushin"><input type="submit" onclick="CheckPassword()" value="送信" ></div>
</form>


	<input type="hidden" id = "flg1" type = "text" value="${sessionScope.flg}">



</body>
</html>