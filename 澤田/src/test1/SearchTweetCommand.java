package test1;

import java.util.ArrayList;

import test1.db.SerchTweet;

public class SearchTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  word = reqc.getParameter("tweetWord")[0];

		String  s_userid = reqc.getParameter("user_session")[0];

		ResponseContext resc = new WebResponseContext();


		ArrayList list = SerchTweet.getSerchTweet(word,s_userid);

		resc.setResult(list);

		resc.setTarget("searchtweet");

		return resc;
	}

}
