$(function(){

  // ボタン押下時の処理
  $('.likebtn').on('click',function(){
	  //フォローされる側のユーザシリアルNOを取得

	 var likecheck =  $(this).attr("id");
	 var id="."+likecheck;
	 console.log(likecheck);
	 var sessionToken2 = document.getElementById("sessionToken");
	 var sessionToken = sessionToken2.innerHTML


	  var likechecks = document.getElementById(likecheck);
	  console.log(likechecks);


	  	$.ajax({
	          url: "LikeAjaxServlet",
	          type: "POST",
	          data: {sessionToken : sessionToken,tweetID : likecheck}
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