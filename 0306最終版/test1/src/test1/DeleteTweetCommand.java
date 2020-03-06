/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.DeleteLike;
import test1.db.DeleteRT;
import test1.db.DeleteTweet;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class DeleteTweetCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  tweetID = reqc.getParameter("tweetNo")[0];

			//ResponseContext resc = new WebResponseContext();

			Connection cn = new OracleConnector().getCn();

			String icon="";

			//follow()の引数に取得したパラメータを入れる
			//取得したパラメータをDBに格納するメソッド(Follows表に)
			DeleteRT.alldDleteRT(tweetID);
			DeleteLike.allDeleteLike(tweetID);
			DeleteTweet.deleteTweet(tweetID);

			resc.setTarget("");
		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}
		return resc;
	}


}
