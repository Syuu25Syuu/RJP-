package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class SerchTweet {
	public static ArrayList getSerchTweet(String word,String sessionToken) {
		ArrayList<MyTweetView_Been> data=new ArrayList<MyTweetView_Been>();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select TWEETS_SERIALNO,USERS_NO,TWEETS_CONTENT from tweets where TWEETS_CONTENT LIKE '%"+word+"%'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String t_no = rs.getString(1);
	        	String u_no = rs.getString(2);
	        	String tweet = rs.getString(3);

	        	String userName = GetUsersName.getUserName(u_no);

	        	String userId = GetUsersId.getUserId(u_no);

	        	String likeCheck = CheckLikeUser.checkLikeUser(sessionToken, t_no);

	        	String likeCount = CountLikeTweet.countLikeTweet(t_no);

	        	MyTweetView_Been b = new MyTweetView_Been();

	        	b.setName(userName);

	        	b.setId(userId);

	        	b.setTweetId(t_no);

	        	b.setChecklike(likeCheck);

	        	b.setLikecounter(likeCount);

	        	b.setTweet(tweet);

	        	data.add(b);


	         }

	        //トランザクションをコミットする
	        cn.commit();

	        rs.close();

	        //ステートメントをクローズする
	        st.close();

	        //RDBMSから切断する
	        cn.close();

	        System.out.println("切断完了");

        }catch(SQLException e){
        	e.printStackTrace();
        }

        return data;

	}
}
