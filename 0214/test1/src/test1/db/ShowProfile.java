package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowProfile {
	public static ArrayList getShowProfile(String user_id,String sessionToken) {
		ArrayList list = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = GetUser_and_Tweet.getUser_and_Tweet(sessionToken, user_id, cn);
		cn.commit();
		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}


		return list;
	}
}
