/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.db.GetHeader;
import test1.db.OracleConnector;
import test1.db.ShowProfileImage;
import test1.json.NotifyJsonFileReader;

public class ShowProfileImageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String profUserId  = reqc.getParameter("serialuserid")[0];	//プロフィールに遷移するユーザーのUSERS_SERIALNO

		String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken

		//ResponseContext resc = new WebResponseContext();

		ArrayList list = new ArrayList();
		ArrayList header = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = ShowProfileImage.showProfile(sessionToken, profUserId, cn);
		header = GetHeader.getHeader(sessionToken,cn);

		cn.commit();
		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}

		//通知数
		int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		resc.setResult(notifyCount);

		reqc.setResult(list);
		resc.setTarget("profilesImage");

		return resc;
	}

}
