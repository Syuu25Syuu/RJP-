/*そのツイートがどれだけRTされているかの判定*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountRT {
	public static String countRT(String tweet_no,Connection cn) {
		String counter = "";
		try {

	        String sql = "select count(RT_TWEET) from RT where RT_TWEET = '"+tweet_no+"'";

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

