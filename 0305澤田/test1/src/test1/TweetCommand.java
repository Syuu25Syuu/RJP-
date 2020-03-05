/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.ComebackHome;
import test1.db.CreateTweet;
import test1.db.OracleConnector;
import test1.file.TweetUpload;
import test1.file.Upload;
import test1.json.NotifyJsonFileReader;

public class TweetCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String  sessionToken = reqc.getParameter("sessionToken")[0];
		String  tweetcontents = reqc.getParameter("contents")[0];

		Upload upload = new TweetUpload();
		String writePath = upload.upload(reqc,"inputFile");
		//ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		ArrayList list = null;
		int showcount = 0;
		try {
			CreateTweet.createTweet(sessionToken, tweetcontents, writePath);

			list = ComebackHome.comeBackHome(sessionToken, cn);

		    //通知が何件あるか確認
			showcount = NotifyJsonFileReader.getNotifyCount(sessionToken);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("sessiontoken-----------------------------------------------"+sessionToken);
		System.out.println("tweetcontent-----------------------------------------------"+tweetcontents);
		System.out.println("writepath-----------------------------------------------"+writePath);
		System.out.println("list-----------------------------------------------"+list);
		reqc.setResult(list);
		resc.setResult(showcount);

		resc.setTarget("home");
		return resc;
	}


}
