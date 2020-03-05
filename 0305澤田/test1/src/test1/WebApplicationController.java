package test1;

import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test1.been.Login_Been;

public class WebApplicationController implements ApplicationController{

	@Override
	public RequestContext getRequest(Object request) {
		// TODO 自動生成されたメソッド・スタブ
		RequestContext reqc = new WebRequestContext();

		reqc.setRequest(request);

		return reqc;
	}

	@Override
	public ResponseContext handleRequest(RequestContext req) {
		// TODO 自動生成されたメソッド・スタブ
		AbstractCommand command = CommandFactory.getCommand(req);
		command.init(req);

		ResponseContext resc = command.execute(new WebResponseContext());

		return resc;
	}

	@Override
	public void handleResponse(RequestContext reqc, ResponseContext resc) {
		// TODO 自動生成されたメソッド・スタブ

		HttpServletRequest req = (HttpServletRequest)reqc.getRequest();
		HttpServletResponse res = (HttpServletResponse)resc.getResponse();

		Object been = resc.getResult();

		//12/12に澤田が修正した----------------------------------
		Object requestbeen = reqc.getResult();

		if(requestbeen != null) {
			req.setAttribute("requestbeen", requestbeen);
		}
		System.out.println("requestbeenの中身は"+requestbeen);
		//-------------------------------------------------------

		System.out.println("beenの中身は"+been);


		//System.out.println("フラグの中身が見たくて2"+flag.equals(""));

		HttpSession session = req.getSession();
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
			//throw new ServletException("サーバーが一度止まったのでセッションが切れました",new SessionCheckException());
		}
		//サーバーが一度停止したときの例外
		if(flg == null && req.getAttribute("requestbeen") != null) {
			System.out.println("aaaaaaaaaaaaaaaaaaセッションないポイ2");
			session.removeAttribute("token");
			//throw new ServletException("サーバーが一度止まったのでセッションが切れました",new SessionCheckException());
		}

		//Defaultであった req.setAttribute("data", resc.getResult());

		RequestDispatcher rd = req.getRequestDispatcher(resc.getTarget());

		try {
			rd.forward(req, res);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
