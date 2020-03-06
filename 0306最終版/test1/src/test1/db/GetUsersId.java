/*USERS_SERIALNOを使用してUSERS_IDを取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class GetUsersId {
	public static String getUserId(String USERS_SERIALNO,Connection cn) throws IntegrationException{
		String name = "";
		try{


	        String sql="select USERS_ID from users where USERS_SERIALNO = '"+USERS_SERIALNO+"'";

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
	        	throw new IntegrationException(e);
	        }
		return name;


	}
}
