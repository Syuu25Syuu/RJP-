/* 自分が他のユーザーをフォローしているかどうかを判定する */

package test1.file;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test1.db.OracleConnector;
import test1.exception.BussinessLogicException;
import test1.exception.IntegrationException;

public class ChangeImageName {
	public static String changeImageName(String imageName) throws BussinessLogicException{	//サーブレットから受け取る画像ファイル

		String fileName ="";	//サーブレットに戻す画像の名前
        try{
	        //Driverインターフェイスを実装するクラスをロードする
	        Connection cn = new OracleConnector().getCn();
	        //自動コミットをOFFにする

	        //System.out.println("接続完了");

	        //SQL文を変数に格納する
	        String sql="select TWEETS_IMAGE from tweets where tweets_image is not null order by TWEETS_DATE desc";		//最新の画像をDBから取ってくる

	        Statement st= cn.createStatement();

	        ResultSet rs = st.executeQuery(sql);

	        boolean flg = false;
	        while(rs.next()){
	        	String beforeName = rs.getString(1);	//前の画像の名前
	        	String extension = imageName.substring(imageName.lastIndexOf("."));		//サーブレットから受け取った画像の拡張子だけを取得

	   		 	String[] afterName = beforeName.split("\\.");	//前の画像の拡張子以外を取得

	   		 	String notExtension = afterName[0];

	   		 	notExtension = notExtension.replace("/test1/images/uploaded/", "");		//パスを除去

	   		 	Integer fileIndex = Integer.parseInt(notExtension);
	   		 	fileIndex ++;
	   		 	String fileIndexString = fileIndex.toString();
	   		 	fileName = fileIndexString+extension;
	   		 	flg = true;
	        	if(flg == true) {	//一回目で辞めたいのでブレイク
	        		break;
	        	}
	         }

	        cn.close();


        }catch(SQLException e){
        	e.printStackTrace();
        	throw new BussinessLogicException();
        }catch(IntegrationException e){
        	e.printStackTrace();
        	throw new BussinessLogicException();
        }


		return fileName;
	}

}
