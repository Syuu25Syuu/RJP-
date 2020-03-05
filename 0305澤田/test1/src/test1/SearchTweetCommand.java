/*検索したい文字列が含まれるツイートを検索し表示させるためのコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.SerchTweet;

public class SearchTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();
		//ResponseContext resc = new WebResponseContext();

		String  word = reqc.getParameter("tweetWord")[0];	//検索対象文字列

		String  sessionToken = reqc.getParameter("user_session")[0];

		Connection cn = new OracleConnector().getCn();
		ArrayList list = new ArrayList<>();

		try {
			cn.setAutoCommit(false);
			list = SerchTweet.getSerchTweet(word,sessionToken,cn);


			cn.commit();
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		resc.setResult(list);

		resc.setTarget("searchtweet");

		return resc;
	}

}
