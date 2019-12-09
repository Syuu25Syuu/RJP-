package test1;

public class SearchUserCommand extends AbstractCommand{

	@Override
	//ユーザ検索ページに移動するためだけのクラス
	public ResponseContext execute() {
		ResponseContext resc = new WebResponseContext();
		//セッションに何かセットしないと例外が出るので適当な文字列をセット
		resc.setResult("flg");
		//ページのパスを指定する
		resc.setTarget("searchuser");
		return resc;
	}

}
