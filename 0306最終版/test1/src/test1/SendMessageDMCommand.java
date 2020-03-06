/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.db.OracleConnector;
import test1.db.SendMessageDMTest;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class SendMessageDMCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  content = reqc.getParameter("content")[0];
			String  sendUserNo = reqc.getParameter("sendUser")[0];
			String  receiveUserNo = reqc.getParameter("receiveUser")[0];

			//ResponseContext resc = new WebResponseContext();

			Connection cn = new OracleConnector().getCn();

			String icon="";


			SendMessageDMTest.sendMessage(content,sendUserNo, receiveUserNo);

			resc.setTarget("directmessage");
		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}
		return resc;
	}


}
