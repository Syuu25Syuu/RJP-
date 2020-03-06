package test1;

public interface ResponseContext {
	public Object getResult();
	public String getTarget();
	public void setResult(Object been);
	public void setTarget(String transferInfo);
	public void setResponse(Object obj);
	public Object getResponse();
	public void setSession(Object obj);
	public Object getSession();
}
