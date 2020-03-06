package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.SerchBean;
import test1.db.OracleConnector;
import test1.db.SearchUserPRTest;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;
/*
 パスワードリマインダーで使うクラス
 メールアドレスをもとにユーザ検索をする
 */
public class SearchUserPRCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  mailaddress = reqc.getParameter("mailaddress")[0];

			//ResponseContext resc = new WebResponseContext();
			//最終的に送出するリスト
			ArrayList data = new ArrayList();
			//ーーーーーーーーーーDB関係ーーーーーーーーーーーーーーーー
			Connection cn = new OracleConnector().getCn();

			ArrayList searchdata = SearchUserPRTest.searchUser(mailaddress, cn);

			Iterator iterator=searchdata.iterator();
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

			cn.close();

			//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
			//requestScopeにdataをセット
			reqc.setResult(data);
			//送信先
			resc.setTarget("searchresultPR");

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}catch (IntegrationException e) {
			// TODO: handle exceptio
			throw new BussinessLogicException(e);
		}

		return resc;
	}
}
