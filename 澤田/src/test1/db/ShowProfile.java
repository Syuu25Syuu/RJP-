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
			String keyID = (String)iterator.next();	//tweet_id
			String valueTweet = (String)tweetmap.get(keyID);
			String likecounter = CountLikeTweet.countLikeTweet(keyID);	//そのツイートのいいね数を表示

			String checklike = CheckLikeUser.checkLikeUser(sessionToken, keyID);	//そのツイートにいいねをしているかの判定

			p.setTweet(valueTweet);
			p.setTweetId(keyID);
			p.setLikecounter(likecounter);
			p.setChecklike(checklike);
			list.add(p);

		}




		return list;
	}
}
