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

		int  userId = Integer.parseInt(req.getParameter("userId"));
		int  followedNo = Integer.parseInt(req.getParameter("followedNo"));

		FollowTest.follow(userId, followedNo);

		System.out.print("aaaa");


        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
