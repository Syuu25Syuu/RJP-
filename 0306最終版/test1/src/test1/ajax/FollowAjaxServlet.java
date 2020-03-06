package test1.ajax;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.CheckFollow;
import test1.db.FollowCancelTest;
import test1.db.FollowTest;
import test1.db.GetUSERS_PROF_IMAGE;
import test1.db.GetUsersName;
import test1.db.OracleConnector;
import test1.json.NotifyJsonFileWriter;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class FollowAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String sessionToken = req.getParameter("userNo");
		String  followedNo = req.getParameter("followedNo");


		//follow()の引数に取得したパラメータを入れる
		//取得したパラメータをDBに格納するメソッド(Follows表に)

		String flgString = CheckFollow.checkFollow(sessionToken, followedNo);

		if(flgString.equals("checked")) {
			FollowCancelTest.cancelFollow(sessionToken, followedNo);
			System.out.println("フォローを取り消したよ");

		}else {
			FollowTest.follow(sessionToken, followedNo);
			System.out.println("フォローしたお");
			//02/24澤田追加--------------------------------------------------------
			Connection cn = new OracleConnector().getCn();
			String name = GetUsersName.getUserName(sessionToken, cn);
			String img_path = GetUSERS_PROF_IMAGE.getProfile(sessionToken, cn);
			//通知データに追加する
			NotifyJsonFileWriter.setJsonData(followedNo, sessionToken, name, img_path, "showprofiles", "フォロー", "");
			//---------------------------------------------------------------------
		}




        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
