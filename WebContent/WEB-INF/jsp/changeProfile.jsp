<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更画面</title>

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

</head>
<body>

	<form method = 'post' action = 'comebackhome' id = 'comebackhome'>
		<div onclick = "document.getElementById('comebackhome').submit();"><h1>変更</h1></div>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
	</form>

<c:forEach var="data" items="${result}" end = "0">

	<form method = "post" action="changeprofile"id = "profchangeform">
		名前:<br><input type = "text" name = "user_name" value= " ${data.name}" class="button"><br><br>
		自己紹介<br><textarea name ="contents" id="contents" cols="40" rows="4" maxlength="150" placeholder="いまどうしてる？" required >${data.profile}</textarea>
		<input type="hidden" name = "user_session" type = "text" value="${sessionScope.token.sessionToken}">
		<br>

	  <p class="rink1" onclick="slideshowR()">←</p>
    <p class="rink2" onclick="slideshowL()">→</p>

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