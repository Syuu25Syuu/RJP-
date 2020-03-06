$(function() {
// inputタグから取得
	$(".tweetbutton").on('click', function(){
    var imgSize1 = $('#fileUp');
    console.log(imgSize1);

    var fp = $("#fileUp");
    var lg = fp[0]
    .files.length;
    var items = fp[0].files;
    var fragment = "";
    if (lg > 0) {
        for (var i = 0; i < lg; i++) {
            var fileName = items[i].name; // ファイル名を取得
            var fileSize = items[i].size; // ファイルサイズを取得
            var fileType = items[i].type; // ファイルのタイプを取得
          // 表示

            console.log(fileSize);
            console.log(fileType);

            if(fileSize>50000000){
            	alert("size NG");
            	return false;		//ファイルサイズが大きい場合は送信をやめる
            }else if(fileType.indexOf('image') == -1){
            	alert("img NG");
            	return false;
            }else{
            	//return false;
            }
            //return false;

        }

    }


  });

});