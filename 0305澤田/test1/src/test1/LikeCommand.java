/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.CheckLikeUser;
import test1.db.DeleteLike;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersName;
import test1.db.LikeTweet;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileWriter;

public class LikeCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String  sessionToken = reqc.getParameter("sessionToken")[0];
		String  tweetID = reqc.getParameter("tweetID")[0];

		tweetID = tweetID.replace("like","");
		tweetID = tweetID.replace("JS","");

		//follow()の引数に取得したパラメータを入れる
		//取得したパラメータをDBに格納するメソッド(Follows表に)
		try {
			String flgString = CheckLikeUser.checkLikeUser(sessionToken,tweetID);
			System.out.println("flgStringの中身は"+flgString);
			if(flgString.equals("checked")) {
				DeleteLike.deleteLike(tweetID,sessionToken);
				System.out.println("いいねをとりけしたよ！incommand");

			}else {
				LikeTweet.likeTweet(tweetID,sessionToken);
				System.out.println("いいねしたよ！incommand");
				//02/24澤田追加--------------------------------------------------------
				Connection cn = new OracleConnector().getCn();
				String name = GetUsersName.getUserName(sessionToken, cn);
				String img_path = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
				String like_user_no = GetUserNo_fromTweet.GetUserNo(tweetID, cn);
				//通知データに追加する
				NotifyJsonFileWriter.setJsonData(like_user_no, sessionToken, name, img_path, "viewtweet", "いいね", tweetID);
				//---------------------------------------------------------------------
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		resc.setResult("");
		reqc.setResult("");
		resc.setTarget("");
		return resc;
	}


}
