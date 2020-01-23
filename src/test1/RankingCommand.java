/* ランキング表示のときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.GoodNewRankingTest;
import test1.db.GoodRankingTest;
import test1.db.OracleConnector;
import test1.db.RTNewRankingTest;
import test1.db.RTRankingTest;

public class RankingCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		String  checkvalue = reqc.getParameter("check_value")[0];
		Connection cn = new OracleConnector().getCn();

		ArrayList datalist = new ArrayList();
		try {
			if(checkvalue.equals("good")) {
				datalist = GoodRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("goodnew")) {
				datalist = GoodNewRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("rt")) {
				datalist = RTRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("rtnew")) {
				datalist = RTNewRankingTest.getranking(sessionToken, cn);
			}
			else {
				//例外ページにとばす
			}

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

        reqc.setResult(datalist);
        resc.setTarget("ranking");


		return resc;
	}

}
