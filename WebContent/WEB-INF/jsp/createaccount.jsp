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
<form method="POST" action="createaccountservlet" name="frml">
<div id="n">名前:<input type="text" name="name"  placeholder="名前" required></div>

<br><div id="i">ID:<input type="text" pattern="^[0-9A-Za-z]+$" name="id" placeholder="ID" required>※半角英数</div><br>





		<label>パスワード</label>
		<input type="password"  name="pass" id="pass" placeholder="パスワード" required>



	<br>

	<label>パスワード (再確認)</label>
	<input type="password"  name="confirm" placeholder="パスワード再確認"  required>


<br><div id="m">メール:<input type="text" name="mailadd" placeholder="メールアドレス" required>
</div>

<br><div id="soushin"><input type="submit" onclick="CheckPassword()" value="送信" ></div>
</form>


	<input type="hidden" id = "flg1" type = "text" value="${sessionScope.flg}">



</body>
</html>