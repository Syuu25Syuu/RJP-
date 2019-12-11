package test1;

import java.util.ArrayList;

import test1.db.tukawanComebackHome;
import test1.db.CreateTweet;

public class CreateTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];


		String  tweet = reqc.getParameter("contents")[0];


		CreateTweet.createTweet(sessionToken,tweet); //return password

		ArrayList list = tukawanComebackHome.comeBackHome(sessionToken);

        resc.setResult(list);
        resc.setTarget("home");


		return resc;
	}

}
