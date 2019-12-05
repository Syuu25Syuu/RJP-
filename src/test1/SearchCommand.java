package test1;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.CheckFollow_by_yuta;
import test1.db.GetUsersId;
import test1.db.GetUsersName;
import test1.db.SearchUserTest;

public class SearchCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  id = reqc.getParameter("id")[0];

		String sessionToken = reqc.getParameter("user_session")[0];

		ResponseContext resc = new WebResponseContext();

		ArrayList search_sid = SearchUserTest.getS(id);

		ArrayList result = new ArrayList();


		Iterator iterator = search_sid.iterator();

		while(iterator.hasNext()) {
			String serialid = (String)iterator.next();

			String userName = GetUsersName.getUserName(serialid);

			String userId = GetUsersId.getUserId(serialid);

			String checkfollow =CheckFollow_by_yuta.getA(sessionToken, serialid);




			SerchBean b = new SerchBean();

			b.setUserNo(serialid);
			b.setUserName(userName);
			b.setUserId(userId);
			b.setCheck(checkfollow);

			result.add(b);



		}

		resc.setResult(result);

		resc.setTarget("searchresult");

		return resc;
	}
}
