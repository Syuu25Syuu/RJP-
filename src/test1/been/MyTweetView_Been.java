package test1.been;

import java.io.Serializable;

public class MyTweetView_Been implements Serializable{
	public MyTweetView_Been() {}

	private String tweet;
	private String tweetdate;
	private String name;
	private String id;
	private String sessionToken;
	private String tweetId;

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
	    	System.out.println("Bean内のTokenは"+sessionToken+"です");
	    	return sessionToken;
	    }
}
