package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import test1.been.MyTweetView_Been;

public class ComebackHome {
	public static ArrayList comeBackHome(String sessionToken,Connection cn) {
		ArrayList list = new ArrayList<>();


		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        String myName = GetUsersName.getUserName(sessionToken, cn);

	        System.out.println("myNameは"+myName);


	        //SQL文を変数に格納する
	        String sql="select TWEETS.TWEETS_DATE,rt.RT_TIME,USERS_SERIALNO,USERS_NAME,tweets.TWEETS_SERIALNO,tweets.USERS_NO,tweets.TWEETS_CONTENT from users "+
	        		"RIGHT outer JOIN RT  rt on  rt.RT_USER = USERS.USERS_SERIALNO "+
	        		"RIGHT OUTER JOIN TWEETS on  rt.RT_TWEET = TWEETS.TWEETS_SERIALNO "+
	        		"where users_serialno = '"+sessionToken+"' "+
	        		"or (USERS_NO) IN  (select FOLLOWED_NO from follows where users_no = '"+sessionToken+"') "+
	        		"or tweets.USERS_NO = '"+sessionToken+"'" +
	        		"or rt.RT_USER = '"+sessionToken+"' "+
	        		"or (rt.RT_USER)IN (select FOLLOWED_NO from follows where users_no = '"+sessionToken+"') "+
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
	        	MyTweetView_Been b = new MyTweetView_Been();

	        	String tweetTimeString = rs.getString(1);



	        	String rtTimeString = rs.getString(2);

	        	System.out.println(rtTimeString);

	        	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	            Date tweetDate = sdFormat.parse(tweetTimeString);




	            String[] aStrings = new String[9];

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





	            if(rtTimeString!=null) {
	            	Date rtDate = sdFormat.parse(rtTimeString);
	            	String rtUser = rs.getString(4);	//tweetをRTしたユーザー名
	            	//System.out.println("ここは生きていますか？"+rtUser);	//生きてた
	            	aStrings[7] = rtUser;
	            	if (aStrings[1].equals(sessionToken)) {

	            		if(aStrings[7].equals(myName)) {


	    	            	map.put(rtDate,aStrings);
	    	            	System.out.println("ここは生きていますか？"+rtUser);
	            		}else {

	            		}
					}else {
		            	aStrings[7] = rtUser;

		            	map.put(rtDate,aStrings);
					}

	            }else {
	            	map.put(tweetDate,aStrings);
	            }

	         }

	        for (Date key : map.keySet()) {
				String tweetID = (String)map.get(key)[0];
				System.out.println("ここの"+tweetID);

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

	        	String userSerialNo = (String)tweetMap.get(key)[1];

	        	String checkLike = (String)tweetMap.get(key)[3];

	        	System.out.println("checklikeだよ"+checkLike);

	        	String checkRT = (String)tweetMap.get(key)[4];

	        	String rtUserName = (String)tweetMap.get(key)[7];


	        	if(rtUserName != null) {
	        		if(rtUserName.equals(myName)){
	        			rtUserName = "リツイート済み";
	        		}else {
	        			rtUserName += "さんがRTしました";
	        		}

	        	}

	        	bean.setName(userName);
	        	bean.setId(userId);
	        	bean.setTweetId(tweetSerialNo);
	        	bean.setTweet(tweetValue);
	        	bean.setChecklike(checkLike);
	        	bean.setCheckRT(checkRT);
	        	bean.setSerialuserid(userSerialNo);
	        	bean.setRtuser(rtUserName);
	        	list.add(bean);

	        }




	        }catch(SQLException e){
	        	e.printStackTrace();
	        }catch (ParseException e) {
				// TODO: handle exception
			}


		return list;

	}
}