/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.showLikeTweet;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class ShowLikeProfCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String userid  = reqc.getParameter("serialuserid")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

			String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken

			Connection cn = new OracleConnector().getCn();

			//ResponseContext resc = new WebResponseContext();

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(userid+"\\\\\\\\"+sessionToken);
			System.out.println();
			System.out.println();
			System.out.println();

			ArrayList list = new ArrayList();

				list = showLikeTweet.showLikeTweet(sessionToken, userid, cn);

				cn.close();


			resc.setResult(list);

			resc.setTarget("profiles");

		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}

		return resc;
	}

}
