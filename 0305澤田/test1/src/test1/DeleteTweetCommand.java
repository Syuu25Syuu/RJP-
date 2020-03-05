/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.DeleteLike;
import test1.db.DeleteRT;
import test1.db.DeleteTweet;
import test1.db.OracleConnector;

public class DeleteTweetCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String  tweetID = reqc.getParameter("tweetNo")[0];

		//ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		String icon="";

		try {
			//follow()の引数に取得したパラメータを入れる
			//取得したパラメータをDBに格納するメソッド(Follows表に)
			DeleteRT.alldDleteRT(tweetID);
			DeleteLike.allDeleteLike(tweetID);
			DeleteTweet.deleteTweet(tweetID);
		}catch(Exception e) {
			e.printStackTrace();
		}
		resc.setTarget("");
		return resc;
	}


}
