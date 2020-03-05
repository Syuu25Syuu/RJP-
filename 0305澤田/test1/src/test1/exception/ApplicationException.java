package test1.exception;

public class ApplicationException extends Exception{
	public ApplicationException() {}
	public ApplicationException(String message) { super(message); }
	public ApplicationException(Throwable cause) { super(cause); }
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}
