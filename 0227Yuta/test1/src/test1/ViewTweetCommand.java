/*そのツイートに対するリプライを全表示するためのコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CheckRTUser;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersId;
import test1.db.GetUsersName;
import test1.db.OracleConnector;

public class ViewTweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionToken

		String replyid = reqc.getParameter("tweet_id")[0];		//返信先のツイートID

		Connection cn = new OracleConnector().getCn();

		/*ここからリプライを表示する処理*/

		ArrayList list = new ArrayList<>();

		String parentSerialUserNo = GetUserNo_fromTweet.GetUserNo(replyid);	//返信先のユーザーシリアルナンバーを取得

		String parentUserName = GetUsersName.getUserName(parentSerialUserNo, cn);

		String parentUserId = GetUsersId.getUserId(parentSerialUserNo,cn);

		String parentTweetContent = GetTweetContent.getTweetContent(replyid);

		String parentLikeCheck = CheckLikeUser.checkLikeUser(sessionToken, replyid, cn);

		String parentRTCheck = CheckRTUser.checkRTUser(sessionToken, replyid, cn);	//親のツイートがRTされているかのチェック




		System.out.println("-------------serialuserid-----------------は"+parentSerialUserNo);

		MyTweetView_Been b = new MyTweetView_Been();


		//list.add(b);



		ArrayList chiledrenlist = GetChildrenTweetSerialNo.getChildrenTweetSerialNo(replyid);

		Iterator it = chiledrenlist.iterator();

		boolean flg = true;

		while(it.hasNext()) {
			String childTweetId = (String)it.next();
			System.out.println("返信に使ってるIDは"+childTweetId+"でしたわ");

			String childrenSerialUserNo = GetUserNo_fromTweet.GetUserNo(childTweetId);

			String childrenUserName = GetUsersName.getUserName(childrenSerialUserNo,cn);

			String childrenUserId = GetUsersId.getUserId(childrenSerialUserNo,cn);

			String childrenTweetContent = GetTweetContent.getTweetContent(childTweetId);

			String childrenCheckLike = CheckLikeUser.checkLikeUser(sessionToken,childTweetId, cn);

			String childrenCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);

			//String tweetID = GetTweets_Serialno.getTweets_Serialno(sessionToken);

			b = new MyTweetView_Been();

			//そのツイートに対するリプライがあった場合
			if(flg==true) {
				//親ツイートをBeanに格納
				b.setSerialuserid(parentSerialUserNo);
				b.setName(parentUserName);
				b.setId(parentUserId);
				b.setSessionToken(sessionToken);
				b.setTweet(parentTweetContent);
				b.setTweetId(replyid);
				b.setCheckRT(parentRTCheck);
				b.setChecklike(parentLikeCheck);

				//02/25澤田追加
				if(parentUserId == "" && parentTweetContent == "") {
					b.setTweet("このツイートは削除されました");
				}

				list.add(b);
				flg = false;
				System.out.println("if文には入ったよ");

				//1件目のリプライをBeanに格納
				b = new MyTweetView_Been();

				b.setSerialuserid(childrenSerialUserNo);
				b.setName(childrenUserName);
				b.setId(childrenUserId);
				b.setTweet(childrenTweetContent);
				b.setChecklike(childrenCheckLike);
				b.setCheckRT(parentRTCheck);
				b.setChecklike(parentLikeCheck);
				b.setTweetId(childTweetId);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId(parentUserId);

				list.add(b);

			}else {
				//2件目以降をBeanに格納
				b.setSerialuserid(childrenSerialUserNo);
				b.setName(childrenUserName);
				b.setId(childrenUserId);
				b.setTweet(childrenTweetContent);
				b.setCheckRT(parentRTCheck);
				b.setChecklike(parentLikeCheck);
				b.setTweetId(childTweetId);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId("@"+parentUserId);
				System.out.println("if文には入ってないよ");

				list.add(b);


			}


		}
		//そのツイートに対するリプライが一切なかった場合の処理
		if(flg==true) {
			b.setSerialuserid(parentSerialUserNo);
			b.setName(parentUserName);
			b.setId(parentUserId);
			b.setSessionToken(sessionToken);
			b.setTweet(parentTweetContent);
			b.setTweetId(replyid);
			b.setCheckRT(parentRTCheck);
			b.setChecklike(parentLikeCheck);
			//02/25澤田追加
			if(parentUserId == "" && parentTweetContent == "") {
				b.setTweet("このツイートは削除されました");
			}
			list.add(b);
			flg = false;

		}



		resc.setResult(list);
        resc.setTarget("viewreply");


		return resc;
	}

}

