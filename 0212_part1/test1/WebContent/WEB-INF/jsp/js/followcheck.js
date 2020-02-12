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

  });


  /*1月23日追加分*/
  $('.changeProf').on('click',function(){
	  $('#profchangeform').submit();
  });




});