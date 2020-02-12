package test1;

public class CreateAccountFormCommand extends AbstractCommand {

	@Override
	public ResponseContext execute() {
		ResponseContext resc = new WebResponseContext();

		resc.setTarget("createaccount");

		return resc;

	}

}
