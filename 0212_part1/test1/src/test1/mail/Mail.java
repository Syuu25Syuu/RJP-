package test1.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class Mail {
	public static void main(String[] argv) {
		mailSend(/*タイトル*/"タイトル：テスト(さわだから)", /*本文*/"テストメールだよ。 172.19.4.85:8080/test1/",
				/*送信先*/"tarou.y37564@gmail.com", /*送信元*/"tarou.y37564@gmail.com", /*送信元パスワード*/"rjptarou18782");
	}
	//メールを送信するメソッド
	//引数は上記のメインメソッドの中身に書いてる感じ
	//第一引数から順番に↓↓↓↓
	//タイトル、本文、送信先、送信元、送信元パスワード
	public static void mailSend(String title, String text, String toAddress,
		String fromAddress, String password) {

		try {
			// プロパティの設定
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);

			// 送信元メールアドレスのセット
			msg.setFrom(new InternetAddress(fromAddress));

			// 送信先メールアドレスのセット
			msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress, false));

			// メールタイトル
			msg.setSubject(title);

			// メール本文
			msg.setText(text);

			// 送信日
			msg.setSentDate(new Date());

			// メール送信
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			try {
				t.connect("smtp.gmail.com", fromAddress, password);
				t.sendMessage(msg, msg.getAllRecipients());
			} finally {
				t.close();
			}
			System.out.println("メール送信完了");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("例外により送信失敗");
		}
	}
}