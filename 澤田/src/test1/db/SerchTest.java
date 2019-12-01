package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SerchTest{
	public static ArrayList<ArrayList> serchUser(String UserId){

		ArrayList<ArrayList> data=new ArrayList<ArrayList>();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select User_ID,User_Name,User_SerialNo from users where User_ID LIKE '%"+UserId+"%'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String a=rs.getString("User_ID");
	        	String b=rs.getString("User_Name");
	        	String c=rs.getString(3);

	        	System.out.println("ユーザID:"+a);
	        	System.out.println("ユーザ名:"+b);

	        	ArrayList<String> childdata=new ArrayList<String>();

	        	childdata.add(a);
	        	childdata.add(b);
	        	childdata.add(c);

	        	data.add(childdata);

	         }

	        //トランザクションをコミットする
	        cn.commit();

	        rs.close();

	        //ステートメントをクローズする
	        st.close();

	        //RDBMSから切断する
	        cn.close();

	        System.out.println("切断完了");

        }catch(SQLException e){
        	e.printStackTrace();
        }

        return data;

        }
}
