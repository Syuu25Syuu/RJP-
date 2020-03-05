/*USERS_SERIALNOを使用してUSERS_IDを取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUSERS_SERIALNO {
	public static String getUserId(String USERS_ID,Connection cn) {
		String name = "";
		try{


	        String sql="select USERS_SERIALNO from users where USERS_ID = '"+USERS_ID+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	name = rs.getString(1);
	         }


	        System.out.println("切断完了");


	        }catch(SQLException e){
	        	e.printStackTrace();

	        }
		return name;


	}
}
