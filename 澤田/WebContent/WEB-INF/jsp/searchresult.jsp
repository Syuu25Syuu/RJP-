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
	      $('#followbtn').on('click',function(){
	    	  console.log("フォローしたユーザ:"+$('#sessionId').val()+"\tフォローされたユーザ:"+$("#followbtn").val());
	        $.ajax({
	          url: "AjaxServlet",
	          type: "POST",
	          data: {userId : $('#sessionId').val(),followedNo : $("#followbtn").val()}
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
    </script>
</head>
<body>
	<table border="1">
		<tr><th>id</th><th>name</th></tr>
		<c:forEach var = "search" items = "${result}">
			<tr><td>${search.userId}</td><td>${search.userName}</td><td><input type="button" id="followbtn" value="${search.userNo}" ></td></tr>
		</c:forEach>
	</table>
	<input type="hidden" value="${sessionScope.token.sessionToken }" id="sessionId"></input>

</body>
</html>