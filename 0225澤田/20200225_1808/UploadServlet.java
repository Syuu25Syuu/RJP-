package test1.file;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import test1.db.ComebackHome;
import test1.db.CreateTweet;
import test1.db.GetHeader;
import test1.db.OracleConnector;

@WebServlet("/UploadServlet")
@MultipartConfig(location="/tmp", maxFileSize=58576000)
public class UploadServlet extends HttpServlet {
	String sessionToken="";
    String tweet="";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {

    	System.out.println(req.getContextPath());

    	try {

	    	req.setCharacterEncoding("UTF-8");
	    	sessionToken = req.getParameter("sessionToken");	//sessionTokenを取得
			tweet = req.getParameter("contents");	//ツイートの内容を取得
			Part part = req.getPart("inputFile");	//投稿された画像を取得

			//02/25澤田追加
			tweet = tweet.replaceAll("&amp;","&amp;amp;");
			tweet = tweet.replaceAll("&lt;","&amp;lt;");
			tweet = tweet.replaceAll("&gt;","&amp;gt;");
			tweet = tweet.replaceAll("&quot;","&amp;quot;");
			tweet = tweet.replaceAll("&apos;","&amp;apos;");
			tweet = tweet.replaceAll("\"","&quot;");
			tweet = tweet.replaceAll("'","&apos;");
			tweet = tweet.replaceAll("<","&lt;");
			tweet = tweet.replaceAll(">","&gt;");
			tweet = tweet.replaceAll( "\n","<br>");

			String name="";
			String newName = "";

			String fullPath="";		//画像を保存するパスを指定する変数

			 String writePath ="";

			System.out.println(part.getSize());

			if(part.getSize()>0) {		//ツイートに画像が含まれるか判定
				 name = this.getFileName(part);	//画像の名前を取得

				 System.out.println("画像の名前さんだよ！！！"+name);

				 newName = ChangeImageName.changeImageName(name);

				 String pathString ="C:/pleiades/workspace/test1/WebContent/images/uploaded/";

				 //String pathString ="C:/eclipse/pleiades/workspace/test1/image";

				 fullPath = pathString+newName;

				 part.write(fullPath);		//画像を書き込む

				 writePath = "/test1/images/uploaded/"+newName;

			}else {

			}

			System.out.println(sessionToken+tweet+part);



			//String writePath = "../iamge/"+newName;

		    CreateTweet.createTweet(sessionToken, tweet, writePath);

		    Connection cn = new OracleConnector().getCn();
		    ArrayList list = ComebackHome.comeBackHome(sessionToken, cn);
		    ArrayList header = GetHeader.getHeader(sessionToken, cn);		//ヘッダー用
		    req.setAttribute("result",list);
		    req.setAttribute("requestbeen",header);

		    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		    dispatcher.forward(req, res);

    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }

/*

    	}catch (ServletException e) {
    		e.printStackTrace();
    		HttpSession session  = req.getSession();
			sessionToken = (String)session.getAttribute("token");
			System.out.println("エラー用"+sessionToken);
		}catch(IOException e) {
			e.printStackTrace();
			HttpSession session  = req.getSession();
			sessionToken = (String)session.getAttribute("token");
			System.out.println("エラー用"+sessionToken);
		}catch (IllegalStateException e) {
			//e.printStackTrace();
			HttpSession session  = req.getSession();
			sessionToken = (String)session.getAttribute("token");
			System.out.println("エラー用"+sessionToken);
		}catch (Exception e) {
			e.printStackTrace();
			HttpSession session  = req.getSession();
			sessionToken = (String)session.getAttribute("token");
			System.out.println("エラー用"+sessionToken);
		}
    	finally {
    		if(sessionToken.equals("")) {
    			HttpSession session  = req.getSession();
    			sessionToken = (String)session.getAttribute("token");
    		}
			 Connection cn = new OracleConnector().getCn();
		     ArrayList list = ComebackHome.comeBackHome(sessionToken, cn);
		     ArrayList header = GetHeader.getHeader(sessionToken, cn);		//ヘッダー用

		     req.setAttribute("result",list);
		     req.setAttribute("requestbeen",header);
		     try {
		    	 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
			     dispatcher.forward(req, res);
		     }catch(ServletException e){
		    	 e.printStackTrace();
		     }catch (IOException e) {
				e.printStackTrace();
			 }catch (NullPointerException e) {
				Connection cns = new OracleConnector().getCn();
				HttpSession session  = req.getSession();
    			String sessionTokens = (String)session.getAttribute("token");

    			ArrayList lists = ComebackHome.comeBackHome(sessionTokens, cns);
    			ArrayList headers = GetHeader.getHeader(sessionTokens, cns);		//ヘッダー用

   		     	req.setAttribute("result",lists);
   		     	req.setAttribute("requestbeen",headers);
   		     	try {
   		     		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
   		     		dispatcher.forward(req, res);
   		     	}catch(ServletException e2){
   		     		e2.printStackTrace();
   		     	}catch (IOException e2) {
   		     		e2.printStackTrace();
   		     	}catch (Exception e2) {
					e.printStackTrace();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}

		}

    }
    */

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                System.out.println("1段階"+name);
                name = name.substring(name.lastIndexOf("\\") + 1);
                System.out.println("2段階"+name);
                break;
            }
        }
        return name;
    }
}