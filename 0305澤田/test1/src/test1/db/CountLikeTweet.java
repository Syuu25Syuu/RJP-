/*そのツイートがどれだけいいねされているかのカウント*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountLikeTweet {
	public static String countLikeTweet(String tweet_no,Connection cn) {
		String counter = "";
		try {


	        String sql = "select count(likes_tweet) from likes where likes_tweet='"+tweet_no+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	counter= rs.getString(1);
	        	//System.out.println("counterの数は"+counter);
	         }




        }catch(SQLException e){
        	e.printStackTrace();

        }
		return counter;

	}
}

