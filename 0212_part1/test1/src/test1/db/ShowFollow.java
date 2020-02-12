package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ShowFollow {
	public static HashMap getFollow(String USERS_SERIALNO,Connection cn) {
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();

		try {

	        String sql = "select USERS_SERIALNO,USERS_NAME,Users_id from USERS where (USERS_SERIALNO) IN (select FOLLOWED_NO from follows where USERS_NO ='"+USERS_SERIALNO+"')";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String serialno = rs.getString(1);
	        	String usersname = rs.getString(2);
	        	String usersid = rs.getString(3);

	        	String[] aStrings = new String[2];

	        	aStrings[0] = usersname;
	        	aStrings[1] = usersid;

	        	map.put(serialno,aStrings);


	         }


        }catch(SQLException e){
        	e.printStackTrace();

        }

		return map;
	}
}
