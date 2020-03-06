package test1;

import test1.exception.PresentationException;

public interface ApplicationController {
	RequestContext getRequest(Object request);
	ResponseContext handleRequest(RequestContext req) throws PresentationException;
	void handleResponse(RequestContext reqc,ResponseContext resc) throws PresentationException;
}
