package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckFollow_by_yuta {
	public static String getA(String sessionToken,String follow) {

		String flgString ="";
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select FOLLOWED_NO from FOLLOWS where Users_No = '"+sessionToken+"' and FOLLOWED_NO = '"+follow+"'";

	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	System.out.println("flgStringの中身は"+flgString+"です");
	         }

	    	if(flgString=="") {
	    		flgString = "フォロー";
			}else {
				flgString = "解除";
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


		return flgString;
	}
}
