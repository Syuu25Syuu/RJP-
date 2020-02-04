package test1;

import java.util.ArrayList;

import test1.management.PRManagement;
/*
 パスワードリマインダーで使うクラス
 入力された数字があっているか確認
 二段階認証時に使う
 */
public class KeyConfirmationPRCommand extends AbstractCommand{
	public ResponseContext execute() {
		RequestContext reqc = getRequestContext();

		//ユーザーIDはrequestScopeで前のページから引き継がれている
		String userid=reqc.getParameter("userid")[0];
		//入力された数字
		int keynumber =Integer.parseInt(reqc.getParameter("key")[0]);

		ResponseContext resc = new WebResponseContext();
		//ユーザーIDに対応するListを取得する
		ArrayList userdata=(ArrayList)PRManagement.getUserData(userid);

		//ユーザーIDがnullだった場合
		if(userdata==null) {
			System.out.println("存在しないユーザーが選択されました");
			//送信先(ユーザー検索ページ)
			resc.setTarget("serchuserPR");
		}
		//入力された数字があっていた場合
		else if((int)userdata.get(0)==keynumber) {
			//かつ入力回数が3回以下の場合
			if((int)userdata.get(1) < 3) {
				System.out.println("入力されたキーナンバーがあっていました");
				PRManagement.deleteUserData(userid);
				//requestScopeにユーザーIDをセットする
				reqc.setResult(userid);
				//送信先(パスワード変更)
				resc.setTarget("changepasswordformPR");
			}
			//入力回数が3回を超えた場合
			else {
				System.out.println("アクセスした回数が3回を超えました");
				//ユーザーIDに対応したListを消す
				PRManagement.deleteUserData(userid);
				//送信先(ユーザー検索ページ)
				resc.setTarget("serchuserPR");
			}
		}
		//入力された数字が間違っていた場合
		else {
			//かつ入力回数が3回以下の場合
			if((int)userdata.get(1) < 2) {
				//requestにセットするリスト
				ArrayList requestdata=new ArrayList();

				System.out.println("入力した値があっていません");
				//アクセス回数を増やす
				PRManagement.updateAccessFrequency(userid);
				//requestScopeにユーザーIDと間違えた回数をセットする
				requestdata.add(userid);
				requestdata.add((int)userdata.get(1));
				reqc.setResult(requestdata);
				//送信先(同じページ)
				resc.setTarget("numberformPR");
			}
			//入力回数が3回を超えた場合
			else {
				System.out.println("アクセスした回数が3回を超えました");
				//ユーザーIDに対応したListを消す
				PRManagement.deleteUserData(userid);
				//送信先(ユーザー検索ページ)
				resc.setTarget("serchuserPR");
			}
		}
		return resc;
	}
}
