package test1.exception;

public class PresentationException extends ApplicationException{
	public PresentationException() {}
	public PresentationException(String message) { super(message); }
	public PresentationException(Throwable cause) { super(cause); }
	public PresentationException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}
