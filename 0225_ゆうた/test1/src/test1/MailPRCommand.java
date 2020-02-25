package test1;

import java.sql.Connection;
import java.util.ArrayList;

import test1.db.MailPRTest;
import test1.db.OracleConnector;
import test1.mail.Mail;
import test1.management.PRManagement;
import test1.random.RandomNumber;
/*
 パスワードリマインダーで使うクラス
 乱数を生成し、
 前のページで選択されたユーザのメールに乱数を送信する
 */
public class MailPRCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		String  userid = reqc.getParameter("userid")[0];
		System.out.println(userid);

		ResponseContext resc = new WebResponseContext();

		//ーーーーーーーーーーーDB関係ーーーーーーーーーーーーーーー
		Connection cn = new OracleConnector().getCn();

		ArrayList data = MailPRTest.searchUser(userid, cn);
		String serialNO = (String)data.get(0);
    	String userID = (String)data.get(1);
    	String userMail = (String)data.get(2);
    	//ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー

		//乱数生成
		int randomValue = RandomNumber.createRamdomNumber();
		System.out.println("生成した乱数\t"+randomValue);
		//メール送信
		Mail.mailSend(/*タイトル*/"パスワード変更用メール", /*本文*/"パスワード変更に必要なものです。\n"+randomValue,
				/*送信先*/userMail, /*送信元*/"tarou.y37564@gmail.com", "rjptarou18782");
		/*
		 ユーザIDと生成した乱数を、
		 管理ファイルに書き込む
		 */
		PRManagement.setUserData(userID,randomValue);
		//requestScopeにユーザーIDをセットする
		//次のページのためにリストで送る
		//requestにセットするリスト
		ArrayList requestdata=new ArrayList();
		requestdata.add(userid);
		requestdata.add(0);
		reqc.setResult(requestdata);
		//送信先
		resc.setTarget("numberformPR");

		return resc;
	}
}
