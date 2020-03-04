package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import test1.been.MyTweetView_Been;

public class showLikeTweet {
	public static ArrayList showLikeTweet(String sessionToken,String profUser,Connection cn) {
		ArrayList list = new ArrayList();
		String myName = GetUsersName.getUserName(sessionToken, cn);
        String profName = GetUsersName.getUserName(profUser, cn);	//プロフィールのユーザー名
        String profId = GetUsersId.getUserId(profUser, cn);	//プロフィールのユーザーID
        String prof = GetProfile.getProfile(profUser, cn);
        //エスケープ処理
		prof = prof.replaceAll("&amp;","&amp;amp;");
		prof = prof.replaceAll("&lt;","&amp;lt;");
		prof = prof.replaceAll("&gt;","&amp;gt;");
		prof = prof.replaceAll("&quot;","&amp;quot;");
		prof = prof.replaceAll("&apos;","&amp;apos;");
		prof = prof.replaceAll("&nbsp;","&amp;nbsp;");
		prof = prof.replaceAll("\"","&quot;");
		prof = prof.replaceAll("'","&apos;");
		prof = prof.replaceAll("<","&lt;");
		prof = prof.replaceAll(">","&gt;");
		prof = prof.replaceAll( "\n","<br>");
        String checkfollow = CheckFollow.checkFollow(sessionToken, profUser, cn);
        System.out.println("checkfollowだよ"+checkfollow);
        String flgfollowbtn ="followbtn";		//プロフィールのユーザーと自分が同一化判定する

        String profImage = GetUSERS_PROF_IMAGE.getProfile(profUser, cn);
        String countFollowers = CountFollowers.countFollowers(profUser, cn);

    	String countFollows = CountFollows.countFollows(profUser, cn);

        if(sessionToken.equals(profUser)) {
        	flgfollowbtn = "changeProf";
        }


		try{

        String sql="select USERS_SERIALNO,USERS_NAME,Users_id,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT,tweets.TWEETS_DATE,tweets.TWEETS_IMAGE from USERS join tweets on USERS_SERIALNO = tweets.USERS_NO JOIN LIKES on LIKES.LIKES_TWEET = tweets.TWEETS_SERIALNO where (tweets.TWEETS_SERIALNO) IN (select likes_tweet from likes  where Likes_User = '"+profUser+"')order by LIKES.LIKES_TIME desc"; //そのユーザーがいいねしたツイートのナンバーを取得

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        boolean flg = true;

        while(rs.next()){

    		System.out.println("いる");
    		flg = false;


        	MyTweetView_Been bean = new MyTweetView_Been();
        	String users_serialno = rs.getString(1);
        	String users_name = rs.getString(2);
        	String users_id = rs.getString(3);
        	String tweetSerialNo = rs.getString(4);
        	String tweets_content = rs.getString(5);
        	//エスケープ処理
    		tweets_content = tweets_content.replaceAll("&amp;","&amp;amp;");
    		tweets_content = tweets_content.replaceAll("&lt;","&amp;lt;");
    		tweets_content = tweets_content.replaceAll("&gt;","&amp;gt;");
    		tweets_content = tweets_content.replaceAll("&quot;","&amp;quot;");
    		tweets_content = tweets_content.replaceAll("&apos;","&amp;apos;");
    		tweets_content = tweets_content.replaceAll("&nbsp;","&amp;nbsp;");
    		tweets_content = tweets_content.replaceAll("\"","&quot;");
    		tweets_content = tweets_content.replaceAll("'","&apos;");
    		tweets_content = tweets_content.replaceAll("<","&lt;");
    		tweets_content = tweets_content.replaceAll(">","&gt;");
    		tweets_content = tweets_content.replaceAll( "\n","<br>");
        	String tweetDate = rs.getString(6);
        	String tweetImg = rs.getString(7);

        	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweetSerialNo, cn);
        	String checkRT = CheckRTUser.checkRTUser(sessionToken, tweetSerialNo, cn);
        	String icon = GetUSERS_PROF_IMAGE.getProfile(users_serialno, cn);

        	/*いいね数・RT数・リプライ数を表示させる*/
        	String likeCount =CountLikeTweet.countLikeTweet(tweetSerialNo, cn);
        	String RTCount = CountRT.countRT(tweetSerialNo, cn);
        	String replyCount = CountReply.countReply(tweetSerialNo, cn);

        	String imageNone="";

        	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date DateTweet = sdFormat.parse(tweetDate);
            tweetDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(DateTweet);

        	//System.out.println("みてね"+tweetImg);

        	if(tweetImg == null) {
        		imageNone ="NonImage";
        	}

        	String mytweetCheck = CheckMyTweet.checkMyTweet(sessionToken, tweetSerialNo, cn);
        	bean.setMytweetCheck(mytweetCheck);

        	bean.setName(users_name);
        	bean.setId(users_id);
        	bean.setSerialuserid(users_serialno);
        	bean.setTweetId(tweetSerialNo);
        	bean.setTweet(tweets_content);
        	bean.setChecklike(checklike);
        	bean.setCheckRT(checkRT);
        	bean.setProfImage(icon);
        	bean.setTweetImg(tweetImg);
        	bean.setTweetdate(tweetDate);
        	bean.setIcon(icon);
        	bean.setTweetImageNone(imageNone);

        	bean.setProfUserName(profName);
        	bean.setCountFollowers(countFollowers);
        	bean.setCountFollows(countFollows);
        	bean.setProfUserId(profId);
        	bean.setProfile(prof);
        	bean.setCheckFollow(checkfollow);
        	bean.setFollowbtn(flgfollowbtn);
        	bean.setProfImage(profImage);

        	bean.setCountRT(RTCount);
        	bean.setCountLike(likeCount);
        	bean.setCountReply(replyCount);

        	bean.setTabCheck_tweet("");
        	bean.setTabCheck_image("");
        	bean.setTabCheck_like("checked");
        	bean.setProfChangeId(profUser);
        	bean.setNullPo("");
        	bean.setNotnullPo("NonImage");

        	list.add(bean);


         }

        if(flg == true) {
        	MyTweetView_Been bean = new MyTweetView_Been();
        	bean.setProfUserName(profName);
        	bean.setCountFollowers(countFollowers);
        	bean.setCountFollows(countFollows);
        	bean.setProfUserId(profId);
        	bean.setProfile(prof);
        	bean.setCheckFollow(checkfollow);
        	bean.setFollowbtn(flgfollowbtn);
        	bean.setProfImage(profImage);
        	bean.setTabCheck_tweet("");
        	bean.setTabCheck_image("");
        	bean.setTabCheck_like("checked");
        	bean.setProfChangeId(profUser);
        	bean.setNullPo("NonImage");
        	bean.setNotnullPo("");

        	list.add(bean);
        }




        }catch(SQLException e){
        	e.printStackTrace();

        }catch(Exception e) {
        	e.printStackTrace();
        }



		return list;
	}
}
