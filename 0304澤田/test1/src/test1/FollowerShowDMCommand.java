package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.FollowerShowTest;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileReader;

public class FollowerShowDMCommand extends AbstractCommand{

	@Override
	//現在のセッションのユーザがフォローしている人を表示するために
	//インテグレーションレイヤからフォローしている人をセッションにセットするメソッド
	public ResponseContext execute() {

		RequestContext reqc=getRequestContext();
		ResponseContext resc = new WebResponseContext();
		//フォローしている人を登録するリスト
		ArrayList datalist=new ArrayList();
		//DB----------------------------------------------------------------
		ArrayList followerdata=new ArrayList();
		//セッションの取得
		String sessionToken=reqc.getParameter("user_session")[0];
		Connection cn = new OracleConnector().getCn();

		//通知数
		int notifyCount = NotifyJsonFileReader.getNotifyCount(sessionToken);

		try {
			//現在のセッションナンバーを引数にいれて
			//getFollower()でDBに接続し、フォローしている人を取ってくる(List)
			followerdata = FollowerShowTest.getFollower(sessionToken,cn);
			cn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//DB----------------------------------------------------------------

		Iterator iterator=followerdata.iterator();
		while (iterator.hasNext()) {
			//一時的にDBからのデータを格納するリスト
			//Iteratorを使い一時的にDBのデータをリストに格納
			ArrayList data = (ArrayList) iterator.next();

			System.out.println(data.get(0));
			System.out.println(data.get(1));
			System.out.println(data.get(2));
			System.out.println(data.get(3));
			//Beanをインスタンス化する
			SerchBean sb=new SerchBean();
			//BeanにDBから取得した値を格納
			String userno=(String)data.get(0);
			String userid=(String)data.get(1);
			String username=(String)data.get(2);
			String usericon=(String)data.get(3);
			sb.setUserNo(userno);
			sb.setUserId(userid);
			sb.setUserName(username);
			sb.setUserIcon(usericon);

			sb.setNotifyCount(notifyCount);
			//最終的にセッションにセットするリストにBeanを格納する
			datalist.add(sb);
		}

		if(datalist.isEmpty()) {
			//Beanをインスタンス化する
			SerchBean sb=new SerchBean();

			sb.setUserNo("フォローしている人がまだいません");
			sb.setUserId("");
			sb.setUserName("");
			sb.setUserIcon("");

			sb.setNotifyCount(notifyCount);
			//最終的にセッションにセットするリストにBeanを格納する
			datalist.add(sb);
		}
		//セッションにリストをセットする
		reqc.setResult(datalist);
		//リクエストに値をセットする
		//ページのパスを指定
		resc.setTarget("followershow");
		return resc;
	}

}
