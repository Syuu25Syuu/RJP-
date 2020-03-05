package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchUserTest {
	public static ArrayList getSearchUser(String searchId,String sessionToken,Connection cn) {

		ArrayList datalist = new ArrayList();
		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        //cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select USERS_SERIALNO,USERS_ID,USERS_NAME,USERS_PROF_IMAGE from users where Users_Name LIKE '%"+searchId+"%' AND users_serialno!='"+sessionToken+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String serialNO = rs.getString(1);
	        	String userID = rs.getString(2);
	        	String userName = rs.getString(3);
	        	String userIcon = rs.getString(4);
	        	String followCheck = CheckFollow.checkFollow(sessionToken, serialNO, cn);

	        	System.out.println("followcheckはこちら！"+followCheck);

	        	ArrayList data = new ArrayList();
	        	/*SerchBean b = new SerchBean();
	        	b.setUserNo(serialNO);
	        	b.setUserName(userName);
	        	b.setUserId(userID);
	        	b.setCheck(followCheck);*/

	        	data.add(serialNO);
	        	data.add(userID);
	        	data.add(userName);
	        	data.add(userIcon);
	        	data.add(followCheck);
	        	System.out.println("----------------"+data.get(0));
				System.out.println("----------------"+data.get(1));
				System.out.println("----------------"+data.get(2));
				System.out.println("----------------"+data.get(3));
				System.out.println("----------------"+data.get(4));

	        	datalist.add(data);

	         }

	        //トランザクションをコミットする
	        //cn.commit();

	        //rs.close();

	        //ステートメントをクローズする
	        //st.close();

	        //RDBMSから切断する
	       // cn.close();

	       //System.out.println("切断完了");

        }catch(SQLException e){
        	e.printStackTrace();
        }

        return datalist;

	}
}
