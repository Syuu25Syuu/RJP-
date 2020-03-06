package test1;

import test1.db.CreateAccount;
import test1.exception.BussinessLogicException;
import test1.exception.CheckIdException;
import test1.exception.IntegrationException;

public class CreateAccountCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		RequestContext reqc = getRequestContext();
		try {
			String  name = reqc.getParameter("name")[0];

			String id = reqc.getParameter("id")[0];

			String  pass = reqc.getParameter("pass")[0];

			String mail = reqc.getParameter("mailadd")[0];

			String imgpath = reqc.getParameter("mypic2")[0];

			//ResponseContext resc = new WebResponseContext();

			String flg = CreateAccount.createAcount(name,id,pass,mail,imgpath);

			if(flg.equals("アカウントの作成に成功しました")) {
	       	 	resc.setTarget("login");
			}/*else {
				reqc.setResult("かぶってた");
	       	 	resc.setTarget("createaccount");
			}*/
		}catch (CheckIdException e) {
			// TODO: handle exception
			reqc.setResult("かぶってた");
       	 	resc.setTarget("createaccount");
		} catch (IntegrationException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}



		return resc;

	}

}
