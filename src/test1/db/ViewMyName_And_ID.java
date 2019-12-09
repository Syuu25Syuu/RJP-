

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ViewMyName_And_ID {
	public static HashMap viewMyName_And_ID(String users_serialno){
		HashMap map = new LinkedHashMap();

		try{
				Connection cn = new OracleConnector().getCn();

		        //自動コミットをOFFにする
		        cn.setAutoCommit(false);

		        System.out.println("接続完了");

		        //SQL文を変数に格納する

		        String sql="select USERS_ID,USERS_NAME from users where USERS_SERIALNO = '"+users_serialno+"'";

		        //Statementインターフェイスを実装するクラスの
		        //インスタンスを取得する
		        Statement st= cn.createStatement();

		        ResultSet rs = st.executeQuery(sql);

		        while(rs.next()){
		        	String users_id = rs.getString(1);
		        	String users_name = rs.getString(2);
		        	map.put("id",users_id);
		        	map.put("name",users_name);
		         }

		        //トランザクションをコミットする
		        cn.commit();

		        //ステートメントをクローズする
		        st.close();

		        //RDBMSから切断する
		        cn.close();

		        System.out.println("切断完了");


		        }catch(SQLException e){
		        	e.printStackTrace();

		        }
			return map;


		}
}
