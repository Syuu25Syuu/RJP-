package test1.exception;

public class SessionCheckException extends Exception{
	public SessionCheckException() {}
	public SessionCheckException(String message) { super(message); }
	public SessionCheckException(Throwable cause) { super(cause); }
	public SessionCheckException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}