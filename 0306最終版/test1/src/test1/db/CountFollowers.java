/*そのツイートがどれだけRTされているかの判定*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class CountFollowers {
	public static String countFollowers(String USERS_SERIALNO,Connection cn) throws IntegrationException{
		String counter = "";
		try {

	        String sql = "select count(Users_NO) from follows where FOLLOWED_NO = '"+USERS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	counter= rs.getString(1);
	         }


        }catch(SQLException e){
        	e.printStackTrace();
        	throw new IntegrationException(e);
        }
		return counter;

	}
}

