/*ログインするためのコマンド*/

package test1;

import test1.utility.UtilityExecuter;

public class LogoutCommand extends AbstractCommand {
	String sessionToken;


	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();

		System.out.println("セッション消しますincommand");
		UtilityExecuter.removeResult(reqc, "token");
		System.out.println("セッション消しましたincommand");

		resc.setTarget("login");
		return resc;
	}


}
