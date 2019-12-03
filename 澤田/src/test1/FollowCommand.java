package test1;

import test1.db.FollowTest;

public class FollowCommand extends AbstractCommand{

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  userId = reqc.getParameter("userId")[0];
		String  followedNo = reqc.getParameter("followedNo")[0];

		FollowTest.follow(userId, followedNo);

		resc.setTarget("serchresult");
		return resc;
	}

}
