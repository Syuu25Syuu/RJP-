package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//DMで使う
//特定の送信ユーザ、受信ユーザと
//最後のメッセージ時間を引数で受け取り
//その時間以降のデータを取得するクラス
public class AdditionDataLoadDMTest{
	public static ArrayList<ArrayList<String>> getDMContent(String SendUserNo,String ReceiveUserNo,String DMTime,Connection cn){
		//戻り値で返すリスト
		//このリストの中にデータがまとまったリストが入っている
		//リストの中にリストが入っている
		ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try{
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //ページに表示されている最後のメッセージより後に
	        //入力されたメッセージを取得するselect文
	        //SQL文を変数に格納する
	        String sql="SELECT USERS_ID,USERS_NAME,dm.DM_CONTENT,dm.DM_SENDUSER,dm.DM_RECEIVEUSER,DM.DM_TIME,users.USERS_PROF_IMAGE FROM USERS users "
	        		+ "LEFT OUTER JOIN DM dm ON users.USERS_SERIALNO=dm.DM_SENDUSER WHERE ((dm.DM_SENDUSER='"+SendUserNo+"' AND dm.DM_RECEIVEUSER='"+ReceiveUserNo+"') OR (dm.DM_SENDUSER='"+ReceiveUserNo+"' AND dm.DM_RECEIVEUSER='"+SendUserNo+"')) AND (dm.DM_TIME > TO_DATE('"+DMTime+"','yyyy-mm-dd hh24:mi:ss')) ORDER BY DM.DM_TIME";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        //ユーザのID、名前とメッセージ内容、送信者・受信者のシリアルナンバー
	        //メッセージの入力時間を取得しリストに格納する
	        System.out.println("追加データ---------------------");
	        while(rs.next()){
	        	String id=rs.getString("Users_ID");
	        	String name=rs.getString("Users_Name");
	        	String content=rs.getString("DM_CONTENT");
	        	String senduserno=rs.getString("DM_SENDUSER");
	        	String receiveuserno=rs.getString("DM_RECEIVEUSER");
	        	String dmtime=rs.getString("DM_TIME");
	        	String icon=rs.getString("USERS_PROF_IMAGE");

	        	System.out.println("ユーザID:"+id);
	        	System.out.println("ユーザ名:"+name);
	        	System.out.println("メッセージ内容:"+content);
	        	System.out.println("送信者No:"+senduserno);
	        	System.out.println("受信者No:"+receiveuserno);
	        	System.out.println("送信時間:"+dmtime);
	        	System.out.println("アイコン画像のパス:"+icon);

	        	//一時的にデータを格納するリスト
	        	ArrayList<String> childdata=new ArrayList<String>();
	        	//取得したデータをリストに格納
	        	childdata.add(id);
	        	childdata.add(name);
	        	childdata.add(content);
	        	childdata.add(senduserno);
	        	childdata.add(receiveuserno);
	        	childdata.add(dmtime);
	        	childdata.add(icon);
	        	//リストにデータが入ったリストを格納
	        	data.add(childdata);

	         }
	        System.out.println("----------------------------------");

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
