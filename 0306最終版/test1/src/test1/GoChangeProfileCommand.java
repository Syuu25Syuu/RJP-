/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;
import test1.db.GetProfile;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class GoChangeProfileCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();
			//ResponseContext resc = new WebResponseContext();

			String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
			String  img = reqc.getParameter("image_path")[0];

			Connection cn = new OracleConnector().getCn();

			ArrayList list = new ArrayList();
			String profString = "";

			profString = GetProfile.getProfile(sessionToken, cn);
			String name = GetUsersName.getUserName(sessionToken, cn);

			MyTweetView_Been bean = new MyTweetView_Been();

			bean.setName(name);

			bean.setProfile(profString);

			bean.setProfImage(img);

			System.out.println("こんにちはこんばんは"+name);


			list.add(bean);

			cn.close();


	        resc.setResult(list);
	        resc.setTarget("changeProfile");
		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}

}
