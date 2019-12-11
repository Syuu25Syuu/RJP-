package test1.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import test1.been.MyTweetView_Been;

public class tukawanComebackHome {
	public static ArrayList comeBackHome(String sessionToken) {
		ArrayList list = new ArrayList<>();

		LinkedHashMap tweetmap = ViewMy_All_Tweet.viewMy_Tweet(sessionToken);	//自分の全ツイートのTWEETS_SERIALNOを取得


		Iterator iterator = tweetmap.keySet().iterator();	//TWEETS_SERIALNOをキーにする
		while(iterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();	//BeanをNew


			String tweetID = (String)iterator.next();	//tweet_id


			String user_serialno = GetUserNo_fromTweet.GetUserNo(tweetID);
			String user_id = GetUsersId.getUserId(user_serialno);
			String user_name = GetUsersName.getUserName(user_serialno);
			//System.out.println("user_nameは"+user_name);

			String valueTweet = (String)tweetmap.get(tweetID); //tweet内容
			String likecounter = CountLikeTweet.countLikeTweet(tweetID);	//そのツイートのいいね数を表示

			String checklike = CheckLikeUser.checkLikeUser(sessionToken,tweetID);	//そのツイートにいいねをしているかの判定

			String checkRT = CheckRTUser.checkRTUser(sessionToken,tweetID);		//そのツイートにＲＴしているかの判定
			String countRT = CountRT.countRT(tweetID);	//そのツイートのＲＴ数を表示

			//Beanにセット

			p.setName(user_name);
			p.setId(user_id);
			p.setCountRT(countRT);
			p.setCheckRT(checkRT);


			p.setSerialuserid(user_serialno);
			p.setTweet(valueTweet);
			p.setTweetId(tweetID);
			p.setLikecounter(likecounter);
			p.setChecklike(checklike);
			list.add(p);

		}

		return list;
	}
}
