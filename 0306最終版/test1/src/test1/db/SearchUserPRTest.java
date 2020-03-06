package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 ユーザを検索する
 */
public class SearchUserPRTest {
	public static ArrayList searchUser(String MailAddress,Connection cn) throws IntegrationException{

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
	        String sql="select USERS_SERIALNO,USERS_ID,USERS_NAME,USERS_PROF_IMAGE from users where Users_mail = '"+MailAddress+"' order by USERS_SERIALNO desc";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	ArrayList tmpdata = new ArrayList();

	        	String serialNO = rs.getString("USERS_SERIALNO");
	        	String userID = rs.getString("USERS_ID");
	        	String userName = rs.getString("USERS_NAME");
	        	String userIcon = rs.getString("USERS_PROF_IMAGE");

	        	tmpdata.add(serialNO);
	        	tmpdata.add(userID);
	        	tmpdata.add(userName);
	        	tmpdata.add(userIcon);

	        	data.add(tmpdata);

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
        	throw new IntegrationException(e);
        }

        return data;

        }
}
