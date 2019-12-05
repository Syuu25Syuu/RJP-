package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FollowTest{
	public static void follow(int UserNo,int FollowedNo){

        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="insert into follows(users_no,followed_no) values("+UserNo+","+FollowedNo+")";

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
