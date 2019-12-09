

package test1.db;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.ChildrenBean;

public class ReplyChildrenTweet {
	public static ArrayList replyChildrenTweet(String sessionToken,String parentTweetId) {
		ArrayList list = new ArrayList<>();

		ArrayList chiledrenlist = GetChildrenTweetSerialNo.getChildrenTweetSerialNo(parentTweetId);

		Iterator it = chiledrenlist.iterator();

		while(it.hasNext()) {
			String childTweetId = (String)it.next();

			String childrenSerialUserNo = GetUserNo_fromTweet.GetUserNo(childTweetId);	//返信先のユーザーシリアルナンバーを取得

			String childrenUserName = GetUsersName.getUserName(childrenSerialUserNo);

			String childrenUserId = GetUsersId.getUserId(childrenSerialUserNo);

			String childrenTweetContent = GetTweetContent.getTweetContent(childTweetId);

			String childrenCheckLike = CheckLikeUser.checkLikeUser(sessionToken, childTweetId);

			String childrenLikeCount = CountLikeTweet.countLikeTweet(childTweetId);

			ChildrenBean bean = new ChildrenBean();

			bean.setChildSerialNo(childrenSerialUserNo);
			bean.setChildUserName(childrenUserName);
			bean.setChildUserId(childrenUserId);
			bean.setChildTweetContent(childrenTweetContent);
			bean.setChildCheckLike(childrenCheckLike);
			bean.setChildTweetLikeCount(childrenLikeCount);

			list.add(bean);

		}

		return list;
	}
}
