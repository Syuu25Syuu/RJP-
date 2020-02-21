$(function(){

  // ボタン押下時の処理
  $('.deletebtn').on('click',function(){
	  //フォローされる側のユーザシリアルNOを取得

	  var tweetNo=$(this).attr("id");
	  var id="#"+tweetNo;
	  var tweetclass = "#tweetdata"+tweetNo;
	  //フォローする側のユーザシリアルNOを取得
	  console.log("id="+id);

	  console.log(tweetclass);

	  var del="#"+tweetNo+"delete";

	  var a= $('#deletetweets').attr("id");
	  console.log("$(id).val()="+$(a).val());

	  console.log(a);


	  var result = window.confirm($(del).val());

	    if( result ) {

	    	$.ajax({
		  		url: "DeleteTweetAjaxServlet",
	 	        type: "POST",
	 	        data: {tweetNo : tweetNo}
		  	}).done(function (result) {
		          // 通信成功時のコールバック

		          console.log("成功");
		        }).fail(function () {
		          // 通信失敗時のコールバック
		          alert("読み込み失敗");
		        }).always(function (result) {
		          // 常に実行する処理
		        });

	    	$(tweetclass).attr({
	    		  'display': 'none'
	    		});
	    	$(tweetclass).attr("style","display:none");
	    	console.log("delete ok");

	    }
	    else {

	    }


  });


  /*1月23日追加分*/
  $('.changeProf').on('click',function(){
	  $('#profchangeform').submit();
  });




});