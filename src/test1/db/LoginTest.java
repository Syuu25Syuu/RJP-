package test1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginTest{
	public static String insertUser_Table(String userID,String UserPass){
        String a ="";
        try{
        //Driverインターフェイスを実装するクラスをロードする
        Connection cn = new OracleConnector().getCn();
        //自動コミットをOFFにする
        cn.setAutoCommit(false);

        System.out.println("接続完了");

        //SQL文を変数に格納する
        String sql="select Users_SerialNo from users where users_pass = '"+UserPass+"' AND users_id = '"+userID+"'";

        //Statementインターフェイスを実装するクラスの
        //インスタンスを取得する
        Statement st= cn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
        	a=rs.getString(1);
        	System.out.println("シリアルナンバー:"+a);
         }

        //トランザクションをコミットする
        cn.commit();

        rs.close();

        //ステートメントをクローズする
        st.close();

        //RDBMSから切断する
        cn.close();

        System.out.println("切断完了");


        }catch(SQLException e){
        	e.printStackTrace();
        }

        return a;

        }
}
