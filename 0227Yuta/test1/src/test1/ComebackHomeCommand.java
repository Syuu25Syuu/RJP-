/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.ComebackHome;
import test1.db.GetHeader;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileReader;

public class ComebackHomeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		Connection cn = new OracleConnector().getCn();

		ArrayList list = new ArrayList();

		ArrayList header = new ArrayList();

		try {
			list = ComebackHome.comeBackHome(sessionToken, cn);

			header = GetHeader.getHeader(sessionToken,cn);


			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		//02/24澤田追加-------------------------------------------------------
		//通知が何件あるか確認
		int showcount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		header.add(showcount);
		//--------------------------------------------------------------------

		reqc.setResult(header);
        resc.setResult(list);
        resc.setTarget("home");


		return resc;
	}

}
