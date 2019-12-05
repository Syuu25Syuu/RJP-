package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class ViewMyLike {
	public static ArrayList viewMyLike(String s_userid) {
		ArrayList list = new ArrayList();

		try{

		Connection cn = new OracleConnector().getCn();

        //自動コミットをOFFにする
        cn.setAutoCommit(false);

        System.out.println("接続完了");

        //SQL文を変数に格納する

        String sql="select likes_tweet from likes  where Likes_User = '"+s_userid+"'"; //自分がいいねしたツイートのナンバーを取得

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
        	String t_no = rs.getString(1);

        	String s_uid = GetUserNo_fromTweet.GetUserNo(t_no);	//自分がいいねした人のシリアルナンバーを取得

        	System.out.println("ツイート主のシリアルナンバー"+s_uid);

        	String u_name = GetUsersName.getUserName(s_uid);


        	String u_id = GetUsersId.getUserId(s_uid);	//いいねをした人のID

        	String t_content = GetTweetContent.getTweetContent(t_no);	//ツイート内容

        	String checklike = CheckLikeUser.checkLikeUser(s_userid, t_no);

        	String count = CountLikeTweet.countLikeTweet(t_no);

        	MyTweetView_Been b = new MyTweetView_Been();

        	b.setName(u_name);

        	b.setId(u_id);

        	b.setTweetId(t_no);

        	b.setTweet(t_content);

        	b.setChecklike(checklike);

        	b.setSerialuserid(s_uid);


        	b.setLikecounter(count);

        	list.add(b);



        	/*
        	String sql2 = "select users_name,users_id,TWEETS_SERIALNO,TWEETS_CONTENT from users,tweets where TWEETS_SERIALNO = '"+t_no+"'";

        	 Statement st2= cn.createStatement();

             ResultSet rs2 = st2.executeQuery(sql2);

             while(rs2.next()) {
            	MyTweetView_Been b = new MyTweetView_Been();

            	b.setName(rs2.getString(1));

            	b.setId(rs2.getString(2));

            	b.setTweetId(rs2.getString(3));

            	b.setTweet(rs2.getString(4));

            	String checklike = CheckLikeUser.checkLikeUser(s_userid, rs2.getString(3));

            	b.setChecklike(checklike);

            	String count = CountLikeTweet.countLikeTweet(t_no);

            	b.setLikecounter(count);

            	list.add(b);

             }
             */


         }

        //トランザクションをコミットする
        cn.commit();

        //ステートメントをクローズする
        st.close();

        //RDBMSから切断する
        cn.close();

        System.out.println("切断完了");


        }catch(SQLException e){
        	e.printStackTrace();

        }catch(Exception e) {
        	e.printStackTrace();
        }



		return list;
	}
}
