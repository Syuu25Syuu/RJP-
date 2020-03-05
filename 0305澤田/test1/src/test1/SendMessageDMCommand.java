/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.OracleConnector;
import test1.db.SendMessageDMTest;

public class SendMessageDMCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		String  content = reqc.getParameter("content")[0];
		String  sendUserNo = reqc.getParameter("sendUser")[0];
		String  receiveUserNo = reqc.getParameter("receiveUser")[0];

		//ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		String icon="";

		try {
			SendMessageDMTest.sendMessage(content,sendUserNo, receiveUserNo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		resc.setTarget("directmessage");
		return resc;
	}


}
