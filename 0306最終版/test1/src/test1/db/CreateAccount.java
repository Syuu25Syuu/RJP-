/*ユーザーを作成する*/

package test1.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import test1.exception.CheckIdException;
import test1.exception.IntegrationException;

public class CreateAccount {
	public static String createAcount(String userName,String userId,String userPass,String userMail,String userImgPath)
		throws IntegrationException{
		try{
			Connection cn = new OracleConnector().getCn();

	        //自動コミットをOFFにする
	        cn.setAutoCommit(false);

	        System.out.println("接続完了");

	        //SQL文を変数に格納する

	        String sql="insert into users(Users_ID,Users_Name,Users_Pass,Users_Mail,USERS_PROF_IMAGE) values('"+userId+"','"+userName+"','"+userPass+"','"+userMail+"','"+userImgPath+"')";

			//Statementインターフェイスを実装するクラスの
			//インスタンスを取得する
			Statement st=cn.createStatement();

			//SQLを実行しトランザクションが開始される。処理件数が返される
			st.executeUpdate(sql);
	        //トランザクションをコミットする
	        cn.commit();

	        //ステートメントをクローズする
	        st.close();

	        //RDBMSから切断する
	        cn.close();

	        System.out.println("切断完了");


        }catch(SQLException e){
        	e.printStackTrace();
        	throw new CheckIdException(e);
        	//return "アカウントの作成に失敗しました";
        }

		return "アカウントの作成に成功しました";
	}
}


