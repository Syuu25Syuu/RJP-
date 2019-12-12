/*そのツイートがどれだけいいねされているかのカウント*/

package test1.newDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountLikeTweet {
	public static String countLikeTweet(String TWEETS_SERIALNO,Connection cn) {
		String counter = "";
		try {


	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        String sql = "select count(likes_tweet) from likes where likes_tweet='"+TWEETS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	counter= rs.getString(1);

	         }



        }catch(SQLException e){
        	e.printStackTrace();

        }
		return counter;

	}
}

