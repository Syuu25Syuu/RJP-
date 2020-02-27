package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CheckRTUser;
import test1.db.CreateReplyTweet;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersId;
import test1.db.GetUsersName;
import test1.db.OracleConnector;

public class CreateReplytweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionToken

		String replyid = reqc.getParameter("replyid")[0];		//返信先のツイートID

		String replycontent = reqc.getParameter("replycontent")[0];	//返信の内容

		//02/25澤田追加
				replycontent = replycontent.replaceAll("&amp;","&amp;amp;");
				replycontent = replycontent.replaceAll("&lt;","&amp;lt;");
				replycontent = replycontent.replaceAll("&gt;","&amp;gt;");
				replycontent = replycontent.replaceAll("&quot;","&amp;quot;");
				replycontent = replycontent.replaceAll("&apos;","&amp;apos;");
				replycontent = replycontent.replaceAll("\"","&quot;");
				replycontent = replycontent.replaceAll("'","&apos;");
				replycontent = replycontent.replaceAll("<","&lt;");
				replycontent = replycontent.replaceAll(">","&gt;");
				replycontent = replycontent.replaceAll( "\n","<br>");


		Connection cn = new OracleConnector().getCn();


		CreateReplyTweet.createReplyTweet(sessionToken, replyid, replycontent);		//ツイートにリプライできる

		/*ここからリプライを表示する処理*/

		ArrayList list = new ArrayList<>();


		String parentSerialUserNo = GetUserNo_fromTweet.GetUserNo(replyid);	//返信先のユーザーシリアルナンバーを取得

		String parentUserName = GetUsersName.getUserName(parentSerialUserNo,cn);

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



			b = new MyTweetView_Been();

			if(flg==true) {
				//親ツイートの格納
				b.setSerialuserid(parentSerialUserNo);
				b.setName(parentUserName);
				b.setId(parentUserId);
				b.setSessionToken(sessionToken);
				b.setTweet(parentTweetContent);
				b.setTweetId(replyid);
				b.setCheckRT(parentRTCheck);
				b.setChecklike(parentLikeCheck);
				list.add(b);

				flg = false;
				System.out.println("if文には入ったよ");

				//最初のリプライをBeanに格納
				b = new MyTweetView_Been();

				b.setSerialuserid(childrenSerialUserNo);
				b.setName(childrenUserName);
				b.setId(childrenUserId);
				b.setTweet(childrenTweetContent);
				b.setChecklike(childrenCheckLike);
				b.setTweetId(childTweetId);
				b.setChecklike(childrenCheckLike);
				b.setCheckRT(childrenCheckRT);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId(parentUserId);

				list.add(b);
			}else {
				//2番目以降のリプライをBeanに格納
				b.setSerialuserid(childrenSerialUserNo);
				b.setName(childrenUserName);
				b.setId(childrenUserId);
				b.setTweet(childrenTweetContent);
				b.setChecklike(childrenCheckLike);
				b.setTweetId(childTweetId);
				b.setChecklike(childrenCheckLike);
				b.setCheckRT(childrenCheckRT);
				//b.setParentSerialUserNo(parentSerialUserNo);
				//b.setParentUserId("@"+parentUserId);
				System.out.println("if文には入ってないよ");

				list.add(b);

			}





		}



		resc.setResult(list);
        resc.setTarget("viewreply");


		return resc;
	}

}
