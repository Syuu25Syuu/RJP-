package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//パスワードを変更するクラス
public class ChangePasswordPRTest {
	public static void changePassword(String UserId,String Password,Connection cn) {

		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        //cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        /*
	         引数のuseridに対応する
	         USERS表のUSERS_PASSを引数のPasswordに変更する
	        */
	        //SQL文を変数に格納する
	        String sql="UPDATE USERS SET USERS_PASS = '"+Password+"' WHERE USERS_ID = '"+UserId+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();
	        //SQL実行
	        st.executeUpdate(sql);
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
	}
}
