/*ログインするためのコマンド*/

package test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import test1.been.Login_Been;
import test1.db.ComebackHome;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.LoginTest;
import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class LoginCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute(ResponseContext resc) throws BussinessLogicException{
		try {
			RequestContext reqc = getRequestContext();

			String  name = reqc.getParameter("name")[0];

			String  pass = reqc.getParameter("pass")[0];

			//ResponseContext resc = new WebResponseContext();

			Connection cn = new OracleConnector().getCn();

			String icon="";


			sessionToken =  LoginTest.insertUser_Table(name,pass,cn); //return password
			icon = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
			ArrayList list = ComebackHome.comeBackHome(sessionToken, cn);
			cn.close();


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
			 //reqc.setResult(list);
			//resc.setTarget("result");	//URL
			//0229澤田追加　けしていいresult.jsp押すのがめんどかった
			resc.setTarget("result");	//URL
			}

		}catch(IntegrationException e) {
			e.printStackTrace();
			throw new BussinessLogicException(e);
		}catch (SQLException e) {
			// TODO: handle exception
			throw new BussinessLogicException(e);
		}
		return resc;
	}


}
