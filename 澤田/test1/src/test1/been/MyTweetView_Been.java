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
