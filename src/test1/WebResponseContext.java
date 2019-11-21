package test1;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext {

	private Object result;
	private String target;

	private HttpServletResponse _response;

	public WebResponseContext() {}

	public void setTarget(String transferInfo) {
		target = "/WEB-INF/jsp/"+transferInfo + ".jsp";
	}

	@Override
	public Object getResult() {
		// TODO 自動生成されたメソッド・スタブ
		return result;
	}

	@Override
	public String getTarget() {
		// TODO 自動生成されたメソッド・スタブ
		return target;
	}

	@Override
	public void setResult(Object been) {
		result = been;

	}

	@Override
	public void setResponse(Object obj) {
		_response = (HttpServletResponse) obj;

	}

	@Override
	public Object getResuponse() {
		// TODO 自動生成されたメソッド・スタブ
		return _response;
	}

}
