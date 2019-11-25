package test1.been;

import java.io.Serializable;
import java.util.ArrayList;

public class MyTweetView_Been implements Serializable{
	public MyTweetView_Been() {}

	private ArrayList tweet;
	private String tweetdate;
	private String name;
	private String id;
	private String sessionToken;

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


	public void setTweet(ArrayList tweet) {
		this.tweet = tweet;
	}

	public ArrayList getTweet() {
		return this.tweet;
	}

	public void setTweetdate(String tweetdate) {
		this.tweetdate = tweetdate;
	}

	public String getTweetdate() {
		return this.tweetdate;
	}

    public void setSessionToken(String sessionToken) {
    	this.sessionToken = sessionToken;
    }
    public String getSessionToken() {
    	System.out.println("Bean内のTokenは"+sessionToken+"です");
    	return sessionToken;
    }



}
