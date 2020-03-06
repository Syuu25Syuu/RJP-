/*検索したい文字列が含まれるツイートを検索し表示させるためのコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.SerchTweet;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class SearchTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();
			//ResponseContext resc = new WebResponseContext();

			String  word = reqc.getParameter("tweetWord")[0];	//検索対象文字列

			String  sessionToken = reqc.getParameter("user_session")[0];

			Connection cn = new OracleConnector().getCn();
			ArrayList list = new ArrayList<>();

			cn.setAutoCommit(false);
			list = SerchTweet.getSerchTweet(word,sessionToken,cn);

			cn.commit();
			cn.close();


			resc.setResult(list);

			resc.setTarget("searchtweet");

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}

		return resc;
	}

}
