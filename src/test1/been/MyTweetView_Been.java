package test1.been;

import java.io.Serializable;

public class MyTweetView_Been implements Serializable{
	public MyTweetView_Been() {}

	private String tweetcontent;
	private String tweetdate;

	public void setTweet(String tweetcontent) {
		this.tweetcontent = tweetcontent;
	}

	public String getTweet() {
		return this.tweetcontent;
	}

	public void setTweetdate(String tweetdate) {
		this.tweetdate = tweetdate;
	}

	public String getTweetdate() {
		return this.tweetdate;
	}

}
