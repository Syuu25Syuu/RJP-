package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DirectMessageTest{
	public static ArrayList<ArrayList<String>> getDMContent(String SendUserNo,String ReceiveUserNo){

		ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="SELECT USERS_ID,USERS_NAME,dm.DM_CONTENT,dm.DM_SENDUSER,dm.DM_RECEIVEUSER,DM.DM_TIME FROM USERS users LEFT OUTER JOIN DM dm ON users.USERS_SERIALNO=dm.DM_SENDUSER WHERE (dm.DM_SENDUSER='"+SendUserNo+"' AND dm.DM_RECEIVEUSER='"+ReceiveUserNo+"') OR (dm.DM_SENDUSER='"+ReceiveUserNo+"' AND dm.DM_RECEIVEUSER='"+SendUserNo+"') ORDER BY DM.DM_TIME";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	String id=rs.getString("Users_ID");
	        	String name=rs.getString("Users_Name");
	        	String content=rs.getString("DM_CONTENT");
	        	String senduserno=rs.getString("DM_SENDUSER");
	        	String receiveuserno=rs.getString("DM_RECEIVEUSER");
	        	String dmtime=rs.getString("DM_TIME");

	        	System.out.println("ユーザID:"+id);
	        	System.out.println("ユーザ名:"+name);
	        	System.out.println("メッセージ内容:"+content);
	        	System.out.println("送信者No:"+senduserno);
	        	System.out.println("受信者No:"+receiveuserno);
	        	System.out.println("送信時間:"+dmtime);

	        	ArrayList<String> childdata=new ArrayList<String>();

	        	childdata.add(id);
	        	childdata.add(name);
	        	childdata.add(content);
	        	childdata.add(senduserno);
	        	childdata.add(receiveuserno);
	        	childdata.add(dmtime);

	        	data.add(childdata);

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
