package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.ConfirmationPRTest;
import test1.db.OracleConnector;
/*
 パスワードリマインダーで使うクラス
 ユーザに前のページで選んだアカウントがあっているか
 確認してもらうために使う
 */
public class ConfirmationPRCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  userid = reqc.getParameter("userid")[0];

		ResponseContext resc = new WebResponseContext();
		//ーーーーーーーーーー----DB関係ー-----ーーーーーーーーーー
		Connection cn = new OracleConnector().getCn();

		ArrayList data = ConfirmationPRTest.getUserData(userid, cn);

		try {
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
		reqc.setResult(data);

		resc.setTarget("confirmationPR");

		return resc;
	}
}
