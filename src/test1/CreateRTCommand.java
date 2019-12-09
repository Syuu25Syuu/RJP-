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
import test1.db.CreateRT;
import test1.db.DeleteRT;
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class CreateRTCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();


		String  sessionToken = reqc.getParameter("user_session")[0];

		String  tweet_Id = reqc.getParameter("tweet_id")[0];

		String checkRT = CheckRTUser.checkRTUser(sessionToken, tweet_Id);		//そのツイートに対してRTをしていた場合「RTをとりけす」という文字列が帰ってくる

		if(checkRT.equals("RTをとりけす")) {
			DeleteRT.deleteRT(tweet_Id, sessionToken);	//RTを取り消す

		}else {
			CreateRT.createRT(sessionToken, tweet_Id);	//RTをする
		}

		//HOMEにツイートを表示させる処理

		HashMap map = ViewMyName_And_ID.viewMyName_And_ID(sessionToken);

		String id =(String) map.get("id");
		String user_name =(String) map.get("name");

		LinkedHashMap tweetmap = ViewMy_Tweet.viewMy_Tweet(sessionToken);


		ArrayList list = new ArrayList<>();

		Iterator iterator = tweetmap.keySet().iterator();
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();
			p.setName(user_name);
			p.setId(id);
			String keyID = (String)iterator.next();	//tweet_id
			String valueTweet = (String)tweetmap.get(keyID);

			String likecounter = CountLikeTweet.countLikeTweet(keyID);	//そのツイートのいいね数を表示

			String checklike = CheckLikeUser.checkLikeUser(sessionToken, keyID);	//そのツイートにいいねをしているかの判定

			checkRT = CheckRTUser.checkRTUser(sessionToken, keyID);		//そのツイートにＲＴしているかの判定
			String countRT = CountRT.countRT(keyID);	//そのツイートのＲＴ数を表示



			p.setCountRT(countRT);
			p.setCheckRT(checkRT);

			p.setTweet(valueTweet);
			p.setTweetId(keyID);
			p.setLikecounter(likecounter);
			p.setChecklike(checklike);
			p.setSerialuserid(keyID);
			list.add(p);


		}



        resc.setResult(list);
        resc.setTarget("home");
		return resc;
	}

}
