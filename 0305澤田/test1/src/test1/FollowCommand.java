/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.CheckFollow;
import test1.db.FollowCancelTest;
import test1.db.FollowTest;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileWriter;

public class FollowCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String  sessionToken = reqc.getParameter("userNo")[0];
		String  followedNo = reqc.getParameter("followedNo")[0];

		//ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		try {
			//follow()の引数に取得したパラメータを入れる
			//取得したパラメータをDBに格納するメソッド(Follows表に)
			String flgString = CheckFollow.checkFollow(sessionToken, followedNo);

			if(flgString.equals("checked")) {
				FollowCancelTest.cancelFollow(sessionToken, followedNo);
				System.out.println("フォローを取り消したよincommand");

			}else {
				FollowTest.follow(sessionToken, followedNo);
				System.out.println("フォローしたおincommand");
				//02/24澤田追加--------------------------------------------------------
				String name = GetUsersName.getUserName(sessionToken, cn);
				String img_path = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
				//通知データに追加する
				NotifyJsonFileWriter.setJsonData(followedNo, sessionToken, name, img_path, "showprofiles", "フォロー", "");
				//---------------------------------------------------------------------
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		resc.setTarget("home");
		return resc;
	}


}
