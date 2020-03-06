package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.ConfirmationPRTest;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 ユーザに前のページで選んだアカウントがあっているか
 確認してもらうために使う
 */
public class ConfirmationPRCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  userid = reqc.getParameter("userid")[0];

			//ResponseContext resc = new WebResponseContext();
			//ーーーーーーーーーー----DB関係ー-----ーーーーーーーーーー
			Connection cn = new OracleConnector().getCn();

			ArrayList data = ConfirmationPRTest.getUserData(userid, cn);

			cn.close();

			//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
			reqc.setResult(data);

			resc.setTarget("confirmationPR");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}
}
