/* ホームに戻るときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.been.Login_Been;
import test1.db.OracleConnector;
import test1.db.ShowProfile;
import test1.db.UpdateName;
import test1.db.UpdateProf;
import test1.db.UpdateProfImage;

public class ChangeProfileCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		String name = reqc.getParameter("user_name")[0];
		String prof = reqc.getParameter("contents")[0];
		String imgpath = reqc.getParameter("mypic2")[0];

		System.out.println("うわあああああああああああああ　　　"+imgpath);

		Connection cn = new OracleConnector().getCn();

		ArrayList list = new ArrayList();
		Login_Been newBean = new Login_Been();

		try {
			UpdateName.updateName(sessionToken, name, cn);
			UpdateProf.updateName(sessionToken, prof, cn);
			UpdateProfImage.updateName(sessionToken, imgpath, cn);

			list = ShowProfile.showProfile(sessionToken, sessionToken, cn);





			newBean.setName(name);

			newBean.setSessionToken(sessionToken);
			newBean.setIcon(imgpath);



			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		resc.setResult(newBean);
        reqc.setResult(list);
        resc.setTarget("profiles");


		return resc;
	}

}
