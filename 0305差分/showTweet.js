$(function(){

  // ボタン押下時の処理
  $('.usertweet').on('click',function(){
	  var tweetNo=$(this).attr("id");
	  var forms = "#showTweetForm"+tweetNo;
	  $(forms).submit();


  });

  $('.usertProf').on('click',function(){
	  console.log("saaaaaaaaaaa");
	  var tweetNo=$(this).attr("id");
	  tweetNo = tweetNo.replace("I", "");
	  var forms = "#myFORM"+tweetNo;
	  $(forms).submit();


  });



});