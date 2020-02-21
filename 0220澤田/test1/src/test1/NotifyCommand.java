package test1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import test1.been.NotifyBean;

public class NotifyCommand extends AbstractCommand{
	@Override
	public ResponseContext execute() {

		RequestContext reqc=getRequestContext();
		ResponseContext resc = new WebResponseContext();

		//セッションの取得
		String sessionUserNo=reqc.getParameter("session_id")[0];
		//String requestUserNo=reqc.getParameter("followed_no")[0];

		//最終的に送出するリスト
		ArrayList data=new ArrayList();
		//json関係
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();

	    try {
	        JsonNode node = mapper.readTree(new File("C:\\pleiades\\workspace\\test1\\src\\test1\\notify.json"));

	        for(int i=0; i < node.get(sessionUserNo).size(); i++) {

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

		        NotifyBean nb = new NotifyBean();
		        nb.setRequestUserNo(request_user_no);
		        nb.setRequestUserName(request_user_name);
		        nb.setRequestUserIconPath(request_user_icon_path);
		        nb.setServletName(servlet_name);
		        nb.setType(type);
		        nb.setTweetId(tweet_id);

		        data.add(nb);
	        }
	        System.out.println(data);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    //リクエストに値をセット
	    reqc.setResult(data);
	    //ページのパスを指定
	  	resc.setTarget("notify");

		return resc;
	}
}
