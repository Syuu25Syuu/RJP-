package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.CheckLikeUser;

public class SerchTweet {
	public static ArrayList getSerchTweet(String word,String sessionToken,Connection cn) {
		ArrayList datalist=new ArrayList();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする


	        //System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select USERS_SERIALNO,USERS_NAME,Users_id,USERS_PROF_IMAGE,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT from USERS " +
	        		"join tweets on USERS_SERIALNO = tweets.USERS_NO " +
	        		"where TWEETS_CONTENT LIKE '%"+word+"%' "+
	        		"order by tweets.TWEETS_DATE desc";




	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){

	        	String user_sirial_no = rs.getString(1);
	        	String users_name = rs.getString(2);
	        	String users_id = rs.getString(3);
	        	String users_img_path = rs.getString(4);
	        	String tweets_no = rs.getString(5);
	        	String tweets_content = rs.getString(6);

	        	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweets_no, cn);

	        	System.out.println(checklike);


	        	ArrayList data = new ArrayList();
	        	/*b.setName(users_name);
	        	b.setId(users_id);
	        	b.setSerialuserid(user_sirial_no);
	        	b.setTweetId(tweets_no);
	        	b.setTweet(tweets_content);
	        	b.setChecklike(checklike);*/
	        	data.add(user_sirial_no);
	        	data.add(users_name);
	        	data.add(users_id);
	        	data.add(users_img_path);
	        	data.add(tweets_no);
	        	data.add(tweets_content);
	        	data.add(checklike);

	        	datalist.add(data);

	         }

	        //トランザクションをコミットする


        }catch(SQLException e){
        	e.printStackTrace();
        }

        return datalist;

	}
}
