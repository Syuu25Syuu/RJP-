package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;
import test1.exception.IntegrationException;

public class ViewMyLike {
	public static ArrayList viewMyLike(String sessionToken,Connection cn) throws IntegrationException{
		ArrayList list = new ArrayList();



		try{



        //自動コミットをOFFにする
        //cn.setAutoCommit(false);

       // System.out.println("接続完了");

        //SQL文を変数に格納する

        String sql="select USERS_SERIALNO,USERS_NAME,Users_id,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT from USERS join tweets on USERS_SERIALNO = tweets.USERS_NO JOIN LIKES on LIKES.LIKES_TWEET = tweets.TWEETS_SERIALNO where (tweets.TWEETS_SERIALNO) IN (select likes_tweet from likes  where Likes_User = '"+sessionToken+"')order by LIKES.LIKES_TIME desc"; //自分がいいねしたツイートのナンバーを取得

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){

        	MyTweetView_Been b = new MyTweetView_Been();
        	String users_serialno = rs.getString(1);
        	String users_name = rs.getString(2);
        	String users_id = rs.getString(3);
        	String tweets_no = rs.getString(4);
        	String tweets_content = rs.getString(5);
        	//エスケープ処理
    		tweets_content = tweets_content.replaceAll("&amp;","&amp;amp;");
    		tweets_content = tweets_content.replaceAll("&lt;","&amp;lt;");
    		tweets_content = tweets_content.replaceAll("&gt;","&amp;gt;");
    		tweets_content = tweets_content.replaceAll("&quot;","&amp;quot;");
    		tweets_content = tweets_content.replaceAll("&apos;","&amp;apos;");
    		tweets_content = tweets_content.replaceAll("&nbsp;","&amp;nbsp;");
    		tweets_content = tweets_content.replaceAll("\"","&quot;");
    		tweets_content = tweets_content.replaceAll("'","&apos;");
    		tweets_content = tweets_content.replaceAll("<","&lt;");
    		tweets_content = tweets_content.replaceAll(">","&gt;");
    		tweets_content = tweets_content.replaceAll("\n","<br>");

        	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweets_no, cn);
        	String checkRT = CheckRTUser.checkRTUser(sessionToken, tweets_no, cn);


        	b.setName(users_name);
        	b.setId(users_id);
        	b.setSerialuserid(users_serialno);
        	b.setTweetId(tweets_no);
        	b.setTweet(tweets_content);
        	b.setChecklike(checklike);
        	b.setCheckRT(checkRT);
        	list.add(b);


         }



        }catch(SQLException e){
        	e.printStackTrace();
        	throw new IntegrationException(e);

        }catch(Exception e) {
        	e.printStackTrace();
        }



		return list;
	}
}
