/*自分がそのツイートにRTしているかどうかの判定*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckRTUser {
	public static String checkRTUser(String user_id,String tweet_id) {
		String flgString = "";

		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

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
	    		flgString = "RT";
			}else {
				flgString = "RTをとりけす";
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

			return flgString;

	}
}