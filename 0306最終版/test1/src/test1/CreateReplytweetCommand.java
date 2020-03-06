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
import test1.db.CreateReplyTweet;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetTweetImage;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersId;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class CreateReplytweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();
			//ResponseContext resc = new WebResponseContext();

			String  sessionToken = reqc.getParameter("user_session")[0];	//sessionToken

			String replyid = reqc.getParameter("replyid")[0];		//返信先のツイートID

			String replycontent = reqc.getParameter("replycontent")[0];	//返信の内容

			String imageNone="";



			Connection cn = new OracleConnector().getCn();


			CreateReplyTweet.createReplyTweet(sessionToken, replyid, replycontent);		//ツイートにリプライできる

			//通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

			/*ここからリプライを表示する処理*/

			ArrayList list = new ArrayList<>();


			String parentSerialUserNo = GetUserNo_fromTweet.GetUserNo(replyid);	//返信先のユーザーシリアルナンバーを取得

			String parentUserName = GetUsersName.getUserName(parentSerialUserNo,cn);

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

			String parentImgPath = GetUSERS_PROF_IMAGE.getProfile(parentSerialUserNo, cn);

			System.out.println("-------------serialuserid-----------------は"+parentSerialUserNo);

			MyTweetView_Been bean = new MyTweetView_Been();


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
				//エスケープ処理
				childrenTweetContent = childrenTweetContent.replaceAll("&amp;","&amp;amp;");
				childrenTweetContent = childrenTweetContent.replaceAll("&lt;","&amp;lt;");
				childrenTweetContent = childrenTweetContent.replaceAll("&gt;","&amp;gt;");
				childrenTweetContent = childrenTweetContent.replaceAll("&quot;","&amp;quot;");
				childrenTweetContent = childrenTweetContent.replaceAll("&apos;","&amp;apos;");
				childrenTweetContent = childrenTweetContent.replaceAll("&nbsp;","&amp;nbsp;");
				childrenTweetContent = childrenTweetContent.replaceAll("\"","&quot;");
				childrenTweetContent = childrenTweetContent.replaceAll("'","&apos;");
				childrenTweetContent = childrenTweetContent.replaceAll("<","&lt;");
				childrenTweetContent = childrenTweetContent.replaceAll(">","&gt;");
				childrenTweetContent = childrenTweetContent.replaceAll("\n","<br>");

				String childrenCheckLike = CheckLikeUser.checkLikeUser(sessionToken,childTweetId, cn);

				String childrenCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);

				String childrenImgPath = GetUSERS_PROF_IMAGE.getProfile(childrenSerialUserNo, cn);



				bean = new MyTweetView_Been();

				if(flg==true) {

					String parentMytweetCheck = CheckMyTweet.checkMyTweet(sessionToken,replyid , cn);

		        	String parentLikeCount =CountLikeTweet.countLikeTweet(replyid, cn);

		        	String parentRTCount = CountRT.countRT(replyid, cn);

		        	String parentReplyCount = CountReply.countReply(replyid, cn);

		        	String parentChecklike = CheckLikeUser.checkLikeUser(sessionToken, replyid, cn);
	            	String parentCheckRT = CheckRTUser.checkRTUser(sessionToken, replyid, cn);
	            	String parentTweetImg = GetTweetImage.getTweetImage(replyid,cn);

	            	System.out.println("親画像はこれ！"+parentTweetImg);

	            	if(parentTweetImg == null) {
	            		imageNone ="NonImage";
	            	}


					//親ツイートの格納
					bean.setSerialuserid(parentSerialUserNo);
					bean.setName(parentUserName);
					bean.setId(parentUserId);
					bean.setSessionToken(sessionToken);
					bean.setTweet(parentTweetContent);
					bean.setTweetId(replyid);
					bean.setCheckRT(parentRTCheck);
					bean.setChecklike(parentLikeCheck);
					bean.setIcon(parentImgPath);
					bean.setChecklike(parentChecklike);
					bean.setCheckRT(parentCheckRT);
					bean.setCountLike(parentLikeCount);
					bean.setCountReply(parentReplyCount);
					bean.setCountRT(parentRTCount);
					bean.setTweetImg(parentTweetImg);
					bean.setTweetImageNone(imageNone);
					bean.setMytweetCheck(parentMytweetCheck);

					bean.setNotifyCount(notifyCount);
					list.add(bean);

					flg = false;
					System.out.println("if文には入ったよ");

					//最初のリプライをBeanに格納
					bean = new MyTweetView_Been();

					String childMytweetCheck = CheckMyTweet.checkMyTweet(sessionToken,childTweetId , cn);

		        	String childLikeCount =CountLikeTweet.countLikeTweet(childTweetId, cn);

		        	String childRTCount = CountRT.countRT(childTweetId, cn);

		        	String childReplyCount = CountReply.countReply(childTweetId, cn);

		        	String childChecklike = CheckLikeUser.checkLikeUser(sessionToken, childTweetId, cn);
	            	String childCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);
	            	String childTweetImg = GetTweetImage.getTweetImage(childTweetId,cn);

	            	System.out.println("子画像はこれ！"+childTweetImg);

	            	if(childTweetImg == null) {
	            		imageNone ="NonImage";
	            	}

					bean.setSerialuserid(childrenSerialUserNo);
					bean.setName(childrenUserName);
					bean.setId(childrenUserId);
					bean.setTweet(childrenTweetContent);
					bean.setChecklike(childrenCheckLike);
					bean.setTweetId(childTweetId);
					bean.setChecklike(childrenCheckLike);
					bean.setCheckRT(childrenCheckRT);
					bean.setIcon(childrenImgPath);
					bean.setChecklike(childChecklike);
					bean.setCheckRT(childCheckRT);
					bean.setCountLike(childLikeCount);
					bean.setCountReply(childReplyCount);
					bean.setCountRT(childRTCount);
					bean.setTweetImg(childTweetImg);
					bean.setTweetImageNone(imageNone);
					bean.setMytweetCheck(childMytweetCheck);
					//b.setParentSerialUserNo(parentSerialUserNo);
					//b.setParentUserId(parentUserId);
					bean.setNotifyCount(notifyCount);

					list.add(bean);
				}else {

					String childMytweetCheck = CheckMyTweet.checkMyTweet(sessionToken,childTweetId , cn);

		        	String childLikeCount =CountLikeTweet.countLikeTweet(childTweetId, cn);

		        	String childRTCount = CountRT.countRT(childTweetId, cn);

		        	String childReplyCount = CountReply.countReply(childTweetId, cn);

		        	String childChecklike = CheckLikeUser.checkLikeUser(sessionToken, childTweetId, cn);
	            	String childCheckRT = CheckRTUser.checkRTUser(sessionToken, childTweetId, cn);
	            	String childTweetImg = GetTweetImage.getTweetImage(childTweetId,cn);

	            	if(childTweetImg == null) {
	            		imageNone ="NonImage";
	            	}

					//2番目以降のリプライをBeanに格納
					bean.setSerialuserid(childrenSerialUserNo);
					bean.setName(childrenUserName);
					bean.setId(childrenUserId);
					bean.setTweet(childrenTweetContent);
					bean.setChecklike(childrenCheckLike);
					bean.setTweetId(childTweetId);
					bean.setChecklike(childrenCheckLike);
					bean.setCheckRT(childrenCheckRT);
					bean.setIcon(childrenImgPath);
					bean.setChecklike(childChecklike);
					bean.setCheckRT(childCheckRT);
					bean.setCountLike(childLikeCount);
					bean.setCountReply(childReplyCount);
					bean.setCountRT(childRTCount);
					bean.setTweetImg(childTweetImg);
					bean.setTweetImageNone(imageNone);
					bean.setMytweetCheck(childMytweetCheck);
					bean.setTweetImageNone(imageNone);
					//b.setParentSerialUserNo(parentSerialUserNo);
					//b.setParentUserId("@"+parentUserId);
					bean.setNotifyCount(notifyCount);
					System.out.println("if文には入ってないよ");

					list.add(bean);
				}
			}
			reqc.setResult(list);
	        resc.setTarget("viewreply");
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}

		return resc;
	}

}
