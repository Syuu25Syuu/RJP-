package test1.ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.OracleConnector;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class LikeCountAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		//String sessionToken = req.getParameter("sessionToken");
		String tweetID = req.getParameter("tweetID");

		   String sql = "select count(likes_tweet) from likes where likes_tweet='"+tweetID+"'";

	        //Statementインターフェイスを実装するクラスの
	        //インスタンスを取得する
		   Connection cn = new OracleConnector().getCn();

	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        while(rs.next()){
	        	counter= rs.getString(1);
	        	//System.out.println("counterの数は"+counter);
	         }

     }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
