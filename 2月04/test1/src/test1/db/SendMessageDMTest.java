package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//フォローをするクラス
public class SendMessageDMTest{
	public static void sendMessage(String content,String SendUserNo,String ReceiveUserNo){

        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //メッセージ内容、送信者・受信者のシリアルナンバーを表(DM表)に格納する
	        //SQL文を変数に格納する
	        String sql="INSERT INTO DM( DM_CONTENT,DM_SENDUSER,DM_RECEIVEUSER) VALUES('"+content+"','"+SendUserNo+"','"+ReceiveUserNo+"')";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();
	        //SQLの実行
	        st.executeUpdate(sql);

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
