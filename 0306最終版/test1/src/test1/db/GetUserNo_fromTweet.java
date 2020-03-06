/*TWEETS_SERIALNOを使用してUSERS_NOを取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class GetUserNo_fromTweet {
	public static String GetUserNo(String tweet_no) throws IntegrationException{
		String user_no = "";
		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql="select USERS_NO from tweets where TWEETS_SERIALNO = '"+tweet_no+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	user_no = rs.getString(1);
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
	        	throw new IntegrationException(e);
	        }
		return user_no;

	}

	public static String GetUserNo(String tweet_no,Connection cn) throws IntegrationException{
		String user_no = "";
		try{


	        //SQL文を変数に格納する

	        String sql="select USERS_NO from tweets where TWEETS_SERIALNO = '"+tweet_no+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	user_no = rs.getString(1);
	         }



	        System.out.println("切断完了");


	        }catch(SQLException e){
	        	e.printStackTrace();
	        	throw new IntegrationException(e);
	        }
		return user_no;

	}
}

