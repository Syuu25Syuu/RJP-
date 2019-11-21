package test1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext {
	private Map _parameters;
	private HttpServletRequest _request;


	@Override
	public String getCommandPath() {
		String servletPath = _request.getServletPath();

		String commandPath = servletPath.substring(1);

		return commandPath;
	}

	@Override
	public String[] getParameter(String key) {
		// TODO 自動生成されたメソッド・スタブ
		return (String[])_parameters.get(key);
	}

	@Override
	public Object getRequest() {
		return _request;
	}

	@Override
	public void setRequest(Object request) {
		_request = (HttpServletRequest)request;
		_parameters = _request.getParameterMap();

	}

}
