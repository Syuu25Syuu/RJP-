/*ユーザーのプロフィールページに遷移するコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import test1.been.MyTweetView_Been;
import test1.db.CheckFollow;
import test1.db.GetUSERS_SERIALNO;
import test1.db.OracleConnector;
import test1.db.ShowFollow;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class ShowFollowCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();
			//ResponseContext resc = new WebResponseContext();

			String sessionToken = reqc.getParameter("sessionToken")[0];	//ログインしてるユーザーのsessionToken

			String  USERS_SERIALNO = reqc.getParameter("serialuserid")[0];	//そのプロフのユーザーのserialナンバー

			System.out.println("フォローズ"+sessionToken+"-------"+USERS_SERIALNO+"----------");

			Connection cn = new OracleConnector().getCn();
			ArrayList list = new ArrayList();

			HashMap<String,String[]> map = new HashMap<String,String[]>();

			//通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);


			map = ShowFollow.getFollow(USERS_SERIALNO,cn);

			for(String key:map.keySet()) {
				MyTweetView_Been bean = new MyTweetView_Been();

				String[] newlistStrings = map.get(key);

				System.out.println("どうも、キーは"+key+"です");

				String userSerialNo = key;

				String userName = newlistStrings[0];

				System.out.println("どうも、ユーザー名は"+userName+"です");

				String userId = newlistStrings[1];

				String userImgPath = newlistStrings[2];

				System.out.println(userId+"-------------------------------");

				String USERS_SERIALNOS = GetUSERS_SERIALNO.getUserId(userId, cn);

				String followcheck = CheckFollow.checkFollow(sessionToken,USERS_SERIALNOS, cn);

				System.out.println(userName+"です。チェック"+followcheck);;

				bean.setName(userName);
				bean.setId(userId);
				bean.setSerialuserid(userSerialNo);
				bean.setIcon(userImgPath);
				bean.setCheckFollow(followcheck);

				bean.setNotifyCount(notifyCount);
				list.add(bean);
			}
			cn.close();
			if(list.isEmpty()) {
				MyTweetView_Been bean = new MyTweetView_Been();

				bean.setName("");
	        	bean.setId("");
	        	bean.setSerialuserid("フォローしている人がまだいません");
	        	bean.setIcon("");
	        	bean.setCheckFollow("");

	        	bean.setNotifyCount(notifyCount);
	        	list.add(bean);
			}

			reqc.setResult(list);

			resc.setTarget("showFollowers");

		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}

}
