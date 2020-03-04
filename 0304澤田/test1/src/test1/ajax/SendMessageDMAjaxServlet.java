package test1.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.db.SendMessageDMTest;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class SendMessageDMAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//メッセージ内容、送信者・受信者のシリアルナンバーのパラメータを取得
		String content=req.getParameter("content");
		//エスケープ処理
		/*content = content.replaceAll("&amp;","&amp;amp;");
		content = content.replaceAll("&lt;","&amp;lt;");
		content = content.replaceAll("&gt;","&amp;gt;");
		content = content.replaceAll("&quot;","&amp;quot;");
		content = content.replaceAll("&apos;","&amp;apos;");
		content = content.replaceAll("\"","&quot;");
		content = content.replaceAll("'","&apos;");
		content = content.replaceAll("<","&lt;");
		content = content.replaceAll(">","&gt;");
		content = content.replaceAll( "\n","<br>");*/
		String sendUserNo = req.getParameter("sendUser");
		String receiveUserNo = req.getParameter("receiveUser");

		//DBに接続してinsertを実行するメソッドに
		//取得した値を引数にセット
		SendMessageDMTest.sendMessage(content,sendUserNo, receiveUserNo);

        }
        public void doGet(HttpServletRequest req,HttpServletResponse res)
        throws ServletException,IOException{
        	doPost(req,res);
        }

}
