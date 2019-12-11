package test1.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import test1.been.MyTweetView_Been;

public class ComebackHome {
	public static ArrayList comeBackHome(String sessionToken) {
		ArrayList list = new ArrayList<>();
		TreeMap<String, String[]> tweetTreeMap = new TreeMap<String, String[]>();


		/*フォローしてる人のRTを格納する処理*/
		ArrayList myFollowes = getMyFollowUser.getMyFollowUsers(sessionToken);	//自分がフォローしているユーザーのリストをゲット
		Iterator iterator = myFollowes.iterator();
		while (iterator.hasNext()) {
			String followUser_id = (String) iterator.next();
			System.out.println("21行目"+followUser_id+"です");
			LinkedHashMap followtweetmap = ViewRT_Tweet.viewRT_Tweet(followUser_id);	//自分の全ツイートのTWEETS_SERIALNOを取得

			Iterator followeriterator = followtweetmap.keySet().iterator();	//TWEETS_SERIALNOをキーにする
			while(followeriterator.hasNext()) {
				String tweetID = (String)followeriterator.next();	//tweet_id

				boolean tweetflg = tweetTreeMap.containsKey(tweetID);
				if(tweetflg == false) {
					String user_serialno = GetUserNo_fromTweet.GetUserNo(tweetID);
					System.out.println("31行目"+user_serialno);
					String user_id = GetUsersId.getUserId(user_serialno);
					String user_name = GetUsersName.getUserName(user_serialno);
					//System.out.println("user_nameは"+user_name);

					String valueTweet = GetTweetContent.getTweetContent(tweetID);
					String countLike = CountLikeTweet.countLikeTweet(tweetID);	//そのツイートのいいね数を表示

					String checklike = CheckLikeUser.checkLikeUser(sessionToken,tweetID);	//そのツイートにいいねをしているかの判定

					String checkRT = CheckRTUser.checkRTUser(sessionToken,tweetID);		//そのツイートにＲＴしているかの判定
					String countRT = CountRT.countRT(tweetID);	//そのツイートのＲＴ数を表示
					String RTUser = user_name + "さんがRTしました";

					//Beanにセット

					String[] myTweetsArray = {user_serialno,user_id,user_name,valueTweet,checklike,countLike,checkRT,countRT,RTUser};



					tweetTreeMap.put(tweetID, myTweetsArray);	//自分のツイートをすべて格納

				}
			}

			/*自分のRTを格納*/

			LinkedHashMap myRTtweetmap = ViewRT_Tweet.viewRT_Tweet(sessionToken);	//自分の全ツイートのTWEETS_SERIALNOを取得

			Iterator myRTiterator = myRTtweetmap.keySet().iterator();	//TWEETS_SERIALNOをキーにする
			while(myRTiterator.hasNext()) {
				String tweetID = (String)myRTiterator.next();	//tweet_id

				boolean tweetflg = tweetTreeMap.containsKey(tweetID);
				if(tweetflg == false) {
					String user_serialno = GetUserNo_fromTweet.GetUserNo(tweetID);
					String user_id = GetUsersId.getUserId(user_serialno);
					String user_name = GetUsersName.getUserName(user_serialno);
					//System.out.println("user_nameは"+user_name);

					String valueTweet = (String)myRTtweetmap.get(tweetID); //tweet内容
					String countLike = CountLikeTweet.countLikeTweet(tweetID);	//そのツイートのいいね数を表示

					String checklike = CheckLikeUser.checkLikeUser(sessionToken,tweetID);	//そのツイートにいいねをしているかの判定

					String checkRT = CheckRTUser.checkRTUser(sessionToken,tweetID);		//そのツイートにＲＴしているかの判定
					String countRT = CountRT.countRT(tweetID);	//そのツイートのＲＴ数を表示
					String RTUser = "RT済み";

					//Beanにセット

					String[] myTweetsArray = {user_serialno,user_id,user_name,valueTweet,checklike,countLike,checkRT,countRT,RTUser};



					tweetTreeMap.put(tweetID, myTweetsArray);	//自分のツイートをすべて格納

				}
			}

		}

		/*自分と自分のフォロワーのツイートを表示*/

		LinkedHashMap myTweetmap = ViewMy_All_Tweet.viewMy_Tweet(sessionToken);	//自分の全ツイートのTWEETS_SERIALNOを取得



		Iterator myiterator = myTweetmap.keySet().iterator();	//TWEETS_SERIALNOをキーにする
		while(myiterator.hasNext()) {
			MyTweetView_Been p = new MyTweetView_Been();	//BeanをNew


			String tweetID = (String)myiterator.next();	//tweet_id
			System.out.println("105行目"+tweetID);

			boolean tweetflg = tweetTreeMap.containsKey(tweetID);
			if(tweetflg == false) {

				String user_serialno = GetUserNo_fromTweet.GetUserNo(tweetID);
				String user_id = GetUsersId.getUserId(user_serialno);
				String user_name = GetUsersName.getUserName(user_serialno);
				//System.out.println("user_nameは"+user_name);

				String valueTweet = (String)myTweetmap.get(tweetID); //tweet内容
				String countLike = CountLikeTweet.countLikeTweet(tweetID);	//そのツイートのいいね数を表示

				String checklike = CheckLikeUser.checkLikeUser(sessionToken,tweetID);	//そのツイートにいいねをしているかの判定

				String checkRT = CheckRTUser.checkRTUser(sessionToken,tweetID);		//そのツイートにＲＴしているかの判定
				String countRT = CountRT.countRT(tweetID);	//そのツイートのＲＴ数を表示

				//Beanにセット

				String[] myTweetsArray = {user_serialno,user_id,user_name,valueTweet,checklike,countLike,checkRT,countRT,""};

				tweetTreeMap.put(tweetID, myTweetsArray);	//自分のツイートをすべて格納

			}

		}

		Iterator setIterator = tweetTreeMap.keySet().iterator();

		while(setIterator.hasNext()) {
			String tweetID = (String)setIterator.next();
			System.out.println("137行目"+tweetID);

			String[] beanStrings = (String[])tweetTreeMap.get(tweetID);

			MyTweetView_Been p = new MyTweetView_Been();	//BeanをNew

			p.setSerialuserid(beanStrings[0]);
			p.setId(beanStrings[1]);

			p.setName(beanStrings[2]);

			System.out.println(beanStrings[2]);


			p.setTweet(beanStrings[3]);

			p.setChecklike(beanStrings[4]);
			p.setLikecounter(beanStrings[5]);
			p.setCheckRT(beanStrings[6]);
			p.setCheckRT(beanStrings[7]);

			p.setRtuser(beanStrings[8]);

			list.add(p);

		}


		return list;
	}
}
