/*そのツイートに自分がいいねをしているかの判定*/

package test1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckLikeUser {
	public static String checkLikeUser(String sessionToken,String TWEETS_SERIALNO,Connection cn) {
		String flgString = "";

		try{


	        //SQL文を変数に格納する

	        String sql="select Likes_User from likes where Likes_User = '"+sessionToken+"'and Likes_Tweet = '"+TWEETS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	System.out.println("flgStringの中身は"+flgString+"です");
	         }

	    	if(flgString=="") {
	    		flgString = "";
			}else {
				flgString = "checked";
			}



	        }catch(SQLException e){
	        	e.printStackTrace();


	        }

			return flgString;

	}
}