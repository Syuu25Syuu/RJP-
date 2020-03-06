package test1.exception;

public class CheckIdException extends IntegrationException{
	public CheckIdException() {}
	public CheckIdException(String message) { super(message); }
	public CheckIdException(Throwable cause) { super(cause); }
	public CheckIdException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}
