/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.ComebackHome;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class ComebackHomeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();
			//ResponseContext resc = new WebResponseContext();

			String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
			Connection cn = new OracleConnector().getCn();

			ArrayList list = new ArrayList();

			ArrayList header = new ArrayList();

			list = ComebackHome.comeBackHome(sessionToken, cn);

			//header = GetHeader.getHeader(sessionToken,cn);
			cn.close();


		//02/24澤田追加-------------------------------------------------------
		//通知が何件あるか確認
		int showcount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		//--------------------------------------------------------------------

		reqc.setResult(list);
        resc.setResult(showcount);
        resc.setTarget("home");
		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}

}
