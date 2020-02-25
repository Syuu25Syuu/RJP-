$(function() {
// inputタグから取得
  $(".changeProfLike").on('click', function(){
		 $('#likeform').submit();
  });
  $(".changeProfPage").on('click', function(){
		 $('#showMoveForm').submit();
  });
  $(".changeProfImage").on('click', function(){
		 $('#imageform').submit();
  });

});