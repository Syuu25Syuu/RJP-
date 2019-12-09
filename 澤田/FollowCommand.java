package test1;

import test1.db.FollowTest;

//Ajaxを使用するため現在は使用していないクラス

public class FollowCommand extends AbstractCommand{

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		int  userId = Integer.parseInt(reqc.getParameter("userId")[0]);
		int  followedNo = Integer.parseInt(reqc.getParameter("followedNo")[0]);

		FollowTest.follow(userId, followedNo);

		resc.setTarget("serchresult");
		return resc;
	}

}
