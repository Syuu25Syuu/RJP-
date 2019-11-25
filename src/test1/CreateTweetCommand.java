package test1;

import java.util.ArrayList;
import java.util.HashMap;

import test1.been.MyTweetView_Been;
import test1.db.CreateTweet;
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class CreateTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  s_userid = reqc.getParameter("user_session")[0];


		System.out.println("useridは"+s_userid+"だよ");

		String  tweet = reqc.getParameter("contents")[0];


		CreateTweet.createTweet(s_userid,tweet); //return password

		HashMap map = ViewMyName_And_ID.viewMyName_And_ID(s_userid);

		String id =(String) map.get("id");
		String user_name =(String) map.get("name");

		ArrayList list = ViewMy_Tweet.viewMy_Tweet(s_userid);

		System.out.println("idさんは"+id+"だよ！！！");


		ResponseContext resc = new WebResponseContext();

		MyTweetView_Been p = new MyTweetView_Been();
		p.setName(user_name);
		p.setId(id);
		p.setTweet(list);


        resc.setResult(p);
        resc.setTarget("home");


		return resc;
	}

}
