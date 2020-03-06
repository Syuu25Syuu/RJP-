package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class GetTweets_Serialno {
	public static String getTweets_Serialno(String sessionToken) throws IntegrationException{
		String tweets_serialnoString = "";

		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        //System.out.println("idを表示するよ"+id);
	        String sql="select TWEETS_SERIALNO from Tweets where USERS_NO = '"+sessionToken+"' order by TWEETS_DATE desc ";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){

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

		return tweets_serialnoString;
	}
}
