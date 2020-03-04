$(function(){

  // ボタン押下時の処理
  $('.usertweet').on('click',function(){
	  var tweetNo=$(this).attr("id");
	  var forms = "#showTweetForm"+tweetNo;
	  $(forms).submit();


  });



});