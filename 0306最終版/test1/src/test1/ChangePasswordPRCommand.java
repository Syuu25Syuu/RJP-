package test1;

import java.sql.Connection;

import test1.db.ChangePasswordPRTest;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 パスワードの変更を行う
 */
public class ChangePasswordPRCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			//ユーザーIDはrequestScopeで前のページから引き継がれている
			String userid=reqc.getParameter("userid")[0];
			//入力されたパスワード
			String password=reqc.getParameter("password")[0];

			//ResponseContext resc = new WebResponseContext();

			//ーーーーーーーーーー----DB関係ー-----ーーーーーーーーーー
			Connection cn = new OracleConnector().getCn();
			//パスワードの変更
			ChangePasswordPRTest.changePassword(userid, password, cn);
			//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
			//requestScopeにユーザーIDをセットする
			reqc.setResult(userid);
			//送信先
			resc.setTarget("passwordconfirmationPR");
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}
}
