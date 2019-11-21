package test1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class FrontServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");

		RequestContext rc = new WebRequestContext();
		rc.setRequest(req);

		AbstractCommand command = CommandFactory.getCommand(rc);

		System.out.println(command);


		System.out.println(rc);

		command.init(rc);

		ResponseContext resc = command.execute();


		Object been = resc.getResult();
		HttpSession session  = req.getSession();

		session.setAttribute("result", been);


		RequestDispatcher dis = req.getRequestDispatcher(resc.getTarget());

		dis.forward(req, res);


        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
