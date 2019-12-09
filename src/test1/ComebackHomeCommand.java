/* ホームに戻るときに使われるコマンド */

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
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class ComebackHomeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  s_userid = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得

		HashMap map = ViewMyName_And_ID.viewMyName_And_ID(s_userid);	//USERS_SERIALNOを使用してUSERS_ID、USERS_NAMEが入ったHashMapを取得

		String id =(String) map.get("id");		//USERS_IDをStringで取得
		String user_name =(String) map.get("name");		//USERS_NAMEを取得

		LinkedHashMap tweetmap = ViewMy_Tweet.viewMy_Tweet(s_userid);	//自分の全ツイートのTWEETS_SERIALNOを取得






		ArrayList list = new ArrayList<>();

		Iterator iterator = tweetmap.keySet().iterator();	//TWEETS_SERIALNOをキーにする
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();	//BeanをNew
			p.setName(user_name);	//自分の
			p.setId(id);
			String keyID = (String)iterator.next();	//tweet_id
			String valueTweet = (String)tweetmap.get(keyID); //tweet内容
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
