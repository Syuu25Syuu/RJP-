package test1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test1.db.CreateAccount;

public class CreateAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
                req.setCharacterEncoding("UTF-8");

                String name=req.getParameter("name");
				String id=req.getParameter("id");
				String pass=req.getParameter("pass");
				String mail=req.getParameter("mailadd");

				String flg = CreateAccount.createAcount(name,id,pass,mail); //return password


				HttpSession session = req.getSession();
				session.setAttribute("flg",flg);

				if(flg.equals("アカウントの作成に成功しました")) {
					System.out.println(flg);
					RequestDispatcher rd = req.getRequestDispatcher("/logins");
	                rd.forward(req,res);
				}else {
					System.out.println(flg);
					RequestDispatcher rd = req.getRequestDispatcher("/createaccount");
	                rd.forward(req,res);
				}

            }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }
    }

