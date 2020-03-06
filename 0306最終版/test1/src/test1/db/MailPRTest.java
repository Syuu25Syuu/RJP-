package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 上位クラスがメールを送信できるように
 シリアルナンバー、ユーザID、メールアドレスを取得する
 */
public class MailPRTest {
	public static ArrayList searchUser(String UserId,Connection cn) throws IntegrationException{
		//最終時に送るデータ
		ArrayList data = new ArrayList();
		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        //cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        /*
	         引数のユーザIDに対応する
	         シリアルナンバー、ユーザID、メールアドレスを
	         USERS表から取得する
	         */
	        //SQL文を変数に格納する
	        String sql="select USERS_SERIALNO,USERS_ID,USERS_MAIL from users WHERE USERS_ID = '"+UserId+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

        	while(rs.next()) {
        		/*
        		 取得したデータから
        		 シリアルナンバー、ユーザID、メールアドレスを
        		 それぞれ変数いれ
        		 直接、送るListに変数を入れる
        		 */
        		String serialNO = rs.getString(1);
            	String userID = rs.getString(2);
            	String userMail = rs.getString(3);
            	System.out.println("シリアルナンバー:"+serialNO);
            	System.out.println("ユーザID:"+userID);
            	System.out.println("メールアドレス:"+userMail);

            	data.add(serialNO);
            	data.add(userID);
            	data.add(userMail);
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
