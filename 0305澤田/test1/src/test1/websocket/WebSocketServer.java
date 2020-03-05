package test1.websocket;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import test1.db.AdditionDataLoadDMTest;
import test1.db.OracleConnector;

@ServerEndpoint("/WebSocketServer")

public class WebSocketServer {

	@OnMessage
	//メインの処理をするとこ
    public String onMessage(String message) {
        /* メッセージ受信時の処理 */
		//引数がStringなのでカンマ区切りで
		//三つの値を渡されてきてるので
		//渡された変数をString[]に値を三つ格納
		String[] a=message.split(",");
        System.out.println("WebSocket受信：" + message+a[0]+a[1]+a[2]);
        //DBにつなぐよう
        Connection cn = new OracleConnector().getCn();
        //最終的に送るデータ
        //戻り値がStringなので最終的に
        //カンマ区切りでデータを一つの文字列として送る
        String data="";
        //DBに接続し現在表示されているメッセージの送信時間以降の
        //メッセージのデータを取得する
        ArrayList list=AdditionDataLoadDMTest.getDMContent(a[0],a[1],a[2],cn);
        Iterator iterator=list.iterator();
        while (iterator.hasNext()) {
        	//一件分のメッセージのデータが入る(一時的)
			ArrayList datalist = (ArrayList)iterator.next();
			System.out.println(datalist);
			//一件分のメッセージのデータを
			//"ユーザID,ユーザ名,メッセージ内容,送信者No,受信者No,送信時間|"
			//となるようにString変数に入れていく
			for(int i=0; i<datalist.size(); i++) {
				data+=datalist.get(i);
				if(i!=datalist.size()-1) {
					data+=",";
				}
			}
			data+="|";
		}
        System.out.println(data);
        return data;  //今回追加する部分
    }

    @OnError
    public void onError(Throwable th) {
        /* エラー発生時の処理 */
        System.out.println("WebSocketエラー：" + th.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        /* セッション解放時の処理 */
        System.out.println("WebSocketセッション確立");
    }
}
