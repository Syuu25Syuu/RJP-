/*TWEETS_SERIALNOを使用してツイート内容を取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetTweetImage {
	public static String getTweetImage(String TWEETS_SERIALNO,Connection cn) {
		String image = "";
		try{

			cn.setAutoCommit(false);

	        //SQL文を変数に格納する

	        String sql="select TWEETS_IMAGE from TWEETS where TWEETS_SERIALNO = '"+TWEETS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	image = rs.getString(1);
	         }


	        }catch(SQLException e){
	        	e.printStackTrace();
	        }
		return image;

	}
}
