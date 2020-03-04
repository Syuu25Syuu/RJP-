/*そのツイートに対するリプライを全表示するためのコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CheckMyTweet;
import test1.db.CheckRTUser;
import test1.db.CountLikeTweet;
import test1.db.CountRT;
import test1.db.CountReply;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetTweetImage;
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

		String parentLikeCount =CountLikeTweet.countLikeTweet(replyid, cn);

    	String parentRTCount = CountRT.countRT(replyid, cn);

    	String parentReplyCount = CountReply.countReply(replyid, cn);

    	String imageNone = "";

    	String parentTweetImg = GetTweetImage.getTweetImage(replyid,cn);

    	String parentMytweetCheck = CheckMyTweet.checkMyTweet(sessionToken,replyid , cn);

    	System.out.println("親画像はこれ！"+parentTweetImg);

    	if(parentTweetImg == null) {
    		imageNone ="NonImage";
    	}




		System.out.println("-------------serialuserid-----------------は"+parentSerialUserNo);

		MyTweetView_Been bean = new MyTweetView_Been();


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

			String childMytweetCheck = CheckMyTweet.checkMyTweet(sessionToken,childTweetId , cn);

        	String childLikeCount =CountLikeTweet.countLikeTweet(childTweetId, cn);

        	String childRTCount = CountRT.countRT(childTweetId, cn);

        	String childReplyCount = CountReply.countReply(childTweetId, cn);

        	String childChecklike = CheckLikeUser.checkLikeUser(sessionToken, childTweetId, cn);
        	String childCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);
        	String childTweetImg = GetTweetImage.getTweetImage(childTweetId,cn);

        	System.out.println("子画像はこれ！"+childTweetImg);



			//String tweetID = GetTweets_Serialno.getTweets_Serialno(sessionToken);

			bean = new MyTweetView_Been();

			//そのツイートに対するリプライがあった場合
			if(flg==true) {
				//親ツイートをBeanに格納


				bean.setSerialuserid(parentSerialUserNo);
				bean.setName(parentUserName);
				bean.setId(parentUserId);
				bean.setSessionToken(sessionToken);
				bean.setTweet(parentTweetContent);
				bean.setTweetId(replyid);
				bean.setCheckRT(parentRTCheck);
				bean.setChecklike(parentLikeCheck);
				bean.setIcon(parentImgPath);

				bean.setCountLike(parentLikeCount);
				bean.setCountReply(parentReplyCount);
				bean.setCountRT(parentRTCount);
				bean.setTweetImg(parentTweetImg);
				bean.setTweetImageNone(imageNone);
				bean.setMytweetCheck(parentMytweetCheck);

				bean.setNotifyCount(notifyCount);

				//02/25澤田追加
				if(parentUserId == "" && parentTweetContent == "") {
					bean.setSerialuserid("このツイートは削除されました");
				}

				list.add(bean);
				flg = false;
				System.out.println("if文には入ったよ");

				if(childTweetImg == null) {
	        		imageNone ="NonImage";
	        	}

				//1件目のリプライをBeanに格納
				bean = new MyTweetView_Been();

				bean.setSerialuserid(childrenSerialUserNo);
				bean.setName(childrenUserName);
				bean.setId(childrenUserId);
				bean.setTweet(childrenTweetContent);
				bean.setChecklike(childrenCheckLike);
				bean.setCheckRT(childrenCheckRT);
				//bean.setChecklike(parentLikeCheck);
				bean.setTweetId(childTweetId);
				bean.setIcon(childrenImgPath);
				//bean.setParentSerialUserNo(parentSerialUserNo);
				//bean.setParentUserId(parentUserId);
				bean.setTweetImg(parentTweetImg);
				bean.setChecklike(childChecklike);
				bean.setCheckRT(childCheckRT);
				bean.setCountLike(childLikeCount);
				bean.setCountReply(childReplyCount);
				bean.setCountRT(childRTCount);
				bean.setTweetImg(childTweetImg);
				bean.setTweetImageNone(imageNone);
				bean.setMytweetCheck(childMytweetCheck);


				bean.setNotifyCount(notifyCount);

				list.add(bean);

			}else {
				if(childTweetImg == null) {
	        		imageNone ="NonImage";
	        	}
				//2件目以降をBeanに格納
				bean.setSerialuserid(childrenSerialUserNo);
				bean.setName(childrenUserName);
				bean.setId(childrenUserId);
				bean.setTweet(childrenTweetContent);
				bean.setCheckRT(childrenCheckRT);
				bean.setChecklike(childrenCheckLike);
				bean.setTweetId(childTweetId);
				bean.setIcon(childrenImgPath);
				//bean.setParentSerialUserNo(parentSerialUserNo);
				//bean.setParentUserId("@"+parentUserId);
				bean.setChecklike(childChecklike);
				bean.setCheckRT(childCheckRT);
				bean.setCountLike(childLikeCount);
				bean.setCountReply(childReplyCount);
				bean.setCountRT(childRTCount);
				bean.setTweetImg(childTweetImg);
				bean.setTweetImageNone(imageNone);
				bean.setMytweetCheck(childMytweetCheck);
				bean.setTweetImageNone(imageNone);

				bean.setNotifyCount(notifyCount);
				System.out.println("if文には入ってないよ");

				list.add(bean);


			}


		}
		//そのツイートに対するリプライが一切なかった場合の処理
		if(flg==true) {
			bean.setSerialuserid(parentSerialUserNo);
			bean.setName(parentUserName);
			bean.setId(parentUserId);
			bean.setSessionToken(sessionToken);
			bean.setTweet(parentTweetContent);
			bean.setTweetId(replyid);
			bean.setCheckRT(parentRTCheck);
			bean.setChecklike(parentLikeCheck);
			bean.setIcon(parentImgPath);

			bean.setCountLike(parentLikeCount);
			bean.setCountReply(parentReplyCount);
			bean.setCountRT(parentRTCount);
			bean.setTweetImg(parentTweetImg);
			bean.setTweetImageNone(imageNone);
			bean.setMytweetCheck(parentMytweetCheck);



			//02/25澤田追加
			if(parentUserId == "" && parentTweetContent == "") {
				bean.setSerialuserid("このツイートは削除されました");
			}
			bean.setNotifyCount(notifyCount);
			list.add(bean);
			flg = false;

		}



		reqc.setResult(list);
        resc.setTarget("viewreply");


		return resc;
	}

}

