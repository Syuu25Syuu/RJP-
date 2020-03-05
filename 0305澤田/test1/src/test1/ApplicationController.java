package test1;

public interface ApplicationController {
	RequestContext getRequest(Object request);
	ResponseContext handleRequest(RequestContext req);
	void handleResponse(RequestContext reqc,ResponseContext resc);
}
