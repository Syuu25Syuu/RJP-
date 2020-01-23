/*USERS_SERIALNOを使用してUSERS_NAMEを取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUsersName {
	public static String getUserName(String USERS_SERIALNO,Connection cn) {
		String name = "";
		try{

	        //SQL文を変数に格納する

	        String sql="select USERS_NAME from users where USERS_SERIALNO = '"+USERS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	name = rs.getString(1);
	         }



	        }catch(SQLException e){
	        	e.printStackTrace();

	        }
		return name;


	}
}
