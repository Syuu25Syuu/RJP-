package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.SearchUserPRTest;
/*
 パスワードリマインダーで使うクラス
 メールアドレスをもとにユーザ検索をする
 */
public class SearchUserPRCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  mailaddress = reqc.getParameter("mailaddress")[0];

		ResponseContext resc = new WebResponseContext();
		//ーーーーーーーーーーDB関係ーーーーーーーーーーーーーーーー
		Connection cn = new OracleConnector().getCn();

		ArrayList data = SearchUserPRTest.searchUser(mailaddress, cn);

		try {
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
		//requestScopeにdataをセット
		reqc.setResult(data);
		//送信先
		resc.setTarget("searchresultPR");

		return resc;
	}
}
