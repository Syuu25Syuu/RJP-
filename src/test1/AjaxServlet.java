package test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.FollowTest;


//@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");

		int  userNo = Integer.parseInt(req.getParameter("userNo"));
		int  followedNo = Integer.parseInt(req.getParameter("followedNo"));

		FollowTest.follow(userNo, followedNo);

		System.out.print("フォローしました");


        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
