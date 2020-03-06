/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.showLikeTweet;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class ShowProfileLikeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String profUserId  = reqc.getParameter("serialuserid")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

			String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken

			//ResponseContext resc = new WebResponseContext();

			ArrayList list = new ArrayList();
			ArrayList header = new ArrayList();
			Connection cn = new OracleConnector().getCn();


			list = showLikeTweet.showLikeTweet(sessionToken, profUserId, cn);
			//header = GetHeader.getHeader(sessionToken,cn);

			//cn.commit();
			cn.close();


			//通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

			resc.setResult(notifyCount);

			reqc.setResult(list);
			resc.setTarget("profilesLike");

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
