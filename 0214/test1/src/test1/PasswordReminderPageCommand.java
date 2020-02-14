package test1;
/*
 パスワードリマインダーぺージに飛ぶためのコマンド
 */
public class PasswordReminderPageCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();
		ResponseContext resc = new WebResponseContext();
		//送信先
		resc.setTarget("serchuserPR");

		return resc;
	}

}
