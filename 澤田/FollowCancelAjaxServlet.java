package test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.FollowCancelTest;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローを解除するクラス
public class FollowCancelAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		int  userNo = Integer.parseInt(req.getParameter("userNo"));
		int  followedNo = Integer.parseInt(req.getParameter("followedNo"));
		//cancelFollow()の引数に取得したパラメータを入れる
		//引数で指定されたフォロー情報をDBから削除する(Follows表から)
		//フォローを解除するメソッド
		FollowCancelTest.cancelFollow(userNo, followedNo);

		System.out.print("フォロー解除しました");


        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
