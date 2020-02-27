/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.GetHeader;
import test1.db.OracleConnector;
import test1.db.showLikeTweet;

public class ShowProfileLikeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String profUserId  = reqc.getParameter("serialuserid")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

		String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken

		ResponseContext resc = new WebResponseContext();

		ArrayList list = new ArrayList();
		ArrayList header = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = showLikeTweet.showLikeTweet(sessionToken, profUserId, cn);
		header = GetHeader.getHeader(sessionToken,cn);

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


		resc.setResult(list);
		reqc.setResult(header);
		resc.setTarget("profilesLike");

		return resc;
	}

}
