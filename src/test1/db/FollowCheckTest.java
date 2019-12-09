package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FollowCheckTest{
	public static ArrayList<String> followCheck(int SessionNo,int FollowNo){
		//送るデータ既にフォローされているユーザのシリアルナンバーを配列で返す
		ArrayList<String> data=new ArrayList<String>();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select Followed_No from follows where Users_No = '"+SessionNo+"' and Followed_No = '"+FollowNo+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String a=rs.getString("Followed_No");

	        	System.out.println("既にフォローされているユーザシリアルナンバー:"+a);

	        	data.add(a);

	         }

	        //トランザクションをコミットする
	        cn.commit();

	        rs.close();

	        //ステートメントをクローズする
	        st.close();

	        //RDBMSから切断する
	        cn.close();

	        System.out.println("切断完了");

        }catch(SQLException e){
        	e.printStackTrace();
        }

        return data;

        }
}
