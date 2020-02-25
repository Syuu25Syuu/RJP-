package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.ComebackHome;
import test1.db.CreateTweet;
import test1.db.GetHeader;
import test1.db.OracleConnector;

public class CreateTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];
		//String  sessionToken  = "2";
		//String imgfullpath = FileContext.getFile(reqc);
		try {
			//HttpServletRequest request = (HttpServletRequest)reqc.getRequest();
			//Part part = request.getPart("file");
			//System.out.println(part);
		} catch (Exception e) {
			// TODO: handle exception
		}



		//System.out.println("fullぱすうううううう"+imgfullpath);

		String  tweet = reqc.getParameter("contents")[0];

		Connection cn = new OracleConnector().getCn();

		CreateTweet.createTweet(sessionToken,tweet,cn); //return password

		ArrayList list = new ArrayList();
		ArrayList header = new ArrayList();

		try {
			list = ComebackHome.comeBackHome(sessionToken, cn);

			header = GetHeader.getHeader(sessionToken,cn);


			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		reqc.setResult(header);
        resc.setResult(list);
        resc.setTarget("home");



		return resc;
	}

}
