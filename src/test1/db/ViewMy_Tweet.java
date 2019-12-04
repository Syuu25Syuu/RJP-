package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class ViewMy_Tweet {
	public static LinkedHashMap viewMy_Tweet(String id){
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();

		try{
				Connection cn = new OracleConnector().getCn();

		        //自動コミットをOFFにする
		        cn.setAutoCommit(false);

		        System.out.println("接続完了");

		        //SQL文を変数に格納する
		        //System.out.println("idを表示するよ"+id);
		        String sql="select TWEETS_SERIALNO,TWEETS_CONTENT from Tweets where USERS_NO = '"+id+"' order by TWEETS_DATE desc ";

		        //Statementインターフェイスを実装するクラスの
		        //インスタンスを取得する
		        Statement st= cn.createStatement();

		        ResultSet rs = st.executeQuery(sql);

		        while(rs.next()){
		        	String no = rs.getString(1);
		        	String tweet = rs.getString(2);

		        	//System.out.println("Bean時点でのtweetidは"+no);
		        	//System.out.println("Bean時点でのtweetは"+tweet);


		        	map.put(no,tweet);

		        	//System.out.println(map.get(no));
		        	//System.out.println(map.get(no)); //ここまではOK
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
			return map;


		}
}
