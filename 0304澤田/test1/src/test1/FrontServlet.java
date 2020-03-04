package test1;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test1.been.Login_Been;
import test1.exception.SessionCheckException;



public class FrontServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		try {
			req.setCharacterEncoding("UTF-8");

			HttpSession session  = req.getSession();


			RequestContext rc = new WebRequestContext();

			/*
			if(session!=null) {
				rc.setSession(session);
				System.out.println("sessionもってるよ。");
			}else {
				System.out.println("sessionもってないよ");
			}

			*/


			rc.setRequest(req);

			AbstractCommand command = CommandFactory.getCommand(rc);

			System.out.println("コマンドの中身"+command);


			System.out.println("rcの中身"+rc);

			command.init(rc);

			ResponseContext resc = command.execute();

			System.out.println("rescの中身"+resc);


			Object been = resc.getResult();

			//12/12に澤田が修正した----------------------------------
			Object requestbeen = rc.getResult();

			if(requestbeen != null) {
				req.setAttribute("requestbeen", requestbeen);
			}
			System.out.println("requestbeenの中身は"+requestbeen);
			//-------------------------------------------------------

			System.out.println("beenの中身は"+been);


			//System.out.println("フラグの中身が見たくて2"+flag.equals(""));

			Serializable flg =(Serializable) session.getAttribute("token");		//sessionScopeの「token」をSerializable型でget

			Login_Been aBeen = new Login_Been();

			System.out.println("aBennの中身"+aBeen);

			System.out.println("flgの中身"+flg);


			if(flg == null){
				session.setAttribute("token", been);	//flgがnullならsessionTokenを発行
				System.out.println("flgはNULLだったよ！");
			}else if(been != null){		//Beanが存在した場合
				if(flg.getClass() == been.getClass()) {
					session.setAttribute("token", been);	//sessionTokenを発行
					System.out.println("flgは同じだったよね");
				}else {
					req.setAttribute("result",been);	//sessionTokenが存在するのでrequestScopeでresultを発行
					System.out.println("beenの中身はnullだったよ");
				}
			}else{
				req.setAttribute("result",been);	//sessionTokenが存在するのでrequestScopeでresultを発行
				System.out.println("flgが違ったよ");
			}


			//サーバーが一度停止したときの例外
			if(flg == null && req.getAttribute("result") != null) {
				System.out.println("aaaaaaaaaaaaaaaaaaセッションないポイ1");
				session.removeAttribute("token");
				throw new ServletException("サーバーが一度止まったのでセッションが切れました",new SessionCheckException());
			}
			//サーバーが一度停止したときの例外
			if(flg == null && req.getAttribute("requestbeen") != null) {
				System.out.println("aaaaaaaaaaaaaaaaaaセッションないポイ2");
				session.removeAttribute("token");
				throw new ServletException("サーバーが一度止まったのでセッションが切れました",new SessionCheckException());
			}

			RequestDispatcher dis = req.getRequestDispatcher(resc.getTarget());

			dis.forward(req, res);
		}catch (NullPointerException e) {
			//ブラウザを閉じたときの例外
			System.out.println("aaaaaaaaaaaaaaaaaaNullpointer");
			throw new ServletException("ブラウザを閉じたのでセッションが切れました",new SessionCheckException());
		}

    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    	doPost(req,res);
    }

}
