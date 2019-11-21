package test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test1.db.CreateTweet;
import test1.db.ViewMyName_And_ID;
import test1.db.ViewMy_Tweet;

public class CreateTweetServlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
                req.setCharacterEncoding("UTF-8");

                HttpSession session = req.getSession();



               String s_userid = (String)session.getAttribute("logintoken");
               String tweet = req.getParameter("contents");


				CreateTweet.createTweet(s_userid,tweet); //return password

				HashMap map = ViewMyName_And_ID.viewMyName_And_ID(s_userid);

				String id =(String) map.get("id");
				String user_name =(String) map.get("name");

				ArrayList list = ViewMy_Tweet.viewMy_Tweet(s_userid);

				System.out.println("idさんは"+id+"だよ！！！");

				session.setAttribute("id", id);

				session.setAttribute("user_name", user_name);

				session.setAttribute("tweet", list);

				RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
	            rd.forward(req,res);


            }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }
    }

