package test1.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NotifyJsonFileWriter {
	public static void main(String[] args) {
		setCount("-1");
		setJsonData("-1","7","yamada","test/icon7.png","FollowServlet","follow","");
	}

	//jsonファイルのデータを
	//一行ごとにlistの要素に入れて
	//ファイルのデータをすべて取得するメソッド
	public static ArrayList getAllData() {
		//returnするデータ
		ArrayList alldatalist = new ArrayList();

		try {
			// 1.ファイルのパスを指定する
	        File file = new File("C:/pleiades/workspace/test1/src/test1/json/notify.json");

	        // 2.ファイルが存在しない場合に例外が発生するので確認する
	        if (!file.exists()) {
	            System.out.print("ファイルが存在しません");
	        }

	        // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;

            while ((data = bufferedReader.readLine()) != null) {
                //System.out.println(data);
                alldatalist.add(data);
            }
            //System.out.println(alldatalist);
            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alldatalist;
	}

	//通知の閲覧数を増やすメソッド
	//通知ボックスページに飛んだ時に全てに既読を付ける
	//show_countを1にする
	//show_countが0ならヘッダーの通知数をカウントする(未読)
	//show_countが1ならヘッダーの通知数をカウントしない(既読)
	public static void setCount(String userno) {
		try {
	        ArrayList alldatalist = getAllData();

            FileWriter fw = new FileWriter("C:/pleiades/workspace/test1/src/test1/json/notify.json");
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            System.out.println(alldatalist);
            for(int i=0; i<alldatalist.size(); i++) {
            	String getdata = (String)alldatalist.get(i);
            	System.out.println(getdata.indexOf(userno));

            	if(getdata.indexOf(userno) == 1) {
            		//System.out.println(getdata);

            		getdata = getdata.replaceAll("\"show_count\":\"0\"",
            				"\"show_count\":\"1\"");
            		System.out.println("閲覧カウントを増やしました");
            	}
            	alldatalist.set(i, getdata);
            }
            for(int i=0; i<alldatalist.size(); i++) {
            	//ファイルに書き込む
                pw.println(alldatalist.get(i));
            }

            pw.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	//通知データを追加するメソッド
	//jsonデータに通知されたユーザのデータがなければ
	//jsonデータに新しく配列を追加する
	public static void setJsonData(String userno,String requestUserNo,String requestUserName,String requestUserIconPath,
			String servletName,String type,String tweetId) {
		try {
			ArrayList alldatalist = getAllData();

            FileWriter fw = new FileWriter("C:/pleiades/workspace/test1/src/test1/json/notify.json");
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            String showCount = "0";

            int matchcount = 0;
            System.out.println(alldatalist);
            for(int i=0; i<alldatalist.size(); i++) {
            	String getdata = (String)alldatalist.get(i);
            	System.out.println(getdata.indexOf(userno));

            	if(getdata.indexOf(userno) == 1) {
            		//System.out.println(getdata);
            		matchcount++;
            		//通知データに同じデータがあるか確認
            		//なければデータを生成
            		if(getdata.indexOf("\"request_user_no\":\""+requestUserNo+"\","
            				+ "\"request_user_name\":\""+requestUserName+"\","
            				+ "\"request_user_icon_path\":\""+requestUserIconPath+"\","
            				+ "\"servlet_name\":\""+servletName+"\","
            				+ "\"type\":\""+type+"\","
        					+ "\"tweet_id\":\""+tweetId+"\",") == -1) {
            			getdata = getdata.replace("]",
                				",{\"request_user_no\":\""+requestUserNo+"\","
                				+ "\"request_user_name\":\""+requestUserName+"\","
                				+ "\"request_user_icon_path\":\""+requestUserIconPath+"\","
                				+ "\"servlet_name\":\""+servletName+"\","
                				+ "\"type\":\""+type+"\","
            					+ "\"tweet_id\":\""+tweetId+"\","
            					+ "\"show_count\":\""+showCount+"\"}]");
            		}else {
            			System.out.println("すでに通知にありました");
            		}
            	}
            	alldatalist.set(i, getdata);


            }
			//ユーザの通知ボックスがあるか確認
			//なければ通知ボックス(配列)を生成
            if(matchcount != 0) {
            	System.out.println("要素が見つかりました");
            }else {
            	System.out.println("要素が見つかりませんでした");
            	System.out.println("要素を生成します");
            	String str = "\""+userno+"\":[{\"request_user_no\":\""+requestUserNo+"\","
            					+ "\"request_user_name\":\""+requestUserName+"\","
            					+ "\"request_user_icon_path\":\""+requestUserIconPath+"\","
            					+ "\"servlet_name\":\""+servletName+"\","
            					+ "\"type\":\""+type+"\","
            					+ "\"tweet_id\":\""+tweetId+"\","
            					+ "\"show_count\":\""+showCount+"\"}]";
            	alldatalist.set(alldatalist.size()-2, alldatalist.get(alldatalist.size()-2)+",");
            	alldatalist.set(alldatalist.size()-1, str);
            	alldatalist.add("}");

			}
            for(int i=0; i<alldatalist.size(); i++) {
            	//ファイルに書き込む
                pw.println(alldatalist.get(i));
            }

            pw.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
