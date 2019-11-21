package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ViewMy_Tweet {
	public static ArrayList<String> viewMy_Tweet(String id){
		ArrayList<String> list = new ArrayList<String>();

		try{
				Connection cn = new OracleConnector().getCn();

		        //自動コミットをOFFにする
		        cn.setAutoCommit(false);

		        System.out.println("接続完了");

		        //SQL文を変数に格納する
		        System.out.println("idを表示するよ"+id);
		        String sql="select TWEET_CONTENT from Tweets where USER_NO = '"+id+"'";

		        //Statementインターフェイスを実装するクラスの
		        //インスタンスを取得する
		        Statement st= cn.createStatement();

		        ResultSet rs = st.executeQuery(sql);

		        while(rs.next()){
		        	String tweet = rs.getString("TWEET_CONTENT");
		        	System.out.println("ツイートは:"+tweet);
		        	list.add(tweet);
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
			return list;


		}
}
