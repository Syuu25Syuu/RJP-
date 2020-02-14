package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetProfile {
	public static String getProfile(String sessionToken,Connection cn) {

		String profString ="";

		try{

	        //SQL文を変数に格納する
	        //System.out.println("idを表示するよ"+id);
	        String sql="select USERS_PROFILE from users where USERS_SERIALNO = '"+sessionToken+"' ";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	profString = rs.getString(1);
	        }



	        }catch(SQLException e){
	        	e.printStackTrace();

	        }

		return profString;
	}
}
