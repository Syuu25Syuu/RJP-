<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

<script>
	    $(function(){

	      // ボタン押下時の処理
	      $('.followbtn').on('click',function(){
	    	  //フォローされる側のユーザシリアルNOを取得
	    	  var followedNo=$(this).attr("id");
	    	  var id="."+followedNo;
	    	  //フォローする側のユーザシリアルNOを取得
	    	  var userNo=$('#sessionId').val();

	    	  console.log("フォローしたユーザ:"+userNo+"\tフォローされたユーザ:"+$(id).val());
	    	  console.log("id="+id);
	    	  console.log("$(id).val()="+$(id).val());

	    	  //フォローされているかのチェック
	    	  var followedcheck="#"+followedNo;
	    	  console.log($(followedcheck).val());

	    	  if($(followedcheck).val()=='フォロー'){
	    	  	console.log("フォローします");
	    	  	$(followedcheck).val('解除');

	    	  	$.ajax({
	  	          url: "FollowAjaxServlet",
	  	          type: "POST",
	  	          data: {userNo : userNo,followedNo : followedNo}
	  	        }).done(function (result) {
	  	          // 通信成功時のコールバック

	  	          console.log("成功");
	  	        }).fail(function () {
	  	          // 通信失敗時のコールバック
	  	          alert("読み込み失敗");
	  	        }).always(function (result) {
	  	          // 常に実行する処理
	  	        });
	    	  }else if($(followedcheck).val()=='解除'){
	    		  $(followedcheck).val('フォロー');
	    		  console.log("解除します");
	    		  $.ajax({
		  	          url: "FollowCancelAjaxServlet",
		  	          type: "POST",
		  	          data: {userNo : userNo,followedNo : followedNo}
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
    </script>
</head>
<body>
	<table border="1">
		<tr><th>id</th><th>name</th></tr>
		<c:forEach var = "search" items = "${result}">
			<tr><td id="userid">${search.userId}</td><td>${search.userName}</td><td><input type="hidden" id="" class="${search.userNo}"
			 value="${search.userNo}"><input type="button" id="${search.userNo}"  class="followbtn" value="${search.check}"></td></tr>
		</c:forEach>
	</table>
	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>

</body>
</html>