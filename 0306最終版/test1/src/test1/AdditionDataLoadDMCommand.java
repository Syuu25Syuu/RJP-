/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.AdditionDataLoadDMTest;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class AdditionDataLoadDMCommand extends AbstractCommand {
	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		RequestContext reqc = getRequestContext();

		try {
			//最終的に送出するリスト
			ArrayList datalist = new ArrayList();
			//最後のメッセージの時間、送信者・受信者のシリアルナンバーのパラメータを取得
			String  dmTime = reqc.getParameter("lastMessageDMTime")[0];
			String  sendUserNo = reqc.getParameter("sendUser")[0];
			String  receiveUserNo = reqc.getParameter("receiveUser")[0];

			Connection cn = new OracleConnector().getCn();

			//DBに接続してselectを実行するメソッドに
			//取得した値を引数にセット
			ArrayList additiondata=AdditionDataLoadDMTest.getDMContent(sendUserNo, receiveUserNo, dmTime,cn);
			System.out.println("更新データ取得完了");

			Iterator iterator=additiondata.iterator();
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
				/*jspのjs内で行う
				dmcontent = dmcontent.replaceAll("&amp;","aaa");
				dmcontent = dmcontent.replaceAll("&lt;","&amp;lt;");
				dmcontent = dmcontent.replaceAll("&gt;","&amp;gt;");
				dmcontent = dmcontent.replaceAll("&quot;","&amp;quot;");
				dmcontent = dmcontent.replaceAll("&apos;","&amp;apos;");
				dmcontent = dmcontent.replaceAll("\"","&quot;");
				dmcontent = dmcontent.replaceAll("'","&apos;");
				dmcontent = dmcontent.replaceAll("<","&lt;");
				dmcontent = dmcontent.replaceAll(">","&gt;");
				dmcontent = dmcontent.replaceAll( "\n","<br>");*/
				String senduserno=(String)data.get(3);
				String receiveuserno=(String)data.get(4);
				String dmtime=(String)data.get(5);
				//ミリ秒を消す
				dmtime = dmtime.replace(".0", "");
				String icon=(String)data.get(6);

				sb.setUserId(id);
				sb.setUserName(name);
				sb.setDmContent(dmcontent);
				sb.setSendUserNo(senduserno);
				sb.setReceiveUserNo(receiveuserno);
				sb.setDmTime(dmtime);
				sb.setUserIcon(icon);
				//最終的にセッションにセットするリストにBeanを格納する
				datalist.add(sb);
			}

			reqc.setResult(datalist);

			resc.setTarget("directmessage");
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}


}
