/*そのツイートに対していいねをするためのコマンド*/

package test1;

import java.util.ArrayList;

import test1.db.CheckLikeUser;
import test1.db.DeleteLike;
import test1.db.LikeTweet;
import test1.db.tukawanComebackHome;

public class LikeTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		ResponseContext resc = new WebResponseContext();

		String  tweet_id = reqc.getParameter("tweet_id")[0];

		String  sessionToken = reqc.getParameter("user_session")[0];

		String flgString = CheckLikeUser.checkLikeUser(sessionToken,tweet_id);
		System.out.println("flgStringの中身は"+flgString);
		if(flgString.equals("いいねをとりけす")) {
			DeleteLike.deleteLike(tweet_id,sessionToken);

		}else {
			LikeTweet.likeTweet(tweet_id,sessionToken);
		}

		ArrayList list = tukawanComebackHome.comeBackHome(sessionToken);

        resc.setResult(list);
        resc.setTarget("home");
		return resc;
	}

}
