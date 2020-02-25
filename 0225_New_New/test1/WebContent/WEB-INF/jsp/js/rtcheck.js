$(function(){

  // ボタン押下時の処理
  $('.rtbtn').on('click',function(){
	  //フォローされる側のユーザシリアルNOを取得

	 var rtcheck =  $(this).attr("id");
	 var id="."+rtcheck;
	 console.log(rtcheck);
	 var sessionToken2 = document.getElementById("sessionToken");
	 var sessionToken = sessionToken2.innerHTML


	  var rtchecks = document.getElementById(rtcheck);
	 // console.log(likechecks);


	  	$.ajax({
	          url: "RTAjaxServlet",
	          type: "POST",
	          data: {sessionToken : sessionToken,tweetID : rtcheck}
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