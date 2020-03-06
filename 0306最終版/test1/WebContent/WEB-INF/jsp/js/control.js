$(function () {

    $(document).keydown(function(event){

        // クリックされたキーコードを取得する
        var keyCode = event.keyCode;

        // F5 の場合は falseをリターン
        if(keyCode == 116) {
            console.log("F5");
            return false;
        }

        // バックスペースキーを制御する
        if(keyCode == 8){
            console.log("Backspace");
            return false;
        }
    });
	//戻るボタンの向こう(historyback)セッションが残ってるから
	history.pushState(null, null, null);
	$(window).on("popstate", function (event) {
	  if (!event.originalEvent.state) {
	    history.pushState(null, null, null);
	    return;
	  }
	});
});
