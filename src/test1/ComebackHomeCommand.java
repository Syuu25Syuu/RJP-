/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.ComebackHome;
import test1.db.OracleConnector;

public class ComebackHomeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		Connection cn = new OracleConnector().getCn();

		ArrayList list = new ArrayList();

		try {
			list = ComebackHome.comeBackHome(sessionToken, cn);

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

        resc.setResult(list);
        resc.setTarget("home");


		return resc;
	}

}
