package test1;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.FollowerShowTest;

public class FollowerShowDMCommand extends AbstractCommand{

	@Override
	//現在のセッションのユーザがフォローしている人を表示するために
	//インテグレーションレイヤからフォローしている人をセッションにセットするメソッド
	public ResponseContext execute() {

		RequestContext reqc=getRequestContext();
		ResponseContext resc = new WebResponseContext();
		//フォローしている人を登録するリスト
		ArrayList data=new ArrayList();
		//セッションの取得
		String sessionToken=reqc.getParameter("user_session")[0];
		//現在のセッションナンバーを引数にいれて
		//getFollower()でDBに接続し、フォローしている人を取ってくる(List)
		ArrayList followerdata= FollowerShowTest.getFollower(sessionToken);

		Iterator iterator=followerdata.iterator();
		while (iterator.hasNext()) {
			//一時的にDBからのデータを格納するリスト
			//Iteratorを使い一時的にDBのデータをリストに格納
			ArrayList datalist = (ArrayList) iterator.next();

			System.out.println(datalist.get(0));
			System.out.println(datalist.get(1));
			System.out.println(datalist.get(2));
			System.out.println(datalist.get(3));
			//Beanをインスタンス化する
			SerchBean sb=new SerchBean();
			//BeanにDBから取得した値を格納
			String userno=(String)datalist.get(0);
			String userid=(String)datalist.get(1);
			String username=(String)datalist.get(2);
			String usericon=(String)datalist.get(3);
			sb.setUserNo(userno);
			sb.setUserId(userid);
			sb.setUserName(username);
			sb.setUserIcon(usericon);
			//最終的にセッションにセットするリストにBeanを格納する
			data.add(sb);
		}
		//セッションにリストをセットする
		resc.setResult(data);
		//リクエストに値をセットする
		//ページのパスを指定
		resc.setTarget("followershow");
		return resc;
	}

}
