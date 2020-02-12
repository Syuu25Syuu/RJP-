package test1.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.been.SerchBean;
import test1.db.AdditionDataLoadDMTest;


//@WebServlet("/AjaxServlet")
//フロントサーブレットではなくweb.xmlに直接urlを記入している
//フォローをするクラス
public class AdditionDataLoadDMAjaxServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		//文字コード指定
		req.setCharacterEncoding("UTF-8");
		//requestにセットするリスト
		ArrayList data=new ArrayList();
		//最後のメッセージの時間、送信者・受信者のシリアルナンバーのパラメータを取得
		String dmTime=req.getParameter("lastMessageDMTime");
		String sendUserNo = req.getParameter("sendUser");
		String receiveUserNo = req.getParameter("receiveUser");

		//DBに接続してselectを実行するメソッドに
		//取得した値を引数にセット
		ArrayList additiondata=AdditionDataLoadDMTest.getDMContent(sendUserNo, receiveUserNo, dmTime);
		System.out.println("更新データ取得完了");

		Iterator iterator=additiondata.iterator();
		while (iterator.hasNext()) {
			//一時的にDBからのデータを格納するリスト
			//Iteratorを使い一時的にDBのデータをリストに格納
			ArrayList datalist = (ArrayList) iterator.next();

			//Beanをインスタンス化する
			SerchBean sb=new SerchBean();
			//BeanにDBから取得した値を格納
			String id=(String)datalist.get(0);
			String name=(String)datalist.get(1);
			String dmcontent=(String)datalist.get(2);
			String senduserno=(String)datalist.get(3);
			String receiveuserno=(String)datalist.get(4);
			String dmtime=(String)datalist.get(5);
			String icon=(String)datalist.get(6);

			sb.setUserId(id);
			sb.setUserName(name);
			sb.setDmContent(dmcontent);
			sb.setSendUserNo(senduserno);
			sb.setReceiveUserNo(receiveuserno);
			sb.setDmTime(dmtime);
			sb.setUserIcon(icon);
			//最終的にセッションにセットするリストにBeanを格納する
			data.add(sb);
		}
		req.setAttribute("additiondata", data);
		RequestDispatcher rd=req.getRequestDispatcher("/chat.jsp");
		rd.forward(req, res);
	}
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException{
    	doPost(req,res);
    }

}
