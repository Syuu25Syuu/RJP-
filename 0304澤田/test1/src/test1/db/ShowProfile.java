package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import test1.been.MyTweetView_Been;
import test1.json.NotifyJsonFileReader;

public class ShowProfile {
	public static ArrayList showProfile(String sessionToken,String no,Connection cn) {
		ArrayList list = new ArrayList();

		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        String myName = GetUsersName.getUserName(sessionToken, cn);
	        String profName = GetUsersName.getUserName(no, cn);	//プロフィールのユーザー名
	        String profId = GetUsersId.getUserId(no, cn);	//プロフィールのユーザーID
	        String prof = GetProfile.getProfile(no, cn);
	        if(prof == null) {
	        	prof="";
	        }
	        //エスケープ処理
    		prof = prof.replaceAll("&amp;","&amp;amp;");
    		prof = prof.replaceAll("&lt;","&amp;lt;");
    		prof = prof.replaceAll("&gt;","&amp;gt;");
    		prof = prof.replaceAll("&quot;","&amp;quot;");
    		prof = prof.replaceAll("&apos;","&amp;apos;");
    		prof = prof.replaceAll("&nbsp;","&amp;nbsp;");
    		prof = prof.replaceAll("\"","&quot;");
    		prof = prof.replaceAll("'","&apos;");
    		prof = prof.replaceAll("<","&lt;");
    		prof = prof.replaceAll(">","&gt;");
    		prof = prof.replaceAll( "\n","<br>");
	        String checkfollow = CheckFollow.checkFollow(sessionToken, no, cn);
	        System.out.println("checkfollowだよ"+checkfollow);
	        String flgfollowbtn ="followbtn";		//プロフィールのユーザーと自分が同一化判定する
	        String countFollowers = CountFollowers.countFollowers(no, cn);

        	String countFollows = CountFollows.countFollows(no, cn);

	        /*1月23日追加*/
	        String profImage = GetUSERS_PROF_IMAGE.getProfile(no, cn);

	      //通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

	        if(sessionToken.equals(no)) {
	        	flgfollowbtn = "changeProf";
	        }

	        //SQL文を変数に格納する
	        String sql="select TWEETS.TWEETS_DATE,rt.RT_TIME,USERS_SERIALNO,USERS_NAME,tweets.TWEETS_SERIALNO,tweets.USERS_NO,tweets.TWEETS_CONTENT,TWEETS.REPLY_TWEET,TWEETS.TWEETS_IMAGE from users "+
	        		"RIGHT outer JOIN RT  rt on  rt.RT_USER = USERS.USERS_SERIALNO "+
	        		"RIGHT OUTER JOIN TWEETS on  rt.RT_TWEET = TWEETS.TWEETS_SERIALNO "+
	        		"where users_serialno = '"+no+"' "+
	        		"or tweets.USERS_NO = '"+no+"'" +
	        		"or rt.RT_USER = '"+no+"' "+
	        		"order by rt.RT_TIME desc";
	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

            TreeMap<Date,String[]> map = new TreeMap<Date,String[]>(new Comparator<Date>() {
    			public int compare(String k1, String k2) {
    				return k2.compareTo(k1) ;
    			}

				@Override
				public int compare(Date o1, Date o2) {
					// TODO 自動生成されたメソッド・スタブ
					return o2.compareTo(o1);
				}
    		});

            LinkedHashMap <String,String[]> tweetMap = new LinkedHashMap <String,String[]>();

	        while(rs.next()){


	        	String tweetTimeString = rs.getString(1);



	        	String rtTimeString = rs.getString(2);

	        	//System.out.println(rtTimeString);

	        	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	            Date tweetDate = sdFormat.parse(tweetTimeString);

	            tweetTimeString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tweetDate);


	            String[] aStrings = new String[11];

	            String users_Serialno = rs.getString(6);	//そのツイートをした人のユーザーシリアルナンバー
	            String tweet_Serialno = rs.getString(5);

	            aStrings[0] = tweet_Serialno;	//ツイートシリアルナンバー
	            aStrings[1] = users_Serialno;	//そのツイートをした人のユーザーシリアルナンバー
	            aStrings[2] = rs.getString(7);	//ツイート本文
	            aStrings[3] = CheckLikeUser.checkLikeUser(sessionToken, tweet_Serialno,cn);

	            aStrings[4] = CheckRTUser.checkRTUser(sessionToken, tweet_Serialno, cn);

	            String tweetUserName = GetUsersName.getUserName(users_Serialno, cn);

	            aStrings[5] = tweetUserName;

	            aStrings[6] = GetUsersId.getUserId(users_Serialno, cn);	//そのツイートをした人のuser_id

	            aStrings[7] = tweetTimeString;

	            aStrings[10] = rs.getString("TWEETS_IMAGE");


	            if(rtTimeString!=null) {
	            	Date rtDate = sdFormat.parse(rtTimeString);

	            	//System.out.println("ここは生きていますか？"+rtUser);	//生きてた
	            	aStrings[8] = profName;

		            map.put(rtDate,aStrings);


	            }else {
	            	map.put(tweetDate,aStrings);
	            }

	         }

	        if(map.isEmpty()==true) {
	        	System.out.println("プロフィール画面は終焉を迎えました");
	        	MyTweetView_Been bean = new MyTweetView_Been();



	        	bean.setSerialuserid(no);

	        	bean.setProfUserName(profName);

	        	bean.setProfUserId(profId);

	        	bean.setCountFollowers(countFollowers);


	        	bean.setCountFollows(countFollows);

	        	bean.setCheckFollow(checkfollow);

	        	bean.setProfile(prof);

	        	bean.setFollowbtn(flgfollowbtn);

	        	bean.setProfImage(profImage);
	        	//notify
	        	bean.setNotifyCount(notifyCount);

	        	list.add(bean);


	        }else {


		        for (Date key : map.keySet()) {
					String tweetID = (String)map.get(key)[0];
					//System.out.println("ここの"+tweetID);

					String[] newlistStrings = map.get(key);

					if(tweetMap.containsKey(tweetID)) {		//重複したらmapに含めない

					}else {
						tweetMap.put(tweetID, newlistStrings);
					}

				}

		        for(String key:tweetMap.keySet()) {

		        	MyTweetView_Been bean = new MyTweetView_Been();

		        	String userName = (String)tweetMap.get(key)[5];

		        	System.out.println("こんにちはuserNameです"+userName);

		        	String userId = (String)tweetMap.get(key)[6];

		        	String tweetSerialNo = key;

		        	String tweetValue = (String)tweetMap.get(key)[2];
		        	//エスケープ処理
		    		tweetValue = tweetValue.replaceAll("&amp;","&amp;amp;");
		    		tweetValue = tweetValue.replaceAll("&lt;","&amp;lt;");
		    		tweetValue = tweetValue.replaceAll("&gt;","&amp;gt;");
		    		tweetValue = tweetValue.replaceAll("&quot;","&amp;quot;");
		    		tweetValue = tweetValue.replaceAll("&apos;","&amp;apos;");
		    		tweetValue = tweetValue.replaceAll("&nbsp;","&amp;nbsp;");
		    		tweetValue = tweetValue.replaceAll("\"","&quot;");
		    		tweetValue = tweetValue.replaceAll("'","&apos;");
		    		tweetValue = tweetValue.replaceAll("<","&lt;");
		    		tweetValue = tweetValue.replaceAll(">","&gt;");
		    		tweetValue = tweetValue.replaceAll( "\n","<br>");

		        	String userSerialNo = (String)tweetMap.get(key)[1];

		        	String checkLike = (String)tweetMap.get(key)[3];

		        	System.out.println("checklikeだよ"+checkLike);

		        	String checkRT = (String)tweetMap.get(key)[4];

		        	String tweetDate = (String)tweetMap.get(key)[7];

		        	String rtUserName = (String)tweetMap.get(key)[8];

		           	String tweetImg = (String)tweetMap.get(key)[10];

		           	String imageNone="";

		        	//System.out.println("みてね"+tweetImg);

		           	if(tweetImg == null) {
		        		imageNone ="NonImage";
		        	}



		        	if(rtUserName != null) {
		        		if(rtUserName.equals(myName)){
		        			rtUserName = "リツイート済み";
		        		}else {
		        			rtUserName += "さんがRTしました";
		        		}

		        	}

		        	String icon = GetUSERS_PROF_IMAGE.getProfile(userSerialNo, cn);


		        	/*いいね数・RT数・リプライ数を表示させる*/
		        	String likeCount =CountLikeTweet.countLikeTweet(tweetSerialNo, cn);
		        	String RTCount = CountRT.countRT(tweetSerialNo, cn);
		        	String replyCount = CountReply.countReply(tweetSerialNo, cn);
		        	String mytweetCheck = CheckMyTweet.checkMyTweet(sessionToken, tweetSerialNo, cn);
		        	bean.setMytweetCheck(mytweetCheck);


		        	bean.setName(userName);
		        	bean.setId(userId);
		        	bean.setTweetId(tweetSerialNo);
		        	bean.setTweet(tweetValue);
		        	bean.setChecklike(checkLike);
		        	bean.setCheckRT(checkRT);
		        	bean.setSerialuserid(userSerialNo);
		        	bean.setRtuser(rtUserName);
		        	bean.setProfUserName(profName);
		        	bean.setCountFollowers(countFollowers);
		        	bean.setCountFollows(countFollows);
		        	bean.setProfUserId(profId);
		        	bean.setProfile(prof);
		        	bean.setCheckFollow(checkfollow);
		        	bean.setFollowbtn(flgfollowbtn);
		        	bean.setProfImage(profImage);
		        	bean.setTweetdate(tweetDate);
		        	bean.setIcon(icon);
		        	bean.setTweetImg(tweetImg);
		        	bean.setTweetImageNone(imageNone);

		        	bean.setCountRT(RTCount);
		        	bean.setCountLike(likeCount);
		        	bean.setCountReply(replyCount);

		        	bean.setTabCheck_tweet("checked");
		        	bean.setTabCheck_image("");
		        	bean.setTabCheck_like("");
		        	bean.setProfChangeId(no);

		        	bean.setNotifyCount(notifyCount);


		        	list.add(bean);

		        }

	        }



	        }catch(SQLException e){
	        	e.printStackTrace();
	        }catch (ParseException e) {
				// TODO: handle exception
			}catch (Exception e) {
				e.printStackTrace();
			}


		return list;

	}
}
