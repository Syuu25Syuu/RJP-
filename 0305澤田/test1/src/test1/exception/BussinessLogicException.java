package test1.exception;

public class BussinessLogicException extends PresentationException{
	public BussinessLogicException() {}
	public BussinessLogicException(String message) { super(message); }
	public BussinessLogicException(Throwable cause) { super(cause); }
	public BussinessLogicException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("例外の理由:"+message);
	}
}
