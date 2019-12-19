/*今使ってないよ*/

package test1;

import java.util.ArrayList;

import test1.db.CheckRTUser;
import test1.db.CreateRT;
import test1.db.DeleteRT;

public class CreateRTCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();


		String  sessionToken = reqc.getParameter("user_session")[0];

		String  tweet_Id = reqc.getParameter("tweet_id")[0];

		String checkRT = CheckRTUser.checkRTUser(sessionToken, tweet_Id);		//そのツイートに対してRTをしていた場合「RTをとりけす」という文字列が帰ってくる

		if(checkRT.equals("RTをとりけす")) {
			DeleteRT.deleteRT(tweet_Id, sessionToken);	//RTを取り消す

		}else {
			CreateRT.createRT(sessionToken, tweet_Id);	//RTをする
		}

		//HOMEにツイートを表示させる処理

		ArrayList list = tukawanComebackHome.comeBackHome(sessionToken);

        resc.setResult(list);
        resc.setTarget("home");
		return resc;
	}

}
