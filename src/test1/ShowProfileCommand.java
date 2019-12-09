/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.util.ArrayList;

import test1.db.ShowProfile;

public class ShowProfileCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String userid  = reqc.getParameter("user_id")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

		System.out.println("userid"+userid+"だよ");

		String  sessionToken = reqc.getParameter("user_session")[0];	//ログインしてるユーザーのsessionToken

		ResponseContext resc = new WebResponseContext();

		ArrayList list = ShowProfile.getShowProfile(userid, sessionToken);



		resc.setResult(list);

		resc.setTarget("profiles");

		return resc;
	}

}
