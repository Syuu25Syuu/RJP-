package test1.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.GetHeader;
import test1.db.OracleConnector;
import test1.db.ShowProfile;
import test1.db.showLikeTweet;


public class ShowProfileServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String sessionToken = req.getParameter("user_session");
		String profUserId = req.getParameter("user_id");
		ArrayList list = new ArrayList();
		ArrayList header = new ArrayList();
		ArrayList likeList = new ArrayList();
		Connection cn = new OracleConnector().getCn();

		try {
		list = ShowProfile.showProfile(sessionToken, profUserId, cn);
		header = GetHeader.getHeader(sessionToken,cn);
		likeList = showLikeTweet.showLikeTweet(sessionToken, profUserId, cn);

		cn.commit();
		cn.close();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				cn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}

		req.setAttribute("result",list);
	    req.setAttribute("requestbeen",header);
	    req.setAttribute("likeList",likeList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profiles.jsp");
		dispatcher.forward(req, res);
     }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }
}
