/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.CheckRTUser;
import test1.db.CreateRT;
import test1.db.DeleteRT;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileWriter;

public class RTCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String  sessionToken = reqc.getParameter("sessionToken")[0];
		String  tweetID = reqc.getParameter("tweetID")[0];

		tweetID = tweetID.replace("rt","");
		//ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		try {
			//follow()の引数に取得したパラメータを入れる
			//取得したパラメータをDBに格納するメソッド(Follows表に)
			String flgString = CheckRTUser.checkRTUser(sessionToken,tweetID);	//ここの戻り値はcheckもしくは空白
			System.out.println("flgStringの中身は"+flgString);
			if(flgString.equals("checked")) {
				DeleteRT.deleteRT(tweetID,sessionToken);
				System.out.println("RTをとりけしたよ！incommand");

			}else {
				CreateRT.createRT(sessionToken,tweetID);
				System.out.println("RTしたよ！incommand");
				//02/24澤田追加--------------------------------------------------------
				String name = GetUsersName.getUserName(sessionToken, cn);
				String img_path = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
				String retweet_user_no = GetUserNo_fromTweet.GetUserNo(tweetID, cn);
				//通知データに追加する
				NotifyJsonFileWriter.setJsonData(retweet_user_no, sessionToken, name, img_path, "viewtweet", "リツイート", tweetID);
				//---------------------------------------------------------------------
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		resc.setResult("");
		reqc.setResult("");
		resc.setTarget("");
		return resc;
	}


}
