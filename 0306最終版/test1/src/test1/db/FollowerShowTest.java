package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.exception.IntegrationException;

//セッションしているユーザがフォローしているユーザをDBから取得するクラス
public class FollowerShowTest {
	public static ArrayList getFollower(String sessionToken,Connection cn) throws IntegrationException{
		//最終的にビジネスロジックレイヤに送出するリスト
		//リストの中にリストが入っていて中に入っているリストは
		//フォローされているユーザの情報が文字列で入っている
		ArrayList<ArrayList<String>> followerdata=new ArrayList<ArrayList<String>>();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //フォローされているユーザのシリアルナンバー、ID、名前を取得する
	        //SQL文を変数に格納する
	        String sql="SELECT USERS_SERIALNO,USERS_ID,USERS_NAME,USERS_PROF_IMAGE FROM USERS LEFT OUTER JOIN FOLLOWS ON users.USERS_SERIALNO=follows.FOLLOWED_NO WHERE follows.USERS_NO='"+sessionToken+"'";

	        Statement st= cn.createStatement();

	        //SQLの実行
	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	//リザルトセットからそれぞれの値を変数に登録
	        	String serialno=rs.getString("users_serialno");
	        	String id=rs.getString("users_id");
	        	String name=rs.getString("users_name");
	        	String usersicon=rs.getString("users_prof_image");
	        	//最終的に送出するリストの中に入るリスト
	        	//フォローされているユーザのシリアルナンバー、ID、名前を格納するリスト
	        	ArrayList<String> childdata=new ArrayList<String>();
	        	//リストに格納
	        	childdata.add(serialno);
	        	childdata.add(id);
	        	childdata.add(name);
	        	childdata.add(usersicon);
	        	System.out.println("フォローしている人のシリアルナンバー:"+childdata.get(0)+"ユーザID:"+childdata.get(1)+"ユーザの名前:"+childdata.get(2)+"ユーザのアイコン:"+childdata.get(3));
	        	//リストをリストに格納
	        	followerdata.add(childdata);

	         }


	        //トランザクションをコミットする
	        //cn.commit();

	        rs.close();

	        //ステートメントをクローズする
	        st.close();

	        //RDBMSから切断する
	        //cn.close();

	        System.out.println("切断完了");

        }catch(SQLException e){
        	e.printStackTrace();
        	throw new IntegrationException(e);
        }


		return followerdata;
	}
}
