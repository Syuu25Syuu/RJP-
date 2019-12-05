package test1;

public class SearchUserCommand extends AbstractCommand{

	@Override
	public ResponseContext execute() {
		ResponseContext resc = new WebResponseContext();
		resc.setTarget("searchuser");
		return resc;
	}

}
