package test1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AuthenticateFilter implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{

        //セッションを獲得
        HttpSession session=((HttpServletRequest) req).getSession();

        //認証トークンを取得
        String flag=(String)session.getAttribute("token");

        //認証トークンがあるか判定
        if(flag!=null){
        	 //本来のURLへ
        	System.out.println("OK");
            chain.doFilter(req,res);
        }else{
        	RequestDispatcher disp=req.getRequestDispatcher("login.jsp");
            disp.forward(req,res);
        }
    }
}