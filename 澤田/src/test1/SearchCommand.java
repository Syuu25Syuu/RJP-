package test1;

import java.util.ArrayList;

import test1.been.SerchBean;
import test1.db.SerchTest;

public class SearchCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  id = reqc.getParameter("id")[0];

		ResponseContext resc = new WebResponseContext();

		ArrayList<ArrayList> data=SerchTest.serchUser(id);



		ArrayList<SerchBean> result=new ArrayList<SerchBean>();

		for(int i=0; i<data.size(); i++) {
			SerchBean sb=new SerchBean();

			sb.setUserId((String)data.get(i).get(0));
			sb.setUserName((String)data.get(i).get(1));
			sb.setUserNo((String)data.get(i).get(2));

			result.add(i, sb);
		}

		resc.setResult(result);

		resc.setTarget("searchresult");

		return resc;
	}
}
