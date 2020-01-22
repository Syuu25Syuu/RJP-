package test1;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.CheckLikeUser;
import test1.db.CountLikeTweet;
import test1.db.CreateReplyTweet;
import test1.db.GetChildrenTweetSerialNo;
import test1.db.GetTweetContent;
import test1.db.GetUserNo_fromTweet;
import test1.db.GetUsersId;
import test1.db.GetUsersName;

public class CreateReplytweetCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionToken

		String replyid = reqc.getParameter("replyid")[0];		//返信先のツイートID

		String replycontent = reqc.getParameter("replycontent")[0];	//返信の内容



		CreateReplyTweet.createReplyTweet(sessionToken, replyid, replycontent);		//ツイートにリプライできる

		/*ここからリプライを表示する処理*/

		ArrayList list = new ArrayList<>();


		String parentSerialUserNo = GetUserNo_fromTweet.GetUserNo(replyid);	//返信先のユーザーシリアルナンバーを取得

		String parentUserName = GetUsersName.getUserName(parentSerialUserNo);

		String parentUserId = GetUsersId.getUserId(parentSerialUserNo);

		String parentTweetContent = GetTweetContent.getTweetContent(replyid);

		String parentLikeCheck = CheckLikeUser.checkLikeUser(sessionToken, replyid);

		String parentLikeCount = CountLikeTweet.countLikeTweet(replyid);

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

			String childrenUserName = GetUsersName.getUserName(childrenSerialUserNo);

			String childrenUserId = GetUsersId.getUserId(childrenSerialUserNo);

			String childrenTweetContent = GetTweetContent.getTweetContent(childTweetId);

			String childrenCheckLike = CheckLikeUser.checkLikeUser(sessionToken, childTweetId);

			String childrenLikeCount = CountLikeTweet.countLikeTweet(childTweetId);

			b = new MyTweetView_Been();

			if(flg==true) {
				b.setSerialuserid(parentSerialUserNo);
				b.setName(parentUserName);
				b.setId(parentUserId);
				b.setSessionToken(sessionToken);
				b.setTweet(parentTweetContent);
				b.setTweetId(replyid);
				b.setLikecounter(parentLikeCount);
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
				b.setLikecounter(childrenLikeCount);
				b.setTweetId(childTweetId);
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
				b.setLikecounter(childrenLikeCount);
				b.setTweetId(childTweetId);
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
