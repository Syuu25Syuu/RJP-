package test1;

import java.io.IOException;
import java.io.Serializable;

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

    	System.out.println("フィルター発動");
        //セッションを獲得
        HttpSession session=((HttpServletRequest) req).getSession();

        //認証トークンを取得
        Serializable flg =(Serializable) session.getAttribute("token");
        //コマンド名取得
        String commandname = req.getServerName();

        if(commandname != "login") {
        	System.out.println("コマンドがログインいがいでした");
        	//認証トークンがあるか判定
            if(flg!=null){
            	 //本来のURLへ
            	System.out.println("----------------------flg"+flg);
            	System.out.println("OK");
                chain.doFilter(req,res);
            }else{
            	System.out.println("セッション切れ");
            	RequestDispatcher disp=req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
                disp.forward(req,res);
            }
        }

    }
}