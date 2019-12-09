/*自分がしたいいね一覧を表示するコマンド*/

package test1;

import java.util.ArrayList;

import test1.db.ViewMyLike;

public class ViewLikeCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();

		String  s_userid = reqc.getParameter("user_session")[0];

		ArrayList list = ViewMyLike.viewMyLike(s_userid);

        resc.setResult(list);




        resc.setTarget("viewlike");
		return resc;
	}

}
