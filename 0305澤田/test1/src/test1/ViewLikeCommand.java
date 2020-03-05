/*自分がしたいいね一覧を表示するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.OracleConnector;
import test1.db.ViewMyLike;

public class ViewLikeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		//ResponseContext resc = new WebResponseContext();

		ArrayList list = new ArrayList<>();

		String  sessionToken = reqc.getParameter("user_session")[0];

		Connection cn = new OracleConnector().getCn();

		try {
		list = ViewMyLike.viewMyLike(sessionToken, cn);

		cn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}



        resc.setResult(list);




        resc.setTarget("viewlike");
		return resc;
	}

}
