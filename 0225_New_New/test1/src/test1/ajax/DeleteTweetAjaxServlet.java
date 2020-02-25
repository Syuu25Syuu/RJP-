package test1.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.DeleteLike;
import test1.db.DeleteRT;
import test1.db.DeleteTweet;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class DeleteTweetAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String tweetID = req.getParameter("tweetNo");

		System.out.println("DeleteAjaxだよ。tweetIDは"+tweetID);
		//follow()の引数に取得したパラメータを入れる
		//取得したパラメータをDBに格納するメソッド(Follows表に)

		DeleteRT.alldDleteRT(tweetID);
		DeleteLike.allDeleteLike(tweetID);
		DeleteTweet.deleteTweet(tweetID);


     }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
