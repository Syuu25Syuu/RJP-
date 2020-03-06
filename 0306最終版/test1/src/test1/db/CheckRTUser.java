/*自分がそのツイートにRTしているかどうかの判定*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class CheckRTUser {
	public static String checkRTUser(String user_id,String tweet_id,Connection cn)
		throws IntegrationException{
		String flgString = "";

		try{

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql="select RT_USER from RT where RT_USER = '"+user_id+"'and RT_TWEET = '"+tweet_id+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	System.out.println("flgStringの中身は"+flgString+"です");
	         }

	    	if(flgString=="") {
	    		flgString = "";
			}else {
				flgString = "checked";
			}



	        System.out.println("切断完了");


	        }catch(SQLException e){
	        	e.printStackTrace();
	        	throw new IntegrationException(e);
	        }

			return flgString;

	}

	public static String checkRTUser(String user_id,String tweet_id)
			throws IntegrationException{
		String flgString = "";

		try{
			Connection cn = new OracleConnector().getCn();

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql="select RT_USER from RT where RT_USER = '"+user_id+"'and RT_TWEET = '"+tweet_id+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	System.out.println("flgStringの中身は"+flgString+"です");
	         }

	    	if(flgString=="") {
	    		flgString = "";
			}else {
				flgString = "checked";
			}


	    	cn.close();


	        }catch(SQLException e){
	        	e.printStackTrace();
	        	throw new IntegrationException(e);
	        }


			return flgString;

	}
}