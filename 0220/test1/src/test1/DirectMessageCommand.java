package test1;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.DirectMessageTest;

public class DirectMessageCommand extends AbstractCommand{

	@Override

	public ResponseContext execute() {

		RequestContext reqc=getRequestContext();
		ResponseContext resc = new WebResponseContext();
		//フォローしている人を登録するリスト
		ArrayList data=new ArrayList();
		//セッションの取得
		String sendUserNo=reqc.getParameter("session_id")[0];
		String receiveUserNo=reqc.getParameter("followed_no")[0];
		String receiveUserName=reqc.getParameter("followed_name")[0];
		//現在のセッションナンバーを引数にいれて
		//getFollower()でDBに接続し、フォローしている人を取ってくる(List)
		ArrayList dmdata= DirectMessageTest.getDMContent(sendUserNo, receiveUserNo);

		Iterator iterator=dmdata.iterator();
		while (iterator.hasNext()) {
			//一時的にDBからのデータを格納するリスト
			//Iteratorを使い一時的にDBのデータをリストに格納
			ArrayList datalist = (ArrayList) iterator.next();

			//Beanをインスタンス化する
			SerchBean sb=new SerchBean();
			//BeanにDBから取得した値を格納
			String id=(String)datalist.get(0);
			String name=(String)datalist.get(1);
			String dmcontent=(String)datalist.get(2);
			String senduserno=(String)datalist.get(3);
			String receiveuserno=(String)datalist.get(4);
			String dmtime=(String)datalist.get(5);
			String icon=(String)datalist.get(6);
			System.out.println("アイコンのパス"+icon);

			sb.setUserId(id);
			sb.setUserName(name);
			sb.setDmContent(dmcontent);
			sb.setSendUserNo(senduserno);
			sb.setReceiveUserNo(receiveuserno);
			sb.setDmTime(dmtime);
			sb.setUserIcon(icon);
			//最終的にセッションにセットするリストにBeanを格納する
			data.add(sb);
		}
		//セッションにリストをセットする
		resc.setResult(data);
		//リクエストに値をセット
		ArrayList receiveuserdata=new ArrayList();
		receiveuserdata.add(receiveUserNo);
		receiveuserdata.add(receiveUserName);
		reqc.setResult(receiveuserdata);
		//ページのパスを指定
		resc.setTarget("directmessage");

		return resc;
	}

}
