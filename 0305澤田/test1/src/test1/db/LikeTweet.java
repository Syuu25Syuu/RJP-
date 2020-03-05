/*対象のツイートにいいねをする*/

package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LikeTweet {
	public static void likeTweet(String tweet_id,String sessionToken) {
		String likecount;

		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql=" insert into Likes(Likes_Tweet,Likes_User)values('"+tweet_id+"','"+sessionToken+"')";




			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();

			//SQLを実行しトランザクションが開始される。処理件数が返される
			st.executeUpdate(sql);
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



	}
}
