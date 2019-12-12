package test1.db;

import java.sql.Connection;
import java.util.ArrayList;

import test1.GetUser_and_Tweet;

public class ShowProfile {
	public static ArrayList getShowProfile(String user_id,String sessionToken) {
		ArrayList list = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = GetUser_and_Tweet.getUser_and_Tweet(sessionToken, user_id, cn);

		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}


		return list;
	}
}
