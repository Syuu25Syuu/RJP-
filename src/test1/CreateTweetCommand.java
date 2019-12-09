package test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CheckRTUser;
import test1.db.CountLikeTweet;
import test1.db.CountRT;
import test1.db.CreateTweet;
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class CreateTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  s_userid = reqc.getParameter("user_session")[0];


		System.out.println("useridは"+s_userid+"だよ");

		String  tweet = reqc.getParameter("contents")[0];


		CreateTweet.createTweet(s_userid,tweet); //return password

		/*以下HOMEにツイートを表示させる処理*/

		HashMap map = ViewMyName_And_ID.viewMyName_And_ID(s_userid);

		String id =(String) map.get("id");
		String user_name =(String) map.get("name");

		LinkedHashMap tweetmap = ViewMy_Tweet.viewMy_Tweet(s_userid);

		//System.out.println("idさんは"+id+"だよ！！！");



		ArrayList list = new ArrayList<>();

		Iterator iterator = tweetmap.keySet().iterator();
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();
			p.setName(user_name);
			p.setId(id);
			String keyID = (String)iterator.next();	//tweet_id
			String valueTweet = (String)tweetmap.get(keyID);
			String likecounter = CountLikeTweet.countLikeTweet(keyID);	//そのツイートのいいね数を表示

			String checklike = CheckLikeUser.checkLikeUser(s_userid, keyID);	//そのツイートにいいねをしているかの判定

			String checkRT = CheckRTUser.checkRTUser(s_userid, keyID);		//そのツイートにＲＴしているかの判定
			String countRT = CountRT.countRT(keyID);	//そのツイートのＲＴ数を表示



			p.setCountRT(countRT);
			p.setCheckRT(checkRT);

			p.setSerialuserid(s_userid);
			p.setTweet(valueTweet);
			p.setTweetId(keyID);
			p.setLikecounter(likecounter);
			p.setChecklike(checklike);
			list.add(p);

		}



        resc.setResult(list);
        resc.setTarget("home");


		return resc;
	}

}
