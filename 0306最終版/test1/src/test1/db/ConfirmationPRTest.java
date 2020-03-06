package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.SerchBean;
import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 ユーザIDに対応するシリアルナンバー、ユーザID、ユーザ名を取得する
 ユーザに選択したアカウントか確認してもらうために使う
 */
public class ConfirmationPRTest {
	public static ArrayList getUserData(String UserId,Connection cn) throws IntegrationException{

		ArrayList data = new ArrayList();
		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        //cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        /*
	         引数のユーザIDに対応するデータを
	         USERS表から取得する
	         */
	        //SQL文を変数に格納する
	        String sql="select USERS_SERIALNO,USERS_ID,USERS_NAME,USERS_PROF_IMAGE from users where Users_id = '"+UserId+"'";

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
	        	String userImgPath = rs.getString(4);

	        	SerchBean sb = new SerchBean();
	        	sb.setUserNo(serialNO);
	        	sb.setUserName(userName);
	        	sb.setUserId(userID);
	        	sb.setUserIcon(userImgPath);

	        	data.add(sb);
	        	System.out.println("-----------------------------------");
	        	System.out.println("ユーザID:"+userID);
	        	System.out.println("ユーザ名:"+userName);
	        	System.out.println("-----------------------------------");

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
