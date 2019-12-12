/*自分がしたRT一覧を表示するコマンド*/

package test1;

import java.util.ArrayList;

import test1.db.ViewMyRT;

public class ViewRTCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];

		ArrayList list = ViewMyRT.viewMyRT(sessionToken);		//RT一覧をゲット

        resc.setResult(list);




        resc.setTarget("viewRT");
		return resc;
	}

}
