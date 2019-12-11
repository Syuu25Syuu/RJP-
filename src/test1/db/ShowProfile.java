package test1.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import test1.been.MyTweetView_Been;

public class ShowProfile {
	public static ArrayList getShowProfile(String user_id,String sessionToken) {
		ArrayList list = new ArrayList();

		String u_name = GetUsersName.getUserName(user_id);		//ユーザー名

		String u_id = GetUsersId.getUserId(user_id);		//ユーザーID

		LinkedHashMap tweetmap = ViewMy_Tweet.viewMy_Tweet(user_id);	//ユーザーのツイート。シリアルナンバーとコンテンツ

		Iterator iterator = tweetmap.keySet().iterator();
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();
			p.setName(u_name);
			System.out.println("ユーザー名"+u_name+"です");
			p.setId(u_id);
			String tweetID = (String)iterator.next();	//tweet_id
			String valueTweet = (String)tweetmap.get(tweetID);
			String likecounter = CountLikeTweet.countLikeTweet(tweetID);	//そのツイートのいいね数を表示

			String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweetID);	//そのツイートにいいねをしているかの判定
			String checkRT = CheckRTUser.checkRTUser(sessionToken,tweetID);		//そのツイートにＲＴしているかの判定
			String countRT = CountRT.countRT(tweetID);	//そのツイートのＲＴ数を表示

			p.setCheckRT(checkRT);
			p.setCountRT(countRT);
			p.setTweet(valueTweet);
			p.setTweetId(tweetID);
			p.setLikecounter(likecounter);
			p.setChecklike(checklike);
			list.add(p);

		}




		return list;
	}
}
