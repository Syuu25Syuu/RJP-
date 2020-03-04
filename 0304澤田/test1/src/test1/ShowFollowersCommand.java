/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import test1.been.MyTweetView_Been;
import test1.db.CheckFollow;
import test1.db.OracleConnector;
import test1.db.ShowFollowers;
import test1.json.NotifyJsonFileReader;

public class ShowFollowersCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken



		String  USERS_SERIALNO = reqc.getParameter("serialuserid")[0];	//そのプロフのユーザーのserialナンバー

		Connection cn = new OracleConnector().getCn();
		ArrayList list = new ArrayList();

		HashMap<String,String[]> map = new HashMap<String,String[]>();

		//通知数
		int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		try {
			map = ShowFollowers.getFollowers(USERS_SERIALNO,cn);

			 for(String userSerialNo:map.keySet()) {

		        	MyTweetView_Been bean = new MyTweetView_Been();

		        	String[] newlistStrings = map.get(userSerialNo);

		        	String userName = newlistStrings[0];

		        	System.out.println("どうも、ユーザー名は"+userName+"です");

		        	String userId = newlistStrings[1];

		        	String userImgPath = newlistStrings[2];

		        	String followcheck = CheckFollow.checkFollowString(sessionToken, USERS_SERIALNO, cn);

		        	bean.setName(userName);
		        	bean.setId(userId);
		        	bean.setSerialuserid(userSerialNo);
		        	bean.setIcon(userImgPath);
		        	bean.setCheckFollow(followcheck);

		        	bean.setNotifyCount(notifyCount);
		        	list.add(bean);

		        }

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		if(list.isEmpty()) {
			MyTweetView_Been bean = new MyTweetView_Been();

			bean.setName("");
        	bean.setId("");
        	bean.setSerialuserid("フォロワーがまだいません");
        	bean.setIcon("");
        	bean.setCheckFollow("");

        	bean.setNotifyCount(notifyCount);
        	list.add(bean);
		}

		reqc.setResult(list);

		resc.setTarget("showFollowers");

		return resc;
	}

}
