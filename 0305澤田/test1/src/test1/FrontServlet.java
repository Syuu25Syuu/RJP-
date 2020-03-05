package test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig(location="/tmp", maxFileSize=58576000)
public class FrontServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		try {
			req.setCharacterEncoding("UTF-8");

			ApplicationController app = new WebApplicationController();

			RequestContext reqc = app.getRequest(req);

			ResponseContext resc = app.handleRequest(reqc);

			resc.setResponse(res);

			app.handleResponse(reqc, resc);

			///HttpSession session  = req.getSession();


			///RequestContext rc = new WebRequestContext();

			/*
			if(session!=null) {
				rc.setSession(session);
				System.out.println("sessionもってるよ。");
			}else {
				System.out.println("sessionもってないよ");
			}

			*/


			///rc.setRequest(req);

			///AbstractCommand command = CommandFactory.getCommand(rc);

			///System.out.println("コマンドの中身"+command);


			///System.out.println("rcの中身"+rc);

			//command.init(rc);

			//ResponseContext resc = command.execute();

			//System.out.println("rescの中身"+resc);


			//

			///RequestDispatcher dis = req.getRequestDispatcher(resc.getTarget());

			///dis.forward(req, res);
		}catch (NullPointerException e) {
			//ブラウザを閉じたときの例外
			System.out.println("aaaaaaaaaaaaaaaaaaNullpointer");
			e.printStackTrace();
			//throw new ServletException("ブラウザを閉じたのでセッションが切れました",new SessionCheckException());
		}

    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    	doPost(req,res);
    }

}
