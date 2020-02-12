/*そのツイートに対するリプライのTWEETS_SERIALNOを取得する*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetChildrenTweetSerialNo {
	public static ArrayList getChildrenTweetSerialNo(String parentSerialNo) {
		ArrayList list = new ArrayList<>();

		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        //System.out.println("idを表示するよ"+id);
	        String sql="select TWEETS_SERIALNO from tweets where REPLY_TWEET = '"+parentSerialNo+"' order by TWEETS_DATE";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String TWEETS_SERIALNO = rs.getString(1);
	        	list.add(TWEETS_SERIALNO);

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
