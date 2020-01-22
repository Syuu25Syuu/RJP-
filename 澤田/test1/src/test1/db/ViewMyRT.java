/*自分がしたRT一覧を表示させる*/

package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.been.MyTweetView_Been;

public class ViewMyRT {
	public static ArrayList viewMyRT(String s_userid) {
		ArrayList list = new ArrayList();

		try{

		Connection cn = new OracleConnector().getCn();

        //自動コミットをOFFにする
        cn.setAutoCommit(false);

        System.out.println("接続完了");

        //SQL文を変数に格納する

        String sql="select RT_TWEET from RT  where RT_USER = '"+s_userid+"'"; //自分がいいねしたツイートのナンバーを取得

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

        	String checkRT = CheckRTUser.checkRTUser(s_userid, t_no);		//そのツイートにＲＴしているかの判定
			String countRT = CountRT.countRT(t_no);	//そのツイートのＲＴ数を表示



			b.setCountRT(countRT);
			b.setCheckRT(checkRT);

        	b.setName(u_name);

        	b.setId(u_id);

        	b.setTweetId(t_no);

        	b.setTweet(t_content);

        	b.setChecklike(checklike);

        	b.setSerialuserid(s_uid);


        	b.setLikecounter(count);

        	list.add(b);




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
