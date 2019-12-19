package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class GetUser_and_Tweet {
	public static ArrayList getUser_and_Tweet(String sessionToken,String no,Connection cn) {
		ArrayList list = new ArrayList();

		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        //SQL文を変数に格納する
	        String sql="select USERS_NAME,Users_id,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT from USERS join tweets " +
	        			"on USERS_SERIALNO = tweets.USERS_NO " +
	        			"where users_serialno = '"+no+"' order by TWEETS_DATE desc";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	MyTweetView_Been b = new MyTweetView_Been();
	        	String users_name = rs.getString(1);
	        	String users_id = rs.getString(2);
	        	String tweets_no = rs.getString(3);
	        	String tweets_content = rs.getString(4);


	        	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweets_no, cn);


	        	b.setName(users_name);
	        	b.setId(users_id);
	        	b.setSerialuserid(no);
	        	b.setTweetId(tweets_no);
	        	b.setTweet(tweets_content);
	        	b.setChecklike(checklike);
	        	list.add(b);

	         }

	        //トランザクションをコミットする


	        //rs.close();

	        //ステートメントをクローズする
	        //st.close();

	        //RDBMSから切断する
	        //cn.close();

	        //System.out.println("切断完了");


	        }catch(SQLException e){
	        	e.printStackTrace();
	        }


		return list;
	}
}
