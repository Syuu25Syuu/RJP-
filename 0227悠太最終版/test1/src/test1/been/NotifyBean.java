package test1.been;

import java.io.Serializable;

public class NotifyBean implements Serializable{
	private String requestUserNo;
	private String requestUserName;
	private String requestUserIconPath;
	private String servletName;
	private String type;
	private String tweetId;
	public String getRequestUserNo() {
		return requestUserNo;
	}
	public void setRequestUserNo(String requestUserNo) {
		this.requestUserNo = requestUserNo;
	}
	public String getRequestUserName() {
		return requestUserName;
	}
	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}
	public String getRequestUserIconPath() {
		return requestUserIconPath;
	}
	public void setRequestUserIconPath(String requestUserIcon_path) {
		this.requestUserIconPath = requestUserIcon_path;
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

}
