package test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.LikeTweet;
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class LikeTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();

		String  tweet_id = reqc.getParameter("tweet_id")[0];

		String  s_userid = reqc.getParameter("user_session")[0];

		String flgString = CheckLikeUser.checkLikeUser(s_userid);
		if(flgString != "") {
			resc.setTarget("home");
			return resc;
		}

		LikeTweet.likeTweet(tweet_id, s_userid);
		HashMap map = ViewMyName_And_ID.viewMyName_And_ID(s_userid);

		String id =(String) map.get("id");
		String user_name =(String) map.get("name");

		LinkedHashMap tweetmap = ViewMy_Tweet.viewMy_Tweet(s_userid);

		System.out.println("idさんは"+id+"だよ！！！");






		ArrayList list = new ArrayList<>();

		Iterator iterator = tweetmap.keySet().iterator();
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();
			p.setName(user_name);
			p.setId(id);
			String keyID = (String)iterator.next();
			String valueTweet = (String)tweetmap.get(keyID);
			p.setTweet(valueTweet);
			p.setTweetId(keyID);
			System.out.println("KEYIDは"+keyID);
			System.out.println("ツイートは"+valueTweet);
			list.add(p);

		}



        resc.setResult(list);
        resc.setTarget("home");
		return resc;
	}

}
