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
	private String checklike;
	private String checkFollow;
	private String countRT;
	private String countLike;
	private String countReply;
	private String tweetImageNone;
	private String tabCheck_tweet;
	private String tabCheck_image;
	private String tabCheck_like;
	private String profChangeId;	//profでつかうよ
	private String nullPo;	//要素を表示にしたいときに使うよ

	private String notnullPo;

	private int notifyCount;

	public String getTabCheck_tweet() {
		return tabCheck_tweet;
	}
	public void setTabCheck_tweet(String tabCheck_tweet) {
		this.tabCheck_tweet = tabCheck_tweet;
	}
	public String getTabCheck_image() {
		return tabCheck_image;
	}
	public void setTabCheck_image(String tabCheck_image) {
		this.tabCheck_image = tabCheck_image;
	}
	public String getTabCheck_like() {
		return tabCheck_like;
	}
	public void setTabCheck_like(String tabCheck_like) {
		this.tabCheck_like = tabCheck_like;
	}
	public String getCountLike() {
		return countLike;
	}
	public void setCountLike(String countLike) {
		this.countLike = countLike;
	}

	private String rtuser;

	private String profUserName;

	private String profUserId;

	private String countFollowers;

	private String countFollows;

	private String profile;

	private String followbtn;

	private String profImage;

	private String icon;

	private String mytweetCheck;

	private String tweetImg;

	public String getCountFollowers() {
		return countFollowers;
	}
	public void setCountFollowers(String countFollowers) {
		this.countFollowers = countFollowers;
	}
	public String getCountFollows() {
		return countFollows;
	}
	public void setCountFollows(String countFollows) {
		this.countFollows = countFollows;
	}
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
	public String getProfUserName() {
		return profUserName;
	}
	public void setProfUserName(String profUserName) {
		this.profUserName = profUserName;
	}
	public String getProfUserId() {
		return profUserId;
	}
	public void setProfUserId(String profUserId) {
		this.profUserId = profUserId;
	}
	public String getCheckFollow() {
		return checkFollow;
	}
	public void setCheckFollow(String checkFollow) {
		this.checkFollow = checkFollow;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getFollowbtn() {
		return followbtn;
	}
	public void setFollowbtn(String followbtn) {
		this.followbtn = followbtn;
	}
	public String getProfImage() {
		return profImage;
	}
	public void setProfImage(String profImage) {
		this.profImage = profImage;
	}
	public String getTweetdate() {
		return tweetdate;
	}
	public void setTweetdate(String tweetdate) {
		this.tweetdate = tweetdate;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMytweetCheck() {
		return mytweetCheck;
	}
	public void setMytweetCheck(String mytweetCheck) {
		this.mytweetCheck = mytweetCheck;
	}
	public String getCountReply() {
		return countReply;
	}
	public void setCountReply(String countReply) {
		this.countReply = countReply;
	}
	public String getTweetImg() {
		return tweetImg;
	}
	public void setTweetImg(String tweetImg) {
		this.tweetImg = tweetImg;
	}
	public String getTweetImageNone() {
		return tweetImageNone;
	}
	public void setTweetImageNone(String tweetImageNone) {
		this.tweetImageNone = tweetImageNone;
	}
	public String getProfChangeId() {
		return profChangeId;
	}
	public void setProfChangeId(String profChangeId) {
		this.profChangeId = profChangeId;
	}
	public String getNullPo() {
		return nullPo;
	}
	public void setNullPo(String nullPo) {
		this.nullPo = nullPo;
	}
	public String getNotnullPo() {
		return notnullPo;
	}
	public void setNotnullPo(String notnullPo) {
		this.notnullPo = notnullPo;
	}
	public int getNotifyCount() {
		return notifyCount;
	}
	public void setNotifyCount(int notifyCount) {
		this.notifyCount = notifyCount;
	}
}
