package test1.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
 パスワードリマインダーを管理するクラス(DB的な感じ)
 このクラス内のMap(management)で
 ユーザID,乱数,アクセス回数を管理する
 */
public class PRManagement {
	//DBのようなMap
	//key:ユーザID   value:ArrayList[乱数,アクセスした回数(入力間違えた数)]
	private static Map management=new HashMap();
	//値をセットするメソッド
	//引数にユーザーIDと乱数を入れる
	public static void setUserData(String userid,int randomValue) {
		//ユーザーのデータのひとまとまり
		ArrayList userdata=new ArrayList();
		//0番目の要素に乱数(キーナンバー)
		userdata.add(randomValue);
		//1番目の要素にアクセスした回数
		userdata.add(0);
		//keyにユーザーID、valueにListを入れる
		management.put(userid, userdata);

		System.out.println("キー(ユーザーID):"+userid);
		System.out.println("ランダムに生成した数:"+randomValue);
		System.out.println("データを作成しました");
	}
	//アクセス回数を増やすメソッド
	public static void updateAccessFrequency(String key) {
		//一時的にkeyにあったListを取得する
		ArrayList instantList=(ArrayList)management.get(key);
		//現在のアクセス回数を取得する(間違えた回数)
		int accessfrequency=(int)instantList.get(1);
		//アクセス回数を+1し、
		//アクセス回数を更新する(Mapのデータを上書きする)
		instantList.set(1, accessfrequency+1);
		management.put(key, instantList);
		System.out.println("アクセス回数を増やしました");
		System.out.println("現在のアクセス回数:"+(accessfrequency+1)+"回");

	}
	//keyに対応するListを取得するメソッド
	public static ArrayList getUserData(String key) {
		return (ArrayList)management.get(key);
	}
	//keyに対応するListを削除するメソッド
	public static void deleteUserData(String key) {
		management.remove(key);
		System.out.println("データを削除しました");
	}//メインメソッドーーーーーーーーーーーーーーー
	public static void main(String[] args) {
		setUserData("test",111);
		ArrayList arrayList= getUserData("test");
		System.out.println(arrayList.get(0));
		System.out.println(arrayList.get(1));
		updateAccessFrequency("test");
		updateAccessFrequency("test");
		deleteUserData("test");
	}
}
