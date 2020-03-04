/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;

import test1.been.Login_Been;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.LoginTest;
import test1.db.OracleConnector;

public class LoginCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  name = reqc.getParameter("name")[0];

		String  pass = reqc.getParameter("pass")[0];

		ResponseContext resc = new WebResponseContext();

		Connection cn = new OracleConnector().getCn();

		String icon="";

		try {
		sessionToken =  LoginTest.insertUser_Table(name,pass,cn); //return password
		icon = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		Login_Been l = new Login_Been();
		l.setName(name);
		l.setPass(pass);
		l.setSessionToken(sessionToken);
		l.setIcon(icon);

		System.out.println("LoginCommand内のsessionTokenは"+sessionToken+"です");

		System.out.println(l);



		 if(sessionToken.equals("")){	//失敗だった場合
			 resc.setResult(l);	//beanの内容を送信
			 resc.setTarget("login");

         }else{
        	 resc.setResult(l);	//beanの内容を送信
        	 //resc.setTarget("result");	//URL
        	 //0229澤田追加　けしていいresult.jsp押すのがめんどかった
        	 resc.setTarget("home");	//URL
         }


		return resc;
	}


}
