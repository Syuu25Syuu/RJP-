package test1;

import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.DirectMessageTest;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
import test1.json.NotifyJsonFileReader;

public class DirectMessageCommand extends AbstractCommand{

	@Override

	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc=getRequestContext();
			//ResponseContext resc = new WebResponseContext();
			//フォローしている人を登録するリスト
			ArrayList datalist=new ArrayList();
			//セッションの取得
			String sendUserNo=reqc.getParameter("session_id")[0];
			String receiveUserNo=reqc.getParameter("followed_no")[0];
			System.out.println(sendUserNo+"senduser-----------------------------------------");
			System.out.println(receiveUserNo+"receive-----------------------------------------");
			String receiveUserName=reqc.getParameter("followed_name")[0];

			//通知数
			int notifyCount = NotifyJsonFileReader.getNotifyCount(sendUserNo);
			//現在のセッションナンバーを引数にいれて
			//getFollower()でDBに接続し、フォローしている人を取ってくる(List)
			ArrayList dmdata= DirectMessageTest.getDMContent(sendUserNo, receiveUserNo);

			Iterator iterator=dmdata.iterator();
			while (iterator.hasNext()) {
				//一時的にDBからのデータを格納するリスト
				//Iteratorを使い一時的にDBのデータをリストに格納
				ArrayList data = (ArrayList) iterator.next();

				//Beanをインスタンス化する
				SerchBean sb=new SerchBean();
				//BeanにDBから取得した値を格納
				String id=(String)data.get(0);
				String name=(String)data.get(1);
				String dmcontent=(String)data.get(2);
				//エスケープ処理
				dmcontent = dmcontent.replaceAll("&amp;","&amp;amp;");
				dmcontent = dmcontent.replaceAll("&lt;","&amp;lt;");
				dmcontent = dmcontent.replaceAll("&gt;","&amp;gt;");
				dmcontent = dmcontent.replaceAll("&quot;","&amp;quot;");
				dmcontent = dmcontent.replaceAll("&apos;","&amp;apos;");
				dmcontent = dmcontent.replaceAll("&nbsp;","&amp;nbsp;");
				dmcontent = dmcontent.replaceAll("\"","&quot;");
				dmcontent = dmcontent.replaceAll("'","&apos;");
				dmcontent = dmcontent.replaceAll("<","&lt;");
				dmcontent = dmcontent.replaceAll(">","&gt;");
				dmcontent = dmcontent.replaceAll( "\n","<br>");
				String senduserno=(String)data.get(3);
				String receiveuserno=(String)data.get(4);
				String dmtime=(String)data.get(5);
				//ミリ秒を消す
				dmtime = dmtime.replace(".0", "");
				String icon=(String)data.get(6);
				System.out.println("アイコンのパス"+icon);

				sb.setUserId(id);
				sb.setUserName(name);
				sb.setDmContent(dmcontent);
				sb.setSendUserNo(sendUserNo);
				sb.setReceiveUserNo(receiveUserNo);
				sb.setDmTime(dmtime);
				sb.setUserIcon(icon);
				sb.setReceiveusername(receiveUserName);

				sb.setNotifyCount(notifyCount);
				//最終的にセッションにセットするリストにBeanを格納する
				datalist.add(sb);
			}
			if(datalist.isEmpty()) {
				//Beanをインスタンス化する
				SerchBean sb=new SerchBean();

				sb.setUserId("");
				sb.setUserName("");
				sb.setDmContent("");
				sb.setSendUserNo(sendUserNo);
				sb.setReceiveUserNo(receiveUserNo);
				sb.setDmTime("");
				sb.setUserIcon("");
				sb.setReceiveusername(receiveUserName);

				sb.setNotifyCount(notifyCount);

				datalist.add(sb);
			}
			System.out.println(sendUserNo+"senduser-----------------------------------------");
			System.out.println(receiveUserNo+"receive-----------------------------------------");
			reqc.setResult(datalist);
			//ページのパスを指定
			resc.setTarget("directmessage");
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}

}
