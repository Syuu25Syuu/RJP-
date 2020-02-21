package test1.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.CheckRTUser;
import test1.db.CreateRT;
import test1.db.DeleteRT;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class RTAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String sessionToken = req.getParameter("sessionToken");
		String tweetID = req.getParameter("tweetID");

		tweetID = tweetID.replace("rt","");

		System.out.println("sessionTokenは"+sessionToken);
		System.out.println("tweetIDは"+tweetID);
		//follow()の引数に取得したパラメータを入れる
		//取得したパラメータをDBに格納するメソッド(Follows表に)

		String flgString = CheckRTUser.checkRTUser(sessionToken,tweetID);	//ここの戻り値はcheckもしくは空白
		System.out.println("flgStringの中身は"+flgString);
		if(flgString.equals("checked")) {
			DeleteRT.deleteRT(tweetID,sessionToken);
			System.out.println("RTをとりけしたよ！");

		}else {
			CreateRT.createRT(sessionToken,tweetID);
			System.out.println("RTしたよ！");
		}

     }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
