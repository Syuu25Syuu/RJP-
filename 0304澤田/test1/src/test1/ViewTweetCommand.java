/*そのツイートに対するリプライを全表示するためのコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CheckRTUser;
import test1.db.CountLikeTweet;
import test1.db.CountRT;
import test1.db.CountReply;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersId;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileReader;

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
		//エスケープ処理
		parentTweetContent = parentTweetContent.replaceAll("&amp;","&amp;amp;");
		parentTweetContent = parentTweetContent.replaceAll("&lt;","&amp;lt;");
		parentTweetContent = parentTweetContent.replaceAll("&gt;","&amp;gt;");
		parentTweetContent = parentTweetContent.replaceAll("&quot;","&amp;quot;");
		parentTweetContent = parentTweetContent.replaceAll("&apos;","&amp;apos;");
		parentTweetContent = parentTweetContent.replaceAll("&nbsp;","&amp;nbsp;");
		parentTweetContent = parentTweetContent.replaceAll("\"","&quot;");
		parentTweetContent = parentTweetContent.replaceAll("'","&apos;");
		parentTweetContent = parentTweetContent.replaceAll("<","&lt;");
		parentTweetContent = parentTweetContent.replaceAll(">","&gt;");
		parentTweetContent = parentTweetContent.replaceAll("\n","<br>");

		String parentLikeCheck = CheckLikeUser.checkLikeUser(sessionToken, replyid, cn);

		String parentRTCheck = CheckRTUser.checkRTUser(sessionToken, replyid, cn);	//親のツイートがRTされているかのチェック

		String parentImgPath = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);

		String likeCount =CountLikeTweet.countLikeTweet(sessionToken, cn);

    	String RTCount = CountRT.countRT(sessionToken, cn);

    	String replyCount = CountReply.countReply(sessionToken, cn);




		System.out.println("-------------serialuserid-----------------は"+parentSerialUserNo);

		MyTweetView_Been b = new MyTweetView_Been();


		//list.add(b);

		//通知数
		int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

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
			//エスケープ処理
			childrenTweetContent = childrenTweetContent.replaceAll("&amp;","&amp;amp;");
			childrenTweetContent = childrenTweetContent.replaceAll("&lt;","&amp;lt;");
			childrenTweetContent = childrenTweetContent.replaceAll("&gt;","&amp;gt;");
			childrenTweetContent = childrenTweetContent.replaceAll("&quot;","&amp;quot;");
			childrenTweetContent = childrenTweetContent.replaceAll("&apos;","&amp;apos;");
			childrenTweetContent = childrenTweetContent.replaceAll("\"","&quot;");
			childrenTweetContent = childrenTweetContent.replaceAll("'","&apos;");
			childrenTweetContent = childrenTweetContent.replaceAll("<","&lt;");
			childrenTweetContent = childrenTweetContent.replaceAll(">","&gt;");
			childrenTweetContent = childrenTweetContent.replaceAll("\n","<br>");

			String childrenCheckLike = CheckLikeUser.checkLikeUser(sessionToken,childTweetId, cn);

			String childrenCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);

			String childrenImgPath = GetUSERS_PROF_IMAGE.getProfile(childrenSerialUserNo, cn);

			String childereLikeCount =CountLikeTweet.countLikeTweet(childrenSerialUserNo, cn);

	    	String childrenRTCount = CountRT.countRT(childrenSerialUserNo, cn);

	    	String childrenreplyCount = CountReply.countReply(childrenSerialUserNo, cn);

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
				b.setIcon(parentImgPath);

				b.setCountLike(likeCount);
				b.setCountRT(RTCount);
				b.setCountReply(replyCount);

				b.setNotifyCount(notifyCount);

				//02/25澤田追加
				if(parentUserId == "" && parentTweetContent == "") {
					b.setSerialuserid("このツイートは削除されました");
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
				b.setCheckRT(childrenCheckRT);
				//b.setChecklike(parentLikeCheck);
				b.setTweetId(childTweetId);
				b.setIcon(childrenImgPath);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId(parentUserId);
				b.setCountLike(childereLikeCount);
				b.setCountRT(childrenRTCount);
				b.setCountReply(childrenreplyCount);
				System.out.println(childereLikeCount+childrenRTCount+childrenreplyCount+"------------------1");

				b.setNotifyCount(notifyCount);

				list.add(b);

			}else {
				//2件目以降をBeanに格納
				b.setSerialuserid(childrenSerialUserNo);
				b.setName(childrenUserName);
				b.setId(childrenUserId);
				b.setTweet(childrenTweetContent);
				b.setCheckRT(childrenCheckRT);
				b.setChecklike(childrenCheckLike);
				b.setTweetId(childTweetId);
				b.setIcon(childrenImgPath);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId("@"+parentUserId);
				b.setCountLike(childereLikeCount);
				b.setCountRT(childrenRTCount);
				b.setCountReply(childrenreplyCount);
				System.out.println(childereLikeCount+childrenRTCount+childrenreplyCount+"------------------2");

				b.setNotifyCount(notifyCount);
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
			b.setIcon(parentImgPath);

			b.setCountLike(likeCount);
			b.setCountRT(RTCount);
			b.setCountReply(replyCount);
			//02/25澤田追加
			if(parentUserId == "" && parentTweetContent == "") {
				b.setSerialuserid("このツイートは削除されました");
			}
			b.setNotifyCount(notifyCount);
			list.add(b);
			flg = false;

		}



		reqc.setResult(list);
        resc.setTarget("viewreply");


		return resc;
	}

}

