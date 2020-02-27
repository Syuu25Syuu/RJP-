/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.ShowProfile;

public class ShowProfileCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String profUserId  = reqc.getParameter("user_id")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

		String sessionToken = reqc.getParameter("user_session")[0];	//ログインしてるユーザーのsessionToken

		ResponseContext resc = new WebResponseContext();

		ArrayList list = new ArrayList();
		ArrayList header = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = ShowProfile.showProfile(sessionToken, profUserId, cn);

		cn.commit();
		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}


		reqc.setResult(list);
		resc.setTarget("profiles");

		return resc;
	}

}
