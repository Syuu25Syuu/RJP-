package test1.exception;

public class IntegrationException extends BussinessLogicException{
	public IntegrationException() {}
	public IntegrationException(String message) { super(message); }
	public IntegrationException(Throwable cause) { super(cause); }
	public IntegrationException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}
