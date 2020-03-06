/*プロフィールの画像を取得するクラス*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class GetUSERS_PROF_IMAGE {
	public static String getProfile(String USERS_SERIALNO,Connection cn) throws IntegrationException{

		String image ="";

		try{

	        //SQL文を変数に格納する
	        //System.out.println("idを表示するよ"+id);
	        String sql="select USERS_PROF_IMAGE from users where USERS_SERIALNO = '"+USERS_SERIALNO+"' ";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	image= rs.getString(1);
	        }

        }catch(SQLException e){
        	e.printStackTrace();
        	throw new IntegrationException(e);
        }

		return image;
	}
}
