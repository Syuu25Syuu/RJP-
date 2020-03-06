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
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class SearchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  keyword = reqc.getParameter("keyword")[0];
			System.out.println(keyword+"--------------------------------------------------");

			String sessionToken = reqc.getParameter("user_session")[0];

			String select = reqc.getParameter("select")[0];

			//ResponseContext resc = new WebResponseContext();

			Connection cn = new OracleConnector().getCn();


			//通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

			ArrayList resultdata = null;
			//最終的に送出するリスト
			ArrayList datalist = new ArrayList();

			if(select.equals("user") == true) {
				resultdata = SearchUserTest.getSearchUser(keyword,sessionToken,cn);
				Iterator iterator=resultdata.iterator();
				while (iterator.hasNext()) {
					//一時的にDBからのデータを格納するリスト
					//Iteratorを使い一時的にDBのデータをリストに格納
					ArrayList data = (ArrayList) iterator.next();

					System.out.println(data.get(0));
					System.out.println(data.get(1));
					System.out.println(data.get(2));
					System.out.println(data.get(3));
					System.out.println(data.get(4));
					//Beanをインスタンス化する
					SerchBean b = new SerchBean();
					//BeanにDBから取得した値を格納
					String serialNO=(String)data.get(0);
					String userID=(String)data.get(1);
					String userName=(String)data.get(2);
					String userIcon=(String)data.get(3);
					String followCheck=(String)data.get(4);

					System.out.println(userName+"です。チェック"+followCheck);

		        	b.setUserNo(serialNO);
		        	b.setUserName(userName);
		        	b.setUserId(userID);
		        	b.setUserIcon(userIcon);
		        	b.setCheck(followCheck);

		        	b.setNotifyCount(notifyCount);
					//最終的にセッションにセットするリストにBeanを格納する
					datalist.add(b);
				}
			}else if(select.equals("tweet") == true) {
				resultdata = SerchTweet.getSerchTweet(keyword,sessionToken,cn);

				Iterator iterator=resultdata.iterator();
				while (iterator.hasNext()) {
					//一時的にDBからのデータを格納するリスト
					//Iteratorを使い一時的にDBのデータをリストに格納
					ArrayList data = (ArrayList) iterator.next();

					System.out.println(data.get(0));
					System.out.println(data.get(1));
					System.out.println(data.get(2));
					System.out.println(data.get(3));
					System.out.println(data.get(4));
					//Beanをインスタンス化する
					MyTweetView_Been b = new MyTweetView_Been();
					//BeanにDBから取得した値を格納
					String user_sirial_no=(String)data.get(0);
					String users_name=(String)data.get(1);
					String users_id=(String)data.get(2);
					String users_img_path=(String)data.get(3);
					String tweets_no=(String)data.get(4);
					String tweets_content=(String)data.get(5);
					//エスケープ処理
					tweets_content = tweets_content.replaceAll("&amp;","&amp;amp;");
					tweets_content = tweets_content.replaceAll("&lt;","&amp;lt;");
					tweets_content = tweets_content.replaceAll("&gt;","&amp;gt;");
					tweets_content = tweets_content.replaceAll("&quot;","&amp;quot;");
					tweets_content = tweets_content.replaceAll("&apos;","&amp;apos;");
					tweets_content = tweets_content.replaceAll("&nbsp;","&amp;nbsp;");
					tweets_content = tweets_content.replaceAll("\"","&quot;");
					tweets_content = tweets_content.replaceAll("'","&apos;");
					tweets_content = tweets_content.replaceAll("<","&lt;");
					tweets_content = tweets_content.replaceAll(">","&gt;");
					tweets_content = tweets_content.replaceAll("\n","<br>");
					String checklike=(String)data.get(6);
					String check_rt = (String)data.get(7);

					//３月４日に追加したよ
					String countLike = (String)data.get(8);
					String countRT = (String)data.get(9);
					String tweetImg = (String)data.get(10);
					String replyCount = (String)data.get(11);

		        	String imageNone="";

		        	//System.out.println("みてね"+tweetImg);

		        	if(tweetImg == null) {
		        		imageNone ="NonImage";
		        	}

					b.setSerialuserid(user_sirial_no);
					b.setName(users_name);
		        	b.setId(users_id);
		        	b.setIcon(users_img_path);
		        	b.setTweetId(tweets_no);
		        	b.setTweet(tweets_content);
		        	b.setChecklike(checklike);
		        	b.setCheckRT(check_rt);
		        	b.setCountRT(countRT);
					b.setCountLike(countLike);
					b.setTweetImg(tweetImg);
					b.setTweetImageNone(imageNone);
					b.setCountReply(replyCount);

		        	b.setNotifyCount(notifyCount);
					//最終的にセッションにセットするリストにBeanを格納する
					datalist.add(b);
				}
			}else {
				System.out.println("検索ができませんでした");
			}
			cn.close();

			if(datalist.isEmpty()) {
				if(select.equals("user") == true) {
					System.out.println("検索結果がありません----------------------------------------");
					SerchBean b = new SerchBean();

					b.setUserNo("検索結果がない");
		        	b.setUserName("");
		        	b.setUserId("");
		        	b.setUserIcon("");
		        	b.setCheck("");

		        	b.setNotifyCount(notifyCount);
					//最終的にセッションにセットするリストにBeanを格納する
					datalist.add(b);
				}else if(select.equals("tweet") == true) {
					System.out.println("検索結果がありません----------------------------------------");
					MyTweetView_Been b = new MyTweetView_Been();

					b.setSerialuserid("検索結果がない");
					b.setName("");
		        	b.setId("");
		        	b.setIcon("");
		        	b.setTweetId("");
		        	b.setTweet("");
		        	b.setChecklike("");

		        	b.setNotifyCount(notifyCount);
					datalist.add(b);
				}else {
					System.out.println("検索ができませんでした");
				}
			}

			reqc.setResult(datalist);

			if(select.equals("user") == true) {
				resc.setTarget("searchresult");
			}else if(select.equals("tweet") == true) {
				resc.setTarget("searchtweet");
			}else {
				System.out.println("検索ができませんでした");
			}
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}
}
