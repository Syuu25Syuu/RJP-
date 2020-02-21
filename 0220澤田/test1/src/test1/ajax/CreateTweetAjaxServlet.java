package test1.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import test1.db.CreateTweet;


@MultipartConfig(location="/tmp", maxFileSize=1048576)
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class CreateTweetAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//パラメータからセッションナンバー(userNo)と
		//フォローされた人のシリアルナンバー(followedNo)を取得
		String sessionToken = req.getParameter("sessionToken");
		String tweet = req.getParameter("tweetID");
		Part part = req.getPart("imageFile");

		System.out.println("paaaaat!!!!  "+part);
		String name = this.getFileName(part);
	    String pathString ="C:/eclipse/pleiades/workspace/test1/WebContent/WEB-INF/uploaded/";

	     String fullPath = pathString+name;

	     part.write(fullPath);

	     CreateTweet.createTweet(sessionToken, tweet, fullPath);

     }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

        private String getFileName(Part part) {
            String name = null;
            for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
                if (dispotion.trim().startsWith("filename")) {
                    name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                    name = name.substring(name.lastIndexOf("\\") + 1);
                    break;
                }
            }
            return name;
        }
}
