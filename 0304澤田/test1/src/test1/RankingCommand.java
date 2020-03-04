/* ランキング表示のときに使われるコマンド */

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.MyTweetView_Been;
import test1.db.GetHeader;
import test1.db.GoodNewRankingTest;
import test1.db.GoodRankingTest;
import test1.db.OracleConnector;
import test1.db.RTNewRankingTest;
import test1.db.RTRankingTest;
import test1.json.NotifyJsonFileReader;

public class RankingCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();

		String  sessionToken = reqc.getParameter("user_session")[0];	//sessionTokenであるUSERS_SERIALNOを取得
		String  checkvalue = reqc.getParameter("check_value")[0];
		Connection cn = new OracleConnector().getCn();

		//最終的に送出するリスト
		ArrayList datalist = new ArrayList();

		ArrayList data = new ArrayList();

		ArrayList header = new ArrayList();

		//通知数
		int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		System.out.println("ーーーーーーーー通知は"+notifyCount+"------------------");

		try {
			if(checkvalue.equals("good")) {
				data = GoodRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("goodnew")) {
				data = GoodNewRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("rt")) {
				data = RTRankingTest.getranking(sessionToken, cn);
			}
			else if(checkvalue.equals("rtnew")) {
				data = RTNewRankingTest.getranking(sessionToken, cn);
			}
			else {
				//例外ページにとばす
			}
			header = GetHeader.getHeader(sessionToken,cn);

			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		Iterator iterator=data.iterator();
		while (iterator.hasNext()) {
			//一時的にDBからのデータを格納するリスト
			//Iteratorを使い一時的にDBのデータをリストに格納
			ArrayList tmpdata = (ArrayList) iterator.next();

			System.out.println(tmpdata.get(0));
			System.out.println(tmpdata.get(1));
			System.out.println(tmpdata.get(2));
			System.out.println(tmpdata.get(3));
			//Beanをインスタンス化する
			MyTweetView_Been b = new MyTweetView_Been();
			//BeanにDBから取得した値を格納
			String user_sirial_no = (String)tmpdata.get(0);
			String users_id = (String)tmpdata.get(1);
			String users_name = (String)tmpdata.get(2);
			String users_img_path = (String)tmpdata.get(3);
			String tweets_no = (String)tmpdata.get(4);
			String tweets_content = (String)tmpdata.get(5);
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
			String check_like = (String)tmpdata.get(6);
			String check_rt = (String)tmpdata.get(7);

			//int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

			b.setSerialuserid(user_sirial_no);
			b.setId(users_id);
			b.setName(users_name);
			b.setIcon(users_img_path);
			b.setTweetId(tweets_no);
			b.setTweet(tweets_content);
			b.setChecklike(check_like);
			b.setCheckRT(check_rt);
			System.out.println("---------ここでの"+notifyCount);
			b.setNotifyCount(notifyCount);
			//最終的にセッションにセットするリストにBeanを格納する
			datalist.add(b);
		}

		if(datalist.isEmpty()) {
			System.out.println("検索結果がありません----------------------------------------");
			//Beanをインスタンス化する
			MyTweetView_Been b = new MyTweetView_Been();

			b.setSerialuserid("まだツイートがありません");
			b.setId("");
			b.setName("");
			b.setIcon("");
			b.setTweetId("");
			b.setTweet("");
			b.setChecklike("");
			b.setCheckRT("");
			System.out.println("---------ここでの"+notifyCount);
			b.setNotifyCount(notifyCount);
			//最終的にセッションにセットするリストにBeanを格納する
			datalist.add(b);
		}

		//resc.setResult(header);
        reqc.setResult(datalist);
        resc.setTarget("ranking");


		return resc;
	}

}
