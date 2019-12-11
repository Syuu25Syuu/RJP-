package test1.been;

import java.io.Serializable;

public class MyTweetView_Been implements Serializable{
	public MyTweetView_Been() {}

	private String tweet;	//コンテンツ
	private String tweetdate;
	private String name;
	private String id;
	private String serialuserid;
	private String sessionToken;
	private String tweetId;
	private String likecounter;
	private String checklike;
	private String countRT;

	private String rtuser;

	public String getRtuser() {
		return rtuser;
	}
	public void setRtuser(String rtuser) {
		this.rtuser = rtuser;
	}

	private String checkRT;

	private String childSerialNo;
	private String childUserName;
	private String childUserId;
	private String childTweetId;
	private String childTweetContent;
	private String childTweetLikeCount;
	private String childCheckLike;


	private String parentSerialUserNo;
	private String parentUserId;



	public String getCountRT() {
		return countRT;
	}
	public void setCountRT(String countRT) {
		this.countRT = countRT;
	}
	public String getCheckRT() {
		return checkRT;
	}
	public void setCheckRT(String checkRT) {
		this.checkRT = checkRT;
	}


	public String getParentSerialUserNo() {
		return parentSerialUserNo;
	}
	public void setParentSerialUserNo(String parentSerialUserNo) {
		this.parentSerialUserNo = parentSerialUserNo;
	}
	public String getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId;
	}
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
	public String getChecklike() {
		return checklike;
	}
	public void setChecklike(String checklike) {
		this.checklike = checklike;
	}
	public String getLikecounter() {
		return likecounter;
	}
	public void setLikecounter(String likecounter) {
		this.likecounter = likecounter;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	   public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public void setSessionToken(String sessionToken) {
	    	this.sessionToken = sessionToken;
	 }
	 public String getSessionToken() {
	    	//System.out.println("Bean内のTokenは"+sessionToken+"です");
		 return sessionToken;
	 }

	public String getSerialuserid() {
		return serialuserid;
	}

	public void setSerialuserid(String serialuserid) {
		this.serialuserid = serialuserid;
	}
}
