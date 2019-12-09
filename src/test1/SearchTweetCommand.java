/*検索したい文字列が含まれるツイートを検索し表示させるためのコマンド*/

package test1;

import java.util.ArrayList;

import test1.db.SerchTweet;

public class SearchTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  word = reqc.getParameter("tweetWord")[0];	//検索対象文字列

		String  sessionToken = reqc.getParameter("user_session")[0];


		ArrayList list = SerchTweet.getSerchTweet(word,sessionToken);

		resc.setResult(list);

		resc.setTarget("searchtweet");

		return resc;
	}

}
