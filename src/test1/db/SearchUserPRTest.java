package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.SerchBean;
/*
 パスワードリマインダーで使うクラス
 ユーザを検索する
 */
public class SearchUserPRTest {
	public static ArrayList searchUser(String MailAddress,Connection cn) {

		ArrayList data = new ArrayList();
		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        //cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        /*
	         引数のメールアドレスに対応する
	         シリアルナンバー、ユーザID、ユーザ名を
	         USERS表から取得する
	         */
	        //SQL文を変数に格納する
	        String sql="select USERS_SERIALNO,USERS_ID,USERS_NAME,USERS_PROF_IMAGE from users where Users_mail = '"+MailAddress+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	/*
	        	 取得したデータから
	        	 シリアルナンバー、ユーザID、ユーザ名を
	        	 それぞれ変数に入れ、それをひとまとまりのBeanに
	        	 してそのBeanをリストに格納する
	        	 */
	        	String serialNO = rs.getString(1);
	        	String userID = rs.getString(2);
	        	String userName = rs.getString(3);
	        	String userImage = rs.getString(4);

	        	SerchBean sb = new SerchBean();
	        	sb.setUserNo(serialNO);
	        	sb.setUserName(userName);
	        	sb.setUserId(userID);
	        	sb.setUserIcon(userImage);

	        	data.add(sb);

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

        return data;

        }
}
