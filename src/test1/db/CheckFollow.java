/* 自分が他のユーザーをフォローしているかどうかを判定する */

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckFollow {
	public static String checkFollow(String sessionToken,String follow,Connection cn) {

		String flgString ="";
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする

	        System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select FOLLOWED_NO from FOLLOWS where Users_No = '"+sessionToken+"' and FOLLOWED_NO = '"+follow+"'";

	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	System.out.println("flgStringの中身は"+flgString+"です");
	         }
	        //フォローしていなかった場合flgStringには空白が返される
	    	if(flgString=="") {
	    		flgString = "";	//フォローしていなかった場合
			}else {
				flgString = "checked";			//フォローしていた場合
			}



        }catch(SQLException e){
        	e.printStackTrace();
        }


		return flgString;
	}

	public static String checkFollow(String sessionToken,String follow) {
		Connection cn = new OracleConnector().getCn();
		String flgString ="";
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
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
	        //フォローしていなかった場合flgStringには空白が返される
	    	if(flgString=="") {
	    		flgString = "";	//フォローしていなかった場合
			}else {
				flgString = "checked";			//フォローしていた場合
			}

	    	cn.commit();
	    	cn.close();


        }catch(SQLException e){
        	e.printStackTrace();
        }


		return flgString;
	}
}
