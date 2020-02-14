package test1.been;

import java.io.Serializable;

public class SerchBean implements Serializable{

	private String username;
	private String userid;
	private String userno;
	private String check;
	private String dmcontent;
	private String senduserno;
	private String receiveuserno;
	private String dmtime;
	private String usericon;


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

	public String getDmContent() {
		return dmcontent;
	}

	public void setDmContent(String dmcontent) {
		this.dmcontent = dmcontent;
	}

	public String getSendUserNo() {
		return senduserno;
	}

	public void setSendUserNo(String senduserno) {
		this.senduserno = senduserno;
	}

	public String getReceiveUserNo() {
		return receiveuserno;
	}

	public void setReceiveUserNo(String receiveuserno) {
		this.receiveuserno = receiveuserno;
	}

	public String getDmTime() {
		return dmtime;
	}

	public void setDmTime(String dmtime) {
		this.dmtime = dmtime;
	}

	public String getUserIcon() {
		return usericon;
	}

	public void setUserIcon(String usericon) {
		this.usericon = usericon;
	}
}
