package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class ShowProfileImage {
	public static ArrayList<MyTweetView_Been> showProfile(String sessionToken,String profUser,Connection cn){
		ArrayList<MyTweetView_Been> list = new ArrayList<MyTweetView_Been>();
		String myName = GetUsersName.getUserName(sessionToken, cn);
        String profName = GetUsersName.getUserName(profUser, cn);	//プロフィールのユーザー名
        String profId = GetUsersId.getUserId(profUser, cn);	//プロフィールのユーザーID
        String prof = GetProfile.getProfile(profUser, cn);
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

            String sql="select USERS_SERIALNO,USERS_NAME,Users_id,tweets.TWEETS_SERIALNO,tweets.TWEETS_CONTENT,tweets.TWEETS_DATE,tweets.TWEETS_IMAGE from USERS " +
            		"join tweets on USERS_SERIALNO = tweets.USERS_NO "+
            		"where TWEETS_IMAGE IS NOT NULL "+
            		"and tweets.USERS_NO = '"+profUser+"' " +
            		"order by TWEETS_DATE";

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
            	String tweets_no = rs.getString(4);
            	String tweets_content = rs.getString(5);
            	String tweetDate = rs.getString(6);
            	String tweetImg = rs.getString(7);

            	String checklike = CheckLikeUser.checkLikeUser(sessionToken, tweets_no, cn);
            	String checkRT = CheckRTUser.checkRTUser(sessionToken, tweets_no, cn);
            	String icon = GetUSERS_PROF_IMAGE.getProfile(users_serialno, cn);

            	String imageNone="";

            	//System.out.println("みてね"+tweetImg);

            	if(tweetImg == null) {
            		imageNone ="NonImage";
            	}

            	bean.setName(users_name);
            	bean.setId(users_id);
            	bean.setSerialuserid(users_serialno);
            	bean.setTweetId(tweets_no);
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
            	bean.setTabCheck_tweet("");
            	bean.setTabCheck_image("checked");
            	bean.setTabCheck_like("");
            	bean.setProfChangeId(profUser);

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
