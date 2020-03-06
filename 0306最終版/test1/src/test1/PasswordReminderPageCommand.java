package test1;
/*
 パスワードリマインダーぺージに飛ぶためのコマンド
 */
public class PasswordReminderPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute(ResponseContext resc) {
		RequestContext reqc = getRequestContext();
		//ResponseContext resc = new WebResponseContext();
		//送信先
		resc.setTarget("serchuserPR");

		return resc;
	}

}
