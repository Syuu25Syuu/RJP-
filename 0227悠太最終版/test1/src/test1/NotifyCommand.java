package test1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import test1.been.NotifyBean;
import test1.db.GetHeader;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileReader;
import test1.json.NotifyJsonFileWriter;

public class NotifyCommand extends AbstractCommand{
	@Override
	public ResponseContext execute() {

		RequestContext reqc=getRequestContext();
		ResponseContext resc = new WebResponseContext();

		//セッションの取得
		String sessionUserNo=reqc.getParameter("session_id")[0];

		//通知ボックスが開かれたとき
		//現在ある通知にすべて既読を付ける
		NotifyJsonFileWriter.setCount(sessionUserNo);

		//最終的に送出するリスト
		ArrayList data=new ArrayList();
		ArrayList header = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {

			header = GetHeader.getHeader(sessionUserNo,cn);


			cn.close();
		}catch(Exception e) {
				e.printStackTrace();
		}

		//ユーザに対するjsonデータを取得する
		ArrayList jsondata=NotifyJsonFileReader.getNotifyData(sessionUserNo);
		Iterator iterator = jsondata.iterator();

		while (iterator.hasNext()) {
			ArrayList tmpdata = (ArrayList) iterator.next();
			String request_user_no =(String)tmpdata.get(0);
			String request_user_name = (String)tmpdata.get(1);
			String request_user_icon_path = (String)tmpdata.get(2);
			String servlet_name = (String)tmpdata.get(3);
			String type = (String)tmpdata.get(4);
			String tweet_id = (String)tmpdata.get(5);

			NotifyBean nb = new NotifyBean();
	        nb.setRequestUserNo(request_user_no);
	        nb.setRequestUserName(request_user_name);
	        nb.setRequestUserIconPath(request_user_icon_path);
	        nb.setServletName(servlet_name);
	        nb.setType(type);
	        nb.setTweetId(tweet_id);

	        data.add(nb);
		}
        System.out.println(data);
	    //リクエストに値をセット
        resc.setResult(data);
	    reqc.setResult(header);
	    //ページのパスを指定
	  	resc.setTarget("notify");

		return resc;
	}
}
