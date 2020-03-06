package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import test1.exception.IntegrationException;

public class ViewLike {

	public static ArrayList viewLike(String user_id) throws IntegrationException{
		ArrayList list = new ArrayList();
		try {
		Connection cn = new OracleConnector().getCn();



        //自動コミットをOFFにする
        cn.setAutoCommit(false);

        System.out.println("接続完了");

        //SQL文を変数に格納する
        //System.out.println("idを表示するよ"+id);
        String sql="select LIKES_TWEET from likes where Likes_User = '"+user_id+"' order by Likes_Time desc ";

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
        	list.add((String)rs.getString(1));
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
        	throw new IntegrationException(e);
        }
		return list;

	}
	public static ArrayList viewTweet(String tweetid) throws IntegrationException{

		ArrayList list = new ArrayList();
		try {
		Connection cn = new OracleConnector().getCn();



        //自動コミットをOFFにする
        cn.setAutoCommit(false);

        System.out.println("接続完了");

        //SQL文を変数に格納する

        String sql="select TWEET_NO TWEET_CONTENT from Tweets where TWEET_NO = '"+tweetid+"' order by TWEET_DATE desc ";

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
        	list.add((String)rs.getString(1));
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
        	throw new IntegrationException(e);
        }
		return list;

	}
}
