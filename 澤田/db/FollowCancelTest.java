package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//フォローを解除するクラス
public class FollowCancelTest{
	public static void cancelFollow(int UserNo,int FollowedNo){

        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //セッションしているユーザのシリアルナンバー(UserNo)と
	        //そのユーザーにフォローされているユーザの
	        //シリアルナンバーがある行を削除する
	        //SQL文を変数に格納する
	        String sql="delete from follows where users_no="+UserNo+" and followed_no="+FollowedNo;

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        int rs = st.executeUpdate(sql);

	        //トランザクションをコミットする
	        cn.commit();

	        //rs.close();

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
