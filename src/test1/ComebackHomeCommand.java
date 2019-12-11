/* ホームに戻るときに使われるコマンド */

package test1;

import java.util.ArrayList;

import test1.db.tukawanComebackHome;

public class ComebackHomeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得


		ArrayList list = tukawanComebackHome.comeBackHome(sessionToken);

        resc.setResult(list);
        resc.setTarget("home");


		return resc;
	}

}
