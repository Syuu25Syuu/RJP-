package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.SearchUserTest;
import test1.db.SerchTweet;

public class SearchCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  keyword = reqc.getParameter("keyword")[0];

		String sessionToken = reqc.getParameter("user_session")[0];

		String select = reqc.getParameter("select")[0];

		ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		ArrayList resultdata = new ArrayList();

		if(select.equals("user") == true) {
			resultdata = SearchUserTest.getSearchUser(keyword,sessionToken,cn);
		}else if(select.equals("tweet") == true) {
			resultdata = SerchTweet.getSerchTweet(keyword,sessionToken,cn);
		}else {
			System.out.println("検索ができませんでした");
		}


		try {
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		resc.setResult(resultdata);

		if(select.equals("user") == true) {
			resc.setTarget("searchresult");
		}else if(select.equals("tweet") == true) {
			resc.setTarget("searchtweet");
		}else {
			System.out.println("検索ができませんでした");
		}

		return resc;
	}
}
