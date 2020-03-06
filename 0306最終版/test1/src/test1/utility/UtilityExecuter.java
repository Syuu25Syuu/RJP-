package test1.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import test1.RequestContext;

public class UtilityExecuter {
	public static void removeResult(RequestContext reqc,String name) {
		try {
			HttpServletRequest req = (HttpServletRequest)reqc.getRequest();
			HttpSession session = (HttpSession)req.getSession();
			System.out.println(session);
			System.out.println(session.getAttribute(name)+"削除する前ーーーーーーーーーーーーーーーーーー");
			session.removeAttribute(name);
			System.out.println(session.getAttribute(name)+"削除した後ーーーーーーーーーーーーーーーーーー");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
