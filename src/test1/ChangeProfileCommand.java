/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.GetUser_and_Tweet;
import test1.db.OracleConnector;
import test1.db.UpdateName;
import test1.db.UpdateProf;

public class ChangeProfileCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		String name = reqc.getParameter("user_name")[0];
		String  prof = reqc.getParameter("contents")[0];

		Connection cn = new OracleConnector().getCn();

		ArrayList list = new ArrayList();

		try {
			UpdateName.updateName(sessionToken, name, cn);
			UpdateProf.updateName(sessionToken, prof, cn);
			list = GetUser_and_Tweet.getUser_and_Tweet(sessionToken, sessionToken, cn);

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

        resc.setResult(list);
        resc.setTarget("profiles");


		return resc;
	}

}
