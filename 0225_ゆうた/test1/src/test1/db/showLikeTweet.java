package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class showLikeTweet {
	public static ArrayList showLikeTweet(String sessionToken,String profUser,Connection cn) {
		ArrayList list = new ArrayList();



		try{


        //自動コミットをOFFにする
        //cn.setAutoCommit(false);

       // System.out.println("接続完了");

        //SQL文を変数に格納する

        String sql="select USERS_SERIALNO,USERS_NAME,Users_id,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT,tweets.TWEETS_DATE,tweets.TWEETS_IMAGE from USERS join tweets on USERS_SERIALNO = tweets.USERS_NO JOIN LIKES on LIKES.LIKES_TWEET = tweets.TWEETS_SERIALNO where (tweets.TWEETS_SERIALNO) IN (select likes_tweet from likes  where Likes_User = '"+profUser+"')order by LIKES.LIKES_TIME desc"; //そのユーザーがいいねしたツイートのナンバーを取得

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        boolean flg = true;

        while(rs.next()){

    		System.out.println("いる");
    		flg = false;


        	MyTweetView_Been b = new MyTweetView_Been();
        	String users_serialno = rs.getString(1);
        	String users_name = rs.getString(2);
        	String users_id = rs.getString(3);
        	String tweets_no = rs.getString(4);
        	String tweets_content = rs.getString(5);
        	String tweetDate = rs.getString(6);
        	String tweetImg = rs.getString(7);

        	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweets_no, cn);
        	String checkRT = CheckRTUser.checkRTUser(sessionToken, tweets_no, cn);
        	String icon = GetUSERS_PROF_IMAGE.getProfile(users_serialno, cn);

        	String imageNone="";

        	//System.out.println("みてね"+tweetImg);

        	if(tweetImg == null) {
        		imageNone ="NonImage";
        	}

        	b.setName(users_name);
        	b.setId(users_id);
         	b.setSerialuserid(users_serialno);
        	b.setTweetId(tweets_no);
        	b.setTweet(tweets_content);
        	b.setChecklike(checklike);
        	b.setCheckRT(checkRT);
        	//b.setProfImage(icon);
        	b.setTweetImg(tweetImg);
        	b.setTweetdate(tweetDate);
        	b.setIcon(icon);
        	b.setTweetImageNone(imageNone);

        	list.add(b);


         }




        }catch(SQLException e){
        	e.printStackTrace();

        }catch(Exception e) {
        	e.printStackTrace();
        }



		return list;
	}
}
