/*Oracleに接続する用のクラス*/

package test1.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import test1.exception.IntegrationException;


public class OracleConnector{
	static Connection cn;

	public OracleConnector() throws IntegrationException{
		String user = "paku";
		String pass = "paku";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",user,pass);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("クラスがないみたい");
			throw new IntegrationException(e);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関係の例外みたい");
			throw new IntegrationException(e);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("例外みたい");
			throw new IntegrationException(e);
		}
	}
	public Connection getCn(){
		return cn;
	}
	public static void closeConnection(Connection cn)throws SQLException{
		cn.close();
	}

	public static void closeConnection(Object cnObject)throws SQLException{
		cn = (Connection)cnObject;
		closeConnection(cn);
	}
}