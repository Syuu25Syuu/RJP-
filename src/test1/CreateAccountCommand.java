package test1;

import test1.db.CreateAccount;

public class CreateAccountCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  name = reqc.getParameter("name")[0];

		String id = reqc.getParameter("id")[0];

		String  pass = reqc.getParameter("pass")[0];

		String mail = reqc.getParameter("mailadd")[0];

		String imgpath = reqc.getParameter("ceo-select")[0];

		ResponseContext resc = new WebResponseContext();

		String flg = CreateAccount.createAcount(name,id,pass,mail,imgpath);

		if(flg.equals("アカウントの作成に成功しました")) {
       	 	resc.setTarget("login");
		}else {

       	 	resc.setTarget("createaccount");
		}


		return resc;

	}

}
