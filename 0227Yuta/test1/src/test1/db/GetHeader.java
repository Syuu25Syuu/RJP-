package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class GetHeader {
	public static ArrayList getHeader(String sessionToken,Connection cn) {
		ArrayList list = new ArrayList();

		try{
	        //Driverインターフェイスを実装するクラスをロードする
	        //Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        String myName = GetUsersName.getUserName(sessionToken, cn);
	        String profName = GetUsersName.getUserName(sessionToken, cn);	//プロフィールのユーザー名
	        String profId = GetUsersId.getUserId(sessionToken, cn);	//プロフィールのユーザーID
	        String prof = GetProfile.getProfile(sessionToken, cn);
	              /*1月23日追加*/
	        String profImage = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);

	        System.out.println("myNameは"+myName);

	        MyTweetView_Been bean = new MyTweetView_Been();



        	bean.setProfUserName(profName);

        	bean.setProfUserId(profId);

        	bean.setProfile(prof);

        	bean.setSerialuserid(sessionToken);


        	bean.setProfImage(profImage);

        	list.add(bean);


	        }catch(SQLException e){
	        	e.printStackTrace();
	        }

		return list;
	}
}
