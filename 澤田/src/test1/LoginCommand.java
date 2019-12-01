package test1;

import test1.been.Login_Been;
import test1.db.LoginTest;

public class LoginCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  name = reqc.getParameter("name")[0];

		String  pass = reqc.getParameter("pass")[0];

		ResponseContext resc = new WebResponseContext();

		sessionToken =  LoginTest.insertUser_Table(name,pass); //return password

		Login_Been l = new Login_Been();
		l.setName(name);
		l.setPass(pass);
		l.setSessionToken(sessionToken);

		System.out.println("LoginCommand内のsessionTokenは"+sessionToken+"です");

		System.out.println(l);



		 if(sessionToken.equals("")){	//失敗だった場合
			 resc.setResult(l);	//beanの内容を送信
			 resc.setTarget("login");

         }else{
        	 resc.setResult(l);	//beanの内容を送信
        	 resc.setTarget("result");	//URL
         }


		return resc;
	}


}
