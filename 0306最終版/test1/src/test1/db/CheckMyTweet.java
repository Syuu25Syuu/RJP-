/*そのツイートに自分がいいねをしているかの判定*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.IntegrationException;

public class CheckMyTweet {
	public static String checkMyTweet(String sessionToken,String TWEETS_SERIALNO,Connection cn)
		throws IntegrationException{
		String flgString = "";

		try{


	        //SQL文を変数に格納する

	        String sql="select TWEETS_SERIALNO from tweets where USERS_NO = '"+sessionToken+"'and TWEETS_SERIALNO = '"+TWEETS_SERIALNO+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	flgString=rs.getString(1);
	        	//System.out.println("flgStringの中身は"+flgString+"です");
	         }

	    	if(flgString == "") {
	    		flgString = "style = 'display:none'";
			}else {
				flgString = "";
			}



	        }catch(SQLException e){
	        	e.printStackTrace();
	        	throw new IntegrationException(e);
	        }

			return flgString;

	}

}