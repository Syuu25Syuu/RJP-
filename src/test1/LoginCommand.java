package test1;

import test1.been.Login_Been;
import test1.db.LoginTest;

public class LoginCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  name = reqc.getParameter("name")[0];

		String  pass = reqc.getParameter("pass")[0];

		ResponseContext resc = new WebResponseContext();

		Login_Been l = new Login_Been();
		l.setName(name);
		l.setPass(pass);

		String check =  LoginTest.insertUser_Table(name,pass); //return password

		 if(check.equals("")){
			 resc.setTarget("login");

         }else{
        	 resc.setResult(check);
        	 resc.setTarget("result");
         }






		return resc;
	}

}
