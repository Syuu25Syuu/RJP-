/*そのツイートがどれだけいいねされているかのカウント*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class CountReply {
	public static String countReply(String TWEETS_SERIALNO,Connection cn) throws IntegrationException{
		String counter = "";
		try {


	        String sql = "select count(tweets_serialno) from tweets where REPLY_TWEET ='"+TWEETS_SERIALNO+"'";

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
        	throw new IntegrationException(e);
        }
		return counter;

	}
}

