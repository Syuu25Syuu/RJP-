/*TWEETS_SERIALNOを使用してツイート内容を取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetTweetContent {
	public static String getTweetContent(String TWEETS_SERIALNO) {
		String content = "";
		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql="select TWEETS_CONTENT from TWEETS where TWEETS_SERIALNO = '"+TWEETS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	content = rs.getString(1);
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
		return content;

	}
}
