package test1.json;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class NotifyJsonFileReader {

	public static void main(String[] args) {
		NotifyJsonFileReader njfr = new NotifyJsonFileReader();
		njfr.getNotifyData("-1");
		njfr.getNotifyCount("-1");
	}
	//JsonNodeを取得するメソッド
	public static JsonNode getJsonNode() {
		//json関係
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;

	    try {
	        node = mapper.readTree(new File("C:/eclipse/pleiades/workspace/test1/src/test1/json/notify.json"));
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    return node;
	}

	//ユーザの通知データを取得するメソッド
	public static ArrayList getNotifyData(String sessionUserNo) {
		//最終的に送出するリスト
		ArrayList notifydata=new ArrayList();

		JsonNode node = getJsonNode();
		//通知があるか確認
		if(node.get(sessionUserNo) != null) {
			for(int i=node.get(sessionUserNo).size()-1; i >= 0; i--) {
	        	ArrayList data=new ArrayList();

	        	String request_user_no = node.get(sessionUserNo).get(i).get("request_user_no").asText();
		        System.out.println("いいねなどをしたユーザNo:"+request_user_no);

		        String request_user_name = node.get(sessionUserNo).get(i).get("request_user_name").asText();
		        System.out.println("いいねなどをしたユーザの名前:"+request_user_name);

		        String request_user_icon_path = node.get(sessionUserNo).get(i).get("request_user_icon_path").asText();
		        System.out.println("画像のパス:"+request_user_icon_path);

		        String servlet_name = node.get(sessionUserNo).get(i).get("servlet_name").asText();
		        System.out.println("サーブレットの名前:"+servlet_name);

		        String type = node.get(sessionUserNo).get(i).get("type").asText();
		        System.out.println("種類:"+type);

		        String tweet_id = node.get(sessionUserNo).get(i).get("tweet_id").asText();
		        System.out.println("ツイートID:"+tweet_id);

		        //show_countは通知数を表示するときしか使わないので
		        //リストに追加する必要はない
		        String show_count = node.get(sessionUserNo).get(i).get("show_count").asText();
		        System.out.println("show_count:"+show_count);

		        data.add(request_user_no);
		        data.add(request_user_name);
		        data.add(request_user_icon_path);
		        data.add(servlet_name);
		        data.add(type);
		        data.add(tweet_id);

		        notifydata.add(data);
	        }
		}else {
			System.out.println("通知がありません");

		}
	    System.out.println(notifydata);
	    return notifydata;
	}

	//ユーザの通知数を取得するメソッド
	public static int getNotifyCount(String sessionUserNo) {
		int count = 0;
		JsonNode node = getJsonNode();

		if(node.get(sessionUserNo) != null) {
			for(int i=0; i<node.get(sessionUserNo).size(); i++) {
				int showcount = node.get(sessionUserNo).get(i).get("show_count").asInt();

				if(showcount == 0) {
					count++;
				}
			}
			System.out.println("通知数:"+count);
		}else {
			System.out.println("通知がありませんでした");
		}
		return count;
	}
}
