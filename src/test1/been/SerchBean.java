package test1.been;

import java.io.Serializable;

public class SerchBean implements Serializable{

	private String username;
	private String userid;
	private String userno;
	private String check;


	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public SerchBean() {}

	public void setUserName(String username) {
		this.username=username;
	}
	public String getUserName(){
		return username;
	}

	public void setUserId(String userid) {
		this.userid=userid;
	}
	public String getUserId(){
		return userid;
	}

	public void setUserNo(String userno) {
		this.userno=userno;
	}
	public String getUserNo(){
		return userno;
	}
}
