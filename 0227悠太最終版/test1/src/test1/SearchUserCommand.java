package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.SearchUserTest;

public class SearchUserCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  id = reqc.getParameter("id")[0];

		String sessionToken = reqc.getParameter("user_session")[0];

		ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		ArrayList list = SearchUserTest.getS(sessionToken, id, cn);

		try {
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		resc.setResult(list);

		resc.setTarget("searchresult");

		return resc;
	}
}
