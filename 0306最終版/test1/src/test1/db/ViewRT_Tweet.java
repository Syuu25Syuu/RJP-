package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import test1.exception.IntegrationException;

public class ViewRT_Tweet {
	public static LinkedHashMap<String, String> viewRT_Tweet(String RT_User) throws IntegrationException{
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();

		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        //System.out.println("idを表示するよ"+id);
	        String sql="select users_no,TWEETS_CONTENT from tweets" +
	        		" where (users_no)IN (select FOLLOWED_NO from follows where users_no = '"+RT_User+"')" +
	        		" OR (TWEETS_SERIALNO)IN(select RT_TWEET from RT where RT_USER ='"+RT_User+"')" +
	        		" OR  users_no = '"+RT_User+"'" +
	        		" order by TWEETS_DATE desc";


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
        	throw new IntegrationException(e);
        }
		return map;
	}
}
