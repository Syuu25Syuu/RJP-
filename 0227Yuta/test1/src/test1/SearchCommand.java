package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.been.SerchBean;
import test1.db.OracleConnector;
import test1.db.SearchUserTest;
import test1.db.SerchTweet;

public class SearchCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  keyword = reqc.getParameter("keyword")[0];

		String sessionToken = reqc.getParameter("user_session")[0];

		String select = reqc.getParameter("select")[0];

		ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();


		ArrayList resultdata = null;
		//最終的に送出するリスト
		ArrayList data = new ArrayList();

		if(select.equals("user") == true) {
			resultdata = SearchUserTest.getSearchUser(keyword,sessionToken,cn);
			Iterator iterator=resultdata.iterator();
			while (iterator.hasNext()) {
				//一時的にDBからのデータを格納するリスト
				//Iteratorを使い一時的にDBのデータをリストに格納
				ArrayList datalist = (ArrayList) iterator.next();

				System.out.println(datalist.get(0));
				System.out.println(datalist.get(1));
				System.out.println(datalist.get(2));
				System.out.println(datalist.get(3));
				System.out.println(datalist.get(4));
				//Beanをインスタンス化する
				SerchBean b = new SerchBean();
				//BeanにDBから取得した値を格納
				String serialNO=(String)datalist.get(0);
				String userName=(String)datalist.get(1);
				String userID=(String)datalist.get(2);
				String userIcon=(String)datalist.get(3);
				String followCheck=(String)datalist.get(3);

	        	b.setUserNo(serialNO);
	        	b.setUserName(userName);
	        	b.setUserId(userID);
	        	b.setUserIcon(userIcon);
	        	b.setCheck(followCheck);
				//最終的にセッションにセットするリストにBeanを格納する
				data.add(b);
			}
		}else if(select.equals("tweet") == true) {
			resultdata = SerchTweet.getSerchTweet(keyword,sessionToken,cn);

			Iterator iterator=resultdata.iterator();
			while (iterator.hasNext()) {
				//一時的にDBからのデータを格納するリスト
				//Iteratorを使い一時的にDBのデータをリストに格納
				ArrayList datalist = (ArrayList) iterator.next();

				System.out.println(datalist.get(0));
				System.out.println(datalist.get(1));
				System.out.println(datalist.get(2));
				System.out.println(datalist.get(3));
				System.out.println(datalist.get(4));
				//Beanをインスタンス化する
				MyTweetView_Been b = new MyTweetView_Been();
				//BeanにDBから取得した値を格納
				String user_sirial_no=(String)datalist.get(0);
				String users_name=(String)datalist.get(1);
				String users_id=(String)datalist.get(2);
				String tweets_no=(String)datalist.get(3);
				String tweets_content=(String)datalist.get(4);
				String checklike=(String)datalist.get(5);

				b.setSerialuserid(user_sirial_no);
				b.setName(users_name);
	        	b.setId(users_id);
	        	b.setTweetId(tweets_no);
	        	b.setTweet(tweets_content);
	        	b.setChecklike(checklike);
				//最終的にセッションにセットするリストにBeanを格納する
				data.add(b);
			}
		}else {
			System.out.println("検索ができませんでした");
		}


		try {
			cn.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		resc.setResult(data);

		if(select.equals("user") == true) {
			resc.setTarget("searchresult");
		}else if(select.equals("tweet") == true) {
			resc.setTarget("searchtweet");
		}else {
			System.out.println("検索ができませんでした");
		}

		return resc;
	}
}
