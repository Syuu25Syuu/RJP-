package test1.been;

import java.io.Serializable;

public class ChildrenBean implements Serializable{
	private String childSerialNo;
	private String childUserName;
	private String childUserId;
	private String childTweetId;
	private String childTweetContent;
	private String childTweetLikeCount;
	private String childCheckLike;

	public String getChildSerialNo() {
		return childSerialNo;
	}
	public void setChildSerialNo(String childSerialNo) {
		this.childSerialNo = childSerialNo;
	}
	public String getChildUserName() {
		return childUserName;
	}
	public void setChildUserName(String childUserName) {
		this.childUserName = childUserName;
	}
	public String getChildUserId() {
		return childUserId;
	}
	public void setChildUserId(String childUserId) {
		this.childUserId = childUserId;
	}
	public String getChildTweetId() {
		return childTweetId;
	}
	public void setChildTweetId(String childTweetId) {
		this.childTweetId = childTweetId;
	}
	public String getChildTweetContent() {
		return childTweetContent;
	}
	public void setChildTweetContent(String childTweetContent) {
		this.childTweetContent = childTweetContent;
	}
	public String getChildTweetLikeCount() {
		return childTweetLikeCount;
	}
	public void setChildTweetLikeCount(String childTweetLikeCount) {
		this.childTweetLikeCount = childTweetLikeCount;
	}
	public String getChildCheckLike() {
		return childCheckLike;
	}
	public void setChildCheckLike(String childCheckLike) {
		this.childCheckLike = childCheckLike;
	}
}
