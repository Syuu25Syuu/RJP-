/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.showLikeTweet;

public class ShowLikeProfCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) {
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
		try {
			list = showLikeTweet.showLikeTweet(sessionToken, userid, cn);

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		resc.setResult(list);

		resc.setTarget("profiles");

		return resc;
	}

}
